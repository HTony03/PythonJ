package VirtualMachine;

import BasicDataType.DataTypeBase;
import BasicDataType.FUNCTION;
import BasicDataType.STR;
import Operate.CLASS;
import Utils.IDcontroller;

import java.util.ArrayList;

public class Formatter {
    public static void scanClass(Namespace namespace, Namespace root, Utils.Code code, IDcontroller id, int size){
        ArrayList<Integer> marks = code.markAllClass();
        ArrayList<Integer> marks2 = code.markAllDef();
        int offset = 0;
        for(Integer mark : marks){
            if(mark < offset){continue;}
            int length = code.subCodeSpace().length();
            BasicDataType.CLASS cls = CLASS.CLASS(((STR)code.GOTO(mark).get(0)).getData(), code, id, namespace, root, size);
            namespace.add(cls);
            scanFunction(cls.namespace(), code.subCodeSpace());

            for(Integer i : marks2){if(i > mark && i < mark + length){marks2.set(marks2.indexOf(i),-1);}}
            while(marks2.contains(-1)) {marks2.remove((Object)(-1));}

            for(DataTypeBase dtb:cls.namespace().getVariables()){
                if(dtb instanceof BasicDataType.CLASS && !(dtb instanceof BasicDataType.FUNCTION)){
                    scanClass(((BasicDataType.CLASS) dtb).namespace(), root, code, id, size);
                }
            }
            offset += length;
        }

        offset = 0;
        for(Integer mark : marks2){
            if(mark < offset){continue;}
            BasicDataType.FUNCTION func = Operate.DEF.DEFINE(((STR)code.GOTO(mark).get(0)).getData(), code);
            namespace.add(func);
            offset += code.subCodeSpace().length();
        }

        matchSuperClass(namespace);
    }

    public static void scanFunction(Namespace namespace, Utils.Code code){
        ArrayList<Integer> marks = code.markAllDef();
        int offset = 0;
        for(Integer mark : marks){
            if(mark < offset){continue;}
            BasicDataType.FUNCTION func = Operate.DEF.DEFINE(((STR)code.GOTO(mark).get(0)).getData(), code);
            namespace.add(func);
            offset += code.subCodeSpace().length();
        }
    }

    public static void matchSuperClass(Namespace namespace){
        ArrayList<BasicDataType.CLASS> classes = namespace.getClasses();
        for(BasicDataType.CLASS cls : classes){
            if(cls.getSuperClassName() != null && cls.getSuperClassName().length() > 0){
                for(BasicDataType.CLASS bdt: classes){
                    if(bdt.name().equals(cls.getSuperClassName())){
                        cls.setSuperClass(bdt);
                        break;
                    }
                }
                if (cls.getSuperClass() == null){
                    //处理异常
                    System.err.println("SuperClass <class "+ cls.getSuperClassName() +"> not found!");
                }
            }
        }
    }
}
