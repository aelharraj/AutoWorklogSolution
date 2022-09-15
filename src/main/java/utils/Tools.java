package utils;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;
import java.util.Properties;
import java.util.Random;


public class Tools {
	static Instant startTime;


	// Read property from other path( user home for e.g)
	public static String readPropertyUserHome(String propertyName, String PropertyValue) {
		String value = "";
		try {
			Properties prop = new Properties();
			// load a properties file from class path, inside static metod
			prop.load(new FileInputStream("/" + propertyName));
			// System.out.println("PATH TO THE PROPERTY: " +
			// App.class.getClass().getResourceAsStream(propertyName));
			// get the property value and print it out

			value = prop.getProperty(PropertyValue);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return value;
	}

	// Update values of Property File/Method 1
	public static void updatePropertyFile(String propertyName, String key, String value) {

		try {
			FileInputStream in = new FileInputStream(propertyName);
			Properties props = new Properties();
			props.load(in);
			in.close();
			FileOutputStream out = new FileOutputStream(propertyName);
			props.setProperty(key, value);
			props.store(out, null);
			out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	public static int RandomNumber() {
		Random rand = new Random();
		rand.nextInt();
		return (int) (Math.random() * 6);

	}
	
	
	
	public static String EndTime() {
		Instant end = Instant.now();
		// System.out.println(Duration.between(startTime, end));
		Duration diff = Duration.between(startTime, end);
		return String.format("%d:%02d:%02d", diff.toHours(), diff.toMinutes(), (diff.toMillis() / 1000) % 60);
	}

	public static Instant startTime() {
		return startTime = Instant.now();
	}

	public static void wait(int timeOut)
	{
		try {
			Thread.sleep(timeOut);
		} catch (Exception e) {
		}
	}
	
	/**
	 * Open a web page in the default browser in the system
	 * @param uri
	 * @return
	 */
	public static boolean openWebpage(URI uri) {
		Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
		if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
			try {
				desktop.browse(uri);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public static boolean openWebpage(URL url) {
		try {
			return openWebpage(url.toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void openFile(String file)
	{
		try {
			Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
			Desktop.getDesktop().open(new File(file));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public static String filePath(File file)
	{
		return file.getAbsolutePath();
	}

}
