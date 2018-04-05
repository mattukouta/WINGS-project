package sample.kouta.mediasample

import android.media.MediaPlayer
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CompoundButton
import android.widget.Switch
import java.io.IOException

class MediaControlActivity : AppCompatActivity() {

    private var _player:MediaPlayer?=null
    private var _btPlay:Button?=null
    private var _btBack:Button?=null
    private var _btForward:Button?=null

    override protected fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media_control)

        _btPlay=findViewById(R.id.btPlay)
        _btBack=findViewById(R.id.btBack)
        _btForward=findViewById(R.id.btForward)

        _player=MediaPlayer()
        var mediaFileUriStr:String="android.resource://"+packageName+"/"+R.raw.mountain_stream
        var mediaFileUri:Uri=Uri.parse(mediaFileUriStr)
        try {
            _player!!.setDataSource(this@MediaControlActivity,mediaFileUri)
            _player!!.setOnPreparedListener{PlayerPreparedListener()}
            _player!!.setOnCompletionListener {PlayerCompletionListener()}
            _player!!.prepareAsync()
        }
        catch (e:IOException){
            e.printStackTrace()
        }

        var loopSwitch=findViewById<Switch>(R.id.swLoop)
        loopSwitch.setOnCheckedChangeListener(LoopSwitchChangedListener())
    }
    inner class PlayerPreparedListener:MediaPlayer.OnPreparedListener{
        override fun onPrepared(mp: MediaPlayer) {
            _btPlay!!.setEnabled(true)
            _btBack!!.setEnabled(true)
            _btForward!!.setEnabled(true)
        }
    }
    inner class PlayerCompletionListener:MediaPlayer.OnCompletionListener{
        override fun onCompletion(mp: MediaPlayer) {
            if(!_player!!.isLooping) {
                _btPlay!!.setText(R.string.bt_play_play)
            }
        }
    }

    fun onPlayButtonClick(view:View){
        if(_player!!.isPlaying){
            _player!!.pause()
            _btPlay!!.setText(R.string.bt_play_play)
        }
        else{
            _player!!.start()
            _btPlay!!.setText(R.string.bt_play_pause)
        }
    }


    override protected fun onDestroy() {
        super.onDestroy()
        if(_player!!.isPlaying){
            _player!!.stop()
        }
        _player!!.release()
        _player=null
    }

    fun onBackButtonClick(view:View){
        _player!!.seekTo(0)
    }
    fun onForwardButtonClick(view:View){
        var duration:Int=_player!!.duration
        _player!!.seekTo(duration)
        if(!_player!!.isPlaying){
            _player!!.start()
        }
    }
    inner class LoopSwitchChangedListener:CompoundButton.OnCheckedChangeListener{
        override fun onCheckedChanged(buttonView: CompoundButton, isChecked: Boolean) {
          _player!!.setLooping(isChecked)
        }
    }
}
