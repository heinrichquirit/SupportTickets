package main.java.net.bigbadcraft.supporttickets.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class ConfigHandler {

	private Plugin plugin;
	private String fileName;
	private File file;
	private FileConfiguration configuration;

	public ConfigHandler(Plugin plugin, String fileName) {
		this.plugin = plugin;
		this.fileName = fileName;
		file = new File(plugin.getDataFolder(), fileName);
		configuration = YamlConfiguration.loadConfiguration(file);
	}

	public void load() {
		file = new File(plugin.getDataFolder(), fileName);
		Util.makeFile(file);
		configuration = YamlConfiguration.loadConfiguration(file);
		reload();
	}

	public void reload() {
		if (file == null) {
			file = new File(plugin.getDataFolder(), fileName);
		}
		configuration = YamlConfiguration.loadConfiguration(file);
		InputStream stream = plugin.getResource(fileName);
		if (stream == null)
			return;
		@SuppressWarnings("deprecation")
		YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(stream);
		configuration.setDefaults(defConfig);
	}

	public void save() {
		if (configuration == null || file == null)
			return;
		try {
			configuration.save(file);
		} catch (IOException e) {
			e.printStackTrace();
			Util.log(Level.SEVERE, "Could not save configuration changes to " + file.getName());
		}
	}
	
	public File getFile() {
		return file;
	}
	
	public FileConfiguration getConfig() {
		return configuration;
	}

}
