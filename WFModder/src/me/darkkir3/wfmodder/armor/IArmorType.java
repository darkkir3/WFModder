package me.darkkir3.wfmodder.armor;

import me.darkkir3.wfmodder.status.IStatusType;

public interface IArmorType 
{	
	public ArmorTypes getArmorType();
	public float getMultiplierAgainst(IStatusType statusType);
}
