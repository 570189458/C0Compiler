package com.C0Compiler.Exceuter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

//C:\Users\XiuMing\Desktop\C0com\stack.txt

public class Exceuter {
    //����ַ����λ��
    private int base_address;
    //��ǰָ������λ��
    private int current_index;
    //��ʼ�����ָ����б�
    private ArrayList<Direct> DirectList;
    //��ʼ��Scanner
    private Scanner sc;
    //��ʼ��ջ
    private Stack<Integer> stack;

    Exceuter()
    {
        //��ʼ������
        base_address = 0;
        current_index = 0;
        DirectList = new ArrayList<Direct>();
        sc = new Scanner(System.in);
        stack = new Stack<Integer>();
    }

    public void init()
    {
        //��ʼ��
        System.out.println("Input C0 Direct File Catalogue");
        String FileCatalogue;
        FileCatalogue = sc.nextLine();
        //���ļ�
        readfile(FileCatalogue);
        execute();
    }

    //��������ָ����н���
    private void execute()
    {
        while(current_index < DirectList.size())
        {
            //System.out.println(current_index+" "+DirectList.size());
            System.out.println(DirectList.get(current_index).get_Direct_name()+" "+DirectList.get(current_index).get_t()+" "+DirectList.get(current_index).get_a());
//            if(!stack.empty()) {
//                System.out.println(stack.peek());
//            }
            analyze(current_index);
            current_index++;
        }
    }

    //����ָ��
    private void analyze(int index)
    {
        int t = DirectList.get(index).get_t();
        int a = DirectList.get(index).get_a();
        switch (DirectList.get(index).get_Direct_name())
        {
            case LIT:direct_LIT(t, a);break;
            case LOD:direct_LOD(t, a);break;
            case STO:direct_STO(t, a);break;
            case CAL:direct_CAL(t, a);break;
            case INT:direct_INT(t, a);break;
            case JMP:direct_JMP(t, a);break;
            case JPC:direct_JPC(t, a);break;
            case ADD:direct_ADD(t, a);break;
            case SUB:direct_SUB(t, a);break;
            case MUL:direct_MUL(t, a);break;
            case DIV:direct_DIV(t, a);break;
            case RED:direct_RED(t, a);break;
            case WRT:direct_WRT(t, a);break;
            case RET:direct_RET(t, a);break;
        }
    }

    private void readfile(String filecatalogue)
    {
        try{
            File file = new File(filecatalogue);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String tmpStr = "";
            int lineCount = 1;
            //���ļ��ж�һ��
            while(null != (tmpStr = br.readLine()))
            {
                //���ַ����ÿո�ָ�
                //System.out.println(tmpStr);
                String[] ttmpStr = tmpStr.split(" ");
                //System.out.println("line:"+lineCount+"data:"+tempStr);
                Direct tmpDirect = new Direct();
                //��ö�������в���
                for(DirectName tmpDr : DirectName.values())
                {
                    if(tmpDr.toString().equals(ttmpStr[0]))
                    {
                        tmpDirect.set_Direct_name(tmpDr);
                        break;
                    }
                    else
                    {
                        //û��ƥ���ָ��
                        //TODO
                    }
                }
                //��tmpDirect��ֵ
                tmpDirect.set_t(Integer.valueOf(ttmpStr[1]));
                tmpDirect.set_a(Integer.valueOf(ttmpStr[2]));
                DirectList.add(tmpDirect);
                lineCount++;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //������ֵȡ��ջ����aΪ����ֵ
    private void direct_LIT(int t, int a)
    {
        stack.push(a);
    }

    //������ֵȡ��ջ����aΪ��Ե�ַ��tΪ����
    private void direct_LOD(int t, int a)
    {
        if(t == 0)
        {
            stack.push(stack.get(a));
        }
        else
        {
            stack.push(stack.get(base_address + a));
        }
    }

    //��ջ����������ĳ������Ԫ�У�aΪ��Ե�ַ��tΪ����
    private void direct_STO(int t, int a)
    {
        if(t == 0)
        {
            stack.set(a, stack.pop());
        }
        else
        {
            stack.set(a + base_address, stack.pop());
        }
    }

    //���ú�����aΪ������ַ
    private void direct_CAL(int t, int a)
    {
        int base = stack.size();
        stack.push(base_address);
        stack.push(current_index + 1);
        base_address = base;
        current_index = a - 1;
    }

    //������ջ��Ϊ�����õĹ��̿���a����Ԫ��������
    private void direct_INT(int t, int a)
    {
        for (int i = 0; i < a; i++)
    {
        stack.push(0);
    }
    }

    //��������ת��a��ַ
    private void direct_JMP(int t, int a)
    {
        current_index = a - 1;
    }

    //������ת����ջ��ֵΪ0������ת��a��ַ������˳��ִ��
    private void direct_JPC(int t, int a)
    {
        if(stack.peek() == 0)
        {
            current_index = a - 1;
        }
    }

    //��ջ����ջ����ӣ�������ջԪ�أ����ֵ��ջ
    private void direct_ADD(int t, int a)
    {
        int x = stack.pop();
        int y = stack.pop();
        stack.push(y + x);
    }

    //��ջ����ȥջ����������ջԪ�أ����ֵ��ջ
    private void direct_SUB(int t, int a)
    {
        int x = stack.pop();
        int y = stack.pop();
        stack.push(y - x);
    }

    //��ջ������ջ����������ջԪ�أ����ֵ��ջ
    private void direct_MUL(int t, int a)
    {
        int x = stack.pop();
        int y = stack.pop();
        stack.push(y * x);
    }

    //��ջ������ջ����������ջԪ�أ����ֵ��ջ
    private void direct_DIV(int t, int a)
    {
        //������Ϊ0
        int x = stack.pop();
        int y = stack.pop();
        if(x == 0)
        {
            throw new ArithmeticException();
        }
        stack.push(y / x);
    }

    //�������ж���һ����������ջ��
    private void direct_RED(int t, int a)
    {
        int tmpint = sc.nextInt();
        stack.push(tmpint);
    }

    //ջ��ֵ�������Ļ������
    private void direct_WRT(int t, int a)
    {
        if(stack.empty())
        {
            System.out.println("Stack Is Empty");
        }
        else
            System.out.println("Output:"+stack.peek());
    }

    //�������ý�����,���ص��õ㲢��ջ
    private void direct_RET(int t, int a)
    {
        if(base_address == 0)
        {
            current_index = Integer.MAX_VALUE - 1;
            return;
        }
        int returnValue = stack.peek();
        current_index = stack.get(base_address + 1) - 1;
        int tmpBaseAddress = base_address;
        base_address = stack.get(base_address);
        while (stack.size() > tmpBaseAddress)
        {
            stack.pop();
        }
        stack.push(returnValue);
    }
}
