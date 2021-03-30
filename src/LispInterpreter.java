package src;

import java.util.Stack;

public class LispInterpreter {
    public static void main(String[] args) {
        Lexer lexer = new Lexer();

        String i = "ab(+(+(1 29) -(3 *(00 4))))";

        Stack<Token> n = lexer.tokenize(i);

        while(true){
            System.out.println(n.pop());
        }
    }
}
