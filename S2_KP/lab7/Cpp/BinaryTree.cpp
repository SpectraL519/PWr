#include <iostream>
#include <limits>
// #include "declarations.hpp"




/** Class implementing Binary Tree's node structure
 * @tparam T 
 * @see Node
 * @see ~Node
 * @see value
 * @see left
 * @see right
 */
template <typename T>
class Node {
    public:
        T value; /** Binary Tree node's value */
        Node <T> *left; /** Binary Tree node's left child */
        Node <T> *right; /** Binary Tree node's right child */
        
        /** Node class constructor */
        Node (T value) {
            this->value = value;
            this->left = nullptr;
            this->right = nullptr;
        }

        /** Node class destructor */
        ~Node() {
            if (this->left) delete this->left;
            if (this->right) delete this->right;
            delete this;
        }
};


/** Class implementing Binary Tree data structure
 * @tparam T 
 * @see BinaryTree
 * @see ~BinaryTree
 * @see isEmpty
 * @see draw
 * @see printTree
 * @see search
 * @see insert
 * @see deleteNode
 * @see minValueNode
 */
template <typename T>
class BinaryTree {
    private: 
        /** Method for printing the node (subtree) into the console */
        void printTree (Node <T> *node) {
            if (node == nullptr) { return; }

            std::cout << node->value;            
            if (node->left != nullptr && node->right != nullptr) {
                std::cout << "(";
                printTree(node->left);
                std::cout << ")(";
                printTree(node->right);
                std::cout << ")";
            }
            else {
                if (node->left != nullptr) {
                    std::cout << "(";
                    printTree(node->left);
                    std::cout << ")()";
                }
                if (node->right != nullptr) {
                    std::cout << "()(";
                    printTree(node->right);
                    std::cout << ")";
                }
            }
        }

        /** Method for finding the tree's node with minimal value */
        Node <T> *minValueNode (Node <T> *node) {
            Node <T> *current = node;
            while (current != nullptr && current->left != nullptr)  { current = current->left; }
            return current;
        }
    

    public:
        /** Binary tree's root node */
        Node <T> *root;


        /** BinaryTree class constructor */
        BinaryTree() {
            this->root = nullptr;
        }

        /** BinaryTree class destructor */
        ~BinaryTree() { if (this->root) delete this->root; }
 
 
        /** Method checking if the tree is empty */
        bool isEmpty() {
            return (this->root == nullptr);
        }

        /** Method printing the tree to the console */
        void draw() {
            if (this->root == nullptr)  { std::cout << "Empty tree" << std::endl; return; }
            printTree(this->root);
            std::cout << std::endl;
        }

        /** Method searching the tree for a given element */
        bool search (Node <T> *node, T element) {
            if (node == nullptr)  { return false; }
            if (element == node->value)    { return true; }
            if (element < node->value)  { return search(node->left, element); }
            return search(node->right, element);  // if (element > node->value)
        }   

        /** Method for inserting a new element into the tree */
        void insert (T element) {
            Node <T> *positionPtr = nullptr;
            Node <T> *tmp = this->root;

            while (tmp != nullptr) {
                positionPtr = tmp;
                if (element < tmp->value)    { tmp = tmp->left; }
                else    { tmp = tmp->right; }
            }

            // if the tree is empty
            if (positionPtr == nullptr) {
                this->root = new Node <T> (element);
                return;
            }

            if (element < positionPtr->value) {
                positionPtr->left = new Node <T> (element);
                return;
            }

            // if (element >= positionPtr->value)
            positionPtr->right = new Node <T> (element);
        }

        /** Method deleting a given element from the tree */
        Node <T> *deleteNode (Node <T> *node, T element) {
            if (node == nullptr)    { return node; }

            if (element < node->value)  { node->left = deleteNode(node->left, element); }
            else if (element > node->value) {node->right = deleteNode(node->right, element); }
            else {
                if (node->left == nullptr && node->right == nullptr)    { return nullptr; }
                else if (node->left == nullptr) {
                    Node <T> *tmp = node->right;
                    free(node);
                    return tmp;
                }
                else if (node->right == nullptr) {
                    Node <T> *tmp = node->left;
                    free(node);
                    return tmp;
                }
                // if both child nodes are not null
                Node <T> *tmp = minValueNode(node->right);
                node->value = tmp->value;
                node->right = deleteNode(node->right, tmp->value);
            }
            return node;
        }
};




/** Method for ingoring line input after a command */
void ignoreFurtherInput (std::string message) {
    if (message != "")    { std::cout << message << std::endl; }
    std::cin.clear();
    std::cin.ignore(std::numeric_limits <std::streamsize>::max(), '\n');
}

/** Method for printing a boolean message to a terminal */
void printBoolean (bool expression) {
    if (expression) { std::cout << "True" << std::endl; return; }
    std::cout << "False" << std::endl;
}



/** BinaryTree program's main method */
int main (int argc, char *argv[])
{
    BinaryTree <int> *intTree = new BinaryTree <int>;
    BinaryTree <double> *doubleTree = new BinaryTree <double>;
    BinaryTree <std::string> *stringTree = new BinaryTree <std::string>;

    while (true) {
        std::cout << "cmd: ";
        std::string command, dtype, element;
        std::cin >> command;

        if (command == "exit") { 
            delete intTree;
            delete doubleTree;
            delete stringTree;
            break; 
        }

        else if (command == "empty") {
            std::cin >> dtype;
            if (dtype == "int") { printBoolean(intTree->isEmpty()); }
            else if (dtype == "double") { printBoolean(doubleTree->isEmpty()); }
            else if (dtype == "string") { printBoolean(stringTree->isEmpty()); }
            else { ignoreFurtherInput("Invalid dtype"); }
        }

        else if (command == "draw") {
            std::cin >> dtype;
            if (dtype == "int") { intTree->draw(); }
            else if (dtype == "double") { doubleTree->draw(); }
            else if (dtype == "string") { stringTree->draw(); }
            else { ignoreFurtherInput("Invalid dtype"); }
        }

        else if (command == "search") {
            std::cin >> dtype;
            if (dtype == "int") {
                std::cin >> element;
                try { printBoolean(intTree->search(intTree->root, std::stoi(element))); }
                catch (std::exception &e) { ignoreFurtherInput("Invalid value"); }
            }
            else if (dtype == "double") {
                std::cin >> element; 
                try { printBoolean(doubleTree->search(doubleTree->root, std::stod(element))); }
                catch (std::exception &e) { ignoreFurtherInput("Invalid value"); }
            }
            else if (dtype == "string") {
                std::string element;
                std::cin >> element;
                printBoolean(stringTree->search(stringTree->root, element));
            }
            else { ignoreFurtherInput("Invalid dtype"); }
        }

        else if (command == "insert") {
            std::cin >> dtype;
            if (dtype == "int") {
                std::cin >> element; 
                try { intTree->insert(std::stoi(element)); }
                catch (std::exception &e) { ignoreFurtherInput("Invalid value"); }
            }
            else if (dtype == "double") {
                std::cin >> element; 
                try { doubleTree->insert(std::stod(element)); }
                catch (std::exception &e) { ignoreFurtherInput("Invalid value"); }
            }
            else if (dtype == "string") {
                std::string element;
                std::cin >> element;
                stringTree->insert(element);
            }
            else { ignoreFurtherInput("Invalid dtype"); }
        }

        else if (command == "delete") {
            std::cin >> dtype;
            if (dtype == "int") {
                std::cin >> element; 
                try { 
                    if (intTree->search(intTree->root, std::stoi(element)))   { intTree->root = intTree->deleteNode(intTree->root, std::stoi(element)); }
                    else    { std::cout << "Couldn't find element: " << element << " to delete in Binary Tree <int>" << std::endl; }
                }
                catch (std::exception &e) { ignoreFurtherInput("Invalid value"); }
            }
            else if (dtype == "double") {
                std::cin >> element; 
                try { 
                    if (doubleTree->search(doubleTree->root, std::stod(element)))   { doubleTree->root = doubleTree->deleteNode(doubleTree->root, std::stod(element)); }
                    else    { std::cout << "Couldn't find element: " << element << " to delete in Binary Tree <double>" << std::endl;; } 
                }
                catch (std::exception &e) { ignoreFurtherInput("Invalid value"); }
            }
            else if (dtype == "string") {
                std::cin >> element;
                if (stringTree->search(stringTree->root, element))    { stringTree->root = stringTree->deleteNode(stringTree->root, element); }
                else    { std::cout << "Couldn't find element: " << element << " to delete in Binary Tree <string>" << std::endl; }
            }
            else { ignoreFurtherInput("Invalid dtype"); }
        }

        else { ignoreFurtherInput("Invalid command"); }
    }

    return 0;
}