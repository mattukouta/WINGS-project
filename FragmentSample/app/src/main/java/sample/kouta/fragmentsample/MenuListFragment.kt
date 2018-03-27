package sample.kouta.fragmentsample


import android.app.Activity
import android.app.FragmentManager
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleAdapter
import java.util.HashMap


class MenuListFragment : Fragment() {
    var _parentActivity:Activity?=null
    var _isLayoutXLarge=true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _parentActivity=activity
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var menuThanksFrame=_parentActivity!!.findViewById<View>(R.id.menuThanksFrame)

        if (menuThanksFrame==null){
            _isLayoutXLarge=false
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view=inflater!!.inflate(R.layout.fragment_menu_list,container,false)
        var lvMenu=view.findViewById<ListView>(R.id.lvMenu)
        var menuList=ArrayList<Map<String,String>>()

        var menu= HashMap<String,String>()
        menu.put("name","唐揚げ定食")
        menu.put("price","800円")
        menuList.add(menu)

        menu= HashMap()
        menu.put("name","ハンバーグ定食")
        menu.put("price","850円")
        menuList.add(menu)

        menu= HashMap()
        menu.put("name","生姜焼き定食")
        menu.put("price","850円")
        menuList.add(menu)

        menu= HashMap()
        menu.put("name","ステーキ定食")
        menu.put("price","1000円")
        menuList.add(menu)

        menu= HashMap()
        menu.put("name","野菜炒め定食")
        menu.put("price","750円")
        menuList.add(menu)

        menu= HashMap()
        menu.put("name","とんかつ定食")
        menu.put("price","900円")
        menuList.add(menu)

        menu= HashMap()
        menu.put("name","ミンチかつ定食")
        menu.put("price","850円")
        menuList.add(menu)

        menu= HashMap()
        menu.put("name","チキンカツ定食")
        menu.put("price","900円")
        menuList.add(menu)

        menu= HashMap()
        menu.put("name","コロッケ定食")
        menu.put("price","850円")
        menuList.add(menu)

        menu= HashMap()
        menu.put("name","焼き魚定食")
        menu.put("price","750円")
        menuList.add(menu)

        menu= HashMap()
        menu.put("name","焼肉定食")
        menu.put("price","900円")
        menuList.add(menu)

        var from= arrayOf("name","price")
        val to = intArrayOf(android.R.id.text1, android.R.id.text2)
        var adapter = SimpleAdapter(_parentActivity,menuList,android.R.layout.simple_list_item_2,from,to)
        lvMenu.adapter=adapter

        lvMenu.setOnItemClickListener(ListItemClickListener())

        return view
    }

    inner class ListItemClickListener: AdapterView.OnItemClickListener{
        override fun onItemClick(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
            var item=parent.getItemAtPosition(position) as Map<String,String>
            var menuName=item.get("name")
            var menuPrice=item.get("price")

            var bundle:Bundle=Bundle()
            bundle.putString("menuName",menuName)
            bundle.putString("menuPrice",menuPrice)

            if(_isLayoutXLarge){
                var manager:android.support.v4.app.FragmentManager=fragmentManager
                var transaction:FragmentTransaction=manager.beginTransaction()
                var menuThanksFragment:MenuThanksFragment=MenuThanksFragment()

                menuThanksFragment.setArguments(bundle)
                transaction.replace(R.id.menuThanksFrame,menuThanksFragment)
                transaction.commit()
            }else{
                var intent= Intent(_parentActivity,MenuThanksActivity::class.java)

                intent.putExtras(bundle)

                startActivity(intent)
            }
        }
    }
}
