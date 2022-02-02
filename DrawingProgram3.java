import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.image.*;

public class DrawingProgram3 extends JFrame implements MouseMotionListener,MouseListener, ChangeListener{
	JPanel drawPanel = new JPanel(){
		public void paintComponent(Graphics g){
			if(image == null){
				image = createImage(getSize().width, getSize().height); 
				g2d = (Graphics2D)image.getGraphics();
				g2d.setColor(Color.WHITE);
				g2d.fillRect(0,0,getSize().width, getSize().height);
			} else{
				g.drawImage(image,0,0, null);
			}
		}
	};
	Point oldPoint =new Point();
	Point newPoint=new Point();
	Image image;
	Graphics2D g2d;
	Color penColor = new Color(0,0,0);
	JSlider penSlider = new JSlider(JSlider.HORIZONTAL,1,20,5);
	int penSize=5;

	
	public DrawingProgram3(){
        super("Painter");
		JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.LEFT));
		toolbar.add(new Label("Drag mouse to draw"));
		this.add(toolbar,BorderLayout.SOUTH);
		JButton colorButton = new JButton("Choose color");
		 /*
		colorButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
					Color startingColor = ;
					penColor = JColorChooser.showDialog(this, "Choose line color", );
				
			}
		}); */
		colorButton.setVisible(true);
            toolbar.add(colorButton);

            colorButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    penColor = JColorChooser.showDialog(null,"Choose line color",penColor);

                }
            });
		

		setSize(800,600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	
		toolbar.add(penSlider);
		penSlider.setMinorTickSpacing(1);
		penSlider.setMajorTickSpacing(5);
		penSlider.setPaintTicks(true);
		penSlider.addChangeListener(this);
		this.add(toolbar,BorderLayout.SOUTH);
		this.add(drawPanel,BorderLayout.CENTER);
		toolbar.add(colorButton,BorderLayout.EAST);
		drawPanel.addMouseMotionListener(this);
		drawPanel.addMouseListener(this);
	}
	
	public static void main(String[] a){
		new DrawingProgram3();
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

	public void stateChanged (ChangeEvent e){
		JSlider tmp=(JSlider)e.getSource();
		if (!tmp.getValueIsAdjusting()){
			penSize = (int)tmp.getValue();
		}

	}
}

