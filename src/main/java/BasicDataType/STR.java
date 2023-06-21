package BasicDataType;

import java.util.ArrayList;

public class STR extends LIST
{
    private String data = "";
    public STR(){
        this.type = "str";
    }

    public STR(String data){
        this.type = "str";
        this.data = data;
    }

    public String getData(){
        return data;
    }

    public String getSubSequence(String code){
        int[] index = SliceUp(code);
        String result = "";
        if(index.length == 2){
            for(int i = index[0]; i <= index[1]; i++){
                result = result + data.charAt(i);
            }
        } else if (index.length == 3){
            for(int i = index[0]; i < index[1]; i+=index[2]){
                result = result + data.charAt(i);
            }
        }
        return result;
    }

    public char getChar(String code){
        int[] index = SliceUp(code);
        if(index.length == 1){
            return data.charAt(index[0]);
        } else {
            return ' ';
        }
    }

    public void append(STR var){
        data = data + var.getData();
    }

    public boolean contains(String var){
        return data.contains(var);
    }

    public String toString(){
        return data;
    }
}
