package com.example.lambdaspectrogram

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.lambdaspectrogram.databinding.FragmentResultBinding

class ResultFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentResultBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_result,
            container,
            false
        )

        val args = ResultFragmentArgs.fromBundle(arguments!!)
        binding.scoreTextView.setText("Score: ${args.score} / 10")

        binding.playAgainButton.setOnClickListener { view ->
            Navigation.findNavController(view).navigate(R.id.action_resultFragment_to_gameFragment)
        }

        return binding.root
    }

}