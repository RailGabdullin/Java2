package Client;

import javafx.fxml.Initializable;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ClientMain {
    public static void main(String[] args) {

        Socket socket;
        Scanner in = null;
        PrintWriter out = null;

            try {
                socket = new Socket( "localhost",22);
                in = new Scanner(socket.getInputStream());
                out = new PrintWriter(socket.getOutputStream());

                Scanner finalIn = in;
                PrintWriter finalOut = out;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            String str = finalIn.nextLine();
                            if (finalIn.equals("end")) break;
                            finalOut.println("Пользователь записал: " + str);
                        }
                    }
                }).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}