package com.ecommerce.orders;

import com.ecommerce.Customer;
import com.ecommerce.Product;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Represents a placed order in the e-commerce system.
 * Manages order status and provides order summaries.
 */
public class Order {

    public enum Status { PENDING, CONFIRMED, SHIPPED, DELIVERED, CANCELLED }

    private int          orderID;
    private Customer     customer;
    private List<Product> products;
    private double       orderTotal;
    private Status       status;
    private String       orderDate;

    // ── Constructor ───────────────────────────────────────────
    public Order(int orderID, Customer customer, List<Product> products, double orderTotal) {
        if (customer == null)
            throw new IllegalArgumentException("Customer cannot be null.");
        if (products == null || products.isEmpty())
            throw new IllegalArgumentException("Order must contain at least one product.");

        this.orderID    = orderID;
        this.customer   = customer;
        this.products   = products;
        this.orderTotal = orderTotal;
        this.status     = Status.PENDING;
        this.orderDate  = LocalDateTime.now()
            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    // ── Getters ───────────────────────────────────────────────
    public int          getOrderID()    { return orderID;    }
    public Customer     getCustomer()   { return customer;   }
    public List<Product> getProducts()  { return products;   }
    public double       getOrderTotal() { return orderTotal; }
    public Status       getStatus()     { return status;     }
    public String       getOrderDate()  { return orderDate;  }

    // ── Status Management ─────────────────────────────────────
    public void updateStatus(Status newStatus) {
        if (this.status == Status.CANCELLED)
            throw new IllegalStateException("Cannot update a cancelled order.");
        this.status = newStatus;
        System.out.println("  Order #" + orderID + " status updated to: " + newStatus);
    }

    public void cancelOrder() {
        if (this.status == Status.DELIVERED)
            throw new IllegalStateException("Cannot cancel a delivered order.");
        this.status = Status.CANCELLED;
        System.out.println("  Order #" + orderID + " has been cancelled.");
    }

    // ── Order Summary ─────────────────────────────────────────
    public void printSummary() {
        System.out.println("  ╔══════════════════════════════════════════════════════╗");
        System.out.printf ("  ║  Order #%-5d   Date: %-15s  Status: %-10s║%n",
            orderID, orderDate, status);
        System.out.println("  ╠══════════════════════════════════════════════════════╣");
        System.out.printf ("  ║  Customer: %-41s║%n", customer.getName());
        System.out.println("  ╠══════════════════════════════════════════════════════╣");
        System.out.println("  ║  Products Ordered:                                   ║");
        for (Product p : products) {
            System.out.printf("  ║    %-25s  $%7.2f              ║%n",
                p.getName(), p.getPrice());
        }
        System.out.println("  ╠══════════════════════════════════════════════════════╣");
        System.out.printf ("  ║  ORDER TOTAL:  $%-35.2f║%n", orderTotal);
        System.out.println("  ╚══════════════════════════════════════════════════════╝");
    }
}
