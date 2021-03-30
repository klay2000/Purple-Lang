package src;

import java.util.Stack;

public class Parser {

    SyntaxNode parse(Stack<Token> stack){
        SyntaxNode root = new SyntaxNode(new Token(""));

        parseSubtree(stack, root);

        return root;
    }

    private void parseSubtree(Stack<Token> stack, SyntaxNode root){
        if()
    }

}
