package me.darkkir3.wfmodder.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import me.darkkir3.wfmodder.weapons.WeaponSlot;

public class WeaponModdingFrame extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	private WeaponSlot weaponSlotToMod = WeaponSlot.NONE;
	private WeaponSelector weaponSelector;

	public void initialize()
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int marginX = (int)(screenSize.getWidth() / 2);
		int marginY = (int)(screenSize.getHeight() / 2);
		
		this.setBounds(marginX, marginY, (int)screenSize.getWidth() - marginX, (int)screenSize.getHeight() - marginY);
		this.setLocationRelativeTo(null);
		
		this.getContentPane().setLayout(new BorderLayout());
		
		JPanel weaponSelectPanel = new JPanel(new BorderLayout());
		
		weaponSelector = new WeaponSelector(this.weaponSlotToMod, null);
		weaponSelectPanel.add(weaponSelector, BorderLayout.CENTER);
		
		this.getContentPane().add(weaponSelectPanel, BorderLayout.NORTH);
		
		this.setVisible(true);
	}
}
