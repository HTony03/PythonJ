package Operate;

public class RETURN extends OperatorBase{
    //return用在def或class中用于返回特定值
    //例：return a(a为其中变量)
    //与print不同的是，return是将a这个变量存到调用的地方：
    //def test():
    //    a = 1
    //    a += 1    #a=2
    //    return a
    //test = test()
    //print(test)    #输出2
    //
    //return不可在def或class外被调用
}

class YIELD extends RETURN{}
