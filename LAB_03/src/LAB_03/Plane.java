package LAB_03;

import javax.swing.JComponent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

public class Plane extends JComponent {
    private int height;
    private int width;
    private Thread thread;
    private boolean ready = true;
    private Graphics2D graphics2D;
    private BufferedImage image;
    private Pilot pilot;
    private Helm helm;
    private Engine engine;
    private float VolumeOfFuel;
    private float MaxSpeed;
    private PrintWriter fout;
    Plane (float volumeOfFuel, float MaxSpeed) throws IOException
    {
        this.VolumeOfFuel = volumeOfFuel;
        this.MaxSpeed = MaxSpeed;
        this.fout = new PrintWriter((new FileWriter("Log.txt")));
    }
    Plane (float volumeOfFuel) throws IOException
    {
        this.VolumeOfFuel = volumeOfFuel;
        this.MaxSpeed = 1;
        this.fout = new PrintWriter((new FileWriter("Log.txt")));
    }
    public void start()
    {   
        height = getHeight();
        width = getWidth();
        fout.write("Height = %d\nWidth = %d\n",height,width);
        image = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
        graphics2D = image.createGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        thread = new Thread(new Runnable() {
            @Override 
            public void run()
            {       
                while (ready)
                {
                    BuildBackground();
                    BuildPlane();
                    Render();                    
                }
            }
        });
        callPilot();
        try {BuildHelmAndEngine(VolumeOfFuel); //fill up at 8l;
        } catch (OutOfMemoryError e)
        {
            JOptionPane.showMessageDialog(null, "You try to fill up more fuel than it can be!","Error", JOptionPane.ERROR_MESSAGE);
            fout.write("An exception was thrown");
        }
        fout.flush();
        thread.start();
    }
    private void GoToSleep(long time) 
    {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            System.err.println(e);
        }//exception
    }
    private void BuildBackground()
    {
        graphics2D.setColor(new Color(255, 165, 0));//orange
        graphics2D.fillRect(0, height-100, width, height);; 
        graphics2D.setColor(new Color(137, 207, 240));
        graphics2D.fillRect(0, 0, width, height-100);; 
    }
    private void BuildPlane() 
    {
        pilot.BuildPilot(graphics2D);
    }
    private void callPilot()
    {
        pilot = new Pilot();
        pilot.move(Pilot.pilot_size, height-100);//start pos
    }
    private void BuildHelmAndEngine(float VolumeOfFuel)
    {
        helm = new Helm();
        engine = new Engine(MaxSpeed,VolumeOfFuel);
        requestFocus();
        addKeyListener(new KeyAdapter(){
            @Override 
            public void keyPressed(KeyEvent e)
            {
                if(e.getKeyCode() == KeyEvent.VK_A) helm.setGo_left(true);
                else if (e.getKeyCode() == KeyEvent.VK_D) helm.setGo_right(true);
                else if (e.getKeyCode() == KeyEvent.VK_R) helm.setGo_AngleUp(true);
                else if (e.getKeyCode() == KeyEvent.VK_F) helm.setGo_AngleDown(true);
                else if (e.getKeyCode() == KeyEvent.VK_SPACE) helm.setAcceleration(true);
            }
            @Override
            public void keyReleased(KeyEvent e)
            {
                if(e.getKeyCode() == KeyEvent.VK_A) helm.setGo_left(false);
                else if (e.getKeyCode() == KeyEvent.VK_D) helm.setGo_right(false);
                else if (e.getKeyCode() == KeyEvent.VK_R) helm.setGo_AngleUp(false);
                else if (e.getKeyCode() == KeyEvent.VK_F) helm.setGo_AngleDown(false);
                else if (e.getKeyCode() == KeyEvent.VK_SPACE) helm.setAcceleration(false);
            }
        });
        new Thread (new Runnable() {
            @Override 
            public void run ()
            {
                boolean gameOver = false;
                float delta = 0.5f;
                while(ready)
                {
                    if(gameOver) ready = false;
                    float angle = pilot.getAngle();
                    if(helm.getGo_AngleUp()) angle -=delta;
                    else if (helm.getGo_AngleDown()) angle +=delta;
                    else if (helm.getAcceleration()) engine.SpeedUp();
                    else if (!helm.getAcceleration()) engine.SpeedDown();
                    GoMoving();
                    OutOfScren();
                    pilot.ChangeAngle(angle);
                    GoToSleep(4);
                    if (engine.getRanOutFuel())
                     {
                        JOptionPane.showMessageDialog(null, "Ran out of fuel","Disaster", JOptionPane.INFORMATION_MESSAGE);
                        gameOver = fall();
                        GoToSleep(100);
                    };

                }
                
            }
        }).start(); 
    }
    private boolean fall()
    {  
        pilot.move(Pilot.pilot_size, height-100);
        pilot.ChangeAngle(0);
        return true;
    }
    private void OutOfScren()
    {
        double x,y;
        x = pilot.getPilot_x();
        y = pilot.getPilot_y();
        if ( x >= width ) pilot.setPilot_x(Pilot.pilot_size);
        else if ( x<0 ) pilot.setPilot_x(width);
        else if (y >= height) pilot.setPilot_y(0);
        else if (y <0) pilot.setPilot_y(height);
    }
    private void Render()
    {
        Graphics graphaics = getGraphics();
        graphaics.drawImage(image, 0, 0, null);
        graphaics.dispose();;
    }
    public void GoMoving()
    {
        double x,y;
        x = pilot.getPilot_x();
        x += Math.cos(Math.toRadians(pilot.getAngle())) * engine.getSpeed();
        pilot.setPilot_x(x);
        y = pilot.getPilot_y();
        y += Math.sin(Math.toRadians(pilot.getAngle()))* engine.getSpeed();
        pilot.setPilot_y(y);        
    }
    /**
 * Method releases used recourses
 */
 public void dispose()  throws IOException
 {
        fout.close();
 }
}