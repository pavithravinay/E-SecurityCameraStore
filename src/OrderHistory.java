import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;

public class OrderHistory extends JFrame {
	
	public OrderHistory(String customerNumber) {
		setTitle("Order History");
		setLayout(null);
		setBounds(300, 100, 300, 300);
		
		Container content = getContentPane();
		content.setLayout(new BorderLayout());
		JPanel c = new JPanel();
		content.add(c, BorderLayout.CENTER);
		JPanel north = new JPanel();
		JPanel south = new JPanel();
		
		c.setLayout(new GridLayout(0, 3));
		north.setLayout(new GridLayout(0, 3));
		south.setLayout(new GridLayout(0, 3));
		
		north.add(new JLabel("#Order"));
		north.add(new JLabel("Total Price"));
		north.add(new JLabel("Status"));
		
		DBConnection dbc = new DBConnection();
		Connection conn;
		try {
			conn = dbc.getDBConnection();
		    Statement stat = conn.createStatement();
	        ResultSet rset = stat.executeQuery("select * from Orders where CID=" + customerNumber);
	        while (rset.next()) {
	        	JLabel jl1 = new JLabel(rset.getString("order_number"));
	        	JLabel jl2 = new JLabel(rset.getString("total_price"));
	        	JLabel jl3 = new JLabel(rset.getString("status"));
	        	c.add(jl1);
	        	c.add(jl2);
	        	c.add(jl3);
	        }
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		JButton b2 = new JButton("Cancel");
		b2.setSize(90, 30);
		b2.addActionListener(new pressB2());
		south.add(b2);
		content.add(south, BorderLayout.SOUTH);
		content.add(north, BorderLayout.NORTH);
		
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	private class pressB2 implements ActionListener{
		public void actionPerformed(ActionEvent arg0){
			dispose();
		}
	}
	
}
