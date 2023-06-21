package BasicDataType;

public class BOOL extends DataTypeBase{

    boolean data = false;
    public BOOL(){
        this.type = "bool";
    }

    public BOOL(String name){
        this.type = "bool";
        this.name = name;
    }

    public BOOL(String name, boolean data){
        this.type = "bool";
        this.name = name;
        this.data = data;
    }

    public boolean getData(){
        return data;
    }

    public void setData(boolean data){
        this.data = data;
    }

    public String toString(){
        if(data) {
            return "True";
        }
        return "False";
    }
}
