package TEDUpolly;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class EvSatimi extends JFrame {
   static JRadioButton ilkEv;
   static JPanel panel;
   static JButton sellBuild;
   static JLabel satilacaklar;
    int y=80;
    public static BufferedImage soldout;
    DefaultListModel<JRadioButton> evler = new DefaultListModel<>();


  /*  public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(soldout,0,0,soldout.getWidth()/4,soldout.getHeight()/4,panel);
    }*/

    public EvSatimi(Player a) {
    	
    	if(a.MalVarligi.size() == 0) {
    		//OYUN BITER, KAZANAN OYNAMAYAN
    		MainTEDUpolly.KazananiBul();
    		
    	}
    	
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100,100,100,200);
        setSize(300,500);



        panel = new JPanel();
        setContentPane(panel);
        panel.setLayout(null);
        panel.setBackground(Color.ORANGE);


        satilacaklar = new JLabel();
        panel.add(satilacaklar);
        satilacaklar.setBounds(100,60,100,20);
        satilacaklar.setText(a.Isim+"'s Assets");
        satilacaklar.setFont(new Font("Century Gothic",Font.BOLD,15));

        ilkEv = new JRadioButton();
        ilkEv.setBounds(100,y,85,20);

        ilkEv.setText(a.MalVarligi.get(0).Isim);
        panel.add(ilkEv);
        evler.addElement(ilkEv);


         if(a.MalVarligi.size() > 1){
            for(int i=1; i <a.MalVarligi.size(); i++){
                JRadioButton yenievler = new JRadioButton();
                yenievler.setBounds(100,y+=20,85,20);
                yenievler.setText(a.MalVarligi.get(i).Isim);
                panel.add(yenievler);
                evler.addElement(yenievler);
            }
        }

        sellBuild = new JButton();
        sellBuild.setBounds(120,300,50,50);
        sellBuild.setText("Sell");
        panel.add(sellBuild);

        sellBuild.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

               for (int i=0; i < evler.size(); i++){
                  if(evler.getElementAt(i).isSelected()){
                      
                      try{
                          for (Square s : a.MalVarligi){
                              if(s.Isim.equals(evler.getElementAt(i).getText())){
                                  a.YerSat(s,a);
                                  panel.remove(evler.getElementAt(i));

                              }
                          }
                      }catch (java.util.ConcurrentModificationException er){
                          System.out.println("Selled !!");
                      }

                  }
               }

                MainTEDUpolly.updatePanelPlayer1TextArea();
                MainTEDUpolly.updatePanelPlayer2TextArea();
                setVisible(false);

                //Eger parasý yetiyosa odeyip devam, yoksa yeniden sat
                Square aSquare = a.KonumBul(a.KonumNo);

				//Parasý yetiyorsa gereken ucreti odeyip sonraki tura gececek. Yoksa yeniden satma ekraný
				MainTEDUpolly.btnNextTurn.setEnabled(false);
				if(aSquare.SpeacialSquare == true) {
					if(aSquare.Isim.equals("Surprise")) {
						//speciale basýlmamasý ve paranýn çekilip nextturn un açýlmasý lazým.
						if(a.Para < MainTEDUpolly.suprisemiktar) {
							a.EksiIslem(MainTEDUpolly.suprisemiktar, a);
							
						}
						MainTEDUpolly.btnNextTurn.setEnabled(true);
						MainTEDUpolly.btnSpecial.setEnabled(false);
						
					}
					else
						MainTEDUpolly.btnSpecial.setEnabled(true);
					
				}
				else if(aSquare.Sahibi.equals(a) == false)
					MainTEDUpolly.btnPayRent.setEnabled(true);
				
				
				
				
				

            }
        });


    }



}
