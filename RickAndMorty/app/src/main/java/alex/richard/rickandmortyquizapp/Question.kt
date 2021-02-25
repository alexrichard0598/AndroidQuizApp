package alex.richard.rickandmortyquizapp

import android.content.Context

class Question(question: Int, answer: Boolean) {
    private val _question = question;
    private val _answer = answer;

    fun CheckQuestion(userAnswer: Boolean): Boolean {
        return userAnswer == _answer;
    }

    fun GetQuestion(context: Context): String {
        return context.getString(_question);
    }
}
