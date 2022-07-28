package TEDUpolly;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.geom.AffineTransform;
public class Square extends JPanel {

	int Sira;
	String Isim;
	int Fiyat;
	int Kira;
	int InsaatUcreti;
	boolean SatinAlindiMi;
	int EvSayisi;
	int OtelSayisi;
	boolean SpeacialSquare;
	Player Sahibi;
	boolean SatinAlinabilir;
	public static int xCoord;
	public static int yCoord;
	JLabel nameLabel;
	static int totalSquares = 0;
	int number;
	private String name;
	
	public Square(int xCoord, int yCoord, int width, int height, int rotationDegrees, int SiraA, String isimA, int fiyatA, int kiraA, int InsaatUcretiA, boolean SatinAlindiMiA, boolean specialsquareA, boolean satinalinabilirA) {

		this.Sira = SiraA;
		this.Isim = isimA;
		this.Fiyat = fiyatA;
		this.Kira = kiraA;
		this.InsaatUcreti = InsaatUcretiA;
		this.SatinAlindiMi = SatinAlindiMiA;
		this.SpeacialSquare = specialsquareA;
		this.SatinAlinabilir = satinalinabilirA;
		
		this.Sahibi = null;
		this.EvSayisi = 0;
		this.OtelSayisi = 0;
		
		String labelString = isimA;
		
		number = totalSquares;
		totalSquares++;
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBounds(xCoord, yCoord, width, height);
		name = labelString;
		this.setLayout(null);

		if(rotationDegrees == 0) {
			nameLabel = new JLabel(labelString);
			nameLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
			nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
			nameLabel.setBounds(0,20,this.getWidth(),20);
			this.add(nameLabel);
		} 
		else {	
			
			nameLabel = new JLabel(labelString) {
				protected void paintComponent(Graphics g) {
					Graphics2D g2 = (Graphics2D)g;
					g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
							RenderingHints.VALUE_ANTIALIAS_ON);
					AffineTransform aT = g2.getTransform();
					Shape oldshape = g2.getClip();
					double x = getWidth()/2.0;
					double y = getHeight()/2.0;
					aT.rotate(Math.toRadians(rotationDegrees), x, y);
					g2.setTransform(aT);
					g2.setClip(oldshape);
					super.paintComponent(g);
				}
			};
			if(rotationDegrees == 90) {
				nameLabel.setBounds(20, 0, this.getWidth(), this.getHeight());
			}
			if(rotationDegrees == -90) {
				nameLabel.setBounds(-10, 0, this.getWidth(), this.getHeight());
			}
			if(rotationDegrees == 180) {
				nameLabel.setBounds(0, 0, this.getWidth(), this.getHeight());
			}
			if(rotationDegrees == 135 || rotationDegrees == -135 || rotationDegrees == -45 || rotationDegrees == 45) {
				nameLabel.setBounds(0, 0, this.getWidth(), this.getHeight());
			}
			nameLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
			nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
			
			this.add(nameLabel);
		} 

	}
	
	//Bir oyuncu yeri satin aldi
	public void SatinAlindi(Square yerA, Player alan) {
		yerA.SatinAlindiMi = true;
		yerA.Sahibi = alan;
		
	}
	
	//Bir oyuncu yeri satti
	public void Satildi(Square yerA) {
		yerA.SatinAlindiMi = false;
		yerA.EvSayisi = 0;
		yerA.OtelSayisi = 0;
		yerA.Sahibi = null;
		
	}
	
	//Ev ya da otel yapildi (Otel icin 3 ev gerekli)
	public void InsaatYapildi(Square yerA) {
		if(yerA.EvSayisi==3) {
			yerA.OtelSayisi++;
			yerA.Kira = yerA.Kira*5;
		}
		else {
			yerA.EvSayisi++;
			yerA.Kira = yerA.Kira*2;
		}
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		switch(number) {
		case 1,3,4:{
			g.drawRect(0, this.getHeight()-20, this.getWidth(), 20);
			g.setColor(Color.GREEN);
			g.fillRect(0, this.getHeight()-20, this.getWidth(), 20);
			break;
		}
		case 6,8,9:{
			g.drawRect(0, 0, 20, this.getHeight());
			g.setColor(Color.CYAN);
			g.fillRect(0, 0, 20, this.getHeight());
			break;
		}
		case 11,13,14:{
			g.drawRect(0, 0, this.getWidth(), 20);
			g.setColor(Color.ORANGE);
			g.fillRect(0, 0, this.getWidth(), 20);
			break;
		}
		case 16,17,19:{
			g.drawRect(this.getWidth()-20, 0, 20, this.getHeight());
			g.setColor(Color.RED);
			g.fillRect(this.getWidth()-20, 0, 20, this.getHeight());
			break;
		}
		default:
			break;
		
		}
	}
	
	//YAPALIM
	public void RenkGuncelle(Graphics g) {
		
		
		
	}

	public int getSira() {
		return Sira;
	}

	public void setSira(int sira) {
		Sira = sira;
	}

	public String getIsim() {
		return Isim;
	}

	public void setIsim(String isim) {
		Isim = isim;
	}

	public int getFiyat() {
		return Fiyat;
	}

	public void setFiyat(int fiyat) {
		Fiyat = fiyat;
	}

	public int getKira() {
		return Kira;
	}

	public void setKira(int kira) {
		Kira = kira;
	}

	public int getInsaatUcreti() {
		return InsaatUcreti;
	}

	public void setInsaatUcreti(int insaatUcreti) {
		InsaatUcreti = insaatUcreti;
	}

	public boolean isSatinAlindiMi() {
		return SatinAlindiMi;
	}

	public void setSatinAlindiMi(boolean satinAlindiMi) {
		SatinAlindiMi = satinAlindiMi;
	}

	public int getEvSayisi() {
		return EvSayisi;
	}

	public void setEvSayisi(int evSayisi) {
		EvSayisi = evSayisi;
	}

	public int getOtelSayisi() {
		return OtelSayisi;
	}

	public void setOtelSayisi(int otelSayisi) {
		OtelSayisi = otelSayisi;
	}

	public boolean isSpeacialSquare() {
		return SpeacialSquare;
	}

	public void setSpeacialSquare(boolean speacialSquare) {
		SpeacialSquare = speacialSquare;
	}

	public Player getSahibi() {
		return Sahibi;
	}

	public void setSahibi(Player sahibi) {
		Sahibi = sahibi;
	}

	public boolean isSatinAlinabilir() {
		return SatinAlinabilir;
	}

	public void setSatinAlinabilir(boolean satinAlinabilir) {
		SatinAlinabilir = satinAlinabilir;
	}

	public static int getxCoord() {
		return xCoord;
	}

	public static void setxCoord(int xCoord) {
		Square.xCoord = xCoord;
	}

	public static int getyCoord() {
		return yCoord;
	}

	public static void setyCoord(int yCoord) {
		Square.yCoord = yCoord;
	}

	public JLabel getNameLabel() {
		return nameLabel;
	}

	public void setNameLabel(JLabel nameLabel) {
		this.nameLabel = nameLabel;
	}

	public static int getTotalSquares() {
		return totalSquares;
	}

	public static void setTotalSquares(int totalSquares) {
		Square.totalSquares = totalSquares;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
