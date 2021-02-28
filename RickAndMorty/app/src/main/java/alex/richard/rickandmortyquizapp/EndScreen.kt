package alex.richard.rickandmortyquizapp

import alex.richard.rickandmortyquizapp.databinding.FragmentEndScreenBinding
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil


class EndScreen : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentEndScreenBinding>(
            inflater,
            R.layout.fragment_end_screen,
            container,
            false
        )

        binding.apply {
            endScreenResults.text =
                getString(R.string.scored_text) + arguments?.get("scoreString").toString()
        }

        (activity as AppCompatActivity).supportActionBar?.title =
            getString(R.string.game_over_text)

        return binding.root
    }
}