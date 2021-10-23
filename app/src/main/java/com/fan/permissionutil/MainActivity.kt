package com.fan.permissionutil

import android.Manifest
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnTest).setOnClickListener {

            PermissionUtil.request(
                this, Manifest.permission.CALL_PHONE,
                Manifest.permission.CAMERA, Manifest.permission.ACCESS_COARSE_LOCATION
            ) { allGranted, deniedList ->
                run {
                    if (allGranted) {
                        call()
                    } else {
                        Toast.makeText(this, "你拒绝了$deniedList.", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    fun call() {
        try {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:10086")
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}