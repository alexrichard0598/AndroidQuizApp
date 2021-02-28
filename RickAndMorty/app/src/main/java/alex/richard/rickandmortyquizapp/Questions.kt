package alex.richard.rickandmortyquizapp

import alex.richard.rickandmortyquizapp.databinding.FragmentQuestionsBinding
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kotlin.time.nanoseconds


class Questions : Fragment() {

    private lateinit var viewModel: QuestionsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(this).get(QuestionsViewModel::class.java)

        val binding = DataBindingUtil.inflate<FragmentQuestionsBinding>(
            inflater,
            R.layout.fragment_questions,
            container,
            false
        )

        binding.apply {
            viewModel.question.observe(
                viewLifecycleOwner,
                { newQuestion ->
                    questionsQuestion.text = context?.getString(newQuestion) ?: ""
                }
            )

            viewModel.score.observe(
                viewLifecycleOwner,
                { newScore ->
                    questionsScore.text = getString(R.string.current_score_text) + newScore
                    (activity as AppCompatActivity).supportActionBar?.title =
                        getString(R.string.app_name) + " - (${newScore})"
                }
            )

            viewModel.answeredCorrectly.observe(
                viewLifecycleOwner,
                { newAnsweredCorrectly ->
                    if (newAnsweredCorrectly != null) {
                        if (newAnsweredCorrectly) {
                            questionsCorrectImage.setImageResource(R.drawable.right_24)
                        } else {
                            questionsCorrectImage.setImageResource(R.drawable.wrong_24)
                        }
                    } else {
                        questionsCorrectImage.setImageResource(0)
                    }

                    val radioButtonsState = viewModel.getRadioState()
                    questionsTrue.isChecked = radioButtonsState.first
                    questionsFalse.isChecked = radioButtonsState.second
                }
            )

            viewModel.gameFinished.observe(viewLifecycleOwner, { gameFinished ->
                if (gameFinished) {
                    findNavController().navigate(
                        QuestionsDirections.actionQuestionsToEndScreen(
                            viewModel.score.value.toString()
                        )
                    )
                }
            })


            viewModel.buttonsEnabled.observe(
                viewLifecycleOwner,
                { newEnabled ->
                    questionsTrue.isEnabled = newEnabled
                    questionsFalse.isEnabled = newEnabled
                }
            )

            btnNext.setOnClickListener {
                viewModel.nextQuestion()
            }

            btnPrev.setOnClickListener {
                viewModel.prevQuestion()
            }

            questionsTrue.setOnClickListener {
                viewModel.answerQuestion(true)
            }

            questionsFalse.setOnClickListener {
                viewModel.answerQuestion(false)
            }
        }

        return binding.root
    }

}