package app.sutthinant.nant.moreroom;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private double latADouble;
    private double lngADouble;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Setup Map
        setupMap();

        //Back Controller
        backController();


    }   //Main Method

    private void getValueFromIntent() {
        MyConstant myConstant = new MyConstant();
        latADouble = getIntent().getDoubleExtra("Lat", myConstant.getLngADouble());
        lngADouble = getIntent().getDoubleExtra("Lng", myConstant.getLngADouble());

        Log.d("19AugV1", "Lat Map ==> " + latADouble);
        Log.d("19AugV1", "Lng Map ==> " + lngADouble);
    }

    private void backController() {
        ImageView imageView = (ImageView) findViewById(R.id.imvBack);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MapsActivity.this, MainActivity.class);
                intent.putExtra("Lat", latADouble);
                intent.putExtra("Lng", lngADouble);
                setResult(1000, intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    private void setupMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //Get Value From Inten
        getValueFromIntent();

        Log.d("19AugV1", "Lat onMap ==> " + latADouble);
        Log.d("19AugV1", "Lng onMap ==> " + lngADouble);

        LatLng latLng = new LatLng(latADouble, lngADouble);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));

        myCreateMarker(latLng);

        //Click on Map

        clickOnMap();


    } //onMapReady

    private void clickOnMap() {
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                mMap.clear();
                myCreateMarker(latLng);
                latADouble = latLng.latitude;
                lngADouble = latLng.longitude;
                Log.d("16JulyV4","Lat ==>"+ latADouble);
                Log.d("16JulyV4","Lng ==>"+ lngADouble);

            }
        });
    }

    private void myCreateMarker(LatLng latLng) {

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_user_marker));
        mMap.addMarker(markerOptions);

    }

}  // Main Class
