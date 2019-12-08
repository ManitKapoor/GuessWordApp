package com.example.guesswordapp.tasks;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.example.guesswordapp.GameActivity;
import com.example.guesswordapp.contants.Constants;
import com.example.guesswordapp.model.Word;
import com.example.guesswordapp.service.GuessWordService;
import com.example.guesswordapp.service.IGuessWordListener;

public class NetworkServiceTask extends AsyncTask<String, Integer, Word> {

    private IGuessWordListener iGuessWordListener;

    public NetworkServiceTask(IGuessWordListener iGuessWordListener) {
        this.iGuessWordListener = iGuessWordListener;
    }

    protected Word doInBackground(String... params) {
        try {
            switch (Word.Type.valueOf(params[0])) {
                case easy:
                    return GuessWordService.getEasyWord();
                case medium:
                    return GuessWordService.getMediumWord();
                case hard:
                    return GuessWordService.getHardWord();
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
        return null;
    }

    protected void onPostExecute(Word result) {
        if (result == null) {
            iGuessWordListener.onRequestError();
        } else {
            iGuessWordListener.onRequestComplete(result);
        }
    }
}
