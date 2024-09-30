
CoinConnection
 
This project is a Java-based application that connects to the CoinConnection API to receive real-time trading data. The application listens for updates on cryptocurrency prices and executes order operations based on user-specified trigger prices.

Features

* Real-Time Data: Connects to the CoinDCX WebSocket API to receive live cryptocurrency market data.
* Order Triggering: Allows users to input trigger prices to execute order operations automatically.
* Spring Boot Integration: Built using the Spring Boot framework for easy integration and scalability.
* WebSocket Communication: Utilizes Spring's WebSocket client to handle communication with the WebSocket API.

Technologies Used

* Java 17
* Spring Boot 3.x
* WebSocket API: Handles real-time communication with CoinDCX.
* Spring Data JPA (optional): For persistence of trading data or trigger price history.
* Maven: Build and dependency management.
* Jackson: For JSON parsing and handling.
* Swing: To create a simple graphical user interface (GUI).


#Prerequisites

Java 17 or higher installed.
Maven installed for building and running the project.
A valid CoinDCX API key for WebSocket connection (if required).


How to Use:
 *  Enter your trigger buy price and trigger sell price in the GUI.
 *  The application will start fetching real-time market prices.
 *  When the current price hits the trigger points, the application will prepare the buy/sell order payloads and display them in the output area.


#Future Improvements:

 *  Add a more advanced user interface with charts and notifications
 * Add authentication support 
* Implement order execution logic.
