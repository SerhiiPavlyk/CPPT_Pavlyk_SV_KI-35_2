/**
 * LAB_04 package
 */ 
package LAB_04;

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
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
/**
 * Class Plane realize game simulator of Plane
 * 
 * @author Pavlyk Serhii
 * @version 1.0
 */
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
    private List<Bomb> bombs;
    private int shootTime;
    
/**
 * Constructor
 * @param volumeOfFuel volume of fuel
 * @param MaxSpeed - maximum speed of the aircraft
 * @throws IOException
 */ 
    Plane (float volumeOfFuel, float MaxSpeed) throws IOException
    {
        this.VolumeOfFuel = volumeOfFuel;
        this.MaxSpeed = MaxSpeed;
        this.fout = new PrintWriter((new FileWriter("Log.txt")));
    }
    /**
 * Constructor
 * @Param volumeOfFuel volume of fuel
 * @throws IOException
 */ 
    Plane (float volumeOfFuel) throws IOException
    {
        this.VolumeOfFuel = volumeOfFuel;
        this.MaxSpeed = 1;
        this.fout = new PrintWriter((new FileWriter("Log.txt")));
    }
    
    /**
 * Method start starts the game
 */ 
    public void start()
    {   
        height = getHeight();
        width = getWidth();
        fout.write("Height = ");
        fout.write(String.valueOf(height));
        fout.write(" Width = ");
        fout.write(String.valueOf(width));
        fout.write(" \n");
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
        loadBombs();
        try {BuildHelmAndEngine(VolumeOfFuel); //fill up at 8l;
        } catch (OutOfMemoryError e)
        {
            JOptionPane.showMessageDialog(null, "You try to fill up more fuel than it can be!","Error", JOptionPane.ERROR_MESSAGE);
            fout.write("An exception was thrown\n");
        }
        if (!ready)
                {
                    fout.close();
                }
        thread.start();
    }
     /**
 * Method GoToSleep puts to sleep the thread in which it is called
 * @param  time час на який потік засинає у мілісекундах
 */ 
    private void GoToSleep(long time) 
    {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            System.err.println(e);
        }//exception
    }
    /**
 * Method BuildBackground create Background
 */ 
    private void BuildBackground()
    {
        graphics2D.setColor(new Color(255, 165, 0));//orange
        graphics2D.fillRect(0, height-100, width, height);
        graphics2D.setColor(new Color(137, 207, 240));
        graphics2D.fillRect(0, 0, width, height-100);
        fout.write("BuildBackground was successful\n");
    }
     /**
 * Method BuildPlane creates an airplane and use Bomb
 */ 
    private void BuildPlane() 
    {
        pilot.BuildPilot(graphics2D);
        for(int i = 0; i < bombs.size(); i++)
        {
            Bomb bomb = bombs.get(i);
            if(bomb != null)
            {
                bomb.draw(graphics2D);
                fout.write("Draw bomb was successful\n");
            } 
        }
        fout.write("BuildPlane was successful\n");


    }
     /**
 * Method creates an object of the class Pilot
 */ 
    private void callPilot() 
    {
        pilot = new Pilot();
        pilot.move(Pilot.pilot_size, height-100);//start pos
        fout.write("callPilot was successful\n");
    }
     /**
 * Method creates an object of the class Helm та Engine
 * @param VolumeOfFuel hold the fuel tank in liters
 */ 
    private void BuildHelmAndEngine(float VolumeOfFuel)
    {
        helm = new Helm();
        engine = new Engine(MaxSpeed,VolumeOfFuel);
        requestFocus();
        addKeyListener(new KeyAdapter(){
            @Override 
            public void keyPressed(KeyEvent e)
            {
                if (e.getKeyCode() == KeyEvent.VK_R) helm.setGo_AngleUp(true);
                else if (e.getKeyCode() == KeyEvent.VK_F) helm.setGo_AngleDown(true);
                else if (e.getKeyCode() == KeyEvent.VK_SPACE) helm.setAcceleration(true);
                else if (e.getKeyCode() == KeyEvent.VK_T) helm.setStartShoot(true);
                else if (e.getKeyCode() == KeyEvent.VK_G) helm.setEndShoot(true);
            }
            @Override
            public void keyReleased(KeyEvent e)
            {
                if (e.getKeyCode() == KeyEvent.VK_R) helm.setGo_AngleUp(false);
                else if (e.getKeyCode() == KeyEvent.VK_F) helm.setGo_AngleDown(false);
                else if (e.getKeyCode() == KeyEvent.VK_SPACE) helm.setAcceleration(false);
                else if (e.getKeyCode() == KeyEvent.VK_T) helm.setStartShoot(false);
                else if (e.getKeyCode() == KeyEvent.VK_G) helm.setEndShoot(false);
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
                    else if (helm.getStartShoot() || helm.getEndShoot())
                    {
                        if(shootTime == 0)
                        {
                            if(helm.getStartShoot())
                            bombs.add(0,new Bomb(pilot.getPilot_x(), pilot.getPilot_y(), pilot.getAngle(), 9, 1.5f));
                            else
                             bombs.add(0,new Bomb(pilot.getPilot_x()+Pilot.pilot_size, pilot.getPilot_y()+Pilot.pilot_size, pilot.getAngle(), 20, 2f));
                        }
                        shootTime++;
                        if (shootTime == 15) shootTime =0;
                    }
                    else shootTime = 0;
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
        fout.write("BuildHelmAndEngine was successful\n");
    }
     /**
 * Method fall ends the game if the plane runs out of fuel and returns it to its starting position
 */ 
    private boolean fall()
    {  
        pilot.move(Pilot.pilot_size, height-100);
        pilot.ChangeAngle(0);
        fout.write("fall was successful\n");
        return true;
    }
     /**
 * Method performs checks to see if the plane does not go beyond the limits of the application,
 *  if it does, then corrects its coordinates
 */ 
    private void OutOfScren()
    {
        double x,y;
        x = pilot.getPilot_x();
        y = pilot.getPilot_y();
        if ( x >= width ) pilot.setPilot_x(Pilot.pilot_size);
        else if ( x<0 ) pilot.setPilot_x(width);
        else if (y >= height) pilot.setPilot_y(0);
        else if (y <0) pilot.setPilot_y(height);
        fout.write("OutOfScren was successful\n");
    }
      /**
 * Method Render generates graphic objects
 */ 
    private void Render()
    {
        Graphics graphaics = getGraphics();
        graphaics.drawImage(image, 0, 0, null);
        graphaics.dispose();
        fout.write("Render was successful\n");
    }
      /**
 * Method GoMoving responsible for the movement of the Plane
 */ 
    public void GoMoving()
    {
        double x,y;
        x = pilot.getPilot_x();
        x += Math.cos(Math.toRadians(pilot.getAngle())) * engine.getSpeed();
        pilot.setPilot_x(x);
        y = pilot.getPilot_y();
        y += Math.sin(Math.toRadians(pilot.getAngle()))* engine.getSpeed();
        pilot.setPilot_y(y);       
        fout.write("GoMoving was successful\n"); 
    }
    /**
 * Method releases used recourses
 */
 public void dispose()  throws IOException
 {
        fout.close();
 }
 /**
 * Method loadBombs creates bombs so that the plane can shoot
 */ 
private void loadBombs()
{
    bombs = new ArrayList<>();
    new Thread (new Runnable() {
        @Override
        public void run()
        {
            while(ready)
            {
                for (int i = 0; i < bombs.size(); i++)
                {
                    Bomb bomb = bombs.get(i);
                    if(bomb != null)
                    {
                        bomb.update();
                        if(!bomb.Cheeck(width, height))
                        {
                            bombs.remove(bomb);
                        }
                    }
                    else bombs.remove(bomb);
                }
                GoToSleep(1);
            }
        }
    }).start();
}
/**
 * Method return the ready
 * */
public boolean isReady()
 {
    return ready;
}
}
