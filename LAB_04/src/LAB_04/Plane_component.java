/**
 * LAB_04 package
 */ 
package LAB_04;
 
import java.awt.*;
import java.awt.geom.AffineTransform;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon; 
/**
 * Class Pilot realize component of Plane as Pilot
 * whith responsible for  Plane class possibilities moving 
 * 
 * @author Pavlyk Serhii
 * @version 1.0s
 */
class Pilot
{/**
 * Constructor load image of an airplane
 */ 
    public Pilot() 
    {
        this.image = new ImageIcon("PlaneModel.png").getImage();
    }
    /*
     * pilot_size static field equal 64
     */
    public static final double pilot_size = 64;
    private double pilot_x,pilot_y;
    private float angle =0;
    private final Image image;
    /** Method ChangeAngle changes the angle of inclination of the plane
    * @param  angle the angle by which the value changes
    */ 
    public void ChangeAngle(float angle)
    {
        if  (angle<0) angle = 359;
        else if(angle > 359) angle = 0;
        this.angle = angle;
        
    }
    /**
 * Method sets the Y  position
 * */
    public void setPilot_y(double pilot_y) {
        this.pilot_y = pilot_y;
    }
    /**
 * Method sets the X  position
 * */
    public void setPilot_x(double pilot_x) {
        this.pilot_x = pilot_x;
    }
    /**
 * Method return the Y  position
 * */
    public double getPilot_y() {
        return pilot_y;
    }
    /**
 * Method return the X  position
 * */
    public double getPilot_x() {
        return pilot_x;
    }
    /**
 * Method BuildPilot creates a graphic model of an airplane
 * @param d2 Graphics2D
 * */
    public void BuildPilot(Graphics2D d2)
    {
        AffineTransform old = d2.getTransform();
        d2.translate(pilot_x, pilot_y);
        AffineTransform transform = new AffineTransform();
        transform.rotate(Math.toRadians(angle+90),pilot_size/2,pilot_size/2);
        d2.drawImage(image, transform, null);
        d2.setTransform(old);
    }
     /**
 * Method move sets a specific position to move the aircraft to
 * @param x set x position
 * @param y set y position
 * 
 */ 
    public void move(double x, double y)
    {
        this.pilot_x = x;
        this.pilot_y = y;
    }
     /**
 * Method return the angle  position
 * */
    public float getAngle() {
        return angle;
    }
     /**
 * Method set the angle  position
 * */
    public void setAngle(float angle) {
        this.angle = angle;
    }
}
/**
 * Class Helm realize component of Plane as Helm
 * whith responsible for  Plane class possibilities get signal from user 
 * 
 * @author Pavlyk Serhii
 * @version 1.0
 */
class Helm
{
    private boolean go_AngleUp;
    private boolean go_AngleDown;
    private boolean acceleration;
    private boolean StartShoot;
    private boolean EndShoot;
/**
 * Method return the Acceleration  position
 * */
    public boolean getAcceleration() {
        return acceleration;
    }
     /**
 * Method set the acceleration  position
 * */
    public void setAcceleration(boolean acceleration) {
        this.acceleration = acceleration;
    }
    /**
 * Method return the AngleUp  position
 * */
    public boolean getGo_AngleUp() {
        return go_AngleUp;
    }
     /**
 * Method set the AngleUp  position
 * */
    public void setGo_AngleUp(boolean go_AngleUp) {
        this.go_AngleUp = go_AngleUp;
    }
   /**
 * Method return the AngleDown  position
 * */
    public boolean getGo_AngleDown() {
        return go_AngleDown;
    }
     /**
 * Method set the AngleDown position
 * */
    public void setGo_AngleDown(boolean go_AngleDown) {
        this.go_AngleDown = go_AngleDown;
    }
    /**
 * Method return the getStartShoot  
 * */
    public boolean getStartShoot() {
        return StartShoot;
    }
     /**
 * Method set the setStartShoot
 * */
    public void setStartShoot(boolean startShoot) {
        StartShoot = startShoot;
    }
    /**
 * Method return the getEndShoot  
 * */
    public boolean getEndShoot() {
        return EndShoot;
    }
     /**
 * Method set the setEndShoot
 * */
    public void setEndShoot(boolean endShoot) {
        EndShoot = endShoot;
    }
}
/**
 * Class Engine realize component of Plane as Engine
 * whith responsible for Plane class possibilities 
 * control Fuel level and speedUp or SpeedDown 
 * @author Pavlyk Serhii
 * @version 1.0
 */
class Engine
{
    /**
 * Constructor
 */ 
    public Engine ()
        {
            MAX_speed = 1f;
            this.VolumeOfFuel = 8f;
        }
        /**
 * Constructor
 * @param Max_speed maximum speed
 * @param VolumeOfFuel hold the fuel tank in liters
 */ 
    public Engine (float Max_speed,float VolumeOfFuel)
        {
            this.MAX_speed = Max_speed;
            this.VolumeOfFuel = VolumeOfFuel;
        }
        /**
 * Constructor
 * @param VolumeOfFuel hold the fuel tank in liters
 * @throws OutOfMemoryError
 */ 
    public Engine (float VolumeOfFuel) throws OutOfMemoryError
    {
        
        if (VolumeOfFuel < MaxVolumeOfFuel) this.VolumeOfFuel = VolumeOfFuel;
        else {
            System.out.print("the filling level has been exceeded!");
            throw new OutOfMemoryError("Exception message");
        }
    }
    private float VolumeOfFuel;
    private boolean RanOutFuel = false;
    private float MaxVolumeOfFuel = 9f;
    private float MAX_speed = 1f;
    private float speed = 0f;
     /**
 * Method SpeedUp controls SpeedUp
 * */
    public void SpeedUp()
    {
        if(speed>MAX_speed) speed = MAX_speed;
        else speed += 0.02f;
        if (VolumeOfFuel < 0.5f && !RanOutFuel && VolumeOfFuel != 0)
        {
            JOptionPane.showMessageDialog(null, "The fuel is fuel is running out!","Warning", JOptionPane.INFORMATION_MESSAGE);
            RanOutFuel = true;
        }
        if (RanOutFuel)
        { 
            VolumeOfFuel = 0;
        }
        else if (VolumeOfFuel > 0) VolumeOfFuel -= 0.005f;
    }
    /**
 * Method SpeedDown controls the SpeedDown
 * */
    public void SpeedDown()
    {
        if (speed < 0) speed = 0;
        else speed -= 0.005f;
        if (VolumeOfFuel < 0.5f && !RanOutFuel && VolumeOfFuel != 0)
        {
            JOptionPane.showMessageDialog(null, "The fuel is fuel is running out!","Warning", JOptionPane.INFORMATION_MESSAGE);
            RanOutFuel = true;
        }
            if (RanOutFuel)
        { 
            VolumeOfFuel = 0;
        }
        else if (VolumeOfFuel > 0) VolumeOfFuel -= 0.0005f;
    }
    /**
 * Method return the RanOutFuel  position
 * */
    public boolean getRanOutFuel() {
        return RanOutFuel;
    }
    /**
 * Method return the Speed  position
 * */
    public float getSpeed() {
        return speed;
    }
}