package BasicDataType;

public class INT extends Number{
    int data;
    public INT(){
        this.type = "int";
        this.data = 0;
    }

    public INT(int data){
        this.type = "int";
        this.data = data;
    }

    public INT(STR data){
        this.type = "int";
        this.data = Integer.parseInt(data.getData());
    }

    public INT(FLOAT data){
        this.type = "int";
        this.data = (int)data.getData();
    }

    public INT(String data){
        this.type = "int";
        this.data = Integer.parseInt(data);
    }

    public INT(String name, double data) {
        this.type = "int";
        this.data = (int)data;
    }

    public int getData() {
        return data;
    }

    public String toString(){
        return String.valueOf(data);
    }
}
