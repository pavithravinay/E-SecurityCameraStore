import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

public class CustomerPortal extends JFrame {
	private JTextField jt1;
	private JTextField jt2;
	private JTextField jt3;
	private JTextField jt4;
	private JTextField jt5;
	private JTextField jt6;
	
	public CustomerPortal() {
		setTitle("Customer Portal");
		setLayout(null);
		setBounds(500, 300, 500, 300);
		Container c = getContentPane();
		
		JLabel jl_login = new JLabel("Login Here");
		jl_login.setOpaque(true);
		jl_login.setBounds(10, 10, 80, 20);
		c.add(jl_login);
		
		JLabel jl1 = new JLabel("Username");
		jl1.setOpaque(true);
		jl1.setBounds(10, 50, 80, 20);
		c.add(jl1);
		jt1 = new JTextField("Email address");
		jt1.setBounds(90, 50, 100, 20);
		c.add(jt1);
		
		JLabel jl2 = new JLabel("Password");
		jl2.setOpaque(true);
		jl2.setBounds(10, 80, 80, 20);
		c.add(jl2);
		jt2 = new JTextField("");
		jt2.setBounds(90, 80, 100, 20);
		c.add(jt2);
		
		JButton b1 = new JButton("Login");
		b1.setBounds(10, 120, 90, 30);
		b1.addActionListener(new pressB1());
		c.add(b1);
		JButton b2 = new JButton("Cancel");
		b2.setBounds(110, 120, 90, 30);
		b2.addActionListener(new pressB2());
		c.add(b2);
		
		JLabel jl_register = new JLabel("Not a customer? Register here");
		jl_register.setOpaque(true);
		jl_register.setBounds(250, 10, 200, 20);
		c.add(jl_register);
		
		JLabel jl3 = new JLabel("Your Name");
		jl3.setOpaque(true);
		jl3.setBounds(250, 50, 80, 20);
		c.add(jl3);
		jt3 = new JTextField("");
		jt3.setBounds(380, 50, 100, 20);
		c.add(jt3);
		
		JLabel jl4 = new JLabel("Email Address");
		jl4.setOpaque(true);
		jl4.setBounds(250, 80, 120, 20);
		c.add(jl4);
		jt4 = new JTextField("");
		jt4.setBounds(380, 80, 100, 20);
		c.add(jt4);
		
		JLabel jl5 = new JLabel("New Password");
		jl5.setOpaque(true);
		jl5.setBounds(250, 110, 120, 20);
		c.add(jl5);
		jt5 = new JTextField("");
		jt5.setBounds(380, 110, 100, 20);
		c.add(jt5);
		
		JLabel jl6 = new JLabel("Password Again");
		jl6.setOpaque(true);
		jl6.setBounds(250, 140, 120, 20);
		c.add(jl6);
		jt6 = new JTextField("");
		jt6.setBounds(380, 140, 100, 20);
		c.add(jt6);
		
		JButton b3 = new JButton("Register");
		b3.setBounds(250, 180, 90, 30);
		b3.addActionListener(new pressB3());
		c.add(b3);
		JButton b4 = new JButton("Cancel");
		b4.setBounds(350, 180, 90, 30);
		b4.addActionListener(new pressB2());
		c.add(b4);
		
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	private class pressB1 implements ActionListener{
		
		DBConnection c = new DBConnection();
		Connection conn;
		boolean flag = false;
		public void actionPerformed(ActionEvent arg0){
		try {
			String id = jt1.getText();
			String pw = jt2.getText();
			conn = c.getDBConnection();
		    Statement stat = conn.createStatement();
	        ResultSet rset = stat.executeQuery("select * from Customer");
	        while (rset.next()) {
	        	if(id.equals(rset.getString("email"))) {
	        		flag = true;
	        		if(pw.equals(rset.getString("password"))) {
	        			dispose();
		            	new CustomerFrame(rset.getString("CID"));
		            	
		            	break;
	        		}
	        		else {
	        			JOptionPane.showMessageDialog(null, "Incorrect password.");
	        		}
	            }
	        }
            if(flag == false) {
            	JOptionPane.showMessageDialog(null, "Email is not registered.");
            }
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		}
	}
	
	private class pressB2 implements ActionListener{
		public void actionPerformed(ActionEvent arg0){
			dispose();
			new PortalFrame();
		}
	}
	
	private class pressB3 implements ActionListener{
		
		DBConnection c = new DBConnection();
		Connection conn;
		boolean flag = false;
		public void actionPerformed(ActionEvent arg0) {
		try
		{
			String name = jt3.getText();
			String email = jt4.getText();
			String pw1 = jt5.getText();
			String pw2 = jt6.getText();
			
			if(!pw1.equals(pw2)) {
				JOptionPane.showMessageDialog(null, "Passwords do not match.");
			}
			else {
				conn = c.getDBConnection();
		        Statement stat = conn.createStatement();
		        ResultSet rset = stat.executeQuery("select * from Customer");
		        while (rset.next()) {
		            if(email.equals(rset.getString("email"))) {
		            	flag = true;
		            	break;
		            }
		         }
	            if(flag == true) {
	            	JOptionPane.showMessageDialog(null, "Email already registered.");
	            }
	            else {
	            	String query = "insert into Customer(name, email, password) values('" + name + "', '" + email +"', '" + pw1 + "')";
	            	stat.executeUpdate(query);
	            	JOptionPane.showMessageDialog(null, "Registration is successful.");
	            }
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		}
	}
}
