package src.main.java.com.klaytonsmith.purple;

import java.util.ArrayList;
import java.util.List;

public class SyntaxNode {
    private static ArrayList<SyntaxNode> nodes = new ArrayList<>();
    private static int highestID = 0;

    public static SyntaxNode getNodeByID(int id){
        return nodes.get(id);
    }

    private Token token;
    private ArrayList<SyntaxNode> children = new ArrayList<SyntaxNode>();
    private int id;

    public SyntaxNode(Token token, List<SyntaxNode> children){
        this.token = token;
        this.children = new ArrayList<SyntaxNode>(children);
        this.id = highestID++;
        nodes.add(this);
    }

    public SyntaxNode(Token token){
        this.token = token;
        this.children = new ArrayList<SyntaxNode>();
        this.id = highestID++;
        nodes.add(this);
    }

    public Token getToken() {
        return token;
    }

    public ArrayList<SyntaxNode> getChildren() {
        return children;
    }

    public void addChild(SyntaxNode child){
        children.add(child);
    }

    public int getId(){
        return id;
    }

    public void printTree(){
        System.out.println(this.token.tokenData);
        for( SyntaxNode i : children){
            i.printTree();
        }
    }

}
