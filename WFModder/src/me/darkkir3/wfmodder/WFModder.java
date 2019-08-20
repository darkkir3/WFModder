package me.darkkir3.wfmodder;

import me.darkkir3.wfmodder.ui.WeaponModdingFrame;
import me.darkkir3.wfmodder.utils.WeaponParser;

public class WFModder 
{
	public static void main(String[] args) 
	{	
		WeaponParser.readWeapons();
		
		WeaponModdingFrame moddingFrame = new WeaponModdingFrame();
		moddingFrame.initialize();
	}
}
