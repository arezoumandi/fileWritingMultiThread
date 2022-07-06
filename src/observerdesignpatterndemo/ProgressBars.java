package observerdesignpatterndemo;

import java.io.File;
import java.util.Observable;
import javax.swing.JProgressBar;
import java.util.Observer;

public class ProgressBars implements Observer {

    File file;
    int fileSize;
    int progressValue;
    JProgressBar jProgressBar;

    public File getFile() {
        return file;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public ProgressBars(File file, int fileSize) {
        this.file = file;
        this.fileSize = fileSize;
        jProgressBar = new JProgressBar(0, fileSize);
    }

    public JProgressBar bind() {
        jProgressBar.setValue((int) ((file.length()/1048576 )));
        jProgressBar.setStringPainted(true);
        return jProgressBar;
    }

    @Override
    public void update(Observable o, Object o1) {
        jProgressBar.setValue((int) ((file.length()/1048576 )));
//        System.out.println("File siz of "+file+" : "+file.length());
    }

    public int getFileSize() {
        return fileSize;
    }

}
