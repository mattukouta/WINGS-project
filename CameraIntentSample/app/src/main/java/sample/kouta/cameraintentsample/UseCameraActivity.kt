package sample.kouta.cameraintentsample

import android.Manifest
import android.app.Activity
import android.content.ContentResolver
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.icu.text.SimpleDateFormat
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.view.View
import android.widget.ImageView
import java.util.*

class UseCameraActivity : AppCompatActivity() {

    var _imageUri:Uri?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_use_camera)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent){
        if(requestCode==200 && resultCode== RESULT_OK){
            var ivCamera:ImageView=findViewById(R.id.ivCamera)
            ivCamera.setImageURI(_imageUri)
//            var bitmap=data.getParcelableExtra<Bitmap>("data")
//            var ivCamera=findViewById<ImageView>(R.id.ivCamera)
//            ivCamera.setImageBitmap(bitmap)
        }
    }

    fun onCameraImageClick(view:View){
        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
            var permissions= arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            ActivityCompat.requestPermissions(this,permissions,2000)
            return
        }
        var dateFormat=SimpleDateFormat("yyyyMMddHHmmss")
        var now= Date(System.currentTimeMillis())
        var nowStr:String= dateFormat.format(now)
        var fileName="UseCameraActivityPhoto_$nowStr.jpg"
        var values= ContentValues()

        values.put(MediaStore.Images.Media.TITLE,fileName)
        values.put(MediaStore.Images.Media.MIME_TYPE,"image/jpeg")

        var resolver:ContentResolver=contentResolver

        _imageUri=resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values)

        var intent=Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        intent.putExtra(MediaStore.EXTRA_OUTPUT,_imageUri)

        startActivityForResult(intent,200)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if(requestCode==2000 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            var ivCamera=findViewById<ImageView>(R.id.ivCamera)
            onCameraImageClick(ivCamera)
        }
    }
}
