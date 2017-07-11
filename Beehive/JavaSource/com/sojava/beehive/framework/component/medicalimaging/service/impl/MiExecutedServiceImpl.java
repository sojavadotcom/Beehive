package com.sojava.beehive.framework.component.medicalimaging.service.impl;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import com.sojava.beehive.framework.component.medicalimaging.bean.MiExecuted;
import com.sojava.beehive.framework.component.medicalimaging.bean.MiExecutedPK;
import com.sojava.beehive.framework.component.medicalimaging.dao.MiExecutedDao;
import com.sojava.beehive.framework.component.medicalimaging.service.MiExecutedService;
import com.sojava.beehive.framework.util.FormatUtil;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class MiExecutedServiceImpl implements MiExecutedService {

	@Resource private MiExecutedDao miExecutedDao;

	@Override
	public void importRecords(InputStream in, String kind) throws Exception {
		List<MiExecuted> records = new ArrayList<MiExecuted>();

		HSSFWorkbook book = new HSSFWorkbook(in);
		HSSFSheet sheet = book.getSheetAt(0);
		for (int r = 3; r <= sheet.getLastRowNum(); r ++) {
			HSSFRow row = sheet.getRow(r);
			MiExecutedPK recordPk = new MiExecutedPK();
			MiExecuted record = new MiExecuted();
			for (int c = 1; c < row.getLastCellNum(); c ++) {
				HSSFCell cell = row.getCell(c);
				switch (c) {
				case 1:
					record.setPatientName(cell.getStringCellValue());
					break;
				case 2:
					recordPk.setMedicalNo(cell.getStringCellValue());
					break;
				case 3:
					record.setInhospitalNo(cell.getStringCellValue());
					break;
				case 4:
					record.setRegisterNo(cell.getStringCellValue());
					break;
				case 5:
					record.setExecuteDept(cell.getStringCellValue());
					break;
				case 6:
					record.setPatientType(cell.getStringCellValue());
					break;
				case 7:
					recordPk.setMedicalItem(cell.getStringCellValue());
					break;
				case 8:
					record.setMedicalPart(cell.getStringCellValue());
					break;
				case 9:
					record.setStatus(cell.getStringCellValue());
					break;
				case 10:
					record.setExecuteTechnician(cell.getStringCellValue());
					record.setExecuteTechnicianStaffId(miExecutedDao.getStaffId(record.getExecuteTechnician()));
					break;
				case 11:
					record.setExecuteTechnicianAssociate(cell.getStringCellValue());
					record.setExecuteTechnicianAssociateStaffId(miExecutedDao.getStaffId(record.getExecuteTechnicianAssociate()));
					break;
				case 12:
					record.setExecuteDiagnostician(cell.getStringCellValue());
					record.setExecuteDiagnosticianStaffId(miExecutedDao.getStaffId(record.getExecuteDiagnostician()));
					break;
				case 13:
					record.setExecuteVerifier(cell.getStringCellValue());
					record.setExecuteVerifierStaffId(miExecutedDao.getStaffId(record.getExecuteVerifier()));
					break;
				case 14:
					record.setExecuteNurse(cell.getStringCellValue());
					record.setExecuteNurseStaffId(miExecutedDao.getStaffId(record.getExecuteNurse()));
					break;
				case 15:
					record.setApplyDoctor(cell.getStringCellValue());
					break;
				case 16:
					record.setApplyDept(cell.getStringCellValue());
					break;
				case 17:
					record.setRegisterTime(FormatUtil.parseDateTime(cell.getStringCellValue()));
					break;
				case 18:
					record.setReportTime(FormatUtil.parseDateTime(cell.getStringCellValue()));
					break;

				default:
					break;
				}
			}
			record.setType(recordPk.getMedicalNo().substring(0, 2).toUpperCase());
			record.setKind(kind);
			record.setId(recordPk);
			if (recordPk.getMedicalItem().indexOf(",") == -1) {
				records.add(record);
			} else {
				records.addAll(recordSplit(record));
			}
		}
		miExecutedDao.save(records.toArray(new MiExecuted[0]));
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

	public MiExecutedDao getMiExecutedDao() {
		return miExecutedDao;
	}

	public void setMiExecutedDao(MiExecutedDao miExecutedDao) {
		this.miExecutedDao = miExecutedDao;
	}

}
