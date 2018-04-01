package sample.kouta.asyncsample;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ge extends AppCompatActivity{
//    private class WeatherInfoReceriver extends AsyncTask<String,String,String>{
//        private TextView _tvWeatherTelop;
//        private TextView _tvWeatherDesc;
//
//        public WeatherInfoReceriver(TextView tvWeatherTelop,TextView tvWeatherDesc){
//            _tvWeatherTelop=tvWeatherTelop;
//            _tvWeatherDesc=tvWeatherDesc;
//        }
//
//        @Override
//        public String doinBackground(String...params){
//            String id=params[0];
//            String urlStr="http://weather.livedoor.com/forecast/webservice/json/v1?city="+id;
//            String result="";
//            }
//        }
//        return result;
//    }
    private String is2Stringz(InputSteram is)throws IOException {
        BufferedReader reader=new BufferedReader(new InputStreamReader(is,"utf-8"));
        StringBuffer sb=new StringBuffer();
        char[] b=new char[1024];
        int line;
        while(0<=(line=reader.read(b))){
            sb.append(b,0,line);
        }
        return sb.toString();
}
