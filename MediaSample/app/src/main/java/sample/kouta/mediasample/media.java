package sample.kouta.mediasample;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import java.io.IOException;

public class media extends AppCompatActivity {
    private MediaPlayer _player;
    private Button _btPlay;
    private Button _btBack;
    private Button _btForward;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_media_control);

        _btPlay=findViewById(R.id.btPlay);
        _btBack=findViewById(R.id.btBack);
        _btForward=findViewById(R.id.btForward);

        _player=new MediaPlayer();
        String mediaFileUriStr="android.resource://"+getPackageName()+"/"+R.raw.mountain_stream;
        Uri mediaFileUri=Uri.parse(mediaFileUriStr);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try{
            _player.setDataSource(media.this,mediaFileUri);
            _player.setOnPreparedListener(new PlayerPreparedListener());
            _player.setOnCompletionListener(new PlayerCompletionListener());
            _player.prepareAsync();
        }
        catch(IOException e){
            e.printStackTrace();
        }

        Switch loopSwitch=findViewById(R.id.swLoop);
        loopSwitch.setOnCheckedChangeListener(new LoopSwitchChangedListener());
    }
    private class PlayerPreparedListener implements MediaPlayer.OnPreparedListener{
        @Override
        public void onPrepared(MediaPlayer mp){
            _btPlay.setEnabled(true);
            _btBack.setEnabled(true);
            _btForward.setEnabled(true);
        }
    }
    private class PlayerCompletionListener implements MediaPlayer.OnCompletionListener{
        @Override
        public void onCompletion(MediaPlayer mp){
            if(!_player.isLooping()) {
                _btPlay.setText(R.string.bt_play_play);
            }
        }
    }
    public void onPlayButtonClick(View view){
        if (_player.isPlaying()){
            _player.pause();
            _btPlay.setText(R.string.bt_play_play);
        }
        else {
            _player.start();
            _btBack.setText(R.string.bt_play_pause);
        }
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(_player.isPlaying()){
            _player.stop();
        }
        _player.release();
        _player=null;
    }

    public void onBackButtonClick(View view){
        _player.seekTo(0);
    }
    public void onForwardButtonClick(View view){
        int duration=_player.getDuration();
        _player.seekTo(duration);
        if(!_player.isPlaying()){
            _player.start();
        }
    }
    private class LoopSwitchChangedListener implements CompoundButton.OnCheckedChangeListener{
        @Override
        public void onCheckedChanged(CompoundButton buttonView,boolean isChecked){
            _player.setLooping(isChecked);
        }
    }
}
