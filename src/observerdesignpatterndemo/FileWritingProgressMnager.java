package observerdesignpatterndemo;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.*;

public class FileWritingProgressMnager {

    public static void main(String[] args) {

        Random rand = new Random();
//        int rn[] = new int[10];
        ProgressBars pb[] = new ProgressBars[10];
        String fileNames[] = new String[10];
        File file[] = new File[10];
        FileWriter fileWriter[] = new FileWriter[10];
        ExecutorService pool = Executors.newFixedThreadPool(10);
        MainForm mainForm = new MainForm();
        
        int [] rn=IntStream.generate(()->(int) (rand.nextInt(200 - 50 + 1) + 50))
                .limit(100)
                .toArray();
        
        String names[]={"a0.txt","a1.txt","a2.txt","a3.txt","a4.txt","a5.txt","a6.txt","a7.txt","a8.txt","a9.txt"};
        File [] files=Arrays.stream(names).
                map(s->new File(s)).
                toArray(size->new File[names.length]);
        
        
        
//        Arrays.stream(file).forEach(e->System.out.println("file name="+e));
//       Arrays.stream(rn).forEach(e-> {
//            return (int)(rand.nextInt(200 - 50 + 1) + 50);
//        });
//        Arrays.stream(pb).forEach(e->new ProgressBars());
            List<ProgressBars> progressBarses=new ArrayList<>();
//            Map<Files ,Integer> parameteMap=createMap();
            
        for (int i = 0; i < 10; i++) {
            rn[i] = (int) rand.nextInt(200 - 50 + 1) + 50;
            fileNames[i] = "a" + i + ".txt";
            file[i] = new File(fileNames[i]);
            pb[i] = new ProgressBars(file[i], rn[i]);
            mainForm.addBars(pb[i]);
            fileWriter[i] = new FileWriter(fileNames[i], "aaaaaaaaaa", rn[i]);
            fileWriter[i].addObserver(pb[i]);
            pool.execute(fileWriter[i]);
        }

        pool.shutdown();

    }

}
