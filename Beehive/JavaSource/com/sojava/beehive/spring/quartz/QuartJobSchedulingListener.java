package com.sojava.beehive.spring.quartz;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.scheduling.quartz.CronTriggerBean;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;

import com.sojava.beehive.framework.exception.ErrorException;

public class QuartJobSchedulingListener implements ApplicationListener<ContextRefreshedEvent> {

	@Resource private Scheduler scheduler;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		try {
			ApplicationContext applicationContext = event.getApplicationContext();
			List<CronTriggerBean> cronTriggerBeans = this.loadCronTriggerBeans(applicationContext);
			this.scheduleJobs(cronTriggerBeans);
		}
		catch (Exception e) {
			new ErrorException(this.getClass(), e);
		}
	}

	private List<CronTriggerBean> loadCronTriggerBeans(ApplicationContext applicationContext) {
		Map<String, Object> quartzJobBeans = applicationContext.getBeansWithAnnotation(QuartzJob.class);
		Set<String> beanNames = quartzJobBeans.keySet();
		List<CronTriggerBean> cronTriggerBeans = new ArrayList<CronTriggerBean>();
		for (String beanName: beanNames) {
			CronTriggerBean cronTriggerBean = null;
			Object object = quartzJobBeans.get(beanName);
			try {
				cronTriggerBean = this.buildCronTriggerBean(object);
			} catch (Exception e) {
				new ErrorException(this.getClass(), e);
			}
			
			if(cronTriggerBean != null) cronTriggerBeans.add(cronTriggerBean);
		}
		return cronTriggerBeans;
	}
	
	public CronTriggerBean buildCronTriggerBean(Object job) throws Exception {
		CronTriggerFactoryBean cronTriggerFactoryBean = null;
		QuartzJob quartzJobAnnotation = AnnotationUtils.findAnnotation(job.getClass(), QuartzJob.class);
		if(Job.class.isAssignableFrom(job.getClass())) {
			cronTriggerFactoryBean = new CronTriggerFactoryBean();
			cronTriggerFactoryBean.setCronExpression(quartzJobAnnotation.cronExp());
			cronTriggerFactoryBean.setName(quartzJobAnnotation.name()+"_trigger");
			cronTriggerFactoryBean.setGroup(quartzJobAnnotation.group());

			JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
			jobDetailFactoryBean.setName(quartzJobAnnotation.name());
			jobDetailFactoryBean.setGroup(quartzJobAnnotation.group());
			jobDetailFactoryBean.setJobClass(job.getClass());
			cronTriggerFactoryBean.setJobDetail(jobDetailFactoryBean.getObject());			
		}
		else throw new RuntimeException(job.getClass() + " doesn't implemented " + Job.class);

		return (CronTriggerBean) cronTriggerFactoryBean.getObject();
	}

	protected void scheduleJobs(List<CronTriggerBean> cronTriggerBeans) {
		for (CronTriggerBean cronTriggerBean : cronTriggerBeans) {
			JobDetail jobDetail = cronTriggerBean.getJobDetail();
			try {
				scheduler.scheduleJob(jobDetail, cronTriggerBean);
			} catch (SchedulerException e) {
				new ErrorException(this.getClass(), e);
			}			
		}
	}
}