package TEDUpolly;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Finish extends JFrame {
    JPanel panel;
    public static BufferedImage win;
    JLabel isim;
    
    public Finish(Player a)  {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0,0,100,200);
        setSize(1467,767);

        panel = new JPanel();
        setContentPane(panel);
        panel.setLayout(null);
        panel.setBackground(Color.BLUE);

      try{
          win = ImageIO.read(new FileImageInputStream(new File("win.png")));
      }catch (IOException e) {
          e.printStackTrace();
      }

      isim = new JLabel();
      isim.setForeground(Color.ORANGE);
      isim.setText(a.Isim + " WON THE GAME !!!");
      isim.setBounds(445,550,800,100);
      panel.add(isim);
      isim.setFont(new Font("Century Gothic",Font.BOLD,50));


    }
    public void paint(Graphics g) {
        super.paint(g);

        g.drawImage(win,0,12,win.getWidth(),win.getHeight(),this);

    }

    /*public static void main(String args[]) {
    	new Finish().setVisible(true);
    	//TEST AMACLI
    }*/


}
