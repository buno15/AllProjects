package jp.gr.java_conf.bunooboi.memorysportssmart

import java.lang.StringBuilder
import kotlin.random.Random

class Main {
    companion object {
        var autoTime: Double = 1.0
        var playTime: Int = 0
        var ansTime: Int = 0

        var competitionType: Int = -1
        var operationType: Int = -1
        var methodType: Int = -1

        var pairCard: Int = 3
        var pairNum: Int = 4

        var Card = MutableList(52) { it }
        var Number = ""

        const val TYPE_CARD = 1
        const val TYPE_NUMBER = 2
        const val TYPE_WORD = 3

        const val TYPE_AUTO = 1
        const val TYPE_MANUAL = 2

        const val TYPE_MEMORY = 1
        const val TYPE_TRAINING = 2

        const val LEFT = 1
        const val RIGHT = 2

        const val TWO = 1
        const val THREE = 2
        const val FOUR = 3
        const val TWOTWO = 4
        const val THREETHREE = 5


        val cardData: MutableList<Card> = mutableListOf()

        fun init() {
            cardData.add(Card("d1", "♦1", R.mipmap.d1))
            cardData.add(Card("d2", "♦2", R.mipmap.d2))
            cardData.add(Card("d3", "♦3", R.mipmap.d3))
            cardData.add(Card("d4", "♦4", R.mipmap.d4))
            cardData.add(Card("d5", "♦5", R.mipmap.d5))
            cardData.add(Card("d6", "♦6", R.mipmap.d6))
            cardData.add(Card("d7", "♦7", R.mipmap.d7))
            cardData.add(Card("d8", "♦8", R.mipmap.d8))
            cardData.add(Card("d9", "♦9", R.mipmap.d9))
            cardData.add(Card("d10", "♦10", R.mipmap.d10))
            cardData.add(Card("d11", "♦11", R.mipmap.d11))
            cardData.add(Card("d12", "♦12", R.mipmap.d12))
            cardData.add(Card("d13", "♦13", R.mipmap.d13))
            cardData.add(Card("c1", "♣1", R.mipmap.c1))
            cardData.add(Card("c2", "♣2", R.mipmap.c2))
            cardData.add(Card("c3", "♣3", R.mipmap.c3))
            cardData.add(Card("c4", "♣4", R.mipmap.c4))
            cardData.add(Card("c5", "♣5", R.mipmap.c5))
            cardData.add(Card("c6", "♣6", R.mipmap.c6))
            cardData.add(Card("c7", "♣7", R.mipmap.c7))
            cardData.add(Card("c8", "♣8", R.mipmap.c8))
            cardData.add(Card("c9", "♣9", R.mipmap.c9))
            cardData.add(Card("c10", "♣10", R.mipmap.c10))
            cardData.add(Card("c11", "♣11", R.mipmap.c11))
            cardData.add(Card("c12", "♣12", R.mipmap.c12))
            cardData.add(Card("c13", "♣13", R.mipmap.c13))
            cardData.add(Card("s1", "♠1", R.mipmap.s1))
            cardData.add(Card("s2", "♠2", R.mipmap.s2))
            cardData.add(Card("s3", "♠3", R.mipmap.s3))
            cardData.add(Card("s4", "♠4", R.mipmap.s4))
            cardData.add(Card("s5", "♠5", R.mipmap.s5))
            cardData.add(Card("s6", "♠6", R.mipmap.s6))
            cardData.add(Card("s7", "♠7", R.mipmap.s7))
            cardData.add(Card("s8", "♠8", R.mipmap.s8))
            cardData.add(Card("s9", "♠9", R.mipmap.s9))
            cardData.add(Card("s10", "♠10", R.mipmap.s10))
            cardData.add(Card("s11", "♠11", R.mipmap.s11))
            cardData.add(Card("s12", "♠12", R.mipmap.s12))
            cardData.add(Card("s13", "♠13", R.mipmap.s13))
            cardData.add(Card("h1", "♥1", R.mipmap.h1))
            cardData.add(Card("h2", "♥2", R.mipmap.h2))
            cardData.add(Card("h3", "♥3", R.mipmap.h3))
            cardData.add(Card("h4", "♥4", R.mipmap.h4))
            cardData.add(Card("h5", "♥5", R.mipmap.h5))
            cardData.add(Card("h6", "♥6", R.mipmap.h6))
            cardData.add(Card("h7", "♥7", R.mipmap.h7))
            cardData.add(Card("h8", "♥8", R.mipmap.h8))
            cardData.add(Card("h9", "♥9", R.mipmap.h9))
            cardData.add(Card("h10", "♥10", R.mipmap.h10))
            cardData.add(Card("h11", "♥11", R.mipmap.h11))
            cardData.add(Card("h12", "♥12", R.mipmap.h12))
            cardData.add(Card("h13", "♥13", R.mipmap.h13))

        }

        fun initCard() {
            Card.shuffle()
        }

        fun initNumber() {
            val sb = StringBuilder()
            for (i in 0..100) {
                val randomInt = Random.nextInt(10)
                sb.append(randomInt)
            }
            Number = sb.toString()
        }
    }
}

data class Card(val name: String, val text: String, val img: Int)