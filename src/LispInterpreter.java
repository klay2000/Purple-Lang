package src;

public class LispInterpreter {
    public static void main(String[] args) {
        Lexer lexer = new Lexer();

        String[] i = "'(+ (+ 1 29) zb(- 3 (* 00 4)))".split("\\s+|(?=\\))|(?<=\\()");

        for(String n : i) System.out.println(n);
    }
}
