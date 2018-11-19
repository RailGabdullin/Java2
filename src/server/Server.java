package server;

import hw.Client;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

public class Server {
    private Vector<ClientHandler> clients;

    public Server() {
        AuthService.connect();
        clients = new Vector<>();
        ServerSocket server = null;
        Socket socket = null;

        try {
            server = new ServerSocket(8183);
            System.out.println("Сервер запущен!");

            while (true) {
                socket = server.accept();
                System.out.println("Клиент подключился");
//                clients.add(new ClientHandler(this, socket));
                new ClientHandler(this, socket);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            AuthService.disconnect();
        }
    }

    public void broadcastMsg(String msg, ClientHandler author) {
        for (ClientHandler o : clients) {
            o.sendMsg(author.nick + ": " + msg);
        }
    }

    public void subscribe(ClientHandler clientHandler) {
        clients.add(clientHandler);
    }

    public void unsubscribe(ClientHandler clientHandler) {
        clients.remove(clientHandler);
    }

//Метод, сбственнннн отправляющий личные сообщения
    public void sendPrivat(String str, String token, ClientHandler author) {
        for (ClientHandler o : clients) {
            if(o.nick.equals(token)) {
                o.sendMsg("Личное от " + author.nick + ": " + str);
                author.sendMsg("Для " + o.nick + ": " + str);
            }
        }

    }
//Специальный метод для выброса ошибок
    public void errorMsg(String str, ClientHandler author) {
                author.sendMsg("Ошибка! " + str);

    }

//Проверяет онлан пользователь или нет
    public boolean isOnline(String checkNick){
        boolean result = false;
        for (ClientHandler o : clients) {
            if (o.nick.equals(checkNick)) {
                result = true;
            }
        }
        return result;
    }
}
