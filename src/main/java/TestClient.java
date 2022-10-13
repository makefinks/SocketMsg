import java.io.IOException;
import java.net.Socket;

public class TestClient {

    public static void main(String[] args) throws IOException {
        Client client = new Client();

        client.connect("localhost", 1234);

        String response = client.sendMessage("Hello World");

    }
}
