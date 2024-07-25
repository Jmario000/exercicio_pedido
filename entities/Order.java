package entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

import entities.enums.OrderStatus;

public class Order {
	DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	DateTimeFormatter fmt2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private LocalDateTime moment;
	private OrderStatus status;

	private Client client;
	private List<OrderItem> item = new LinkedList<>();

	public Order() {
	}

	public Order(LocalDateTime moment, OrderStatus status, Client client) {
		this.moment = moment;
		this.status = status;
		this.client = client;
	}

	public LocalDateTime getMoment() {
		return moment;
	}

	public void setMoment(LocalDateTime moment) {
		this.moment = moment;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<OrderItem> getItem() {
		return item;
	}

	public void addItem(OrderItem item) {
		this.item.add(item);
	}

	public void removeItem(OrderItem item) {
		this.item.remove(item);
	}

	public Double total() {
		double total = 0.0;
		for (OrderItem o : item) {
			total += o.subTotal();
		}
		return total;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("ORDER SUMARY:\n").append("Order moment: ").append(fmt.format(getMoment())).append("\n")
				.append("Order Status: ").append(getStatus()).append("\n").append("Client: ").append(client.getName())
				.append(" (" + client.getBirthDate().format(fmt2) + ")").append(" - " + client.getEmail()).append("\n")
				.append("Order items:\n");
		for (OrderItem o : item) {
			sb.append(o.getProduct().getName() + ", $").append(String.format("%.2f", o.getProduct().getPrice()) + ", ")
					.append("Quantity: " + o.getQuantity()).append(", ").append("Subtotal: $")
					.append(String.format("%.2f", o.subTotal())).append("\n");
		}
		sb.append("Total price: $" + String.format("%.2f", total()));
		return sb.toString();
	}
}
