package com.sojava.beehive.framework.component.medicalimaging.service.impl;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.sojava.beehive.framework.component.medicalimaging.bean.MiExecuted;
import com.sojava.beehive.framework.component.medicalimaging.bean.MiExecutedPK;
import com.sojava.beehive.framework.component.medicalimaging.bean.MiWorkload;
import com.sojava.beehive.framework.component.medicalimaging.bean.VMiExecutedStaffPerformance;
import com.sojava.beehive.framework.component.medicalimaging.bean.WorkStatistic;
import com.sojava.beehive.framework.component.medicalimaging.dao.MiExecutedDao;
import com.sojava.beehive.framework.component.medicalimaging.service.MiExecutedService;
import com.sojava.beehive.framework.define.Page;
import com.sojava.beehive.framework.exception.ErrorException;
import com.sojava.beehive.framework.math.Arith;
import com.sojava.beehive.framework.util.FormatUtil;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class MiExecutedServiceImpl implements MiExecutedService {

	@Resource private MiExecutedDao miExecutedDao;

	@Override
	public void importRecords(InputStream in, String kind) throws Exception {
		List<MiExecuted> records = new ArrayList<MiExecuted>();
		String[] msg = new String[2];

		try {
			msg[0] = "准备";
			msg[1] = "读取数据";
			HSSFWorkbook book = new HSSFWorkbook(in);
			HSSFSheet sheet = book.getSheetAt(0);
			for (int r = 3; r <= sheet.getLastRowNum(); r ++) {
				msg[0] = "第" + (r+1) + "行 ";
				HSSFRow row = sheet.getRow(r);
				MiExecutedPK recordPk = new MiExecutedPK();
				MiExecuted record = new MiExecuted();
				for (int c = 1; c < row.getLastCellNum(); c ++) {
					HSSFCell cell = row.getCell(c);
					String value = cell.getStringCellValue();
					switch (c) {
					case 1:
						msg[1] = "读取\"患者姓名\"";
						record.setPatientName(value);
						break;
					case 2:
						msg[1] = "读取\"检查号\"[PK]";
						recordPk.setMedicalNo(value);
						break;
					case 3:
						msg[1] = "读取\"住院号\"";
						record.setInhospitalNo(value);
						break;
					case 4:
						msg[1] = "读取\"登记号\"";
						record.setRegisterNo(value);
						break;
					case 5:
						msg[1] = "读取\"检查科室\"";
						record.setExecuteDept(value);
						break;
					case 6:
						msg[1] = "读取\"患者类型\"";
						record.setPatientType(value);
						break;
					case 7:
						msg[1] = "读取\"检查项目\"[PK]";
						recordPk.setMedicalItem(value);
						break;
					case 8:
						msg[1] = "读取\"检查部位\"";
						record.setMedicalPart(value);
						break;
					case 9:
						msg[1] = "读取\"状态\"";
						record.setStatus(value);
						break;
					case 10:
						msg[1] = "读取\"操作技师\"";
						record.setExecuteTechnician(value);
						record.setExecuteTechnicianStaffId(miExecutedDao.getStaffId(record.getExecuteTechnician()));
						break;
					case 11:
						msg[1] = "读取\"辅助技师\"";
						record.setExecuteTechnicianAssociate(value);
						record.setExecuteTechnicianAssociateStaffId(miExecutedDao.getStaffId(record.getExecuteTechnicianAssociate()));
						break;
					case 12:
						msg[1] = "读取\"报告医生\"";
						record.setExecuteDiagnostician(value);
						record.setExecuteDiagnosticianStaffId(miExecutedDao.getStaffId(record.getExecuteDiagnostician()));
						break;
					case 13:
						msg[1] = "读取\"审核医生\"";
						record.setExecuteVerifier(value);
						record.setExecuteVerifierStaffId(miExecutedDao.getStaffId(record.getExecuteVerifier()));
						break;
					case 14:
						msg[1] = "读取\"分诊护士\"";
						record.setExecuteNurse(value);
						record.setExecuteNurseStaffId(miExecutedDao.getStaffId(record.getExecuteNurse()));
						break;
					case 15:
						msg[1] = "读取\"申请医生\"";
						record.setApplyDoctor(value);
						break;
					case 16:
						msg[1] = "读取\"申请科室\"";
						record.setApplyDept(value);
						break;
					case 17:
						msg[1] = "处理\"登记日期\"";
						value = value.trim().equals("") ? "1901-01-01 00:00:00" : value;
						record.setRegisterTime(FormatUtil.parseDateTime(value));
						break;
					case 18:
						msg[1] = "处理\"报告日期\"";
						value = value.trim().equals("") ? "1901-01-01 00:00:00" : value;
						record.setReportTime(FormatUtil.parseDateTime(value));
						break;
	
					default:
						break;
					}
				}
				msg[1] = "辨别类型[type]";
				record.setType(recordPk.getMedicalNo().substring(0, 2).toUpperCase());
				msg[1] = "设置分类[kind]";
				record.setKind(kind);
				msg[1] = "设置主键";
				record.setId(recordPk);
				msg[1] = "处理合并项目";
				if (recordPk.getMedicalItem().indexOf(",") == -1) {
					msg[1] = "非合并项目";
					records.add(record);
				} else {
					msg[1] = "拆分合并项目";
					records.addAll(recordSplit(record));
				}
			}
			msg[0] = "保存数据";
			msg[1] = "";
			miExecutedDao.save(records.toArray(new MiExecuted[0]));
		}
		catch(Exception ex) {
			throw new ErrorException(this.getClass(), msg[0] + msg[1] + ":" + ex.getMessage());
		}
	}

	public List<MiExecuted> recordSplit(MiExecuted miExecuted) {
		List<MiExecuted> result = new ArrayList<MiExecuted>();
		MiExecutedPK miExecutedPk = miExecuted.getId();
		String[] medicalItems = miExecutedPk.getMedicalItem().split(",");
		for (int i = 0; i < medicalItems.length; i ++) {
			MiExecuted rec = new MiExecuted(miExecuted);
			MiExecutedPK recPk = rec.getId();
			recPk.setMedicalItem(medicalItems[i]);
			result.add(rec);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public WorkStatistic findWorkStatistic(int year, int month) throws Exception {

		WorkStatistic workStatistic = null;
		List<WorkStatistic> list = (List<WorkStatistic>) miExecutedDao.query(WorkStatistic.class, new Criterion[]{Restrictions.eq("year", year), Restrictions.eq("month", month)}, null, null, false);
		if (list != null && list.size() > 0) {
			workStatistic = list.get(0);
		} else {
			workStatistic = new WorkStatistic();
			workStatistic.setYear(year);
			workStatistic.setMonth(month);
			String _month = "0" + month;
			_month = _month.length() > 2 ? _month.substring(1) : _month;
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(FormatUtil.parseDate(year + "-" + _month + "-26"));
			calendar.set(Calendar.MONTH, month-2);
			workStatistic.setBeginDate(calendar.getTime());
			workStatistic.setEndDate(FormatUtil.parseDate(year + "-" + _month + "-25"));
			workStatistic.setBudget(0d);
			workStatistic.setOvertimeCost(0d);
			workStatistic.setNurseRate(5d);
			workStatistic.setNurseCost(0d);
			workStatistic.setPerformanceTotal(0d);
			workStatistic.setMedicalRate(92d);
			workStatistic.setMedicalTotal(0d);
			workStatistic.setManageRate(8d);
			workStatistic.setManageTotal(0d);
		}
		return workStatistic;
	}

	@SuppressWarnings("unchecked")
	@Override
	public VMiExecutedStaffPerformance[] findStaffPerformance(WorkStatistic workStatistic, Page page, boolean scale) throws Exception {
		List<VMiExecutedStaffPerformance> list = (List<VMiExecutedStaffPerformance>) miExecutedDao.query(
				VMiExecutedStaffPerformance.class,
				new Criterion[]{Restrictions.eq("workStatisticsId", workStatistic.getId())},
				new Order[]{Order.asc("groupId"), Order.asc("staffId")},
				page,
				false
			);
		double jobCoefTotal = 0d;
		for (VMiExecutedStaffPerformance staffPerformance: list) {
			jobCoefTotal += staffPerformance.getStaffJobCoef() != null ? staffPerformance.getStaffJobCoef() : 0d;
		}
/*
		String groupName = "";
		double medicalTotal_ = 0d,
				totalAmount_ = 0d,
				techPointTotal_ = 0d,
				diagnoPointTotal_ = 0d,
				assistPointTotal_ = 0d,
				pointTotal_ = 0d;
		double medicalTotal__ = 0d,
				totalAmount__ = 0d,
				techPointTotal__ = 0d,
				diagnoPointTotal__ = 0d,
				assistPointTotal__ = 0d,
				pointTotal__ = 0d;
*/
		for (int i = 0; i < list.size(); i ++) {
			VMiExecutedStaffPerformance staffPerformance = list.get(i);

			if (staffPerformance.getStaffJobCoef() != null) {
				staffPerformance.setManageTotal(Arith.mul(
													Arith.div(workStatistic.getManageTotal(), jobCoefTotal),
													staffPerformance.getStaffJobCoef()
												));
			}

			double techPointTotal = staffPerformance.getTechPointTotal() == null ? 0d : staffPerformance.getTechPointTotal();
			double techWorkerQuantity = staffPerformance.getTechWorkerQuantity() == null ? 0d : staffPerformance.getTechWorkerQuantity();
			double techWorkerStatisQuantity = staffPerformance.getTechWorkerStatisQuantity() == null ? 0d : staffPerformance.getTechWorkerStatisQuantity();
			staffPerformance.setTechPointTotal(scale ? FormatUtil.formatDecimal(techPointTotal, 4) : techPointTotal);
			staffPerformance.setTechWorkerQuantity(techWorkerQuantity);
			staffPerformance.setTechWorkerStatisQuantity(techWorkerStatisQuantity);
			double diagnoPointTotal = staffPerformance.getDiagnoPointTotal() == null ? 0d : staffPerformance.getDiagnoPointTotal();
			double diagnoWorkerQuantity = staffPerformance.getDiagnoWorkerQuantity() == null ? 0d : staffPerformance.getDiagnoWorkerQuantity();
			double diagnoWorkStatisQuantity = staffPerformance.getDiagnoWorkerStatisQuantity() == null ? 0d : staffPerformance.getDiagnoWorkerStatisQuantity();
			staffPerformance.setDiagnoPointTotal(scale ? FormatUtil.formatDecimal(diagnoPointTotal, 4) : diagnoPointTotal);
			staffPerformance.setDiagnoWorkerQuantity(diagnoWorkerQuantity);
			staffPerformance.setDiagnoWorkerStatisQuantity(diagnoWorkStatisQuantity);
			double assistPointTotal = staffPerformance.getAssistPointTotal() == null ? 0d : staffPerformance.getAssistPointTotal();
			double assistWorkerQuantity = staffPerformance.getAssistWorkerQuantity() == null ? 0d : staffPerformance.getAssistWorkerQuantity();
			double assistWorkerStatisQuantity = staffPerformance.getAssistWorkerStatisQuantity() == null ? 0d : staffPerformance.getAssistWorkerStatisQuantity();
			staffPerformance.setAssistPointTotal(scale ? FormatUtil.formatDecimal(assistPointTotal, 4) : assistPointTotal);
			staffPerformance.setAssistWorkerQuantity(assistWorkerQuantity);
			staffPerformance.setAssistWorkerStatisQuantity(assistWorkerStatisQuantity);
			double pointTotal = techPointTotal + diagnoPointTotal + assistPointTotal;
			staffPerformance.setPointTotal(scale ? FormatUtil.formatDecimal(pointTotal, 4) : pointTotal);

			double techWorkerAmount = staffPerformance.getTechWorkerAmount() == null ? 0d : staffPerformance.getTechWorkerAmount();
			double diagnoWorkerAmount = staffPerformance.getDiagnoWorkerAmount() == null ? 0d : staffPerformance.getDiagnoWorkerAmount();
			double assistWorkerAmount = staffPerformance.getAssistWorkerAmount() == null ? 0d : staffPerformance.getAssistWorkerAmount();
			double medicalTotal = Arith.mul(pointTotal, workStatistic.getPointValue());//techWorkerAmount + diagnoWorkerAmount + assistWorkerAmount;
			double manageTotal = staffPerformance.getManageTotal() == null ? 0d : staffPerformance.getManageTotal();
			double totalAmount = medicalTotal + manageTotal;
			staffPerformance.setTechWorkerAmount(scale ? FormatUtil.formatDecimal(techWorkerAmount, 2) : techWorkerAmount);
			staffPerformance.setDiagnoWorkerAmount(scale ? FormatUtil.formatDecimal(diagnoWorkerAmount, 2) : diagnoWorkerAmount);
			staffPerformance.setAssistWorkerAmount(scale ? FormatUtil.formatDecimal(assistWorkerAmount, 2) : assistWorkerAmount);
			staffPerformance.setMedicalTotal(scale ? FormatUtil.formatDecimal(medicalTotal, 2) : medicalTotal);
			staffPerformance.setManageTotal(scale ? FormatUtil.formatDecimal(manageTotal, 2) : manageTotal);
			staffPerformance.setTotalAmount(scale ? FormatUtil.formatDecimal(totalAmount, 2) : totalAmount);
/*
			String _groupName = staffPerformance.getGroupName();
			if (!groupName.equals(_groupName) || i == list.size()-1) {
				VMiExecutedStaffPerformance amountItem = null;
				if (i > 0 || i == list.size()-1) {
					amountItem = new VMiExecutedStaffPerformance();
					amountItem.setGroupName(_groupName);
					amountItem.setStaffName("合计");
					amountItem.setMedicalTotal(scale ? FormatUtil.formatDecimal(medicalTotal_, 2) : medicalTotal_);
					amountItem.setTotalAmount(scale ? FormatUtil.formatDecimal(totalAmount_, 2) : totalAmount_);
					amountItem.setTechPointTotal(scale ? FormatUtil.formatDecimal(techPointTotal_,  4) : techPointTotal_);
					amountItem.setDiagnoPointTotal(scale ? FormatUtil.formatDecimal(diagnoPointTotal_, 4) : diagnoPointTotal_);
					amountItem.setAssistPointTotal(scale ? FormatUtil.formatDecimal(assistPointTotal_, 4) : assistPointTotal_);
					amountItem.setPointTotal(scale ? FormatUtil.formatDecimal(pointTotal_, 4) : pointTotal_);

					medicalTotal_ = 0d;
					totalAmount_ = 0d;
					techPointTotal_ = 0d;
					diagnoPointTotal_ = 0d;
					assistPointTotal_ = 0d;
					pointTotal_ = 0d;
				}

				if (i == list.size()-1) {
					list.add(amountItem);
					VMiExecutedStaffPerformance totalAmountItem = new VMiExecutedStaffPerformance();
					totalAmountItem.setGroupName("");
					totalAmountItem.setStaffName("总计");
					totalAmountItem.setMedicalTotal(scale ? FormatUtil.formatDecimal(medicalTotal__, 2) : medicalTotal__);
					totalAmountItem.setTotalAmount(scale ? FormatUtil.formatDecimal(totalAmount__, 2) : totalAmount__);
					totalAmountItem.setTechPointTotal(scale ? FormatUtil.formatDecimal(techPointTotal__, 4) : techPointTotal__);
					totalAmountItem.setDiagnoPointTotal(scale ? FormatUtil.formatDecimal(diagnoPointTotal__, 4) : diagnoPointTotal__);
					totalAmountItem.setAssistPointTotal(scale ? FormatUtil.formatDecimal(assistPointTotal__, 4) : assistPointTotal__);
					totalAmountItem.setPointTotal(scale ? FormatUtil.formatDecimal(pointTotal__, 4) : pointTotal__);
					list.add(totalAmountItem);
					break;
				} else if (i > 0) {
					list.add(i, amountItem);
				}

				groupName = _groupName;
			} else {
				medicalTotal_ += medicalTotal;
				totalAmount_ += totalAmount;
				techPointTotal_ += techPointTotal;
				diagnoPointTotal_ += diagnoPointTotal;
				assistPointTotal_ += assistPointTotal;
				pointTotal_ += pointTotal;
	
				medicalTotal__ += medicalTotal;
				totalAmount__ += totalAmount;
				techPointTotal__ += techPointTotal;
				diagnoPointTotal__ += diagnoPointTotal;
				assistPointTotal__ += assistPointTotal;
				pointTotal__ += pointTotal;
			}
			if (i == 0) {
				medicalTotal_ += medicalTotal;
				totalAmount_ += totalAmount;
				techPointTotal_ += techPointTotal;
				diagnoPointTotal_ += diagnoPointTotal;
				assistPointTotal_ += assistPointTotal;
				pointTotal_ += pointTotal;
	
				medicalTotal__ += medicalTotal;
				totalAmount__ += totalAmount;
				techPointTotal__ += techPointTotal;
				diagnoPointTotal__ += diagnoPointTotal;
				assistPointTotal__ += assistPointTotal;
				pointTotal__ += pointTotal;
			}
*/
		}

		return list.toArray(new VMiExecutedStaffPerformance[0]);
	}

	@SuppressWarnings("unchecked")
	@Override
	public MiWorkload[] findWorkload(WorkStatistic workStatistic, String type, String kind, Page page) throws Exception {
		List<MiWorkload> list = (List<MiWorkload>) miExecutedDao.query(MiWorkload.class, new Criterion[]{
				Restrictions.eq("workStatistic.id", workStatistic.getId()),
				Restrictions.eq("type", type),
				Restrictions.eq("kind", kind)
			}, null, page, false);
		return list.toArray(new MiWorkload[0]);
	}

	public MiExecutedDao getMiExecutedDao() {
		return miExecutedDao;
	}

	public void setMiExecutedDao(MiExecutedDao miExecutedDao) {
		this.miExecutedDao = miExecutedDao;
	}

}
