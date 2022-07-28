package TEDUpolly;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;


public class MainTEDUpolly extends JFrame{
	
	static int OynayanOyuncu = 0;
	static Square OynayanOyuncuSquare;
	int count=0;
    static int OynamayanOyuncu = 1;
    static Square OynamayanOyuncuSquare;
    static ArrayList<Player> players = new ArrayList<Player>();
    static int realTurSayisi=1;
	public static BufferedImage imageBuilding;
	private JPanel contentIncluder;
	static JTextArea infoConsole;
	static JTextArea islemInfo;
	JPanel playerAssetsPanel;
	CardLayout c1 = new CardLayout();
	static  JButton btnNextTurn;
	static  JButton btnRollDice;
	static  JButton btnPayRent;
	static  JButton btnBuy;
	static  JButton btnSpecial;
	static  JButton btnBuild;
	static  JButton btnSell;
	static JTextArea panelPlayer1TextArea;
	static JTextArea panelPlayer2TextArea;
	JTextArea kiraGosterimiTexti;
	Board gameBoard;
	static Player Oyuncu1;
	static Player Oyuncu2;
	static int suprisemiktar = 0;
	
	public MainTEDUpolly() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(1080,720);
		contentIncluder = new JPanel();
		contentIncluder.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentIncluder);
		contentIncluder.setLayout(null);

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		layeredPane.setBounds(6, 6, 632, 630);
		contentIncluder.add(layeredPane);

		gameBoard = new Board(6,6,612,612);
		gameBoard.setBackground(new Color(0, 42, 165, 255));
		layeredPane.add(gameBoard, new Integer(0));

		Oyuncu1 = new Player(Giris.player1, 1, Color.RED);
		players.add(Oyuncu1);
		layeredPane.add(Oyuncu1, new Integer(1));

		Oyuncu2 = new Player(Giris.player2, 2, Color.BLUE);
		players.add(Oyuncu2);
		layeredPane.add(Oyuncu2, new Integer(1));

		JPanel rightPanel = new JPanel();
		rightPanel.setBackground(Color.LIGHT_GRAY);
		rightPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		rightPanel.setBounds(634, 6, 419, 630);
		contentIncluder.add(rightPanel);
		rightPanel.setLayout(null);

		JPanel kiraIcinPanel = new JPanel();
		kiraIcinPanel.setBounds(81,230,246,78);
		rightPanel.add(kiraIcinPanel);
		kiraIcinPanel.setLayout(null);
		kiraIcinPanel.setVisible(false);

		JLabel buyOrRent = new JLabel();
		buyOrRent.setForeground(Color.RED);
		buyOrRent.setFont(new Font("Century Gothic",Font.BOLD,12));
		buyOrRent.setHorizontalAlignment(SwingConstants.CENTER);
		kiraIcinPanel.add(buyOrRent);
		buyOrRent.setBounds(6,2,220,15);

		btnBuy = new JButton("Buy");
		btnBuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Player oynayan = players.get(OynayanOyuncu);

				OynayanOyuncuSquare = oynayan.KonumBul(oynayan.KonumNo);
				oynayan.YerSatinAlma(OynayanOyuncuSquare, oynayan);

				infoConsole.setText(oynayan.Isim + " bought "+ OynayanOyuncuSquare.Isim + " with paying " + OynayanOyuncuSquare.Fiyat + "$.");

				btnBuy.setEnabled(false);
				btnNextTurn.setEnabled(true);
				updatePanelPlayer1TextArea();
				updatePanelPlayer2TextArea();
			}
		});
		btnBuy.setBounds(81, 441, 117, 29);
		rightPanel.add(btnBuy);
		btnBuy.setEnabled(false);

		btnPayRent = new JButton("Pay Rent");
		btnPayRent.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Player oynayan = players.get(OynayanOyuncu);
				Player oynamayan = players.get(OynamayanOyuncu);
				
				OynayanOyuncuSquare = oynayan.KonumBul(oynayan.KonumNo);
				
				oynayan.Kiraodeme(OynayanOyuncuSquare, oynayan);
				
				infoConsole.setText(oynayan.Isim + " paid " + OynayanOyuncuSquare.Kira + " $ to the "+ oynamayan.Isim);
				
				btnPayRent.setEnabled(false);
				btnNextTurn.setEnabled(true);
				updatePanelPlayer1TextArea();
				updatePanelPlayer2TextArea();
			}
		});
		btnPayRent.setBounds(210, 441, 117, 29);
		rightPanel.add(btnPayRent);
		btnPayRent.setEnabled(false);

		Dice Zar = new Dice(288, 406, 40, 40);
		layeredPane.add(Zar, new Integer(1));

		btnRollDice = new JButton("Roll Dice");
		btnRollDice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
				Player oynayan = players.get(OynayanOyuncu);
				Player oynamayan = players.get(OynamayanOyuncu);
					
				Zar.ZarAt();
				Zar.updateGPA(oynayan, Zar.getZarSonucu());
				int ZarTotal = Zar.getZarSonucu();
					
					oynayan.Ilerle(ZarTotal);
					
					//KONUM NOSUNA GORE KONUM BULUNCAK
					OynayanOyuncuSquare = oynayan.KonumBul(oynayan.KonumNo);
					
					//Ozel Kare
					if(OynayanOyuncuSquare.SpeacialSquare==true) {
						btnSpecial.setEnabled(true);
						btnNextTurn.setEnabled(false);
						
					}
					//Karenin sahibi yok. Para yeterse satin alabilir
					else if(OynayanOyuncuSquare.SatinAlindiMi==false && OynayanOyuncuSquare.SatinAlinabilir==true) {
						if (oynayan.Para < OynayanOyuncuSquare.Fiyat){
							btnNextTurn.setEnabled(true);
							buyOrRent.setText("Price of "+OynayanOyuncuSquare.Isim);
							kiraGosterimiTexti.setVisible(true);
							kiraIcinPanel.setVisible(true);
							kiraGosterimiTexti.setText(OynayanOyuncuSquare.Isim+"'s price is "+OynayanOyuncuSquare.Fiyat+" $.");
						}else {
							buyOrRent.setText("Price of "+OynayanOyuncuSquare.Isim);
							kiraGosterimiTexti.setVisible(true);
							kiraIcinPanel.setVisible(true);
							kiraGosterimiTexti.setText(OynayanOyuncuSquare.Isim+"'s price is "+OynayanOyuncuSquare.Fiyat+" $.");
							btnBuy.setEnabled(true);
							btnNextTurn.setEnabled(true);
						}
						
					}
					//Karenin Sahibi, insaat yapabilir BUILD/SELL
					else if(OynayanOyuncuSquare.SatinAlinabilir==true && OynayanOyuncuSquare.SatinAlindiMi==true && OynayanOyuncuSquare.Sahibi.equals(oynayan)) {
						//Insaata parasi yok
						if(oynayan.Para < OynayanOyuncuSquare.InsaatUcreti) {
							buyOrRent.setText("Building and Sell Options");
							kiraGosterimiTexti.setVisible(true);
							kiraIcinPanel.setVisible(true);
							kiraGosterimiTexti.setText("Building cost is "+OynayanOyuncuSquare.InsaatUcreti+" $."+"\nSelling price is "+OynayanOyuncuSquare.Fiyat/2+" $.");
							btnBuy.setEnabled(false);
							btnPayRent.setEnabled(false);
							btnNextTurn.setEnabled(true);
							btnSell.setEnabled(true);
							btnBuild.setEnabled(false);
							
						}
						else {
							buyOrRent.setText("Building and Sell Options");
							kiraGosterimiTexti.setVisible(true);
							kiraIcinPanel.setVisible(true);
							kiraGosterimiTexti.setText("Building cost is "+OynayanOyuncuSquare.InsaatUcreti+" $."+"\nSelling price is "+OynayanOyuncuSquare.Fiyat/2+" $.");
							btnBuy.setEnabled(false);
							btnPayRent.setEnabled(false);
							btnNextTurn.setEnabled(true);
							btnSell.setEnabled(true);
							btnBuild.setEnabled(true);
							
						}
						
					}
					//Karenin sahibi degil, kira odiycek PAY RENT
					else if(OynayanOyuncuSquare.SatinAlinabilir==true && OynayanOyuncuSquare.SatinAlindiMi==true && OynayanOyuncuSquare.Sahibi.equals(oynamayan)) {
							buyOrRent.setText("Rent Area");
							kiraGosterimiTexti.setVisible(true);
							kiraIcinPanel.setVisible(true);
							kiraGosterimiTexti.setText("The owner of "+OynayanOyuncuSquare.Isim+" is "+oynamayan.Isim+". "+oynayan.Isim+"  will pay "+OynayanOyuncuSquare.Kira+" $ rent to "+oynamayan.Isim+".");
							btnBuy.setEnabled(false);
							btnRollDice.setEnabled(false);
							btnNextTurn.setEnabled(false);
							btnPayRent.setEnabled(true);

					}
					//Start vs.
					else {
						btnNextTurn.setEnabled(true);
						
					}

				btnRollDice.setEnabled(false);
				layeredPane.remove(gameBoard);
				layeredPane.add(gameBoard, new Integer(0));
				
				updatePanelPlayer1TextArea();
				updatePanelPlayer2TextArea();

			}
		});
		btnRollDice.setBounds(81, 385, 246, 53);
		rightPanel.add(btnRollDice);

		btnNextTurn = new JButton("Next Turn");
		btnNextTurn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				kiraIcinPanel.setVisible(false);
				kiraGosterimiTexti.setVisible(false);

				btnRollDice.setEnabled(true);
				btnBuy.setEnabled(false);
				btnPayRent.setEnabled(false);
				btnNextTurn.setEnabled(false);
				btnSpecial.setEnabled(false);
				btnBuild.setEnabled(false);
				btnSell.setEnabled(false);
				
				Player oynayan = players.get(OynayanOyuncu);
				Player oynamayan = players.get(OynamayanOyuncu);
				
				OynayanOyuncuSquare = oynayan.KonumBul(oynayan.KonumNo);
				
				//oyuncu donmus ise
				if(OynayanOyuncu==0 && oynamayan.DonduMu==true) {
					oynamayan.DonduMu=false;
					
				}
				//oyuncu donmus ise
				else if(OynayanOyuncu==1 && oynamayan.DonduMu==true) {
					oynamayan.DonduMu=false;
					
				}
				//normal ise
				else if(OynayanOyuncu==0) {
					OynayanOyuncu=1;
					OynamayanOyuncu=0;
					
				}
				else if(OynayanOyuncu==1) {
					OynayanOyuncu=0;
					OynamayanOyuncu=1;
					
				}
				//Oyun biticek
				//IPTAL
				/*
				if(realTurSayisi>=112) {
					KazananiBul();
					
				}*/
				
				realTurSayisi++;
				realTurSayisi = realTurSayisi/2;
				System.out.println("Current turn: " + realTurSayisi);
				oynayan.oyuncutursayisi++;
				System.out.println(oynayan.Isim + " turn sayýsý: " + oynayan.oyuncutursayisi);
				
				c1.show(playerAssetsPanel, ""+(OynayanOyuncu==0 ? 1 : 2));
				updatePanelPlayer1TextArea();
				updatePanelPlayer2TextArea();
				infoConsole.setText("It's now player "+(OynayanOyuncu==0 ? Giris.player1 : Giris.player2)+"'s turn!");
			}

		});
		
		btnNextTurn.setBounds(81, 550, 246, 53);
		rightPanel.add(btnNextTurn);
		btnNextTurn.setEnabled(false);
		
		btnSpecial = new JButton("Special");
		btnSpecial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//SPECIAL METHODS
				Player oynayan = players.get(OynayanOyuncu);
				Player oynamayan = players.get(OynamayanOyuncu);

				OynayanOyuncuSquare = oynayan.KonumBul(oynayan.KonumNo);

				//Supriz kart ise
				if(OynayanOyuncuSquare.SpeacialSquare && OynayanOyuncuSquare.Isim.equals("Surprise")) {

					String Cards [] = new String [11];
					Cards[0] = "You won 10000$ from KDV return :)";
					Cards[1] = "You lose 10000$ from taxes :(";
					Cards[2] = "You failed a lesson, you paid $5000 for the course again :(";
					Cards[3] = "You win a scholarship! You earned $20000 :)";
					Cards[4] = "You find 1$ on the road! :)";
					Cards[5] = "One of your projects has been exploded! You paid $10000 to the school :(";
					Cards[6] = "One of your projects has been awarded! You get $5000 reward :)";
					Cards[7] = "You get sick! You paid $5000 to the hospital :(";
					Cards[8] = "One of your investments made you lost $7000 :(";
					Cards[9] = "One of your investments made you earn $7000 :)";
					Cards[10] = "You dropped your $1 :(";

					Random random = new Random();
					int randomnum = random.nextInt(10);

					switch(randomnum) {
						case 0:{
							oynayan.ArtiIslem(10000, oynayan);
							infoConsole.setText(Cards[0]);
							JOptionPane.showMessageDialog(null,"You won 10000$ from KDV return :)");
							break;
						}
						case 1:{
							oynayan.EksiIslem(10000, oynayan);
							suprisemiktar = 10000;
							infoConsole.setText(Cards[1]);
							JOptionPane.showMessageDialog(null,"You lose 10000$ from taxes :(");
							break;
						}
						case 2:{
							oynayan.EksiIslem(5000, oynayan);
							suprisemiktar = 5000;
							infoConsole.setText(Cards[2]);
							JOptionPane.showMessageDialog(null,"You failed a lesson, you paid $5000 for the course again :(");
							break;
						}
						case 3:{
							oynayan.ArtiIslem(20000, oynayan);
							infoConsole.setText(Cards[3]);
							JOptionPane.showMessageDialog(null,"You win a scholarship! You earned $20000 :)");
							break;
						}
						case 4:{
							oynayan.ArtiIslem(1, oynayan);
							infoConsole.setText(Cards[4]);
							JOptionPane.showMessageDialog(null,"You find 1$ on the road! :)");
							break;
						}
						case 5:{
							oynayan.EksiIslem(10000, oynayan);
							suprisemiktar = 10000;
							infoConsole.setText(Cards[5]);
							JOptionPane.showMessageDialog(null,"One of your projects has been exploded! You paid $10000 to the school :(");
							break;
						}
						case 6:{
							oynayan.ArtiIslem(5000, oynayan);
							infoConsole.setText(Cards[6]);
							JOptionPane.showMessageDialog(null,"One of your projects has been awarded! You get $5000 reward :)");
							break;
						}
						case 7:{
							oynayan.EksiIslem(5000, oynayan);
							suprisemiktar = 5000;
							infoConsole.setText(Cards[7]);
							JOptionPane.showMessageDialog(null,"You get sick! You paid $5000 to the hospital :(");
							break;
						}
						case 8:{
							oynayan.EksiIslem(7000, oynayan);
							suprisemiktar = 7000;
							infoConsole.setText(Cards[8]);
							JOptionPane.showMessageDialog(null,"One of your investments made you lost $7000 :(");
							break;
						}
						case 9:{
							oynayan.ArtiIslem(7000, oynayan);
							infoConsole.setText(Cards[9]);
							JOptionPane.showMessageDialog(null,"One of your investments made you earn $7000 :)");

							break;
						}
						case 10:{
							oynayan.ArtiIslem(1, oynayan);
							infoConsole.setText(Cards[10]);
							JOptionPane.showMessageDialog(null,"You dropped your $1 :(");
							break;
						}
					}
				}
				//Summer School
				else if(OynayanOyuncuSquare.SpeacialSquare==true && OynayanOyuncuSquare.Isim.equals("Summer School")) {

					oynayan.EksiIslem(5000, oynayan);
					oynayan.SpecialGPA(1);
					infoConsole.setText(oynayan.Isim + " registred for Summer School. (-$5000)");
					JOptionPane.showMessageDialog(null,oynayan.Isim + " registred for Summer School. (-$5000)");

				}
				//Registration Freeze
				else if(OynayanOyuncuSquare.SpeacialSquare==true && OynayanOyuncuSquare.Isim.equals("Registration Freeze")) {

					oynayan.DonduMu=true;
					infoConsole.setText(oynayan.Isim + " freezed his registration for 1 turn.");
					JOptionPane.showMessageDialog(null,oynayan.Isim + " freezed his registration for 1 turn.");

				}//ERASMUS
				else if(OynayanOyuncuSquare.SpeacialSquare==true && OynayanOyuncuSquare.Isim.equals("Erasmus")) {

					oynayan.EksiIslem(25000, oynayan);
					oynayan.SpecialGPA(4);
					infoConsole.setText(oynayan.Isim + " will go to erasmus. Hope he/she got enough money :)");
					JOptionPane.showMessageDialog(null,oynayan.Isim + " will go to erasmus. Hope he/she got enough money :)");

				}else if(OynayanOyuncuSquare.SpeacialSquare==true && OynayanOyuncuSquare.Isim.equals("Start")){
					int maas = (int)5000 * (int)oynayan.GPA;
					oynayan.ArtiIslem(maas, oynayan);

				}
				updatePanelPlayer1TextArea();
				updatePanelPlayer2TextArea();
				btnSpecial.setEnabled(false);
				btnNextTurn.setEnabled(true);

				//ERROR SAYFASI GIBI SAYFA ACTIRIP CARTI GOSTERCEZ
				
				
				
			}
		});
		btnSpecial.setBounds(81, 474, 246, 40);
		rightPanel.add(btnSpecial);
		btnSpecial.setEnabled(false);

		btnBuild = new JButton("Build");
		btnBuild.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Player oynayan = players.get(OynayanOyuncu);
				Player oynamayan = players.get(OynamayanOyuncu);

				OynayanOyuncuSquare = oynayan.KonumBul(oynayan.KonumNo);

				JPanel evPaneli = new JPanel();

				oynayan.InsaatYapma(OynayanOyuncuSquare, oynayan);
				buyOrRent.setText("New Rent Cost of "+OynayanOyuncuSquare.Isim);
				kiraGosterimiTexti.setVisible(true);
				kiraIcinPanel.setVisible(true);
				kiraGosterimiTexti.setText(OynayanOyuncuSquare.Isim+"'s price updated. \n New rent cost is "+OynayanOyuncuSquare.Kira+" $.");

				infoConsole.setText(oynayan.Isim + ", made a building to " + OynayanOyuncuSquare.Isim + " square with the cost of " + OynayanOyuncuSquare.InsaatUcreti + "$.");

				updatePanelPlayer1TextArea();
				updatePanelPlayer2TextArea();

				btnBuild.setEnabled(false);
				btnSell.setEnabled(false);

			}
		});
		btnBuild.setBounds(81,518,117,29);
		rightPanel.add(btnBuild);

		btnBuild.setEnabled(false);

		btnSell = new JButton("Sell");
		btnSell.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Player oynayan = players.get(OynayanOyuncu);
				Player oynamayan = players.get(OynamayanOyuncu);

				OynayanOyuncuSquare = oynayan.KonumBul(oynayan.KonumNo);

				oynayan.YerSat(OynayanOyuncuSquare, oynayan);
				infoConsole.setText(oynayan.Isim + ", selled " + OynayanOyuncuSquare.Isim + " for " +OynayanOyuncuSquare.Fiyat/2 + "$.");


				updatePanelPlayer1TextArea();
				updatePanelPlayer2TextArea();
				btnBuild.setEnabled(false);
				btnSell.setEnabled(false);
			}
		});
		btnSell.setBounds(210,518,117,29);
		rightPanel.add(btnSell);
		btnSell.setEnabled(false);

		JPanel test = new JPanel();
		test.setBounds(81, 312, 246, 68);
		rightPanel.add(test);
		test.setLayout(null);

		playerAssetsPanel = new JPanel();
		playerAssetsPanel.setBounds(81, 28, 246, 189);
		rightPanel.add(playerAssetsPanel);
		playerAssetsPanel.setLayout(c1);

		JPanel panelPlayer1 = new JPanel();
		panelPlayer1.setBackground(Color.RED);
		playerAssetsPanel.add(panelPlayer1, "1");
		panelPlayer1.setLayout(null);

		JLabel panelPlayer1Title = new JLabel(Giris.player1+" All Wealth");
		panelPlayer1Title.setFont(new Font("Century Gothic",Font.BOLD,14));
		panelPlayer1Title.setForeground(Color.WHITE);
		panelPlayer1Title.setHorizontalAlignment(SwingConstants.CENTER);
		panelPlayer1Title.setBounds(0, 6, 240, 16);
		panelPlayer1.add(panelPlayer1Title);

		panelPlayer1TextArea = new JTextArea();
		panelPlayer1TextArea.setBounds(10, 34, 230, 149);
		panelPlayer1.add(panelPlayer1TextArea);

		JPanel panelPlayer2 = new JPanel();
		panelPlayer2.setBackground(Color.BLUE);
		playerAssetsPanel.add(panelPlayer2, "2");
		panelPlayer2.setLayout(null);
		c1.show(playerAssetsPanel, ""+OynayanOyuncu);

		JLabel panelPlayer2Title = new JLabel(Giris.player2+" All Wealth");
		panelPlayer2Title.setFont(new Font("Century Gothic",Font.BOLD,14));
		panelPlayer2Title.setForeground(Color.WHITE);
		panelPlayer2Title.setHorizontalAlignment(SwingConstants.CENTER);
		panelPlayer2Title.setBounds(0, 6, 240, 16);
		panelPlayer2.add(panelPlayer2Title);

		panelPlayer2TextArea = new JTextArea();
		panelPlayer2TextArea.setBounds(10, 34, 230, 149);
		panelPlayer2.add(panelPlayer2TextArea);
		
		updatePanelPlayer1TextArea();
		updatePanelPlayer2TextArea();

		kiraGosterimiTexti = new JTextArea();
		kiraGosterimiTexti.setColumns(20);
		kiraGosterimiTexti.setRows(5);
		kiraIcinPanel.add(kiraGosterimiTexti);
		kiraGosterimiTexti.setBounds(6,20,234,56);
		kiraGosterimiTexti.setLineWrap(true);
		kiraGosterimiTexti.setVisible(false);

		infoConsole = new JTextArea();
		infoConsole.setColumns(20);
		infoConsole.setRows(5);
		infoConsole.setBounds(6, 6, 234, 56);
		test.add(infoConsole);
		infoConsole.setLineWrap(true);
		infoConsole.setText(Giris.player1+" starts the game by clicking Roll Dice!");

		
		//TRANSACTIONS EKLENCEK

		
	}
	
	
	public static void updatePanelPlayer2TextArea() {
		// TODO Auto-generated method stub
		String result2 = Oyuncu2.Bilgilendirme();
		panelPlayer2TextArea.setText(result2);

		
	}

	public static void updatePanelPlayer1TextArea() {
		// TODO Auto-generated method stub
		String result = Oyuncu1.Bilgilendirme();
		panelPlayer1TextArea.setText(result);

		
	}
	
	public static void errorBox(String infoMessage, String titleBar) {
		JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.ERROR_MESSAGE);
	}

	public static void KazananiBul() {

		Player oynayan = players.get(OynayanOyuncu);
		Player oynamayan = players.get(OynamayanOyuncu);

		OynayanOyuncuSquare = oynayan.KonumBul(oynayan.KonumNo);

		btnNextTurn.setEnabled(false);
		btnRollDice.setEnabled(false);
		btnPayRent.setEnabled(false);
		btnBuy.setEnabled(false);
		btnSpecial.setEnabled(false);
		btnBuild.setEnabled(false);
		btnSell.setEnabled(false);
		int oynamayandeger = (int)oynamayan.DegerHesaplama();
		
		System.out.println(oynamayan.Isim + " WIN THE GAME WITH "+oynamayandeger+ "$ WEALTH!!");
		Finish bitir = new Finish(oynamayan);
		bitir.setVisible(true);
		
		/*
		double oynayandeger = oynayan.DegerHesaplama();
		double oynamayandeger = oynamayan.DegerHesaplama();


		if(oynayandeger > oynamayandeger) {
			infoConsole.setText(oynayan.Isim + ", " + oynayandeger + "$ malvarligi ile oyunu kazandi!!");
			btnNextTurn.setEnabled(false);
			btnRollDice.setEnabled(false);
			btnPayRent.setEnabled(false);
			btnBuy.setEnabled(false);
			btnSpecial.setEnabled(false);

		}
		else if(oynamayandeger > oynayandeger) {
			infoConsole.setText(oynamayan.Isim + ", " + oynamayandeger + "$ malvarligi ile oyunu kazandi!!");
			btnNextTurn.setEnabled(false);
			btnRollDice.setEnabled(false);
			btnPayRent.setEnabled(false);
			btnBuy.setEnabled(false);
			btnSpecial.setEnabled(false);

		}
		else {
			infoConsole.setText("Oyun berabere bitti !!");
			btnNextTurn.setEnabled(false);
			btnRollDice.setEnabled(false);
			btnPayRent.setEnabled(false);
			btnBuy.setEnabled(false);
			btnSpecial.setEnabled(false);

		}
		*/
	}

	public static void main(String[] args) {

		MainTEDUpolly frame = new MainTEDUpolly();
		frame.setVisible(true);

	}

}