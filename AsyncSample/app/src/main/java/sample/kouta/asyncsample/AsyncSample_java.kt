package sample.kouta.asyncsample

import android.app.LauncherActivity
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.TextView

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import org.w3c.dom.Text

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.ArrayList
import java.util.HashMap

class AsyncSample_java : AppCompatActivity() {
    override fun onCreate(saveInstanceState: Bundle?) {
        super.onCreate(saveInstanceState)
        setContentView(R.layout.activity_weather_info)

        val lvCityList = findViewById<ListView>(R.id.lvCityList)
        val cityList = ArrayList<Map<String, String>>()
        var city: MutableMap<String, String> = HashMap()
        city["name"] = "大阪"
        city["id"] = "270000"
        cityList.add(city)
        city = HashMap()
        city["name"] = "神戸"
        city["id"] = "280010"
        cityList.add(city)

        val from = arrayOf("name")
        val to = intArrayOf(android.R.id.text1)

        val adapter = SimpleAdapter(this@AsyncSample_java, cityList, android.R.layout.simple_expandable_list_item_1, from, to)
        lvCityList.adapter = adapter
        lvCityList.onItemClickListener = ListItemClickListener()
    }

    private inner class ListItemClickListener : AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            val item = parent.getItemAtPosition(position) as Map<String, String>
            val cityName = item["name"]
            val cityId = item["id"]
            val tvCityName = findViewById<TextView>(R.id.tvCityName)
            tvCityName.text = cityName + "の天気:"

            val tvWeatherTelop = findViewById<TextView>(R.id.tvWeatherTelop)
            val tvWeatherDesc = findViewById<TextView>(R.id.tvWeatherDesc)
            val receiver = WeatherInfoReceiver(tvWeatherTelop, tvWeatherDesc)
            receiver.execute(cityId)
        }
    }

    private inner class WeatherInfoReceiver(private val _tvWeatherTelop: TextView, private val _tvWeatherDesc: TextView) : AsyncTask<String, String, String>() {

        override fun doInBackground(vararg params: String): String {
            val id = params[0]
            val urlStr = "http://weather.livedoor.com/forecast/webservice/json/v1?city=$id"
            var result = ""
            var con: HttpURLConnection? = null
            var `is`: InputStream? = null
            try {
                val url = URL(urlStr)
                con = url.openConnection() as HttpURLConnection
                con.requestMethod = "GET"
                con.connect()
                `is` = con.inputStream
                result = is2String(`is`)
            } catch (ex: MalformedURLException) {
            } catch (ex: IOException) {
            } finally {
                if (con != null) {
                    con.disconnect()
                }
                if (`is` != null) {
                    try {
                        `is`.close()
                    } catch (ex: IOException) {
                    }

                }
            }

            return result
        }

        public override fun onPostExecute(result: String) {
            var telop = ""
            var desc = ""

            try {
                val rootJSON = JSONObject(result)
                val descriptionJSON = rootJSON.getJSONObject("description")
                desc = descriptionJSON.getString("text")
                val forecasts = rootJSON.getJSONArray("forecasts")
                val forecastNow = forecasts.getJSONObject(0)
                telop = forecastNow.getString("telop")
            } catch (ex: JSONException) {
            }

            _tvWeatherTelop.text = telop
            _tvWeatherDesc.text = desc
        }

        @Throws(IOException::class)
        private fun is2String(`is`: InputStream?): String {
            val reader = BufferedReader(InputStreamReader(`is`!!, "UTF-8"))
            val sb = StringBuffer()
            val b = CharArray(1024)
            var line: Int
            while () {
                sb.append(b, 0, line)
            }
            return sb.toString()
        }
    }
}
