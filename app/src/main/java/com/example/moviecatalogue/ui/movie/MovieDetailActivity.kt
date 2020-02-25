package com.example.moviecatalogue.ui.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.moviecatalogue.R
import com.example.moviecatalogue.model.MovieDetail
import com.example.moviecatalogue.utils.Constant
import com.example.moviecatalogue.viewmodel.MovieDetailViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }

    private lateinit var viewModel: MovieDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val movieId = intent.getStringExtra(EXTRA_MOVIE)

        supportActionBar?.title = "Movie Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        showLoading(true)
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(MovieDetailViewModel::class.java)

        viewModel.setData(movieId).observe(this, Observer { t ->
            t.let {
                if(it != null ){
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
            sm_detail.startShimmer()
            sm_detail.visibility = View.VISIBLE
        } else {
            sm_detail.stopShimmer()
            sm_detail.visibility = View.GONE
        }
    }

    private fun init(data: MovieDetail) {
        supportActionBar?.title = data.title
        Picasso.get().load(Constant.URL_IMG + "w342"+data.backdrop).placeholder(R.drawable.play).into(img_m_backdrop)
        Picasso.get().load(Constant.URL_IMG + "w92"+data.poster).placeholder(R.drawable.play).into(img_m_poster)
        tv_m_title.text = data.title
        tv_m_date.text = data.date
        tv_m_score.text = data.score
        tv_m_tag_line.text = data.tagLine
        tv_m_overview.text = data.overview
    }
}
