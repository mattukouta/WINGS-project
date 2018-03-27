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
    var _isLayoutXLarge:Boolean=true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _parentActivity=activity

        var manager=fragmentManager
        var menuListFragment=manager.findFragmentById(R.id.fragmentMenuList)

        if(menuListFragment==null){
            _isLayoutXLarge=false
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view=inflater!!.inflate(R.layout.fragment_menu_thanks,container,false)
        var extras:Bundle?=null

        if(_isLayoutXLarge){
            extras=arguments
        }else{
            var intent=_parentActivity!!.intent
            extras=intent.extras
        }

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
        override fun onClick(view: View) {
            if(_isLayoutXLarge){
                var manager=fragmentManager
                var transaction=manager.beginTransaction()
                transaction.remove(this@MenuThanksFragment)
                transaction.commit()
            }else {
                _parentActivity!!.finish()
            }
        }
    }
}