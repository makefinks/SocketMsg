import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class TestClient {

    public static void main(String[] args) throws IOException, InterruptedException {
        Client client = new Client();

        client.connect("localhost", 5555);

        String response = client.sendMessage("Hello World");


    }
}
