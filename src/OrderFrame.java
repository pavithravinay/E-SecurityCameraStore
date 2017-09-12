import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;

public class OrderFrame extends JFrame {
	
	private JTextField jt1;
	private String orderID;
	
	public OrderFrame(String orderNumber) {
		orderID = orderNumber;
		setTitle("Order Information");
		setLayout(null);
		setBounds(300, 100, 500, 650);
		
		Container c = getContentPane();
		
		JLabel jl = new JLabel("Order Number " + String.valueOf(orderNumber));
		jl.setOpaque(true);
		jl.setBounds(10, 10, 130, 20);
		c.add(jl);
		
		DBConnection dbc = new DBConnection();
		Connection conn;
		String order_cID = "", order_total = "", order_status = "", order_address = "", order_summary = "";
		String customer_name = "", customer_email = "";
		try {
			conn = dbc.getDBConnection();
		    Statement stat = conn.createStatement();
	        ResultSet rset = stat.executeQuery("select * from Orders");
	        while (rset.next()) {
	        	if(orderNumber.equals(rset.getString("order_number"))) {
	        		order_cID = rset.getString("CID");
	        		order_total = rset.getString("total_price");
	        		order_status = rset.getString("status");
	        		order_address = rset.getString("address");
	        		order_summary = rset.getString("summary");
	        		break;
	        	}
	        }
	        
	        rset = stat.executeQuery("select * from Customer");
	        while (rset.next()) {
	        	if(order_cID.equals(rset.getString("CID"))) {
	        		customer_name = rset.getString("name");
	        		customer_email = rset.getString("email");
	        		break;
	        	}
	        }
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		JLabel jl1 = new JLabel("Status");
		jl1.setOpaque(true);
		jl1.setBounds(10, 50, 80, 20);
		c.add(jl1);
		jt1 = new JTextField(order_status);
		jt1.setBounds(90, 50, 300, 20);
		c.add(jt1);
		
		JLabel jl2 = new JLabel("Customer");
		jl2.setOpaque(true);
		jl2.setBounds(10, 80, 80, 20);
		c.add(jl2);
		JLabel jl21 = new JLabel(customer_name);
		jl21.setBounds(90, 80, 300, 20);
		c.add(jl21);
		
		JLabel jl3 = new JLabel("Email");
		jl3.setOpaque(true);
		jl3.setBounds(10, 110, 80, 20);
		c.add(jl3);
		JLabel jl31 = new JLabel(customer_email);
		jl31.setBounds(90, 110, 300, 20);
		c.add(jl31);
		
		JLabel jl4 = new JLabel("Total($)");
		jl4.setOpaque(true);
		jl4.setBounds(10, 140, 80, 20);
		c.add(jl4);
		JLabel jl41 = new JLabel(order_total);
		jl41.setBounds(90, 140, 100, 20);
		c.add(jl41);
		
		JLabel jl5 = new JLabel("Address");
		jl5.setOpaque(true);
		jl5.setBounds(10, 170, 80, 20);
		c.add(jl5);
		JLabel jl51 = new JLabel(order_address);
		jl51.setBounds(90, 170, 300, 20);
		c.add(jl51);
		
		JLabel jl6 = new JLabel("Summary");
		jl6.setOpaque(true);
		jl6.setBounds(10, 200, 80, 20);
		c.add(jl6);
		JTextArea jt3 = new JTextArea(order_summary);
		jt3.setLineWrap(true);
		jt3.setWrapStyleWord(true);
		JScrollPane jsp = new JScrollPane(jt3, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.setBounds(90, 200, 300, 300);
		jt3.setEditable(false);
		c.add(jsp);
		
		JButton b1 = new JButton("Submit");
		b1.setBounds(100, 550, 90, 30);
		b1.addActionListener(new pressB1());
		c.add(b1);
		JButton b2 = new JButton("Cancel");
		b2.setBounds(200, 550, 90, 30);
		b2.addActionListener(new pressB2());
		c.add(b2);
		
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	private class pressB1 implements ActionListener{
		DBConnection c = new DBConnection();
		Connection conn;
		boolean flag = false;
		public void actionPerformed(ActionEvent arg0) {
		try
		{
			conn = c.getDBConnection();
	        Statement stat = conn.createStatement();
        	ResultSet rset = stat.executeQuery("select * from Orders");
        	while (rset.next()) {
	        	if(orderID.equals(rset.getString("order_number"))) {
	        		flag = true;
	        		String query = "update Orders set status='" + jt1.getText() + "' where order_number=" + orderID;
	        		stat.executeUpdate(query);
	        		JOptionPane.showMessageDialog(null, "The status of order " + orderID + " is updated.");
	        		dispose();
	            	break;
        		}
        		
	        }
        	if(flag == false) {
    			JOptionPane.showMessageDialog(null, "Order " + orderID + " not found. Refresh item frame.");
    			dispose();
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
		}
	}
	
}
