package src;

import java.util.Stack;

public class Interpreter {
    public static void main(String[] args) {
        Lexer lexer = new Lexer();
        Parser parser = new Parser();
        Executor exec = new Executor();

        String i = "+(+(1 29) 2)";

        Stack<Token> n = lexer.tokenize(i);

        SyntaxNode root = parser.parse(n);

        System.out.println(exec.execute(root));


    }
}
