package com.example.guesswordapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import com.example.guesswordapp.contants.Constants;
import com.example.guesswordapp.model.Word;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {

    private Word gameWord = null;

    public void goBack() {
        this.finish();
    }

    public void showSuccessDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.success_message)
                .setPositiveButton(R.string.success_ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        goBack();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        goBack();
                    }
                });
        builder.create().show();
    }

    public void showFailueDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.failure_message)
                .setPositiveButton(R.string.failure_ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        goBack();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        goBack();
                    }
                });
        builder.create().show();
    }

    public void checkGuess(View v) {
        EditText guessText = findViewById(R.id.guessEditText);
        if ( guessText.getText().toString().equalsIgnoreCase(gameWord.getValue()) ) {
            showSuccessDialog();
        } else {
            gameWord.decreaseChances();
            if (gameWord.getChances() == 0) {
                showFailueDialog();
            } else {
                setChancesString();
            }
            Toast.makeText(this,R.string.try_again_message, Toast.LENGTH_SHORT).show();
        }

    }

    private void setChancesString() {
        TextView chancesView = findViewById(R.id.chancesview);
        chancesView.setText(gameWord.getChancesString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        gameWord = getIntent().getExtras().getParcelable(Constants.WORD);

        TextView hintTextView = findViewById(R.id.hinttextview);
        hintTextView.setText(gameWord.getDescription());

        setChancesString();
    }

}
