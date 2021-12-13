package com.gmail.eamosse.imdb.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.gmail.eamosse.imdb.databinding.FragmentHomeSecondBinding
import kotlinx.android.synthetic.main.fragment_home_second.*

import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeSecondFragment : Fragment() {
    val args: HomeSecondFragmentArgs by navArgs()
    private val homeViewModel: HomeViewModel by viewModel()
    private lateinit var binding: FragmentHomeSecondBinding

    companion object {
        const val ARG_GENRE = "genre"
    }

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(homeViewModel) {
            token.observe(
                viewLifecycleOwner,
                Observer {
                    getMoc(id = args.myArg.toInt(), page)

                    mocList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                        override fun onScrollStateChanged(
                            recyclerView: RecyclerView,
                            newState: Int
                        ) {
                            super.onScrollStateChanged(recyclerView, newState);
                            if (!recyclerView.canScrollVertically(1)) {
                                val thread = Thread {
                                    Thread.sleep(1_000)
                                    binding.scrollview.smoothScrollBy(0, scrolled);
                                }.start();
                                loadMoreData(id = args.myArg.toInt(), page++)
                            }
                        }

                        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                            super.onScrolled(recyclerView, dx, dy)
                            scrolled += dy;
                        }
                    })
                }
            )

            moc.observe(
                viewLifecycleOwner,
                Observer {
                    binding.mocList.adapter = MovieOfACategoryAdapter(it) {
                        val action = HomeSecondFragmentDirections.actionHomeSecondFragmentToMovieAboutFragment(it.id.toString());
                        NavHostFragment.findNavController(this@HomeSecondFragment).navigate(action)
                    }

                }
            )


        }
    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        with(homeViewModel) {
//
//        }
//    }

}

