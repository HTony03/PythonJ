package Operate;

import BasicDataType.FUNCTION;
import BasicDataType.INT;
import BasicDataType.STR;
import Utils.Code;
import Utils.Data;
import VirtualMachine.ByLineInterpreter;

import java.util.ArrayList;

import static Utils.Utils.parameterMatching;
import static Utils.Utils.parenthesisMatching;

public class DEF extends OperatorBase {
    public static FUNCTION DEFINE(String line, Code codeSpace){
        line = format(line);
        if(!match(line, "def")){
            // 处理异常
            return null;
        }
        String linex = line.substring(4);
        Data data = parenthesisMatching(linex);
        String inside = ((STR)data.get(0)).getData();
        int front = ((INT)data.get(1)).getData();
        Code code = codeSpace.subCodeSpace();
        FUNCTION result = new FUNCTION(linex.substring(0,front), code, parameterMatching(inside));
        if(codeSpace.getPriviousLine().equals("@staticmethod")){result.setStatic(true);}
        return result;
    }
}
