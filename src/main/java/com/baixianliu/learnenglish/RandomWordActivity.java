package com.baixianliu.learnenglish;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

import java.io.InputStream;

public class RandomWordActivity extends Activity {
//    private ArrayList<String> wordList;
    private ArrayList<String> wordList = new ArrayList<>();
//    private ArrayList<String> lineList;
    private ArrayList<String> lineList = new ArrayList<>();


    private FileBasedList fileBasedList = null;

    private HashMap<String, Integer> wordMap = new HashMap<>();

//    public MainActivity() throws IOException {
//        initLineList();
//        initWordList();
//    }

    public InputStream getFileAsset(String fileName) throws IOException {
        AssetManager assetManager = getAssets();
        InputStream asset = assetManager.open(fileName);
        return asset;
    }

    void init() throws IOException {
//        initLineList();
//        initWordList();
         InputStream asset = getFileAsset("basic_words");
         fileBasedList = new FileBasedList(asset);

         this.wordList = fileBasedList.getWordList();

//         fileBasedList = new FileBasedList(asset);
//         this.lineList = fileBasedList.getLineList();
    }

//    private void initLineList() throws IOException {
//        AssetManager assetManager = getAssets();
////        if (assetManager == null) {
////            System.out.println("assetManager is null!");
////        }
////        AssetManager assetManager = getBaseContext().getAssets();
//        InputStream inputStream = assetManager.open("basic_words");
//        Scanner scanner = new Scanner(inputStream);
//        while (scanner.hasNextLine()) {
//            lineList.add(scanner.nextLine());
//        }
//        scanner.close();
//    }

//    private void initWordList() throws IOException {
//        // MainActivity instance has not been fully instantiated here
//        // this might be why getAssets() returns null
//        // Problem Solved
//        AssetManager assetManager = getAssets();
//        InputStream inputStream = assetManager.open("basic_words");
//        Scanner scanner = new Scanner(inputStream);
//        while (scanner.hasNext()) {
//            wordList.add(scanner.next());
//        }
//        scanner.close();
//    }

//    public void printWordList() {
//        System.out.println(wordList);
//    }
//
//    public void printLineList() {
//        System.out.println(lineList);
//    }

    public void onClickGoNext(View view) {
        Intent intent = new Intent(this, SpellWordActivity.class);
        startActivity(intent);
    }

    public void onClickShowWord(View view) {
        TextView word_panel = (TextView) findViewById(R.id.word_panel);
        int wordListLength = wordList.size();
        int randomIndex = new Random().nextInt(wordListLength);
        String randomWord = wordList.get(randomIndex);
        word_panel.setText(randomWord);

        ImageView image = (ImageView) findViewById(R.id.image);

//        if (randomWord.equals("boat")) {
//            image.setImageResource(R.drawable.boat);
//        } else if (randomWord.equals("cap")) {
//            image.setImageResource(R.drawable.cap);
//        } else if (randomWord.equals("sweep")) {
//            image.setImageResource(R.drawable.sweep);
//        } else if (randomWord.equals("flag")) {
//            image.setImageResource(R.drawable.flag);
//        } else if (randomWord.equals("soap")) {
//            image.setImageResource(R.drawable.soap);
//        } else if (randomWord.equals("ship")) {
//            image.setImageResource(R.drawable.ship);
//        } else if (randomWord.equals("spoon")) {
//            image.setImageResource(R.drawable.spoon);
//        } else if (randomWord.equals("hat")) {
//            image.setImageResource(R.drawable.hat);
//        }

        image.setImageResource(wordMap.get(randomWord));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
//    protected void onStart() throws IOException {
//    onStart() does not throw IOException
//    protected void onStart() throws IOException {
    protected void onStart() {
        // refactored
//        FileBasedList fileBasedList = new FileBasedList();
        super.onStart();

//        TextView welcome = (TextView) findViewById(R.id.welcome);
//        welcome.setBackgroundColor(0x0000FF);
//        there is an alpha factor in the first byte
//        welcome.setBackgroundColor(0xFF00FF00);

//        ImageView image = (ImageView) findViewById(R.drawable.image);
        ImageView image = (ImageView) findViewById(R.id.image);
//        can be set in layout xml with android:src
        image.setImageResource(R.drawable.z_lufei_smile);

        Button button = (Button) findViewById(R.id.get_word);
//        can be set in layout xml with android:background
        button.setBackgroundColor(0xFF0000FF);
//        button.setBackground(getDrawable(R.drawable.z_red_button));



        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        IOException undeclared
//        FileBasedList fileBasedList = new FileBasedList();
       /* try {
            *//*FileBasedList fileBasedList = new FileBasedList();
            System.out.println("wordList:");
            fileBasedList.printWordList();
            System.out.println();
            System.out.println("lineList:");
            fileBasedList.printLineList();*//*
            printLineList();
            printWordList();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        System.out.println("wordList:");
//        printLineList();
        fileBasedList.printLineList();

        System.out.println();

        System.out.println("lineList");
//        printWordList();
        fileBasedList.printWordList();
        initWordMap();
    }

    void initWordMap() {
        Collections.sort(wordList);
        int i = 0x7f030001;
        for (String item : wordList) {
            wordMap.put(item, i++);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
