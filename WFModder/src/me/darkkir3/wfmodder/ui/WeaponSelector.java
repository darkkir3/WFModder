package me.darkkir3.wfmodder.ui;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JComboBox;

import me.darkkir3.wfmodder.utils.ParsableWeapon;
import me.darkkir3.wfmodder.utils.WeaponParser;
import me.darkkir3.wfmodder.weapons.WeaponSlot;

public class WeaponSelector extends JComboBox<String> implements ItemListener
{
	private static final long serialVersionUID = 1L;
	
	private ParsableWeapon selectedWeapon;
	/**
	 * Only display weapons for that specific weapon slot
	 */
	private WeaponSlot weaponSlot;
	
	public WeaponSelector(WeaponSlot weaponSlot, ActionListener listener)
	{
		this.weaponSlot = weaponSlot;
		this.addItemListener(this);
		if(listener != null)
		{
			this.addActionListener(listener);
		}
		
		ArrayList<String> weaponsToDisplay = WeaponParser.fetchWeaponNamesBySlot(this.weaponSlot);
		weaponsToDisplay.sort(null);
		weaponsToDisplay.forEach(t -> this.addItem(t));
		
		if(weaponsToDisplay != null)
		{
			this.selectedItemReminder = WeaponParser.fetchWeapon(weaponsToDisplay.get(0));
		}
		
		this.setRenderer(new WeaponSelectorRenderer());
	}
	
	public WeaponSlot getWeaponSlot()
	{
		return this.weaponSlot;
	}
	
	public ParsableWeapon getSelectedWeapon()
	{
		return this.selectedWeapon;
	}

	@Override
	public void itemStateChanged(ItemEvent e) 
	{
		if (e.getStateChange() == ItemEvent.SELECTED)
		{
			String selectedWeaponName = (String)this.getSelectedItem();
			if(selectedWeaponName != null && !selectedWeaponName.trim().isEmpty())
			{
				this.selectedWeapon = WeaponParser.fetchWeapon(selectedWeaponName);
			}
	          
	    }      
	}
}
