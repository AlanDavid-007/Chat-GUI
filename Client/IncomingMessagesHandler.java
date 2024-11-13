package Client;

import java.io.BufferedReader;
import java.io.IOException;

public class IncomingMessagesHandler implements Runnable {
    private BufferedReader in;
    private ChatClientGUI clientGUI;

    public IncomingMessagesHandler(BufferedReader in, ChatClientGUI clientGUI) {
        this.in = in;
        this.clientGUI = clientGUI;
    }

    public void run() {
        String message;
        try {
            while ((message = in.readLine()) != null) {
                clientGUI.appendMessage(message); // Assumindo que esta função está atualizando a interface
                System.out.println("Mensagem recebida no cliente: " + message); // Para verificar as mensagens recebidas
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
