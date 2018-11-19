import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerTest {
    public static void main(String[] args) {


//    void Server() {
        ServerSocket serverSocket = null;
        Socket socket = null;
        try {
            serverSocket = new ServerSocket(23);
            System.out.println("Сервер запущен...");
            socket = serverSocket.accept();
            System.out.println("Подключение установлено");
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