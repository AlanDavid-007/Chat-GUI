package Server;

import Shared.InputSanitizer;
import Shared.Constants;
import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    private static final int MAX_CLIENTS = 10;
    private static Set<PrintWriter> clientWriters = new HashSet<>();

    public static void main(String[] args) {
        System.out.println("Servidor iniciado...");
        try (ServerSocket serverSocket = new ServerSocket(Constants.SERVER_PORT)) {
            while (true) {
                if (clientWriters.size() < MAX_CLIENTS) {
                    new ClientHandler(serverSocket.accept()).start();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void broadcastMessage(String message) {
        System.out.println("Broadcasting mensagem: " + message); // Para verificar o que está sendo enviado
        synchronized (clientWriters) {
            for (PrintWriter writer : clientWriters) {
                writer.println(message);
            }
        }
    }

    static void addClientWriter(PrintWriter writer) {
        synchronized (clientWriters) {
            clientWriters.add(writer);
        }
    }

    static void removeClientWriter(PrintWriter writer) {
        synchronized (clientWriters) {
            clientWriters.remove(writer);
        }
    }
}

