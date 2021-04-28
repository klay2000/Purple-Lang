package src;

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
}
