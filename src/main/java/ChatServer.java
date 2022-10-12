import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private BufferedReader out;
    private PrintWriter in;

    public void start(int port){
        try{
            serverSocket = new ServerSocket(port);


        }catch (IOException e){
            e.printStackTrace();
        }

    }
    public void stop() throws IOException {
        serverSocket.close();
        out.close();
        in.close();

    }



}
