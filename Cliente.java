package com.company;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import javax.swing.*;

public class Cliente extends JFrame implements ActionListener, KeyListener {

    public static void main(String[] args) throws UnknownHostException, IOException {

            Socket client;
            int opcao = 0;
            opcao = Integer.parseInt(JOptionPane.showInputDialog("Digite o servidor de sua preferencia"));
            Socket cliente = null;
            if (opcao == 1) {
                cliente = new Socket("127.0.1.1", 9998);
            }if (opcao == 2) {
                cliente = new Socket("127.0.1.1", 9999);
            }else{
                System.out.printf("Voce digitou uma opcao invalida.");
            }

        double num1, num2, operacao;
        double total;
        char   opr;

        System.out.println("Conectado");

        ObjectInputStream resultado = new ObjectInputStream(cliente.getInputStream());
        ObjectOutputStream dados = new ObjectOutputStream(cliente.getOutputStream());

        num1     = Double.parseDouble(JOptionPane.showInputDialog("Digite o primeiro número"));
        num2     = Double.parseDouble(JOptionPane.showInputDialog("Digite o segundo número"));
        operacao = Double.parseDouble(JOptionPane.showInputDialog("Qual operação desejada? 1=+,2=-,3=*,4=/,5=%,6=r,7=^"));

        dados.writeDouble(num1);
        dados.writeDouble(num2);

        dados.writeDouble(operacao);

        dados.flush();

        opr   = resultado.readChar();
        total = resultado.readDouble();
        System.out.println("Total eh " + num1 + opr + num2 + " = " + total);

        cliente.close();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }
}