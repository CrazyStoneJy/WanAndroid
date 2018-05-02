package me.crazystone.study.wanandroid.main

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import me.crazystone.study.wanandroid.DefaultFragment
import me.crazystone.study.wanandroid.R
import me.crazystone.study.wanandroid.biz.accounting.AccountingFragment
import me.crazystone.study.wanandroid.biz.article.list.ArticleFragment
import me.crazystone.study.wanandroid.base.BaseActivity
import me.crazystone.study.wanandroid.biz.running.RunningFragment

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun setLayoutId() {
        setContentView(R.layout.activity_main)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        initFragment()

        nav_view.setNavigationItemSelectedListener(this)
    }

    fun initFragment() {
        val fragment = ArticleFragment()
        supportFragmentManager.beginTransaction().replace(R.id.main_frame, fragment).commit()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val transaction = supportFragmentManager.beginTransaction()
        var fragment: Fragment? = null
        when (item.itemId) {
            R.id.nav_accounting -> {
                fragment = AccountingFragment()
            }
            R.id.nav_running -> {
                fragment = RunningFragment()
            }
            R.id.nav_slideshow -> {
                fragment = DefaultFragment()
            }
            R.id.nav_manage -> {
                fragment = DefaultFragment()
            }
            R.id.nav_share -> {
                fragment = DefaultFragment()
            }
            R.id.nav_send -> {
                fragment = DefaultFragment()
            }
        }

        transaction.replace(R.id.main_frame, fragment)
        transaction.commit()
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
