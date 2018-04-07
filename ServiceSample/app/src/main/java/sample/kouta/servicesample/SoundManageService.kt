package sample.kouta.servicesample

import android.app.*
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.IBinder
import android.provider.MediaStore
import android.support.v4.app.NotificationCompat
import java.io.IOException

class SoundManageService : Service() {

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    var _player: MediaPlayer?=null

    override fun onCreate() {
        _player= MediaPlayer()

        var id="soundmanagerservice_notification_channel"
        var name=getString(R.string.notification_channel_name)
        var importance=NotificationManager.IMPORTANCE_DEFAULT
        var channel=NotificationChannel(id,name,importance)
        var manager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(channel)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        var mediaFileUriStr="android.resource://"+packageName+"/"+R.raw.mountain_stream
        var mediaFileUri= Uri.parse(mediaFileUriStr)
        try{
            _player!!.setDataSource(this@SoundManageService,mediaFileUri)
            _player!!.setOnPreparedListener (PlayerPreparedListener())
            _player!!.setOnCompletionListener (PlayerCompletionListener())
            _player!!.prepareAsync()
        }
        catch (e:IOException){
            e.printStackTrace()
        }
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        if(_player!!.isPlaying){
            _player!!.stop()
        }
        _player!!.release()
        _player=null
    }

    inner class PlayerPreparedListener : MediaPlayer.OnPreparedListener{
        override fun onPrepared(mp: MediaPlayer?) {
            mp!!.start()

            var builder=NotificationCompat.Builder(this@SoundManageService,"soundmanagerservice_notification_channel")
            builder.setSmallIcon(android.R.drawable.ic_dialog_info)
            builder.setContentTitle(getString(R.string.msg_notification_title_start))
            builder.setContentText(getString(R.string.msg_notification_text_start))
            var intent=Intent(this@SoundManageService,SoundStartActivity::class.java)
            intent.putExtra("fromNotification",true)
            var stopServiceIntent=PendingIntent.getActivity(this@SoundManageService,0,intent,PendingIntent.FLAG_CANCEL_CURRENT)
            builder.setContentIntent(stopServiceIntent)
            builder.setAutoCancel(true)
            var notification=builder.build()
            var manager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.notify(1,notification)
        }
    }
    inner class PlayerCompletionListener : MediaPlayer.OnCompletionListener{
        override fun onCompletion(mp: MediaPlayer?) {
            var builder=NotificationCompat.Builder(this@SoundManageService,"soundmanagerservice_notification_channel")
            builder.setSmallIcon(android.R.drawable.ic_dialog_info)
            builder.setContentTitle(getString(R.string.msg_notification_title_finish))
            builder.setContentText(getString(R.string.msg_notification_text_finish))
            var notification=builder.build()
            var manager =getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.notify(0, notification)
            stopSelf()
        }
    }
}
