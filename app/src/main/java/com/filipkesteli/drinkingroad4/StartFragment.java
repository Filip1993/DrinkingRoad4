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
import android.widget.Toast;

import com.filipkesteli.drinkingroad4.countries.Belarus;
import com.filipkesteli.drinkingroad4.countries.Bhutan;
import com.filipkesteli.drinkingroad4.countries.Croatia;
import com.filipkesteli.drinkingroad4.countries.Ireland;
import com.filipkesteli.drinkingroad4.countries.Israel;
import com.filipkesteli.drinkingroad4.countries.Macedonia;
import com.filipkesteli.drinkingroad4.countries.Pakistan;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class StartFragment extends Fragment {

    public static final String LIST_OF_POSSIBLE_SIPS_EXTRA = "LIST_OF_POSSIBLE_SIPS_EXTRA";
    public static final String NUMBER_OF_PLAYERS_EXTRA = "NUMBER_OF_PLAYERS_EXTRA";
    //View variables
    private CardView cvStart;
    private CardView cvPakistan;
    private CardView cvBhutan;
    private CardView cvIsrael;
    private CardView cvMacedonia;
    private CardView cvIreland;
    private CardView cvCroatia;
    private CardView cvBelarus;
    private EditText etPlayers;

    //Bussiness logic variables
    private List<CardView> cardViewList = new ArrayList<>();
    private List<Integer> listOfPossibleSips = new ArrayList<>();
    public Integer numberOfPlayers;

    //Objects from my own classes
    private Pakistan pakistan;
    private Bhutan bhutan;
    private Israel israel;
    private Macedonia macedonia;
    private Ireland ireland;
    private Croatia croatia;
    private Belarus belarus;

    public StartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_start, container, false);
        initWidgets(v);
        initCoutries();
        initBussinessLogicVariables();
        setupDefaultValues();
        setupListeners();
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

    private void initCoutries() {
        pakistan = new Pakistan(1, 4);
        bhutan = new Bhutan(2, 5);
        israel = new Israel(3, 6);
        macedonia = new Macedonia(4, 7);
        ireland = new Ireland(5, 8);
        croatia = new Croatia(6, 9);
        belarus = new Belarus(7, 10);
    }

    private void initBussinessLogicVariables() {
        cardViewList.add(cvPakistan);
        cardViewList.add(cvBhutan);
        cardViewList.add(cvIsrael);
        cardViewList.add(cvMacedonia);
        cardViewList.add(cvIreland);
        cardViewList.add(cvCroatia);
        cardViewList.add(cvBelarus);
    }

    //Problematicno:
    private void setupDefaultValues() {
        //default values:
        cvPakistan.setCardBackgroundColor(Color.YELLOW);
        listOfPossibleSips = pakistan.getPossibleSips();
        etPlayers.setText(5 + "");
        numberOfPlayers = 5;
        Toast.makeText(getActivity(), listOfPossibleSips.size() + "\n" + numberOfPlayers, Toast.LENGTH_SHORT).show();
    }

    private boolean isFormOK() {
        if (etPlayers.getText().toString().trim().length() == 0) {
            Toast.makeText(getActivity(), "Please insert number of players", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void setupListeners() {
        cvStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MapsActivity.class);
                if (isFormOK()) {
                    ArrayList<Integer> arrayList = (ArrayList<Integer>) listOfPossibleSips;
                    intent.putIntegerArrayListExtra(LIST_OF_POSSIBLE_SIPS_EXTRA, arrayList);
                    numberOfPlayers = Integer.parseInt(etPlayers.getText().toString() + "");
                    intent.putExtra(NUMBER_OF_PLAYERS_EXTRA, numberOfPlayers);
                    startActivity(intent);
                }
            }
        });

        cvPakistan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCardColor(cvPakistan);
                listOfPossibleSips = pakistan.getPossibleSips();
                Toast.makeText(getActivity(), "Minimum sips: " + listOfPossibleSips.get(0) + "\n" + "Maximum sips: " + listOfPossibleSips.get(listOfPossibleSips.size() - 1), Toast.LENGTH_SHORT).show();
            }
        });
        cvBhutan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCardColor(cvBhutan);
                listOfPossibleSips = bhutan.getPossibleSips();
                Toast.makeText(getActivity(), "Minimum sips: " + listOfPossibleSips.get(0) + "\n" + "Maximum sips: " + listOfPossibleSips.get(listOfPossibleSips.size() - 1), Toast.LENGTH_SHORT).show();
            }
        });
        cvIsrael.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCardColor(cvIsrael);
                listOfPossibleSips = israel.getPossibleSips();
                Toast.makeText(getActivity(), "Minimum sips: " + listOfPossibleSips.get(0) + "\n" + "Maximum sips: " + listOfPossibleSips.get(listOfPossibleSips.size() - 1), Toast.LENGTH_SHORT).show();
            }
        });
        cvMacedonia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCardColor(cvMacedonia);
                listOfPossibleSips = macedonia.getPossibleSips();
                Toast.makeText(getActivity(), "Minimum sips: " + listOfPossibleSips.get(0) + "\n" + "Maximum sips: " + listOfPossibleSips.get(listOfPossibleSips.size() - 1), Toast.LENGTH_SHORT).show();
            }
        });
        cvIreland.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCardColor(cvIreland);
                listOfPossibleSips = ireland.getPossibleSips();
                Toast.makeText(getActivity(), "Minimum sips: " + listOfPossibleSips.get(0) + "\n" + "Maximum sips: " + listOfPossibleSips.get(listOfPossibleSips.size() - 1), Toast.LENGTH_SHORT).show();
            }
        });
        cvCroatia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCardColor(cvCroatia);
                listOfPossibleSips = croatia.getPossibleSips();
                Toast.makeText(getActivity(), "Minimum sips: " + listOfPossibleSips.get(0) + "\n" + "Maximum sips: " + listOfPossibleSips.get(listOfPossibleSips.size() - 1), Toast.LENGTH_SHORT).show();
            }
        });
        cvBelarus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCardColor(cvBelarus);
                listOfPossibleSips = belarus.getPossibleSips();
                Toast.makeText(getActivity(), "Minimum sips: " + listOfPossibleSips.get(0) + "\n" + "Maximum sips: " + listOfPossibleSips.get(listOfPossibleSips.size() - 1), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setCardColor(CardView cardView) {
        for (CardView cv : cardViewList) {
            if (cv != cardView) {
                cv.setCardBackgroundColor(Color.WHITE);
            } else {
                cv.setCardBackgroundColor(Color.YELLOW);
            }
        }
    }
}
