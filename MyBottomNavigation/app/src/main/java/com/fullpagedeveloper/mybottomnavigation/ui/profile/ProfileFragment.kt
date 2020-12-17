package com.fullpagedeveloper.mybottomnavigation.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.fullpagedeveloper.mybottomnavigation.R

class ProfileFragment : Fragment() {

    private lateinit var profileViewModel: ProfileVIewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        profileViewModel = ViewModelProviders.of(this).get(ProfileVIewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_profile, container, false)
        val text: TextView = root.findViewById(R.id.tv_Profile)
        profileViewModel.text.observe(viewLifecycleOwner, {
            text.text = it
        })

        return root
    }
}