# Java Echo Server
[![Build Status](https://travis-ci.org/junehoe/java-echo-server.svg?branch=walking-skeleton)](https://travis-ci.org/junehoe/java-echo-server)

An echo server written in Java.
## How to Run
Clone the repository to your local machine, then go inside the directory by running:
```
cd /java-echo-server
```
Inside of the directory, run the following to start the server:
```
gradle run -Pport=<desired-port-number>
```
To run client:
```
nc <ip-address> <port-number-of-server-socket>
```
### Example
If you would like to create a server on port `4242`, then you would run:
```
gradle run -Pport=4242
```
This creates a server socket on port `4242`. To connect to the socket, find your local IP address. In OSX, go to System Preferences -> Network and you should see the IP address that your machine is connected to.

While the server socket is running, open a new terminal window and run (assuming IP address is 1.2.3.4):
```
nc 1.2.3.4 4242
```
In the client terminal window, type messages and watch the server echo them back!
## How to Run Tests
Inside of the root directory, run:
```
gradle test
```
Alternatively, you can run a test watcher by running:
```
gradle -t test
```
