import javax.swing.*;

public class MainApp {

    public static void main(String[] args) {

        JFrame conFrame = new JFrame();
        conFrame.setVisible(true);
        conFrame.setSize(300, 200);

        ConnectPanel conPanel = new ConnectPanel();
        conFrame.add(conPanel.$$$getRootComponent$$$());
        conFrame.pack();

    }
}
