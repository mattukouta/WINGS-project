package sample.kouta.asyncsample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.TextView

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
        }
    }
}
