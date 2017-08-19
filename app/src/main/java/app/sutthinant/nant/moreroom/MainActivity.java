package app.sutthinant.nant.moreroom;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.jibble.simpleftp.SimpleFTP;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Explicit
    private String nameString, priceString, phoneString,
            imageString, optionString, latString, lngString;
    private EditText nameEditText, priceEditText, phoneEditText;
    private int indexAnInt = 0;
    private int[] picInts = new int[]{R.id.imvShowPic1,
            R.id.imvShowPic2, R.id.imvShowPic3, R.id.imvShowPic4,};

    private Uri uri;
    private ArrayList<String> stringArrayList, nameImageArrayList, optionArrayList;
    private String[] pathImageStrings;
    private MyConstant myConstant;
    private boolean[] optionBoolean = new boolean[8];
    private double latADouble, lngADouble;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setup Constant
        setupConstant();

        //Back Controller
        backController();

        //Initial View

        initailView();

        //Add Picture Controller
        addPictureController();

        //Show LatLng
        showLatLng();

        //Setup Location
        setupLocation();


        //Aircondition controller
        airConditionController();

        //CCTV controller
        ccTvController();

        //wifi Controller
        wiFiController();

        //KeyCard Controller
        keyCardController();

        //Car Controller
        carController();

        //Motocycle Controller
        motocycleController();

        //Furniture Controller
        furnitureController();

        //Satellite Controller
        satelliteController();


    }   //Main Method


    private void setupLocation() {
        ImageView imageView = (ImageView) findViewById(R.id.imvSetupLatLng);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                intent.putExtra("Lat", latADouble);
                intent.putExtra("Lng", lngADouble);

                Log.d("19AugV1", "Lat Main ==> " + latADouble);
                Log.d("19AugV1", "Lng Main ==> " + lngADouble);


                startActivityForResult(intent, 1000);

            }
        });
    }

    private void showLatLng() {
        TextView textView = (TextView) findViewById(R.id.txtShowLatLng);
        String strLat = String.format("%.4f", latADouble);
        String strLng = String.format("%.4f", lngADouble);

        textView.setText("Lat =" + strLat + " , Lng =" + strLng);
    }

    private void airConditionController() {
        final String tag = "16JulyV3";
        final ImageView imageView = (ImageView) findViewById(R.id.imvAirCondition);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                optionBoolean[0] = !optionBoolean[0];
                MyConstant myConstant = new MyConstant();

                int[] ints = myConstant.getAirConditionInts();

                for (int i = 0; i < ints.length; i += 1) {
//                    Log.d(tag, "ints[" + i + "] ==>" + ints[i]);
                }

//                Log.d(tag, "optionBooleen ==>" + optionBoolean[0]);

                if (optionBoolean[0]) {
                    imageView.setImageResource(ints[1]);
                } else {
                    imageView.setImageResource(ints[0]);

                }

            } // On Click
        });
    }

    private void ccTvController() {

        final ImageView imageView = (ImageView) findViewById(R.id.imvCctv);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                optionBoolean[1] = !optionBoolean[1];
                MyConstant myConstant = new MyConstant();

                int[] ints = myConstant.getCcTvInts();

                for (int i = 0; i < ints.length; i += 1) {
//                    Log.d(tag, "ints[" + i + "] ==>" + ints[i]);
                }

//                Log.d(tag, "optionBooleen ==>" + optionBoolean[0]);

                if (optionBoolean[1]) {
                    imageView.setImageResource(ints[1]);
                } else {
                    imageView.setImageResource(ints[0]);

                }

            } // On Click
        });
    }

    private void wiFiController() {

        final ImageView imageView = (ImageView) findViewById(R.id.imvWifi);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                optionBoolean[2] = !optionBoolean[2];
                MyConstant myConstant = new MyConstant();

                int[] ints = myConstant.getWiFiInts();

                for (int i = 0; i < ints.length; i += 1) {
//                    Log.d(tag, "ints[" + i + "] ==>" + ints[i]);
                }

//                Log.d(tag, "optionBooleen ==>" + optionBoolean[0]);

                if (optionBoolean[2]) {
                    imageView.setImageResource(ints[1]);
                } else {
                    imageView.setImageResource(ints[0]);

                }

            } // On Click
        });


    }

    private void keyCardController() {

        final ImageView imageView = (ImageView) findViewById(R.id.imvKeyCard);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                optionBoolean[3] = !optionBoolean[3];
                MyConstant myConstant = new MyConstant();

                int[] ints = myConstant.getKeyInts();

                for (int i = 0; i < ints.length; i += 1) {
//                    Log.d(tag, "ints[" + i + "] ==>" + ints[i]);
                }

//                Log.d(tag, "optionBooleen ==>" + optionBoolean[0]);

                if (optionBoolean[3]) {
                    imageView.setImageResource(ints[1]);
                } else {
                    imageView.setImageResource(ints[0]);

                }

            } // On Click
        });
    }

    private void carController() {

        final ImageView imageView = (ImageView) findViewById(R.id.imvCar);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                optionBoolean[4] = !optionBoolean[4];
                MyConstant myConstant = new MyConstant();

                int[] ints = myConstant.getCarInts();

                for (int i = 0; i < ints.length; i += 1) {
//                    Log.d(tag, "ints[" + i + "] ==>" + ints[i]);
                }

//                Log.d(tag, "optionBooleen ==>" + optionBoolean[0]);

                if (optionBoolean[4]) {
                    imageView.setImageResource(ints[1]);
                } else {
                    imageView.setImageResource(ints[0]);

                }

            } // On Click
        });

    }

    private void motocycleController() {
        final ImageView imageView = (ImageView) findViewById(R.id.imvMotocycle);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                optionBoolean[5] = !optionBoolean[5];
                MyConstant myConstant = new MyConstant();

                int[] ints = myConstant.getMoTocycleInts();

                for (int i = 0; i < ints.length; i += 1) {
//                    Log.d(tag, "ints[" + i + "] ==>" + ints[i]);
                }

//                Log.d(tag, "optionBooleen ==>" + optionBoolean[0]);

                if (optionBoolean[5]) {
                    imageView.setImageResource(ints[1]);
                } else {
                    imageView.setImageResource(ints[0]);

                }

            } // On Click
        });
    }

    private void furnitureController() {
        final ImageView imageView = (ImageView) findViewById(R.id.imvFurniture);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                optionBoolean[6] = !optionBoolean[6];
                MyConstant myConstant = new MyConstant();

                int[] ints = myConstant.getFurNiTureInts();

                for (int i = 0; i < ints.length; i += 1) {
//                    Log.d(tag, "ints[" + i + "] ==>" + ints[i]);
                }

//                Log.d(tag, "optionBooleen ==>" + optionBoolean[0]);

                if (optionBoolean[6]) {
                    imageView.setImageResource(ints[1]);
                } else {
                    imageView.setImageResource(ints[0]);

                }

            } // On Click
        });
    }

    private void satelliteController() {
        final ImageView imageView = (ImageView) findViewById(R.id.imvSattelite);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                optionBoolean[7] = !optionBoolean[7];
                MyConstant myConstant = new MyConstant();


                int[] ints = myConstant.getSatTeliteInts();

                for (int i = 0; i < ints.length; i += 1) {
//                    Log.d(tag, "ints[" + i + "] ==>" + ints[i]);
                }

//                Log.d(tag, "optionBooleen ==>" + optionBoolean[0]);

                if (optionBoolean[7]) {
                    imageView.setImageResource(ints[1]);
                } else {
                    imageView.setImageResource(ints[0]);

                }

            } // On Click
        });
    }


    private void setupConstant() {
        //setup ArrayList
        stringArrayList = new ArrayList<String>();
        nameImageArrayList = new ArrayList<String>();
        optionArrayList = new ArrayList<String>();


        //Create Instance or Object
        MyConstant myConstant = new MyConstant();

        //Get Value From Intent
        latADouble = getIntent().getDoubleExtra("Lat", myConstant.getLatADouble());
        lngADouble = getIntent().getDoubleExtra("Lng", myConstant.getLngADouble());

        //Set Up all Boolean ==L false
        for (int i = 0; i < optionBoolean.length; i += 1) {
            optionBoolean[i] = false;
        }

    }   // setUpConstant

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String tag = "16JulyV1";
        Log.d(tag, "requestCode ==>" + requestCode);

        //For Image

        if (requestCode == indexAnInt && resultCode == RESULT_OK) {
            Log.d(tag, "All Result OK");


            //Show Image
            uri = data.getData();
            try {

                //Display Image
                Bitmap bitmap = BitmapFactory
                        .decodeStream(getContentResolver().openInputStream(uri));
                ImageView imageView = (ImageView) findViewById(picInts[indexAnInt]);
                bitmap = Bitmap.createScaledBitmap(bitmap, 100, 100, true);
                imageView.setImageBitmap(bitmap);

                //Find path and add ArrayList
                stringArrayList.add(findPantImage());
                Log.d(tag, "stringArrayList ==>" + stringArrayList);


            } catch (Exception e) {
                Log.d(tag, "e ShowImage ==>" + e.toString());
            }
            indexAnInt += 1;
        }   //if


        //For Lat,Lng

        if (requestCode == 1000) {
            Log.d("16JulyV4", "Result OK");
            MyConstant myConstant = new MyConstant();
            latADouble = data.getDoubleExtra("Lat", myConstant.getLatADouble());
            lngADouble = data.getDoubleExtra("Lng", myConstant.getLngADouble());

            showLatLng();
        }


    }  //OnActivityResult

    private String findPantImage() {

        String strResult = null;

        String[] strings = new String[]{MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, strings, null, null, null);

        if (cursor != null) {

            cursor.moveToFirst();
            int i = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            strResult = cursor.getString(i);


        } else {
            strResult = uri.getPath();
        }


        return strResult;
    }

    private void addPictureController() {
        ImageView imageView = (ImageView) findViewById(R.id.imvadd);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (indexAnInt < picInts.length) {
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/*");
                    startActivityForResult(Intent.createChooser(intent, "Please Choose App"),
                            indexAnInt);
                } else {
                    MyAlert myAlert = new MyAlert(MainActivity.this);
                    myAlert.myDialog("Over Picture", "Can't Add More" + Integer.toString(picInts.length) + "Pic");
                }

            }
        });
    }

    private void initailView() {
        nameEditText = (EditText) findViewById(R.id.editName);
        priceEditText = (EditText) findViewById(R.id.editPrice);
        phoneEditText = (EditText) findViewById(R.id.editPhone);

    }

    public void clickSave(View view) {

        //Get Value From Edit Text
        nameString = nameEditText.getText().toString().trim();
        priceString = priceEditText.getText().toString().trim();
        phoneString = phoneEditText.getText().toString().trim();

        //Check Space
        if (nameString.equals("") || priceString.equals("") || phoneString.equals("")) {
            //Have Space
            MyAlert myAlert = new MyAlert(MainActivity.this);
            myAlert.myDialog(getString(R.string.title_have_space),
                    getString(R.string.message_have_space));
        } else {
            //No space

            createArrayFromArrayList();
            uploadValueToServer();


        }


    } //Click Save

    private void uploadValueToServer() {
        String tab = "19AugV3";
        latString = Double.toString(latADouble);
        lngString = Double.toString(lngADouble);

        //Show log
        Log.d(tab, "" + nameString);
        Log.d(tab, "" + priceString);
        Log.d(tab, "" + phoneString);
        Log.d(tab, "" + imageString);
        Log.d(tab, "" + optionString);
        Log.d(tab, "" + latString);
        Log.d(tab, "" + lngString);

        try {

            PostRoomToServer postRoomToServer = new PostRoomToServer(MainActivity.this);
            MyConstant myConstant = new MyConstant();
            postRoomToServer.execute(
                    nameString,
                    priceString,
                    phoneString,
                    imageString,
                    optionString,
                    latString,
                    lngString,
                    myConstant.getUrlPostRoomString()
            );

            String strResult = postRoomToServer.get();
            Log.d(tab, "Result ==>" + strResult);

            if (Boolean.parseBoolean(strResult)) {
                finish();
            } else {
                Toast.makeText(MainActivity.this, "Cannot Upload To Server Please Try Again",
                        Toast.LENGTH_LONG).show();
            }

        } catch (Exception e) {
            Log.d(tab, "e uploadValue ==>" + e.toString());
        }


    }

    private void createArrayFromArrayList() {

        String tag = "16JulyV2";
        Log.d(tag, "arrayList.size ==>" + stringArrayList.size());

        //About Image
        if (stringArrayList.size() != 0) {
            //Have Image
            pathImageStrings = new String[stringArrayList.size()];
            pathImageStrings = stringArrayList.toArray(new String[0]);

            for (int i = 0; i < pathImageStrings.length; i += 1) {
                Log.d(tag, "pathImage[" + i + "] ==L" + pathImageStrings[i]);

                upLoadImageToServer(pathImageStrings[i]);

                String strNameImage = pathImageStrings[i].substring(pathImageStrings[i].lastIndexOf("/"));
                strNameImage = "http://rentroom.in.th/android/Image" + strNameImage;
                nameImageArrayList.add(strNameImage);


            }  //for

        }   //if
        //About Option

        for (int i = 0; i < optionBoolean.length; i += 1) {
            Log.d(tag, "optionBool[" + i + "] ==>" + optionBoolean[i]);
            String strOption = Boolean.toString(optionBoolean[i]);
            optionArrayList.add(strOption);

        } //for
        imageString = nameImageArrayList.toString();
        optionString = optionArrayList.toString();

        Log.d(tag, "imageString ==>" + imageString);
        Log.d(tag, "optionString ==>" + optionString);


    } //CreateArray

    private void upLoadImageToServer(String pathImageString) {
        String tab = "19AugV2";

        try {

            //Change Policy
            StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy
                    .Builder().permitAll().build();
            StrictMode.setThreadPolicy(threadPolicy);

            //Upload to server'
            SimpleFTP simpleFTP = new SimpleFTP();
            MyConstant myConstant = new MyConstant();
            simpleFTP.connect(myConstant.getHostFTPString(), myConstant.getPortAnInt(),
                    myConstant.getUserFTPString(), myConstant.getPasswordFTPString());
            simpleFTP.bin();
            simpleFTP.cwd("Image");
            simpleFTP.stor(new File(pathImageString));
            simpleFTP.disconnect();


        } catch (Exception e) {
            Log.d(tab, "e upload ==>" + e.toString());
        }
    }

    private void backController() {
        ImageView imageView = (ImageView) findViewById(R.id.imvBack);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


}   //Main Class
