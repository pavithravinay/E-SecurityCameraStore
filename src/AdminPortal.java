import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class AdminPortal extends JFrame {
	static JTextField jt;

	public AdminPortal() {

		jt = new JTextField(20);

		setTitle("Admin Login");
		setLayout(null);
		setBounds(500, 300, 230, 150);
		Container c = getContentPane();

		JLabel jl = new JLabel("Please enter your Credentials.", JLabel.CENTER);
		jl.setHorizontalAlignment(SwingConstants.CENTER);
		c.add(jl);
		jl.setOpaque(true);
		jl.setSize(210, 40);

		JButton b1 = new JButton("Login");
		c.add(b1);
		b1.setBounds(10, 60, 90, 30);
		b1.addActionListener(new PressB1());

		JButton b2 = new JButton("Cancel");
		c.add(b2);
		b2.setBounds(110, 60, 90, 30);

		b2.addActionListener(new PressB2());

		c.add(jt);
		jt.setBounds(70, 40, 70, 25);
		setVisible(true);

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	class PressB1 implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			String credential = "5";

			String id = AdminPortal.jt.getText();// read input
			System.out.println(id);
			if (id.equals(credential)) {
				dispose();
				new AdminFrame();
			} else {
				JOptionPane.showMessageDialog(null, "Incorrect credential.");
			}

		}
	}

	class PressB2 implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			dispose();
			new PortalFrame();
		}
	}
}
