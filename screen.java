
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.List;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;
import java.applet.*;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;



public class screen extends JPanel implements MouseListener, MouseMotionListener{
    private static Color _backColor;
    boolean loading = true;
    boolean gameOn = false;
    private static long time;
    private static long startTime;
    private static int x;
    private static int y;
    private static JLabel label = new JLabel();

    public screen(int x, int y){
        this.x = x;
        this.y = y;
        setSize(new Dimension(x, y));
        addMouseListener(this);
        addMouseMotionListener(this);
        

        _backColor = Color.BLUE;

        // setBackground(_backColor);

        this.setLayout(null);

        // repaint();


        
        // this.setLayout(null);
        // this.setLayout(new FlowLayout(10));
        JButton returnButton = new JButton("<-");
        returnButton.setBounds(10, 10, 50, 50);
        this.add(returnButton);

        // this.setLayout(null);
        JButton button = new JButton("CLICK HERE");
        button.setBounds(x/2-75, y/2-50, 150, 100);
        this.add(button);

        if (loading == true) {
            button.addActionListener(e -> {
                while (true) {
                    String sec = JOptionPane.showInputDialog(this, "How many seconds do you want?");
                    try {
                        time = Integer.parseInt(sec)*1000;
                        loading = false;
                        button.setVisible(false);
                        

                        int labelW = 500;
                        int labelH = 100;
                        label.setText("<html>Click on the screen to begin the game!<br>Your goal is to click after exactly<html> " + time + "<html> milliseconds<html>");
                        label.setFont(new Font("Arial", Font.BOLD, 20)); // Set font and size
                        label.setForeground(Color.BLUE);
                        label.setSize(500, 100);
                        label.setLocation(x/2-labelW/2, y/2-labelH/2);
                        // label.setBackground(Color.BLUE);
                        // Add the label to the panel
                        this.add(label);
                        label.setOpaque(true);
                        label.setVisible(true);
                        repaint();
                        break;
                    }
                    catch (Exception err) {
                        JOptionPane.showMessageDialog(null, "The value you entered was not a number!");
                        break;
                    }
                }
            });
        }

        returnButton.addActionListener(e -> {
            loading = true;
            button.setVisible(true);
        });

        


    }

    /*
     * renders the screen called every 18 ms
     */
    public void Render(){

    }

    /*
     * incredibly bad could prolly be made way faster and more efficient 
     * but it gets the job done
     */
    public void update(){

    }

    
        

    public void clear(){
        Graphics g = getGraphics();
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    @Override
    public void mouseDragged(MouseEvent e) {

        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!loading) {
            int labelW = 500;
            int labelH = 100;
            label.setForeground(Color.BLUE);
            label.setSize(labelW, labelH);
            label.setFont(new Font("Arial", Font.BOLD, 20)); // Set font and size
            label.setLocation(x/2-labelW/2, y/2-labelH/2);
            long elapsedTime = 0;
            if (gameOn == false) {   
                startTime = (new Date()).getTime();
                label.setText("Game has begun!");
                this.add(label);
                label.setOpaque(true);
                label.setVisible(true);
                repaint();
            }
            else {
                elapsedTime = (new Date()).getTime() - startTime;
                label.setText("<html>You clicked at: " + elapsedTime + " milliseconds<br>You were off by: " + Math.abs(elapsedTime - time) + " milliseconds<html>");
                this.add(label);
                label.setOpaque(true);
                label.setVisible(true);
                repaint();
                System.out.println("The entered time was: " + time);
                System.out.println("The elapsed time was: " + elapsedTime);
                System.out.println("You were off by: " + Math.abs(elapsedTime - time) + " milliseconds");
                System.out.println("You were off by: " + (double) Math.abs(elapsedTime - time)/1000 + " seconds");
                System.out.println();
                elapsedTime = 0;
            }
            gameOn = !gameOn;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
