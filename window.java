

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
/*
 * this is just a little window used to dispaly the program on a JFrame
 * runs the thread that updates and renders the screen
 */
public class window extends JFrame{
    screen _screen;
    
    public window(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        int width = 1000;
        int height = 750;
        setSize(width, height);
        setResizable(false);
        setTitle("Timer Game");
        _screen = new screen(width, height);
        add(_screen);
        setVisible(true);
        addKeyListener(new KeyInterceptor());
        TimerTask task = new TimerTask() {

            public void run(){
                _screen.Render();
            }
        };

        TimerTask updateScreen = new TimerTask() {
            
            public void run(){
                _screen.update();
            }
        };
        

        //WOAH threading!
        Timer tick = new Timer();
        tick.schedule(task, 0, 1);

        tick.schedule(updateScreen, 0,  1);

        
    }



}
