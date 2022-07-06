package observerdesignpatterndemo;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.*;

public class MainForm {

    public JFrame jFrame;
    public JPanel jPanel;
    int fileSize;
    JLabel jLabel;
    JLabel fileSizeLable;

    public void addBars(ProgressBars progressBar) {
        jLabel = new JLabel(progressBar.getFile().toString());
//        fileSizeLable = new JLabel(progressBar.getFileSize() + " MB");
        JProgressBar jProgressBar = progressBar.bind();
        jProgressBar.setBounds(100, 100, 100, 100);

    

//        jPanel.add(fileSizeLable);
        jPanel.add(jLabel);
        jPanel.add(jProgressBar);
        jFrame.add(jPanel);
    }

    public MainForm() {
        jFrame = new JFrame();
        jPanel = new JPanel();

        jFrame.setSize(240, 600);
        jFrame.setVisible(true);
    }
}
