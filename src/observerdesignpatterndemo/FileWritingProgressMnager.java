package observerdesignpatterndemo;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class FileWritingProgressMnager {

    public static void main(String[] args) {
        int min=50;
        int max=200;
        Random rand = new Random();
        ExecutorService pool = Executors.newFixedThreadPool(10);
        MainForm mainForm = new MainForm();
        Map<File, Integer> unSortedMap = new HashMap<>();
        ArrayList<ProgressBars> progressBarses = new ArrayList<>();
        ArrayList<FileWriterObservable> fileWriters = new ArrayList<>();

        //generate file names for pass File class to create 10 files
        String names[] = {"a0.txt", "a1.txt", "a2.txt", "a3.txt", "a4.txt", "a5.txt", "a6.txt", "a7.txt", "a8.txt", "a9.txt"};
        File[] files = Arrays.stream(names).
                map(s -> new File(s)).
                toArray(size -> new File[names.length]);

        //Generate a mpa to mapping file name and file size(random size)
        Arrays.stream(files).forEach((e)
                -> {
            unSortedMap.put(e, (int) (rand.nextInt(max - min + 1) + min));
        });     
        
        Map<File, Integer> parameteMap=unSortedMap.entrySet()
                .stream().sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue
                ,(oldKey ,newKey)->oldKey, LinkedHashMap::new));
        
        
        //Generate and bind progress Bars to files
        parameteMap.forEach((k, v) -> {
            progressBarses.add(new ProgressBars(k, v));
            fileWriters.add(new FileWriterObservable(k.getName(), "aaaaa", v));
            System.out.println("Size of file"+k+" :"+v);
        });

        //Add progress bars to form
        progressBarses.forEach((ProgressBars e) -> {
            mainForm.addBars(e);
        });

        //Add observers and run threads to writing into files
        for (int i = 0; i < 10; i++) {
            fileWriters.get(i).addObserver(progressBarses.get(i));
            pool.execute(fileWriters.get(i));
        }

        pool.shutdown();

    }

}
