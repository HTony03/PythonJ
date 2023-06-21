package BasicDataType;

public abstract class DataTypeBase implements Cloneable{

    //变量类型和变量名
    String type, name;

    private boolean STATIC, ABSTRACT, FINAL;
    Object data;

    public String name(){return name;}
    public String getType(){return type;}

    public static final DataTypeBase NULL = null;

    public boolean equals(DataTypeBase a){
        return this.getClass() == a.getClass();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String toString(){
        return null;
    }

    public boolean isStatic() {
        return STATIC;
    }

    public boolean isFinal() {
        return FINAL;
    }

    public boolean isAbstract() {
        return ABSTRACT;
    }

    public void setStatic(boolean STATIC) {
        this.STATIC = STATIC;
    }

    public void setFinal(boolean FINAL) {
        this.FINAL = FINAL;
    }

    public void setAbstract(boolean ABSTRACT) {
        this.ABSTRACT = ABSTRACT;
    }

    public void print(){}
}
