package me.darkkir3.wfmodder;

import me.darkkir3.wfmodder.armor.ArmorTypes;
import me.darkkir3.wfmodder.health.HealthTypes;
import me.darkkir3.wfmodder.shield.ShieldTypes;
import me.darkkir3.wfmodder.status.IStatusType;
import me.darkkir3.wfmodder.utils.ArmorUtils;

public class Enemy 
{
	private float health, shield, armor;
	
	private HealthTypes healthType = HealthTypes.NONE;
	private ArmorTypes armorType = ArmorTypes.NONE;
	private ShieldTypes shieldType = ShieldTypes.NONE;
	
	public Enemy(float baseHealth, float baseShield, float baseArmor)
	{
		this.health = baseHealth;
		this.shield = baseShield;
		this.armor = baseArmor;
	}
	
	public void applyScaling(float baseLevel, float currentLevel)
	{
		this.armor = ArmorUtils.getTotalArmorOf(this.armor, baseLevel, currentLevel);
	}
	
	public float getHealth()
	{
		return this.health;
	}
	
	public float getShield()
	{
		return this.shield;
	}
	
	public float getArmor()
	{
		return this.armor;
	}
	
	public void setHealth(float health)
	{
		this.health = health;
	}
	
	public void setShield(float shield)
	{
		this.shield = shield;
	}
	
	public void setArmor(float armor)
	{
		this.armor = armor;
	}
	
	public HealthTypes getHealthType() 
	{
		return healthType;
	}

	public void setHealthType(HealthTypes healthType) 
	{
		this.healthType = healthType;
	}

	public ArmorTypes getArmorType() 
	{
		return armorType;
	}

	public void setArmorType(ArmorTypes armorType) 
	{
		this.armorType = armorType;
	}

	public ShieldTypes getShieldType() 
	{
		return shieldType;
	}

	public void setShieldType(ShieldTypes shieldType) 
	{
		this.shieldType = shieldType;
	}
	
	public float getMultiplierAgainst(IStatusType statusType)
	{
		float statusTypeMultiplier = 1f;
		if(this.shield > 0f && this.shieldType != ShieldTypes.NONE)
		{
			statusTypeMultiplier = this.shieldType.getMultiplierAgainst(statusType);
		}
		else if(this.armor > 0f && this.armorType != ArmorTypes.NONE)
		{
			statusTypeMultiplier = this.armorType.getMultiplierAgainst(statusType);
		}
		else if(this.health > 0f && this.healthType != HealthTypes.NONE)
		{
			statusTypeMultiplier = this.healthType.getMultiplierAgainst(statusType);
		}
		
		return statusTypeMultiplier;
	}
}
