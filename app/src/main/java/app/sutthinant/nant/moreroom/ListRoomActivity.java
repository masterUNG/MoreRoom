package app.sutthinant.nant.moreroom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ListRoomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_room);

        //Add Fragment
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.contentListRoom, new ListRoomFragment())
                    .commit();
        }

    }   // Main Method

}   // Main Class
