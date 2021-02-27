package alex.richard.rickandmortyquizapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuestionsViewModel : ViewModel() {
    private val _questions = mutableListOf(
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

    private lateinit var _questionBank: MutableList<Question>

    private var answeredCorrect = 0
    private var attempted = 0
    private var questionCount = 0
    private var questionIndex: Int = 0


    private var _score = MutableLiveData<String>("0/0")
    val score: LiveData<String>
        get() = _score

    private var _question = MutableLiveData<Int>()
    val question: LiveData<Int>
        get() = _question

    private var _buttonsEnabled = MutableLiveData<Boolean>(true)
    val buttonsEnabled: LiveData<Boolean>
        get() = _buttonsEnabled

    private var _answeredCorrectly = MutableLiveData<Boolean?>()
    val answeredCorrectly: LiveData<Boolean?>
        get() = _answeredCorrectly

    private var _gameFinished = MutableLiveData<Boolean>(false)
    val gameFinished: LiveData<Boolean>
        get() = _gameFinished

    init {
        resetQuestions()
        updateQuestion()
    }

    fun answerQuestion(userAnswer: Boolean) {
        attempted++
        _questionBank[questionIndex].attempted = true
        val isCorrect = _questionBank[questionIndex].answer == userAnswer
        _questionBank[questionIndex].correct = isCorrect
        if (isCorrect) {
            answeredCorrect++
        }
        updateQuestion()
    }

    fun getCorrectAnswer(): Boolean {
        return _questionBank[questionIndex].answer
    }

    private fun resetQuestions() {
        _questionBank = _questions
        questionCount = _questionBank.count()
        _questionBank.shuffle()
    }

    fun nextQuestion() {
        if (questionIndex != questionCount - 1) {
            questionIndex++
        } else {
            questionIndex = 0
        }
        updateQuestion()
    }

    fun prevQuestion() {
        if (questionIndex != 0) {
            questionIndex--
        } else {
            questionIndex = questionCount - 1
        }
        updateQuestion()
    }

    private fun updateQuestion() {
        val question = _questionBank[questionIndex]
        _score.value = "Score: ${answeredCorrect}/${attempted}"
        _question.value = question.question
        _buttonsEnabled.value = !question.attempted

        if (question.attempted) {
            _answeredCorrectly.value = question.correct
        } else {
            _answeredCorrectly.value = null
        }

        if(attempted == _questionBank.count()) {
            _gameFinished.value = true
        }

    }
}