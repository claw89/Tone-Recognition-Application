package com.example.lambdaspectrogram

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.example.lambdaspectrogram.databinding.FragmentHomeBinding
import java.io.InputStream

class HomeFragment: Fragment() {
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentHomeBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )

        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        viewModel.loadModules(readJSONFromAsset()!!)

        viewModel.loaded.observe(this, Observer { loaded ->
            if (loaded) {
                binding.playGameButton.isEnabled = true
                binding.playGameButton.isClickable = true
                binding.loadingInfo.isVisible = false
                binding.progressBar.isVisible = false
            }
        })

        binding.playGameButton.setOnClickListener {view ->
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_gameFragment)
        }


        return binding.root
    }

    private fun readJSONFromAsset(): String? {
        var json: String? = null
        try {
            val  inputStream: InputStream = context!!.assets.open("data.json")
            json = inputStream.bufferedReader().use{it.readText()}
        } catch (ex: Exception) {
            ex.printStackTrace()
            return null
        }
        return json
    }
}