package Server;

import java.io.Console;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerMain {


    public ServerMain() {
        ClientHandler client;
        ServerSocket serverSocket = null;
        Socket socket = null;
        try {
            serverSocket = new ServerSocket(22);
            System.out.println("Сервер запущен...");
            socket = serverSocket.accept();
            System.out.println("Подключение установлено");
            Scanner scr = new Scanner(socket.getInputStream());
            Scanner inScanner = new Scanner(System.in);
            PrintWriter prtwrt = new PrintWriter(socket.getOutputStream(), true);

            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        String str = scr.nextLine();
                        System.out.println(str);
                    }
                }
            });
            t1.start();


//            try {
//                t1.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        String inStr = inScanner.nextLine();
                        if (inStr.equals("/end")) break;
                        prtwrt.println(inStr);
                        prtwrt.flush();
                    }
                }
            });
            t2.start();

            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}