package Client;

import Shared.InputSanitizer;
import Shared.Constants;
import java.io.*;
import java.net.*;

public class ChatClient {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private ChatClientGUI clientGUI;

    public static void main(String[] args) {
        new ChatClient().start();
    }

    public void start() {
        clientGUI = new ChatClientGUI(this); 
        try {
            socket = new Socket("localhost", Constants.SERVER_PORT);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            new Thread(new IncomingMessagesHandler(in, clientGUI)).start();
        } catch (IOException e) {
            e.printStackTrace();
            clientGUI.showError("Não foi possível conectar ao servidor.");
        }
    }

    public void sendMessage(String message) {
        if (out != null && !message.isEmpty()) {
            // Apenas enviar a mensagem bruta, sem sanitização
            System.out.println("Mensagem enviada pelo cliente: " + message);  // Verificação
            out.println(message);
        } else {
            System.out.println("Mensagem não enviada: Conexão com o servidor não estabelecida.");
        }
    }
}
