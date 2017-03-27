package com.urbas.zadanie1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * Created by marm1 on 22.03.2017.
 */

public class GradeActivity extends AppCompatActivity {

    static final String OCENA = "ocena";
    private ArrayList<ModelOceny> mDane;
    int mLiczbaOcen;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grade_layout);

        if (savedInstanceState == null) {
            Bundle bundle = getIntent().getExtras();
            mLiczbaOcen = bundle.getInt(MainActivity.LICZBA_OCEN);
        } else{
            mLiczbaOcen = savedInstanceState.getInt(MainActivity.LICZBA_OCEN);
            for (int i = 0; i < mLiczbaOcen; i++)
                mDane.add(new ModelOceny("ocena " + (i + 1), savedInstanceState.getInt(OCENA + i)));
        }

        mDane = new ArrayList<ModelOceny>();
        for (int i = 0; i < mLiczbaOcen; i++)
            mDane.add(new ModelOceny("ocena " + (i + 1)));
        Adapter adapter = new Adapter(this, mDane);
        ListView listaOcen = (ListView) findViewById(R.id.grade_window);
        listaOcen.setAdapter(adapter);


    }

    public void backToMainactivity(View view) {
        Bundle bundle = new Bundle();
        spakujOceny(bundle);
        Intent zamiar = new Intent();
        zamiar.putExtras(bundle);
        setResult(RESULT_OK, zamiar);
        finish();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        spakujOceny(outState);
    }
    private void spakujOceny(Bundle bundle) {
        bundle.putInt(MainActivity.LICZBA_OCEN, mLiczbaOcen);
        for (int i = 0; i < mLiczbaOcen; i++)
            bundle.putInt(OCENA + i, mDane.get(i).dajOcene());
    }
}
