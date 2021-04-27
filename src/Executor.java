package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

public class Executor {

    private HashMap<String, Function<List<String>, String>> primitiveFns = new HashMap();

    private static Executor instance;

    public static Executor getInstance() {
        if(instance == null) instance = new Executor();
        return instance;
    }

    private Executor(){
        primitiveFns.put("+", (List<String> args) -> {
            int sum = 0;
            for(String i : args) {
                sum += Integer.decode(i);
            }
            return Integer.toString(sum);
        });
        primitiveFns.put("", (List<String> args) -> args.get(0));
    }

    public String execute(SyntaxNode root){

        ArrayList<String> args = new ArrayList();
        for(SyntaxNode i : root.getChildren()){
            args.add(execute(i));
        }

        Token token = root.getToken();

        switch (token.type){
            case function:
                if(isPrimitiveFn(token.tokenData)) return runPrimitiveFn(token.tokenData, args);
                break;

            case literal:
                return token.tokenData;
        }

        return "";
    }

    private String runPrimitiveFn(String tokenData, ArrayList<String> args) {
        return primitiveFns.get(tokenData).apply(args);
    }

    private boolean isPrimitiveFn(String tokenData) {
        return primitiveFns.containsKey(tokenData);
    }
}
