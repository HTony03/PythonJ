package BasicDataType;

import Utils.Code;
import Utils.Data;
import VirtualMachine.Namespace;

import java.util.ArrayList;
import java.util.Objects;

public class FUNCTION extends CLASS
{
    ArrayList<String> parametersString = new ArrayList<>();
    Data returns;

    public FUNCTION(){
        this.type = "function";
    }

    public FUNCTION(String name){
        this.type = "function";
        this.name = name;
    }

    public FUNCTION(STR name){
        this.type = "function";
        this.name = name.getData();
    }

    public FUNCTION(STR name, Code body){
        this.type = "function";
        this.name = name.getData();
        this.code = body;
    }

    public FUNCTION(String name, Code body, ArrayList<String> parameters){
        this.type = "function";
        this.name = name;
        this.code = body;
        this.parametersString = parameters;
    }

    public  <T extends DataTypeBase> T assigment(T[] vars){
        if(vars.length != parametersString.size()){
            // 处理异常
            return null;
        }
        int i = 0;
        for(T var: vars){
            try {
                T varx = (T) ((DataTypeBase) var).clone();
                ((DataTypeBase)varx).name = parametersString.get(i);
                namespace().add(varx);
                i++;
            } catch (Exception e){
                //处理异常
                return null;
            }
        }
        return null;
    }

    public Data returns(){
        return returns;
    }

    public void print(){
        if(isAbstract()){
            System.out.println("@abstract");
        }
        if(isStatic()){
            System.out.println("@static");
        }
        System.out.println("function_name: " + name);
        System.out.println("parameters: " + parametersString);
        System.out.println("code: ");
        code.print();
    }

    public void print(int level){
        if(isAbstract()){
            blanks(level);
            System.out.println("@abstract");
        }
        if(isStatic()){
            blanks(level);
            System.out.println("@static");
        }
        blanks(level);
        System.out.println("function_name: " + name);
        blanks(level);
        System.out.println("parameters: " + parametersString);
        blanks(level);
        System.out.println("code: ");
        code.print(level);
    }

    public void blanks(int level){
        for(int i = 0; i < level; i++){
            System.out.print("    ");
        }
    }
}
