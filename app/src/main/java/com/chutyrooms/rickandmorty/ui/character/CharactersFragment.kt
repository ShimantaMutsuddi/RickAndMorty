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
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.chutyrooms.rickandmorty.R
import com.chutyrooms.rickandmorty.data.paging.CharacterAdapter
import com.chutyrooms.rickandmorty.data.paging.LoaderAdapter
import com.chutyrooms.rickandmorty.databinding.FragmentCharactersBinding
import com.chutyrooms.rickandmorty.utils.Resource
import com.example.rickandmorty.data.entities.Character
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalPagingApi
@AndroidEntryPoint
class CharactersFragment : Fragment(),CharacterAdapter.OnItemClickListener {

    private var _binding: FragmentCharactersBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CharactersViewModel by viewModels()
    private lateinit var adapter: CharacterAdapter


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
        adapter = CharacterAdapter(this)
        setupRecyclerView()
        setupObservers()



    }
    private fun setupRecyclerView() {

        binding.charactersRv.layoutManager = LinearLayoutManager(requireContext())
        binding.charactersRv.adapter = adapter
        //binding.charactersRv.layoutManager=LinearLayoutManager()
        binding.charactersRv.setHasFixedSize(true)
        binding.charactersRv.adapter=adapter.withLoadStateHeaderAndFooter(
            header = LoaderAdapter(),
            footer = LoaderAdapter(),
        )
    }

    private fun setupObservers() {
        viewModel.characters.observe(viewLifecycleOwner, Observer {resource->
            adapter.submitData(lifecycle,resource)

            /*when (resource.status) {
                Resource.Status.SUCCESS -> {
                    val pagingData = resource.data.
                    binding.progressBar.visibility = View.GONE

                    adapter.submitData(lifecycle,pagingData)
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    binding.progressBar.visibility = View.VISIBLE
            }*/
        })


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

   /* override fun onClickedCharacter(characterId: Int) {
        val action=CharactersFragmentDirections.actionCharactersFragmentToCharacterDetailFragment(characterId)
        Navigation.findNavController(binding.root).navigate(action)


    }*/

    override fun onItemClick(character: Character) {

        val action=CharactersFragmentDirections.actionCharactersFragmentToCharacterDetailFragment(character)
        Navigation.findNavController(binding.root).navigate(action)
    }


}