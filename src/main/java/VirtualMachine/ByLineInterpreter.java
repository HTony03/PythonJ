package VirtualMachine;

import BasicDataType.FUNCTION;
import BasicDataType.STR;
import Utils.Code;
import Utils.Data;

public class ByLineInterpreter {
    public Data interpret(String line){
        return Data.NULL;
    }

    public Data getReturns(FUNCTION func, Data attributes){
        Namespace namespace = func.namespace();
        Code code = func.code();
        while(func.returns() == null){
            interpret(((STR)code.nextLine().get(0)).getData());
        }
        return func.returns();
    }
}
