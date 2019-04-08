package me.darkkir3.wfmodder.weapons;

import java.util.HashMap;

import me.darkkir3.wfmodder.Enemy;
import me.darkkir3.wfmodder.status.StatusTypes;

public abstract class BaseWeapon 
{
	/**
	 * map status types with their respective base dmg value
	 */
	protected HashMap<StatusTypes, Float> baseDamage;
	protected WeaponSlot weaponSlot = WeaponSlot.PRIMARY;
	/**
	 * 100 accuracy = pinpoint; everything lower = more spread
	 */
	protected float accuracy;
	/**
	 * time in seconds needed to reload
	 */
	protected float reloadSpeed;
	/**
	 * amount of shots in magazine
	 */
	protected float magazine;
	/**
	 * How quickly a weapon can be charged
	 */
	protected float chargeAttack;
	/**
	 * crit chance (1 = 100%)
	 */
	protected float criticalRate;
	/**
	 * the listed damage multiplier for critical hits (as shown in the modding menu)
	 */
	protected float criticalDamage;
	/**
	 * status chance (1 = 100%)
	 */
	protected float statusChance;
	/**
	 * fire rate (shots per seconds; also influences charge rate)
	 */
	protected float fireRate;
	/**
	 * innate multishot on the weapon (1f = 100%)
	 */
	protected float multishot;
	
	public abstract void updateWeaponState(Enemy enemy, float currentTime);
}
