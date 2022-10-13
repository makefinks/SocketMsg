import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Logger;

public class ChatServer extends Thread{

    private static final Logger log = Logger.getLogger("ChatLogger");
    private ServerSocket serverSocket;
    private BufferedReader out;
    private PrintWriter in;

    private boolean running = true;
    private int port;

    public ChatServer(int port){
        this.port = port;
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
                new ClientHandler(serverSocket.accept()).start();
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



}
