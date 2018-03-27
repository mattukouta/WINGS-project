package sample.kouta.fragmentsample


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView

class clas : Fragment() {
    protected fun onCreate(saveInstanceState: Bundle) {
        super.onCreate(saveInstanceState)
        setContentView(R.layout.activity_main)
    }

    private inner class ListItemClickListener : AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            val intent = Intent(_parentActivity, MenuListActivity::class.java)

            menuThanksFragment.setArguments(bundle)
        }
    }
}
