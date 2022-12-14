/**
 * LAB_04 package 
 */ 
package LAB_04;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
/**
 * Class PlaneApp responsible for Plane class possibilities demonstration 
 * 
 * @author Pavlyk Serhii
 * @version 1.0
 */
public class PlaneApp extends JFrame  {
/**
 * Constructor
 * @throws IOException
 */ 
public PlaneApp ()throws IOException{
    SetParameters();
}
/**
 * Method SetParameters - починає встановює параметри для робити додатку
 * @throws IOException
 */ 
private void SetParameters ()throws IOException
{
    setTitle("BomberPLane");
    setSize(1280,720);
    setLocationRelativeTo(null);
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());
    Plane plane = new Plane(8,1);
    add(plane);
    addWindowListener(new WindowAdapter()
    {
        @Override
        public void windowOpened(WindowEvent e) {
            plane.start();
        }
    }); 
    if (!plane.isReady())
    plane.dispose();
} 
}
