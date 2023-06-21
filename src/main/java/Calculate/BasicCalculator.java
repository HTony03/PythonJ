package Calculate;

import BasicDataType.FLOAT;
import BasicDataType.STR;
import Utils.Data;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BasicCalculator extends Calculator{
    public final static Pattern NUMBER = Pattern.compile("(-?\\d+)(\\.\\d+)?|0+(\\.0+)?");
    @Override
    public Data calculate(Data data) {
        data = format(data);
        STR func = (STR)data.get(0);
        String expression = func.getData();

        String[] opGroup = NUMBER.split(expression);
        Matcher matcher = NUMBER.matcher(expression);
        String[] numGroup = new String[matcher.groupCount()];

        int i = 0;
        while (matcher.find()) {numGroup[i++] = matcher.group();}
        ArrayList<String> exp = new ArrayList<>();
        i = 0;
        for(;i<numGroup.length;){
            if(numGroup[i] != null) {
                exp.add(opGroup[i]);
                exp.add(numGroup[i]);
                exp.remove("");
            }
            i++;
        }

        while(exp.contains("%")){
            int index = exp.indexOf("%");
            double a = Double.parseDouble(exp.get(index-1));
            double b = Double.parseDouble(exp.get(index+1));
            exp.set(index-1, String.valueOf(a%b));
            exp.remove(index);
            exp.remove(index);
        }

        while(exp.contains("/")){
            int index = exp.indexOf("/");
            double a = Double.parseDouble(exp.get(index+1));
            exp.set(index+1, String.valueOf(1/a));
            exp.set(index, "*");
        }

        while(exp.contains("*")){
            int index = exp.indexOf("*");
            double a = Double.parseDouble(exp.get(index-1));
            double b = Double.parseDouble(exp.get(index+1));
            exp.set(index-1, String.valueOf(a*b));
            exp.remove(index);
            exp.remove(index);
        }

        while(exp.contains("+")){
            int index = exp.indexOf("+");
            double a = Double.parseDouble(exp.get(index-1));
            double b = Double.parseDouble(exp.get(index+1));
            exp.set(index-1, String.valueOf(a+b));
            exp.remove(index);
            exp.remove(index);
        }

        data.setData(0,new FLOAT(exp.get(0)));
        return data;
    }

    private static Data format(Data data){
        STR func = (STR)data.get(0);
        String expression = func.getData();
        if (expression.charAt(0) == '-'){
            expression = "0" + expression;
        }
        String lastExp = "";
        while (!lastExp.equals(expression)) {
            lastExp = expression;
            expression = expression.replaceAll(" ","").replaceAll("-\\+","+-").replaceAll("--","+").replaceAll("-","+-").replaceAll("\\+\\+","+");
        }
        data.setData(0, new STR(expression));
        return data;
    }


}
