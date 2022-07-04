package observerdesignpatterndemo;

import javax.swing.*;

public class MainForm {

    public JFrame jFrame;
    public JPanel jPanel;
    int fileSize;
    JLabel jLabel;

    public void addBars(ProgressBars progressBar) {
        jLabel = new JLabel(progressBar.getFile().toString());
        jLabel.setBounds(100, 100, 100, 100);

        JProgressBar jProgressBar = progressBar.bind();
        jProgressBar.setBounds(100, 100, 100, 100);

        jPanel.add(jLabel);
        jPanel.add(jProgressBar);
        jFrame.add(jPanel);
    }

    public MainForm() {
        jFrame = new JFrame();
        jPanel = new JPanel();
        jFrame.setSize(230, 600);
        jFrame.setVisible(true);
    }
}
