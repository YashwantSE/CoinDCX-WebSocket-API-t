package com.example.CoinTrigger;

public class TradeManager {
    private double triggerBuyPrice;
    private double triggerSellPrice;
    public TradeManager(double triggerBuyPrice,double triggerSellPrice){
        this.triggerBuyPrice = triggerBuyPrice;
        this.triggerSellPrice = triggerSellPrice;
    }

    public void handleMarketPrice(double curr_Price){
        if(curr_Price<=triggerBuyPrice){
            prepareBuyOrder(curr_Price);
        }else if(curr_Price>=triggerSellPrice){
            prepareSellOrder(curr_Price);
        }

}

    private void prepareBuyOrder(double currPrice) {
        System.out.println("Buy order prepared at price: "+ currPrice);
        System.out.println("Buy order payload: {\"order\": \"buy\", \"price\": " + currPrice + "}");

    }

    private void prepareSellOrder(double currPrice) {

        System.out.println("Sell order prepared at price: "+ currPrice);
        System.out.println("Sell order payload: {\"order\": \"sell\", \"price\": " + currPrice + "}");

    }
    }
