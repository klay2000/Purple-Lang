package src;

import java.util.Stack;

public class Parser {

    SyntaxNode parse(Stack<Token> stack){
        SyntaxNode root = new SyntaxNode(new Token(""));

        parseSubtree(stack, root);

        return root;
    }

    private void parseSubtree(Stack<Token> stack, SyntaxNode root) {
        while (!stack.empty()) {
            switch (stack.peek().type) {
                case open:
                    stack.pop();
                    parseSubtree(stack, root.getChildren().get(root.getChildren().size()-1));
                    break;

                case close:
                    stack.pop();
                    return;

                case literal:
                case function:
                    root.addChild(new SyntaxNode(stack.pop()));
                    break;
            }
        }
    }

}
