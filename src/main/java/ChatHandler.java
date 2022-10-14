import javax.sound.sampled.*;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.URISyntaxException;
import java.net.URL;

public class ChatHandler {
    private boolean host;
    private ChatPanel chatPanel;
    private Client client;
    private Clip notClip;

    private File notSound;
    public ChatHandler(boolean host, ChatPanel chatPanel) throws LineUnavailableException, IOException, UnsupportedAudioFileException, URISyntaxException {
        this.host = host;
        this.chatPanel = chatPanel;


        //Audio
        URL resource = getClass().getClassLoader().getResource("notification.wav");
        assert resource != null;
        notSound = new File(resource.toURI());
        //src/main/java/notification.wav"


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

        chatPanel.getChatArea().append("-".repeat(50) + "\n");
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

                            //Play sound if message comes from other user
                            if(!line.split(":")[0].equals(chatPanel.getConnectPanel().getNameField().getText())){
                                playNotification();
                            }

                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (UnsupportedAudioFileException e) {
                        throw new RuntimeException(e);
                    } catch (LineUnavailableException e) {
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

    public void playNotification() throws LineUnavailableException, UnsupportedAudioFileException, IOException {
        System.out.println("playing sound");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(notSound);
        notClip = AudioSystem.getClip();
        notClip.open(audioStream);
        notClip.start();
    }


}
