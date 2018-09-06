package sendmail.send_mail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Sendmail {
	 public String readData(){
	        BufferedReader br = null;
	        StringBuilder sb = new StringBuilder();
	        try {
	            br = new BufferedReader(
	                    new FileReader(
	                            new File("C:/Users/bülent/eclipse-workspace/send-mail/dogumgunu.html")));
	            String line="";
	            
	            while((line = br.readLine())!=null){
	                sb.append(line);
	                sb.append("\r\n");
	            }
	            
	            return sb.toString();
	            
	        } catch (FileNotFoundException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }finally{
	            if(br!=null){
	                try {
	                    br.close();
	                } catch (IOException e) {
	                    br = null;
	                }
	            }    
	        }
			return sb.toString();
	    }

	public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
		DataAcces da = new DataAcces();
		da.KullaniciId();
		da.userName();
		Sendmail sm = new Sendmail();
		String mesaggeBody;
		Config config = new Config();
		
		Session session = Session.getDefaultInstance(config.getPropValues(), new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication("blnt.ylmz025@gmail.com", "33676671212");
			}
		});
		

		for (int i = 0; i < da.getList().size(); i++) {
			try {

				String username = da.getUserNameList().get(i);
				mesaggeBody=sm.readData();
				mesaggeBody = mesaggeBody.replace("name",username);
				System.out.println(mesaggeBody);
				
				
				String to = da.getList().get(i);

				MimeBodyPart mbp = new MimeBodyPart();
				
				MimeMultipart mmp = new MimeMultipart();
				
				mbp.setText(mesaggeBody, "UTF-8");
				
				mbp.addHeader("Content-Type", "text/html");
				
				mmp.addBodyPart(mbp);
				
				MimeMessage msg = new MimeMessage(session);
				
				msg.setContent(mmp);
				// msg.setText("DenemeeeeeKontrollll :) :)");

				msg.setHeader("XPriority", "1");
				
				String timeStamp = new SimpleDateFormat("yyyymmdd_hh-mm-ss").format(new Date());

				msg.setSubject("Dogum Günü Kutlamasi : " + timeStamp);

				msg.setSentDate(new Date());

				InternetAddress[] address = InternetAddress.parse(to, true);

				msg.setRecipients(Message.RecipientType.TO, address);

				// msg.setContent(index, "index/html; charset=utf-8");

				msg.setFrom(new InternetAddress("<blnt.ylmz025@gmail.com>"));
				
				Transport.send(msg);

				System.out.println("Mail has been sent successfully");

			} catch (MessagingException mex) {

				System.out.println("Unable to send an email" + mex);

			}

		}
	}
}