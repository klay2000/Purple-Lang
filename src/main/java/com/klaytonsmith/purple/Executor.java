package src.main.java.com.klaytonsmith.purple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiFunction;

public class Executor {

    private HashMap<String, BiFunction<List<SyntaxNode>, Integer, String>> primitiveFns = new HashMap();

    private static Executor instance;

    private FunctionStackManager SM = new FunctionStackManager();

    public static Executor getInstance() {
        if(instance == null) instance = new Executor();
        return instance;
    }

    private Executor(){
        primitiveFns.put("+", (List<SyntaxNode> args, Integer depth) -> {
            int sum = 0;
            for(SyntaxNode i : args) {
                sum += Integer.decode(execute(i, depth));
            }
            return Integer.toString(sum);
        });
        primitiveFns.put("", (List<SyntaxNode> args, Integer depth) -> {
            if(args.size() == 1) return execute(args.get(0));

            String output = "(";

            for(SyntaxNode i : args){
                output += execute(i) + ", ";
            }
            output += ")";

            return output;
        });
        primitiveFns.put("ref", (List<SyntaxNode> args, Integer depth) -> Integer.toString(args.get(0).getId()));
        primitiveFns.put("deref", (List<SyntaxNode> args, Integer depth) -> execute(SyntaxNode.getNodeByID(Integer.decode(execute(args.get(0))))));
        primitiveFns.put("print", (List<SyntaxNode> args, Integer depth) -> {args.forEach((SyntaxNode i) -> {
                System.out.println(execute(i, depth));
            });
            return "";
        });
        primitiveFns.put("=", (List<SyntaxNode> args, Integer depth) -> registerFunction(args, depth));
        primitiveFns.put("==", (List<SyntaxNode> args, Integer depth) -> {
            boolean out = true;
            String eq = execute(args.get(0), depth);

            for(SyntaxNode i : args){
                out = out && execute(i, depth).equals(eq);
            }

            return Boolean.toString(out);
        });
        primitiveFns.put("if", (List<SyntaxNode> args, Integer depth) -> {
            if(execute(args.get(0), depth).equals("true")){
                return execute(args.get(1), depth);
            }else if(args.size() > 2)return execute(args.get(2), depth);
            else return "";
        });
    }

    public String execute(SyntaxNode root, int depth){
        depth += 1;

        Token token = root.getToken();

        switch (token.type){
            case function:
                if(token.tokenData.equals("=")){


                }

                ArrayList<SyntaxNode> args = root.getChildren();

                // if it's a built in function run it
                if(isPrimitiveFn(token.tokenData)) return runPrimitiveFn(token.tokenData, args, depth);

                // otherwise get it, set it's variables, then run it.
                PurpleFunction fun = SM.getFunction(token.tokenData, depth);
                for(int i = 0; i < fun.args.size(); i++){
                    SM.addFunction(new PurpleFunction(execute(args.get(i), depth), fun.args.get(i).getToken().tokenData, depth));
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

    private String runPrimitiveFn(String tokenData, ArrayList<SyntaxNode> args, int depth) {
        return primitiveFns.get(tokenData).apply(args, depth);
    }

    private boolean isPrimitiveFn(String tokenData) {
        return primitiveFns.containsKey(tokenData);
    }

    private String registerFunction(List<SyntaxNode> args, int depth){
        ArrayList<SyntaxNode> fnArgs = new ArrayList<>();

        for(SyntaxNode i : args.get(0).getChildren()){
            fnArgs.add(i);
        }

        PurpleFunction fun = new PurpleFunction(args.get(args.size()-1),
                args.get(0).getToken().tokenData,
                fnArgs,
                depth-1);

        SM.addFunction(fun);

        return "";
    }

}
