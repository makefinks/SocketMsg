import javax.swing.*;
import java.io.IOException;

public class MainApp {

    public static void main(String[] args) throws IOException {

        JFrame conFrame = new JFrame();
        conFrame.setVisible(true);
        conFrame.setSize(300, 200);

        ConnectPanel conPanel = new ConnectPanel(conFrame);
        conFrame.add(conPanel.$$$getRootComponent$$$());
        conFrame.pack();

        //ChatServer server = new ChatServer(5555);
       //server.start();




    }
}
