package me.darkkir3.wfmodder;

import me.darkkir3.wfmodder.status.IStatusType;

public class Enemy 
{
	private float health, shield, armor;
	
	public Enemy(float health, float shield, float armor)
	{
		this.health = health;
		this.shield = shield;
		this.armor = armor;
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
	
	public void applyDamage(IStatusType statusType, float value)
	{
		
	}
}
