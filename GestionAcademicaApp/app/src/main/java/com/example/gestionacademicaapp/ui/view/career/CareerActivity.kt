package com.example.gestionacademicaapp.ui.view.career

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.drawerlayout.widget.DrawerLayout.DrawerListener
import com.example.gestionacademicaapp.R
import com.example.gestionacademicaapp.databinding.ActivityCareerBinding
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener
import kotlinx.android.synthetic.main.activity_career.*
import kotlinx.android.synthetic.main.nav_fragment_container.*
import kotlinx.android.synthetic.main.nav_fragment_container.view.*

class CareerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCareerBinding
    private var selectedOption = R.id.nav_item_careers
    private var toolbarText = "Careers"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCareerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.navView.toolbar)

        var toggle = ActionBarDrawerToggle(this, binding.drawerLayout, toolbar, R.string.open, R.string.close)
        toggle.isDrawerIndicatorEnabled = true
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        toolbar.title = "Careers"
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, CareersFragment()).commit()

        //Set listenrs
        drawerLayout.addDrawerListener(drawerListener)
        binding.navView.setNavigationItemSelectedListener(navigationListener)
    }

    var drawerListener = object : DrawerListener {
        override fun onDrawerSlide(drawerView: View, slideOffset: Float) {}

        override fun onDrawerOpened(drawerView: View) {}

        override fun onDrawerClosed(drawerView: View) {
            when (selectedOption) {
                R.id.nav_item_careers -> {
                    toolbar.title = "Careers"
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container, CareersFragment())
                        .commit()
                }
            }
        }

        override fun onDrawerStateChanged(newState: Int) {}
    }

    var navigationListener =
        OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_item_careers -> {
                    selectedOption = R.id.nav_item_careers
                }
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }


}