package com.example.CoinTrigger;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter trigger buy price: ");
        double triggerBuyPrice = scanner.nextDouble();

        System.out.print("Enter trigger sell price: ");
        double triggerSellPrice = scanner.nextDouble();

        TradeManager tradeManager = new TradeManager(triggerBuyPrice, triggerSellPrice);

        double[] marketPrices = {45000, 47000, 48000, 44000, 46000};

        for (double price : marketPrices) {
            System.out.println("Current market price: " + price);
            tradeManager.handleMarketPrice(price);
        }

        scanner.close();
    }
}
