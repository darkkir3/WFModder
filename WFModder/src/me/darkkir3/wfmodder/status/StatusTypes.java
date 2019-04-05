package me.darkkir3.wfmodder.status;

public enum StatusTypes 
{
	IMPACT,
	PUNCTURE,
	SLASH,
	COLD,
	ELECTRICITY,
	HEAT,
	TOXIN,
	BLAST,
	CORROSIVE,
	GAS,
	MAGNETIC,
	RADIATION,
	VIRAL,
	TRUE,
	VOID;
	
	/**IPS
	 * @return
	 */
	public boolean isPhysical()
	{
		return this == IMPACT || this == PUNCTURE || this == SLASH;
	}
	
	/**Anything that is not physical or void or finisher dmg is considered elemental
	 * @return
	 */
	public boolean isElemental()
	{
		return this != VOID && this != TRUE && !this.isPhysical();
	}
}
