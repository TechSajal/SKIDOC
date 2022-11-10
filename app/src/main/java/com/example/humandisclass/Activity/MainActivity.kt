package com.example.humandisclass.Activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.humandisclass.Frament.ScanFragment
import com.example.humandisclass.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.system.exitProcess


class MainActivity : AppCompatActivity() {
    private lateinit var navcontroller:NavController
    private lateinit var  bottomnavitionView:BottomNavigationView
    private var id = 0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        val navController = findNavController(R.id.maincantainer)
        val options = NavOptions.Builder()
            .setLaunchSingleTop(true)
            .setPopUpTo(navController.graph.startDestinationId,false)
            .build()
        bottomnavitionView = findViewById(R.id.bottomnavigationview)
        bottomnavitionView.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.mainFragment -> {
                    id=1
                    navController.navigate(R.id.mainFragment,null,options)
                }
                R.id.scanFragment -> {
                    id=2
                    navController.navigate(R.id.scanFragment,null,options)
                }
                R.id.searchFragment -> {
                    id=3
                    navController.navigate(R.id.searchFragment,null,options)
                }
            }
            true
        }
        bottomnavitionView.setOnItemReselectedListener { item ->
            return@setOnItemReselectedListener
        }

        val i = intent.getIntExtra("place",0)
        if (i==1){
            id=1
            navController.navigate(R.id.mainFragment,null,options)
        }else if (i==2){
            id=2
            bottomnavitionView.selectedItemId = R.id.scanFragment
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemview = item.itemId
        when(itemview){
            R.id.scan -> {
                   id=2
                   bottomnavitionView.selectedItemId = R.id.scanFragment
            }
            R.id.settingtoolbar ->{
                val intent = Intent(this,SettingActivity::class.java)
                startActivity(intent)

            }
        }
        return false
    }

    private var doubleBackToExitPressedOnce = false
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (id==2){
            bottomnavitionView.selectedItemId = R.id.mainFragment
        }else if (id==3){
            bottomnavitionView.selectedItemId = R.id.mainFragment
        }else{
            if (doubleBackToExitPressedOnce){
                finish()
            }
            this.doubleBackToExitPressedOnce = true
            Toast.makeText(this, "Please click back again to exit", Toast.LENGTH_SHORT).show()
            Handler(Looper.getMainLooper()).postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
        }
    }

}