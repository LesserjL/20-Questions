//This version has printSideways as an extra public method for use in lecture.

// The QuestionTree class can be used to play a "twenty questions" game with
// the user in which the computer attempts to guess an object the user is
// thinking of by asking yes/no questions.  If it fails to guess correctly, it
// asks the user for information about the object and adds it to the tree.  It
// includes methods for writing the current tree to a file and reading in a
// tree from a file.
//
// Trees are stored as a nonempty sequence of line pairs where the first line
// is "A:" for an answer node and "Q:" for a question node.  Nodes are written
// in peorder.

import java.io.*;
import java.util.*;

public class QuestionTree {
    /**Create a private root instance variable that is a QuestionNode**/
    private QuestionNode overallRoot;
    private Scanner console;

    // post: constructs a question tree with "Tiveron" in the root
    public QuestionTree() {
        console = new Scanner(System.in);
        overallRoot = new QuestionNode("Tiveron");
        /**Initialize overallRoot with Tiveron as the teacher stored in the root. Which Constructor of QuestionNode should you use?**/
        
    }

    // pre : input is open and contains a question tree in legal format (see
    //       class comment for file format
    // post: replaces the existing question tree with the tree contained in the
    //       file
    public void read(Scanner input) {
        overallRoot = readHelper(input);
    }

    // pre : input is open and contains a question tree in legal format (see
    //       class comment for file format
    // post: reads a valid tree and returns a reference to it
    private QuestionNode readHelper(Scanner input) {
        if(!input.hasNextLine()) {
            return null;
        }
        String kind = input.nextLine();
        if(kind.equals("A:")){
            return new QuestionNode(input.nextLine());
        } else{
            return new QuestionNode(input.nextLine(), readHelper(input), readHelper(input));
        }
        /**Write a conditional statement that checks to see if the input is an answer or a question. 
         * 
         * If it is an answer which QuestionNode constructor should you use if it is an answer node?
         *  See line 41 for help with how to obtain the data to be stored in the node
         *  
         *  If it is a question which QuestionNode constructor should you use if it is an Question node?
         *  Hint: For the left and right Question nodes you will need a recursive call to readHelper
         *
         *  Then it calls the appropriate QuestionNode constructor
        */
        
    }   

    // pre : ouput is open for writing
    // post: writes the entire question tree to output in standard format (see
    //       class comment for format information)
    public void write(PrintStream output) {
        write(output, overallRoot);
        output.close();
    }

    // pre : ouput is open for writing
    // post: writes the given question tree to output in standard format (see
    //       class comment for format information)
    private void write(PrintStream output, QuestionNode root) {
        if (root.isLeaf()) {
            output.println("A:");
            output.println(root.getText());  
        } else {
            output.println("Q:");
            output.println(root.text);
            write(output,root.getLeft());   
            write(output, root.getRight());  
        }
    }

    // post: asks the user a series of yes/no questions to guess an object,
    //       adding the obect to the tree if it is not there already
    public void askQuestions() {
        overallRoot = askQuestions(overallRoot);
    }

    // post: asks the user a series of yes/no questions to guess an object,
    //       adding the obect to the tree if it is not there already
    private QuestionNode askQuestions(QuestionNode root) {
        if (root.isLeaf()) {
            root = processLeaf(root);
        } else if (yesTo(root.text)) {
            root.left = askQuestions(root.left);
        } else {
            root.right = askQuestions(root.right);
        }
        return root;
    }

    // pre : root is a leaf node of the tree

    // post: either wins the game by correctly guessing the user's object or
    //       adds the object to the tree, returning a new subtree with an extra
    //       question and the new object
    private QuestionNode processLeaf(QuestionNode root) {
        if (yesTo("Would your object happen to be " + root.text + "?")) {
            System.out.println("Great, I got it right!");
        }
        else {
            
            System.out.print("What is the name of your object? ");
            String name = console.nextLine();
            System.out.println("Please give me a yes/no question that");
            System.out.println("distinguishes between your object");
            System.out.print("and mine--> ");
            String question = console.nextLine();
            if (yesTo("And what is the answer for your object?")) {
                root = new QuestionNode(question, new QuestionNode(name), root);
            } else {
                root = new QuestionNode(question, root, new QuestionNode(name));
            }
        }
        return root;
    }

    // post: asks the user a question, forcing an answer of "y" or "n";
    //       returns true if the answer was yes, returns false otherwise
    public boolean yesTo(String prompt) {
        System.out.print(prompt + " (y/n)? ");
        String response = console.nextLine().trim().toLowerCase();
        while (!response.equals("y") && !response.equals("n")) {
            System.out.println("Please answer y or n.");
            System.out.print(prompt + " (y/n)? ");
            response = console.nextLine().trim().toLowerCase();
        }
        return response.equals("y");
    }

    // post: prints the tree contents, one per line, following an
    //       inorder traversal and using indentation to indicate
    //       node depth; prints right to left so that it looks
    //       correct when the output is rotated.
    public void printSideways() {
        printSideways(overallRoot, 0);
    }

    // post: prints in reversed preorder the tree with given
    //       root, indenting each line to the given level
    private void printSideways(QuestionNode root, int level) {
        if (root != null) {
            printSideways(root.right, level + 1);
            for (int i = 0; i < level; i++) {
                System.out.print("    ");
            }
            System.out.println(root.text);
            printSideways(root.left, level + 1);
        }
    }
} 
