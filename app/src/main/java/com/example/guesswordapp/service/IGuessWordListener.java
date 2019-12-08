package com.example.guesswordapp.service;

import com.example.guesswordapp.model.Word;

public interface IGuessWordListener {
    void onRequestComplete(Word obj);
    void onRequestError();
}
