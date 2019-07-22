package me.darkkir3.wfmodder.ui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;

import me.darkkir3.wfmodder.utils.WeaponParser;
import me.darkkir3.wfmodder.weapons.WeaponSlot;

public class WeaponSelectorRenderer extends JLabel implements ListCellRenderer<String>
{
	private static final long serialVersionUID = 1L;

	public WeaponSelectorRenderer()
	{
		this.setOpaque(true);
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.setVerticalAlignment(SwingConstants.CENTER);
		
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends String> list, String value, int index, boolean isSelected,
			boolean cellHasFocus) 
	{
		WeaponSlot weaponSlot = WeaponParser.getWeaponSlotByName(value);
		this.setText(value);
		
		if(isSelected)
		{
			this.setBackground(list.getSelectionBackground());
		}
		else
		{
			this.setBackground(list.getBackground());
		}
		
		int ordinal = weaponSlot.ordinal() - 1;
		
		Color background = this.getBackground();
		for(int i = 0; i < ordinal; i++)
		{
			background = background.darker();
		}
		this.setBackground(background);
		
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		return this;
	}

}
