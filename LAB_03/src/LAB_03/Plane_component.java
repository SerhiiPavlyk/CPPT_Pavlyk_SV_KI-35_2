package LAB_03;

import java.awt.*;
import java.awt.geom.AffineTransform;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon; 
class Pilot
{
    public Pilot() 
    {
        this.image = new ImageIcon(getClass().getResource("PlaneModel.png")).getImage();
    }
    public static final double pilot_size = 64;
    private double pilot_x,pilot_y;
    private float angle =0;
    private final Image image;
    public void ChangeAngle(float angle)
    {
        if  (angle<0) angle = 359;
        else if(angle > 359) angle = 0;
        this.angle = angle;
        
    }
    public void setPilot_y(double pilot_y) {
        this.pilot_y = pilot_y;
    }
    public void setPilot_x(double pilot_x) {
        this.pilot_x = pilot_x;
    }
    public double getPilot_y() {
        return pilot_y;
    }
    public double getPilot_x() {
        return pilot_x;
    }
    public void BuildPilot(Graphics2D d2)
    {
        AffineTransform old = d2.getTransform();
        d2.translate(pilot_x, pilot_y);
        AffineTransform transform = new AffineTransform();
        transform.rotate(Math.toRadians(angle+90),pilot_size/2,pilot_size/2);


        d2.drawImage(image, transform, null);
        d2.setTransform(old);
    }
    public void move(double x, double y)
    {
        this.pilot_x = x;
        this.pilot_y = y;
    }
    public float getAngle() {
        return angle;
    }
    public void setAngle(float angle) {
        this.angle = angle;
    }
}
class Helm
{
    private boolean go_right;
    private boolean go_left;
    private boolean go_AngleUp;
    private boolean go_AngleDown;
    private boolean acceleration;

    public boolean getAcceleration() {
        return acceleration;
    }
    public void setAcceleration(boolean acceleration) {
        this.acceleration = acceleration;
    }
    public boolean getGo_right() {
        return go_right;
    }
    public void setGo_right(boolean go_right) {
        this.go_right = go_right;
    }
   
    public boolean getGo_left() {
        return go_left;
    }
    public void setGo_left(boolean go_left) {
        this.go_left = go_left;
    }
    public boolean getGo_AngleUp() {
        return go_AngleUp;
    }
    public void setGo_AngleUp(boolean go_AngleUp) {
        this.go_AngleUp = go_AngleUp;
    }
   
    public boolean getGo_AngleDown() {
        return go_AngleDown;
    }
    public void setGo_AngleDown(boolean go_AngleDown) {
        this.go_AngleDown = go_AngleDown;
    }


}
class Engine
{
    public Engine ()
        {
            MAX_speed = 1f;
            this.VolumeOfFuel = 8f;
        }
    public Engine (float Max_speed,float VolumeOfFuel)
        {
            this.MAX_speed = Max_speed;
            this.VolumeOfFuel = VolumeOfFuel;
        }
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
    public boolean getRanOutFuel() {
        return RanOutFuel;
    }
    public float getSpeed() {
        return speed;
    }
}