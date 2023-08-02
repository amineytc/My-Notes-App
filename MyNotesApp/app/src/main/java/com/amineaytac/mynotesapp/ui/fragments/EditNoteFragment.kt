package com.amineaytac.mynotesapp.ui.fragments

import android.os.Bundle
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.amineaytac.mynotesapp.Model.Notes
import com.amineaytac.mynotesapp.R
import com.amineaytac.mynotesapp.databinding.FragmentEditNoteBinding
import com.amineaytac.mynotesapp.ui.viewmodel.NotesViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.text.SimpleDateFormat
import java.util.*


class EditNoteFragment : Fragment() {

    lateinit var binding  :FragmentEditNoteBinding
    val oldNotes by navArgs<EditNoteFragmentArgs>()
    var priority : String="1"
    val viewModel : NotesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentEditNoteBinding.inflate(layoutInflater,container,false)
        setHasOptionsMenu(true)

        binding.editTitleId.setText(oldNotes.data.title)
        binding.editSubtitleId.setText(oldNotes.data.subTitle)
        binding.editNotesId.setText(oldNotes.data.notes)

        when(oldNotes.data.priority){
            "1"->{
                binding.dGreenImageId.setImageResource(R.drawable.doneicon)
                binding.dRedImageId.setImageResource(0)
                binding.dYellowImageId.setImageResource(0)
            }
            "2"->{
                priority="2"
                binding.dYellowImageId.setImageResource(R.drawable.doneicon)
                binding.dRedImageId.setImageResource(0)
                binding.dGreenImageId.setImageResource(0)
            }
            "3"->{
                priority="3"
                binding.dRedImageId.setImageResource(R.drawable.doneicon)
                binding.dGreenImageId.setImageResource(0)
                binding.dYellowImageId.setImageResource(0)
            }
        }

        binding.dGreenImageId.setOnClickListener{
            priority="1"
            binding.dGreenImageId.setImageResource(R.drawable.doneicon)
            binding.dRedImageId.setImageResource(0)
            binding.dYellowImageId.setImageResource(0)
        }
        binding.dYellowImageId.setOnClickListener{
            priority="2"
            binding.dYellowImageId.setImageResource(R.drawable.doneicon)
            binding.dRedImageId.setImageResource(0)
            binding.dGreenImageId.setImageResource(0)
        }
        binding.dRedImageId.setOnClickListener {
            priority="3"
            binding.dRedImageId.setImageResource(R.drawable.doneicon)
            binding.dGreenImageId.setImageResource(0)
            binding.dYellowImageId.setImageResource(0)
        }

        binding.btnEditNoteId.setOnClickListener {
            updateNotes(it)
        }
        return binding.root
    }

    private fun updateNotes(it: View?) {

        val title = binding.editTitleId.text.toString()
        val subtitle = binding.editSubtitleId.text.toString()
        val notes = binding.editNotesId.text.toString()

        val pattern = "yyyy-MM-dd"
        val simpleDateFormat = SimpleDateFormat(pattern)
        val notesDate = simpleDateFormat.format(Date())

        val data= Notes(
            oldNotes.data.id,
            title=title,
            notes=notes,
            date=notesDate.toString(),
            subTitle=subtitle,
            priority)
        viewModel.updateNotes(data)

        Toast.makeText(
            requireContext(),
            "Notes Updates Succesfully",
            Toast.LENGTH_LONG).show()

        Navigation.findNavController(it!!).navigate(R.id.action_editNoteFragment_to_homeFragment)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId== R.id.menu_delete){

            val bottomSheet : BottomSheetDialog =
            BottomSheetDialog(requireContext(), R.style.BottomSheetStyle)
            bottomSheet.setContentView(R.layout.dialog_delete)

            val textviewYes=bottomSheet.findViewById<TextView>(R.id.dialog_yes)
            val textviewNo=bottomSheet.findViewById<TextView>(R.id.dialog_no)

            textviewNo?.setOnClickListener {
                bottomSheet.dismiss()
            }
            textviewYes?.setOnClickListener {
                viewModel.deleteNotes(oldNotes.data.id!!)
                bottomSheet.dismiss()
                findNavController().navigate(R.id.action_editNoteFragment_to_homeFragment)

            }
            bottomSheet.show()
        }
        return super.onOptionsItemSelected(item)
    }
}