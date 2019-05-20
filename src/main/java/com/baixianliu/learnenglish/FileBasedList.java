package com.baixianliu.learnenglish;

import android.content.res.AssetManager;

import android.app.Activity;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.Scanner;
import java.io.File;

//public class FileBasedList extends ArrayList {
//public class FileBasedList {
//extends Activity to access getAssets()
public class FileBasedList extends Activity {
    private ArrayList<String> wordList;
    private ArrayList<String> lineList;

    private InputStream inputStream;
//    private InputStream originalInputStream;

//    private static FileBasedList singleton;
//    private static FileBasedList singleton = null;

//    static {
//        try {

//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

//    public static FileBasedList getInstance() {
//        return FileBasedList.singleton;
//    }

//    public FileBasedList() {
//private FileBasedList() throws IOException {
//    public FileBasedList() throws IOException {

    // Dependency Injection
    public FileBasedList(InputStream inputStream) throws IOException {
        this.inputStream = inputStream;
        wordList = new ArrayList<>();
        lineList = new ArrayList<>();
        initWordList();
//        initLineList();
    }

    public ArrayList<String> getWordList() {
        return wordList;
    }

    public ArrayList<String> getLineList() {
        return lineList;
    }

//    public InputStream getFileAsset(String fileName) {
//        AssetManager assetManager = getAssets();
//        InputStream asset = assetManager.open(fileName);
//        return asset;
//    }

    //    public ArrayList<String> initWordList() throws IOException {
    public void initWordList() throws IOException {
        // FileInputStream fileInputStream = new FileInputStream("../res/words");
//        FileReader fileReader = new FileReader("../res/words");
//        char[] charBuffer = new char[]{};
//        fileReader.read(charBuffer);
//        bug found: file path mistaken
//        mv res/words java/com/baixianliu/learnenglish/
//        Scanner scanner = new Scanner(new File("../res/words"));
//        Scanner scanner = new Scanner(new File("words"));
//        cannot reference the file as in the same directory with src file
//        Scanner scanner = new Scanner(new File("@files/words"));

        Scanner scanner = new Scanner(inputStream);

        while (scanner.hasNext()) {
            wordList.add(scanner.next());
        }
        scanner.close();
//        return wordList;

        // reset the inputStream so that it can be read from the beginning
        // in initLineList()
//        inputStream.reset();
    }

//    public ArrayList<String> initLineList() throws IOException {
    public void initLineList() throws IOException {
//        bug found: file path mistaken
//        mv res/words java/com/baixianliu/learnenglish/
//        Scanner scanner = new Scanner(new File("../res/words"));
//        cannot reference the file as in the same directory with src file
//        Scanner scanner = new Scanner(new File("@files/words"));
//        AssetManager assetManager = getAssets();
//        InputStream input = assetManager.open("words");

        // should initialize scanner with a new InputStream
        // inputStream has been changed by initWordList()
        Scanner scanner = new Scanner(inputStream);

        while (scanner.hasNextLine()) {
            lineList.add(scanner.nextLine());
        }
        scanner.close();

//        return lineList;
    }

    public void printWordList() {
        System.out.println(wordList);
    }

    public void printLineList() {
        System.out.println(lineList);
    }
}
