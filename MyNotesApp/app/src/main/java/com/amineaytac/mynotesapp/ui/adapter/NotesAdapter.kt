package com.amineaytac.mynotesapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.amineaytac.mynotesapp.Model.Notes
import com.amineaytac.mynotesapp.R
import com.amineaytac.mynotesapp.databinding.ItemNotesBinding
import com.amineaytac.mynotesapp.ui.fragments.HomeFragmentDirections

class NotesAdapter(val requireContext: Context, val noteList: List<Notes>)
    : RecyclerView.Adapter<NotesAdapter.notesViewHolder>() {

    class notesViewHolder(val binding : ItemNotesBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): notesViewHolder {
        return notesViewHolder(
            ItemNotesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false))
    }

    override fun getItemCount() = noteList.size

    override fun onBindViewHolder(holder: notesViewHolder, position: Int) {
        val data = noteList[position]
        holder.binding.notesItemDateId.text=data.date
        holder.binding.notesItemSubtitleId.text=data.subTitle
        holder.binding.notesItemTitleId.text=data.title

        when(data.priority){
            "1"->{
                holder.binding.viewItemPriorityId.setBackgroundResource(R.drawable.green_dot)
            }
            "2"->{
                holder.binding.viewItemPriorityId.setBackgroundResource(R.drawable.yellow_dot)
            }
            "3"->{
                holder.binding.viewItemPriorityId.setBackgroundResource(R.drawable.red_dot)
            }
        }

        holder.binding.root.setOnClickListener{

            val action = HomeFragmentDirections.actionHomeFragmentToEditNoteFragment(data)
            Navigation.findNavController(it).navigate(action)
        }
    }
}