package BasicDataType;

public class BYTE extends Number{

    byte data;
    public BYTE(){
        this.data = 0;
        this.type = "byte";
    }
    public BYTE(String name){
        this.type = "byte";
        this.name = name;
        this.data = 0;
    }

    public BYTE(String name, DataTypeBase data){
        this.type = "byte";
        if(data instanceof STR) this.data = Byte.parseByte(((STR)data).getData());
        else if(data instanceof Number) this.data = (byte)((FLOAT)data).getData();
        else if(data instanceof INT) this.data = (byte)((INT)data).getData();
        this.name = name;
    }

    public BYTE(STR name, DataTypeBase data){
        this.type = "byte";
        if(data instanceof STR) this.data = Byte.parseByte(((STR)data).getData());
        else if(data instanceof Number) this.data = (byte)((FLOAT)data).getData();
        else if(data instanceof INT) this.data = (byte)((INT)data).getData();
        this.name = name.getData();
    }

    public BYTE(String name, double data) {
        this.type = "byte";
        this.name = name;
        this.data = (byte)data;
    }

    public double getData() {
        return data;
    }

    public String toString(){
        return ""+data;
    }
}
