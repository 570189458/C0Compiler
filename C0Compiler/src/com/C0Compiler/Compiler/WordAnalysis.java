package com.C0Compiler.Compiler;

import java.util.Arrays;

public class WordAnalysis {
    //单词缓存
    private String wordTmp;
    //行数
    private int row;
    //缓存单词字符指针
    private int index;

    WordAnalysis()
    {
        wordTmp = "";
        row = 0;
        index = 0;
        getSym();
    }

    private String[] reservedWord = {
            "main",
            "void",
            "if",
            "while",
            "return",
            "int"
    };

    public enum symbol
    {
        //基本字
        mainsymbol,     //main
        voidsymbol,     //void
        ifsymbol,       //条件
        whilesymbol,    //循环
        returnsymbol,   //返回
        intsymbol,      //int
        nullsymbol,     //null

        ifsym,          //if
        elsesym,        //else
        whilesym,       //while

        readsym,        //读
        writesym,       //写
        scanfsym,       //scanf
        printfsym,      //printf

        //常数
        number,         //数字
        //界限符
        leftsym,        //(
        rightsym,       //)
        beginsym,       //{
        endsym,         //}
        comma,          //,
        semicolon,      //;
        period,         //.

        //标识符
        ident,

        //运算符
        add,            //+
        sub,            //-
        mul,            //*
        div,            ///
        equal,          //=
        restasym        //赋值
    };

    /**
     * 判断字符的类型
     */
    public enum wordType{
        Baseword,
        Number,
        Delimiter,
        Identifier,
        Operator
    }

    public enum charType{
        NULL,
        Letter,
        Number
    }

    private charType getType(char ch)
    {
        if(ch == ' ' || ch == 10 || ch == 9)
        {
            return charType.NULL;
        }else if(ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z' || ch == '_')
        {
            return charType.Letter;
        }else if(ch >= '0' && ch <='9')
        {
            return charType.Number;
        }
        return null;
    }

    /**
     * 获取下一个字符
     */
    private char getChar(){
        while(wordTmp.equals(""))
        {
            if(row >= ComMain.sourceCode.size())
            {
                return '.';
            }
            else
            {
                wordTmp = ComMain.sourceCode.get(row);
                row++;
                index = 0;
                deletenote();
            }
        }
        char tmp = wordTmp.charAt(index);
        index++;
        if(index >= wordTmp.length())
        {
            wordTmp = "";
        }
        return tmp;
    }

    /**
     * 删去注释
     */
    private void deletenote()
    {
        int delIndex = wordTmp.indexOf("//");
        if(delIndex == -1)
        {
            return;
        }
        else
        {
            wordTmp = wordTmp.substring(0,delIndex);
            return;
        }
    }

    /**
     * 获取下一个单词
     */
    private void getSym()
    {
        char ch = getChar();
        while(true)
        {
            if(ch == '.')
            {
                System.out.println("文档结束!");
                break;
            }
            else if(getType(ch) != charType.NULL)
            {
                //第一个字符是字母
                if(getType(ch) == charType.Letter)
                {
                    String strtmp = "";
                    strtmp += ch;
                    ch = getChar();
                    while(getType(ch) == charType.Number || getType(ch) == charType.Letter)
                    {
                        strtmp += ch;
                        ch = getChar();
                    }
                    if(Arrays.asList(reservedWord).contains(strtmp))
                    {
                        System.out.println("保留字" + strtmp);
                    }
                    else
                    {
                        System.out.println("单词" + strtmp);
                    }
                }
                //第一个是数字
                else if(getType(ch) == charType.Number)
                {
                    int numTmp = ch - '0';
                    ch = getChar();
                    while (getType(ch) == charType.Number)
                    {
                        numTmp = numTmp * 10 + (ch - '0');
                        ch = getChar();
                    }
                    System.out.println("数字" + numTmp);
                }
                else if(ch == '(')
                {
                    System.out.println("符号" + ch);
                    ch = getChar();
                }
                else if(ch == ')')
                {
                    System.out.println("符号" + ch);
                    ch = getChar();
                }
                else if(ch == '{')
                {
                    System.out.println("符号" + ch);
                    ch = getChar();
                }
                else if(ch == '}')
                {
                    System.out.println("符号" + ch);
                    ch = getChar();
                }
                else
                {
                    System.out.println(ch);
                    ch = getChar();
                }
            }
            else
            {
                ch = getChar();
            }
        }
    }
}
