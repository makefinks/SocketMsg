import java.awt.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ChatHandler {
    private boolean host;
    private ChatPanel chatPanel;
    private Client client;
    public ChatHandler(boolean host, ChatPanel chatPanel){
        this.host = host;
        this.chatPanel = chatPanel;
    }

    public void initialize() throws IOException {
        client = new Client();
        String ip = chatPanel.getConnectPanel().getIpField().getText();
        int port = Integer.parseInt(chatPanel.getConnectPanel().getPortField().getText());
        if(host){
            InetAddress ipAdress = InetAddress.getLocalHost();
            chatPanel.getChatArea().append("New Server established \n IP: " + ipAdress.toString() + "\n Port: " + chatPanel.getConnectPanel().getPortField().getText() + "\n");
            client.connect("localhost", port, chatPanel.getConnectPanel().getNameField().getText());
        }else{
            chatPanel.getChatArea().append("Establishing Connection to " + ip + ":" + port + "\n");
            try{
                client.connect(ip, port, chatPanel.getConnectPanel().getNameField().getText());
                chatPanel.getChatArea().append("Sucess! \n");
            }catch (Exception e){
                e.printStackTrace();
            }

        }

        chatPanel.getChatArea().append("-".repeat(10) + "\n");
        listen();

    }

    public void sendMessage(String msgText) throws IOException {
        client.sendMessage(msgText);
    }

    public void listen() throws IOException {
        Thread listenThread = new Thread(){
            public void run() {
                String line;
                while(true){
                    try {
                       // if (!((line = client.getIn().readLine()) != null)) break;
                        if((line = client.getIn().readLine()) != null){
                            chatPanel.getChatArea().append(line + "\n");
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    //chatPanel.getChatArea().append(line + "\n");
                }
            }
        };
        listenThread.start();
    }

    public void drawInfoLine(String line){
        chatPanel.getChatArea().setForeground(Color.red);
        chatPanel.getChatArea().append(line + "\n");
        chatPanel.getChatArea().setForeground(Color.BLACK);
    }

}
