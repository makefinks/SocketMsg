import java.io.*;
import java.net.Socket;
import java.util.logging.Logger;

public class Client {

    private static final Logger log = Logger.getLogger("ClientLogger");
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    public void connect(String ip, int port, String username) throws IOException{
       socket = new Socket(ip, port);
       out = new PrintWriter(socket.getOutputStream(), true);
       in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

       log.info(socket + " sending username-data");
       out.println("username-data: " + username);
   }

   public void sendMessage(String message) throws IOException {
        out.println(message);
        //out.flush();
       // log.info("Client with socket " + socket.toString() + " send message: " + "'" + message + "'");
   }

   public void terminate() throws IOException {
        socket.close();
        in.close();
        out.close();
   }

   public boolean isConnected(){
       return socket.isConnected();
   }


    public Socket getSocket() {
        return socket;
    }

    public PrintWriter getOut() {
        return out;
    }

    public BufferedReader getIn() {
        return in;
    }
}
