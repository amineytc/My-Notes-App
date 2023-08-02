package com.amineaytac.mynotesapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.amineaytac.mynotesapp.R
import com.amineaytac.mynotesapp.ui.viewmodel.NotesViewModel
import com.amineaytac.mynotesapp.databinding.FragmentHomeBinding
import com.amineaytac.mynotesapp.ui.adapter.NotesAdapter


class HomeFragment : Fragment() {
    lateinit var binding : FragmentHomeBinding
    val viewModel : NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        

        viewModel.getNotes().observe(viewLifecycleOwner) { noteList ->
            binding.recycleAllNotesId.layoutManager = GridLayoutManager(requireContext(), 2)
            binding.recycleAllNotesId.adapter = NotesAdapter(requireContext(), noteList)
        }


        binding.allNotes.setOnClickListener {
            viewModel.getNotes().observe(viewLifecycleOwner) { noteList ->
                binding.recycleAllNotesId.layoutManager = GridLayoutManager(requireContext(), 2)
                binding.recycleAllNotesId.adapter = NotesAdapter(requireContext(), noteList)
            }
        }

        binding.filterHigh.setOnClickListener {
            viewModel.getHighNotes().observe(viewLifecycleOwner) { noteList ->
                binding.recycleAllNotesId.layoutManager = GridLayoutManager(requireContext(), 2)
                binding.recycleAllNotesId.adapter = NotesAdapter(requireContext(), noteList)
            }
        }
        binding.filterMedium.setOnClickListener {
            viewModel.getMediumNotes().observe(viewLifecycleOwner) { noteList ->
                binding.recycleAllNotesId.layoutManager = GridLayoutManager(requireContext(), 2)
                binding.recycleAllNotesId.adapter = NotesAdapter(requireContext(), noteList)
            }
        }
        binding.filterLow.setOnClickListener {
            viewModel.getLowNotes().observe(viewLifecycleOwner) { noteList ->
                binding.recycleAllNotesId.layoutManager = GridLayoutManager(requireContext(), 2)
                binding.recycleAllNotesId.adapter = NotesAdapter(requireContext(), noteList)
            }
        }


        binding.btnAddNotesId.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_createNoteFragment)
        }

        return binding.root
    }


}