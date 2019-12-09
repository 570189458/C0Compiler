package com.C0Compiler.Compiler;

import java.util.Arrays;

public class WordAnalysis {
    //���ʻ���
    private String wordTmp;
    //����
    private int row;
    //���浥���ַ�ָ��
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
        //������
        mainsymbol,     //main
        voidsymbol,     //void
        ifsymbol,       //����
        whilesymbol,    //ѭ��
        returnsymbol,   //����
        intsymbol,      //int
        nullsymbol,     //null

        ifsym,          //if
        elsesym,        //else
        whilesym,       //while

        readsym,        //��
        writesym,       //д
        scanfsym,       //scanf
        printfsym,      //printf

        //����
        number,         //����
        //���޷�
        leftsym,        //(
        rightsym,       //)
        beginsym,       //{
        endsym,         //}
        comma,          //,
        semicolon,      //;
        period,         //.

        //��ʶ��
        ident,

        //�����
        add,            //+
        sub,            //-
        mul,            //*
        div,            ///
        equal,          //=
        restasym        //��ֵ
    };

    /**
     * �ж��ַ�������
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
     * ��ȡ��һ���ַ�
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
     * ɾȥע��
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
     * ��ȡ��һ������
     */
    private void getSym()
    {
        char ch = getChar();
        while(true)
        {
            if(ch == '.')
            {
                System.out.println("�ĵ�����!");
                break;
            }
            else if(getType(ch) != charType.NULL)
            {
                //��һ���ַ�����ĸ
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
                        System.out.println("������" + strtmp);
                    }
                    else
                    {
                        System.out.println("����" + strtmp);
                    }
                }
                //��һ��������
                else if(getType(ch) == charType.Number)
                {
                    int numTmp = ch - '0';
                    ch = getChar();
                    while (getType(ch) == charType.Number)
                    {
                        numTmp = numTmp * 10 + (ch - '0');
                        ch = getChar();
                    }
                    System.out.println("����" + numTmp);
                }
                else if(ch == '(')
                {
                    System.out.println("����" + ch);
                    ch = getChar();
                }
                else if(ch == ')')
                {
                    System.out.println("����" + ch);
                    ch = getChar();
                }
                else if(ch == '{')
                {
                    System.out.println("����" + ch);
                    ch = getChar();
                }
                else if(ch == '}')
                {
                    System.out.println("����" + ch);
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
