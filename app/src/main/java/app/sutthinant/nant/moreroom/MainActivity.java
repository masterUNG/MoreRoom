package app.sutthinant.nant.moreroom;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    //Explicit
    private String nameString, priceString, phoneString;
    private EditText nameEditText, priceEditText, phoneEditText;
    private int indexAnInt = 0;
    private int[] picInts = new int[]{R.id.imvShowPic1,
            R.id.imvShowPic2, R.id.imvShowPic3, R.id.imvShowPic4,};

    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Back Controller
        backController();

        //Initial View

        initailView();

        //Add Picture Controller
        addPictureController();


    }   //Main Method

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String tag = "16JulyV1";
        Log.d(tag, "requestCode ==>" + requestCode);



        if (requestCode == indexAnInt && resultCode == RESULT_OK) {
            Log.d(tag, "All Result OK");


            //Show Image
            uri = data.getData();
            try {

                Bitmap bitmap = BitmapFactory
                        .decodeStream(getContentResolver().openInputStream(uri));
                ImageView imageView = (ImageView) findViewById(picInts[indexAnInt]);
                bitmap = Bitmap.createScaledBitmap(bitmap, 100, 100, true);
                imageView.setImageBitmap(bitmap);

            } catch (Exception e) {
                Log.d(tag, "e ShowImage ==>" + e.toString());
            }
            indexAnInt += 1;
        }   //if


    }  //OnActivityResult

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

        }



    } //Click Save

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
