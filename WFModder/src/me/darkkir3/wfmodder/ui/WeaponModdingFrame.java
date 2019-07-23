package me.darkkir3.wfmodder.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import me.darkkir3.wfmodder.utils.ParsableWeapon;
import me.darkkir3.wfmodder.weapons.WeaponSlot;

public class WeaponModdingFrame extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	private WeaponSlot weaponSlotToMod = WeaponSlot.NONE;
	private WeaponSelector weaponSelector;
	private WeaponStatsPanel weaponStatsPanel;
	private ParsableWeapon selectedWeapon;

	public void initialize()
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int marginX = (int)(screenSize.getWidth() / 1.25);
		int marginY = (int)(screenSize.getHeight() / 1.25);
		
		this.setBounds(marginX, marginY, (int)screenSize.getWidth() - marginX, (int)screenSize.getHeight() - marginY);
		this.setLocationRelativeTo(null);
		
		this.getContentPane().setLayout(new BorderLayout());
		
		JPanel weaponSelectPanel = new JPanel(new BorderLayout());
		
		this.weaponSelector = new WeaponSelector(this.weaponSlotToMod, new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e) 
					{
						if(weaponSelector != null && weaponSelector.getSelectedWeapon() != null)
						{
							weaponStatsPanel.setWeaponToDisplay(weaponSelector.getSelectedWeapon());
							selectedWeapon = weaponSelector.getSelectedWeapon();
						}
					}
			
				});
		weaponSelectPanel.add(weaponSelector, BorderLayout.NORTH);
		
		this.weaponStatsPanel = new WeaponStatsPanel();
		this.weaponStatsPanel.setWeaponToDisplay(weaponSelector.getSelectedWeapon());
		weaponSelectPanel.add(weaponStatsPanel, BorderLayout.CENTER);
		
		
		this.getContentPane().add(weaponSelectPanel, BorderLayout.NORTH);
		
		this.setVisible(true);
	}
	
	public ParsableWeapon getSelectedWeapon()
	{
		return this.selectedWeapon;
	}
}
