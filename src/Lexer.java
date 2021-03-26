package src;

import java.util.Arrays;
import java.util.Stack;

public class Lexer {

    public Stack<String> split(String text){

        Stack<String> tokenStack = new Stack<>();

        tokenStack.addAll(Arrays.asList(text.split("\\s+|(?=\\))|(?<=\\()")));

        return tokenStack;

    }

    public Stack<Token> tokenize(Stack<String> tokenStrings){
        return new Stack<Token>();
    }

}
