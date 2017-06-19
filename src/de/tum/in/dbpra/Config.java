package de.tum.in.dbpra;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
	public static String HOST;
	public static String USER;
	public static String PASSWORD;
	public static String PORT;
	public Config() {
		Properties prop = new Properties();
		InputStream input = null;
		try {

			input = new FileInputStream("config.properties");
			prop.load(input);

			// get the property value
			HOST = prop.getProperty("host");
			USER = prop.getProperty("user");
			PASSWORD = prop.getProperty("password");
			PORT = prop.getProperty("port");

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}