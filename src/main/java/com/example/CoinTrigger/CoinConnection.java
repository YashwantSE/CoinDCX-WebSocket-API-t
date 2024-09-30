package com.example.CoinTrigger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.WebSocketSession;



import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.CompletableFuture;


import javax.swing.JTextArea;

public class CoinConnection extends WebSocketClient {

    private static final Logger logger = LoggerFactory.getLogger(CoinConnection.class);
    private final URI uri;
    private ObjectMapper mapper = new ObjectMapper();
    private static JTextArea outputArea;
    private double triggerPrice;

    public CoinConnection(URI uri, double triggerPrice, JTextArea outputArea) {
        super(uri);
        this.uri = uri;
        this.triggerPrice = triggerPrice;
        this.outputArea = outputArea; // Pass output area from the GUI
    }
    public void onOpen(ServerHandshake handshakedata) {
        logToGUI("Connection opened");

        try {
            ObjectNode subscribeMessage = mapper.createObjectNode();
            subscribeMessage.put("e", "subscribe");
            subscribeMessage.put("symbol", "BTC_USDT");
            subscribeMessage.put("channel", "ticker");

            this.send(subscribeMessage.toString());
            logToGUI("Subscription message sent: " + subscribeMessage);

        } catch (Exception e) {
            logToGUI("Error creating subscription message: " + e.getMessage());
        }
    }



    public void onMessage(String message) {
        logToGUI("Received message: " + message);

        // Simulate order processing based on incoming market data
        try {
            ObjectNode receivedJson = mapper.readValue(message, ObjectNode.class);
            // Assuming we get market price from the message (mocking here)
            double marketPrice = receivedJson.has("p") ? receivedJson.get("p").asDouble() : 45000.0;

            logToGUI("Current market price: " + marketPrice);

            if (marketPrice > triggerPrice) {
                logToGUI("Sell order prepared at price: " + marketPrice);
                ObjectNode sellOrder = mapper.createObjectNode();
                sellOrder.put("order", "sell");
                sellOrder.put("price", marketPrice);
                logToGUI("Payload: " + sellOrder.toString());
            }

        } catch (Exception e) {
            logToGUI("Error parsing incoming message: " + e.getMessage());
        }
    }





    public void onError(Exception ex) {
        logToGUI("WebSocket error occurred: " + ex.getMessage());
    }

    public void onClose(int code, String reason, boolean remote) {
        logToGUI("Connection closed. Code: " + code + ", Reason: " + reason + ", Remote: " + remote);
    }
    private static void logToGUI(String message) {
        if (outputArea != null) {
            outputArea.append(message + "\n");
        }
        System.out.println(message);
    }


    public static void main(String[] args) {
        try {
            URI uri = new URI("wss://stream.coindcx.com");
            JTextArea outputArea = new JTextArea();
            CoinConnection client = new CoinConnection(uri, 900.0, null);
             logToGUI("Connection failed.");
            client.connect();
        } catch (URISyntaxException e) {
            logger.error("Invalid URI", e);
        }
    }




}