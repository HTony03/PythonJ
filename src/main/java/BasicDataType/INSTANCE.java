package BasicDataType;

import VirtualMachine.Namespace;

import java.util.HashSet;

public class INSTANCE extends CLASS {

    // 定义该实例是哪一个类的实例
    public final CLASS belongClass;

    //这个实例的所有动态属性
    private final Namespace dynamic_namespace;

    public INSTANCE(CLASS belongClass, Namespace namespace) {
        this.type =  "instance";
        this.belongClass = belongClass;
        this.dynamic_namespace = namespace;
    }

    public INSTANCE(CLASS belongClass, Namespace father, Namespace root, long UUID) {
        this.type =  "instance";
        this.belongClass = belongClass;
        this.dynamic_namespace = new Namespace(UUID, father, root, 0);
    }

    public INSTANCE(CLASS belongClass, Namespace father, Namespace root, long UUID, int size) {
        this.type =  "instance";
        this.belongClass = belongClass;
        this.dynamic_namespace = new Namespace(UUID, father, root, size);
    }

    public void setName(String name){
        this.name = name;
    }

    public String name(){
        return name;
    }

    public Namespace getStaticNamespace() {
        return super.namespace();
    }

    public Namespace getDynamicNamespace() {
        return dynamic_namespace;
    }

    public void define(DataTypeBase var){
        if(dynamic_namespace.contains(var.name())){
            if(!dynamic_namespace.getByName(var.name()).equals(var)){
                try{
                    var = transform(var,dynamic_namespace.getByName(var.name()));
                }catch (Exception e){
                    //处理异常
                }
            }
            dynamic_namespace.remove(var.name());
        }
        dynamic_namespace.add(var);
    }
    public CLASS getCLASS(){
        return this.belongClass;
    }
}
