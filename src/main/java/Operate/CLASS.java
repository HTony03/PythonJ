package Operate;

import BasicDataType.DataTypeBase;
import BasicDataType.FUNCTION;
import BasicDataType.INT;
import BasicDataType.STR;
import Utils.Code;
import Utils.Data;
import Utils.IDcontroller;
import VirtualMachine.ByLineInterpreter;
import VirtualMachine.Namespace;

import static Utils.Utils.parameterMatching;
import static Utils.Utils.parenthesisMatching;

public class CLASS extends OperatorBase{
    public static BasicDataType.CLASS CLASS(String line, Code codeSpace, IDcontroller id, Namespace namespace, Namespace root, int size){
        line = format(line);
        if(!match(line, "class")){
            //处理异常
            return null;
        }
        BasicDataType.CLASS cls;
        Code code = codeSpace.subCodeSpace();
        String linex = line.substring(6);
        if(line.contains("(")) {
            Data data = parenthesisMatching(linex);
            String inside = ((STR) data.get(0)).getData();
            int front = ((INT) data.get(1)).getData();
            cls = new BasicDataType.CLASS(linex.substring(0,front), id.getNewID(), namespace, root, size, code);
            cls.setSuperClassName(inside);
        }else {
            cls = new BasicDataType.CLASS(linex.replaceAll(":",""), id.getNewID(), namespace, root, size, code);
        }

        return cls;
    }
}
