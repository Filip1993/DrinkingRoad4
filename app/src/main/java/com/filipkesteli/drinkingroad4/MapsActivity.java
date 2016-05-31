package com.filipkesteli.drinkingroad4;

import android.app.DialogFragment;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.maps.android.SphericalUtil;
import com.google.maps.android.heatmaps.HeatmapTileProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    public static final String INTENT_TO_DIALOG_FRAGMENT = "INTENT_TO_DIALOG_FRAGMENT";
    private GoogleMap mMap;

    private FloatingActionButton fab;
    private int counter;

    private List<LatLng> listOfLatLngs = new ArrayList<>();
    private List<LatLng> listOfInitialLatLngs = new ArrayList<>(); //random coordinates
//    private Intent intent = getIntent(); //moram se otvoriti vise metoda u ovom Activityju

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        //Bussiness Logic:
        initWidgets();
        setupListeners();
        initPoints();
    }

    private void initWidgets() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
    }

    private void initPoints() {
        for (int i = 0; i < 10; i++) {
            Random randomLat = new Random();
            double lat = 90 * randomLat.nextDouble();
            Random randomLng = new Random();
            double lng = 180 * randomLng.nextDouble();
            LatLng l = new LatLng(lat, lng);
            listOfInitialLatLngs.add(l);
        }
        addHeatMap(listOfInitialLatLngs);
        listOfLatLngs.addAll(listOfInitialLatLngs);
    }

    private void addHeatMap(List<LatLng> list) {
        // Create a heat map tile provider, passing it the latlngs of the listOfInitialLatLngs:
        HeatmapTileProvider heatmapTileProvider = new HeatmapTileProvider.Builder().data(list).build();
        // Add a tile overlay to the map, using the heat map tile provider.
        mMap.addTileOverlay(new TileOverlayOptions().tileProvider(heatmapTileProvider));
    }

    private void setupListeners() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRuleOfTheGame();
            }
        });
    }

    private void getRuleOfTheGame() {
        Intent intent = getIntent(); //prihvacamo intent
        if (intent.hasExtra(StartFragment.LIST_OF_POSSIBLE_SIPS_EXTRA)) {
            DialogFragment dialogFragment = new PROBAdialog(); //stvaramo novi dijalog
            dialogFragment.show(getFragmentManager(), null);

            LatLng tryLatLnt = getRandomPoint();
            ArrayList<Integer> arrayList = intent.getIntegerArrayListExtra(StartFragment.LIST_OF_POSSIBLE_SIPS_EXTRA);

            for (LatLng l : listOfLatLngs) {
                Double distanceInMeters = SphericalUtil.computeDistanceBetween(tryLatLnt, l);
                ArrayList<Double> doubleArrayList = new ArrayList<>();
                doubleArrayList.add(distanceInMeters);
                Double minDistance = Collections.min(doubleArrayList);

                if (minDistance <= 10000) {
                    counter = arrayList.get(3);
                } else if (minDistance <= 10000 && minDistance <= 100000) {
                    counter = arrayList.get(2);
                } else if (minDistance <= 100000 && minDistance <= 1000000) {
                    counter = arrayList.get(1);
                } else if (minDistance <= 10000000 && minDistance <= 10000000) {
                    counter = arrayList.get(0);
                }
            }
            addHeatMap(listOfLatLngs);
            Intent intentToDialog = new Intent(MapsActivity.this, PROBAdialog.class);
            intentToDialog.putExtra(INTENT_TO_DIALOG_FRAGMENT, counter); //int extra -> number of sips to drink
        }
    }

    private LatLng getRandomPoint() {
        Random randomLat = new Random();
        double lat = 90 * randomLat.nextDouble();
        Random randomLng = new Random();
        double lng = 180 * randomLng.nextDouble();
        LatLng l = new LatLng(lat, lng);
        listOfLatLngs.add(l);
        return l;
    }
}

