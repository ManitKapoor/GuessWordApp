package com.example.guesswordapp;

import android.content.Intent;
import android.os.Bundle;

import com.example.guesswordapp.contants.Constants;
import com.example.guesswordapp.model.Word;
import com.example.guesswordapp.service.GuessWordService;
import com.example.guesswordapp.service.IGuessWordListener;
import com.example.guesswordapp.tasks.NetworkServiceTask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    boolean isLoading;

    public class MainActivityListener implements IGuessWordListener {

        private MainActivity activity;

        public MainActivityListener(MainActivity activity) {
            this.activity = activity;
        }

        @Override
        public void onRequestComplete(Word word) {
            Intent intent = new Intent(activity, GameActivity.class);
            System.out.println(word.getDescription());
            activity.isLoading = false;
            intent.putExtra(Constants.WORD,word);
            activity.startActivity(intent);
        }

        @Override
        public void onRequestError() {
            Toast.makeText(activity,R.string.no_net,Toast.LENGTH_SHORT).show();
        }
    }


    public void selectHardMode(View view) {
        this.loadGame(Word.Type.hard);
    }

    public void selectMediumMode(View view) {
        this.loadGame(Word.Type.medium);
    }

    public void selectEasyMode(View view) {
        this.loadGame(Word.Type.easy);
    }

    private void loadGame(Word.Type wordType) {
        MainActivityListener mainActivityListener = new MainActivityListener(this);
        if (isLoading) {
            return;
        }
        isLoading = true;
        Toast.makeText(this,R.string.loading,Toast.LENGTH_LONG).show();
        new NetworkServiceTask(mainActivityListener).execute(wordType.toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isLoading = false;
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}
