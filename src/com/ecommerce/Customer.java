package com.ecommerce;

import com.ecommerce.orders.Order;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a customer with a shopping cart.
 * Manages cart operations and order placement.
 */
public class Customer {

    private int           customerID;
    private String        name;
    private String        email;
    private List<Product> cart;
    private List<Order>   orderHistory;

    private static int orderCounter = 1000;

    // ── Constructor ───────────────────────────────────────────
    public Customer(int customerID, String name, String email) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Customer name cannot be empty.");
        if (email == null || !email.contains("@"))
            throw new IllegalArgumentException("Invalid email address.");

        this.customerID   = customerID;
        this.name         = name.trim();
        this.email        = email.trim();
        this.cart         = new ArrayList<>();
        this.orderHistory = new ArrayList<>();
    }

    // ── Getters ───────────────────────────────────────────────
    public int         getCustomerID()   { return customerID;   }
    public String      getName()         { return name;         }
    public String      getEmail()        { return email;        }
    public List<Product> getCart()       { return cart;         }
    public List<Order>   getOrderHistory(){ return orderHistory; }

    // ── Cart Operations ───────────────────────────────────────
    public void addToCart(Product product) {
        if (product == null)
            throw new IllegalArgumentException("Product cannot be null.");
        if (!product.isAvailable())
            throw new IllegalStateException("Product '" + product.getName() + "' is out of stock.");

        cart.add(product);
        System.out.println("  + Added to cart: " + product.getName());
    }

    public void removeFromCart(int productID) {
        boolean removed = cart.removeIf(p -> p.getProductID() == productID);
        if (!removed)
            System.out.println("  ! Product ID " + productID + " not found in cart.");
        else
            System.out.println("  - Removed product ID " + productID + " from cart.");
    }

    public double calculateTotal() {
        return cart.stream().mapToDouble(Product::getPrice).sum();
    }

    public void viewCart() {
        if (cart.isEmpty()) {
            System.out.println("  Cart is empty.");
            return;
        }
        System.out.println("  ┌─ Shopping Cart ─────────────────────────────────────┐");
        cart.forEach(p -> System.out.println("  │" + p));
        System.out.printf("  └─ Total: $%.2f ──────────────────────────────────────┘%n",
            calculateTotal());
    }

    // ── Place Order ───────────────────────────────────────────
    public Order placeOrder() {
        if (cart.isEmpty())
            throw new IllegalStateException("Cannot place order with an empty cart.");

        int     orderID = ++orderCounter;
        double  total   = calculateTotal();
        Order   order   = new Order(orderID, this, new ArrayList<>(cart), total);

        orderHistory.add(order);
        cart.clear();

        System.out.println("  Order #" + orderID + " placed successfully! Total: $" +
            String.format("%.2f", total));
        return order;
    }

    // ── Display ───────────────────────────────────────────────
    @Override
    public String toString() {
        return String.format("Customer [ID: %d | Name: %-15s | Email: %s]",
            customerID, name, email);
    }
}
