package app.sutthinant.nant.moreroom;

/**
 * Created by Lotus on 15/7/2560.
 */

public class MyConstant {

    //General Constant
    private double latADouble = 13.673279;
    private double lngADouble = 100.606747; //==> Bangna Section
    private int iconAlert = R.mipmap.ic_aircondition_light;


    private int[] airConditionInts = new int[]{R.mipmap.ic_aircondition_light, R.mipmap.ic_aircondition_dark};
    public int[] getAirConditionInts() {
        return airConditionInts;
    }

    private int[] ccTvInts = new int[]{R.mipmap.ic_cctv_ligt, R.mipmap.ic_cctv_dark};
    public int[] getCctv() {
        return ccTvInts;
    }

    public int getIconAlert() {
        return iconAlert;
    }

    public double getLatADouble() {
        return latADouble;
    }

    public double getLngADouble() {
        return lngADouble;
    }
}   //Main Class
