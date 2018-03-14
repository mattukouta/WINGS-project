package sample.kouta.intentsample

import android.app.LauncherActivity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleAdapter
import java.util.ArrayList
import java.util.HashMap

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var lvMenu=findViewById<ListView>(R.id.lvMenu)
        var menuList=ArrayList<Map<String,String>>()

        var menu=HashMap<String,String>()
        menu.put("name","唐揚げ定食")
        menu.put("price","800円")
        menuList.add(menu)

        menu=HashMap()
        menu.put("name","ハンバーグ定食")
        menu.put("price","850円")
        menuList.add(menu)

        menu=HashMap()
        menu.put("name","生姜焼き定食")
        menu.put("price","850円")
        menuList.add(menu)

        menu=HashMap()
        menu.put("name","ステーキ定食")
        menu.put("price","1000円")
        menuList.add(menu)

        menu=HashMap()
        menu.put("name","野菜炒め定食")
        menu.put("price","750円")
        menuList.add(menu)

        menu=HashMap()
        menu.put("name","とんかつ定食")
        menu.put("price","900円")
        menuList.add(menu)

        menu=HashMap()
        menu.put("name","ミンチかつ定食")
        menu.put("price","850円")
        menuList.add(menu)

        menu=HashMap()
        menu.put("name","チキンカツ定食")
        menu.put("price","900円")
        menuList.add(menu)

        menu=HashMap()
        menu.put("name","コロッケ定食")
        menu.put("price","850円")
        menuList.add(menu)

        menu=HashMap()
        menu.put("name","焼き魚定食")
        menu.put("price","750円")
        menuList.add(menu)

        menu=HashMap()
        menu.put("name","焼肉定食")
        menu.put("price","900円")
        menuList.add(menu)

        var from= arrayOf("name","price")
        val to = intArrayOf(android.R.id.text1, android.R.id.text2)
        var adapter = SimpleAdapter(this,menuList,android.R.layout.simple_list_item_2,from,to)
        lvMenu.adapter=adapter

        lvMenu.setOnItemClickListener(ListItemClickListener())
    }

    inner class ListItemClickListener:AdapterView.OnItemClickListener{
        override fun onItemClick(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
            var item=parent.getItemAtPosition(position) as Map<String,String>

            var menuName=item.get("name")
            var menuPrice=item.get("price")

            var intent=Intent(this@MainActivity,Main2Activity::class.java)

            intent.putExtra("menuName",menuName)
            intent.putExtra("menuPrice",menuPrice)

            startActivity(intent)
        }
    }
}
