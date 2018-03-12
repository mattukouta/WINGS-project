package sample.kouta.listviewsample2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var lvMenu=findViewById(R.id.lvMenu) as ListView
        var menuList=ArrayList<String>()

        menuList.add("唐揚げ定食")
        menuList.add("ハンバーグ定食")
        menuList.add("生姜焼き定食")
        menuList.add("ステーキ定食")
        menuList.add("野菜炒め定食")
        menuList.add("とんかつ定食")
        menuList.add("ミンチかつ定食")
        menuList.add("チキンカツ定食")
        menuList.add("コロッケ定食")
        menuList.add("焼き魚定食")
        menuList.add("焼肉定食")

        var adapter=ArrayAdapter<String>(this@MainActivity,android.R.layout.simple_list_item_1,menuList)
        lvMenu.adapter=adapter
        lvMenu.setOnItemClickListener(ListItemClickListener())

    }

    inner class ListItemClickListener : AdapterView.OnItemClickListener{
        override  fun onItemClick(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
            var dialogFragment=OrderConfirmDialogFragment()
            dialogFragment.show(fragmentManager,"OrderConfirmDialogFragment")
        }
    }
}
