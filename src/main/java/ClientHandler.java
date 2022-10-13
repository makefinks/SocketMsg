import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.Buffer;
import java.util.logging.Logger;

public class ClientHandler extends Thread{

    public static final Logger log = Logger.getLogger("log");
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public ClientHandler(Socket socket){
        clientSocket = socket;
    }

    public void run(){
        log.info("New Client has connected to " + clientSocket);
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            log.info("Accepting input from client" + clientSocket);
            String line;

            while((line = in.readLine()) != null){
                System.out.println(line);
                out.println("Message received" + line);
                System.out.println("sending confirmation");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
