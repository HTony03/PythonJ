package BasicDataType;

public class FLOAT extends Number{
    Double data;
    public FLOAT(){
        this.type = "float";
        this.data = 0.0;
    }

    public FLOAT(Double data){
        this.type = "float";
        this.data = data;
    }

    public FLOAT(STR data){
        this.type = "float";
        this.data = Double.parseDouble(data.getData());
    }

    public FLOAT(String data){
        this.type = "float";
        this.data = Double.parseDouble(data);
    }

    public FLOAT(String name, double data) {
        this.type = "float";
        this.data = data;
    }

    public double getData(){
        return data;
    }

    public String toString(){
        return data.toString();
    }
}
