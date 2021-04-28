package src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

public class Executor {

    private HashMap<String, Function<List<String>, String>> primitiveFns = new HashMap();

    private static Executor instance;

    private FunctionStackManager SM = new FunctionStackManager();

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

            String output = "(";

            for(String i : args){
                output += i + ", ";
            }
            output += ")";

            return output;
        });
        primitiveFns.put("deref", (List<String> args) -> execute(SyntaxNode.getNodeByID(Integer.decode(args.get(0)))));
        primitiveFns.put("print", (List<String> args) -> {args.forEach((String i) -> {
                System.out.println(i);
            });
            return "";
        });
    }

    public String execute(SyntaxNode root, int depth){
        depth += 1;

        Token token = root.getToken();

        switch (token.type){
            case function:
                if(token.tokenData.equals("ref")) return Integer.toString(root.getChildren().get(0).getId());
                if(token.tokenData.equals("=")){

                    ArrayList<SyntaxNode> args = new ArrayList<>();

                    for(SyntaxNode i : root.getChildren().get(0).getChildren()){
                        args.add(i);
                    }

                    PurpleFunction fun = new PurpleFunction(root.getChildren().get(root.getChildren().size()-1),
                            root.getChildren().get(0).getToken().tokenData,
                            args,
                            depth-1);

                    SM.addFunction(fun);

                    return "";
                }

                ArrayList<String> args = new ArrayList();
                for(SyntaxNode i : root.getChildren()){
                    args.add(execute(i, depth));
                }

                // if it's a built in function run it
                if(isPrimitiveFn(token.tokenData)) return runPrimitiveFn(token.tokenData, args);

                // otherwise get it, set it's variables, then run it.
                PurpleFunction fun = SM.getFunction(token.tokenData, depth);
                for(int i = 0; i < fun.args.size(); i++){
                    SM.addFunction(new PurpleFunction(args.get(i), fun.args.get(i).getToken().tokenData, depth));
                }

                return execute(fun.rootNode, depth);

            case literal:
                return token.tokenData;
        }

        return "";
    }

    public String execute(SyntaxNode root){
        return execute(root, -1);
    }

    private String runPrimitiveFn(String tokenData, ArrayList<String> args) {
        return primitiveFns.get(tokenData).apply(args);
    }

    private boolean isPrimitiveFn(String tokenData) {
        return primitiveFns.containsKey(tokenData);
    }
}
