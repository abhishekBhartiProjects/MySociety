package io.github.abhishekbhartiprojects.mysociety.feature.android.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.github.abhishekbhartiprojects.mysociety.feature.R

class HomeEventFragment: Fragment() {

    companion object {
        fun newInstance(bundle: Bundle?) = HomeEventFragment().apply { arguments = bundle }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.home_event_fragment, container, false)

        return view
    }
}