package LAB_03;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class PlaneApp extends JFrame  {
public PlaneApp ()throws IOException{
    SetParameters();
}
private void SetParameters ()throws IOException
{
    setTitle("PLane");
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
    plane.dispose();
} 
    public static void main(String[] args) throws IOException{
        PlaneApp planeApp = new PlaneApp();
        planeApp.setVisible(true);
    }
}
