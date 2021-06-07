package com.chintankhetiya.mvvm.architectural

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.chintankhetiya.mvvm.architectural.adapter.UniversitiesAdapter
import com.chintankhetiya.mvvm.architectural.constant.UtilConstant
import com.chintankhetiya.mvvm.architectural.databinding.ActivityUniversitiesDashboardBinding
import com.chintankhetiya.mvvm.architectural.model.UniversitiesEntity
import com.chintankhetiya.mvvm.architectural.utils.AppUtility
import com.chintankhetiya.mvvm.architectural.utils.affectOnItemClicks
import com.chintankhetiya.mvvm.architectural.viewmodel.UniversitiesViewModel

class UniversitiesDashboardActivity : AppCompatActivity() {
    var mArrUniversitiesList = ArrayList<UniversitiesEntity>()
    lateinit var objUniversitiesAdapter: UniversitiesAdapter
    lateinit var universitiesViewModel: UniversitiesViewModel
    lateinit var bindMainActivity: ActivityUniversitiesDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindMainActivity =
            DataBindingUtil.setContentView(this, R.layout.activity_universities_dashboard)
        bindMainActivity.setLifecycleOwner(this);

        /*Init Toolbar */
        setupToolbar()

        /*Init View Model */
        universitiesViewModel = ViewModelProvider(this).get(UniversitiesViewModel::class.java)

        /*Init RecyclerView with MVVM LiveData*/
        setupRecyclerView();

        /*Call the data : API or Local Database - Room */
        onLoadUniversities();

        /*List Item click*/
        bindMainActivity.rvUniversitiesList.affectOnItemClicks { position, view ->
            setSelectedItem(position)
            objUniversitiesAdapter.notifyDataSetChanged()
        }

        /*Pull to Refresh */
        bindMainActivity.swipeContainer.setOnRefreshListener {
            onLoadUniversities();
        }

        /*Click on Retry*/
        bindMainActivity.layNetworkLost.btnRetry.setOnClickListener {
            Log.v(UtilConstant.LOG_TAG, "Called from llNetworkFail");

            /*Hit API with Daily*/
            onLoadUniversities();
        }

        // Configure the refreshing colors
        bindMainActivity.swipeContainer.setColorSchemeResources(
            R.color.colorPrimary,
            R.color.colorPrimaryDark,
            R.color.green_retry
        );
    }

    private fun onLoadUniversities() {
        try {
            bindMainActivity.rvUniversitiesList.showShimmerAdapter()

            if (AppUtility.isConnectedToNetwork(this)) {

                /*Hit the API call*/
                universitiesViewModel.initAPI()

                /*Live Data */
                universitiesViewModel.liveDataUniversitiesAPI.observe(this, Observer {
                    isDataFound(it, true)

                })
            } else {
                /*Hide Shimmer animation */
                bindMainActivity.rvUniversitiesList.hideShimmerAdapter()

                /*Live Data */
                universitiesViewModel.getUniversitiesList(this).observe(this, Observer {
                    bindMainActivity.layToolbar.toolbarTitle.setText(
                        getString(
                            R.string.lbl_universities,
                            "Offline-Room Database"
                        )
                    )
                    isDataFound(it, false)
                })
            }
        } catch (e: Exception) {
            /*Hide Shimmer animation */
            bindMainActivity.rvUniversitiesList.hideShimmerAdapter()
            /*Handel the Network UI*/
            networkFailureUI(false)
        }
    }

    private fun isDataFound(list: List<UniversitiesEntity>, isNetworkAvailable: Boolean) {
        try {
            if (list.isNotEmpty()) {
                /*Clear older records */
                objUniversitiesAdapter.clear()

                /*Insert in database */
                if (isNetworkAvailable) {
                    for (row in list) {
                        universitiesViewModel.insertData(this, row.name, row.country, row.code)
                    }
                    bindMainActivity.layToolbar.toolbarTitle.setText(
                        getString(
                            R.string.lbl_universities,
                            "Online-API"
                        )
                    )
                }

                /*Updated with new data */
                mArrUniversitiesList.addAll(list)

                networkFailureUI(true)
            } else {
                if (mArrUniversitiesList.size > 0) {
                    AppUtility.showToast(this, getString(R.string.msg_no_fresh_data_found))
                } else {
                    networkFailureUI(false)
                }
            }
            /*Update swipe state */
            bindMainActivity.swipeContainer.setRefreshing(false);

            /*Hide Shimmer animation once get the API data in list */
            bindMainActivity.rvUniversitiesList.hideShimmerAdapter()

            /*Notify the adapter */
            objUniversitiesAdapter.notifyDataSetChanged()

        } catch (Ex: Exception) {
            Log.e(UtilConstant.LOG_TAG, Ex.toString())
        }
    }

    /*on Click update UI*/
    private fun setSelectedItem(position: Int) {
        for (i in 0 until mArrUniversitiesList.size) {
            if (i == position && mArrUniversitiesList[i].isClicked)
                mArrUniversitiesList[i].isClicked = false
            else mArrUniversitiesList[i].isClicked =
                i == position && !mArrUniversitiesList[i].isClicked
        }
    }


    private fun setupToolbar() {
        setSupportActionBar(bindMainActivity.layToolbar.toolbar)
        bindMainActivity.layToolbar.toolbar.overflowIcon =
            ContextCompat.getDrawable(this, R.drawable.icon_more)
    }

    private fun setupRecyclerView() {
        objUniversitiesAdapter = UniversitiesAdapter(mArrUniversitiesList)
        objUniversitiesAdapter.setHasStableIds(true)
        bindMainActivity.rvUniversitiesList.layoutManager = LinearLayoutManager(this)
        bindMainActivity.rvUniversitiesList.adapter = objUniversitiesAdapter
        bindMainActivity.rvUniversitiesList.itemAnimator = DefaultItemAnimator()
        bindMainActivity.rvUniversitiesList.isNestedScrollingEnabled = true

    }

    private fun networkFailureUI(flag: Boolean) {
        if (flag) {
            bindMainActivity.rvUniversitiesList.visibility = View.VISIBLE
            bindMainActivity.swipeContainer.visibility = View.VISIBLE
            bindMainActivity.layNetworkLost.llNetworkFail.visibility = View.GONE
        } else {
            bindMainActivity.rvUniversitiesList.visibility = View.GONE
            bindMainActivity.swipeContainer.visibility = View.GONE
            bindMainActivity.layNetworkLost.llNetworkFail.visibility = View.VISIBLE
        }
    }

    private fun onSortTheList(byValue: String) {
        if (byValue.equals(getString(R.string.menu_sort_by_country))) {
            mArrUniversitiesList.sortWith(compareBy { it.country })
            objUniversitiesAdapter.notifyDataSetChanged()
        } else {
            mArrUniversitiesList.sortWith(compareBy { it.name })
            objUniversitiesAdapter.notifyDataSetChanged()
        }

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_star -> {
                onSortTheList(getString(R.string.menu_sort_by_country))
                return true
            }
            R.id.menu_name -> {
                onSortTheList(getString(R.string.menu_sort_by_name))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}


