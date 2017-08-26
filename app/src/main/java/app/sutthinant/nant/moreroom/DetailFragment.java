package app.sutthinant.nant.moreroom;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by masterung on 8/26/2017 AD.
 */

public class DetailFragment extends Fragment{

    private String[] resultStrings;


    public static DetailFragment detailInstance(String[] resultStrings) {

        DetailFragment detailFragment = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArray("Detail", resultStrings);
        detailFragment.setArguments(bundle);

        return detailFragment;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_room, container, false);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        resultStrings = getArguments().getStringArray("Detail");
        for (int i=0;i<resultStrings.length;i+=1) {
            Log.d("26AugV2", "result[" + i + "] ==> " + resultStrings[i]);
        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);




    }
}   // Main Class
