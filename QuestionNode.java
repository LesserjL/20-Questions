// The QuestionNode class is used in conjunction with the QuestionTree.  Leaf
// nodes store the name of an object and branch nodes store a question.

public class QuestionNode {
    /**Create 3 instance variables to store the data and references to the left and right nodes. For this project you can make them public :) **/
    public String text;
    public QuestionNode left;
    public QuestionNode right;
    
    /**Create a Constructure with a single parameter for the data that sets the left and right nodes to null**/
    public QuestionNode(String text){
        this(d,null,null);
    }
    
    /**Create another constructor that takes data (text) and left/right references**/
    public QuestionNode(String text, QuestionNode left, QuestionNode right){
        this.text = text;
        this.left = left;
        this.right = right;
    }

    public String getText(){
        return text;
    }
    /** post: returns whether or not a node is a leaf node**/
    public boolean isLeaf() {
        return left == null && right == null; /**Update this method to test whether or not this node is a left node**/
    }
    public QuestionNode getLeft()
    {
        return left;
    }

    public QuestionNode getRight()
    {
        return right;
    }
}
