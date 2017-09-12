import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class PortalFrame extends JFrame {
	
	public PortalFrame() {
		
		setTitle("E-Store System");
		setLayout(null);
		setBounds(500, 300, 230, 150);
		Container c = getContentPane();
		
		JLabel jl = new JLabel("Choose your identity",JLabel.CENTER);
		jl.setHorizontalAlignment(SwingConstants.CENTER);
		jl.setOpaque(true);
		jl.setSize(210, 50);
		c.add(jl);
		
		JButton b1 = new JButton("Admin");
		b1.setBounds(10, 60, 90, 30);
		b1.addActionListener(new pressB1());
		c.add(b1);
		
		JButton b2 = new JButton("Customer");		
		b2.setBounds(110, 60, 90, 30);		
		b2.addActionListener(new pressB2());		
		c.add(b2);
		
		JButton b3 = new JButton("I am just browsing.");
		b3.setBounds(30, 90, 160, 30);		
		b3.addActionListener(new pressB3());		
		c.add(b3);
		
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	private class pressB1 implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			dispose();
			new AdminPortal();
		}
	}
	
	private class pressB2 implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			dispose();
			new CustomerPortal();
		}
	}
	
	private class pressB3 implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			dispose();
			new CustomerFrame("0");
		}
	}
	
}