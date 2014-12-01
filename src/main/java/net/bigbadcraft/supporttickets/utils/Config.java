package main.java.net.bigbadcraft.supporttickets.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
	
	private static Properties prop = new Properties();
	
	private String pluginPath = null;
	
	private String fName;
	private File f;
	
	private String dir = "plugins_mod " + File.separator + "SupportTickets";
	private File directory;
	
	public Config(String fileName) {
		fName = fileName;
		directory = new File(dir);
		Util.makeDir(directory);
		pluginPath = "plugins_mod" + File.separator + "SupportTickets" + File.separator + fName;
		f = new File(pluginPath);
		Util.makeFile(f);
		loadDefaults();
	}
	
	public void loadDefaults() {
		saveSetting("ticket-limiter", "5");
	}

	public void saveSetting(String path, String value) {
		prop.setProperty(path, value);
		try {
			prop.store(new FileOutputStream(pluginPath), null);
		} catch (FileNotFoundException e) {
			Util.log(DebugLevel.HIGH + fName + " could not be found.");
			e.printStackTrace();
		} catch (IOException e) {
			Util.log(DebugLevel.HIGH + fName + " could not be saved.");
			e.printStackTrace();
		}
	}
	
	public String getValue(String path) {
		if (prop.containsKey(path)) {
			return prop.getProperty(path);
		}
		return null;
	}
	
	public String getFileName() {
		return fName;
	}
	
	public File getFile() {
		return f;
	}
	
}
