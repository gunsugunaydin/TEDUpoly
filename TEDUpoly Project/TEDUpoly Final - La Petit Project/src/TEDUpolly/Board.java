package TEDUpolly;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Board extends JPanel {

	public static ArrayList<Square> Squares = new ArrayList<Square>();
	public static ArrayList<Square> SpecialSquares = new ArrayList<Square>();
	public static BufferedImage start;
	public static BufferedImage surprise;
	public static BufferedImage surprise1;
	public static BufferedImage gym;
	public static BufferedImage main;
	public static BufferedImage pool;
	public static BufferedImage erasmus;
	public static BufferedImage logo;
	public static BufferedImage summer;
	public static BufferedImage freeze;


	public Board(int xCoord, int yCoord, int width, int height) {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBounds(xCoord, yCoord, 612, 612);
		this.setLayout(null);
		initializeSquares();

		//START
		try {
			start = ImageIO.read(new FileImageInputStream(new File("start1.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//SURPRISE
		try {
			surprise = ImageIO.read(new FileImageInputStream(new File("surprise.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//GYM
		try {
			gym = ImageIO.read(new FileImageInputStream(new File("gym.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//MAIN LOGO
		try {
			main = ImageIO.read(new FileImageInputStream(new File("main.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//POOL
		try {
			pool = ImageIO.read(new FileImageInputStream(new File("pool.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//ERASMUS
		try {
			erasmus = ImageIO.read(new FileImageInputStream(new File("erasmus.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//TEDUPOLLY
		try {
			logo = ImageIO.read(new FileImageInputStream(new File("tedupoly.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//SUPRISE2
		try {
			surprise1 = ImageIO.read(new FileImageInputStream(new File("surprise1.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//SUMMERSCHOOL
		try {
			summer = ImageIO.read(new FileImageInputStream(new File("summer.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//REGISTRATION FREEZE
		try {
			freeze = ImageIO.read(new FileImageInputStream(new File("freeze.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public void paint(Graphics g) {
		super.paint(g);

		g.drawImage(start,0,12,start.getWidth()/7,start.getHeight()/7,this);
		g.drawImage(surprise,508,208,surprise.getWidth()/7,surprise.getHeight()/7,this);
		g.drawImage(gym,212,-5,gym.getWidth()/5,gym.getHeight()/5,this);
		g.drawImage(main,250,125,main.getWidth()/5,main.getHeight()/5,this);
		g.drawImage(pool,308,506,pool.getWidth()/6,pool.getHeight()/6,this);
		g.drawImage(erasmus,455,475,erasmus.getWidth()/5,erasmus.getHeight()/4,this);
		g.drawImage(logo,179,310,logo.getWidth()/3,logo.getHeight()/3,this);
		g.drawImage(surprise1,6,208,surprise1.getWidth()/7,surprise1.getHeight()/7,this);
		g.drawImage(summer,505,2,summer.getWidth()/12,summer.getHeight()/8,this);
		g.drawImage(freeze,-20,520,freeze.getWidth()/6,freeze.getHeight()/6,this);
		
	}

	private void initializeSquares() {
		// TODO Auto-generated method stub

		//(int xCoord, int yCoord, int width, int height, String labelString, int rotationDegrees, int siraA, String isimA, int fiyatA, int kiraA, int insa ucretiA, boolean satinalindi mi, boolean specialsquareA, boolean satinalinabilirA)
		//ust taraf
		Square square00 = new Square(6,6,100,100,135, 0, "Start", 0, 0, 0, false, true, false);
		this.add(square00);
		Squares.add(square00);
		SpecialSquares.add(square00);
		
		Square square01 = new Square(106,6,100,100,0, 1, "F Block 1", 15000, 1500, 1000, false, false, true);
		this.add(square01);
		Squares.add(square01);
		
		Square square02 = new Square(206,6,100,100,0, 2, "TEDU GYM", 20000, 2500, 2000, false, false, true);
		this.add(square02);
		Squares.add(square02);
		
		Square square03 = new Square(306,6,100,100,0, 3, "F Block 2", 18000, 2100, 1000, false, false, true);
		this.add(square03);
		Squares.add(square03);
		
		Square square04 = new Square(406,6,100,100,0, 4, "F Block 3", 20000, 2600, 1000, false, false, true);
		this.add(square04);
		Squares.add(square04);
		
		Square square05 = new Square(506,6,100,100,-135, 5, "Summer School", 2000, 250, 500, false, true, false);
		this.add(square05);
		Squares.add(square05);
		SpecialSquares.add(square05);

		//sag taraf
		Square square06 = new Square(506,106,100,100,-90, 6, "G Block 1", 25000, 3150, 3000, false, false, true);
		this.add(square06);
		Squares.add(square06);
		
		Square square07 = new Square(506,206,100,100,-90, 7, "Surprise", 2000, 250, 500, false, true, false);
		this.add(square07);
		Squares.add(square07);
		SpecialSquares.add(square07);
		
		Square square08 = new Square(506,306,100,100,-90, 8, "G Block 2", 30000, 3600, 3000, false, false, true);
		this.add(square08);
		Squares.add(square08);
		
		Square square09 = new Square(506,406,100,100,-90, 9, "G Block 3", 33000, 4350, 3000, false, false, true);
		this.add(square09);
		Squares.add(square09);
		
		Square square10 = new Square(506,506,100,100,-45, 10, "Erasmus", 2000, 250, 500, false, true, false);
		this.add(square10);
		Squares.add(square10);
		SpecialSquares.add(square10);

		//alt taraf
		Square square11 = new Square(406,506,100,100,0, 11, "B Block 1", 37500, 4700, 5000, false, false, true);
		this.add(square11);
		Squares.add(square11);
		
		Square square12 = new Square(306,506,100,100,0, 12, "Swimming Pool", 20000, 2500, 2000, false, false, true);
		this.add(square12);
		Squares.add(square12);
		
		Square square13 = new Square(206,506,100,100,0, 13, "B Block 2", 45000, 5400, 5000, false, false, true);
		this.add(square13);
		Squares.add(square13);
		
		Square square14 = new Square(106,506,100,100,0, 14, "B Block 3", 50000, 6600, 5000, false, false, true);
		this.add(square14);
		Squares.add(square14);
		
		Square square15 = new Square(6,506,100,100,45, 15, "Registration Freeze", 2000, 250, 500, false, true, false);
		this.add(square15);
		Squares.add(square15);
		SpecialSquares.add(square15);
		
		//sol taraf
		Square square16 = new Square(6,406,100,100,90, 16, "A Block 1", 62500, 7800, 7000, false, false, true);
		this.add(square16);
		Squares.add(square16);
		
		Square square17 = new Square(6,306,100,100,90, 17, "A Block 2", 75000, 9000, 7000, false, false, true);
		this.add(square17);
		Squares.add(square17);
		
		Square square18 = new Square(6,206,100,100,90, 18, "Surprise", 2000, 250, 500, false, true, false);
		this.add(square18);
		Squares.add(square18);
		SpecialSquares.add(square18);
		
		Square square19 = new Square(6,106,100,100,90, 19, "A Block 3", 82500, 11000, 7000, false, false, true);
		this.add(square19);
		Squares.add(square19);
		
	}
	
	//Oyuncunun sira nosuna bakarak Square bulma
	public Square KonumBul(int konumnoA) {
		Square Bulundu = null;
		for(int i=0; i<this.Squares.size(); i++) {
			Square Konum = Squares.get(i);
			int No = Konum.Sira;
			
			if(No == konumnoA) {
				Bulundu = Konum;
			}
		}
		return Bulundu;
		
	}

	public ArrayList<Square> getSpecialSquares(){
		return SpecialSquares;
	}
	
	public ArrayList<Square> getSquares(){
		return Squares;
	}

	public static BufferedImage getStart() {
		return start;
	}

	public static void setStart(BufferedImage start) {
		Board.start = start;
	}

	public static BufferedImage getSurprise() {
		return surprise;
	}

	public static void setSurprise(BufferedImage surprise) {
		Board.surprise = surprise;
	}

	public static BufferedImage getSurprise1() {
		return surprise1;
	}

	public static void setSurprise1(BufferedImage surprise1) {
		Board.surprise1 = surprise1;
	}

	public static BufferedImage getGym() {
		return gym;
	}

	public static void setGym(BufferedImage gym) {
		Board.gym = gym;
	}

	public static BufferedImage getMain() {
		return main;
	}

	public static void setMain(BufferedImage main) {
		Board.main = main;
	}

	public static BufferedImage getPool() {
		return pool;
	}

	public static void setPool(BufferedImage pool) {
		Board.pool = pool;
	}

	public static BufferedImage getErasmus() {
		return erasmus;
	}

	public static void setErasmus(BufferedImage erasmus) {
		Board.erasmus = erasmus;
	}

	public static BufferedImage getLogo() {
		return logo;
	}

	public static void setLogo(BufferedImage logo) {
		Board.logo = logo;
	}

	public static BufferedImage getSummer() {
		return summer;
	}

	public static void setSummer(BufferedImage summer) {
		Board.summer = summer;
	}

	public static BufferedImage getFreeze() {
		return freeze;
	}

	public static void setFreeze(BufferedImage freeze) {
		Board.freeze = freeze;
	}

	public static void setSquares(ArrayList<Square> allSquares) {
		Board.Squares = allSquares;
	}

	public static void setSpecialSquares(ArrayList<Square> SpecialSquares) {
		Board.SpecialSquares = SpecialSquares;
	}
	
}
