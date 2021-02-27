package alex.richard.rickandmortyquizapp

import android.content.Context

class Question(question: Int, answer: Boolean) {
    val question = question;
    val answer = answer;
    var attempted = false;
    var correct = false;
}
