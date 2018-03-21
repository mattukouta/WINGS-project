package sample.kouta.fragmentsample


import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.SimpleAdapter


class MenuListFragment : Fragment() {

    var _parentActivity:Activity?=null

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        _parentActivity=activity
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view=inflater!!.inflate(R.layout.fragment_menu_list,container,false)
        var lvMenu=view.findViewById<ListView>(R.id.lvMenu)
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

        var adapter = SimpleAdapter(_parentActivity,menuList,android.R.layout.simple_list_item_2,from,to)
        lvMenu.adapter=adapter
        return view

    }

}
