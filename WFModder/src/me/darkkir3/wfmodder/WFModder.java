package me.darkkir3.wfmodder;

import me.darkkir3.wfmodder.utils.ParsableWeapon;
import me.darkkir3.wfmodder.utils.WeaponParser;
import me.darkkir3.wfmodder.weapons.AutomaticWeapon;

public class WFModder 
{
	public static void main(String[] args) 
	{
		WeaponParser.readWeapons();
		AutomaticWeapon karakWraith = new AutomaticWeapon(WeaponParser.fetchWeapon("Karak Wraith"));
		
	}
}
