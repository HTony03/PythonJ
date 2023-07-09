package Operate;

public class TRYEXCEPT extends OperatorBase
{
}

class TRY extends OperatorBase
{
    //try接代码 except接对应的报错信息，例：
    //SyntaxError: unterminated string literal (detected at line 1)
    //对应判断：
    // try:
    //            testlines
    //except SyntaxError():
    //        lines
    //有时需要import对应库中的特定报错
    //例：
    //from selenium.common.exceptions import UnexpectedAlertPresentException
    //lines
    //except UnexpectedAlertPresentException():
}

class EXCEPT extends OperatorBase {

}

class ELSE extends  OperatorBase{

}
