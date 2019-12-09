package com.C0Compiler.Exceuter;

import javax.swing.*;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Exceuter exceuter = new Exceuter();
//		exceuter.init();
        JFrame frame = new JFrame("C0");
        frame.setContentPane(new ExceuterForm().panel1);
        frame.setSize(800,600);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

	}

}
