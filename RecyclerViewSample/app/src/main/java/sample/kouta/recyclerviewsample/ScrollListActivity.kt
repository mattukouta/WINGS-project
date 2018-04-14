package sample.kouta.recyclerviewsample

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v4.content.res.TypedArrayUtils.getString
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.support.v7.widget.Toolbar
import android.widget.Toast

class ScrollListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scroll_list)

        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.setLogo(R.mipmap.ic_launcher)
        setSupportActionBar(toolbar)
        var toolbarLayout = findViewById<CollapsingToolbarLayout>(R.id.toolbarLayout)
        toolbarLayout.title = getString(R.string.toolbar_title)
        toolbarLayout.setExpandedTitleColor(Color.WHITE)
        toolbarLayout.setCollapsedTitleTextColor(Color.LTGRAY)

        var lvMenu=findViewById<RecyclerView>(R.id.lvMenu)
        var layout=LinearLayoutManager(this@ScrollListActivity)
        lvMenu.layoutManager=layout
        var menuList=createTeisyokuList()
        var adapter=RecyclerListAdapter(menuList)
        lvMenu.adapter=adapter

        var decorator=DividerItemDecoration(this@ScrollListActivity,layout.orientation)
        lvMenu.addItemDecoration(decorator)
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
        menu["price"]=850
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


    inner class RecyclerListViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        var _tvMenuName:TextView
        var _tvMenuPrice:TextView
        init {
            _tvMenuName = itemView.findViewById(R.id.tvMenuName)
            _tvMenuPrice = itemView.findViewById(R.id.tvMenuPrice)
        }
    }
    inner class RecyclerListAdapter(var _listData: List<Map<String, Any>>) :RecyclerView.Adapter<RecyclerListViewHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup,viewType:Int):RecyclerListViewHolder{
            var inflater=LayoutInflater.from(this@ScrollListActivity)
            var view=inflater.inflate(R.layout.row,parent,false)
            view.setOnClickListener(ItemClickListener())
            return RecyclerListViewHolder(view)
        }
        override fun onBindViewHolder(holder: RecyclerListViewHolder, position: Int) {
            var item=_listData.get(position)
            var menuName=item.get("name") as String
            var menuPrice=item.get("price") as Int
            var menuPriceStr= menuPrice.toString()
            holder._tvMenuName.text=menuName
            holder._tvMenuPrice.text=menuPriceStr
        }
        override fun getItemCount(): Int {
            return _listData.size
        }
    }
    inner class ItemClickListener:View.OnClickListener{
        override fun onClick(view: View?) {
            var tvMenuName=view!!.findViewById<TextView>(R.id.tvMenuName)
            var menuName=tvMenuName.text.toString()
            var msg= getString(R.string.msg_header)+menuName
            Toast.makeText(this@ScrollListActivity,msg,Toast.LENGTH_SHORT).show()
        }
    }
}
