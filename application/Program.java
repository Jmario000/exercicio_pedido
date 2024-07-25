package application;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

import entities.Client;
import entities.Order;
import entities.OrderItem;
import entities.Product;
import entities.enums.OrderStatus;

public class Program {

	public static void main(String[] args) {
		DateTimeFormatter fmt2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDateTime moment = LocalDateTime.now();

		try {
			JOptionPane.showMessageDialog(null, "Enter client data:");
			String clientName = JOptionPane.showInputDialog("Name: ");
			String email = JOptionPane.showInputDialog("E-mail: ");
			LocalDate birthDate = LocalDate.parse(JOptionPane.showInputDialog("Birth date (DD/MM/YYYY): "), fmt2);
			Client client = new Client(clientName, email, birthDate);
			JOptionPane.showMessageDialog(null, "Enter order data:");
			OrderStatus status = OrderStatus.valueOf(JOptionPane.showInputDialog("Status:"));
			int n = Integer.parseInt(JOptionPane.showInputDialog("How many items to this order?"));
			Order order = new Order(moment, status, client);

			for (int i = 1; i <= n; i++) {
				JOptionPane.showMessageDialog(null, "Enter #" + i + " item data:");
				String productName = JOptionPane.showInputDialog(null, "Product name:");
				Double price = Double.parseDouble(JOptionPane.showInputDialog("Product price:"));
				int quantity = Integer.parseInt(JOptionPane.showInputDialog("Quantity:"));
				Product product = new Product(productName, price);
				OrderItem oi = new OrderItem(quantity, price, product);
				order.addItem(oi);
			}
			
			JOptionPane.showMessageDialog(null, order);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}

	}

}
