import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Cart extends JFrame {

	private String custId;
	private HashMap<String, JCheckBox> checkList = new HashMap<>();
	private HashMap<String, JTextField> fieldList = new HashMap<>();
	private HashMap<String, String> quantityList = new HashMap<>();

	public Cart(String customerId) {
		custId = customerId;
		setTitle("Cart");
		setBounds(100, 100, 800, 300);
		BorderLayout border = new BorderLayout();

		Container content = getContentPane();
		content.setLayout(border);

		JPanel north = new JPanel(new GridLayout(0, 3));
		JPanel center = new JPanel(new GridLayout(0, 3));
		JPanel south = new JPanel(new GridLayout(0, 2));

		JLabel name = new JLabel("Item name:");
		north.add(name);

		JLabel itemNumber = new JLabel("Item Number:");
		north.add(itemNumber);

		JLabel quantity = new JLabel("Quantity:");
		north.add(quantity);

		JButton placeOrder = new JButton("Place Order");
		center.add(placeOrder);
		placeOrder.setSize(50, 50);
		placeOrder.setVisible(true);
		south.add(placeOrder);
		placeOrder.addActionListener(new PlaceOrderButton());

		JButton delete = new JButton("Delete Item");
		center.add(delete);
		delete.setSize(50, 50);
		delete.setVisible(true);
		south.add(delete);
		delete.addActionListener(new DeleteButton());

		content.add(north, BorderLayout.NORTH);
		content.add(south, BorderLayout.SOUTH);

		DBConnection c = new DBConnection();
		Connection conn;

		try {
			conn = c.getDBConnection();
			Statement stat = conn.createStatement();
			ResultSet rset = stat.executeQuery(
					"select cID,name,i.rID,quantity from Cart c INNER JOIN Item i ON c.rID = i.rID where cID="
							+ customerId);

			while (rset.next()) {
				String itemName = rset.getString("name");
				String itemId = rset.getString("rID");
				String itemQuantity = rset.getString("quantity");

				JCheckBox cb1;
				cb1 = new JCheckBox(itemName);
				center.add(cb1);
				cb1.setSize(150, 50);
				cb1.setVisible(true);
				checkList.put(itemId, cb1);

				JLabel label4 = new JLabel(itemId);
				center.add(label4);
				label4.setSize(150, 50);
				label4.setVisible(true);

				JTextField jt = new JTextField(itemQuantity);
				center.add(jt);
				jt.setSize(50, 50);
				fieldList.put(itemId, jt);
				quantityList.put(itemId, itemQuantity);

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		JLabel label5 = new JLabel("");
		center.add(label5);
		JLabel label6 = new JLabel("");
		center.add(label6);

		content.add(center, BorderLayout.CENTER);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

	private class DeleteButton implements ActionListener {
		public void actionPerformed(ActionEvent actionEvent) {

			DBConnection c = new DBConnection();
			Connection conn;

			try {
				conn = c.getDBConnection();
				Statement stat = conn.createStatement();
				for (String k : checkList.keySet()) {
					if (checkList.get(k).isSelected()) {
						stat.executeQuery("DELETE FROM cart WHERE cId=" + custId + " and rId=" + k);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			dispose();
			new Cart(custId);

		}
	}

	private class PlaceOrderButton implements ActionListener {
		public void actionPerformed(ActionEvent actionEvent) {

			
			DBConnection c = new DBConnection();
			Connection conn;

			try {

				conn = c.getDBConnection();
				Statement stat = conn.createStatement();

				for (String f : fieldList.keySet()) {
					if (!fieldList.get(f).getText().equals(quantityList.get(f))) {
						stat.executeQuery("UPDATE cart SET quantity=" + fieldList.get(f).getText() + " WHERE rId=" + f);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			dispose();
			new OrderPage(custId);

		}
	}

}
