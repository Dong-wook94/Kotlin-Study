package org.techtown.sampletab;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kwabenaberko.openweathermaplib.constants.Lang;
import com.kwabenaberko.openweathermaplib.constants.Units;
import com.kwabenaberko.openweathermaplib.implementation.OpenWeatherMapHelper;
import com.kwabenaberko.openweathermaplib.implementation.callbacks.CurrentWeatherCallback;
import com.kwabenaberko.openweathermaplib.models.currentweather.CurrentWeather;

import static com.gun0912.tedpermission.TedPermission.TAG;

public class Fragment1 extends Fragment {

    View view;
    TextView weatherView;
    TextView cloth;
    TextView city;
    ImageView imageView;
    private LocationManager manager;
    boolean isGPSEnabled = false;
    private static final int REQUEST_CODE_LOCATION = 2;
    Double latt = 0.0;
    Double lonn = 0.0;


    public Fragment1(){

    }


    private String OPEN_WEATHER_MAP_API_KEY = "9be9ab67b44cf43dd31a08a0cdf0f363";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment1, container, false);
        weatherView = (TextView) view.findViewById(R.id.weatherview);
        cloth = (TextView) view.findViewById(R.id.cloth);
        imageView = (ImageView)view.findViewById(R.id.imageView);
        city = (TextView) view.findViewById(R.id.cityview);
        startLocationService();

        //lat = (TextView) view.findViewById(R.id.lat);
        //lon = (TextView) view.findViewById(R.id.lon);

        return view;
    }





    private void startLocationService(){

        manager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        long minTime = 1000;
        float minDistance = 1;

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) !=PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED){

            Toast.makeText(getActivity(),"Don't have permission",Toast.LENGTH_LONG).show();
            return;
        }
        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER,minTime,minDistance,mlocationListener);
        manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,minTime,minDistance,mlocationListener);
    }

    private void stopLocationService(){

        if(ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            Toast.makeText(getActivity(), "Dont have permission",Toast.LENGTH_LONG).show();
            return;
        }
        manager.removeUpdates(mlocationListener); }

    private final LocationListener mlocationListener= new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            latt = location.getLatitude();
            lonn = location.getLongitude();
            Log.d("도시","location Changed : "+latt+","+lonn);
            getWeather();
            stopLocationService();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    private void freshFragment(){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(this).attach(this).commit();
    }


    private void getWeather(){
        //Instantiate Class With Your ApiKey As The Parameter
        OpenWeatherMapHelper helper = new OpenWeatherMapHelper(OPEN_WEATHER_MAP_API_KEY);

        //Set Units
        helper.setUnits(Units.IMPERIAL);

        //Set lang
        helper.setLang(Lang.ENGLISH);

        /*
        This Example Only Shows how to get current weather by city name
        Check the docs for more methods [https://github.com/KwabenBerko/OpenWeatherMap-Android-Library/]
        */

        final String sample1 = getString(R.string.s1);
        final String sample2 = getString(R.string.s2);
        final String sample3 = getString(R.string.s3);
        final String sample4 = getString(R.string.s4);
        final String sample5 = getString(R.string.s5);
        final String sample6 = getString(R.string.s6);
        final String sample7 = getString(R.string.s7);
        final String sample8 = getString(R.string.s8);


        helper.getCurrentWeatherByGeoCoordinates(latt ,lonn, new CurrentWeatherCallback() {
            Double temp1;
            int temp;
            @Override
            public void onSuccess(CurrentWeather currentWeather) {
                Log.v("place",currentWeather.getName());
                temp1= (currentWeather.getMain().getTempMax() -32) * 5/9;//화씨를 섭씨로 변환
                temp = (int)Math.round(temp1);
                String resultWeather = temp+"°C";
                Log.v(TAG, resultWeather);
                weatherView.setText(resultWeather);
                Log.d("도시",currentWeather.getName());
                Log.d("도시",latt+","+lonn);
                city.setText(currentWeather.getName());

                if(0<=temp && temp<=5){
                    cloth.setText(sample1);
                    Log.d(TAG,cloth.getText().toString());
                    imageView.setImageResource(R.drawable.image_1);
                }
                else if(6<=temp && temp<=9){
                    cloth.setText(sample2);
                    Log.d(TAG,cloth.getText().toString());
                    imageView.setImageResource(R.drawable.image_2);
                }
                else if(10<=temp && temp<=11){
                    cloth.setText(sample3);
                    Log.d(TAG,cloth.getText().toString());
                    imageView.setImageResource(R.drawable.image_3);
                }
                else if(12<=temp && temp<=16){
                    cloth.setText(sample4);
                    Log.d(TAG,cloth.getText().toString());
                    imageView.setImageResource(R.drawable.image_4);
                }
                else if(17<=temp && temp<=19){
                    cloth.setText(sample5);
                    Log.d(TAG,cloth.getText().toString());
                    imageView.setImageResource(R.drawable.image_5);
                }
                else if(20<=temp && temp<=22){
                    cloth.setText(sample6);
                    Log.d(TAG,cloth.getText().toString());
                    imageView.setImageResource(R.drawable.image_6);
                }
                else if(23<=temp && temp<=26){
                    cloth.setText(sample7);
                    Log.d(TAG,cloth.getText().toString());
                    imageView.setImageResource(R.drawable.image_7);
                }
                else {
                    cloth.setText(sample8);
                    Log.d(TAG,cloth.getText().toString());
                    imageView.setImageResource(R.drawable.image_8);
                }
            }


            @Override
            public void onFailure(Throwable throwable) {
                Log.v(TAG, throwable.getMessage());
            }

        });
    }

}