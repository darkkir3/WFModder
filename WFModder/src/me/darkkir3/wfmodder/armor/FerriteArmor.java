package me.darkkir3.wfmodder.armor;

import me.darkkir3.wfmodder.status.IStatusType;

public class FerriteArmor implements IArmorType
{

	@Override
	public float getMultiplierAgainst(IStatusType statusType) 
	{
		float defaultMultiplier = 1f;
		switch(statusType.getStatusType())
		{
			case PUNCTURE:
				defaultMultiplier += 0.5f;
				break;
			case SLASH:
				break;
			case TOXIN:
				defaultMultiplier += 0.25f;
				break;
			case BLAST:
				defaultMultiplier -= 0.25f;
				break;
			case CORROSIVE:
				defaultMultiplier += 0.75f;
				break;
			default:
				break;
		}
		
		return defaultMultiplier;
	}

	@Override
	public ArmorTypes getArmorType() 
	{
		return ArmorTypes.Ferrite;
	}

}
