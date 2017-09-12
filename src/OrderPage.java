import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import javax.swing.*;

public class OrderPage extends JFrame {
	
	private JTextField jt1;
	private JTextField jt2;
	private JTextArea jt3;
	private String customerID;
	
	public OrderPage(String customerNumber) {
		customerID = customerNumber;
		setTitle("Order Information");
		setLayout(null);
		setBounds(300, 100, 500, 650);
		
		Container c = getContentPane();
		
		JLabel jl = new JLabel("New Order");
		jl.setOpaque(true);
		jl.setBounds(10, 10, 130, 20);
		c.add(jl);
		
		DBConnection dbc = new DBConnection();
		Connection conn;
		String customer_name = "", customer_email = "", order_total = "", order_summary = "";
		try {
			conn = dbc.getDBConnection();
		    Statement stat = conn.createStatement();
	        ResultSet rset = stat.executeQuery("select * from Customer");
	        while (rset.next()) {
	        	if(customerNumber.equals(rset.getString("CID"))) {
	        		customer_name = rset.getString("name");
	        		customer_email = rset.getString("email");
	        		break;
	        	}
	        }
	        
	        HashMap<String, Integer> items = new HashMap();
	        rset = stat.executeQuery("select * from Cart");
	        while (rset.next()) {
	        	if(customerNumber.equals(rset.getString("CID"))) {
	        		items.put(rset.getString("RID"), Integer.parseInt(rset.getString("quantity")));
	        	}
	        }
	        
	        float total = 0;
	        rset = stat.executeQuery("select * from Item");
	        while (rset.next()) {
	        	String rID = rset.getString("RID");
	        	if(items.containsKey(rID)) {
	        		String item_price = rset.getString("price");
	        		total += items.get(rID) * Float.parseFloat(item_price);
	        		order_summary += rset.getString("name") + "\tPrice:" + item_price + "\tQuantity:" + String.valueOf(items.get(rID)) + "\n";
	        	}
	        }
	        order_total = String.format("%.2f", total);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		JLabel jl1 = new JLabel("Name");
		jl1.setOpaque(true);
		jl1.setBounds(10, 50, 80, 20);
		c.add(jl1);
		JLabel jl11 = new JLabel(customer_name);
		jl11.setBounds(90, 50, 300, 20);
		c.add(jl11);
		
		JLabel jl2 = new JLabel("Email");
		jl2.setOpaque(true);
		jl2.setBounds(10, 80, 80, 20);
		c.add(jl2);
		JLabel jl21 = new JLabel(customer_email);
		jl21.setBounds(90, 80, 300, 20);
		c.add(jl21);
		
		JLabel jl3 = new JLabel("Card NO.");
		jl3.setOpaque(true);
		jl3.setBounds(10, 110, 80, 20);
		c.add(jl3);
		JTextField jt31 = new JTextField("");
		jt31.setBounds(90, 110, 300, 20);
		c.add(jt31);
		
		JLabel jl4 = new JLabel("Address");
		jl4.setOpaque(true);
		jl4.setBounds(10, 140, 80, 20);
		c.add(jl4);
		jt1 = new JTextField("");
		jt1.setBounds(90, 140, 300, 20);
		c.add(jt1);
		
		JLabel jl5 = new JLabel("Total Price");
		jl5.setOpaque(true);
		jl5.setBounds(10, 170, 80, 20);
		c.add(jl5);
		jt2 = new JTextField(order_total);
		jt2.setBounds(90, 170, 100, 20);
		c.add(jt2);
		
		JLabel jl6 = new JLabel("Summary");
		jl6.setOpaque(true);
		jl6.setBounds(10, 200, 80, 20);
		c.add(jl6);
		jt3 = new JTextArea(order_summary);
		jt3.setLineWrap(true);
		jt3.setWrapStyleWord(true);
		JScrollPane jsp = new JScrollPane(jt3, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.setBounds(90, 200, 300, 300);
		c.add(jsp);
		
		JButton b1 = new JButton("Place Order");
		b1.setBounds(100, 550, 110, 30);
		b1.addActionListener(new pressB1());
		c.add(b1);
		JButton b2 = new JButton("Cancel");
		b2.setBounds(250, 550, 100, 30);
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
	        String query = "insert into Orders(CID, total_price, status, address, summary) values(" + customerID + ", '" + jt2.getText() + "', 'Placed', '"+ jt1.getText() +"', '" + jt3.getText() + "')";
        	stat.executeUpdate(query);
        	
        	query = "delete from Cart where CID=" + customerID;
        	stat.executeUpdate(query);
        	
        	JOptionPane.showMessageDialog(null, "Order is placed.");
        	dispose();

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
