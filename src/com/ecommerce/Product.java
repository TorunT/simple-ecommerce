package com.ecommerce;

/**
 * Represents a product available for purchase in the store.
 * Encapsulates product details with proper getters and setters.
 */
public class Product {

    private int    productID;
    private String name;
    private double price;
    private int    stock;
    private String category;

    // ── Constructor ───────────────────────────────────────────
    public Product(int productID, String name, double price, int stock, String category) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Product name cannot be empty.");
        if (price < 0)
            throw new IllegalArgumentException("Price cannot be negative.");
        if (stock < 0)
            throw new IllegalArgumentException("Stock cannot be negative.");

        this.productID = productID;
        this.name      = name.trim();
        this.price     = price;
        this.stock     = stock;
        this.category  = category;
    }

    // ── Getters ───────────────────────────────────────────────
    public int    getProductID() { return productID; }
    public String getName()      { return name;      }
    public double getPrice()     { return price;     }
    public int    getStock()     { return stock;     }
    public String getCategory()  { return category;  }

    // ── Setters ───────────────────────────────────────────────
    public void setPrice(double price) {
        if (price < 0) throw new IllegalArgumentException("Price cannot be negative.");
        this.price = price;
    }

    public void setStock(int stock) {
        if (stock < 0) throw new IllegalArgumentException("Stock cannot be negative.");
        this.stock = stock;
    }

    public boolean isAvailable() {
        return stock > 0;
    }

    // ── Display ───────────────────────────────────────────────
    @Override
    public String toString() {
        return String.format("  [ID: %03d] %-25s $%7.2f   Stock: %d   Category: %s",
            productID, name, price, stock, category);
    }
}
