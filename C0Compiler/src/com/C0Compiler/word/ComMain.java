package com.C0Compiler.word;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//C:\Users\XiuMing\Desktop\C0com\C0.txt

public class ComMain {
    private WordAnalysis wordAnalysis;
    private Scanner sc = new Scanner(System.in);
    public static ArrayList<String> sourceCode;

    public static void main(String[] args) {
        //TODO
        ComMain main = new ComMain();
        main.init();
    }

    //初始化
    private void init()
    {
        sourceCode = new ArrayList<>();
        initFile();
        wordAnalysis = new WordAnalysis();
    }

    /**
     * 初始化文件
     */
    private void initFile()
    {
        while (true)
        {
            System.out.println("input c0 file");
            if(readFile(sc.next()))
            {
                break;
            }
            else
            {
                System.out.println("文件错误");
            }
        }
    }

    /**
     * 读取文件
     */
    private boolean readFile(String filename)
    {
        File file = new File(filename);
        BufferedReader reader = null;
        try
        {
            reader = new BufferedReader(new FileReader(file));
            String strtmp;
            while ((strtmp = reader.readLine()) != null)
            {
                sourceCode.add(strtmp);
            }
            reader.close();
            return true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }finally {
            if(reader != null)
            {
                try {
                    reader.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
