/** @author Jakub Musial 268442  */
/** Binary Tree Application - Client @version 2.0 */
/** Java @version jdk1.8.0_331 */




import javafx.application.Application;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.*;
import java.net.*;




/** Class implementing Binary Tree Application Client's GUI element: data input panel 
 * @see InputPanel
 * @see InputField
 * @see getText
 * @see clearText
*/
class InputPanel extends HBox {
    private TextField InputField; /** Input panel's text field */

    /** InputPanel class constructor */
    public InputPanel (String FieldName) {
        super();

        this.InputField = new TextField();

        this.setAlignment(Pos.CENTER_LEFT);
        this.getChildren().addAll(new Label(FieldName), this.InputField);
    }

    /** Mathod for getting text from an input panel */
    public String getText()    { return this.InputField.getText(); }

    /** Input panel's clearing method */
    public void clearText()   { this.InputField.setText(""); }
}





/** /** Class implementing Binary Tree Application's client (GUI)
 * Attributes:
 * @see socket
 * @see messageToServer
 * @see messageFromServer
 * 
 * Methods:
 * @see main
 * @see start
 * @see putMessage
 * @see putBooleanMessage
 * @see putErrorMessage
 */
public class BinaryTreeClient extends Application {
    private Socket socket; /** Client's socket on the server */
    private ObjectOutputStream messageToServer;  /** Client's output stream (used for sending messages from to the server) */
    private ObjectInputStream messageFromServer; /** Client's input stream (used for recieving messages from the server) */


    /** Method for displaying an info message on a client's GUI */
    private void putMessage (BorderPane MainPane, String message) {
        Label MessageLabel = new Label(message);
        MessageLabel.setAlignment(Pos.CENTER);
        MessageLabel.setFont(new Font("Courier New", 36));
        MainPane.setCenter(MessageLabel);
        MainPane.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
    }
    
    /** Method for displaying a message of Boolean value on a client's GUI */
    private void putBooleanMessage (BorderPane MainPane, Boolean message) {
        Label MessageLabel = new Label();
        MessageLabel.setAlignment(Pos.CENTER);
        MessageLabel.setFont(new Font("Courier New", 36));
        MainPane.setCenter(MessageLabel);
        if (message) {
            MessageLabel.setText("True");
            MainPane.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, null, null)));
        }
        else {
            MessageLabel.setText("False");
            MainPane.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
        }
    }
    
    /** Method for displaying an error message on a client's GUI */
    private void putErrorMessage (BorderPane MainPane, String message) {
        Label MessageLabel = new Label(message);
        MessageLabel.setAlignment(Pos.CENTER);
        MessageLabel.setFont(new Font("Courier New", 36));
        MainPane.setCenter(MessageLabel);
        MainPane.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
    }
    
    /** Method for displaying a Binary Tree structure recieved from the server */
    private <T extends Comparable <T> > void drawRecievedTree (BorderPane MainPane) {
        try { 
            String dtype = (String) messageFromServer.readObject();
            if (dtype.equals("int")) {
                BinaryTree <Integer> intTree = (BinaryTree <Integer>) messageFromServer.readObject();
                intTree.draw(MainPane);
            }
            else if (dtype.equals("double")) {
                BinaryTree <Double> doubleTree = (BinaryTree <Double>) messageFromServer.readObject();
                doubleTree.draw(MainPane);
            }
            else if (dtype.equals("string")) {
                BinaryTree <String> stringTree = (BinaryTree <String>) messageFromServer.readObject();
                stringTree.draw(MainPane);
            }
            else { putErrorMessage(MainPane, "Server error: invalid message"); }
        }
        catch (IOException | ClassNotFoundException e) { System.out.println("Server error:\n" + e.getMessage()); }
    }


    /** Binary Tree Application Client's GUI starting method */
    @Override
    public void start (Stage PrimaryStage) {
        BorderPane MainPane = new BorderPane(); // Main application Pane (border layout)
        MainPane.setPrefSize(1024, 628);

        InputPanel CommandInputPanel = new InputPanel("command: "); // Binary Tree Application Client' GUI element: command input panel
        InputPanel DtypeInputPanel = new InputPanel("dtype: "); // Binary Tree Application Client' GUI element: data type input panel
        InputPanel ValueInputPanel = new InputPanel("value: "); // Binary Tree Application Client' GUI element: element value input panel

        try {
            this.socket = new Socket("localhost", 4444);
            this.messageToServer = new ObjectOutputStream(socket.getOutputStream());
            this.messageFromServer = new ObjectInputStream(socket.getInputStream());
        }
        catch (UnknownHostException e) { putErrorMessage(MainPane, "Server not found\n" + e.getMessage()); }
        catch (IOException e) { putErrorMessage(MainPane, "I/O Error:\n" + e.getMessage()); }

        Button ProceedButton = new Button("Proceed"); // Binary Tree Application Client' GUI element: proceed button
        ProceedButton.setOnAction(new EventHandler <ActionEvent> () {
            @Override
            public void handle (ActionEvent event) {       
                try {
                    messageToServer.writeObject(CommandInputPanel.getText());
                    messageToServer.writeObject(DtypeInputPanel.getText());
                    messageToServer.writeObject(ValueInputPanel.getText()); 
                }
                catch (IOException e) { System.out.println("I/O Error: " + e.getMessage()); }

                String messageType = "none";
                try { messageType = (String) messageFromServer.readObject(); }
                catch (IOException | ClassNotFoundException e) { System.out.println("I/O Error: " + e.getMessage()); }

                if (messageType.equals("Info")) { 
                    try { putMessage(MainPane, (String) messageFromServer.readObject()); }
                    catch (IOException | ClassNotFoundException e) { System.out.println("I/O Error: " + e.getMessage()); }
                }
                else if (messageType.equals("Boolean")) { 
                    try { putBooleanMessage(MainPane, (Boolean) messageFromServer.readObject()); }
                    catch (IOException | ClassNotFoundException e) { System.out.println("I/O Error: " + e.getMessage()); }
                }
                else if (messageType.equals("Error")) {
                    try { putErrorMessage(MainPane, (String) messageFromServer.readObject()); }
                    catch (IOException | ClassNotFoundException e) { System.out.println("I/O Error: " + e.getMessage()); } 
                }
                else if (messageType.equals("Tree")) { drawRecievedTree(MainPane); }
                else { putErrorMessage(MainPane, "Server communiacation error"); }
 
                CommandInputPanel.clearText();
                DtypeInputPanel.clearText();
                ValueInputPanel.clearText();
            }
        });

        VBox CommandPanel = new VBox(); // Panel for figure type selection
        CommandPanel.setPadding(new Insets(10, 12, 10, 12));
        CommandPanel.setSpacing(10);
        CommandPanel.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));
        CommandPanel.getChildren().addAll(CommandInputPanel, DtypeInputPanel, ValueInputPanel, ProceedButton);
        
        MainPane.setTop(CommandPanel);

        PrimaryStage.setTitle("Binary Tree Application");
        PrimaryStage.setScene(new Scene(MainPane));
        PrimaryStage.show();
    }

    /** Binary Tree Application Client program's main method */
    public static void main (String[] args) {
        Application.launch(args);
    }
}
