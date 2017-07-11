package com.sojava.beehive.spring.quartz;

import javax.annotation.Resource;

import org.quartz.Job;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

public class SpringQuartzJobFactory extends SpringBeanJobFactory {

	@Resource private ApplicationContext ctx;

	@Override
	protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
		Job job = ctx.getBean(bundle.getJobDetail().getJobClass());
	    BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(job);
	    MutablePropertyValues pvs = new MutablePropertyValues();
	    pvs.addPropertyValues(bundle.getJobDetail().getJobDataMap());
	    pvs.addPropertyValues(bundle.getTrigger().getJobDataMap());
	    bw.setPropertyValues(pvs, true);

	    return job;
	}	
}