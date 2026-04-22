# Simple E-Commerce System

> A Java console application simulating a real-world e-commerce platform, built using object-oriented design principles and Java packages. Created for **CS 1103 Unit 2** at the University of the People.

---

## Overview

**SimpleEcommerceSystem** demonstrates how Java packages organize and encapsulate code in a real-world scenario. Customers can browse a product catalog, manage a shopping cart, place orders, and track order status — all structured across two dedicated packages following professional OOP design.

---

## Package Structure

```
src/
│
├── Main.java                          # Entry point (outside packages)
│
├── com/ecommerce/
│   ├── Product.java                   # Product entity with validation
│   └── Customer.java                  # Customer with cart operations
│
└── com/ecommerce/orders/
    └── Order.java                     # Order management & status tracking
```

---

## Features

| Feature | Description |
|---|---|
| Product catalog | Browse available products with stock and pricing |
| Shopping cart | Add / remove products with real-time total calculation |
| Order placement | Convert cart into a tracked order |
| Order status | PENDING → CONFIRMED → SHIPPED → DELIVERED / CANCELLED |
| Input validation | All inputs validated with descriptive exception messages |
| Order history | Full order history per customer |
| Exception handling | Custom error messages for invalid operations |

---

## Sample Output

```
╔══════════════════════════════════════════════════════╗
║       SIMPLE E-COMMERCE SYSTEM  —  CS 1103 Unit 2   ║
║          Java Packages & OOP Design Demo             ║
╚══════════════════════════════════════════════════════╝

┌─────────────────────────────────────────────────────┐
│    1. PRODUCT CATALOG                                │
└─────────────────────────────────────────────────────┘
  [ID: 001] Laptop Pro 15            $ 1299.99   Stock: 10   Category: Electronics
  [ID: 002] Wireless Mouse           $   29.99   Stock: 50   Category: Accessories
  ...

┌─────────────────────────────────────────────────────┐
│    7. ORDER SUMMARIES                                │
└─────────────────────────────────────────────────────┘
  ╔══════════════════════════════════════════════════════╗
  ║  Order #1001   Date: 2026-04-22 14:30  Status: SHIPPED   ║
  ╠══════════════════════════════════════════════════════╣
  ║  Customer: Alice Johnson                             ║
  ╠══════════════════════════════════════════════════════╣
  ║  Products Ordered:                                   ║
  ║    Laptop Pro 15              $ 1299.99              ║
  ║    Mechanical Keyboard        $   89.99              ║
  ╠══════════════════════════════════════════════════════╣
  ║  ORDER TOTAL:  $1389.98                              ║
  ╚══════════════════════════════════════════════════════╝
```

---

## How to Run

### Prerequisites
- Java JDK 8 or higher

### Steps

```bash
# 1. Clone the repository
git clone https://github.com/TorunT/simple-ecommerce.git
cd simple-ecommerce/src

# 2. Compile all packages
javac com/ecommerce/Product.java
javac com/ecommerce/orders/Order.java com/ecommerce/Customer.java
javac Main.java

# 3. Run the program
java Main
```

---

## OOP Concepts Demonstrated

- **Packages** — `com.ecommerce` and `com.ecommerce.orders` for modular organization
- **Encapsulation** — `private` fields with `public` getters/setters
- **Access modifiers** — `public`, `private`, `protected` applied correctly
- **Import statements** — cross-package class usage via `import`
- **Exception handling** — `IllegalArgumentException`, `IllegalStateException`
- **Enums** — `Order.Status` for type-safe status management
- **Java Streams** — cart total calculation with `.stream().mapToDouble()`
- **ArrayList** — dynamic cart and order history management

---

## Class Diagram

```
com.ecommerce
├── Product
│   ├── productID, name, price, stock, category
│   ├── getters / setters
│   └── isAvailable()
│
└── Customer
    ├── customerID, name, email
    ├── cart: List<Product>
    ├── orderHistory: List<Order>
    ├── addToCart() / removeFromCart()
    ├── calculateTotal()
    ├── viewCart()
    └── placeOrder() → Order

com.ecommerce.orders
└── Order
    ├── orderID, customer, products, orderTotal
    ├── status: Status (enum)
    ├── updateStatus()
    ├── cancelOrder()
    └── printSummary()
```

---

## Academic Context

**Course:** CS 1103 — Programming 2
**University:** University of the People
**Unit:** 2 — Java Packages & OOP Design
**Assignment:** Simple E-Commerce System

---

## References

- Eck, D. J. (2022). *Introduction to programming using Java version 9, JavaFX edition*. https://math.hws.edu/javanotes/
- Samoylov, N. (2018). *Introduction to programming: Learn to program in Java with data structures, algorithms, and logic*. Packt Publishing.
- Packages in Java. (n.d.). Board Infinity. https://www.boardinfinity.com/blog/packages-in-java/

