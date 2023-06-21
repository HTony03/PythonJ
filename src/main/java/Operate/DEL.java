package Operate;

import VirtualMachine.Namespace;

public class DEL extends OperatorBase{

    public static void DEL(String line, Namespace namespace){
        line = format(line);
        if(!match2(line, "del")) return;
        line = line.substring(4).replaceAll(" ","");
        String[] vars = line.split(",");
        for (String var:vars){
            if(var.contains("[")){

            }else {
                namespace.remove(var);
            }
        }
    }
}
