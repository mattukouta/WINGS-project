package sample.kouta.toolbarsample

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toolbar

class ScrollArticleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scroll_article)

        var toolbar=findViewById<android.support.v7.widget.Toolbar>(R.id.toolbar)
        toolbar.setLogo(R.mipmap.ic_launcher)
        toolbar.setTitle(R.string.toolbar_title)
        toolbar.setTitleTextColor(Color.WHITE)
        toolbar.setSubtitle(R.string.toolbar_subtitle)
        toolbar.setSubtitleTextColor(Color.LTGRAY)
        setSupportActionBar(toolbar)
    }
}
