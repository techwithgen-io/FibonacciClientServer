package cop2805;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;

public class FibonacciClient {
    private JFrame frame;
    private JTextField inputField;
    private JLabel resultLabel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FibonacciClient().createGUI());
    }

    private void createGUI() {
        frame = new JFrame("Fibonacci Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 180);
        frame.setLayout(new GridLayout(4, 1));

        JLabel promptLabel = new JLabel("Enter a positive number:", SwingConstants.CENTER);
        inputField = new JTextField();
        JButton sendButton = new JButton("Calculate Fibonacci");
        resultLabel = new JLabel("Result will appear here", SwingConstants.CENTER);

        sendButton.addActionListener(e -> handleSend());

        frame.add(promptLabel);
        frame.add(inputField);
        frame.add(sendButton);
        frame.add(resultLabel);
        frame.setLocationRelativeTo(null); 
        frame.setVisible(true);
    }

    private void handleSend() {
        String input = inputField.getText().trim();
        int number;

        try {
            number = Integer.parseInt(input);
            if (number < 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            resultLabel.setText("Please enter a valid positive integer.");
            return;
        }

        resultLabel.setText("Calculating...");

        // background thread
        new SwingWorker<String, Void>() {
            @Override
            protected String doInBackground() {
                try (
                    Socket socket = new Socket("localhost", 5000);
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
                ) {
                    out.println(number);
                    return in.readLine();
                } catch (IOException e) {
                    return "Error: Could not connect to server.";
                }
            }

            @Override
            protected void done() {
                try {
                    String response = get();
                    resultLabel.setText("Fibonacci(" + number + ") = " + response);
                } catch (Exception e) {
                    resultLabel.setText("Error occurred.");
                }
            }
        }.execute();
    }
}
