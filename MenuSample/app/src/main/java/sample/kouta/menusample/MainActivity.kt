package sample.kouta.menusample

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleAdapter
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class MainActivity : AppCompatActivity() {

    var _lvMenu :ListView?=null
    var _menuList:List<Map<String,Any>>?=null
    final var FROM= arrayOf("name","price")
    final var TO= intArrayOf(R.id.tvMenuName,R.id.tvMenuPrice)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        _lvMenu=findViewById(R.id.lvMenu)
        _menuList=createTeisyokuList()

        var adapter=SimpleAdapter(this@MainActivity,_menuList,R.layout.row,FROM,TO)
        _lvMenu?.adapter=adapter
        _lvMenu?.setOnItemClickListener(ListItemClickListener())
    }
    fun createTeisyokuList() : List<Map<String,Any>>{
        var menuList=ArrayList<Map<String,Any>>()
        var menu =HashMap<String,Any>()
        menu.put("name","から揚げ定食")
        menu.put("price",800)
        menu.put("desc","若鶏のから揚げにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)

        menu= HashMap()
        menu.put("name","ハンバーグ定食")
        menu.put("price",850)
        menu.put("desc","手ごねハンバーグにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)

        menu=HashMap()
        menu.put("name","生姜焼き定食")
        menu.put("price",850)
        menu.put("desc","生姜焼きにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)

        menu=HashMap()
        menu.put("name","ステーキ定食")
        menu.put("price",1000)
        menu.put("desc","ロースステーキにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)

        menu=HashMap()
        menu.put("name","野菜炒め定食")
        menu.put("price",750)
        menu.put("desc","野菜炒めにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)

        menu=HashMap()
        menu.put("name","とんかつ定食")
        menu.put("price",900)
        menu.put("desc","ロースとんかつにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)

        menu=HashMap()
        menu.put("name","ミンチかつ定食")
        menu.put("price",850)
        menu.put("desc","ミンチかつにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)

        menu=HashMap()
        menu.put("name","チキンカツ定食")
        menu.put("price",900)
        menu.put("desc","チキンカツにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)

        menu=HashMap()
        menu.put("name","コロッケ定食")
        menu.put("price",850)
        menu.put("desc","手作りコロッケにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)

        menu=HashMap()
        menu.put("name","焼き魚定食")
        menu.put("price",750)
        menu.put("desc","旬の焼き魚にサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)

        menu=HashMap()
        menu.put("name","焼肉定食")
        menu.put("price",900)
        menu.put("desc","焼肉にサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)

        return menuList
    }
    inner class ListItemClickListener: AdapterView.OnItemClickListener{
        override fun onItemClick(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
            var item=parent.getItemAtPosition(position) as Map<String,Any>

            var menuName=item.get("name") as String
            var menuPrice=item.get("price") as Integer

            var intent= Intent(this@MainActivity,Main2Activity::class.java)

            intent.putExtra("menuName",menuName)
            intent.putExtra("menuPrice","  $menuPrice 円")

            startActivity(intent)
        }
    }
}
