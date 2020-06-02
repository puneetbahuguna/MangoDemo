package com.schmersaldemo.mango.view

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import com.schmersaldemo.mango.R
import com.schmersaldemo.mango.biometric.DeviceAuthPrompt
import com.schmersaldemo.mango.settings.Preferences
import com.schmersaldemo.mango.view.viewmodels.HomeScreenViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.nav_header_main.*
import kotlinx.coroutines.launch

/***
Author: Puneet Bahuguna
 ***/

class MainActivity : DeviceAuthPrompt(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var homeScreenViewModel: HomeScreenViewModel
    lateinit var context: Context
    var loggedinUserRole:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this@MainActivity.applicationContext
        homeScreenViewModel = ViewModelProvider(this).get(HomeScreenViewModel::class.java)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)
        navView.getHeaderView(0).findViewById<TextView>(R.id.username)
            .setText(Preferences.getUsername(this))
        lifecycleScope.launch {
            if(Preferences.getLangCode(context).equals("PT-BR")){
                loggedinUserRole = homeScreenViewModel.getUserRolePR(Preferences.getuserRoleID(context))
            }else{
                loggedinUserRole = homeScreenViewModel.getUserRole(Preferences.getuserRoleID(context))
            }
            userrole.setText(loggedinUserRole)
        }
        setNavigationChecked()
        populateCustomerList()
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        setNavigationChecked()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        //menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        /*return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }*/
        return false
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_cuslist -> {
                // Handle the camera action
            }
            R.id.nav_logout -> {
                AlertDialog.Builder(this)
                    .setTitle(getString(R.string.alertitle))
                    .setMessage(getString(R.string.alermsg))
                    .setPositiveButton(getString(R.string.ok), { dialog: DialogInterface, which: Int -> logout() })
                    .setNegativeButton(getString(R.string.cancel)) { dialog: DialogInterface, which: Int ->
                        dialog.dismiss()
                        setNavigationChecked()
                    }.show()

            }
            R.id.nav_setting->{
                startActivity(Intent().setClass(this,GeneralSettings::class.java))
            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    fun logout() {
        startActivity(Intent().setClass(this, AddLoginAccount::class.java))
        MainActivity().finish()
    }

    fun setNavigationChecked() {
        nav_view.menu.getItem(0).setChecked(true)
    }

    fun populateCustomerList() {
        lifecycleScope.launch {
            val customerList = homeScreenViewModel.getCustomerList(Preferences.getuserRoleID(this@MainActivity))
            customerRecyclelist.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = CustomerListAdapter(customerList)
            }
        }
    }
}