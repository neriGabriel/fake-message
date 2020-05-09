package com.example.fakemessage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_ID = MainActivity.class.getSimpleName() + ".MESSAGE";
    public static final int REQUEST_ANSWER = 1;
    private EditText edtMessage;
    private TextView textResultMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.edtMessage = findViewById(R.id.edtMessage);
        this.textResultMain = findViewById(R.id.textResultMain);

        if(savedInstanceState == null) {
            Toast.makeText(this, "Instance bundle null", Toast.LENGTH_LONG).show();
        } else {
            this.textResultMain.setText(savedInstanceState.getString("savedtext"));
        }
    }

    public void callNewActivity(View view) {
        Intent intent = new Intent(this, NewActivity.class);
        intent.putExtra(EXTRA_ID, this.edtMessage.getText().toString());
        this.edtMessage.getText().clear();
        startActivity(intent);
    }

    /*
    * CHAMA UMA ACTIVITY ESPERANDO POR UMA RESPOSTA
    * */
    public void callNewActivityForResult(View view) {
        Intent intent = new Intent(this, NewActivity.class);
        intent.putExtra(this.EXTRA_ID, this.edtMessage.getText().toString());
        this.edtMessage.getText().clear();
        startActivityForResult(intent, this.REQUEST_ANSWER);
    }

    /*
    * PEGA RETORNO DA OUTRA ACTIVITY
    * */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(requestCode == REQUEST_ANSWER) {

            switch(resultCode) {
                case RESULT_OK:
                        String reciviedAnswer = data.getStringExtra(NewActivity.ANSWER_ID);
                        this.textResultMain.setText(reciviedAnswer);
                break;

                case RESULT_CANCELED:
                    break;
            }
        }
    }

    /*
    * VALIDAÇÃO QUANDO GIRAR A TELA E NÃO PERDER O STATE
    * */
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        if(!textResultMain.getText().toString().isEmpty()) {
            outState.putString("savedtext", textResultMain.getText().toString());
        }
    }
}
