package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

public class Executor {

    private HashMap<String, Function<List<String>, String>> primitiveFns = new HashMap();

    private ArrayList<PurpleFunction> functions = new ArrayList<>();

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
        primitiveFns.put("", (List<String> args) -> {
            if(args.size() == 1) return args.get(0);

            String output = "";

            for(String i : args){
                output += i + ", ";
            }

            return output;
        });
        primitiveFns.put("deref", (List<String> args) -> execute(SyntaxNode.getNodeByID(Integer.decode(args.get(0)))));
        primitiveFns.put("print", (List<String> args) -> {args.forEach((String i) -> {
            System.out.println(i);
        });
        return "";
        });
    }

    public String execute(SyntaxNode root){



        Token token = root.getToken();

        switch (token.type){
            case function:
                if(token.tokenData.equals("ref")) return Integer.toString(root.getChildren().get(0).getId());

                ArrayList<String> args = new ArrayList();
                for(SyntaxNode i : root.getChildren()){
                    args.add(execute(i));
                }

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
