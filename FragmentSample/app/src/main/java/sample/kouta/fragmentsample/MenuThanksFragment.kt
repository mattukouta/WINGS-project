package sample.kouta.fragmentsample


import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView


class MenuThanksFragment:Fragment(){

    var _parentActivity: Activity?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _parentActivity=activity
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view=inflater!!.inflate(R.layout.fragment_menu_thanks,container,false)
        var intent=_parentActivity!!.intent

        var extras=intent.extras

        var menuName=""
        var menuPrice=""

        if(extras!=null){
            menuName=extras.getString("menuName")
            menuPrice=extras.getString("menuPrice")
        }
        var tvMenuName=view.findViewById<TextView>(R.id.tvMenuName)
        var tvMenuPrice=view.findViewById<TextView>(R.id.tvMenuPrice)

        tvMenuName.setText(menuName)
        tvMenuPrice.setText(menuPrice)

        var btBackButton=view.findViewById<Button>(R.id.btBackButton)
        btBackButton.setOnClickListener(ButtonClickListener())

        return view
    }
    inner class ButtonClickListener:View.OnClickListener{
        override fun onClick(v: View?) {
            _parentActivity!!.finish()
        }
    }
}