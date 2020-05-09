package com.example.fakemessage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class NewActivity extends AppCompatActivity {

    public static final String ANSWER_ID = NewActivity.class.getSimpleName() + ".MESSAGE";
    TextView textMessageRecived;
    EditText textAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        Intent intent = getIntent();
        this.textMessageRecived = findViewById(R.id.textView3);
        this.textAnswer = findViewById(R.id.editTextResposta);

        if (intent.hasExtra(MainActivity.EXTRA_ID)) {
            String messageRecivied = intent.getStringExtra(MainActivity.EXTRA_ID);
            this.textMessageRecived.setText(messageRecivied);
        }
        else {
            this.textMessageRecived.setText("Não há mensagem");
        }
    }

    /*
    * MANDA O RETORNO PRA ACTIVITY ANTERIOR
    * */
    public void sendAnswer(View view) {
        String answer = this.textAnswer.getText().toString();
        Intent answerIntent = new Intent();
        answerIntent.putExtra(this.ANSWER_ID, answer);
        setResult(RESULT_OK, answerIntent);
        finish();
    }
}
