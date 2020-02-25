package com.example.moviecatalogue.ui.tvshow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalogue.R
import com.example.moviecatalogue.adapter.SeasonAdapter
import com.example.moviecatalogue.model.Season
import com.example.moviecatalogue.model.TVShowDetail
import com.example.moviecatalogue.utils.Constant
import com.example.moviecatalogue.viewmodel.TvDetailViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_tv_show_detail.*

class TvShowDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_TV_SHOW = "extra_tv_show"
    }
    private lateinit var viewmodel: TvDetailViewModel
    private lateinit var adapter: SeasonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tv_show_detail)

        showLoading(true)
        val tvId = intent.getStringExtra(EXTRA_TV_SHOW)

        supportActionBar?.title = "TV Show Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewmodel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(TvDetailViewModel::class.java)

        viewmodel.setData(tvId).observe(this, Observer {t->
            t.let {
                if(it != null ) {
                    showLoading(false)
                    init(it)
                }
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    private fun showLoading(loading: Boolean) {
        if (loading) {
            sm_detail_tv.startShimmer()
            sm_detail_tv.visibility = View.VISIBLE
        } else {
            sm_detail_tv.stopShimmer()
            sm_detail_tv.visibility = View.GONE
        }
    }

    private fun init(data: TVShowDetail) {
        supportActionBar?.title = data.name
        Picasso.get().load(Constant.URL_IMG + "w342"+data.backdrop).placeholder(R.drawable.play).into(img_tv_backdrop)
        Picasso.get().load(Constant.URL_IMG + "w92"+data.poster).placeholder(R.drawable.play).into(img_tv_poster)
        tv_tv_date.text = data.date
        tv_tv_overview.text = data.overview
        tv_tv_score.text = data.score
        tv_tv_title.text = data.name
        rv_season.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        adapter = SeasonAdapter(data.season as ArrayList<Season>)
        rv_season.adapter = adapter
        adapter.notifyDataSetChanged()
    }
}
