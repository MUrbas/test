package com.urbas.zadanie1;

import android.app.Activity;
import android.support.annotation.IdRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by marm1 on 23.03.2017.
 */

public class Adapter extends ArrayAdapter<ModelOceny> {

    private List<ModelOceny> listaOcen;
    private Activity kontekst;

    public Adapter(Activity kontekst, List<ModelOceny> listaOcen) {
        super(kontekst, R.layout.grade_layout, listaOcen);
        this.kontekst = kontekst;
        this.listaOcen = listaOcen;
    }


    @Override
    public View getView(int numerWiersza, View widokDoRecyklingu, ViewGroup parent) {
        View widok = null;
        //tworzenie nowego wiersza
        if (widokDoRecyklingu == null) {
            LayoutInflater pompka = kontekst.getLayoutInflater();
            widok = pompka.inflate(R.layout.lista_ocen, null);


            final RadioGroup grupaOceny = (RadioGroup) widok.findViewById(R.id.radio_group);
            grupaOceny.setOnCheckedChangeListener(
                    new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup group,
                                                     int checkedId) {
                            aktualizujModelOceny(group, checkedId);
                        }
                    });
            grupaOceny.setTag(listaOcen.get(numerWiersza));
            //aktualizacja istniejącego wiersza
        }else{
                widok = widokDoRecyklingu;
                RadioGroup grupaOceny = (RadioGroup) widok.findViewById(R.id.radio_group);
                //powiązanie grupy przycisków z obiektem w modelu
                grupaOceny.setTag(listaOcen.get(numerWiersza));
            }

            TextView etykieta = (TextView) widok.findViewById(R.id.wierszOcenaEtykieta);
            //ustawienie tekstu etykiety na podstawie modelu
            etykieta.setText(listaOcen.get(numerWiersza).dajNazwe());
            RadioGroup grupaOceny = (RadioGroup) widok.findViewById(R.id.radio_group);
            ustawOcene(grupaOceny, numerWiersza);

            return widok;
        }

    private void aktualizujModelOceny(RadioGroup group, int checkedId) {
        ModelOceny element = (ModelOceny) group.getTag();
        switch (checkedId) {
            case R.id.ocena_dwa:
                element.ustawOcene(2);
                break;
            case R.id.ocena_trzy:
                element.ustawOcene(3);
                break;
            case R.id.ocena_cztery:
                element.ustawOcene(4);
                break;
            case R.id.ocena_piec:
                element.ustawOcene(5);
                break;
        }
    }


    private void ustawOcene(RadioGroup grupaOceny,
                            int numerWiersza) {
        switch (listaOcen.get(numerWiersza).dajOcene()) {
            case 2:
                grupaOceny.check(R.id.ocena_dwa);
                break;
            case 3:
                grupaOceny.check(R.id.ocena_trzy);
                break;
            case 4:
                grupaOceny.check(R.id.ocena_cztery);
                break;
            case 5:
                grupaOceny.check(R.id.ocena_piec);
                break;
        }
    }
}
/*
    //tworzenie nowego wiersza
    @Override
    public View getView(int numerWiersza, View widokDoRecyklingu, ViewGroup parent)
    {

        View widok = null;
        ViewHolder holder;

        if (widokDoRecyklingu == null)
        {
            widokDoRecyklingu = LayoutInflater.from(kontekst).inflate(R.layout.lista_ocen,parent,false);
            holder = new ViewHolder();
            holder.radioGroup = (RadioGroup) widokDoRecyklingu.findViewById(R.id.radio_group);
            holder.gradeLabel = (TextView) widokDoRecyklingu.findViewById(R.id.wierszOcenaEtykieta);
            widokDoRecyklingu.setTag(holder);
        }
        else
        {
            widok = widokDoRecyklingu;
            holder = (ViewHolder) widokDoRecyklingu.getTag();
            holder.radioGroup.clearCheck();
        }

        final ModelOceny ocena = listaOcen.get(numerWiersza);
        holder.gradeLabel.setText(ocena.getLabel());
        holder.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (checkedId == R.id.ocena_dwa) {
                    ocena.setValue(2);
                }else if (checkedId == R.id.ocena_trzy){
                    ocena.setValue(3);
                }else if (checkedId == R.id.ocena_cztery){
                    ocena.setValue(4);
                }else if (checkedId == R.id.ocena_piec){
                    ocena.setValue(5);
                }
            }
        });

        if (ocena.getValue() == 2) {
            holder.radioGroup.check(R.id.ocena_dwa);
        } else if (ocena.getValue() == 3) {
            holder.radioGroup.check(R.id.ocena_trzy);
        } else if (ocena.getValue() == 4) {
            holder.radioGroup.check(R.id.ocena_cztery);
        } else if (ocena.getValue() == 5) {
            holder.radioGroup.check(R.id.ocena_piec);
        }


        return widok;
    }
}
*/

class ViewHolder{
    TextView gradeLabel;
    RadioGroup radioGroup;
}