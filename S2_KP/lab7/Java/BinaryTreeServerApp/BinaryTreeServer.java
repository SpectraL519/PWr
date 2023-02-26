/** @author Jakub Musial 268442  */
/** Binary Tree Application - Server @version 2.0 */
/** Java @version jdk1.8.0_331 */



import java.io.*;
import java.net.*;




/** Class implementing single client's thread on the server 
 * @see BinaryTreeServerThread
 * 
 * Attributes:
 * @see socket
 * @see messageFromClient
 * @see messageToClient
 * @see command
 * @see dtype
 * @see element
 * 
 * Methods:
 * @see run
 * @see processCommand
 * @see sendBooleanMessageToClient
 * @see sendMessageToClient
 * @see sendTreeToClient 
*/
class BinaryTreeServerThread extends Thread {
    private Socket socket; /** Client's socket on the server*/
    private ObjectInputStream messageFromClient; /** Client's input stream on the server (used for recieving messages from a client on the server) */
    private ObjectOutputStream messageToClient; /** Client's output stream on the server (used for sending messages to a client from the server) */

    private String command; /** A command recieved from a client: \nempty \ndraw \nsearch \ninsert \n delete*/
    private String dtype; /** Data type for a command recieved from a client: \nint \ndouble \nstring) */
    private String element; /** Element's value for a command recieved from a client */


    /** BinaryTreeServerThread class constructor */
    public BinaryTreeServerThread(Socket socket) {
        this.socket = socket;
    }

    
    /** Method for sending a message of Boolean value to a client */
    private void sendBooleanMessageToClient (Boolean message) {
        try {
            this.messageToClient.writeObject("Boolean");
            this.messageToClient.writeObject(message);
        }
        catch (IOException e) { System.out.println("I/O Error: " + e.getMessage()); }
    }
    
    /** Method for sending an info message (of String value) to a client */
    private void sendMessageToClient (String messageType, String message) {
        try {
            this.messageToClient.writeObject(messageType);
            this.messageToClient.writeObject(message);
        }
        catch (IOException e) { System.out.println("I/O Error: " + e.getMessage()); }
    }
    

    /** Method for sending a BinaryTree class object to a client */
    private <T extends Comparable <T> > void sendTreeToClient (String dtype,  BinaryTree <T> tree) {
        try {
            this.messageToClient.reset();
            this.messageToClient.writeObject("Tree");
            this.messageToClient.writeObject(dtype);
            this.messageToClient.writeObject(tree);
        }
        catch (IOException e) { System.out.println("I/O Error: " + e.getMessage()); }
    }
    
    /** Method for processing a command (with it's dtype and value) recieved from a client */
    private void processCommand() {
        if (command.equals("empty")) {
            if (dtype.equals("int")) { sendBooleanMessageToClient(BinaryTreeServer.intTree.isEmpty()); }
            else if (dtype.equals("double")) { sendBooleanMessageToClient(BinaryTreeServer.doubleTree.isEmpty()); }
            else if (dtype.equals("string")) { sendBooleanMessageToClient(BinaryTreeServer.stringTree.isEmpty()); }
            else { sendMessageToClient("Error", "Invalid dtype"); } 
        }
        
        else if (command.equals("draw")) {
            if (dtype.equals("int")) { 
                if (!BinaryTreeServer.intTree.isEmpty()) { sendTreeToClient("int", BinaryTreeServer.intTree); }
                else { sendMessageToClient("Error", "Empty tree"); }
            }
            else if (dtype.equals("double")) { 
                if (!BinaryTreeServer.doubleTree.isEmpty()) { sendTreeToClient("double", BinaryTreeServer.doubleTree); }
                else { sendMessageToClient("Error", "Empty tree"); }
            }
            else if (dtype.equals("string")) { 
                if (!BinaryTreeServer.stringTree.isEmpty()) { sendTreeToClient("string", BinaryTreeServer.stringTree); }
                else { sendMessageToClient("Error", "Empty tree"); }
            } 
            else { sendMessageToClient("Error", "Invalid dtype"); }
        }
        
        else if (command.equals("search")) {
            if (dtype.equals("int")) { 
                try { sendBooleanMessageToClient(BinaryTreeServer.intTree.search(BinaryTreeServer.intTree.root, Integer.parseInt(element))); }
                catch (NumberFormatException e) { sendMessageToClient("Error", "Invalid value"); }
            }
            else if (dtype.equals("double")) {
                try { sendBooleanMessageToClient(BinaryTreeServer.doubleTree.search(BinaryTreeServer.doubleTree.root, Double.parseDouble(element))); }
                catch (NumberFormatException e) { sendMessageToClient("Error", "Invalid value"); }
            }
            else if (dtype.equals("string")) {
                sendBooleanMessageToClient(BinaryTreeServer.stringTree.search(BinaryTreeServer.stringTree.root, element));
            }
            else { sendMessageToClient("Error", "Invalid dtype"); }
        }
        
        else if (command.equals("insert")) {
            if (dtype.equals("int")) {
                try { 
                    BinaryTreeServer.intTree.insert(Integer.parseInt(element)); 
                    sendMessageToClient("Info", "Successfully added the element: " + element + "\nto Binary Tree <int>");
                }
                catch (NumberFormatException e) { sendMessageToClient("Error", "Invalid value"); }
            }
            else if (dtype.equals("double")) {
                try { 
                    BinaryTreeServer.doubleTree.insert(Double.parseDouble(element)); 
                    sendMessageToClient("Info", "Successfully added the element: " + element + "\nto Binary Tree <double>");
                }
                catch (NumberFormatException e) { sendMessageToClient("Error", "Invalid value"); }
            }
            else if (dtype.equals("string")) { 
                BinaryTreeServer.stringTree.insert(element); 
                sendMessageToClient("Info", "Successfully added the element: " + element + "\nto Binary Tree <string>");
            }
            else { sendMessageToClient("Error", "Invalid dtype"); }
        }
        
        else if (command.equals("delete")) {
            if (dtype.equals("int")) {
                try { 
                    int value = Integer.parseInt(element);
                    if (BinaryTreeServer.intTree.search(BinaryTreeServer.intTree.root, value)) {
                        BinaryTreeServer.intTree.root = BinaryTreeServer.intTree.deleteNode(BinaryTreeServer.intTree.root, value); 
                        sendMessageToClient("Info", "Successfully deleted element: " + element + "\nfrom Binary Tree <int>");
                    }
                    else { sendMessageToClient("Error", "Couldn't find element: " + element + "\nin Binary Tree <int>"); }
                }
                catch (NumberFormatException e) { sendMessageToClient("Error", "Invalid value"); }
            }
            else if (dtype.equals("double")) {
                try { 
                    double value = Double.parseDouble(element);
                    if (BinaryTreeServer.doubleTree.search(BinaryTreeServer.doubleTree.root, value)) {
                        BinaryTreeServer.doubleTree.root = BinaryTreeServer.doubleTree.deleteNode(BinaryTreeServer.doubleTree.root, value); 
                        sendMessageToClient("Info", "Successfully deleted element: " + element + "\nfrom Binary Tree <double>");
                    }
                    else { sendMessageToClient("Error", "Couldn't find element: " + element + "\nin Binary Tree <double>"); }
                }
                catch (NumberFormatException e) { sendMessageToClient("Error", "Invalid value"); }
            }
            else if (dtype.equals("string")) { 
                if (BinaryTreeServer.stringTree.search(BinaryTreeServer.stringTree.root, element)) {
                    BinaryTreeServer.stringTree.root = BinaryTreeServer.stringTree.deleteNode(BinaryTreeServer.stringTree.root, element); 
                    sendMessageToClient("Info", "Successfully deleted element: " + element + "\nfrom Binary Tree <int>");
                }
                else { sendMessageToClient("Error", "Couldn't find element: " + element + "\nin Binary Tree <string>"); }
            }
            else { sendMessageToClient("Error", "Invalid dtype"); }
        }

        else { sendMessageToClient("Error", "Invalid command"); }
    }
    
    /** BinaryTreeServerThread class' running method (main operating method) */
    public void run() {
        try {
            this.messageFromClient = new ObjectInputStream(this.socket.getInputStream());
            this.messageToClient = new ObjectOutputStream(this.socket.getOutputStream());
            
            do { 
                try {
                    // getting a command message from client
                    this.command = (String) this.messageFromClient.readObject();
                    this.dtype = (String) this.messageFromClient.readObject();
                    this.element = (String) this.messageFromClient.readObject();
                    processCommand(); 
                }
                catch (ClassNotFoundException e) { System.out.println("Invalid class type of the object recieved from the client"); }
                catch (IOException e) { break; }
            } while (this.socket.isConnected());

            System.out.println("Client disconnected!");
        }
        catch (IOException e) { System.out.println("I/O Error: " + e.getMessage()); }
    }
}





/** Class implementing Binary Tree Application's server 
 * Attributes:
 * @see intTree
 * @see doubleTree
 * @see stringTree
 * 
 * Methods:
 * @see main
*/
public class BinaryTreeServer {
    public static BinaryTree <Integer> intTree = new BinaryTree <Integer> (); /** Binary Tree Application Server's BinaryTree structure of dtype = Integer */
    public static BinaryTree <Double> doubleTree = new BinaryTree <Double> (); /** Binary Tree Application Server's BinaryTree structure of dtype = Double */
    public static BinaryTree <String> stringTree = new BinaryTree <String> (); /** Binary Tree Application Server's BinaryTree structure of dtype = String */

    /** Binary Tree Application Server program's main method */
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(4444)) {
            System.out.println("Server is listening on port 4444");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected!");
 
                new BinaryTreeServerThread(socket).start();
            }
 
        } 
        catch (IOException e) {
            System.out.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}