package org.android.an.oa4android

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import org.android.an.oa4android.dummy.DummyContent

class MainActivity : AppCompatActivity(),
        NavigationView.OnNavigationItemSelectedListener,
        MineFragment.OnListFragmentInteractionListener {

    override fun onListFragmentInteraction(item: DummyContent.DummyItem?) {

    }

    private var navigationCheckId: Int = 0

    private val homeFragment: HomeFragment
        get() = supportFragmentManager.findFragmentByTag(HOME_FRAGMENT_TAG) as HomeFragment?
                ?: HomeFragment.newInstance("", "")

    private val passwordFragment: PasswordFragment
        get() = supportFragmentManager.findFragmentByTag(PASSWORD_FRAGMENT_TAG) as PasswordFragment?
                ?: PasswordFragment.newInstance()

    private val workFragment: WorkFragment
        get() = supportFragmentManager.findFragmentByTag(WORK_FRAGMENT_TAG) as WorkFragment?
                ?: WorkFragment.newInstance("", "")

    private val dynamicFragment: DynamicFragment
        get() = supportFragmentManager.findFragmentByTag(DYNAMIC_FRAGMENT_TAG) as DynamicFragment?
                ?: DynamicFragment.newInstance(1)

    private val reportFragment: ReportFragment
        get() = supportFragmentManager.findFragmentByTag(REPORT_FRAGMENT_TAG) as ReportFragment?
                ?: ReportFragment.newInstance(1)

    private val personalFragment: PersonalFragment
        get() = supportFragmentManager.findFragmentByTag(PERSONAL_FRAGMENT_TAG) as PersonalFragment?
                ?: PersonalFragment.newInstance("", "")

    private val colleagueFragment: ColleagueFragment
        get() = supportFragmentManager.findFragmentByTag(COLLEAGUES_FRAGMENT_TAG) as ColleagueFragment?
                ?: ColleagueFragment.newInstance(1)

    private val annualFragment: MineFragment
        get() = supportFragmentManager.findFragmentByTag(ANNUAL_FRAGMENT_TAG) as MineFragment?
                ?: MineFragment.newInstance(MineFragment.TYPE_ANNUAL)

    private val quarterFragment: MineFragment
        get() = supportFragmentManager.findFragmentByTag(QUARTER_FRAGMENT_TAG) as MineFragment?
                ?: MineFragment.newInstance(MineFragment.TYPE_QUARTER)

    private val monthlyFragment: MineFragment
        get() = supportFragmentManager.findFragmentByTag(MONTHLY_FRAGMENT_TAG) as MineFragment?
                ?: MineFragment.newInstance(MineFragment.TYPE_MONTHLY)

    private val weeklyFragment: MineFragment
        get() = supportFragmentManager.findFragmentByTag(WEEKLY_FRAGMENT_TAG) as MineFragment?
                ?: MineFragment.newInstance(MineFragment.TYPE_WEEKLY)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        if (savedInstanceState == null) {
            showFragmentInActivity("首页", homeFragment, HOME_FRAGMENT_TAG)
        }
    }

    private fun showFragmentInActivity(title: String, fragment: Fragment, tag: String) {
        supportActionBar?.title = title
        supportFragmentManager.apply {
            beginTransaction().apply {
                if (fragment.isAdded) {
                    show(fragment)
                } else {
                    add(R.id.content, fragment, tag)
                }
                for (f in fragments) {
                    if (f != fragment) { hide(f) }
                }
                commit()

            }
        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (navigationCheckId != 0) {
            nav_view.menu.findItem(navigationCheckId).isChecked = false
            navigationCheckId = 0
        }
        return when (item.itemId) {
            R.id.home -> {
                showFragmentInActivity(item.title.toString(), homeFragment, HOME_FRAGMENT_TAG)
                true
            }
            R.id.password -> {
                showFragmentInActivity(item.title.toString(), passwordFragment, PASSWORD_FRAGMENT_TAG)
                true
            }
            R.id.logout -> {

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        navigationCheckId = item.itemId
        when (item.itemId) {
            R.id.nav_work -> {
                showFragmentInActivity(item.title.toString(), workFragment, WORK_FRAGMENT_TAG)
            }
            R.id.nav_dynamic -> {
                showFragmentInActivity(item.title.toString(), dynamicFragment, DYNAMIC_FRAGMENT_TAG)
            }
            R.id.nav_report -> {
                showFragmentInActivity(item.title.toString(), reportFragment, REPORT_FRAGMENT_TAG)
            }
            R.id.nav_personal -> {
                showFragmentInActivity(item.title.toString(), personalFragment, PERSONAL_FRAGMENT_TAG)
            }
            R.id.nav_colleague -> {
                showFragmentInActivity(item.title.toString(), colleagueFragment, COLLEAGUES_FRAGMENT_TAG)
            }
            R.id.nav_annual -> {
                showFragmentInActivity(item.title.toString(), annualFragment, ANNUAL_FRAGMENT_TAG)
            }
            R.id.nav_quarter -> {
                showFragmentInActivity(item.title.toString(), quarterFragment, QUARTER_FRAGMENT_TAG)
            }
            R.id.nav_monthly -> {
                showFragmentInActivity(item.title.toString(), monthlyFragment, MONTHLY_FRAGMENT_TAG)
            }
            R.id.nav_weekly -> {
                showFragmentInActivity(item.title.toString(), weeklyFragment, WEEKLY_FRAGMENT_TAG)
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    companion object {
        private const val HOME_FRAGMENT_TAG = "home"
        private const val PASSWORD_FRAGMENT_TAG = "password"
        private const val WORK_FRAGMENT_TAG = "work"
        private const val DYNAMIC_FRAGMENT_TAG = "dynamic"
        private const val REPORT_FRAGMENT_TAG = "report"
        private const val PERSONAL_FRAGMENT_TAG = "personal"
        private const val COLLEAGUES_FRAGMENT_TAG = "colleague"
        private const val ANNUAL_FRAGMENT_TAG = "annual"
        private const val QUARTER_FRAGMENT_TAG = "quarter"
        private const val MONTHLY_FRAGMENT_TAG = "monthly"
        private const val WEEKLY_FRAGMENT_TAG = "weekly"
    }

}
