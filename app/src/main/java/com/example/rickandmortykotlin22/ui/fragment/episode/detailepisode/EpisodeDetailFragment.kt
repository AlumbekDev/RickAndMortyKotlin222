package com.example.rickandmortykotlin22.ui.fragment.episode.detailepisode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import kotlinx.coroutines.flow.collect
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.rickandmortykotlin22.databinding.FragmentEpisodeDetailBinding
import com.example.rickandmortykotlin22.keeper.resource.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EpisodeDetailFragment : Fragment() {
    private val viewModel: EpisodeDetailViewModel by viewModels()
    private lateinit var binding: FragmentEpisodeDetailBinding
    private val args: EpisodeDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentEpisodeDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupObservers()
    }

    private fun initialize() {
        viewModel.fetchEpisode(args.id)
    }

    private fun setupObservers() = with(binding) {
        lifecycleScope.launch {
            viewModel.episodeState.collect {
                when (it) {
                    is Resource.Error -> {
                        Toast.makeText(requireActivity(), it.massage, Toast.LENGTH_SHORT).show()
                    }
                    is Resource.Loading -> {

                    }
                    is Resource.Success -> {
                        it.data?.let { data ->
                            name.text = data.name
                            airDate.text = data.air_date
                            episode.text = data.episode
                            url.text = data.url
                            created.text = data.created
                        }
                    }
                }
            }
        }
    }
}