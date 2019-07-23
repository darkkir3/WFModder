package me.darkkir3.wfmodder.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

import me.darkkir3.wfmodder.utils.ConfigReader;
import me.darkkir3.wfmodder.utils.ParsableWeapon;

public class WeaponStatsPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	private ParsableWeapon weaponToDisplay;
	
	private final int marginX = 10, marginY = 10;
	private final int offsetY = 5;
	
	//store those values per panel so we don't need to fetch it for each draw call
	private final String accuracy;
	private final String critical_chance;
	private final String critical_multiplier;
	private final String fire_rate;
	
	private BufferedImage weaponImage;
	
	
	public WeaponStatsPanel() 
	{
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        accuracy = ConfigReader.readLangFile("accuracy");
        critical_chance = ConfigReader.readLangFile("critical_chance");
        critical_multiplier = ConfigReader.readLangFile("critical_multiplier");
        fire_rate = ConfigReader.readLangFile("fire_rate");
    }
	
	public void setWeaponToDisplay(ParsableWeapon weapon)
	{
		this.weaponToDisplay = weapon;
		
		if(weapon.getImageURL() != null)
		{
		    try 
		    {
		    	final URL url = new URL(weapon.getImageURL());
		    	final HttpURLConnection connection = (HttpURLConnection) url
		    	        .openConnection();
		    	connection.setRequestProperty(
		    	    "User-Agent",
		    	    //custom user agent since the default java vm gets blocked
		    	    "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox/47.0");
		    	this.weaponImage = ImageIO.read(connection.getInputStream());
		    	this.repaint();
			} 
		    catch (IOException e) 
		    {
				e.printStackTrace();
			}
		}
		else
		{
			this.repaint();
		}
		
	}

    public Dimension getPreferredSize() 
    {
        return new Dimension(250, 250);
    }

    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);  
        
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);

        int currentPosY = 2 * marginY;
        int fontHeight = g2d.getFontMetrics().getHeight();
        
        int textSizeY = currentPosY + (fontHeight + offsetY) * 4;
        int imageSize = this.getHeight() - (textSizeY);
        
        g2d.drawImage(this.weaponImage, (this.getWidth() / 2) - (imageSize / 2), textSizeY, imageSize, imageSize, null);
        g2d.setColor(new Color(1f, 1f, 1f, 0.5f));
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
        
        g2d.setColor(Color.BLACK);
        
        this.drawStatLine(g2d, marginX, currentPosY, accuracy, weaponToDisplay.accuracy, "");
        currentPosY += fontHeight + offsetY;
        this.drawStatLine(g2d, marginX, currentPosY, critical_chance, weaponToDisplay.criticalChance * 100f, "%");
        currentPosY += fontHeight + offsetY;
        this.drawStatLine(g2d, marginX, currentPosY, critical_multiplier, weaponToDisplay.criticalMultiplier, "x");
        currentPosY += fontHeight + offsetY;
        this.drawStatLine(g2d, marginX, currentPosY, fire_rate, weaponToDisplay.fireRate, "");
        
        
    }  
    
    private void drawStatLine(Graphics2D g2d, int startX, int startY, String valueName, Object value, String append)
    {
    	String valueToDraw = null;
    	if(value instanceof String)
    	{
    		valueToDraw = (String)value;
    	}
    	
    	if(value instanceof Number)
    	{
    		valueToDraw = String.valueOf(Math.round((float)value * 100.0) / 100.0);
    	}
    	
    	int width = this.getWidth();
    	
    	g2d.drawString(valueName, startX, startY);
    	g2d.drawString(valueToDraw + append, width - startX - g2d.getFontMetrics().stringWidth(valueToDraw + append), startY);
    	
    	g2d.drawLine(startX, startY + g2d.getFontMetrics().getDescent(), width - marginX, startY + g2d.getFontMetrics().getDescent());
    	
    }

}
