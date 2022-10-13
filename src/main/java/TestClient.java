import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class TestClient {

    public static void main(String[] args) throws IOException, InterruptedException {
        Client client = new Client();
        Client client2 = new Client();

        client.connect("localhost", 5555);
        client2.connect("localhost", 5555);


    }
}
