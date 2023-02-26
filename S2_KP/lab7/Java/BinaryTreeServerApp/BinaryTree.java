/** @author Jakub Musial 268442  */
/** Binary Tree @version 1.0 */
/** Java @version jdk1.8.0_331 */



import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;

import javafx.scene.control.Label;
import javafx.scene.text.Text;

import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;





/** Class implementing Binary Tree's nodes */
class Node <T extends Comparable <T> > implements java.io.Serializable {
    public T value; /** Tree node's value */
    public Node <T> left; /** Tree node's left child pointer */
    public Node <T> right; /** Tree node's right child pointer */
    
    /** Node class constructor */
    public Node (T value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }
}





/** Clas implementing Binary Tree data structure */
public class BinaryTree <T extends Comparable <T> > implements java.io.Serializable {
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

        /** Method returning the tree's height */
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