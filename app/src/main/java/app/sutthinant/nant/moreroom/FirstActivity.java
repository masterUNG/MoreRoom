package app.sutthinant.nant.moreroom;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
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

import org.json.JSONArray;
import org.json.JSONObject;

public class FirstActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LocationManager locationManager;
    private Criteria criteria;
    private double latADouble, lngADouble;
    private MyConstant myConstant;
    private String[] nameStrings, priceStrings, phoneStrings, imageStrings, optionStrings,
            latStrings, lngStrings, roomStrings, idStrings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        //Setup Constant
        setupConstant();

        //Create Map
        createMapFragment();

        //Refresh Controller
        refreshController();

        //Add Controller
        addController();

    }   //Main Method

    private void addController() {
        ImageView imageView = (ImageView) findViewById(R.id.imvadd);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("19AugV1", "Lat First ==> " + latADouble);
                Log.d("19AugV1", "Lng First ==> " + lngADouble);

                Intent intent = new Intent(FirstActivity.this, MainActivity.class);
                intent.putExtra("Lat", latADouble);
                intent.putExtra("Lng", lngADouble);
                startActivity(intent);
            }
        });
    }

    private void refreshController() {
        ImageView imageView = (ImageView) findViewById(R.id.imvRefresh);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onResume();

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        //For GPS
        Location gpsLocation = myFindLocation(LocationManager.GPS_PROVIDER);
        if (gpsLocation != null) {
            latADouble = gpsLocation.getLatitude();
            lngADouble = gpsLocation.getLongitude();

        }

        //For Network
        Location networkLocation = myFindLocation(LocationManager.NETWORK_PROVIDER);
        if (networkLocation != null) {
            latADouble = networkLocation.getLatitude();
            lngADouble = networkLocation.getLongitude();

        }


        Log.d("15JulyV1", "Lat ==>" + latADouble);
        Log.d("15JulyV1", "Lng ==>" + lngADouble);

        try {
            mMap.clear();
            createMap();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        locationManager.removeUpdates(locationListener);
    }

    public Location myFindLocation(String strProvider) {


        Location location = null;
        if (locationManager.isProviderEnabled(strProvider)) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return null;
            }
            locationManager.requestLocationUpdates(strProvider, 1000, 10, locationListener);
            location = locationManager.getLastKnownLocation(strProvider);

        }


        return location;
    }


    public LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            lngADouble = location.getLatitude();
            lngADouble = location.getLongitude();

        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };

    private void setupConstant() {

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        myConstant = new MyConstant();
        latADouble = myConstant.getLatADouble();
        lngADouble = myConstant.getLngADouble();


    }

    private void createMapFragment() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //Create Map
        createMap();


    }   //onMapReady

    private void createMap() {
        LatLng latLng = new LatLng(latADouble, lngADouble);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));

        //Create Marker
        createMarker(latLng, "คุณอยู่ที่นี่", R.mipmap.ic_user_marker);

        String tag = "19AugV4";


        //Create Room Marker
        try {
            MyConstant myConstant = new MyConstant();
            GetAllData getAllData = new GetAllData(FirstActivity.this);
            getAllData.execute(myConstant.getUrlGetRoomString());

            String strJSON = getAllData.get();
            Log.d(tag, "JSON ==>" + strJSON);

            JSONArray jsonArray = new JSONArray(strJSON);
            idStrings = new String[jsonArray.length()];
            nameStrings = new String[jsonArray.length()];
            priceStrings = new String[jsonArray.length()];
            phoneStrings = new String[jsonArray.length()];
            imageStrings = new String[jsonArray.length()];
            optionStrings = new String[jsonArray.length()];
            latStrings = new String[jsonArray.length()];
            lngStrings = new String[jsonArray.length()];
            roomStrings = new String[jsonArray.length()];

            for (int i = 0; i < jsonArray.length(); i += 1) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                nameStrings[i] = jsonObject.getString("id");
                nameStrings[i] = jsonObject.getString("Name");
                priceStrings[i] = jsonObject.getString("Price");
                phoneStrings[i] = jsonObject.getString("Phone");
                imageStrings[i] = jsonObject.getString("Image");
                optionStrings[i] = jsonObject.getString("Option");
                latStrings[i] = jsonObject.getString("Lat");
                lngStrings[i] = jsonObject.getString("Lng");
                roomStrings[i] = jsonObject.getString("Room");

                double doulat = Double.parseDouble(latStrings[i]);
                double doulng = Double.parseDouble(lngStrings[i]);
                LatLng latLng1 = new LatLng(doulat, doulng);

                int[] iconInts = myConstant.getRoomInts();
                int intdex = Integer.parseInt(roomStrings[i]);

                createMarker(latLng1, nameStrings[i], iconInts[intdex]);


            }  //for

        } catch (Exception e) {
            Log.d(tag, "e createMap ==>" + e.toString());

        }


    }      //createMap

    private void createMarker(LatLng latLng, String strTitle, int intIcon) {
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title(strTitle);
        markerOptions.icon(BitmapDescriptorFactory.fromResource(intIcon));
        mMap.addMarker(markerOptions);
    }
} //Main Class
