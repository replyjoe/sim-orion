package simorion;

import javax.swing.JButton;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

/**
 * Changes normal JButtons into Circular JButtons
 * 
 * @author Joe
 */
class CircleButton extends JButton {
    
    public CircleButton(String label) {
        
        super(label);
        Dimension size = getPreferredSize();
        size.width = size.height = Math.max(size.width,size.height);
        setPreferredSize(size);
        setContentAreaFilled(false); //remove previous square button shape
    }

    //creates background colour circle
    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillOval(0, 0, getSize().width,getSize().height);
        super.paintComponent(g);
    }

    //creates black border to indicate the circle
    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        g.drawOval(0, 0, getSize().width,getSize().height);
    }

    
    // HIT BOX DETECTION 
    Shape circle;
    @Override
    public boolean contains(int x, int y) {
        circle = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
        return circle.contains(x, y);
    }
}
