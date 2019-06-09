Order management system

Components
1. Process - Main java program and server and order manager implementation
2. Config - Load the configuration for order manager how it should react to certain symbols
3. Sessions - Client sessions , a Console client which works based on inputs keyed into the console
   Messages are ; delimited.
   Example 35=D; 55=STEL.SI;
4. Messsages - All type of messages handled , client messages like neworder, amend , cancel,
   A poison pill messsage to shutdown the server, a status message to dump the current state of order manager to console.


Design Patterns
Singleton
MessageFactory to return types of Messages
Flyweight - Integer.valueOf  when parsing the incoming fix messages as string token.
Client - Currently only implemnetation is a ConsoleClient which reads the input from console can be extended
 to FixSocketClient etc etc .

ToDo
1. Make config read from config files could be from xml files.
2. Add a quickfixJ engine in the front to take a proper FIX message as an input.
3. It is serving only 1 console client at the moment
4. Exception handling
5. Junits
6. Recovery persisting the state of the OMS.
7. Improve the fix message parsing logic, currently it assumes all messages start with message type tag 35


Sample Output
==============
35=D; 55=STEL.SI; 44=1; 11=AC; 1=A1; 54=1; 38=100;
35=8; 150=0; 39=0; 55=STEL.SI; 11=AC; 38=100; 44=1.0;
35=S;
Symbol STEL.SI , Side Buy , Account A1 ,State Open
Displaying status of 1 orders
35=D; 55=STEL.SI; 44=2; 11=AC2; 1=AB; 54=2; 38=200;
35=8; 150=0; 39=0; 55=STEL.SI; 11=AC2; 38=200; 44=2.0;
35=S;
Symbol STEL.SI , Side Sell , Account AB ,State Open
Symbol STEL.SI , Side Buy , Account A1 ,State Open
Displaying status of 2 orders
35=K;
Shutting down