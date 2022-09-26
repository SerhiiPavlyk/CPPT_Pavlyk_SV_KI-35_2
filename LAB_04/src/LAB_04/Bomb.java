/**
 * LAB_04 package 
 */ 
package LAB_04;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.AffineTransform;
/**
 * abstract interface Bomb_color declaration
 * @param color - set Bomb color
*/ 
abstract interface Bomb_color
{
     Color color = new Color(4, 49, 107);
}
/**
 * interface Bomb_color declaration extends Bomb_color
 * update - update sth
 * draw - draw sth by Graphics2D
*/ 
interface Bomb_things extends Bomb_color
{
     void update();
     void draw( Graphics2D d2);
}
/**
 * Class Bomb implements interface Bomb_things realize Bomb
 * @author Pavlyk Serhii
 * @version 1.0
 */
public class Bomb implements Bomb_things  {
    private double bomb_x;
    private double bomb_y;
    private final Shape shape;
    private final float bomb_angele;
    private double size;
    private float speed = 1f;
/**
 * Constructor
 * @param x X coordinate of Bomb
 * @param y Y coordinate of Bomb
 * @param angele Angle of Bomb
 * @param size sizr of Bomb
 * @param speed fall speed of Bomb
 */
    public Bomb(double x, double y, float angele,double size,float speed)
    {
        x += Pilot.pilot_size /2 - size/2;
        y += Pilot.pilot_size /2 - size/2;
        this.bomb_x = x;
        this.bomb_y = y;
        this.bomb_angele = angele;
        this.size = size;
        this.shape = new Ellipse2D.Double(0,0,size,size);
        this.speed = speed;
    }
    /*
     * Method update() update position of Bomb
     * Realize method from interfase Bomb_things
     */
    @Override
    public void update() {
        bomb_x += Math.cos(Math.toRadians(bomb_angele))*speed;
        bomb_y += Math.sin(Math.toRadians(bomb_angele))*speed;
    }
    /*
     * Method draw() draw Bomb by Graphics2D
     * Realize method from interfase Bomb_things
     */
    @Override
    public void draw(Graphics2D d2) {
        AffineTransform oldTransform = d2.getTransform();
        d2.setColor(color);
        d2.translate(bomb_x,bomb_y);
        d2.fill(shape);
        d2.setTransform(oldTransform);
    }
    /**
     * Method return the position of Bomb X
     */
    public double GetX()
    {
        return bomb_x;
    }
    /**
     * Method return the position of Bomb Y
     */
    public double GetY()
    {
        return bomb_y;
    }
    /**
     * Method return the size of Bomb
     */
    public double GetSize()
    {
        return size;
    }
    /**
     * Method Cheeck checks if the bomb has gone off the screen  
     */
    public boolean Cheeck(int wight, int height)
    {
        if(bomb_x <= - size|| bomb_y <= - size || bomb_x > wight || bomb_y >height)
        return false;
        else return true;
    }
}
