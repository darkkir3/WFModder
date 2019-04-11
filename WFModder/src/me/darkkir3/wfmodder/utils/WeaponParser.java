package me.darkkir3.wfmodder.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.commons.io.IOUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

public final class WeaponParser 
{
	private static JsonArray primaryWeapons;
	private static JsonArray secondaryWeapons;
	
	public static void readWeapons()
	{
		//directly read the raw weapon data of github
		String primaryJson = readFromURL(
				"https://raw.githubusercontent.com/WFCD/warframe-items/development/data/json/Primary.json");
		
		String secondaryJson = readFromURL(
				"https://raw.githubusercontent.com/WFCD/warframe-items/development/data/json/Secondary.json");
		
		JsonParser jsonParser = new JsonParser();
		
		primaryWeapons = jsonParser.parse(primaryJson).getAsJsonArray();
		secondaryWeapons = jsonParser.parse(secondaryJson).getAsJsonArray();
	}
	
	private static String readFromURL(String url)
	{
		String result = "";
		try
		{
			InputStream in = new URL(url).openStream();
			result = IOUtils.toString(in, "UTF-8");
			in.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		return result;
	}
}
