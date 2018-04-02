package sample.kouta.asyncsample

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_weather_info.*
import kotlinx.android.synthetic.main.activity_weather_info.view.*
import org.json.JSONException
import org.json.JSONObject
import sample.kouta.asyncsample.R.id.text
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class WeatherInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_info)

        var lvCityList=findViewById<ListView>(R.id.lvCityList)
        var cityList =ArrayList<Map<String,String>>()

        var city=HashMap<String,String>()
        city.put("name","大阪")
        city.put("id","270000")
        cityList.add(city)

        city=HashMap()
        city.put("name","神戸")
        city.put("id","280010")
        cityList.add(city)
//
//        city=HashMap()
//        city.put("name","豊橋")
//        city.put("id","290000")
//        cityList.add(city)
//
//        city=HashMap()
//        city.put("name","京都")
//        city.put("id","300000")
//        cityList.add(city)
//
//        city=HashMap()
//        city.put("name","舞鶴")
//        city.put("id","310100")
//        cityList.add(city)
//
//        city=HashMap()
//        city.put("name","横浜")
//        city.put("id","320000")
//        cityList.add(city)
//
//        city=HashMap()
//        city.put("name","東京")
//        city.put("id","350010")
//        cityList.add(city)
//
//        city=HashMap()
//        city.put("name","千葉")
//        city.put("id","350010")
//        cityList.add(city)
//
//        city=HashMap()
//        city.put("name","札幌")
//        city.put("id","500000")
//        cityList.add(city)

        var from= arrayOf("name")
        var to= intArrayOf(android.R.id.text1)
        var adapter=SimpleAdapter(this@WeatherInfoActivity,cityList,android.R.layout.simple_expandable_list_item_1,from,to)
        lvCityList.setAdapter(adapter)
        lvCityList.setOnItemClickListener(ListItemClickListener())
    }

    inner class ListItemClickListener : AdapterView.OnItemClickListener{
        override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            var item = parent!!.getItemAtPosition(position) as Map<String,String>
            var cityName=item.get("name")
            var cityId=item.get("id")
            var tvCityName=findViewById<TextView>(R.id.tvCityName)

            tvCityName.setText(cityName+"の天気: ")

            var tvWeatherTelop=findViewById<TextView>(R.id.tvWeatherTelop)
            var tvWeatherDesc=findViewById<TextView>(R.id.tvWeatherDesc)
            var receiver=WeatherInfoReceiver(tvWeatherTelop,tvWeatherDesc)
            receiver.execute(cityId)
        }
    }

    inner class WeatherInfoReceiver(var _tvWeatherTelop:TextView=tvWeatherTelop, var _tvWeatherDesc:TextView=tvWeatherDesc) : AsyncTask<String,String,String>(){

        override fun doInBackground(vararg params: String): String {
            var id=params[0]
            var urlStr="http://weather.livedoor.com/forecast/webservice/json/v1?city=$id"
            var result=""

            var con:HttpURLConnection?=null
            var Is:InputStream?=null

            try {
                var url= URL(urlStr)
                con=url.openConnection() as HttpURLConnection
                con.setRequestMethod("GET")
                con.connect()
                Is=con.getInputStream()
                result=is2String(Is)
            }
            catch (ex: MalformedURLException){
            }
            catch (ex:IOException){
            }
            finally {
                if(con!=null){
                    con.disconnect()
                }
                if(Is!=null){
                    try {
                        Is.close()
                    }
                    catch (ex:IOException){
                    }
                }
            }
            return result
        }

        @Throws(IOException::class)
        fun is2String(Is:InputStream): String{
            var reader = BufferedReader(InputStreamReader(Is, "UTF-8"))
            var sb = StringBuffer()
            var b = CharArray(1024)
            var line: Int

            while ({line=reader.read(b);line}()!=0) {
                sb.append(b, 0, line)
            }
            return sb.toString()
        }

        override fun onPostExecute(result: String?) {
            var telop = ""
            var desc = ""

            try{
                var rootJSON=JSONObject(result)
                var descriptionJSON=rootJSON.getJSONObject("description")
                desc=descriptionJSON.getString("text")

                var forecasts=rootJSON.getJSONArray("forecasts")
                var forecastsNow=forecasts.getJSONObject(0)
                telop=forecastsNow.getString("telop")
            }
            catch (ex: JSONException){
            }

            _tvWeatherTelop.setText(telop)
            _tvWeatherDesc.setText(desc)
        }
    }
}
