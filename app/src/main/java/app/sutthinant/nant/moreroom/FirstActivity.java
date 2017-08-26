package app.sutthinant.nant.moreroom;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONObject;

public class FirstActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LocationManager locationManager;
    private Criteria criteria;
    private double latADouble, lngADouble;
    private MyConstant myConstant;
    private String[] nameStrings, priceStrings, phoneStrings, imageStrings, optionStrings,
            latStrings, lngStrings, roomStrings, idStrings;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ActionBarDrawerToggle actionBarDrawerToggle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        //Initial View
        initialView();

        //Setup Constant
        setupConstant();

        //Create Map
        createMapFragment();

        //Refresh Controller
        refreshController();

        //Add Controller
        addController();

        //Add Room Drawer
        addRoomDrawer();

        //Refresh Drawer
        refreshDrawer();

        //List Room Drawer
        listRoomDrawer();

    }   //Main Method

    private void listRoomDrawer() {
        TextView textView = (TextView) findViewById(R.id.txtListRoomDrawer);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FirstActivity.this, ListRoomActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawers();
            }
        });
    }

    private void refreshDrawer() {
        TextView textView = (TextView) findViewById(R.id.txtRefreshDrawer);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onResume();
                drawerLayout.closeDrawers();
            }
        });
    }

    private void addRoomDrawer() {
        TextView textView = (TextView) findViewById(R.id.txtAddRoomDrawer);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myIntentToMain();
                drawerLayout.closeDrawers();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    private void initialView() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerFirst);
        toolbar = (Toolbar) findViewById(R.id.toolBarFirst);
        setSupportActionBar(toolbar);

        actionBarDrawerToggle = new ActionBarDrawerToggle(
                FirstActivity.this,
                drawerLayout,
                R.string.open_drawer,
                R.string.close_drawer
        );

        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }   // initialView

    private void addController() {
        ImageView imageView = (ImageView) findViewById(R.id.imvadd);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("19AugV1", "Lat First ==> " + latADouble);
                Log.d("19AugV1", "Lng First ==> " + lngADouble);

                myIntentToMain();
            }
        });
    }

    private void myIntentToMain() {
        Intent intent = new Intent(FirstActivity.this, MainActivity.class);
        intent.putExtra("Lat", latADouble);
        intent.putExtra("Lng", lngADouble);
        startActivity(intent);
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
