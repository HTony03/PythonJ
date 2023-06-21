package Operate;

public abstract class OperatorBase {

    public static boolean match(String line, String key){
        if(!line.endsWith(":")){return false;}
        if(!line.startsWith(key + " ")){return false;}
        return true;
    }

    public static boolean match2(String line, String key){
        if(!line.startsWith(key + " ")){return false;}
        return true;
    }

    public static String format(String line){
        while(line.contains("  ")){line = line.replaceAll("  ", " ");}
        while(line.startsWith(" ")){line = line.substring(1);}
        while(line.charAt(line.length()-1) == ' '){line = line.substring(0,line.length()-1);}
        return line;
    }
}
