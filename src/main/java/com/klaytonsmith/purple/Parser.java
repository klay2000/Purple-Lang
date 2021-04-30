package src.main.java.com.klaytonsmith.purple;

import java.util.Stack;

public class Parser {

    SyntaxNode parse(Stack<Token> stack){
        SyntaxNode root = new SyntaxNode(new Token("", 0, 0));

        parseSubtree(stack, root);

        return root;
    }

    private void parseSubtree(Stack<Token> stack, SyntaxNode root) {
        while (!stack.empty()) {
            switch (stack.peek().type) {
                case open:
                    stack.pop();
                    /* add an empty container node if there is no node to hold arguments or there is already
                       arguments in the previous node.
                     */
                    if(root.getChildren().size() == 0
                            || root.getChildren().get(root.getChildren().size()-1).getChildren().size() != 0)
                        root.addChild(new SyntaxNode(new Token("",
                                root.getToken().getLineNum(),
                                root.getToken().getCharNum())));

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
