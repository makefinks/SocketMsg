import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Logger;

public class ChatServer extends Thread{

    private static final Logger log = Logger.getLogger("ChatLogger");
    private ArrayList<ClientHandler> clientHandlers;
    private ServerSocket serverSocket;
    private BufferedReader in;
    private PrintWriter out;

    private boolean running = true;
    private int port;

    public ChatServer(int port){
        this.port = port;
        clientHandlers = new ArrayList<>();
    }

    public void run(){

        try {
            serverSocket = new ServerSocket(port);
            log.info("New Server with " + serverSocket + " has been created");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while(running){
            try {
                ClientHandler handler = new ClientHandler(serverSocket.accept(), this);
                clientHandlers.add(handler);
                handler.start();
                //handler.sendConnectBroadcast();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void stopServer() throws IOException {
        serverSocket.close();
        out.close();
        in.close();
    }

    public void answer(String message){
        out.println(message);
    }

    public ArrayList<ClientHandler> getClientHandlers(){
        return clientHandlers;
    }
}
