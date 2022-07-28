package TEDUpolly;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Random;

public class Dice extends JPanel {
	
	Random rnd = new Random();
	int ZarSonucu = 1;
	
	public Dice(int xCoord, int yCoord, int width, int height) {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBounds(xCoord, yCoord, width, height);
		
	}
	
	//Zar atma ile gpa guncelleme
		public void updateGPA(Player zaratan, int zarsonucu) {
			
			switch(zarsonucu) {
			case 1:{
				zaratan.NotToplami += 1.0;
				break;
			}
			case 2:{
				zaratan.NotToplami += 2.0;
				break;
			}
			case 3:{
				zaratan.NotToplami += 2.5;
				break;
			}
			case 4:{
				zaratan.NotToplami += 3.0;
				break;
			}
			case 5:{
				zaratan.NotToplami += 3.5;
				break;
			}
			case 6:{
				zaratan.NotToplami += 4.0;
				break;
			}
			}
			
			zaratan.GPA = zaratan.NotToplami/zaratan.oyuncutursayisi;
			
		}
		
	//Zar sonucuna gore zari degistirme
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if(ZarSonucu == 1) {
			g.fillOval(getWidth()/2 - 5/2, getHeight()/2 - 5/2, 5, 5);
		} 
		else if(ZarSonucu == 2) {
			g.fillOval(getWidth()/2 - 15, getHeight()/2 + 10, 5, 5);
			g.fillOval(getWidth()/2 + 10, getHeight()/2 - 15, 5, 5);
		} 
		else if(ZarSonucu == 3) {
			g.fillOval(getWidth()/2 - 15, getHeight()/2 + 10, 5, 5);
			g.fillOval(getWidth()/2 + 10, getHeight()/2 - 15, 5, 5);
			g.fillOval(getWidth()/2 - 5/2, getHeight()/2 - 5/2, 5, 5);
		} 
		else if(ZarSonucu == 4) {
			g.fillOval(getWidth()/2 - 15, getHeight()/2 + 10, 5, 5);
			g.fillOval(getWidth()/2 + 10, getHeight()/2 - 15, 5, 5);
			g.fillOval(getWidth()/2 - 15, getHeight()/2 - 15, 5, 5);
			g.fillOval(getWidth()/2 + 10, getHeight()/2 + 10, 5, 5);
		} 
		else if(ZarSonucu == 5) {
			g.fillOval(getWidth()/2 - 15, getHeight()/2 + 10, 5, 5);
			g.fillOval(getWidth()/2 + 10, getHeight()/2 - 15, 5, 5);
			g.fillOval(getWidth()/2 - 15, getHeight()/2 - 15, 5, 5);
			g.fillOval(getWidth()/2 + 10, getHeight()/2 + 10, 5, 5);
			g.fillOval(getWidth()/2 - 5/2, getHeight()/2 - 5/2, 5, 5);
		} 
		else {//(ZarSonucu == 6)
			g.fillOval(getWidth()/2 - 15, getHeight()/2 + 10, 5, 5);
			g.fillOval(getWidth()/2 + 10, getHeight()/2 - 15, 5, 5);
			g.fillOval(getWidth()/2 - 15, getHeight()/2 - 15, 5, 5);
			g.fillOval(getWidth()/2 + 10, getHeight()/2 + 10, 5, 5);
			g.fillOval(getWidth()/2 - 15, getHeight()/2 - 5/2, 5, 5);
			g.fillOval(getWidth()/2 + 10, getHeight()/2 - 5/2, 5, 5);
		}
		
	}
	
	public void ZarAt(){
		ZarSonucu = rnd.nextInt(6) + 1;
		repaint();
	}

	public Random getRnd() {
		return rnd;
	}

	public void setRnd(Random rnd) {
		this.rnd = rnd;
	}

	public int getZarSonucu() {
		return ZarSonucu;
	}

	public void setZarSonucu(int zarSonucu) {
		ZarSonucu = zarSonucu;
	}
	
}
