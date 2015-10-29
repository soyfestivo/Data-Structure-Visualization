import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.FileInputStream;
import java.lang.Thread;

public class Canvas extends JPanel {
	public Canvas() {
		setPreferredSize(new Dimension(800,420));
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.RED);
		g.fillRect(0, 0, getWidth(), getHeight());
	}
}