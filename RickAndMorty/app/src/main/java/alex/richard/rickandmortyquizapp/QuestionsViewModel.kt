package alex.richard.rickandmortyquizapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuestionsViewModel : ViewModel() {
    private val questionBank = mutableListOf(
    Question(R.string.question_1, false),
    Question(R.string.question_2, true),
    Question(R.string.question_3, true),
    Question(R.string.question_4, false),
    Question(R.string.question_5, false),
    Question(R.string.question_6, true),
    Question(R.string.question_7, false),
    Question(R.string.question_8, true),
    Question(R.string.question_9, false),
    Question(R.string.question_10, false),
    Question(R.string.question_11, false),
    Question(R.string.question_12, true),
    Question(R.string.question_13, false),
    Question(R.string.question_14, true),
    Question(R.string.question_15, false),
    Question(R.string.question_16, false),
    Question(R.string.question_17, true),
    Question(R.string.question_18, false),
    Question(R.string.question_19, false),
    Question(R.string.question_20, true)
    )

    private var answeredCorrect = 0
    private var attempted = 0
    private var questionIndex = 0

    private var _score = MutableLiveData<String>()
    val score: LiveData<String>
        get() = _score

    private var _question = MutableLiveData<Int>()
    val question: LiveData<Int>
     get() =  _question
}