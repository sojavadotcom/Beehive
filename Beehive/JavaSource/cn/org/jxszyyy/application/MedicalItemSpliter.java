package cn.org.jxszyyy.application;

import com.sojava.beehive.common.datasource.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MedicalItemSpliter {
	private final static String DB_DRIVER_NAME = "org.postgresql.Driver";
	private final static String DB_URL = "jdbc:postgresql://127.0.0.1:5432/postgres";
	private final static String DB_USER_NAME = "postgres";
	private final static String DB_PASSWORD = "johnjia";
	private Connection connect;

	public static void main(String[] args) {
		MedicalItemSpliter spliter = new MedicalItemSpliter();
		spliter.split();
	}

	public void split() {
		try {
			System.out.println("Starting....");
			DBConnector connector = new DBConnector(DB_DRIVER_NAME, DB_URL, DB_USER_NAME, DB_PASSWORD);
			connect = connector.getConnect();
			String sql = "select * from medicalinnovation.medical_examination where strpos(medical_item, ',')>0";
			PreparedStatement stmt = connect.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String medicalItem = rs.getString("medical_item");
				String[] items = medicalItem.split("\\Q,\\E");
				for(int i = 0; i < items.length; i ++) {
					String item = items[i];
					if (i == 0) {
						sql = "update medicalinnovation.medical_examination set medical_item='" + item + "' where id=" + id;
					} else {
						sql = "insert into medicalinnovation.medical_examination (patent_name,medical_no,inhospital_no,register_no,executed_dept,patient_type,medical_item,medical_party,status,executed_tech,executed_assist,executed_disgnose,executed_verifier,executed_nurse,medical_doctory,medical_dept,register_time,report_time,kind) "
								+ "select patent_name,medical_no,inhospital_no,register_no,executed_dept,patient_type,'" + item + "',medical_party,status,executed_tech,executed_assist,executed_disgnose,executed_verifier,executed_nurse,medical_doctory,medical_dept,register_time,report_time,kind from medicalinnovation.medical_examination where id=" + id;
					}
					PreparedStatement pstmt = connect.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
					pstmt.executeUpdate();
				}
			}
			System.out.println("Complated!");

			connect.commit();
		}
		catch(Exception ex) {
			try {connect.rollback();} catch(Exception e) {}
			System.out.println(ex.getMessage());
		}
		finally {
			try {connect.close();} catch(Exception e) {}
		}
	}
}
