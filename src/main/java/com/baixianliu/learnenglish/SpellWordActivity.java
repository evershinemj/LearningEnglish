package com.baixianliu.learnenglish;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class SpellWordActivity extends Activity {

    private ArrayList<String> wordList;
    private ArrayList<String> lineList;

    private HashMap<String, Integer> wordMap = new HashMap<>();

    private int pictureCount = 0x7f030001;

    private int wordIndex = 0;

//    FileBasedList fileBasedList = new FileBasedList();
//    FileBasedList singleton = FileBasedList.getInstance();
    private FileBasedList fileBasedList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spell_word);
    }

    public InputStream getFileAsset(String fileName) throws IOException {
        AssetManager assetManager = getAssets();
        InputStream asset = assetManager.open(fileName);
        return asset;
    }

    @Override
    protected void onStart() {
        super.onStart();
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.sort(wordList);
        for (String item : wordList) {
            int i = 0x7f030001;
            wordMap.put(item, i++);
        }
    }

    public void init() throws IOException {
        InputStream asset = getFileAsset("basic_words");
        this.fileBasedList = new FileBasedList(asset);

        this.wordList = fileBasedList.getWordList();
//        this.lineList = fileBasedList.getLineList();
    }

    public void onClickGoBack(View view) {
        Intent intent = new Intent(this, RandomWordActivity.class);
        startActivity(intent);
    }

    public void onClickCheckWord(View view) {
//        ArrayList<String> wordList = singleton.getWordList();
        EditText spell = (EditText) findViewById(R.id.spell);
        TextView result = (TextView) findViewById(R.id.result);
        String word = spell.getText().toString();

//        if (wordList.contains(word)) {
//            result.setText("您输入的单词在词汇表中");
//        } else {
//            result.setText("您输入的单词不在词汇表中");
//        }
//

//        IndexOutOfBoundsException thrown because pictureCount is too big
//        should define another instance variable
//        if (word.equals(wordList.get(pictureCount - 1))) {

//        if (wordIndex > wordList.size()) {

//        changePicture() should be called before this check
//        if (wordIndex == wordList.size()) {
//            result.setText("没有其他单词啦");
//            return;
//        }

        if (wordIndex == wordList.size()) {
            result.setText("没有其他单词啦");
            return;
        }

//        if (word.equals(wordList.get(wordIndex++))) {
//        should increment wordIndex inside if block!
        if (word.equals(wordList.get(wordIndex))) {
            result.setText("恭喜您，回答正确！");


            changePicture();
//            result.setText("");
            spell.setText("");
            wordIndex++;
        } else {
            result.setText("拼写出了点问题，继续努力哦");
//            result.setText("");
            spell.setText("");
        }
//        changePicture();
//        should move into the if block
    }

    public void changePicture() {
        if (wordIndex == wordList.size() - 1) {
            return;
        }
        ImageView picture = findViewById(R.id.spell_image);
        picture.setImageResource(++pictureCount);
    }
}
