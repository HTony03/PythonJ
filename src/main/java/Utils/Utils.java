package Utils;

import BasicDataType.*;
import VirtualMachine.Namespace;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static Data parenthesisMatching(Data data){
        STR func = (STR)data.get(0);
        String expression = func.getData();
        return parenthesisMatching(expression);
    }

    public static Data parenthesisMatching(String expression){
        Data data = new Data();
        int level = 0 ,front = -1,back = -1;
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '('  || expression.charAt(i) == '[') {
                if (level == 0) {
                    front = i;
                }
                level++;
            } else if (expression.charAt(i) == ')'  || expression.charAt(i) == ']') {
                level--;
                if (level == 0) {
                    back = i;
                    break;
                }
            }
        }

        if (front != -1 && back != -1) {
            STR result = new STR(expression.substring(front + 1, back));
            data = new Data();
            data.add(result);
            data.add(new INT(front));
            data.add(new INT(back));
            return data;
        }
        data = new Data();
        data.add(new STR(expression));
        data.add(new INT(-1));
        return data;
    }

    public static boolean parenthesisCounting(Data data){
        STR func = (STR)data.get(0);
        String expression = func.getData();
        int level = 0;
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '(') {
                level++;
            } else if (expression.charAt(i) == ')') {
                level--;
            }
        }
        return level == 0;
    }

    public static Data variableMatching(Namespace namespace, Data data){
        STR expression = (STR)data.get(0);
        String code = expression.getData();
        //pattern = Pattern.compile();
        return null;
    }

    public static void generateMethodList(Namespace root, ArrayList<String> resultList, String prefix,boolean onClass){
        if (root == null){
            return;
        }
        for(DataTypeBase dtb : root.getVariables()){
            if (dtb instanceof FUNCTION) {
                if(onClass) {
                    if(dtb.isStatic()) {
                        resultList.add(prefix + dtb.name());
                    }
                }else {
                    resultList.add(prefix + dtb.name());
                }
            }else if(dtb instanceof INSTANCE){
                String name = prefix + dtb.name();
                Namespace subNamespace = ((INSTANCE) dtb).getDynamicNamespace();
                generateMethodList(subNamespace, resultList, name + ".",false);
                Namespace subNamespace1 = ((INSTANCE) dtb).getCLASS().namespace();
                generateMethodList(subNamespace1, resultList, name + ".", false);
            }else if(dtb instanceof CLASS){
                Namespace subNamespace = ((CLASS) dtb).namespace();
                String name = prefix + dtb.name();
                generateMethodList(subNamespace, resultList, name + ".", true);
            }
        }
    }

    public static void generateVarList(Namespace root, ArrayList<String> resultList, String prefix){
        for(DataTypeBase dtb : root.getVariables()){
            if (!(dtb instanceof CLASS)){
                resultList.add(prefix+dtb.name());
            }else if(!(dtb instanceof FUNCTION)){
                Namespace subNamespace = ((CLASS) dtb).namespace();
                String name = prefix + dtb.name();
                generateVarList(subNamespace, resultList, name + ".");
            }
        }
    }

    public static void functionAssignment(String code, Namespace namespace){
        ArrayList<String> methodList =  new ArrayList<>();
        generateMethodList(namespace, methodList, "", false);
    }

    public static FUNCTION getFunct(String path, Namespace namespace){

        String[] paths = path.split("\\.");
        DataTypeBase temp;
        Namespace tempSpace = namespace;
        for (String s : paths) {
            temp = tempSpace.getByName(s);
            if(temp instanceof FUNCTION){
                return (FUNCTION)temp;
            }
        }
        return null;
    }

    public static ArrayList<String> parameterMatching(String str){
        ArrayList<String> parameters = new ArrayList<>();
        if(!str.contains(",")){
            parameters.add(str);
            return parameters;
        }
        ArrayList<Integer> commas = new ArrayList<>();
        int level = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                level += 1;
            } else if (str.charAt(i) == ')') {
                level -= 1;
            } else if (str.charAt(i) == ',' && level == 0) {
                commas.add(i);
            }
        }
        parameters.add(str.substring(0, commas.get(0)).replaceAll(" ", ""));
        for (int i = 0; i<commas.size()-1; i++) {
            parameters.add(str.substring(commas.get(i)+1, commas.get(i+1)).replaceAll(" ", ""));
        }
        parameters.add(str.substring(commas.get(commas.size()-1)+1).replaceAll(" ", ""));
        return parameters;
    }
}
