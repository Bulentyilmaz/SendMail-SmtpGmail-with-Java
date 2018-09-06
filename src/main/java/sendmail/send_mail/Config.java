package sendmail.send_mail;

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
import java.util.Properties;
import javax.mail.Session;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Config {

	public Properties getPropValues() throws IOException {

		Properties properties = new Properties();

		InputStream inputStream;

		String propFileName = "config.properties";

		inputStream=Config.class.getClassLoader().getResourceAsStream(propFileName);

		if (inputStream != null) {
			
			properties.load(inputStream);
		
		} else{
			
			throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
		
		}
		
		inputStream.close();
		return properties;
	}

	/*
	 * public void configManager() {
	 * 
	 * props.put("mail.smtp.host", "true");
	 * 
	 * props.put("mail.smtp.auth", "true");
	 * 
	 * props.put("mail.smtp.starttls.enable", "true");
	 * 
	 * props.put("mail.smtp.host", "smtp.gmail.com");
	 * 
	 * props.put("mail.smtp.port", "587");
	 * 
	 * props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
	 * 
	 * }
	 * 
	 * 
	 * public void setProps(Properties props) { this.props = props; }
	 * 
	 * public Properties getProps() { return props; }
	 */
}
