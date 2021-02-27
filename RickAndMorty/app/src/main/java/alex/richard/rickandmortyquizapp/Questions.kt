package alex.richard.rickandmortyquizapp

import alex.richard.rickandmortyquizapp.databinding.FragmentQuestionsBinding
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
                    questionsScore.text = newScore
                }
            )

            viewModel.answeredCorrectly.observe(
                viewLifecycleOwner,
                { newAnsweredCorrectly ->
                    if (newAnsweredCorrectly == null) {
                        questionsCorrectImage.setImageResource(0)
                        questionsTrue.isChecked = false
                        questionsFalse.isChecked = false
                    } else if (newAnsweredCorrectly) {
                        questionsCorrectImage.setImageResource(R.drawable.right_24)
                        if (viewModel.getCorrectAnswer()) {
                            questionsTrue.isChecked = true
                            questionsFalse.isChecked = false
                        } else {
                            questionsTrue.isChecked = false
                            questionsFalse.isChecked = true
                        }
                    } else {
                        questionsCorrectImage.setImageResource(R.drawable.wrong_24)
                        if (viewModel.getCorrectAnswer()) {
                            questionsTrue.isChecked = false
                            questionsFalse.isChecked = true
                        } else {
                            questionsTrue.isChecked = true
                            questionsFalse.isChecked = false
                        }
                    }
                }
            )

            viewModel.gameFinished.observe(viewLifecycleOwner, {
                gameFinished ->
                if(gameFinished) {
                    findNavController().navigate(QuestionsDirections.actionQuestionsToEndScreen())
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