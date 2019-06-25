package me.darkkir3.wfmodder;

import me.darkkir3.wfmodder.armor.ArmorTypes;
import me.darkkir3.wfmodder.health.HealthTypes;
import me.darkkir3.wfmodder.shield.ShieldTypes;
import me.darkkir3.wfmodder.utils.EnemyStats;
import me.darkkir3.wfmodder.utils.WeaponParser;
import me.darkkir3.wfmodder.weapons.AutomaticWeapon;

public class WFModder 
{
	public static void main(String[] args) 
	{
		WeaponParser.readWeapons();
		AutomaticWeapon karakWraith = new AutomaticWeapon(WeaponParser.fetchWeapon("Karak Wraith"));
		
		Enemy enemy = new Enemy(100000, 0, 1000);
		enemy.setHealthType(HealthTypes.FLESH);
		enemy.setShieldType(ShieldTypes.SHIELD);
		enemy.setArmorType(ArmorTypes.FERRITE);
		
		EnemyStats stats = new EnemyStats(karakWraith, enemy);
		
		System.out.println("TTK: " + stats.getTimeToKill() / 1000f + " seconds");
	}
}
