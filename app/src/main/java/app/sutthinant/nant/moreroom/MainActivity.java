package app.sutthinant.nant.moreroom;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Explicit
    private String nameString, priceString, phoneString;
    private EditText nameEditText, priceEditText, phoneEditText;
    private int indexAnInt = 0;
    private int[] picInts = new int[]{R.id.imvShowPic1,
            R.id.imvShowPic2, R.id.imvShowPic3, R.id.imvShowPic4,};

    private Uri uri;
    private ArrayList<String> stringArrayList;
    private String[] pathImageStrings;
    private MyConstant myConstant;
    private boolean[] optionBoolean = new boolean[9];
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


    }   //Main Method

    private void setupLocation() {
        ImageView imageView = (ImageView) findViewById(R.id.imvSetupLatLng);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                intent.putExtra("Lat", latADouble);
                intent.putExtra("Lng", lngADouble);
                startActivityForResult(intent, 1000);

            }
        });
    }

    private void showLatLng() {
        TextView textView = (TextView) findViewById(R.id.txtShowLatLng);
        textView.setText(Double.toString(latADouble) +" , " + Double.toString(lngADouble));
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

                for (int i=0; i<ints.length; i+=1) {
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

    private void setupConstant() {
        stringArrayList = new ArrayList<String>();
        MyConstant myConstant = new MyConstant();

        latADouble = getIntent().getDoubleExtra("lat", myConstant.getLatADouble());
        lngADouble = getIntent().getDoubleExtra("lng", myConstant.getLngADouble());


        for (int i=0; i<optionBoolean.length; i+=1) {
            optionBoolean[i] = false;
        }

    }

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
                    myAlert.myDialog("Over Picture", "Can't Add More"+ Integer.toString(picInts.length)+"Pic");
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
        if (nameString.equals("")||priceString.equals("")||phoneString.equals("")) {
            //Have Space
            MyAlert myAlert = new MyAlert(MainActivity.this);
            myAlert.myDialog(getString(R.string.title_have_space),
                    getString(R.string.message_have_space));
        } else {
            //No space

            createArrayFromArrayList();


        }



    } //Click Save

    private void createArrayFromArrayList() {

        String tag = "16JulyV2";
        Log.d(tag, "arrayList.size ==>" + stringArrayList.size());

        if (stringArrayList.size() != 0) {
            //Have Image
            pathImageStrings = new String[stringArrayList.size()];
            pathImageStrings = stringArrayList.toArray(new String[0]);

            for (int i=0;i<pathImageStrings.length; i+=1) {
                Log.d(tag, "pathImage[" + i + "] ==L" + pathImageStrings[i]);
            }

        }


    } //CreateArray

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
