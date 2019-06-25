package me.darkkir3.wfmodder.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigReader 
{
	private static Properties configToUse;
	
	private ConfigReader() {}
	
	private static Properties getConfigToUse()
	{
		if(ConfigReader.configToUse == null)
		{
			try 
			{
				InputStream in = new FileInputStream("data" + File.separator + "config.properties");
				ConfigReader.configToUse = new Properties();
				configToUse.load(in);
			} 
			catch (IOException e) 
			{
				System.err.println("Failed to read config file");
				e.printStackTrace();
			}
		}
		
		return ConfigReader.configToUse;
	}
	
	public static String readConfigS(String key)
	{
		return ConfigReader.getConfigToUse().getProperty(key);
	}
	
	public static float readConfigF(String key)
	{
		return Float.valueOf(ConfigReader.readConfigS(key));
	}
}
