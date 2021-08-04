package jp.gr.java_conf.bunooboi.memorysportssmart

import java.lang.StringBuilder
import kotlin.random.Random

class Main {
    companion object {
        var autoTime: Double = 1.0
        var playTime: Int = 0
        var ansTime: Int = 0
        var operationStyle: String = ""

        var pairCard: Int = 0
        var pairNum: Int = 0

        var Card = MutableList(52) { it }
        var Number = ""

        fun initCard() {
            Card.shuffle()
        }

        fun initNumber() {
            val sb = StringBuilder()
            for (i in 0..99) {
                val randomInt = Random.nextInt(10)
                sb.append(randomInt)
            }
            Number = sb.toString()
        }
    }
}