package com.company;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor<total> extends Thread {
    private static ArrayList<BufferedWriter> clientes;
    private static ServerSocket server;
    private String nome;
    private Socket con;
    private InputStream in;
    private InputStreamReader inr;
    private BufferedReader bfr;

    public Servidor(Socket con) {
        this.con = con;
        try {
            in  = con.getInputStream();
            inr = new InputStreamReader(in);
            bfr = new BufferedReader(inr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void run() {
    }
    public static void main(String[] args) throws IOException {
        double num1, num2, operacao;
        double total = 0;
        char   opr = '@';

        //Socket da Porta 9998
        ServerSocket servidor = new ServerSocket(9998);
        System.out.println("Porta 9998 aberta!");

        System.out.print("Aguardando conexão do cliente...");
        Socket cliente = servidor.accept();

        System.out.println("Nova conexao com o cliente " + cliente.getInetAddress().getHostAddress());

        ObjectOutputStream resultado = new ObjectOutputStream(cliente.getOutputStream());
        ObjectInputStream dados = new ObjectInputStream(cliente.getInputStream());

        num1 = dados.readDouble();
        num2 = dados.readDouble();
        operacao = (char) dados.readDouble();

        if (operacao == 1) {
            opr = '+';
            total = ((num1) + (num2));
        }
        if (operacao == 2) {
            opr = '-';
            total = ((num1) - (num2));
        }
        if (operacao == 3) {
            opr = '*';
            total = ((num1) * (num2));
        }
        if (operacao == 4) {
            opr = '/';
            total = ((num1) / (num2));
        }
        if (operacao >= 8){
            System.out.printf("Voce digitou uma operacao invalida.");
        }
        resultado.writeChar(opr);
        resultado.writeDouble(total);
        resultado.flush();
        servidor.close();
    }
}





    
    
    
    
    
    
   

