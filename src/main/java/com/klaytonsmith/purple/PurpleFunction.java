package src.main.java.com.klaytonsmith.purple;

import java.util.ArrayList;
import java.util.List;

public class PurpleFunction {
    SyntaxNode rootNode;
    String name;
    List<SyntaxNode> args;
    int depth;

    public PurpleFunction(SyntaxNode rootNode, String name, List<SyntaxNode> args, int depth){
        this.rootNode = rootNode;
        this.name = name;
        this.args = args;
        this.depth = depth;
    }

    public PurpleFunction(String value, String name, int depth){
        this(new SyntaxNode(new Token(value, -1, -1)), name, new ArrayList<>(), depth);
    }
}
