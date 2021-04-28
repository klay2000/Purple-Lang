package src;

import java.util.ArrayList;

public class FunctionStackManager {
    private ArrayList<PurpleFunction> funStack = new ArrayList<>();

    public void addFunction(PurpleFunction function){
        funStack.add(function);
    }

    public PurpleFunction getFunction(String name, int depth){

        // iterate through functions backwards
        for(int i = funStack.size()-1; i >= 0; i--){
            PurpleFunction f = funStack.get(i);
            // remove functions from deeper in the stack
            if(f.depth > depth) funStack.remove(f);
            // return if the name is correct
            else if(f.name.equals(name)) return f;
        }

        return null;
    }
}
