import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;

public class ItemPage extends JFrame {
	
	private JTextField jt1;
	private String itemID;
	private String customerID;
	
	public ItemPage(String customerNumber, String itemNumber) {
		customerID = customerNumber;
		itemID = itemNumber;
		setTitle("Item Information");
		setLayout(null);
		setBounds(50, 50, 1100, 600);
		
		Container c = getContentPane();
		
		JLabel jl = new JLabel("Item Number  " + String.valueOf(itemNumber));
		jl.setOpaque(true);
		jl.setBounds(10, 10, 130, 20);
		c.add(jl);
		
		DBConnection dbc = new DBConnection();
		Connection conn;
		String item_name = "", item_price = "", item_url = "", item_category = "", item_description = "";
		try {
			conn = dbc.getDBConnection();
		    Statement stat = conn.createStatement();
	        ResultSet rset = stat.executeQuery("select * from Item");
	        while (rset.next()) {
	        	if(itemNumber.equals(rset.getString("RID"))) {
	        		item_name = rset.getString("name");
	        		item_price = rset.getString("price");
	        		item_url = rset.getString("url");
	        		item_category = rset.getString("category");
	        		item_description = rset.getString("description");
	        		break;
	        	}
	        }
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		JLabel jl1 = new JLabel("Name");
		jl1.setOpaque(true);
		jl1.setBounds(10, 50, 80, 20);
		c.add(jl1);
		JLabel jl11 = new JLabel(item_name);
		jl11.setOpaque(true);
		jl11.setBounds(90, 50, 300, 20);
		c.add(jl11);
		
		JLabel jl2 = new JLabel("Price($)");
		jl2.setOpaque(true);
		jl2.setBounds(10, 80, 80, 20);
		c.add(jl2);
		JLabel jl21 = new JLabel(item_price);
		jl21.setBounds(90, 80, 100, 20);
		c.add(jl21);
		
		JLabel jl4 = new JLabel("Category");
		jl4.setOpaque(true);
		jl4.setBounds(10, 110, 80, 20);
		c.add(jl4);
		JLabel jl41 = new JLabel(item_category);
		jl41.setOpaque(true);
		jl41.setBounds(90, 110, 100, 20);
		c.add(jl41);
		
		JLabel jl5 = new JLabel("URL");
		jl5.setOpaque(true);
		jl5.setBounds(10, 140, 80, 20);
		c.add(jl5);
		JLabel jl51 = new JLabel(item_url);
		jl51.setBounds(90, 140, 800, 20);
		c.add(jl51);
		
		JLabel jl3 = new JLabel("Description");
		jl3.setOpaque(true);
		jl3.setBounds(10, 170, 80, 20);
		c.add(jl3);
		JTextArea jt3 = new JTextArea(item_description);
		jt3.setLineWrap(true);
		jt3.setWrapStyleWord(true);
		JScrollPane jsp = new JScrollPane(jt3, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.setBounds(90, 170, 800, 200);
		jt3.setEditable(false);
		c.add(jsp);
		
		jt1 = new JTextField("1");
		jt1.setBounds(500, 450, 90, 30);
		c.add(jt1);
		JButton b1 = new JButton("Add to Cart");
		b1.setBounds(600, 450, 120, 30);
		b1.addActionListener(new pressB1());
		c.add(b1);
		JButton b3 = new JButton("Cancel");
		b3.setBounds(800, 450, 90, 30);
		b3.addActionListener(new pressB3());
		c.add(b3);
		
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	private class pressB1 implements ActionListener{
		
		public void actionPerformed(ActionEvent arg0) {
		DBConnection c = new DBConnection();
		Connection conn;
		boolean flag = false;
		try
		{
			if(customerID.equals("0")) {
				JOptionPane.showMessageDialog(null, "You must login to continue.");
				
			}
			else {
			conn = c.getDBConnection();
	        Statement stat = conn.createStatement();
	        ResultSet rset = stat.executeQuery("select * from Cart");
        	while (rset.next()) {
        		if(customerID.equals(rset.getString("CID")) && itemID.equals(rset.getString("RID"))) {
	        		flag = true;
	        		int currentQuantity = Integer.parseInt(rset.getString("quantity")) + Integer.parseInt(jt1.getText());
	        		System.out.println(currentQuantity);
	        		String query = "update Cart set quantity=" + String.valueOf(currentQuantity) + " where CID=" + customerID + " and RID=" + itemID;
	        		stat.executeUpdate(query);
	        		dispose();
	        		JOptionPane.showMessageDialog(null, "Item " + itemID + " is added to cart.");
	            	break;
        		}
        	}
        	if(flag == false){
		        String query = "insert into Cart(CID, RID, quantity) values(" + customerID + ", " + itemID + ", "+ jt1.getText() +")";
	        	stat.executeUpdate(query);
	        	dispose();
	        	JOptionPane.showMessageDialog(null, "Item " + itemID + " is added to cart.");
	        	
        	}
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		}
	}
	
	private class pressB3 implements ActionListener{
		public void actionPerformed(ActionEvent arg0){
			dispose();
		}
	}
	
}
