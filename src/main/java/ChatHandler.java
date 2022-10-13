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
            client.connect("localhost", port);
        }else{
            chatPanel.getChatArea().append("Establishing Connection to " + ip + ":" + port + "\n");
            try{
                client.connect(ip, port);
                chatPanel.getChatArea().append("Sucess!");
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
                        if (!((line = client.getIn().readLine()) != null)) break;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    chatPanel.getChatArea().append(line + "\n");
                }
            }
        };
        listenThread.start();


    }
}
