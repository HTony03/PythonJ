package Utils;

import BasicDataType.INT;
import BasicDataType.STR;

import java.util.ArrayList;

public class Code {
    private String[] lines;

    private ArrayList<Integer> marks = new ArrayList<>();
    private int pointer = 0;
    public Code(String[] lines){
        this.lines = lines;
    }

    public Code(String rawCode){
        String[] rawCodes = rawCode.split("\n");
        ArrayList<String> code = new ArrayList<>();
        for(String s:rawCodes){
            if(!s.replaceAll(" ","").equals("")){
                code.add(s);
            }
        }
        lines = code.toArray(new String[0]);
    }

    public Data nextLine(){
        if(pointer < lines.length){
            pointer++;
            return new Data(){{
                add(new STR(removeTab(lines[pointer])));
                add(new INT(countTab(lines[pointer])));
            }};

        }else{
            return null;
        }
    }

    public ArrayList<Integer> marks(){
        return marks;
    }

    public ArrayList<Integer> markAllDef(){
        ArrayList<Integer> result = new ArrayList<>();
        for(int i = 0; i < lines.length; i++){
            if(lines[i].contains("def")){
                result.add(i);
            }
        }
        return result;
    }

    public ArrayList<Integer> markAllClass(){
        ArrayList<Integer> result = new ArrayList<>();
        for(int i = 0; i < lines.length; i++){
            if(lines[i].contains("class")){
                result.add(i);
            }
        }
        return result;
    }

    public void makeZero(){
        pointer = 0;
    }

    public void skip(int num){
        pointer += num;
    }

    public int length(){
        return lines.length;
    }

    public String getLine(int point){
        return lines[point];
    }

    public boolean hasNextLine(){
        return pointer < lines.length;
    }

    public int countTab(String singleLine){
        String regex = singleLine;
        int count = 0;
        while (regex.startsWith("\t") || regex.startsWith("    ")) {
            if(regex.startsWith("\t")){
                regex = regex.substring(1);
            }else{
                regex = regex.substring(4);
            }
            count++;
        }
        return count;
    }

    public String removeTab(String singleLine){
        while (singleLine.charAt(0) == '\t' || singleLine.startsWith("    ")) {
            singleLine = singleLine.substring(1);
        }
        return singleLine;
    }

    public Data GOTO(int point){
        pointer = point-1;
        return nextLine();
    }

    public Code subCodeSpace(){
        int p = pointer;
        int base = countTab(getLine(p));
        p++;
        ArrayList<String> result = new ArrayList<>();
        for(;p<lines.length;p++){
            if(countTab(getLine(p)) <= base){
                break;
            }
            result.add(getLine(p));
        }
        return new Code(result.toArray(new String[0]));
    }

    public ArrayList<String> getRawCode(){
        ArrayList<String> result = new ArrayList<>();
        for(String i:lines){
            result.add(i);
        }
        return result;
    }

    public String getPriviousLine(){
        if(pointer <= 0){
            pointer = 0;
            return "";
        }
        String result = lines[pointer-1];
        while (result.charAt(0) == '\t' || result.charAt(0) == ' ') {
            result = result.substring(1);
        }
        while (result.charAt(result.length()-1) == '\n' || result.charAt(result.length()-1) == ' ') {
            result = result.substring(0,result.length()-1);
        }
        return result;
    }

    public String toString(){
        String result = "";
        for(String i:lines){
            result += i + "\n";
        }
        return result;
    }

    public void print(){
        for(String i:lines){
            System.out.println(i);
        }
    }

    public void print(int level){
        for(String i:lines){
            blanks(level);
            System.out.println(i);
        }
    }

    public void blanks(int level){
        for(int i = 0; i < level; i++){
            System.out.print("    ");
        }
    }
}
