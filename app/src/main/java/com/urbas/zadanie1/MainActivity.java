package com.urbas.zadanie1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    public static final String LICZBA_OCEN = "Liczba ocen";
    EditText name;
    EditText surname;
    EditText oceny;
    float srednia;
    boolean koniec=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.name);
        name.setOnFocusChangeListener(
                new View.OnFocusChangeListener() {
                       @Override
                       public void onFocusChange(View v, boolean hasFocus) {
                        if(hasFocus == false && TextUtils.isEmpty(name.getText().toString()))
                        {
                            toastName("Imie");
                        }
                        checkAndShowButton();
                       }
                       });
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(TextUtils.isEmpty(name.getText().toString())){
                    toastName("Imie");
                }
                checkAndShowButton();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        surname = (EditText) findViewById(R.id.surname);
        surname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus == false && TextUtils.isEmpty(surname.getText().toString())){
                    toastName("Nazwisko");
                }
                checkAndShowButton();
            }
        });
        surname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(TextUtils.isEmpty(surname.getText().toString())){
                    toastName("Nazwisko");
                }
                checkAndShowButton();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        oceny = (EditText) findViewById(R.id.oceny);
        oceny.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus == false && TextUtils.isEmpty(oceny.getText().toString()))
                {
                    toastName("Oceny");
                }
                if (!hasFocus && !TextUtils.isEmpty(oceny.getText().toString())){
                    int min = 5, max = 15;
                    if (Integer.valueOf(oceny.getText().toString())<min || Integer.valueOf(oceny.getText().toString())>max){
                        Toast.makeText(MainActivity.this, "Liczby od 5 do 15", Toast.LENGTH_SHORT).show();
                        oceny.setText("");
                    }

                }
                checkAndShowButton();
            }
        });
        oceny.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(oceny.getText().toString())) {
                    toastName("Oceny");
                }
                checkAndShowButton();

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(oceny.getText().toString())){
                checkAndShowButton();
                }
            }
        });


    }

    public void onClick(View view) {
        int min = 5, max = 15;
        if (Integer.valueOf(oceny.getText().toString()) < min || Integer.valueOf(oceny.getText().toString()) > max) {
        Toast.makeText(MainActivity.this, "Liczby od 5 do 15", Toast.LENGTH_SHORT).show();
        oceny.setText("");
    }else{
        Intent intent = new Intent(this, GradeActivity.class);
        intent.putExtra(LICZBA_OCEN, Integer.parseInt(oceny.getText().toString()));
        startActivityForResult(intent, 1);
    }
    }
    public void checkAndShowButton(){
        Button button = (Button) findViewById(R.id.buttonMain);
        if (!name.getText().toString().matches("") && !surname.getText().toString().matches("")
                && !oceny.getText().toString().matches("") && koniec == false){
            button.setVisibility(View.VISIBLE);
        }else
            button.setVisibility(View.GONE);
    }

    public void toastName(String a){
        Toast.makeText(MainActivity.this, "Uzupelnij "+a , Toast.LENGTH_SHORT).show();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

            Button button = (Button) findViewById(R.id.buttonMain);
            button.setVisibility(View.GONE);

            koniec = true;

            Bundle tobolek = data.getExtras();
            int liczbaOcen = tobolek.getInt(LICZBA_OCEN);
            int suma = 0;
            for (int i = 0; i < liczbaOcen; i++)
                suma += tobolek.getInt(GradeActivity.OCENA + i);
            srednia = (float) suma / liczbaOcen;

            TextView poleSrednia = (TextView) findViewById(R.id.srednia);

            String sredniaText = "Twoja srednia: "+String.format("%1.2f",srednia);

            poleSrednia.setText(sredniaText);
            poleSrednia.setVisibility(View.VISIBLE);
            Button buttonRezultat = (Button) findViewById(R.id.buttonRezultat);
            if (srednia >= 3){
                buttonRezultat.setText("Super :)");
                buttonRezultat.setVisibility(View.VISIBLE);
            }else{
                buttonRezultat.setText("Tym razem mi nie poszło");
                buttonRezultat.setVisibility(View.VISIBLE);
            }


        }
    }

    public void onClickRezult(View view) {

        if (srednia <=3){
            Toast.makeText(this, "Gratulacje! Otrzymujesz zaliczenie!", Toast.LENGTH_SHORT).show();
            finish();
        }else{
            Toast.makeText(this, "Gratulacje! Otrzymujesz zaliczenie!", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
