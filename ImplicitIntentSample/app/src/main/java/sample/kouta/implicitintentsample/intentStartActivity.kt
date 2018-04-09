package sample.kouta.implicitintentsample

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import java.io.UnsupportedEncodingException
import java.net.URLEncoder

class intentStartActivity : AppCompatActivity() {

    var _latitube:Double=0.0
    var _longitube:Double=0.0

    var _tvLatitube:TextView?=null
    var _tvLongitube:TextView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_start)

        _tvLatitube=findViewById(R.id.tvLatitube)
        _tvLongitube=findViewById(R.id.tvLongitube)

        var locationManager:LocationManager=getSystemService(LOCATION_SERVICE) as LocationManager
        var locationListener=GPSLocationListener()

        if(ActivityCompat.checkSelfPermission(this@intentStartActivity,Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
            var permissions= arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)
            ActivityCompat.requestPermissions(this@intentStartActivity,permissions,1000)

            return
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0f,locationListener)

    }
    fun onMapSearchButtonClick(view: View){
        var etSearchWord=findViewById<EditText>(R.id.etSearchWord)
        var searchWord:String=etSearchWord.getText().toString()

        try {
            searchWord=URLEncoder.encode(searchWord,"utf-8")
            var uriStr="geo:0,0?q="+searchWord
            var uri:Uri= Uri.parse(uriStr)
            var intent=Intent(Intent.ACTION_VIEW,uri)
            startActivity(intent)
        }
        catch (ex:UnsupportedEncodingException){
            Log.e("IntentStartActivity","検索キーワード変換失敗",ex)
        }
    }

    fun onMapShowCurrentButtonClick(view: View){
        var uriStr="geo:$_latitube,$_longitube"
        var uri=Uri.parse(uriStr)
        var intent=Intent(Intent.ACTION_VIEW,uri)
        startActivity(intent)
    }

    inner class GPSLocationListener:LocationListener{
        override fun onLocationChanged(location: Location) {
            _latitube=location.latitude
            _longitube=location.longitude
            _tvLatitube!!.setText(java.lang.Double.toString(_latitube))
            _tvLongitube!!.setText(java.lang.Double.toString(_longitube))
        }

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onProviderDisabled(provider: String) {}
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == 1000 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            var locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
            var locationListener = GPSLocationListener()

            if (ActivityCompat.checkSelfPermission(this@intentStartActivity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, locationListener)
        }
    }
}


