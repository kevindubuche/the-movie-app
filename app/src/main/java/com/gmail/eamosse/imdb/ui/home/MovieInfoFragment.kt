package com.gmail.eamosse.imdb.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import com.gmail.eamosse.imdb.R
import com.gmail.eamosse.imdb.databinding.FragmentMovieInfoBinding
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_movie_info.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieInfoFragment : Fragment() {
    val args: MovieInfoFragmentArgs by navArgs()
    private val homeViewModel: HomeViewModel by viewModel()
    private lateinit var binding: FragmentMovieInfoBinding


    private lateinit var imagePreview : ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imagePreview = view.findViewById(R.id.moviePoster)
        with(homeViewModel) {
            token.observe(
                viewLifecycleOwner,
                Observer {
                    getMovie(id = args.movieId.toInt())
                    movieFavorite.setOnClickListener(View.OnClickListener {
                    })
                }
            )

            movie.observe(
                viewLifecycleOwner,
                Observer {
                    binding.item = it;
                    Picasso.get().load("https://image.tmdb.org/t/p/w500${it.poster_path}")
                        .error(R.drawable.ic_launcher_background)
                        .into(imagePreview)
                }
            )

        }
    }
}

