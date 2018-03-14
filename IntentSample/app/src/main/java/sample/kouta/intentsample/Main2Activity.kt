package sample.kouta.intentsample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

         var intent=getIntent()

        var menuName=intent.getStringExtra("menuName")
        var menuPrice=intent.getStringExtra("menuPrice")

        var tvMenuName=findViewById<TextView>(R.id.tvMenuName)
        var tvMenuPrice=findViewById<TextView>(R.id.tvMenuPrice)

        tvMenuName.setText(menuName)
        tvMenuPrice.setText(menuPrice)
    }

    fun onBackButtonClick(view: View){
        finish()
    }
}
