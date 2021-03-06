package com.example.rickandmortykotlin22.ui.fragment.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.rickandmortykotlin22.R
import com.example.rickandmortykotlin22.databinding.FragmentCharacterBinding
import com.example.rickandmortykotlin22.keeper.base.BaseFragment
import com.example.rickandmortykotlin22.ui.adapter.CharacterAdapter
import com.example.rickandmortykotlin22.ui.adapter.paging.LoadStateAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class CharacterFragment :
    BaseFragment<CharacterViewModel, FragmentCharacterBinding>(R.layout.fragment_character) {

    private val viewModel: CharacterViewModel by viewModel()
    private lateinit var binding: FragmentCharacterBinding
    private val characterAdapter =
        CharacterAdapter(this::setOnItemClickListener, this ::setOnLongClickListener)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initialize() {
        setupCharacterRecycler()

    }

    private fun setupCharacterRecycler() = with(binding) {
        recyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.adapter = characterAdapter.withLoadStateFooter(LoadStateAdapter {
            characterAdapter.retry()
        })

        characterAdapter.addLoadStateListener { loadStates ->
            recyclerView.isVisible = loadStates.refresh is LoadState.NotLoading
            progressBar.isVisible = loadStates.refresh is LoadState.Loading
            characterSwipeRefreshLayout.isRefreshing = loadStates.refresh is LoadState.Loading
        }
    }

    override fun setupRequest() {
        lifecycleScope.launch {
            viewModel.fetchCharacters().collectLatest { data ->
                characterAdapter.submitData(data)
            }
        }
        Toast.makeText(requireContext(), "Character", Toast.LENGTH_LONG).show()
    }

    private fun setOnItemClickListener(id: Int) {
        findNavController().navigate(
            CharacterFragmentDirections
                .actionCharacterFragmentToCharacterDetailFragment(id)
        )
    }

    private fun setOnLongClickListener(image: String) {
        findNavController().navigate(
            CharacterFragmentDirections.actionCharacterFragmentToMyDialogFragment(image)
        )
    }

    override fun swipeRefresh() {
        binding.characterSwipeRefreshLayout.setOnRefreshListener { characterAdapter.refresh() }
    }
}