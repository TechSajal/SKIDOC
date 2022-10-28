package com.example.humandisclass.Activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
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


class MainActivity : AppCompatActivity() {
    private lateinit var navcontroller:NavController
    private lateinit var  bottomnavitionView:BottomNavigationView
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
                    navController.navigate(R.id.mainFragment,null,options)
                }
                R.id.scanFragment -> {
                    navController.navigate(R.id.scanFragment,null,options)
                }
                R.id.searchFragment -> {
                    navController.navigate(R.id.scanFragment,null,options)
                }
            }
            true
        }
        bottomnavitionView.setOnItemReselectedListener { item ->
            return@setOnItemReselectedListener
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
                   bottomnavitionView.selectedItemId = R.id.scanFragment
            }
            R.id.settingtoolbar ->{
                val intent = Intent(this,SettingActivity::class.java)
                startActivity(intent)

            }
        }
        return false
    }

}