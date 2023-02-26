/** @author Jakub Musial 268442  */
/** Program @version 1.0 */
/** Java @version jdk1.8.0_331 */

import javafx.application.Application;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;

import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;

import javafx.geometry.Insets;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.geometry.Pos;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;






/** Class implementing Binary Tree's nodes */
class Node <T extends Comparable <T> > {
    public T value;
    public Node <T> left;
    public Node <T> right;

    /** Node class constructor */
    public Node (T value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }
}


/** Clas implementing Binary Tree data structure */
class BinaryTree <T extends Comparable <T> > {
    public Node <T> root; /** Binary tree's root node */

    /** BinaryTree class constructor */
    public BinaryTree() {
        this.root = null;
    }

    /** Method checking if the tree is empty */
    public boolean isEmpty() {
        return (this.root == null); 
    }

        /** Method for drawing a single node */
        private void drawNode (Pane DrawField, Node <T> node, Boolean root, double startX, double startY, double endX, double endY, int height, int level, double paneWidth){            
            Line line = new Line(startX, startY - 35, endX, endY - 5);
            Circle circle = new Circle(endX, endY, 15, Color.LIGHTBLUE);
            Text text = new Text(endX - 5, endY + 5, String.valueOf(node.value));
            if (root) { DrawField.getChildren().addAll(circle, text); }
            else { DrawField.getChildren().addAll(line, circle, text); }

            double spacing = (paneWidth / Math.pow(2, level + 1));
            if(node.left != null) { drawNode(DrawField, node.left, false, endX, endY + 50, endX - spacing, endY + 50, height, level + 1, paneWidth); }
            if(node.right != null) { drawNode(DrawField, node.right, false, endX, endY + 50, endX + spacing, endY + 50, height, level + 1, paneWidth); }
        }

        private int getHeight (Node <T> node) {
            if (node == null)   { return 0; }
            
            return (1 + Math.max(getHeight(node.left), getHeight(node.right)));
        }

    /** Method printing the tree to the console */
    public void draw (BorderPane MainPane) {
        if (this.root == null)  { 
            Label EmptyTreeLabel = new Label("Empty tree");
            EmptyTreeLabel.setFont(new Font("Courier New", 36));
            MainPane.setCenter(EmptyTreeLabel);
            MainPane.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
            return; 
        }
        // if (root != null)
        Pane DrawSpace = new Pane();
        drawNode(DrawSpace, this.root, true, MainPane.getWidth() / 2, 50, MainPane.getWidth() / 2, 50, this.getHeight(this.root), 1, MainPane.getWidth());
        MainPane.setCenter(DrawSpace);
        MainPane.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
    }

    /** Method searching the tree for a given element */
    public boolean search (Node <T> node, T element) {
        if (node == null)  { return false; }
        if (element.compareTo(node.value) == 0) { return true; }
        if (element.compareTo(node.value) < 0)  { return search(node.left, element); }
        return search(node.right, element);  // if (element.compareTo(node.value) > 0)
    }

    /** Method for inserting a new element into the tree */  
    public void insert (T element) {
        Node <T> positionPtr = null;
        Node <T> tmp = this.root;

        while (tmp != null) {
            positionPtr = tmp;
            if (element.compareTo(tmp.value) < 0)   { tmp = tmp.left; }
            else    { tmp = tmp.right; }
        }

        // if the tree is empty
        if  (positionPtr == null) {
            this.root = new Node <T> (element);
            return;
        }

        if (element.compareTo(positionPtr.value) < 0) {
            positionPtr.left = new Node <T> (element);
            return;
        }
        // if (element.compareTo(positionPtr.value) >= 0)
        positionPtr.right = new Node <T> (element);
    }

        /** Method for finding the tree's node with minimal value */
        private Node <T> minValueNode (Node <T> node) {
            Node <T> current = node;
            while (current != null && current.left != null)  { current = current.left; }
            return current;
        }

    /** Method deleting a given element from the tree */
    public Node <T> deleteNode (Node <T> node, T element) {
        if (node == null)    { return node; }

        if (element.compareTo(node.value) < 0)  { node.left = deleteNode(node.left, element); }
        else if (element.compareTo(node.value) > 0) {node.right = deleteNode(node.right, element); }
        else {
            if (node.left == null && node.right == null)    { return null; }
            else if (node.left == null) {
                Node <T> tmp = node.right;
                node = null;
                return tmp;
            }
            else if (node.right == null) {
                Node <T> tmp = node.left;
                node = null;
                return tmp;
            }
            // if both child nodes are not null
            Node <T> tmp = minValueNode(node.right);
            node.value = tmp.value;
            node.right = deleteNode(node.right, tmp.value);
        }
        return node;
    }
}





class InputPanel extends HBox {
    private TextField InputField;

    public InputPanel (String FieldName) {
        super();

        this.InputField = new TextField();

        this.setAlignment(Pos.CENTER_LEFT);
        this.getChildren().addAll(new Label(FieldName), this.InputField);
    }

    public String getText()    { return this.InputField.getText(); }
    public void clearText()   { this.InputField.setText(""); }
}



/** Class implementing BinaryTree application */
public class BinaryTreeApplication extends Application {
    private void putMessage (BorderPane MainPane, String message) {
        Label MessageLabel = new Label(message);
        MessageLabel.setAlignment(Pos.CENTER);
        MessageLabel.setFont(new Font("Courier New", 36));
        MainPane.setCenter(MessageLabel);
        MainPane.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
    }
    
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
    
    private void putErrorMessage (BorderPane MainPane, String message) {
        Label MessageLabel = new Label(message);
        MessageLabel.setAlignment(Pos.CENTER);
        MessageLabel.setFont(new Font("Courier New", 36));
        MainPane.setCenter(MessageLabel);
        MainPane.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
    }



    @Override
    public void start (Stage PrimaryStage) {
        BinaryTree <Integer> intTree = new BinaryTree <Integer> ();
        BinaryTree <Double> doubleTree = new BinaryTree <Double> ();
        BinaryTree <String> stringTree = new BinaryTree <String> ();

        /** Main application Pane */
        BorderPane MainPane = new BorderPane();
        MainPane.setPrefSize(1024, 628);

        InputPanel CommandInputPanel = new InputPanel("command: ");
        InputPanel DtypeInputPanel = new InputPanel("dtype: ");
        InputPanel ValueInputPanel = new InputPanel("value: ");

        Button ProceedButton = new Button("Proceed");
        ProceedButton.setOnAction(new EventHandler <ActionEvent> () {
            @Override
            public void handle (ActionEvent event) {
                String command = CommandInputPanel.getText();
                String dtype = DtypeInputPanel.getText();
                String element = ValueInputPanel.getText();

                if (command.equals("empty")) {
                    if (dtype.equals("int")) { putBooleanMessage(MainPane, intTree.isEmpty()); }
                    else if (dtype.equals("double")) { putBooleanMessage(MainPane, doubleTree.isEmpty()); }
                    else if (dtype.equals("string")) { putBooleanMessage(MainPane, stringTree.isEmpty()); }
                    else { putErrorMessage(MainPane, "Invalid dtype"); }
                }

                else if (command.equals("draw")) {
                    if (dtype.equals("int")) { intTree.draw(MainPane); }
                    else if (dtype.equals("double")) { doubleTree.draw(MainPane); }
                    else if (dtype.equals("string")) { stringTree.draw(MainPane); } 
                    else { putErrorMessage(MainPane, "Invalid dtype"); }
                }

                else if (command.equals("search")) {
                    if (dtype.equals("int")) { 
                        try { putBooleanMessage(MainPane, intTree.search(intTree.root, Integer.parseInt(element))); }
                        catch (NumberFormatException e) { putErrorMessage(MainPane, "Invalid value"); }
                    }
                    else if (dtype.equals("double")) {
                        try { putBooleanMessage(MainPane, doubleTree.search(doubleTree.root, Double.parseDouble(element))); }
                        catch (NumberFormatException e) { putErrorMessage(MainPane, "Invalid value"); }
                    }
                    else if (dtype.equals("string")) {
                        putBooleanMessage(MainPane, stringTree.search(stringTree.root, element));
                    }
                    else { putErrorMessage(MainPane, "Invalid dtype"); }
                }

                else if (command.equals("insert")) {
                    if (dtype.equals("int")) {
                        try { 
                            intTree.insert(Integer.parseInt(element)); 
                            putMessage(MainPane, "Successfully added the element: " + element + "\nto Binary Tree <int>"); 
                        }
                        catch (NumberFormatException e) { putErrorMessage(MainPane, "Invalid value"); }
                    }
                    else if (dtype.equals("double")) {
                        try { 
                            doubleTree.insert(Double.parseDouble(element)); 
                            putMessage(MainPane, "Successfully added the element: " + element + "\nto Binary Tree <double>");
                        }
                        catch (NumberFormatException e) { putErrorMessage(MainPane, "Invalid value"); }
                    }
                    else if (dtype.equals("string")) { 
                        stringTree.insert(element); 
                        putMessage(MainPane, "Successfully added the element: " + element + "\nto Binary Tree <string>");
                    }
                    else { putErrorMessage(MainPane, "Invalid dtype"); }
                }

                else if (command.equals("delete")) {
                    if (dtype.equals("int")) {
                        try { 
                            int value = Integer.parseInt(element);
                            if (intTree.search(intTree.root, value)) {
                                intTree.root = intTree.deleteNode(intTree.root, value); 
                                putMessage(MainPane, "Successfully deleted element: " + element + "\nfrom Binary Tree <int>");
                            }
                            else { putErrorMessage(MainPane, "Couldn't find element: " + element + "\nin Binary Tree <int>"); }
                        }
                        catch (NumberFormatException e) { putErrorMessage(MainPane, "Invalid value"); }
                    }
                    else if (dtype.equals("double")) {
                        try { 
                            double value = Double.parseDouble(element);
                            if (doubleTree.search(doubleTree.root, value)) {
                                doubleTree.root = doubleTree.deleteNode(doubleTree.root, value); 
                                putMessage(MainPane, "Successfully deleted the element: " + element + "\nfrom Binary Tree <double>");
                            }
                            else { putErrorMessage(MainPane, "Couldn't find element: "  + element + "\nin Binary Tree <double>"); }
                        }
                        catch (NumberFormatException e) { putErrorMessage(MainPane, "Invalid value"); }
                    }
                    else if (dtype.equals("string")) { 
                        if (stringTree.search(stringTree.root, element)) {
                            stringTree.root = stringTree.deleteNode(stringTree.root, element); 
                            putMessage(MainPane, "Successfully deleted the element: " + element + "\nfrom Binary Tree <string>");
                        }
                        else { putErrorMessage(MainPane, "Couldn't find element: " + element + "\nin Binary Tree <string>"); }
                    }
                    else { putErrorMessage(MainPane, "Invalid dtype"); }
                }

                else { putErrorMessage(MainPane, "Invalid command"); }
 
                CommandInputPanel.clearText();
                DtypeInputPanel.clearText();
                ValueInputPanel.clearText();
            }
        });

        /** Panel for figure type selection */
        VBox CommandPanel = new VBox();
        CommandPanel.setPadding(new Insets(10, 12, 10, 12));
        CommandPanel.setSpacing(10);
        CommandPanel.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));
        CommandPanel.getChildren().addAll(CommandInputPanel, DtypeInputPanel, ValueInputPanel, ProceedButton);
        
        MainPane.setTop(CommandPanel);

        PrimaryStage.setTitle("Binary Tree Application");
        PrimaryStage.setScene(new Scene(MainPane));
        PrimaryStage.show();
    }

    public static void main (String[] args) {
        Application.launch(args);
    }
}