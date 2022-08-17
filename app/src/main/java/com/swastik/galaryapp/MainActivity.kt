package com.swastik.galaryapp

import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var imagepicker = arrayListOf<viewData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if (ActivityCompat.checkSelfPermission(this,android.Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),1010)
        }

        imagepicker = ArrayList()
        if (imagepicker!!.isEmpty()) {

            progress_bar.visibility = ProgressBar.VISIBLE;
            imagepicker = getimages()
            recycler_view.adapter = Adapter(this, imagepicker!!)
            recycler_view.layoutManager = GridLayoutManager(this, 3)
            recycler_view.setHasFixedSize(true)
            progress_bar.visibility = ProgressBar.GONE;

        }


    }


    private fun getimages(): ArrayList<viewData> {
        val images = ArrayList<viewData>()

        var allimaeuri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI

        val projection = arrayOf(MediaStore.Images.ImageColumns.DATA,
            MediaStore.Images.Media.DISPLAY_NAME)


        val cursor = this.contentResolver.query(allimaeuri, projection , null, null, null)

        try {
            cursor!!.moveToFirst()
            do {
                val image = viewData()
                image.image =
                    cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA))
                image.image_name =
                    cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME))

                images.add(image)
            } while (cursor.moveToNext())
            cursor.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return images
    }

}