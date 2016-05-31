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
import com.google.maps.android.heatmaps.HeatmapTileProvider;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private FloatingActionButton fab;

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

        //Bussiness Logic:
        initWidgets();
        setupListeners();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        //addHeatMap();
        //proba();
        addHeatMap();
    }

    private void initWidgets() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
    }

    private void setupListeners() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new PROBAdialog();
                dialogFragment.show(getFragmentManager(), null);
            }
        });
    }

    private void proba() {
        Intent intent = getIntent();
        if (intent.hasExtra(StartFragment.LIST_OF_POSSIBLE_SIPS_EXTRA) && intent.hasExtra(StartFragment.NUMBER_OF_PLAYERS_EXTRA)) {
            Toast.makeText(MapsActivity.this,
                    intent.getIntegerArrayListExtra(StartFragment.LIST_OF_POSSIBLE_SIPS_EXTRA).size()
                            + "\n"
                            + intent.getIntExtra(StartFragment.NUMBER_OF_PLAYERS_EXTRA, -199)
                    , Toast.LENGTH_SHORT).show();
        }
        /*Toast.makeText(MapsActivity.this, startFragment.getListOfPossibleSips().size() + "\n" + startFragment.getNumberOfPlayers().toString() + "\n" + startFragment.numberOfPlayers, Toast.LENGTH_SHORT).show();*/
    }

    /**
     * Add a simple heat map to the map
     */
    private void addHeatMap() {
        Intent intent = getIntent();
        if (intent.hasExtra(StartFragment.LIST_OF_POSSIBLE_SIPS_EXTRA)) {
            for (int i = 0; i < 10; i++) {
                Random randomLat = new Random();
                double lat = 90 * randomLat.nextDouble();
                Random randomLng = new Random();
                double lng = 180 * randomLng.nextDouble();
                LatLng l = new LatLng(lat, lng);
                listOfInitialLatLngs.add(l);
            }


            // Get the data: latitude/longitude positions
            LatLng l = new LatLng(3, 4);
            listOfLatLngs.add(l);

            // Create a heat map tile provider, passing it the latlngs of the listOfInitialLatLngs:
            HeatmapTileProvider provider = new HeatmapTileProvider.Builder().data(listOfInitialLatLngs).build();
            // Add a tile overlay to the map, using the heat map tile provider.
            mMap.addTileOverlay(new TileOverlayOptions().tileProvider(provider));
        }
    }
}

