package com.chutyrooms.rickandmorty.ui.features.characterdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import coil.load
import com.chutyrooms.rickandmorty.databinding.FragmentCharacterDetailBinding
import com.example.rickandmorty.data.entities.Character
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CharacterDetailFragment : Fragment() {

    private var _binding: FragmentCharacterDetailBinding? = null
    private val binding get() = _binding!!
    val args: CharacterDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //arguments?.getInt("id", 0) ?: 0.let { viewModel.start(it) }
        args.character?.let { bindCharacter(it) }
        // setupObservers()
    }


    private fun bindCharacter(character: Character) {
        binding.name.text = character.name
        binding.species.text = character.species
        binding.status.text = character.status
        binding.gender.text = character.gender
        binding.origin.text = character.origin.name.toString()
        binding.location.text = character.location.name.toString()
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