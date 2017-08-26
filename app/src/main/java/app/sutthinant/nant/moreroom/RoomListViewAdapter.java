package app.sutthinant.nant.moreroom;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by masterung on 8/26/2017 AD.
 */

public class RoomListViewAdapter extends BaseAdapter{

    private Context context;
    private String[] iconStrings, nameStrings, priceStrings;

    public RoomListViewAdapter(Context context,
                               String[] iconStrings,
                               String[] nameStrings,
                               String[] priceStrings) {
        this.context = context;
        this.iconStrings = iconStrings;
        this.nameStrings = nameStrings;
        this.priceStrings = priceStrings;
    }

    @Override
    public int getCount() {
        return nameStrings.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1 = layoutInflater.inflate(R.layout.room_listview_layout, viewGroup, false);

        //For Image
        try {

            ImageView imageView = view1.findViewById(R.id.imvIcon);
            Picasso.with(context).load(iconStrings[i]).into(imageView);

        } catch (Exception e) {
            Log.d("26AugV2", "e Adapter ==> " + e.toString());
        }

        //For Text
        TextView nameTextView = view1.findViewById(R.id.txtName);
        TextView priceTextView = view1.findViewById(R.id.txtPrice);
        nameTextView.setText(nameStrings[1]);
        priceTextView.setText(priceStrings[i]);

        return view1;
    }
}   // Main Class
