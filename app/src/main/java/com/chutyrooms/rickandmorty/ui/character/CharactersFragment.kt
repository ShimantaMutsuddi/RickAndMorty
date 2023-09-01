package com.chutyrooms.rickandmorty.ui.character

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.chutyrooms.rickandmorty.R
import com.chutyrooms.rickandmorty.databinding.FragmentCharactersBinding
import com.chutyrooms.rickandmorty.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment : Fragment(),CharactersAdapter.CharacterItemListener {

    private var _binding: FragmentCharactersBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CharactersViewModel by viewModels()
    private lateinit var adapter: CharactersAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCharactersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()



    }
    private fun setupRecyclerView() {
        adapter = CharactersAdapter(this)
        binding.charactersRv.layoutManager = LinearLayoutManager(requireContext())
        binding.charactersRv.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.characters.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    if (!it.data.isNullOrEmpty()) adapter.setItems(ArrayList(it.data))
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    binding.progressBar.visibility = View.VISIBLE
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClickedCharacter(characterId: Int) {
        val action=CharactersFragmentDirections.actionCharactersFragmentToCharacterDetailFragment(characterId)
        Navigation.findNavController(binding.root).navigate(action)


    }


}