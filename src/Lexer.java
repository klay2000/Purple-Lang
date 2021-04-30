package src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Lexer {

    private Stack<String> split(String text){

        Stack<String> tokenStack = new Stack<String>();

        ArrayList<String> strings = new ArrayList<String>(
                Arrays.asList(text.split("\\s+|(?=\\))|(?<=\\()|(?<=\\))|(?=\\()")));

        while(strings.contains("")){
            strings.remove("");
        }

        tokenStack.addAll(strings);

        return tokenStack;

    }

    public Stack<Token> tokenize(String text){

        Stack<String> tokenStrings = split(text);

        Stack<Token> tokens = new Stack<Token>();

        int charNum = 0;
        int lineNum = 0;

        while(!tokenStrings.isEmpty()){
            String i = tokenStrings.pop();
            tokens.add(new Token(i, charNum, lineNum));

            if(i.contains("\n")){
                charNum = 0;
                lineNum++;
            }
            else charNum += i.length();

        }

        return tokens;

    }

}
