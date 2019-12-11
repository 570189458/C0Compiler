package com.C0Compiler.JavaCC;

public class Error {
    public static enum ErrCode
    {
        ArithmeticException,    //�����쳣
        InputParamErrException, //�����������
        VariableException,      //�����쳣
        FunctionException,      //�����쳣
        UnknowException         //δ֪����
    }
    public static boolean errFlag = false;
    public static void ShowErrMsg(int errCode,String errMsg)
    {
        switch (errCode)
        {
            case 1:{showMessage(ErrCode.ArithmeticException,errMsg);break;}
            case 2:{showMessage(ErrCode.InputParamErrException,errMsg);break;}
            case 3:{showMessage(ErrCode.VariableException,errMsg);break;}
            case 4:{showMessage(ErrCode.FunctionException,errMsg);break;}
            default:{showMessage(ErrCode.UnknowException,errMsg);break;}
        }
    }

    private static void showMessage(ErrCode errCode,String errMsg){
        System.err.println("Error-"+errCode.toString()+": "+errMsg);
        errFlag=true;
    }
}
