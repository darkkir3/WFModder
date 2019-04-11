package me.darkkir3.wfmodder.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public final class WeaponParser 
{
	private static JsonArray primaryWeapons;
	private static JsonArray secondaryWeapons;
	
	private static HashMap<String, JsonObject> weaponList = new HashMap<String, JsonObject>();
	
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
		
		fetchWeapons(primaryWeapons);
		fetchWeapons(secondaryWeapons);
	}
	
	/**Converts the jsonarray of all weapons into a mapping via the weapon name
	 * @param weaponElements
	 */
	private static void fetchWeapons(JsonArray weaponElements)
	{
		JsonObject o = null;
		for(int i = 0; i < weaponElements.size(); i++)
		{
			o = primaryWeapons.get(i).getAsJsonObject();
			weaponList.put(o.get("name").getAsString(), o);
		}
	}
	
	/**Fetch the wepaon data of a specific weapon
	 * @param weaponName
	 * @return
	 */
	public static ParsableWeapon fetchWeapon(String weaponName)
	{
		Gson gson = new Gson();
		ParsableWeapon weapon = gson.fromJson(weaponList.get(weaponName), ParsableWeapon.class);
		
		return weapon;
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
