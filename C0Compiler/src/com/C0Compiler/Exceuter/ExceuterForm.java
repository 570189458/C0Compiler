package com.C0Compiler.Exceuter;

import com.C0Compiler.JavaCC.*;
import com.C0Compiler.JavaCC.Compiler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ExceuterForm {
    public JPanel panel1;
    private JButton button1;
    private JTextArea textArea1;
    private JButton 解释Button;
    private JLabel outField;
    private JCheckBox isCompiler;


    private File openFile;
    private FileInputStream fileInputStream;
    private FileOutputStream fileOutputStream;
    private OutputStreamWriter outputStreamWriter;

    ExceuterForm()
    {
        button1.setName("Browse");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.showOpenDialog(chooser);
                openFile = chooser.getSelectedFile();
                JOptionPane.showMessageDialog(null, openFile.getPath());

                try {
                    textArea1.setText("");
                    FileReader fileReader = null;
                    fileReader = new FileReader(openFile);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String line;
                    while((line = bufferedReader.readLine()) != null)
                    {
                        textArea1.append(line + "\n");
                    }
                    fileReader.close();
                    bufferedReader.close();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });
        解释Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!isCompiler.isSelected()) {
                    Exceuter exceuter = new Exceuter();
                    exceuter.init(openFile.getPath());
                    String out = exceuter.getOut();
                    outField.setText(out);
                }
                else if(isCompiler.isSelected())
                {
                    try {
                        Compiler.main(openFile.getPath());
                        ArrayList<MiddleCodeItem> middleCodeItems = Compiler.getMiddleCodeList();
                        String out = "";
                        out+="<html>";
                        out+="<body>";
                        for(MiddleCodeItem item : middleCodeItems)
                        {
                            out+=item.codeType+" "+item.arg0+" "+item.arg1+"<br>";
                        }
                        out+="<body>";
                        out+="</html>";
                        outField.setText(out);

                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }

    public static int inputDialog()
    {
        String inputVal = JOptionPane.showInputDialog("输入数据");
        while(!isNumber(inputVal))
        {
            JOptionPane.showMessageDialog(null, "输入非数字");
            inputVal = JOptionPane.showInputDialog("再次输入数据");
        }
        int val = Integer.parseInt(inputVal);
        return val;
    }

    public static boolean isNumber(String str)
    {
        for(int i = 0; i < str.length(); i++)
        {
            if(!Character.isDigit(str.charAt(i)))
            {
                return false;
            }
        }
        return true;
    }
}
