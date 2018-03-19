package sample.kouta.menusample

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.Toast
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
        registerForContextMenu(_lvMenu)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var inflater=getMenuInflater()
        inflater.inflate(R.menu.menu_options_menu_list,menu)
        return super.onCreateOptionsMenu(menu)
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
        override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            var item=parent.getItemAtPosition(position) as Map<String,Any>
            order(item)
        }
    }

    fun createCurryList():List<Map<String,Any>>{
        var menuList=ArrayList<Map<String,Any>>()
        var menu=HashMap<String,Any>()

        menu.put("name","ビーフカレー")
        menu.put("price",520)
        menu.put("desc","特選スパイスをきかせた国産ビーフ100%のカレーです。")
        menuList.add(menu)

        menu= HashMap()
        menu.put("name","ポークカレー")
        menu.put("price",420)
        menu.put("desc","特選スパイスをきかせた国産ポーク100%のカレーです。")
        menuList.add(menu)

        menu= HashMap()
        menu.put("name","ハンバーグカレー")
        menu.put("price",620)
        menu.put("desc","特選スパイスをきかせた国産肉100%のハンバーグカレーです。")
        menuList.add(menu)

        menu= HashMap()
        menu.put("name","チーズカレー")
        menu.put("price",560)
        menu.put("desc","特選スパイスをきかせた国産チーズ100%のカレーです。")
        menuList.add(menu)

        menu= HashMap()
        menu.put("name","カツカレー")
        menu.put("price",760)
        menu.put("desc","特選スパイスをきかせた国産肉100%のカツカレーです。")
        menuList.add(menu)

        menu= HashMap()
        menu.put("name","ビーフカツカレー")
        menu.put("price",880)
        menu.put("desc","特選スパイスをきかせた国産ビーフ100%で作ったカツのカレーです。")
        menuList.add(menu)

        menu= HashMap()
        menu.put("name","からあげカレー")
        menu.put("price",540)
        menu.put("desc","特選スパイスをきかせた国産鶏肉100%で作ったからあげのカレーです。")
        menuList.add(menu)

        return menuList
    }


   override fun onOptionsItemSelected(item:MenuItem):Boolean{
        var itemId=item.itemId

       when(itemId){
           R.id.menuListOptionTeisyoku->
                   _menuList=createTeisyokuList()
           R.id.menuListOptionCurry->
                   _menuList=createCurryList()
       }
       var adapter=SimpleAdapter(this@MainActivity,_menuList,R.layout.row,FROM,TO)
       _lvMenu!!.adapter=adapter
       return super.onOptionsItemSelected(item)
    }

    override fun onCreateContextMenu(menu: ContextMenu?, view: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, view, menuInfo)
        var inflater=menuInflater
        inflater.inflate(R.menu.menu_context_menu_list,menu)
        menu!!.setHeaderTitle(R.string.menu_list_context_header)
    }
    fun order(menu:Map<String,Any>){
        var menuName=menu.get("name") as String
        var menuPrice=menu.get("price") as Integer
        var intent =Intent(this@MainActivity,Main2Activity::class.java)
        intent.putExtra("menuName",menuName)
        intent.putExtra("menuPrice","$menuPrice 円")
        startActivity(intent)
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        var info=item!!.menuInfo as AdapterView.AdapterContextMenuInfo
        var listPosition=info.position
        var menu =_menuList!!.get(listPosition)
        var itemId=item.itemId
        when(itemId){
            R.id.menuListContextDesc ->{
                var desc=menu.get("desc") as String
                Toast.makeText(this@MainActivity,desc,Toast.LENGTH_LONG).show()
            }
            R.id.menuListContextOrder->
                    order(menu)
        }
        return super.onContextItemSelected(item)
    }
}
