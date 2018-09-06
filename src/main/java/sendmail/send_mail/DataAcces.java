package sendmail.send_mail;

import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;

public class DataAcces {
	private ArrayList<String> userNameList;
	private ArrayList<String> sendtoMail;


	public DataAcces() {
		sendtoMail = new ArrayList<String>();
		userNameList = new ArrayList<String>();
		
	}

	public ArrayList<String> getList() {
		return sendtoMail;
	}
	
	
	public ArrayList<String> getUserNameList() {
		return userNameList;
	}
	
	
	
	public void userName() throws SQLException, ClassNotFoundException {
		
		String url = "jdbc:sqlserver://localhost:1433;databaseName=Avivasa;integratedSecurity=true;";
		
		Connection conn = DriverManager.getConnection(url);
		
		Statement st = conn.createStatement();
		
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");	
	
		
		String sqlname = "DECLARE @endDate DATETIME\r\n" + 
				"DECLARE @today DATETIME\r\n" + 
				"SELECT @endDate = getDate()+6, @today = getDate()\r\n" + 
				"SELECT kullaniciAdi FROM Kullanicilar \r\n" + 
				"    WHERE \r\n" + 
				"    (DATEPART (month, KullaniciDogumTarihi) >= DATEPART (month, @today)\r\n" + 
				"        AND DATEPART (day, KullaniciDogumTarihi) >= DATEPART (day, @today))\r\n" + 
				"   " ;
		
		ResultSet rsname=st.executeQuery(sqlname);		
		
		while(rsname.next()) {
			userNameList.add(rsname.getString("kullaniciAdi"));
		}
		
	}
	


	public void KullaniciId() throws ClassNotFoundException, SQLException {
		String url = "jdbc:sqlserver://localhost:1433;databaseName=Avivasa;integratedSecurity=true;";		
		Connection conn = DriverManager.getConnection(url);
		Statement st = conn.createStatement();
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		/////////////////////////////////////////////////////////////////////
		String sqlid = "select Kullanicilar.KullaniciId from Kullanicilar ";
		ArrayList<Integer> KullaniciId = new ArrayList<Integer>();
		ResultSet rsId = st.executeQuery(sqlid);

		while (rsId.next()) {
			KullaniciId.add(rsId.getInt("KullaniciId"));

		}
	

		//////////////////////////////////////////////////////////////////////////////////////////////
		
		String sqlDt = "DECLARE @endDate DATETIME\r\n" + 
				"DECLARE @today DATETIME\r\n" + 
				"SELECT @endDate = getDate()+6, @today = getDate()\r\n" + 
				"SELECT KullaniciDogumTarihi,kullaniciMail FROM Kullanicilar \r\n" + 
				"    WHERE \r\n" + 
				"    (DATEPART (month, KullaniciDogumTarihi) >= DATEPART (month, @today)\r\n" + 
				"        AND DATEPART (day, KullaniciDogumTarihi) >= DATEPART (day, @today))";
		

		ResultSet rsDt = st.executeQuery(sqlDt);
		while (rsDt.next()) {
			sendtoMail.add(rsDt.getString("kullaniciMail"));

		}
		
		
		
		//////////////////////////////////////////////////////////////////////////////////////////////////
		//String sqlMail = "select Kullanicilar.kullaniciMail from Kullanicilar";


		//ResultSet rsMail = st.executeQuery(sqlMail);

		//while (rsMail.next()) {
			//kullaniciMail.add(rsMail.getString("kullaniciMail"));

		//}
		
		
		/*
		
		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int currentMonth = localDate.getMonthValue();
		int currentDay = localDate.getDayOfMonth();

		String sqlDt = "select * from Kullanicilar";
		ArrayList<Integer> userIdList = new ArrayList<Integer>();
		
		HashMap<Integer, Date> userBirthDayMap = new HashMap<Integer, Date>();
		ResultSet rsDt = st.executeQuery(sqlDt);

		while (rsDt.next()) {
			userBirthDayMap.put(rsDt.getInt("KullaniciId"), rsDt.getDate("KullaniciDogumTarihi"));
		}

		for (int j = 1; j <= userBirthDayMap.size(); j++) {

			Calendar cal = Calendar.getInstance();
			cal.setTime(userBirthDayMap.get(j));
			int birthMonth = cal.get(Calendar.MONTH) + 1;
			int birthDay = cal.get(Calendar.DAY_OF_MONTH);

			if (currentMonth == birthMonth && currentDay == birthDay) {
				userIdList.add(j);
			}

		}
		String mailSendTo;

		for (int i = 0; i < userIdList.size(); i++) {

			for (int j = 0; j < kullaniciMail.size() + 1; j++) {

				if (userIdList.get(i) == j) {

					mailSendTo = kullaniciMail.get(i);
					sendtoMail.add(mailSendTo);

					System.out.println(mailSendTo);
				}

			}

		}*/

	}

	
}