package src;

import java.util.Stack;

public class Parser {

    SyntaxNode parse(Stack<Token> stack){
        SyntaxNode root = new SyntaxNode(new Token(""));

        int parCount = 0;

        Token token = stack.pop();

        switch (token.type){
            case open:
                parCount++;
            break;

            case close:
                parCount--;
            break;
        }

        return root;
    }

    private void parseSubtree(Stack<Token> stack){

    }

}
