package com.android_demo.mainactivity


import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.SystemClock
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.RootMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.v7.widget.RecyclerView
import com.android_demo.R
import com.android_demo.ui.mainscreen.ui.MainActivity
import org.hamcrest.Matchers
import org.junit.*
import org.junit.runners.MethodSorters
/**
 * MainActivityUITestCases
 * @desc - Ui test for MainActivity
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class MainActivityUITestCases {
    private var activeNetworkInfo: NetworkInfo? = null

    @get:Rule
    val activityTestRule: ActivityTestRule<MainActivity> = ActivityTestRule(
        MainActivity::class.java)


    @Before
    fun setUp() {
        val connectivityManager =
            activityTestRule.activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        activeNetworkInfo = connectivityManager.activeNetworkInfo
    }

    /**testAInitialNetwork - Check if network is not available, Dialog is displaying*/
    @Test
    fun testAInitialNetwork() {
        if (!activeNetworkInfo!!.isConnected)
            onView(withText(R.string.networkerror_meesgae)).check(matches(isDisplayed()))
    }

    /**testBSampleRecyclerVisible - Check Recycler view is visible*/
    @Test
    fun testBSampleRecyclerVisible() {
        onView(withId(R.id.recyclerView))
            .inRoot(
                RootMatchers.withDecorView(
                    Matchers.`is`(activityTestRule.activity.window.decorView)
                )
            )
            .check(matches(isDisplayed()))
    }

    /**testCForRecyclerItemCount - Check row items count in Recyclerview*/
    @Test
    fun testCForRecyclerItemCount() {
        testWaitforNetworkReq()
        val recyclerView = activityTestRule.activity.findViewById<RecyclerView>(R.id.recyclerView)
        Assert.assertNotNull(recyclerView)
        Assert.assertNotNull(recyclerView.adapter)
        val count = 5/*count to check with row item*/
        Assert.assertTrue(recyclerView.adapter!!.itemCount > count)

    }



    private fun testWaitforNetworkReq() {
        SystemClock.sleep(5000)/*sleep for completion network calls and get response*/
    }

}
