package src.main.java.com.klaytonsmith.purple;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.Stack;

public class Interpreter {
    public static void main(String[] args) {
        Lexer lexer = new Lexer();
        Parser parser = new Parser();
        Executor exec = Executor.getInstance();

        Scanner scanner = new Scanner(System.in);


        try {

            if (args.length > 0) {
                String program = Files.readString(Path.of(args[0]));

                System.out.println("running " + args[0] + "...");

                exec.execute(parser.parse(lexer.tokenize(program)));
            }
        }catch (Exception e){System.out.println("File " + args[0] + "not found!");}

        while(true) {

            System.out.print(">>");

            String i = scanner.nextLine();

            Stack<Token> n = lexer.tokenize(i);

            SyntaxNode root = parser.parse(n);

            System.out.println(exec.execute(root));
        }

    }
}
