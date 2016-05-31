package com.filipkesteli.drinkingroad4;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.filipkesteli.drinkingroad4.countries.Belarus;
import com.filipkesteli.drinkingroad4.countries.Bhutan;
import com.filipkesteli.drinkingroad4.countries.Croatia;
import com.filipkesteli.drinkingroad4.countries.Ireland;
import com.filipkesteli.drinkingroad4.countries.Israel;
import com.filipkesteli.drinkingroad4.countries.Macedonia;
import com.filipkesteli.drinkingroad4.countries.Pakistan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class PROBAStartFragment extends Fragment {

    private CardView cvStart;

    private CardView cvPakistan;
    private CardView cvBhutan;
    private CardView cvIsrael;
    private CardView cvMacedonia;
    private CardView cvIreland;
    private CardView cvCroatia;
    private CardView cvBelarus;

    private EditText etPlayers;

    private Pakistan pakistan;
    private Bhutan bhutan;
    private Israel israel;
    private Macedonia macedonia;
    private Ireland ireland;
    private Croatia croatia;
    private Belarus belarus;

    private HashMap<CardView,Integer> hashMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_start, container, false);

        initWidgets(v);
        initHashMap();
        initCountries();

        return v;
    }

    private void initWidgets(View v) {
        cvPakistan = (CardView) v.findViewById(R.id.cvPakistan);
        cvBhutan = (CardView) v.findViewById(R.id.cvBhutan);
        cvIsrael = (CardView) v.findViewById(R.id.cvIsrael);
        cvMacedonia = (CardView) v.findViewById(R.id.cvMacedonia);
        cvIreland = (CardView) v.findViewById(R.id.cvIreland);
        cvCroatia = (CardView) v.findViewById(R.id.cvCroatia);
        cvBelarus = (CardView) v.findViewById(R.id.cvBelarus);

        cvStart = (CardView) v.findViewById(R.id.cvStart);

        etPlayers = (EditText) v.findViewById(R.id.etPlayers);
    }

    private void initHashMap() {
        hashMap = new HashMap<>();

        hashMap.put(cvPakistan, R.color.white);
        hashMap.put(cvBhutan, R.color.white);
        hashMap.put(cvIsrael, R.color.white);
        hashMap.put(cvMacedonia, R.color.white);
        hashMap.put(cvIreland, R.color.white);
        hashMap.put(cvCroatia, R.color.white);
        hashMap.put(cvBelarus, R.color.white);
    }

    private void initCountries() {
        pakistan = new Pakistan(1, 4);
        bhutan = new Bhutan(2, 5);
        israel = new Israel(3, 6);
        macedonia = new Macedonia(4, 7);
        ireland = new Ireland(5, 8);
        croatia = new Croatia(6, 9);
        belarus = new Belarus(7, 10);
    }

    private void setupListeners(final CardView cardView) {
    }

    public void setupListeners2() {
        cvPakistan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void setBackgroundColor(final CardView cardView){
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCardViewRed100(cardView);
            }
        });
    }

    private void setCardViewRed100(CardView cardView) {
        cardView.setCardBackgroundColor(R.color.red_100);
        for (Map.Entry<CardView, Integer> entry : hashMap.entrySet()) {
            CardView key = entry.getKey();
            Integer value = entry.getValue();
            if (key != cardView) {
                key.setCardBackgroundColor(R.color.white);
            }
        }
    }

    private void setupListeners2(final CardView cardView) {
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCardColor(cardView);
            }
        });
    }

    private List<CardView> cardViewList;

    private void setCardColor(CardView cardView) {
        cardViewList = new ArrayList<>();
        cardViewList.add(cvPakistan);
        cardViewList.add(cvBhutan);
        cardViewList.add(cvIsrael);
        cardViewList.add(cvMacedonia);
        cardViewList.add(cvIreland);
        cardViewList.add(cvCroatia);
        cardViewList.add(cvBelarus);

        for (CardView cv : cardViewList) {
            if (cv != cardView) {
                cv.setCardBackgroundColor(Color.WHITE);
            } else {
                cv.setCardBackgroundColor(Color.YELLOW);
            }
        }
    }
}


