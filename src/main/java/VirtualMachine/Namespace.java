package VirtualMachine;

import BasicDataType.CLASS;
import BasicDataType.DataTypeBase;
import BasicDataType.FUNCTION;
import BasicDataType.INSTANCE;

import java.util.ArrayList;
import java.util.HashSet;

public class Namespace {
    public final long UUID;
    public Namespace father;

    public Namespace root;

    private HashSet<DataTypeBase> variables;

    public Namespace(long UUID, Namespace father,Namespace root, int size) {
        this.UUID = UUID;
        this.root = root;
        this.father = father;
        if(size <= 0){variables = new HashSet<>();}else {variables = new HashSet<>(size);}
    }

    public Namespace(long UUID, int size){
        this.UUID = UUID;
        if(size <= 0){variables = new HashSet<>();}else {variables = new HashSet<>(size);}
    }

    public HashSet<DataTypeBase> getVariables() {
        return variables;
    }

    public void add(DataTypeBase variable) {
        variables.add(variable);
    }

    public void remove(DataTypeBase variable) {
        variables.remove(variable);
    }

    public void remove(String varName){
        for(DataTypeBase dtb:variables){
            if(dtb.name().equals(varName)){
                variables.remove(dtb);
                break;
            }
        }
    }

    public String[] getVarNames(){
        String[] names = new String[variables.size()];
        int i = 0;
        for(DataTypeBase dtb:variables){
            names[i++] = dtb.name();
        }
        return names;
    }

    public DataTypeBase getByName(String varName) {
        for (DataTypeBase dtb : variables) {
            if (dtb.name().equals(varName)) {
                return dtb;
            }
        }
        return DataTypeBase.NULL;
    }

    public boolean contains(String varName){
        for(DataTypeBase dtb:variables){
            if(dtb.name().equals(varName)){
                return true;
            }
        }
        return false;
    }

    public void print(int level){
        for(DataTypeBase dtb:variables){
            if(dtb instanceof CLASS){
                ((CLASS) dtb).print(level + 1);
            }else {
                dtb.print();
            }
            System.out.println();
        }
    }

    public ArrayList<BasicDataType.CLASS> getClasses(){
        ArrayList<BasicDataType.CLASS> classes = new ArrayList<>();
        for(DataTypeBase dtb:variables){
            if(dtb instanceof BasicDataType.CLASS && !(dtb instanceof BasicDataType.FUNCTION)){
                classes.add((BasicDataType.CLASS) dtb);
                classes.addAll(((BasicDataType.CLASS) dtb).namespace().getClasses());
            }
        }
        return classes;
    }

    public FUNCTION reachFunction(String path){
        String[] paths = path.split("\\.");
        if(paths.length == 1){
            for(DataTypeBase dtb:variables){
                if(dtb instanceof FUNCTION && dtb.name().equals(path)){
                    return (FUNCTION) dtb;
                }
            }
        }else {
            for(DataTypeBase dtb:variables){
                if(dtb instanceof INSTANCE && dtb.name().equals(paths[0])){
                    return ((INSTANCE) dtb).getCLASS().namespace().reachFunction(path.substring(paths[0].length() + 1));
                }else if(dtb instanceof CLASS && dtb.name().equals(paths[0])){
                    return ((CLASS) dtb).namespace().reachFunction(path.substring(paths[0].length() + 1));
                }
            }
        }
        return null;
    }

    public Namespace root(){
        return root;
    }
}
