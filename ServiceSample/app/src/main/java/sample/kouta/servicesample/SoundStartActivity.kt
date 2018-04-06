package sample.kouta.servicesample

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AppCompatViewInflater
import android.view.View
import android.widget.Button

class SoundStartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sound_start)
    }
    fun onPlayButtonClick(view:View){
        var intent=Intent(this@SoundStartActivity,SoundManageService::class.java)
        startService(intent)

        var btPlay=findViewById<Button>(R.id.btPlay)
        var btStop=findViewById<Button>(R.id.btStop)
        btPlay.isEnabled=false
        btStop.isEnabled=true
    }
    fun onStopButtonClick(view: View){
        var intent=Intent(this@SoundStartActivity,SoundManageService::class.java)
        stopService(intent)

        var btPlay=findViewById<Button>(R.id.btPlay)
        var btStop=findViewById<Button>(R.id.btStop)
        btPlay.isEnabled=true
        btStop.isEnabled=false
    }
}
