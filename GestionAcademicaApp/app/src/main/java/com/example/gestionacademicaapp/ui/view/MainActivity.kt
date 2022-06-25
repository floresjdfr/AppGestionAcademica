package com.example.gestionacademicaapp.ui.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout.DrawerListener
import com.example.gestionacademicaapp.R
import com.example.gestionacademicaapp.databinding.ActivityMainBinding
import com.example.gestionacademicaapp.ui.view.career.CareersFragment
import com.example.gestionacademicaapp.ui.view.cycle.CyclesFragment
import com.example.gestionacademicaapp.ui.view.student.StudentsFragment
import com.example.gestionacademicaapp.ui.view.teacher.TeachersFragment
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.nav_fragment_container.*
import kotlinx.android.synthetic.main.nav_fragment_container.view.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var selectedOption = R.id.nav_item_careers
    private var toolbarText = "Careers"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
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
                R.id.nav_item_cycle -> {
                    toolbar.title = "Cycles"
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container, CyclesFragment())
                        .commit()
                }
                R.id.nav_item_teacher -> {
                    toolbar.title = "Teachers"
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container, TeachersFragment())
                        .commit()
                }
                R.id.nav_item_student -> {
                    toolbar.title = "Students"
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container, StudentsFragment())
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
                R.id.nav_item_cycle -> {
                    selectedOption = R.id.nav_item_cycle
                }
                R.id.nav_item_teacher -> {
                    selectedOption = R.id.nav_item_teacher
                }
                R.id.nav_item_student -> {
                    selectedOption = R.id.nav_item_student
                }
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }


}