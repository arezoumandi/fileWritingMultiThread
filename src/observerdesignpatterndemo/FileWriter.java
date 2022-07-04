package observerdesignpatterndemo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Observable;

public class FileWriter extends Observable implements Runnable {
    

    String fileName;
    String data;
    int size;

    public FileWriter(String fileName, String data, int size) {
        this.fileName = fileName;
        this.data = data;
        this.size = size;
    }

    
    @Override
    public void run() {
        try {
            File file = new File(this.fileName);
            while (file.length() < this.size*1024*1024) {
                java.io.FileWriter fileWriter = new java.io.FileWriter(this.fileName, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(this.data);
                bufferedWriter.newLine();
                setChanged();
                notifyObservers(file);
                bufferedWriter.close();
                
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

 
    

}
