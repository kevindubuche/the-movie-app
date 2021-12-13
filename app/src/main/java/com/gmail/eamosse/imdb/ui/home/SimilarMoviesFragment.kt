package com.gmail.eamosse.imdb.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.gmail.eamosse.imdb.databinding.FragmentHomeSecondBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class SimilarMoviesFragment : Fragment() {
        val args: HomeSecondFragmentArgs by navArgs()
        private val homeViewModel: HomeViewModel by viewModel()
        private lateinit var binding: FragmentHomeSecondBinding

        var page: Int = 1
        var scrolled = 0;

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            binding = FragmentHomeSecondBinding.inflate(inflater, container, false)
            return binding.root
        }
}