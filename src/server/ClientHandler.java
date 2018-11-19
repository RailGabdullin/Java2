package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {

    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;
    private Server server;
    public String nick;

    public ClientHandler(Server server, Socket socket) {
        try {
            this.socket = socket;
            this.server = server;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        //цикл авторизации
                        while (true) {
                            String str = in.readUTF();
                            if (str.startsWith("/auth")) {
                                String[] tokens = str.split(" ");
                                String newNick = AuthService.getNickname(tokens[1], tokens[2]);
                                if (newNick != null) {
                                    sendMsg("/authok");
                                    nick = newNick;
                                    server.subscribe(ClientHandler.this);
                                    break;
                                } else {
                                    sendMsg("Неверный логин/пароль");
                                }
                            }
                        }
                        //рабочий цикл
                        while (true) {
                            String str = in.readUTF();
                            if (str.equals("/end")) {
                                out.writeUTF("/serverclosed");
                                break;
                            }
                            String[] tokens = str.split(" ");
                            if (tokens.length > 1 && tokens[0].equals("/w")) {
                                    //Тут идет проерка на отправление сообщения самому себе. Для печати ошибок изготовлен специальный метод
                                    if (tokens[1].equals(ClientHandler.this.nick)) {
                                        server.errorMsg("Нельзя отправить сообщение самому себе", ClientHandler.this);
                                        //Здесь проверяем есть ли пользователь с таким ником
                                    } else if (AuthService.nickIsExist(tokens[1])) {
                                        //Проверяем онлайн пользователь или нет
                                        if (server.isOnline(tokens[1])) {
                                            //Выпиливаем из запроса пользователя команду личного сообщения, чтобы потом подставить красивенько что нам надо
                                            String strtockens = "";
                                            for (int i = 2; i < tokens.length; i++) {
                                                strtockens = strtockens + " " + tokens[i];
                                            }
                                            //Если все ок, то отправляем личное сообщение через специальный метод
                                            server.sendPrivat(strtockens, tokens[1], ClientHandler.this);
                                            //При необходимости кидаем соответсвующие ошибки
                                        } else {
                                            server.errorMsg("Пользователь сейчас офлайн", ClientHandler.this);
                                        }
                                    } else {
                                        server.errorMsg("Такого пользователя не существует", ClientHandler.this);
                                    }
                                } else {
                                    server.broadcastMsg(str, ClientHandler.this);
                                }
                        }

//                            if (tokens[0].equals("/w") && AuthService.nickIsExist(tokens[1])) {
//                                String strtockens = "";
//                                for (int i = 2; i < tokens.length; i++) {
//                                    strtockens = strtockens + " " + tokens[i];
//                                }
//                                server.sendPrivat(strtockens, tokens[1], ClientHandler.this);
//                            } else {
//                                server.broadcastMsg(str, ClientHandler.this);
//                            }
//                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        server.unsubscribe(ClientHandler.this);
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
