package com.example.CoinTrigger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInterface {

    private TradeManager tradeManager;
    private double curr_Price = 45000.0;
    private final JFrame frame;
    private JTextArea outputArea;
    private JTextField sellPriceField;
    private JTextField buyPriceField;
    private

     UserInterface() {
        frame = new JFrame("Trade Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(100, 200);


        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        JLabel buyPriceLabel = new JLabel("Trigger Buy Price");
        buyPriceField = new JTextField();
        JLabel sellPriceLabel = new JLabel("Trigger Sell Price");
        sellPriceField = new JTextField();
        JButton startButton = new JButton("Start Trading");

        inputPanel.add(buyPriceLabel);
        inputPanel.add(buyPriceField);
        inputPanel.add(sellPriceLabel);
        inputPanel.add(sellPriceField);

        inputPanel.add(startButton);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);


        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                double triggerBuyPrice=Double.parseDouble(buyPriceField.getText());
                double triggerSellPrice = Double.parseDouble(sellPriceField.getText());
                tradeManager = new TradeManager(triggerBuyPrice,triggerSellPrice);
                outputArea.append("Trading Startes..\n");
                simulateMarket();
                }catch(NumberFormatException em){
                    outputArea.append("Invaild input.please valid number.\n");
                }
            }
        });
        frame.setVisible(true);
    }

    private void simulateMarket(){
        new Thread(() -> {
            for (int i = 0; i < 10; i++) { // Simulate 10 price changes
                curr_Price += (Math.random() * 1000 - 500); // Random price fluctuation
                double price = Math.round(curr_Price * 100.0) / 100.0; // Round to 2 decimals

                SwingUtilities.invokeLater(() -> {
                    outputArea.append("Current market price: " + price + "\n");
                    tradeManager.handleMarketPrice(price);
                });

                try {
                    Thread.sleep(2000); // Wait 2 seconds between updates
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

}
