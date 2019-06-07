Order management system

Components
1. Process - Main java program and server implementation
2. ConfigManager - Load the configuration
3. OrderManager - Order and state machine
4. Client - Client sessions
5. Admin - To capture state of the system
6. Tests - Junit tests


Design Patterns
Singleton ConfigManager
MessageFactory to return types of Messages
Strategy pattern ? TODo lets try to use this with lambda for accept /reject list
Client - Currently only implemnetation is a ConsoleClient which reads the input from console can be extended
 to FixSocketClient etc etc .

ToDo
1. Make config read from config files could be from xml files.
2. Add a quickfixJ engine in the front to take a proper FIX message as an input.
3. It is serving only 1 console client at the moment
