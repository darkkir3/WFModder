package me.darkkir3.wfmodder.utils;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;

import me.darkkir3.wfmodder.status.StatusTypes;

public final class DamageUtils 
{
	private static final Random rand = new Random();
	
	
	/**Calculates the individual chances for a specific status chance to proc
	 * @param damageByStatus a hashmap mapping status types with their corresponding damage values
	 * @return a hashmap mapping status types with their effective proc rate ranging from 0 to 1
	 */
	public static HashMap<StatusTypes, Float> calculateStatusWeighting(HashMap<StatusTypes, Float> damageByStatus)
	{
		HashMap<StatusTypes, Float> weightingMap = new HashMap<StatusTypes, Float>();
		
		boolean hasElementals = false;
		boolean hasPhysicals = false;
		
		for(Entry<StatusTypes, Float> entry : weightingMap.entrySet())
		{
			StatusTypes statusType = entry.getKey();
			
			hasElementals |= statusType.isElemental();
			hasPhysicals |= statusType.isPhysical();
		}
		
		
		final float physicalModifier = 
				(hasPhysicals && hasElementals) ? 4f : 1f;
		
		float totalDamage = 0f;
		for(Entry<StatusTypes, Float> entry : weightingMap.entrySet())
		{
			float damageToAdd = entry.getValue();
			if(entry.getKey().isPhysical())
			{
				damageToAdd *= physicalModifier;
			}
			
			totalDamage += damageToAdd;
			
			weightingMap.put(entry.getKey(), damageToAdd);
		}
		
		for(Entry<StatusTypes, Float> entry : weightingMap.entrySet())
		{
			entry.setValue(entry.getValue() / totalDamage);
		}
		
		return weightingMap;
	}
	
	public static boolean isStatusProcced(float statusChance)
	{
		return rand.nextFloat() <= statusChance;
	}
	
	public static StatusTypes getStatusTypeProcced(HashMap<StatusTypes, Float> statusWeighting)
	{
		StatusTypes statusTypeToReturn = statusWeighting.size() > 0 ? 
				(StatusTypes)statusWeighting.keySet().toArray()[0] : StatusTypes.TRUE;
		
		float procToTrigger = rand.nextFloat();
		for(Entry<StatusTypes, Float> entry : statusWeighting.entrySet())
		{
			float chanceToProc = entry.getValue();
			if(procToTrigger > chanceToProc)
			{
				procToTrigger -= chanceToProc;
			}
			else
			{
				return entry.getKey();
			}
		}
		
		return statusTypeToReturn;
	}
}
