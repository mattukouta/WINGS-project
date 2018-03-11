package sample.kouta.listviewsample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var lvMenu:ListView=findViewById(R.id.lvMenu)

        lvMenu.setOnItemClickListener(ListItemClickListener())
    }
    inner class ListItemClickListener : AdapterView.OnItemClickListener{
        override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            var item:String=parent.getItemAtPosition(position) as String
            var show:String="あなたが選んだ定食:$item"
            Toast.makeText(this@MainActivity,show,Toast.LENGTH_LONG).show()
        }
    }
}
