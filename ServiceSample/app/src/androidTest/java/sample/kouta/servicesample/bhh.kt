package sample.kouta.servicesample

import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import android.media.MediaPlayer
import android.support.v4.app.NotificationCompat

class bhh {
    fun onCompletion(mp: MediaPlayer) {
        val builder = NotificationCompat.Builder(this@SoundManageService, "soundmanagerservice_notification_channel")
        builder.setSmallIcon(android.R.drawable.ic_dialog_info)
        builder.setContentTitle(getString(R.string.msg_notification_title_finish))
        builder.setContentText(getString(R.string.msg_notification_text_finish))
        val notification = builder.build()
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as Notificationmanager
        manager.notify(0, notification)
        stopSelf()
    }
}
