package src;

public class Token{
    TokenType type;
    String tokenData;

    Token(String tokenString){
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
                if(tokenString.matches("\\d+")){
                    type = TokenType.literal;
                }
                else{
                    type = TokenType.function;
                }
                tokenData = tokenString;
        }
    }

}
