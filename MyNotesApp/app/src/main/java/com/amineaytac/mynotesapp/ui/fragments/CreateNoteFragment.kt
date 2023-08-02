package com.amineaytac.mynotesapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.amineaytac.mynotesapp.Model.Notes
import com.amineaytac.mynotesapp.R
import com.amineaytac.mynotesapp.ui.viewmodel.NotesViewModel
import com.amineaytac.mynotesapp.databinding.FragmentCreateNoteBinding
import java.text.SimpleDateFormat
import java.util.*

class CreateNoteFragment : Fragment() {

    lateinit var binding : FragmentCreateNoteBinding
    var priority : String="1"
    val viewModel : NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCreateNoteBinding.inflate(layoutInflater,container,false)
        // binding.dGreenId.setImageResource(R.drawable.doneicon)

        binding.dGreenId.setOnClickListener{
            priority="1"
            binding.dGreenId.setImageResource(R.drawable.doneicon)
            binding.dRedId.setImageResource(0)
            binding.dYellowId.setImageResource(0)
        }
        binding.dYellowId.setOnClickListener{
            priority="2"
            binding.dYellowId.setImageResource(R.drawable.doneicon)
            binding.dRedId.setImageResource(0)
            binding.dGreenId.setImageResource(0)
        }
        binding.dRedId.setOnClickListener {
            priority="3"
            binding.dRedId.setImageResource(R.drawable.doneicon)
            binding.dGreenId.setImageResource(0)
            binding.dYellowId.setImageResource(0)
        }

        binding.btnSaveNote.setOnClickListener {
            createNotes(it)
        }




        return binding.root
    }

    private fun createNotes(it: View?) {
        val title = binding.edtTxtTitleId.text.toString()
        val subtitle = binding.edtTextSubtitleId.text.toString()
        val notes = binding.edtTextNotesId.text.toString()

        val pattern = "yyyy-MM-dd"
        val simpleDateFormat = SimpleDateFormat(pattern)
        val notesDate = simpleDateFormat.format(Date())

        val data= Notes(null,title=title,
            notes=notes,
            date=notesDate.toString(),
            subTitle=subtitle,
            priority)
        viewModel.addNotes(data)

        Toast.makeText(requireContext(),"Notes Created Succesfully", Toast.LENGTH_LONG).show()
        Navigation.findNavController(it!!).navigate(R.id.action_createNoteFragment_to_homeFragment)



    }


}