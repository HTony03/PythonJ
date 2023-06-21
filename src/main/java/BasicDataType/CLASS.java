package BasicDataType;

import Utils.Code;
import VirtualMachine.ByLineInterpreter;
import VirtualMachine.Namespace;

public class CLASS extends DataTypeBase
{
    //这个类的所有静态属性
    private Namespace namespace;

    Code code;

    private String superClassName;

    private CLASS superClass;

    public CLASS(){
        this.type = "class";
    }

    public CLASS(String name,long UUID, Namespace father, Namespace root, int size){
        this.type = "class";
        this.name = name;
        namespace = new Namespace(UUID, father, root, size);
    }

    public CLASS(String name,long UUID, Namespace father, Namespace root, int size, Code code){
        this.type = "class";
        this.name = name;
        namespace = new Namespace(UUID, father, root, size);
        this.code = code;
    }

    public CLASS(STR name,long UUID, Namespace father, Namespace root, int size){
        this.type = "class";
        this.name = name.getData();
        namespace = new Namespace(UUID, father, root, size);
    }

    public CLASS(STR name,long UUID, Namespace father, Namespace root, int size, Code code){
        this.type = "class";
        this.name = name.getData();
        namespace = new Namespace(UUID, father, root, size);
        this.code = code;
    }

    public Namespace namespace(){
        return namespace;
    }

    public Code code(){
        return code;
    }

    public void define(DataTypeBase var){
        if(namespace.contains(var.name())){
            if(!namespace.getByName(var.name()).equals(var)){
                try{
                    var = transform(var,namespace.getByName(var.name()));
                }catch (Exception e){
                    //处理异常
                }
            }
            namespace.remove(var.name());
        }
        namespace.add(var);
    }

    public <T extends DataTypeBase> T transform(DataTypeBase var1, DataTypeBase var2) throws Exception{
        if((var1 instanceof Number || var1 instanceof BOOL) && (var2 instanceof Number || var2 instanceof BOOL)){
            if(var2 instanceof FLOAT){
                if(var1 instanceof FLOAT) {
                    return (T) new FLOAT(var1.name(), ((FLOAT) var1).getData());
                }
                if(var1 instanceof INT) {
                    return (T) new FLOAT(var1.name(), ((INT) var1).getData());
                }
                if(var1 instanceof BYTE) {
                    return (T) new FLOAT(var1.name(), ((BYTE) var1).getData());
                }
                if(var1 instanceof BOOL) {
                    if(((BOOL)var1).getData()){
                        return (T) new FLOAT(var1.name(), 1);
                    }else {
                        return (T) new FLOAT(var1.name(), 0);
                    }
                }
            }
            if(var2 instanceof INT){
                if(var1 instanceof FLOAT) {
                    return (T) new INT(var1.name(), ((FLOAT) var1).getData());
                }
                if(var1 instanceof INT) {
                    return (T) new INT(var1.name(), ((INT) var1).getData());
                }
                if(var1 instanceof BYTE) {
                    return (T) new INT(var1.name(), ((BYTE) var1).getData());
                }
                if(var1 instanceof BOOL) {
                    if(((BOOL)var1).getData()){
                        return (T) new INT(var1.name(), 1);
                    }else {
                        return (T) new INT(var1.name(), 0);
                    }
                }
            }
            if(var2 instanceof BYTE){
                if(var1 instanceof FLOAT) {
                    return (T) new BYTE(var1.name(), ((FLOAT) var1).getData());
                }
                if(var1 instanceof INT) {
                    return (T) new INT(var1.name(), ((INT) var1).getData());
                }
                if(var1 instanceof BYTE) {
                    return (T) new INT(var1.name(), ((BYTE) var1).getData());
                }
                if(var1 instanceof BOOL) {
                    if(((BOOL)var1).getData()){
                        return (T) new BYTE(var1.name(), 1);
                    }else {
                        return (T) new BYTE(var1.name(), 0);
                    }
                }
            }
            if (var2 instanceof BOOL){
                if(var1 instanceof FLOAT) {
                    if(((FLOAT) var1).getData() > 0) {
                        return (T) new BOOL(var1.name(), true);
                    }else {
                        return (T) new BOOL(var1.name(), true);
                    }
                }
                if(var1 instanceof INT) {
                    if(((FLOAT) var1).getData() > 0) {
                        return (T) new BOOL(var1.name(), true);
                    }else {
                        return (T) new BOOL(var1.name(), true);
                    }
                }
                if(var1 instanceof BYTE) {
                    if(((FLOAT) var1).getData() > 0) {
                        return (T) new BOOL(var1.name(), true);
                    }else {
                        return (T) new BOOL(var1.name(), true);
                    }
                }
            }
        }
        throw new Exception();
    }

    public CLASS getData(){
        return this;
    }

    public INSTANCE construct(String name, long UUID, int size, Namespace father, Namespace root, DataTypeBase... var){
        Namespace sub_namespace = new Namespace(UUID, father, root, size);
        if(var != null) {
            for (DataTypeBase v : var) {
                sub_namespace.add(v);
            }
        }
        INSTANCE instance = new INSTANCE(this, sub_namespace);
        instance.setName(name);
        return instance;
    }

    public void setSuperClassName(String name){
        superClassName = name;
    }

    public String getSuperClassName(){
        return superClassName;
    }

    public void print(){
        if(superClassName != null && !superClassName.equals("")){
            System.out.println("@super <class" + superClass.name() + ">");
        }
        System.out.println("<class " + name + ">");
        namespace.print(0);
    }

    public void print(int level){
        if(superClassName != null && !superClassName.equals("")){
            blanks(level);
            System.out.println("@super <class " + superClass.name() + ">");
        }
        blanks(level);
        System.out.println("<class " + name + ">");
        namespace.print(level + 1);
    }

    public void blanks(int level){
        for(int i = 0; i < level; i++){
            System.out.print("    ");
        }
    }

    public void setSuperClass(CLASS superClass) {
        this.superClass = superClass;
    }

    public CLASS getSuperClass() {
        return superClass;
    }
}
