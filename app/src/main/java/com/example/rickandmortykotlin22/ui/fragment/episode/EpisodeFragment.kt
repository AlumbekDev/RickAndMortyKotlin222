package com.example.rickandmortykotlin22.ui.fragment.episode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmortykotlin22.R
import com.example.rickandmortykotlin22.databinding.FragmentEpisodeBinding
import com.example.rickandmortykotlin22.keeper.base.BaseFragment
import com.example.rickandmortykotlin22.ui.adapter.EpisodeAdapter
import com.example.rickandmortykotlin22.ui.adapter.paging.LoadStateAdapter
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlinx.coroutines.launch


class EpisodeFragment :
    BaseFragment<EpisodeViewModel, FragmentEpisodeBinding>(R.layout.fragment_episode) {

    private lateinit var binding: FragmentEpisodeBinding
    private val viewModel: EpisodeViewModel by viewModel()
    private val episodeAdapter = EpisodeAdapter(this::setupListeners)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEpisodeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun initialize() = with(binding) {
        setupEpisodeRecycler()
    }

    private fun setupEpisodeRecycler() = with(binding) {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = episodeAdapter.withLoadStateFooter(LoadStateAdapter {
            episodeAdapter.retry()
        })
        episodeAdapter.addLoadStateListener { loadStates ->
            recyclerView.isVisible = loadStates.refresh is LoadState.NotLoading
            progressBar.isVisible = loadStates.refresh is LoadState.Loading
            swipeRefresh.isRefreshing = loadStates.refresh is LoadState.Loading
        }
    }

    override fun setupRequest() {
        lifecycleScope.launch {
            viewModel.fetchEpisodes().collectLatest {
                episodeAdapter.submitData(it)

            }
        }
        Toast.makeText(requireContext(), "Episode", Toast.LENGTH_LONG).show()
    }

    private fun setupListeners(id: Int) {
        findNavController().navigate(
            EpisodeFragmentDirections.actionEpisodeFragmentToEpisodeDetailFragment(
                id
            )
        )
    }

    override fun swipeRefresh() {
        binding.swipeRefresh.setOnRefreshListener { episodeAdapter.refresh() }
    }
}