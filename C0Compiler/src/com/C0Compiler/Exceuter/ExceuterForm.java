package com.C0Compiler.Exceuter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class ExceuterForm {
    public JPanel panel1;
    private JButton button1;
    private JTextArea textArea1;
    private JButton Ω‚ ÕButton;


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
    }
}
