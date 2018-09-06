package sendmail.send_mail;

import java.io.IOException;

public class ConfigRead extends Config {

	public static void main(String[] args) throws IOException {

		ConfigRead properties = new ConfigRead();
		properties.getPropValues();

	}

}
