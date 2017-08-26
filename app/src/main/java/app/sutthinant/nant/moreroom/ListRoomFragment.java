package app.sutthinant.nant.moreroom;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by masterung on 8/26/2017 AD.
 */

public class ListRoomFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listroom, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Create ToolBar
        createToolBar();

        //Create ListView
        createListView();


    }   // onActivityCreated

    private void createListView() {
        ListView listView = getView().findViewById(R.id.livRoom);
        MyConstant myConstant = new MyConstant();
        String strURL = myConstant.getUrlGetRoomString();
        String tag = "26AugV1";
        String[] columnRoomTable = myConstant.getColumnRoomTable();

        try {

            GetAllData getAllData = new GetAllData(getActivity());
            getAllData.execute(strURL);
            String strJSON = getAllData.get();
            Log.d(tag, "JSoN ==> " + strJSON);

            JSONArray jsonArray = new JSONArray(strJSON);

            String[] idStrings = new String[jsonArray.length()];
            String[] nameStrings = new String[jsonArray.length()];
            String[] priceStrings = new String[jsonArray.length()];
            String[] phoneStrings = new String[jsonArray.length()];
            String[] imageStrings = new String[jsonArray.length()];
            String[] optionStrings = new String[jsonArray.length()];
            String[] latStrings = new String[jsonArray.length()];
            String[] lngStrings = new String[jsonArray.length()];
            String[] roomStrings = new String[jsonArray.length()];
            String[] firstImageStrings = new String[jsonArray.length()];

            for (int i = 0; i < jsonArray.length(); i += 1) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                idStrings[i] = jsonObject.getString(columnRoomTable[0]);
                nameStrings[i] = jsonObject.getString(columnRoomTable[1]);
                priceStrings[i] = jsonObject.getString(columnRoomTable[2]);
                phoneStrings[i] = jsonObject.getString(columnRoomTable[3]);
                imageStrings[i] = jsonObject.getString(columnRoomTable[4]);

                firstImageStrings[i] = findImage(imageStrings[i]);

                optionStrings[i] = jsonObject.getString(columnRoomTable[5]);
                latStrings[i] = jsonObject.getString(columnRoomTable[6]);
                lngStrings[i] = jsonObject.getString(columnRoomTable[7]);
                roomStrings[i] = jsonObject.getString(columnRoomTable[8]);

            }   //for

            RoomListViewAdapter roomListViewAdapter = new RoomListViewAdapter(getActivity(),
                    firstImageStrings, nameStrings, priceStrings);
            listView.setAdapter(roomListViewAdapter);

        } catch (Exception e) {
            Log.d(tag, "e createListview ==> " + e.toString());
        }


    }   // createListView

    private String findImage(String strArrayList) {

        String strResult = null;
        String tag = "26AugV2";
        int allDigi = strArrayList.length();
        Log.d(tag, "allDigi ==> " + allDigi);
        strResult = strArrayList.substring(1, (allDigi-1));
        Log.d(tag, "strResult1 ==> " + strResult);
        String[] strings = strResult.split(",");
        Log.d(tag, "strings[0] ==> " + strings[0]);


        return strings[0];
    }

    private void createToolBar() {
        Toolbar toolbar = getView().findViewById(R.id.toolbarListRoom);
        ((ListRoomActivity) getActivity()).setSupportActionBar(toolbar);
        ((ListRoomActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((ListRoomActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.mipmap.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });


    }
}   // Main Class
