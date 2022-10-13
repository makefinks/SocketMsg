import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;
import java.util.logging.Logger;

public class ClientHandler extends Thread{
    public static final Logger log = Logger.getLogger("log");
    private Socket clientSocket;
    private BufferedWriter out;
    private BufferedReader in;
    private ServerSocket server;

    public ClientHandler(Socket socket, ServerSocket server){
        clientSocket = socket;
        this.server = server;

    }

    public void run(){
        log.info("New Client has connected to " + clientSocket);
        try {
            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            log.info("Accepting input from client " + clientSocket);
            String line;
            while((line = in.readLine()) != null){
                out.write("Message received" + line);
                log.info("Received message from " + clientSocket + ": " + line);

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
