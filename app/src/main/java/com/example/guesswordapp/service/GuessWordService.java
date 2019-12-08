package com.example.guesswordapp.service;

import com.example.guesswordapp.api.GuessWordApi;
import com.example.guesswordapp.model.Word;

import java.io.IOException;

import retrofit2.Retrofit;

public class GuessWordService {

    private static GuessWordApi getApi() {
        Retrofit retrofit = NetworkClient.getRetrofitClient();
        return retrofit.create(GuessWordApi.class);
    }

    public static Word getEasyWord() throws IOException {
        GuessWordApi guessWordApi = getApi();
        return guessWordApi.getEasyWord().execute().body();
    }

    public static Word getMediumWord() throws IOException {
        GuessWordApi guessWordApi = getApi();
        return guessWordApi.getMediumWord().execute().body();
    }

    public static Word getHardWord() throws IOException {
        GuessWordApi guessWordApi = getApi();
        return guessWordApi.getHardWord().execute().body();
    }

}
