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
    private String username;
    private boolean conMsg  = false;
    ArrayList<ClientHandler> handlers;

    public ClientHandler(Socket socket, ChatServer server) throws IOException {
        clientSocket = socket;
        this.server = server;

        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        handlers = server.getClientHandlers();

    }

    public void run(){
        log.info("New Client has connected to " + clientSocket);
        try {

           // log.info("Accepting input from client " + clientSocket);

            String line;
            while((line = in.readLine()) != null){
                if(line.contains("username-data:")) {
                    username = line.split(":")[1].trim();
                }
                else {
                    if(line.equals("/quit")){
                        break;
                    }
                    log.info("Received message from " + clientSocket + ": " + line);
                    //send message to all connected Clients
                    // handlers.remove(this);
                    for (ClientHandler handler : handlers) {
                        log.info(handler.clientSocket + " sent message: " + "'" + line + "'");
                        handler.out.println(username + ": " + line);
                    }
                }
                if(!conMsg) {
                    sendConnectBroadcast();
                    conMsg = true;
                    }
                }
            //end of connection
            out.println(username + " has left the chat!");
            clientSocket.close();




        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendConnectBroadcast(){
        log.info(clientSocket + " broadcasting connection info");
        for(ClientHandler handler : handlers){
            handler.out.println(username + " has connected to the chat!");
        }

    }

    public PrintWriter getOut() {
        return out;
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public BufferedReader getIn() {
        return in;
    }

    public ChatServer getServer() {
        return server;
    }

    public String getUsername() {
        return username;
    }
}
