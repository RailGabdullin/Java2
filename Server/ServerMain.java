package Server;

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
            client = new ClientHandler(this, socket);
            Scanner scr = new Scanner(socket.getInputStream());
            PrintWriter prtwrt = new PrintWriter(socket.getOutputStream());
            while (true) {
                String str = scr.nextLine();
                if (str.equals("/end")) break;
                System.out.println(str);
                prtwrt.println("Сервер записал " + str);
                prtwrt.flush();
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