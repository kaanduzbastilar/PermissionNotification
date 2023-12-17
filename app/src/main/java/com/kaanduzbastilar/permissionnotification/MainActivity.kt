package com.kaanduzbastilar.permissionnotification

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.kaanduzbastilar.permissionnotification.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var permissionLauncher: ActivityResultLauncher<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()){}
        postNotifications(binding.root)

    }

    fun postNotifications(view: View){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            if(ContextCompat.checkSelfPermission(this@MainActivity, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED){
                if(ActivityCompat.shouldShowRequestPermissionRationale(this@MainActivity, Manifest.permission.POST_NOTIFICATIONS)){
                    Snackbar.make(view,"Bildirimler için izin gerekiyor", Snackbar.LENGTH_INDEFINITE).setAction("İzin ver", View.OnClickListener {
                        permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                    }).show()
                }else{
                    permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                }
            }
        }
    }
}
