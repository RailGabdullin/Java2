package Client;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.Scanner;

public class ClientMain {
    public static void main(String[] args) {

        Socket socket;

            try {
                socket = new Socket( "localhost",22);
                Scanner in = new Scanner(socket.getInputStream());
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                Scanner inScanner = new Scanner(System.in);

                Thread t1 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            String str = in.nextLine();
                            System.out.println( "Сообщение от сервера: " + str);
                        }
                    }
                });
                t1.start();


                Thread t2 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            String inStr = inScanner.nextLine();
                            if (inStr.equals("end")) break;
                            out.println("Сообщение от пользователя: " + inStr);
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
            }
    }
}