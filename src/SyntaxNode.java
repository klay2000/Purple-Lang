package src;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SyntaxNode {
    private Token token;
    private ArrayList<SyntaxNode> children = new ArrayList<SyntaxNode>();

    public SyntaxNode(Token token, List<SyntaxNode> children){
        this.token = token;
        this.children = new ArrayList<SyntaxNode>(children);
    }

    public SyntaxNode(Token token){
        this.token = token;
        this.children = new ArrayList<SyntaxNode>();
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


}
