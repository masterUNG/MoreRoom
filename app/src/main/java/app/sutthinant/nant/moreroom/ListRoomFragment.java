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

/**
 * Created by masterung on 8/26/2017 AD.
 */

public class ListRoomFragment extends Fragment{

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

        try {

            GetAllData getAllData = new GetAllData(getActivity());
            getAllData.execute(strURL);
            String strJSON = getAllData.get();
            Log.d(tag, "JSoN ==> " + strJSON);

        } catch (Exception e) {
            Log.d(tag, "e createListview ==> " + e.toString());
        }



    }   // createListView

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
