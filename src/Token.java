package src;

public class Token{
    TokenType type;
    String tokenData;

    private final int lineNum;
    private final int charNum;

    Token(String tokenString, int lineNum, int charNum){
        this.lineNum = lineNum;
        this.charNum = charNum;

        switch(tokenString){
            case "(":
                type = TokenType.open;
                tokenData = "(";
                break;

            case ")":
                type = TokenType.close;
                tokenData = ")";
                break;

            default:
                if(tokenString.matches("-?\\d+")){
                    type = TokenType.literal;
                }
                else{
                    type = TokenType.function;
                }
                tokenData = tokenString;
        }
    }

    public int getLineNum(){
        return this.lineNum;
    }

    public int getCharNum(){
        return this.charNum;
    }

}
