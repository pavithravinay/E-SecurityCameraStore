import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class CustomerFrame extends JFrame {
	
	private JCheckBox cb1;
	private JCheckBox cb2;
	private JCheckBox cb3;
	private String customerID;
	
	public CustomerFrame(String customerNumber) {
		RetailItemSearchCriteria criteria = new RetailItemSearchCriteria(true, true, true);
		defalultConstructor(customerNumber, criteria);
	}

	public CustomerFrame(String customerNumber, RetailItemSearchCriteria criteria) {
		defalultConstructor(customerNumber, criteria);

	}

	public ImageIcon addImage(String url) {

		ImageIcon icon = null;

		try {
			ImageIcon ic = new ImageIcon(new URL(url));
			Image img = ic.getImage();
			Image bi = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
			icon = new ImageIcon(bi);
		} catch (MalformedURLException e) {
			System.out.println("No image");
			e.printStackTrace();
		}
		return icon;
	}

	private void defalultConstructor(String customerNumber, RetailItemSearchCriteria criteria) {
		customerID = customerNumber;
		setTitle("Customer Frame");
		setBounds(100, 100, 900, 1600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BorderLayout border = new BorderLayout();
		Container content = getContentPane();
		content.setLayout(border);

		JPanel north = new JPanel();

		cb1 = new JCheckBox("Accessories");
		cb1.setSelected(criteria.showAccessories);

		cb2 = new JCheckBox("Security Cameras");
		cb2.setSelected(criteria.showSecurityCameras);

		cb3 = new JCheckBox("DVRs");
		cb3.setSelected(criteria.showDvrs);

		JButton search = new JButton("Search");
		search.addActionListener(new SearchButton());
		JButton cart = new JButton("Cart");
		cart.addActionListener(new CartButton());
		JButton order = new JButton("Order History");
		order.addActionListener(new OrderButton());
		JButton login;
		if(customerNumber.equals("0")) {
			login = new JButton("Login");
			login.addActionListener(new LoginButton());
			north.add(login);
		}

		north.add(cb1);
		north.add(cb2);
		north.add(cb3);
		north.add(search);
		north.add(cart);
		north.add(order);
		content.add(north, BorderLayout.NORTH);

		JPanel center = new JPanel(new GridLayout(4, 9));
		
		ImageIcon buttonIcon;
		
		DBConnection c = new DBConnection();
		Connection conn;

		try {
			conn = c.getDBConnection();
			Statement stat = conn.createStatement();
			ResultSet rset = stat.executeQuery("select rId,name,price,url,description, category from Item");
			while (rset.next()) {
				String cat = rset.getString("Category");
				if (criteria.showAccessories && cat.equals("Accessories")) {
					buttonIcon = addImage(rset.getString("url"));
					JButton button;
					button = new JButton(rset.getString("name") + " $" + rset.getString("price"), buttonIcon);
					button.setActionCommand(rset.getString("rID"));
					center.add(button);
					button.setSize(100, 120);
					button.setVerticalTextPosition(SwingConstants.BOTTOM);
					button.setHorizontalTextPosition(SwingConstants.CENTER);
					button.addActionListener(new ItemButton());
				}

				else if (criteria.showSecurityCameras && cat.equals("Security Cameras")) {
					buttonIcon = addImage(rset.getString("url"));
					JButton button;
					button = new JButton(rset.getString("name") + " $" + rset.getString("price"), buttonIcon);
					button.setActionCommand(rset.getString("rID"));
					center.add(button);
					button.setSize(100, 120);
					button.setVerticalTextPosition(SwingConstants.BOTTOM);
					button.setHorizontalTextPosition(SwingConstants.CENTER);
					button.addActionListener(new ItemButton());
				}

				else if (criteria.showDvrs && cat.equals("DVRs")) {
					buttonIcon = addImage(rset.getString("url"));
					JButton button;
					button = new JButton(rset.getString("name") + " $" + rset.getString("price"), buttonIcon);
					button.setActionCommand(rset.getString("rID"));
					center.add(button);
					button.setSize(100, 120);
					button.setVerticalTextPosition(SwingConstants.BOTTOM);
					button.setHorizontalTextPosition(SwingConstants.CENTER);
					button.addActionListener(new ItemButton());
				} 
				else {
					continue;
				}

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		content.add(center, BorderLayout.CENTER);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}

	private class SearchButton implements ActionListener {
		public void actionPerformed(ActionEvent actionEvent) {
			
			RetailItemSearchCriteria criteria = new RetailItemSearchCriteria(false, false, false);

			if (cb1.isSelected()) {
				criteria.showAccessories = true;
			}
			if (cb2.isSelected()) {
				criteria.showSecurityCameras = true;
			}
			if (cb3.isSelected()) {
				criteria.showDvrs = true;
			}
			dispose();
			new CustomerFrame(customerID, criteria);
			

		}
	}
	
	private class CartButton implements ActionListener {
		
		public void actionPerformed(ActionEvent arg0) {
			if(customerID.equals("0")) {
				JOptionPane.showMessageDialog(null, "You must login to continue.");
				
			}
			else {
				new Cart(customerID);
			}
		}
	}
	
	private class ItemButton implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			new ItemPage(customerID, arg0.getActionCommand());
		}
	}

	private class OrderButton implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if(customerID.equals("0")) {
				JOptionPane.showMessageDialog(null, "You must login to continue.");
				
			}
			else {
				new OrderHistory(customerID);
			}
		}
	}
	
	private class LoginButton implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			dispose();
			new CustomerPortal();
		}
	}
	
}