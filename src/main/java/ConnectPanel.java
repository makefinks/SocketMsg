import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

public class ConnectPanel {
    private JFrame parent;
    private JTextField portField;
    private JButton connectButton;
    private JProgressBar progressBar;
    private JPanel compPanel;
    private JTextField ipField;
    private JCheckBox hostCheckBox;
    private JTextField nameField;
    private JLabel nameLbl;
    private JLabel statusLbl;
    private ChatServer server;

    private ConnectPanel connectPanel;

    public ConnectPanel(JFrame parent) {

        this.parent = parent;
        connectPanel = this;

        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (hostCheckBox.isSelected()) {
                    if (!portField.getText().equals("")) {
                        server = new ChatServer(Integer.parseInt(portField.getText()));
                        server.start();

                        JFrame chatFrame = new JFrame();
                        chatFrame.setSize(500, 500);
                        chatFrame.setVisible(true);
                        chatFrame.setLocationRelativeTo(null);
                        chatFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        parent.dispose();

                        ChatPanel chatPanel = null;
                        try {
                            chatPanel = new ChatPanel(connectPanel);
                        } catch (UnknownHostException ex) {
                            throw new RuntimeException(ex);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        chatFrame.add(chatPanel.$$$getRootComponent$$$());
                        parent.dispose();
                    }
                } else {
                    if (!ipField.getText().equals("") && !portField.getText().equals("")) {

                        JFrame chatFrame = new JFrame();
                        chatFrame.setSize(500, 500);
                        chatFrame.setVisible(true);
                        chatFrame.setLocationRelativeTo(null);
                        chatFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                        ChatPanel chatPanel = null;
                        try {
                            chatPanel = new ChatPanel(connectPanel);
                        } catch (UnknownHostException ex) {
                            throw new RuntimeException(ex);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        chatFrame.add(chatPanel.$$$getRootComponent$$$());
                        parent.dispose();

                    }

                }
            }
        });

        hostCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (hostCheckBox.isSelected()) {
                    ipField.setEnabled(false);
                } else {
                    ipField.setEnabled(true);
                }
            }
        });
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        compPanel = new JPanel();
        compPanel.setLayout(new GridLayoutManager(4, 5, new Insets(0, 0, 0, 0), -1, -1));
        portField = new JTextField();
        portField.setText("");
        compPanel.add(portField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        connectButton = new JButton();
        connectButton.setText("connect");
        compPanel.add(connectButton, new GridConstraints(3, 1, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("IP");
        compPanel.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Port");
        compPanel.add(label2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ipField = new JTextField();
        ipField.setText("");
        compPanel.add(ipField, new GridConstraints(0, 1, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final Spacer spacer1 = new Spacer();
        compPanel.add(spacer1, new GridConstraints(1, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        compPanel.add(spacer2, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        hostCheckBox = new JCheckBox();
        hostCheckBox.setText("Host");
        compPanel.add(hostCheckBox, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        nameField = new JTextField();
        nameField.setText("");
        compPanel.add(nameField, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        nameLbl = new JLabel();
        nameLbl.setText("Name");
        compPanel.add(nameLbl, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return compPanel;
    }

    public JTextField getIpField() {
        return ipField;
    }

    public JTextField getPortField() {
        return portField;
    }

    public JButton getConnectButton() {
        return connectButton;
    }

    public JProgressBar getProgressBar() {
        return progressBar;
    }

    public JPanel getCompPanel() {
        return compPanel;
    }

    public JCheckBox getHostCheckBox() {
        return hostCheckBox;
    }

    public JLabel getStatusLbl() {
        return statusLbl;
    }

    public ConnectPanel getConnectPanel() {
        return connectPanel;
    }

    public JFrame getParent() {
        return parent;
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JLabel getNameLbl() {
        return nameLbl;
    }

    public ChatServer getServer() {
        return server;
    }
}
