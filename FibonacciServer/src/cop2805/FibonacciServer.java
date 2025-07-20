package cop2805;

import java.io.*;
import java.net.*;

public class FibonacciServer {
    public static void main(String[] args) {
        final int PORT = 5000;
        System.out.println("Server started on port " + PORT);
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connected to client");

                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                String input = in.readLine();
                try {
                    int n = Integer.parseInt(input);
                    int result = fibonacci(n);
                    out.println(result);
                } catch (NumberFormatException e) {
                    out.println("Invalid input. Please enter a positive integer.");
                }

                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int fibonacci(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        
        int v1 = 0, v2 = 1, v3 = 0;
        for (int i = 2; i <= n; i++) {
            v3 = v1 + v2;
            v1 = v2;
            v2 = v3;
        }
        return v3;
    }
}
