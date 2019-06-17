Order management system

Components
1. Process - Main java program and server and order manager implementation
2. Config - Load the configuration for order manager how it should react to certain symbols
3. Sessions - Client sessions , a Console client which works based on inputs keyed into the console
   Messages are ; delimited.
   Example 35=D; 55=STEL.SI;
4. Messsages - All type of messages handled , client messages like neworder, amend , cancel,
   A poison pill messsage to shutdown the server, a status message to dump the current state of order manager to console.
5. test - OrderManagerTest based on Junit5


Design Patterns
Singleton
MessageFactory to return types of Messages
Flyweight - Integer.valueOf  when parsing the incoming fix messages as string token.
Client - Currently only implemnetation is a ConsoleClient which reads the input from console can be extended
 to FixSocketClient etc etc .

ToDo and Assumptions
1. Make config read from config files could be from xml files.
2. Add a quickfixJ engine in the front to take a proper FIX message as an input.
3. It is serving only 1 console client at the moment and we are supporting only FIX clients
4. Better Exception handling
5. More Junits
6. Recovery persisting the state of the OMS.
7. Improve the fix message parsing logic, currently it assumes all messages start with message type tag 35
8. Capture details around amends, how much quantity or price was amended. basically keep track of all the transactions.


Sample Input/Output with states the order went through in the output
===========================================================

35=D; 1=A; 11=A1; 54=1; 55=STEL.SI; 38=100; 44=1;
35=8; 150=0; 39=0; 55=STEL.SI; 11=A1; 38=100; 44=1.0;
35=S;
Symbol STEL.SI, Side Buy, Account A, State Open, Orig Qty 100, Open Qty 100, Exec Qty 0, Price 1.0 [ Open ]
Displaying status of 1 orders
35=F; 11=B1; 41=A;
35=9; 55=null; 11=B1; 58=Order not found;
35=S;
Symbol STEL.SI, Side Buy, Account A, State Open, Orig Qty 100, Open Qty 100, Exec Qty 0, Price 1.0 [ Open ]
Displaying status of 1 orders
35=F; 11=B2; 41=A1;
35=8; 150=4; 39=4; 55=STEL.SI;
35=S;
Symbol STEL.SI, Side Buy, Account A, State Cancelled, Orig Qty 100, Open Qty 0, Exec Qty 0, Price 1.0 [ Open Cancelled ]
Displaying status of 1 orders
35=F; 11=B3; 41=A1;
35=9; 55=null; 11=B3; 58=Invalid state transition from current state Cancelled;
35=S;
Symbol STEL.SI, Side Buy, Account A, State Cancelled, Orig Qty 100, Open Qty 0, Exec Qty 0, Price 1.0 [ Open Cancelled ]
Displaying status of 1 orders
35=D; 1=A; 11=C1; 54=1; 55=STEL.SI; 38=100; 44=1;
35=8; 150=0; 39=0; 55=STEL.SI; 11=C1; 38=100; 44=1.0;
35=G; 11=C2; 41=C1; 38=200;
35=8; 150=5; 39=5; 11=C2; 41=C1; 55=STEL.SI;
35=S;
Symbol STEL.SI, Side Buy, Account A, State Cancelled, Orig Qty 100, Open Qty 0, Exec Qty 0, Price 1.0 [ Open Cancelled ]
Symbol STEL.SI, Side Buy, Account A, State Amended, Orig Qty 100, Open Qty 200, Exec Qty 0, Price 0.0 [ Open Amended ]
Displaying status of 2 orders
35=G; 11=C3; 41=C2; 38=300;
35=8; 150=5; 39=5; 11=C3; 41=C2; 55=STEL.SI;
35=S;
Symbol STEL.SI, Side Buy, Account A, State Cancelled, Orig Qty 100, Open Qty 0, Exec Qty 0, Price 1.0 [ Open Cancelled ]
Symbol STEL.SI, Side Buy, Account A, State Amended, Orig Qty 100, Open Qty 300, Exec Qty 0, Price 0.0 [ Open Amended Amended ]
Displaying status of 2 orders
35=F; 11=C4; 41=C3;
35=8; 150=4; 39=4; 55=STEL.SI;
35=S;
Symbol STEL.SI, Side Buy, Account A, State Cancelled, Orig Qty 100, Open Qty 0, Exec Qty 0, Price 1.0 [ Open Cancelled ]
Symbol STEL.SI, Side Buy, Account A, State Cancelled, Orig Qty 100, Open Qty 0, Exec Qty 0, Price 0.0 [ Open Amended Amended Cancelled ]
Displaying status of 2 orders
35=D; 1=B; 11=D1; 54=1; 55=SIAL.SI; 38=100; 44=1;
35=8; 150=0; 39=0; 55=SIAL.SI; 11=D1; 38=100; 44=1.0;
35=8; 150=2; 39=2; 55=SIAL.SI; 11=D1; 44=0.0;
35=F; 11=D2; 41=D1;
35=9; 55=null; 11=D2; 58=Invalid state transition from current state FFill;
35=G; 11=D3; 41=D1; 38=200;
35=9; 55=null; 11=D3; 58=Invalid state transition from current state FFill;


