package com.android_demo.mainactivity


import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.support.test.rule.ActivityTestRule
import com.android_demo.model.DataModel
import com.android_demo.network.ApiInterface
import com.android_demo.network.RetrofitClientInstance
import com.android_demo.ui.mainscrenn.ui.MainActivity
import com.android_demo.utils.CONSTANTS
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import org.junit.*
import org.junit.runners.MethodSorters
import retrofit2.Response
import java.io.IOException

/**
 * MainActivityUnitTestCase
 * @desc- Test cases for MainActivity
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class MainActivityUnitTestCase {
    private var apiEndpoints: ApiInterface? = null
    private var activeNetworkInfo: NetworkInfo? = null

    @get:Rule
    val activityTestRule: ActivityTestRule<MainActivity> = ActivityTestRule(
        MainActivity::class.java)


    @Before
    fun setUp() {
        apiEndpoints = RetrofitClientInstance().getClient().create(ApiInterface::class.java)
        val connectivityManager =
            activityTestRule.activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        activeNetworkInfo = connectivityManager.activeNetworkInfo
    }

    /**testAInitialNetwork - Network Check*/
    @Test
    fun testANetworkConnection() {
        assertNotNull(activeNetworkInfo)
        activeNetworkInfo?.isConnected?.let { assertTrue(it) }
    }

    /**testBfetchNetworkData - Fetching data from network*/
    @Test
    @Throws(InterruptedException::class)
    fun testBfetchNetworkData() {
        try {
            val response = onGetResponse()
            assertNotNull(response)
            response?.isSuccessful?.let { assertTrue(it) }

        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    /**testCForRowItemCount - check row items count*/
    @Test
    fun testCForRowItemCount() {
        val data = onGetResponse()
        val rowResponse = data?.body()
        Assert.assertNotNull(rowResponse?.results)
        val rowData: Int = rowResponse?.results?.size!!

        Assert.assertTrue(rowData > 5)

    }

    private fun onGetResponse(): Response<DataModel>? {
        val call = apiEndpoints?.getListData(CONSTANTS.DATA_URL)
        try {
            return call?.execute()

        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }
}
