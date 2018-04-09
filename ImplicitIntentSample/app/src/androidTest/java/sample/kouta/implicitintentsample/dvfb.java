package sample.kouta.implicitintentsample;

import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;

import java.util.Locale;

public class dvfb extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
    }
    private class GPSLocationListener implements LocationListener{
        @Override
        public void onLocationChanged(Location location){
            _latitube=location.getLatitube();
            _tvLatitube.setText(Double.toString(_latitube))
        }
    }
}
