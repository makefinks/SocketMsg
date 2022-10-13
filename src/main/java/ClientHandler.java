import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.logging.Logger;

public class ClientHandler extends Thread{
    public static final Logger log = Logger.getLogger("log");
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private ChatServer server;

    public ClientHandler(Socket socket, ChatServer server){
        clientSocket = socket;
        this.server = server;

    }

    public void run(){
        log.info("New Client has connected to " + clientSocket);
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
           // log.info("Accepting input from client " + clientSocket);

            String line;
            while((line = in.readLine()) != null){
                log.info("Received message from " + clientSocket + ": " + line);

                //send message to all connected Clients
                ArrayList<ClientHandler> handlers = server.getClientHandlers();
               // handlers.remove(this);
                for(ClientHandler handler : handlers){
                    log.info(handler.clientSocket + " sent message: " + "'" + line + "'");
                    handler.out.println(clientSocket + ": " + line);
                }


            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public PrintWriter getOut() {
        return out;
    }
}
