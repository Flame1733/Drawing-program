import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class DrawingProgram1 extends JFrame implements MouseMotionListener,MouseListener {
    
	Point oldPoint =new Point();
	Point newPoint=new Point();
    Image image;
    int penSize=5;
    Graphics2D g2d;
	Color penColor = new Color(0,0,0);

	


	public DrawingProgram1(){
		super("Painter");
		JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.LEFT));
		toolbar.add(new Label("Drag mouse to draw"));
		this.add(toolbar,BorderLayout.SOUTH);
		setSize(800,600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JPanel drawPanel = new JPanel(){
			public void paintComponent(Graphics g){
				if(image == null){
					image = createImage(getSize().width, getSize().height); 
					g2d = (Graphics2D)image.getGraphics();
					g2d.setColor(Color.WHITE);
					g2d.fillRect(0,0,getSize().width, getSize().height);
				} 
				else{
					g.drawImage(image,0,0, null);
				}
			}
		};
		this.add(drawPanel,BorderLayout.CENTER);
		drawPanel.addMouseMotionListener(this);
		drawPanel.addMouseListener(this);
        
	}


    
	
	public static void main(String[] a){
		new DrawingProgram1();
	}

    public void mouseMoved (MouseEvent me) {
		oldPoint = me.getPoint();
	}
	public void mouseDragged (MouseEvent me) {
		g2d.setStroke(new BasicStroke(penSize,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
		g2d.setColor(penColor);
		newPoint = me.getPoint(); 
		g2d.drawLine(oldPoint.x, oldPoint.y, newPoint.x, newPoint.y);
		repaint();
		oldPoint = newPoint;
        System.out.println("Y: " + newPoint.y + " X: " + newPoint.x);
	}

	public void mouseClicked (MouseEvent me){}
	public void mousePressed (MouseEvent me){}
	public void mouseReleased (MouseEvent me){}
	public void mouseEntered (MouseEvent me){}
	public void mouseExited (MouseEvent me){}
}
