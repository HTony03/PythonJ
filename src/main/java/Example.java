import BasicDataType.*;
import Calculate.BasicCalculator;
import Calculate.GeneralCalculator;
import Operate.DEF;
import Operate.DEL;
import Utils.Code;
import Utils.Data;
import Utils.IDcontroller;
import Utils.Utils;
import VirtualMachine.ByLineInterpreter;
import VirtualMachine.Formatter;
import VirtualMachine.Namespace;

import java.util.ArrayList;

public class Example {

    public static void main(String[] args) {
        System.out.println("Hello PythonJ!");

        /*
        示例代码放这里
        */
        IDcontroller id = new IDcontroller();
        Namespace namespace = new Namespace(id.getNewID(),0);
        GeneralCalculator gc = new GeneralCalculator(namespace);
        System.out.println(((FLOAT)gc.calculate(new Data(){{
            add(new STR("-0.5*(3%2*(5+1))"));
        }}).get(0)).getData());

        ArrayList<String> exp = new ArrayList<>();
        CLASS class1 = new CLASS("A", id.getNewID(), namespace, namespace,0);
        //namespace.add(class1);
        INSTANCE a = class1.construct("A1", id.getNewID(), 0, namespace, null);
        //namespace.add(a);
        voidLine();
        FUNCTION function = new FUNCTION("func");
        FUNCTION function2 = new FUNCTION("func2");
        class1.define(function);
        a.define(function2);

        Code code = new Code(
                     "class a(b):\n" +
                              "\n" +
                              "    @staticmethod\n" +
                              "    def funcx(x):\n" +
                              "        print('hello world')\n" +
                              "        pass\n" +
                              "    def func2(x, y):\n" +
                              "        a = x + y\n" +
                              "        return a\n" +
                              "def test():\n" +
                              "    pass\n\n" +
                              "class b:\n" +
                              "    pass"
        );
        //code.print();
        voidLine();
        Formatter.scanClass(namespace, namespace, code, id, 0);
        DEL.DEL("del test", namespace);
        namespace.print(-1);
        /*
        FUNCTION func = DEF.DEFINE(((STR)code.GOTO(1).get(0)).getData(), new ByLineInterpreter(), code);
        class1.define(func);
        func.print();
        voidLine();
        Utils.Utils.generateMethodList(namespace, exp, "", false);
        System.out.println(exp);

         */
    }

    public static void voidLine(){System.out.println("");}
}
