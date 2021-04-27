package src;

import java.util.Scanner;
import java.util.Stack;

public class Interpreter {
    public static void main(String[] args) {
        Lexer lexer = new Lexer();
        Parser parser = new Parser();
        Executor exec = Executor.getInstance();

        Scanner scanner = new Scanner(System.in);

        while(true) {

            System.out.print(">>");

            String i = scanner.nextLine();

            Stack<Token> n = lexer.tokenize(i);

            SyntaxNode root = parser.parse(n);

            System.out.println(exec.execute(root));
        }

    }
}
