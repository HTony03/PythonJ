package Calculate;

import BasicDataType.*;
import Utils.Data;
import Utils.Utils;
import VirtualMachine.Namespace;

import java.util.ArrayList;
import java.util.regex.Pattern;

import static Utils.Utils.parenthesisCounting;
import static Utils.Utils.parenthesisMatching;

public class GeneralCalculator extends Calculator{

    Namespace namespace;
    public GeneralCalculator(Namespace namespace){
        this.namespace = namespace;
    }

    @Override
    public Data calculate(Data data) {
        BasicCalculator bc = new BasicCalculator();
        if(data == Data.NULL || !parenthesisCounting(data)){
            // 处理异常
            return Data.NULL;
        }

        while(!parenthesisMatching(data).equals(Data.NULL)){
            if(data.size() > 1 && ((INT)data.get(1)).getData() == -1){
                break;
            }
            if(((INT)parenthesisMatching(data).get(1)).getData() == -1){
                break;
            }

            STR func = (STR)data.get(0);
            String expression = func.getData();
            Data subdata = calculate(parenthesisMatching(data));
            DataTypeBase dtb = subdata.get(0);
            if(dtb instanceof STR){
                data.setData(0,new STR(expression.substring(0,((INT)subdata.get(1)).getData()) + ((STR)subdata.get(0)).getData() + expression.substring(((INT)subdata.get(2)).getData()+1,expression.length())));
            }else if (dtb instanceof FLOAT){
                data.setData(0,new STR(expression.substring(0,((INT)subdata.get(1)).getData()) + ((FLOAT)subdata.get(0)).getData() + expression.substring(((INT)subdata.get(2)).getData()+1,expression.length())));
            }
        }
        return bc.calculate(data);
    }

    public Data assigment(String lines){
        ArrayList<String> methodList = new ArrayList<>();
        Utils.generateMethodList(namespace.root(),methodList,"",false);
        ArrayList<String> varList = new ArrayList<>();
        Utils.generateVarList(namespace.root(),methodList,"");
        Pattern pattern;
        for(String method: methodList){
            pattern = Pattern.compile(method);
            FUNCTION func = namespace.reachFunction(method);
        }
    }

    public void bubbleSorting(ArrayList<String> list){
        for(int i = 0; i < list.size(); i++){
            for(int j = i+1; j < list.size(); j++){
                if(list.get(i).compareTo(list.get(j)) > 0){
                    String temp = list.get(i);
                    list.set(i,list.get(j));
                    list.set(j,temp);
                }
            }
        }
    }
}
