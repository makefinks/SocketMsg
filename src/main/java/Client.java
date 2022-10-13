import java.io.*;
import java.net.Socket;
import java.util.logging.Logger;

public class Client {

    private static final Logger log = Logger.getLogger("ClientLogger");
    private Socket socket;
    private BufferedWriter out;
    private BufferedReader in;
    public void connect(String ip, int port) throws IOException {
       socket = new Socket(ip, port);
       out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
       in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
   }

   public String sendMessage(String message) throws IOException {
        out.write(message);
        log.info("Client with socket " + socket.toString() + " send message: " + "'" + message + "'");
        return in.readLine();
   }

   public void terminate() throws IOException {
        socket.close();
        in.close();
        out.close();
   }

}
