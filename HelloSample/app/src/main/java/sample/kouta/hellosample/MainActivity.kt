package sample.kouta.hellosample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_main.view.*
import sample.kouta.hellosample.R.id.tvOutput

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btClick=findViewById<Button>(R.id.btClick)
        var listener=HelloListener()
        btClick.setOnClickListener(listener)

        val btClear=findViewById<Button>(R.id.btClear)
        btClear.setOnClickListener(listener)
    }
    private inner class HelloListener : View.OnClickListener{
        override fun onClick(view: View){
            var input=findViewById<EditText>(R.id.etName)
            var output=findViewById<TextView>(R.id.tvOutput)
            var id:Int=view.getId()
            var inputStr:String

            when(id){
                R.id.btClick->{
                    inputStr=input.getText().toString()
                    output.text=inputStr+"さん、こんにちは"
                }
                R.id.btClear->{
                    input.text=null
                    output.text=null
                }
            }
        }
    }
}
