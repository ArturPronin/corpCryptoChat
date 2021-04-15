package com.example.testcryptochat;

import android.util.Log;
import android.util.Pair;

import androidx.core.util.Consumer;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    // localhost

    Map<Long, String> names = new ConcurrentHashMap<>();
    WebSocketClient client;
    private Consumer<Pair<String, String>> onMessageReceived;
    private Consumer<Integer> onUsersAmountUpdated;
    private Consumer<String> onUserJoined;

    public Server(Consumer<Pair<String, String>> onMessageReceived, Consumer<Integer> onUsersAmountUpdated,
                  Consumer<String> onUserJoined) {
        this.onMessageReceived = onMessageReceived;
        this.onUsersAmountUpdated = onUsersAmountUpdated;
        this.onUserJoined = onUserJoined;
    }

    public void connect() {
        URI address;
        try {
            address = new URI("http://192.168.43.160:8881"); //http://192.168.0.101:8881
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return;
        }

        client = new WebSocketClient(address) {
            @Override
            public void onOpen(ServerHandshake handshakedata) {
                // При подключении
                Log.i("SERVER", "Connection to server is open");
            }

            @Override
            public void onMessage(String message) {
                // При поступлении "сообщения" с сервера
                Log.i("SERVER", "Got message from server: " + message);
                int type = Protocol.getType(message);
                if (type == Protocol.USER_STATUS) {
                    // Обработать факт подключения или отключения
                    userStatusChanged(message);
                }

                if (type == Protocol.MESSAGE) {
                    // Показать сообщение на экране
                    displayIncomingMessage(message);
                }
            }

            @Override
            public void onClose(int code, String reason, boolean remote) {
                // При закрытии соединения
                Log.i("SERVER", "Server connection closed");

            }

            @Override
            public void onError(Exception ex) {
                // При ошибке
                Log.i("SERVER", "ERROR occurred: " + ex.getMessage());
            }
        };
        client.connect();
    }



    private void displayIncomingMessage(String json) {
        Protocol.Message m = Protocol.unpackMessage(json);
        String name = names.get(m.getSender());
        if (name == null) {
            name = "No name";
        }
        String text = m.getEncodedText();
        try {
            text = Crypto.decrypt(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
        onMessageReceived.accept(
                new Pair<String, String>(name, text)
        );
    }

    private void userStatusChanged(String json) {
        Protocol.UserStatus s = Protocol.unpackStatus(json);
        Protocol.User user = s.getUser();
        if (s.isConnected()) {
            names.put(user.getId(), user.getName());
            onUserJoined.accept(user.getName());
        } else {
            names.remove(user.getId());
        }
        onUsersAmountUpdated.accept(names.size());

    }
    public void sendMessage(String message) {
        if (client == null || !client.isOpen()){
            return;
        }
        try {
            message = Crypto.encrypt(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Protocol.Message m = new Protocol.Message(message);
        m.setReceiver(Protocol.GROUP_CHAT);
        String packedMessage = Protocol.packMessage(m);
        Log.i("SERVER", "Sending message" +packedMessage);
        client.send(packedMessage);
    }

    public void sendUserName(String name) {
        String myName = Protocol.packName(new Protocol.UserName(name));
        Log.i("SERVER", "Sending my name to server " + myName);
        client.send(myName);
    }
}
