package com.chutyrooms.rickandmorty.ui.characterdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import coil.load
import com.chutyrooms.rickandmorty.databinding.FragmentCharacterDetailBinding
import com.chutyrooms.rickandmorty.databinding.FragmentCharactersBinding
import com.chutyrooms.rickandmorty.utils.Resource
import com.example.rickandmorty.data.entities.Character
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CharacterDetailFragment : Fragment() {

    private var _binding: FragmentCharacterDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CharacterDetailViewModel by viewModels()
    val args: CharacterDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //arguments?.getInt("id", 0) ?: 0.let { viewModel.start(it) }
        args.characterId?.let { viewModel.start(it) }
        setupObservers()
    }
    private fun setupObservers() {
        viewModel.character.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    bindCharacter(it.data!!)
                    binding.progressBar.visibility = View.GONE
                    binding.characterCl.visibility = View.VISIBLE
                }

                Resource.Status.ERROR ->
                    Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.characterCl.visibility = View.GONE
                }
            }
        })
    }

    private fun bindCharacter(character: Character) {
        binding.name.text = character.name
        binding.species.text = character.species
        binding.status.text = character.status
        binding.gender.text = character.gender
        binding.image.load(character.image) {
            crossfade(true)
            crossfade(1000)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}