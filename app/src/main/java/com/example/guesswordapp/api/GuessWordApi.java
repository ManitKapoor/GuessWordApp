package com.example.guesswordapp.api;

import com.example.guesswordapp.model.Word;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GuessWordApi {

    @GET("easy")
    Call<Word> getEasyWord();

    @GET("medium")
    Call<Word> getMediumWord();

    @GET("hard")
    Call<Word> getHardWord();

}
