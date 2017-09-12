import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;

public class ItemFrame extends JFrame {
	
	private JTextField jt1;
	private JTextField jt2;
	private JTextArea jt3;
	private JTextField jt4;
	private String category = "";
	private String itemID;
	
	public ItemFrame() {
		setTitle("Item Information");
		setLayout(null);
		setBounds(50, 50, 1100, 600);
		
		Container c = getContentPane();
		
		JLabel jl = new JLabel("New Item");
		jl.setOpaque(true);
		jl.setBounds(10, 10, 130, 20);
		c.add(jl);
		
		JLabel jl1 = new JLabel("Name");
		jl1.setOpaque(true);
		jl1.setBounds(10, 50, 80, 20);
		c.add(jl1);
		jt1 = new JTextField("");
		jt1.setBounds(90, 50, 600, 20);
		c.add(jt1);
		
		JLabel jl2 = new JLabel("Price($)");
		jl2.setOpaque(true);
		jl2.setBounds(10, 80, 80, 20);
		c.add(jl2);
		jt2 = new JTextField("");
		jt2.setBounds(90, 80, 100, 20);
		c.add(jt2);
		
		JLabel jl4 = new JLabel("Category");
		jl4.setOpaque(true);
		jl4.setBounds(10, 110, 80, 20);
		c.add(jl4);
		JLabel jl5 = new JLabel("Accessories");
		jl5.setOpaque(true);
		jl5.setBounds(90, 110, 80, 20);
		c.add(jl5);
		JLabel jl6 = new JLabel("Security Cameras");
		jl6.setOpaque(true);
		jl6.setBounds(90, 140, 110, 20);
		c.add(jl6);
		JLabel jl7 = new JLabel("DVRs");
		jl7.setOpaque(true);
		jl7.setBounds(90, 170, 80, 20);
		c.add(jl7);
		JRadioButton brackets = new JRadioButton("Bracket");
		brackets.setActionCommand("Accessories");
		brackets.setBounds(200, 110, 80, 20);
		c.add(brackets);
		brackets.addActionListener(new pressRadioButton());
		JRadioButton cables = new JRadioButton("Cable");
		cables.setActionCommand("Accessories");
		cables.setBounds(280, 110, 70, 20);
		c.add(cables);
		cables.addActionListener(new pressRadioButton());
		JRadioButton connectors = new JRadioButton("Connector");
		connectors.setActionCommand("Accessories");
		connectors.setBounds(350, 110, 100, 20);
		c.add(connectors);
		connectors.addActionListener(new pressRadioButton());
		JRadioButton housings = new JRadioButton("Housing");
		housings.setActionCommand("Accessories");
		housings.setBounds(450, 110, 90, 20);
		c.add(housings);
		housings.addActionListener(new pressRadioButton());
		JRadioButton infrared_lightings = new JRadioButton("Infrared Lighting");
		infrared_lightings.setActionCommand("Accessories");
		infrared_lightings.setBounds(540, 110, 140, 20);
		c.add(infrared_lightings);
		infrared_lightings.addActionListener(new pressRadioButton());
		JRadioButton lenses = new JRadioButton("Lense");
		lenses.setActionCommand("Accessories");
		lenses.setBounds(680, 110, 70, 20);
		c.add(lenses);
		lenses.addActionListener(new pressRadioButton());
		JRadioButton microphones = new JRadioButton("Microphone");
		microphones.setActionCommand("Accessories");
		microphones.setBounds(750, 110, 110, 20);
		c.add(microphones);
		microphones.addActionListener(new pressRadioButton());
		JRadioButton monitors = new JRadioButton("Monitor");
		monitors.setActionCommand("Accessories");
		monitors.setBounds(860, 110, 90, 20);
		c.add(monitors);
		monitors.addActionListener(new pressRadioButton());
		JRadioButton power_supplies = new JRadioButton("Power Supply");
		power_supplies.setActionCommand("Accessories");
		power_supplies.setBounds(950, 110, 140, 20);
		c.add(power_supplies);
		power_supplies.addActionListener(new pressRadioButton());
		JRadioButton ipCameras = new JRadioButton("IP Surveillance Camera");
		ipCameras.setActionCommand("Security Cameras");
		ipCameras.setBounds(200, 140, 180, 20);
		c.add(ipCameras);
		ipCameras.addActionListener(new pressRadioButton());
		JRadioButton digitalCameras = new JRadioButton("Digital Surveillance Camera");
		digitalCameras.setActionCommand("Security Cameras");
		digitalCameras.setBounds(380, 140, 210, 20);
		c.add(digitalCameras);
		digitalCameras.addActionListener(new pressRadioButton());
		JRadioButton analogCameras = new JRadioButton("Analog Security Camera");
		analogCameras.setActionCommand("Security Cameras");
		analogCameras.setBounds(590, 140, 190, 20);
		c.add(analogCameras);
		analogCameras.addActionListener(new pressRadioButton());
		JRadioButton analogDVRs = new JRadioButton("Analog DVR");
		analogDVRs.setActionCommand("DVRs");
		analogDVRs.setBounds(200, 170, 110, 20);
		c.add(analogDVRs);
		analogDVRs.addActionListener(new pressRadioButton());
		JRadioButton digitalDVRs = new JRadioButton("Digital DVR");
		digitalDVRs.setActionCommand("DVRs");
		digitalDVRs.setBounds(310, 170, 110, 20);
		c.add(digitalDVRs);
		digitalDVRs.addActionListener(new pressRadioButton());
		JRadioButton hybridDVRs = new JRadioButton("Hybrid DVR");
		hybridDVRs.setActionCommand("DVRs");
		hybridDVRs.setBounds(420, 170, 110, 20);
		c.add(hybridDVRs);
		hybridDVRs.addActionListener(new pressRadioButton());
		ButtonGroup group = new ButtonGroup();
		group.add(brackets);
		group.add(cables);
		group.add(connectors);
		group.add(housings);
		group.add(infrared_lightings);
		group.add(lenses);
		group.add(microphones);
		group.add(monitors);
		group.add(power_supplies);
		group.add(ipCameras);
		group.add(digitalCameras);
		group.add(analogCameras);
		group.add(analogDVRs);
		group.add(digitalDVRs);
		group.add(hybridDVRs);
		
		JLabel jl8 = new JLabel("URL");
		jl8.setOpaque(true);
		jl8.setBounds(10, 200, 80, 20);
		c.add(jl8);
		jt4 = new JTextField("");
		jt4.setBounds(90, 200, 800, 20);
		c.add(jt4);
		
		JLabel jl3 = new JLabel("Description");
		jl3.setOpaque(true);
		jl3.setBounds(10, 230, 80, 20);
		c.add(jl3);
		jt3 = new JTextArea();
		jt3.setLineWrap(true);
		jt3.setWrapStyleWord(true);
		JScrollPane jsp = new JScrollPane(jt3, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.setBounds(90, 230, 800, 200);
		c.add(jsp);
		
		JButton b1 = new JButton("Submit");
		b1.setBounds(600, 450, 90, 30);
		b1.addActionListener(new pressB10());
		c.add(b1);
		JButton b3 = new JButton("Cancel");
		b3.setBounds(800, 450, 90, 30);
		b3.addActionListener(new pressB3());
		c.add(b3);
		
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	public ItemFrame(String itemNumber) {
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
		jt1 = new JTextField(item_name);
		jt1.setBounds(90, 50, 600, 20);
		c.add(jt1);
		
		JLabel jl2 = new JLabel("Price($)");
		jl2.setOpaque(true);
		jl2.setBounds(10, 80, 80, 20);
		c.add(jl2);
		jt2 = new JTextField(item_price);
		jt2.setBounds(90, 80, 100, 20);
		c.add(jt2);
		
		JLabel jl4 = new JLabel("Category");
		jl4.setOpaque(true);
		jl4.setBounds(10, 110, 80, 20);
		c.add(jl4);
		JRadioButton jrb1 = new JRadioButton("Accessories");
		jrb1.setActionCommand("Accessories");
		jrb1.setBounds(90, 110, 110, 20);
		c.add(jrb1);
		jrb1.addActionListener(new pressRadioButton());
		JRadioButton jrb2 = new JRadioButton("Security Cameras");
		jrb2.setActionCommand("Security Cameras");
		jrb2.setBounds(200, 110, 140, 20);
		c.add(jrb2);
		jrb2.addActionListener(new pressRadioButton());
		JRadioButton jrb3 = new JRadioButton("DVRs");
		jrb3.setActionCommand("DVRs");
		jrb3.setBounds(340, 110, 80, 20);
		c.add(jrb3);
		jrb3.addActionListener(new pressRadioButton());
		
		ButtonGroup group = new ButtonGroup();
		group.add(jrb1);
		group.add(jrb2);
		group.add(jrb3);
		
		if(item_category.equals("Accessories")) {
			jrb1.setSelected(true);
			category = "Accessories";
		}
		if(item_category.equals("Security Cameras")) {
			jrb2.setSelected(true);
			category = "Security Cameras";
		}
		if(item_category.equals("DVRs")) {
			jrb3.setSelected(true);
			category = "DVRs";
		}
		
		
		JLabel jl5 = new JLabel("URL");
		jl5.setOpaque(true);
		jl5.setBounds(10, 140, 80, 20);
		c.add(jl5);
		jt4 = new JTextField(item_url);
		jt4.setBounds(90, 140, 800, 20);
		c.add(jt4);
		
		JLabel jl3 = new JLabel("Description");
		jl3.setOpaque(true);
		jl3.setBounds(10, 170, 80, 20);
		c.add(jl3);
		jt3 = new JTextArea(item_description);
		jt3.setLineWrap(true);
		jt3.setWrapStyleWord(true);
		JScrollPane jsp = new JScrollPane(jt3, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.setBounds(90, 170, 800, 200);
		c.add(jsp);
		
		JButton b1 = new JButton("Submit");
		b1.setBounds(600, 450, 90, 30);
		b1.addActionListener(new pressB11());
		c.add(b1);
		JButton b2 = new JButton("Delete");
		b2.setBounds(700, 450, 90, 30);
		b2.addActionListener(new pressB2());
		c.add(b2);
		JButton b3 = new JButton("Cancel");
		b3.setBounds(800, 450, 90, 30);
		b3.addActionListener(new pressB3());
		c.add(b3);
		
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	private class pressB10 implements ActionListener{
		DBConnection c = new DBConnection();
		Connection conn;
		public void actionPerformed(ActionEvent arg0) {
		try
		{
			String name = jt1.getText();
			String price = jt2.getText();
			String url = jt4.getText();
			String description = jt3.getText();
			
			if(name.equals("") || price.equals("") || url.equals("") || description.equals("") || category.equals("")) {
				JOptionPane.showMessageDialog(null, "All fields need to be filled.");
			}
			else {
				conn = c.getDBConnection();
		        Statement stat = conn.createStatement();
		        String query = "insert into Item(name, price, url, description, category) values('" + name + "', " + price +", '" + url + "', '" + description + "', '" + category + "')";
            	stat.executeUpdate(query);
            	JOptionPane.showMessageDialog(null, "One new item " + name + " is added.");
            	dispose();
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		}
	}
	
	private class pressB11 implements ActionListener{
		DBConnection c = new DBConnection();
		Connection conn;
		boolean flag = false;
		public void actionPerformed(ActionEvent arg0) {
		try
		{
			String name = jt1.getText();
			String price = jt2.getText();
			String url = jt4.getText();
			String description = jt3.getText();
			
			if(name.equals("") || price.equals("") || url.equals("") || description.equals("") || category.equals("")) {
				JOptionPane.showMessageDialog(null, "All fields need to be filled.");
			}
			else {
				conn = c.getDBConnection();
		        Statement stat = conn.createStatement();
            	ResultSet rset = stat.executeQuery("select * from Item");
            	while (rset.next()) {
    	        	if(itemID.equals(rset.getString("RID"))) {
    	        		flag = true;
    	        		String query = "update Item set name='" + name + "', price=" + price + ", url='" + url + "', description='" + description + "', category='" + category + "' where RID=" + itemID;
    	        		stat.executeUpdate(query);
    	        		JOptionPane.showMessageDialog(null, "Item " + itemID + " is updated. Refresh item frame.");
    	        		dispose();
		            	break;
	        		}
	        		
    	        }
            	if(flag == false) {
        			JOptionPane.showMessageDialog(null, "Item " + itemID + " not found. Refresh item frame.");
        			dispose();
        		}
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		}
	}
	
	private class pressRadioButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
		    category = e.getActionCommand();
		}
	}
	
	private class pressB2 implements ActionListener{
		DBConnection c = new DBConnection();
		Connection conn;
		public void actionPerformed(ActionEvent arg0) {
		try
		{
			conn = c.getDBConnection();
	        Statement stat = conn.createStatement();
	        String query = "delete from Item where RID=" + itemID;
        	stat.executeUpdate(query);
        	dispose();
        	JOptionPane.showMessageDialog(null, "Item " + itemID + " is deleted. Refresh item frame.");
        	
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
