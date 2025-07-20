# Java Fibonacci Client-Server Application

This project demonstrates a basic Java client-server model using sockets. The server computes Fibonacci numbers and the client provides a GUI to request them.

---

## Projects Overview

### Server (`/FibonacciServer`)
- Listens on port `5000`
- Accepts a number from the client
- Computes the Fibonacci number using dynamic programming
- Sends the result back to the client

### Client (`/FibonacciClient`)
- Java Swing GUI
- User enters a number
- Connects to the server via socket
- Displays the Fibonacci number returned from the server
- Handles input errors gracefully

---

## Requirements

- Java 8+
- Any IDE (NetBeans, IntelliJ, Eclipse) or command line

---

## ▶How to Run

### 1. Start the Server
```bash
cd FibonacciServer
javac FibonacciServer.java
java FibonacciServer
