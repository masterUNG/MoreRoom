package app.sutthinant.nant.moreroom;

/**
 * Created by Lotus on 15/7/2560.
 */

public class MyConstant {

    //General Constant
    private double latADouble = 13.673279;
    private double lngADouble = 100.606747; //==> Bangna Section
    private int iconAlert = R.mipmap.ic_aircondition_light;
    private int[] roomInts = new int[]{R.mipmap.ic_blank, R.mipmap.ic_one, R.mipmap.ic_two,
            R.mipmap.ic_three};
    private String[] columnRoomTable = new String[]{
            "id",
            "Name",
            "Price",
            "Phone",
            "Image",
            "Option",
            "Lat",
            "Lng",
            "Room"};

    //for url
    private String urlPostRoomString = "http://www.rentroom.in.th/android/addData.php";
    private String urlGetRoomString = "http://rentroom.in.th/android/getAllRoom.php";

    //for FTP
    private String hostFTPString = "ftp.rentroom.in.th";
    private String userFTPString = "nant@rentroom.in.th";
    private String passwordFTPString = "Abc12345";
    private int portAnInt = 21;


    public String[] getColumnRoomTable() {
        return columnRoomTable;
    }

    public int[] getRoomInts() {
        return roomInts;
    }

    public String getUrlGetRoomString() {
        return urlGetRoomString;
    }

    public String getHostFTPString() {
        return hostFTPString;
    }

    public String getUserFTPString() {
        return userFTPString;
    }

    public String getPasswordFTPString() {
        return passwordFTPString;
    }

    public int getPortAnInt() {
        return portAnInt;
    }

    public String getUrlPostRoomString() {
        return urlPostRoomString;
    }

    private int[] airConditionInts = new int[]{R.mipmap.ic_aircondition_light, R.mipmap.ic_aircondition_dark};
    public int[] getAirConditionInts() {
        return airConditionInts;
    }

    private int[] ccTvInts = new int[]{R.mipmap.ic_cctv_ligt, R.mipmap.ic_cctv_dark};
    public int[] getCcTvInts() {
        return ccTvInts;
    }

    private int[] wiFiInts = new int[]{R.mipmap.ic_wifi_light, R.mipmap.ic_wifi_dark};
    public int[] getWiFiInts() {
        return wiFiInts;
    }

    private int[] carInts = new int[]{R.mipmap.ic_car_light, R.mipmap.ic_car_dark};
    public int[] getCarInts() {
        return carInts;
    }

    private int[] keyInts = new int[]{R.mipmap.ic_keycard_light, R.mipmap.ic_keycard_dark};
    public int[] getKeyInts() {
        return keyInts;
    }

    private int[] moTocycleInts = new int[]{R.mipmap.ic_moto_light, R.mipmap.ic_moto_dark};

    public int[] getMoTocycleInts() {
        return moTocycleInts;
    }

    private int[] furNiTureInts = new int[]{R.mipmap.ic_sofar_light, R.mipmap.ic_sofar_dark};

    public int[] getFurNiTureInts() {
        return furNiTureInts;
    }

    private int[] satTeliteInts = new int[]{R.mipmap.ic_sat_light, R.mipmap.ic_sat_dark};

    public int[] getSatTeliteInts() {
        return satTeliteInts;
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
