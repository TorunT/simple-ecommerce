import com.ecommerce.Customer;
import com.ecommerce.Product;
import com.ecommerce.orders.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================
 *  SimpleEcommerceSystem — Main Program
 *  CS 1103 | Unit 2 Programming Assignment
 * ============================================================
 *  Demonstrates Java packages, encapsulation, and OOP design
 *  through a functional e-commerce simulation.
 * ============================================================
 */
public class Main {

    // ── ANSI Colors ───────────────────────────────────────────
    static final String RESET  = "\u001B[0m";
    static final String BOLD   = "\u001B[1m";
    static final String CYAN   = "\u001B[36m";
    static final String GREEN  = "\u001B[32m";
    static final String YELLOW = "\u001B[33m";
    static final String BLUE   = "\u001B[34m";
    static final String RED    = "\u001B[31m";

    public static void main(String[] args) {

        printBanner();

        // ── 1. INITIALIZE PRODUCT CATALOG ─────────────────────
        section("1. PRODUCT CATALOG");
        List<Product> catalog = new ArrayList<>();
        catalog.add(new Product(1, "Laptop Pro 15",      1299.99, 10, "Electronics"));
        catalog.add(new Product(2, "Wireless Mouse",       29.99,  50, "Accessories"));
        catalog.add(new Product(3, "Mechanical Keyboard",  89.99,  30, "Accessories"));
        catalog.add(new Product(4, "4K Monitor",          399.99,  8, "Electronics"));
        catalog.add(new Product(5, "USB-C Hub",            49.99, 25, "Accessories"));
        catalog.add(new Product(6, "Noise-Cancel Headset", 179.99, 15, "Audio"));
        catalog.add(new Product(7, "Webcam HD 1080p",      69.99,  20, "Electronics"));

        System.out.println(CYAN + "  Available Products:" + RESET);
        catalog.forEach(System.out::println);

        // ── 2. CREATE CUSTOMERS ───────────────────────────────
        section("2. CUSTOMER REGISTRATION");
        Customer customer1 = new Customer(101, "Alice Johnson", "alice@email.com");
        Customer customer2 = new Customer(102, "Bob Martinez",  "bob@email.com");
        System.out.println(GREEN + "  Registered: " + RESET + customer1);
        System.out.println(GREEN + "  Registered: " + RESET + customer2);

        // ── 3. BROWSING & ADDING TO CART ─────────────────────
        section("3. SHOPPING — Alice's Cart");
        System.out.println(YELLOW + "  Alice is adding products to her cart..." + RESET);
        try {
            customer1.addToCart(catalog.get(0));  // Laptop Pro 15
            customer1.addToCart(catalog.get(1));  // Wireless Mouse
            customer1.addToCart(catalog.get(2));  // Mechanical Keyboard
        } catch (IllegalStateException e) {
            System.out.println(RED + "  [ERROR] " + e.getMessage() + RESET);
        }
        customer1.viewCart();

        // ── 4. REMOVE ITEM FROM CART ──────────────────────────
        section("4. CART MANAGEMENT");
        System.out.println(YELLOW + "  Alice removes the mouse from her cart..." + RESET);
        customer1.removeFromCart(2);
        customer1.viewCart();

        // ── 5. PLACE ORDER — CUSTOMER 1 ──────────────────────
        section("5. ORDER PLACEMENT — Alice");
        Order order1 = null;
        try {
            order1 = customer1.placeOrder();
        } catch (IllegalStateException e) {
            System.out.println(RED + "  [ERROR] " + e.getMessage() + RESET);
        }

        // ── 6. BOB'S SHOPPING SESSION ─────────────────────────
        section("6. SHOPPING — Bob's Cart");
        System.out.println(YELLOW + "  Bob is adding products to his cart..." + RESET);
        try {
            customer2.addToCart(catalog.get(3));  // 4K Monitor
            customer2.addToCart(catalog.get(5));  // Headset
            customer2.addToCart(catalog.get(6));  // Webcam
        } catch (IllegalStateException e) {
            System.out.println(RED + "  [ERROR] " + e.getMessage() + RESET);
        }
        customer2.viewCart();

        Order order2 = null;
        try {
            order2 = customer2.placeOrder();
        } catch (IllegalStateException e) {
            System.out.println(RED + "  [ERROR] " + e.getMessage() + RESET);
        }

        // ── 7. ORDER SUMMARIES ────────────────────────────────
        section("7. ORDER SUMMARIES");
        if (order1 != null) order1.printSummary();
        System.out.println();
        if (order2 != null) order2.printSummary();

        // ── 8. ORDER STATUS UPDATES ───────────────────────────
        section("8. ORDER STATUS MANAGEMENT");
        try {
            if (order1 != null) order1.updateStatus(Order.Status.CONFIRMED);
            if (order1 != null) order1.updateStatus(Order.Status.SHIPPED);
            if (order2 != null) order2.updateStatus(Order.Status.CONFIRMED);
            if (order2 != null) order2.cancelOrder();
        } catch (IllegalStateException e) {
            System.out.println(RED + "  [ERROR] " + e.getMessage() + RESET);
        }

        // ── 9. EXCEPTION HANDLING DEMO ───────────────────────
        section("9. INPUT VALIDATION & EXCEPTION HANDLING");
        System.out.println(YELLOW + "  Testing invalid inputs..." + RESET);

        try {
            new Product(99, "", 100.0, 5, "Test");
        } catch (IllegalArgumentException e) {
            System.out.println(RED + "  [CAUGHT] " + e.getMessage() + RESET);
        }

        try {
            new Product(99, "Bad Price", -50.0, 5, "Test");
        } catch (IllegalArgumentException e) {
            System.out.println(RED + "  [CAUGHT] " + e.getMessage() + RESET);
        }

        try {
            new Customer(999, "No Email", "invalidemail");
        } catch (IllegalArgumentException e) {
            System.out.println(RED + "  [CAUGHT] " + e.getMessage() + RESET);
        }

        try {
            Customer emptyCart = new Customer(200, "Empty User", "empty@email.com");
            emptyCart.placeOrder();
        } catch (IllegalStateException e) {
            System.out.println(RED + "  [CAUGHT] " + e.getMessage() + RESET);
        }

        // ── 10. ORDER HISTORY ─────────────────────────────────
        section("10. ORDER HISTORY");
        System.out.println(BLUE + "  Alice's order history:" + RESET);
        customer1.getOrderHistory().forEach(o ->
            System.out.println("    Order #" + o.getOrderID() +
                " | Total: $" + String.format("%.2f", o.getOrderTotal()) +
                " | Status: " + o.getStatus()));

        System.out.println(BLUE + "\n  Bob's order history:" + RESET);
        customer2.getOrderHistory().forEach(o ->
            System.out.println("    Order #" + o.getOrderID() +
                " | Total: $" + String.format("%.2f", o.getOrderTotal()) +
                " | Status: " + o.getStatus()));

        printFooter();
    }

    // ── Helpers ───────────────────────────────────────────────
    static void printBanner() {
        System.out.println(CYAN + BOLD);
        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║       SIMPLE E-COMMERCE SYSTEM  —  CS 1103 Unit 2   ║");
        System.out.println("║          Java Packages & OOP Design Demo             ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.println(RESET);
    }

    static void section(String title) {
        System.out.println(CYAN + BOLD +
            "\n┌─────────────────────────────────────────────────────┐");
        System.out.printf("│  %-51s│%n", "  " + title);
        System.out.println("└─────────────────────────────────────────────────────┘"
            + RESET);
    }

    static void printFooter() {
        System.out.println(CYAN +
            "\n──────────────────────────────────────────────────────");
        System.out.println("  E-Commerce System demo complete. CS1103 | UoPeople");
        System.out.println("──────────────────────────────────────────────────────"
            + RESET);
    }
}
