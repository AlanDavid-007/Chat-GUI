package Server;

import Shared.InputSanitizer;
import java.io.*;
import java.net.*;

public class ClientHandler extends Thread {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            ChatServer.addClientWriter(out);

            String message;
            while ((message = in.readLine()) != null) {
                // Log the raw message before sanitization
                System.out.println("Mensagem bruta recebida: " + message);

                // Sanitize the message first
                String sanitizedMessage = InputSanitizer.sanitize(message);
                
                // Log the sanitized message
                System.out.println("Mensagem recebida e sanitizada: " + sanitizedMessage);
                
                // Check if the raw message contains a script tag
                if (message.contains("<script>")) {
                    System.out.println("Mensagem maliciosa detectada e bloqueada do broadcast.");
                } else {
                    // If the message is safe, send it to all clients
                    System.out.println("Broadcasting mensagem: " + sanitizedMessage);
                    ChatServer.broadcastMessage(sanitizedMessage);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ChatServer.removeClientWriter(out);
        }
    }
}
