package app.sutthinant.nant.moreroom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    //Explicit
    private String nameString, priceString, phoneString;
    private EditText nameEditText, priceEditText, phoneEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Back Controller
        backController();

        //Initail View

        initailView();


    }   //Main Method

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
