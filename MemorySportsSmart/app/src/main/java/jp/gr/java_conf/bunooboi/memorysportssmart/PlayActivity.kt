package jp.gr.java_conf.bunooboi.memorysportssmart

import android.os.Bundle
import android.os.Handler
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_play.*
import java.util.*


class PlayActivity : AppCompatActivity() {

    var startTimer: Timer? = null
    var playTimer: Timer? = null
    var autoTimer: Timer? = null

    var timeView: TextView? = null
    var cardView: MutableList<ImageView>? = mutableListOf()

    var cardIndex = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)

        timeView = findViewById(R.id.time)


        val mainLayout: LinearLayout = findViewById(R.id.main)
        mainLayout.gravity = Gravity.CENTER

        if (Main.competitionType == Main.TYPE_CARD) {
            for (i in 0 until Main.pairCard) {
                cardView!!.add(ImageView(this))
                cardView!![i].adjustViewBounds = true
                cardView!![i].layoutParams = LinearLayout.LayoutParams(
                    0,
                    LinearLayout.LayoutParams.WRAP_CONTENT, 1F
                )

                val param = cardView!![i].layoutParams as ViewGroup.MarginLayoutParams
                param.setMargins(15, 15, 15, 15)
                cardView!![i].layoutParams = param


                mainLayout.addView(
                    cardView!![i]
                )
            }
        } else if (Main.competitionType == Main.TYPE_NUMBER) {

        }


        val left: Button = findViewById(R.id.left)
        left.text = "<-"
        left.setOnClickListener {
            change(Main.LEFT)
        }

        val right: Button = findViewById(R.id.right)
        right.text = "->"
        right.setOnClickListener {
            change(Main.RIGHT)
        }

        if (Main.operationType == Main.TYPE_AUTO) {
            left.visibility = View.INVISIBLE
            right.visibility = View.INVISIBLE
        }

        start()
    }

    private fun start() {
        var count = 5

        val handler = Handler()
        startTimer = Timer()
        startTimer!!.schedule(object : TimerTask() {
            override fun run() {
                handler.post {
                    if (count <= 0) {
                        stop(startTimer!!)
                        if (Main.operationType == Main.TYPE_MANUAL)
                            playTime()
                        else if (Main.operationType == Main.TYPE_AUTO) {
                            timeView!!.visibility = View.INVISIBLE
                            autoTime()
                        }
                    }
                    timeView!!.text = count.toString()
                    count--
                }
            }
        }, 1000, 1000)
    }

    private fun stop(timer: Timer) {
        timer?.cancel()
    }

    private fun playTime() {
        val handler = Handler()
        playTimer = Timer()
        playTimer!!.schedule(object : TimerTask() {
            override fun run() {
                handler.post {
                    Main.playTime++

                    val minute = String.format("%02d", (Main.playTime / 60))
                    val second = String.format("%02d", (Main.playTime % 60))

                    timeView!!.text = "$minute:$second"
                }
            }
        }, 0, 1000)
    }

    private fun autoTime() {
        val handler = Handler()
        autoTimer = Timer()
        autoTimer!!.schedule(object : TimerTask() {
            override fun run() {
                handler.post {
                    if (cardIndex >= 52) {
                        timeView!!.visibility = View.VISIBLE
                        timeView!!.text = "Done"
                        stop(autoTimer!!)
                    } else {
                        for (i in 0 until Main.pairCard) {
                            if (cardIndex < 52)
                                cardView!![i].setImageResource(Main.cardData[Main.Card[cardIndex++]].img)
                            else
                                cardView!![i].visibility = View.INVISIBLE
                        }
                    }
                }
            }
        }, 0, (Main.autoTime * 1000).toLong())
    }

    private fun change(dir: Int) {
        if (dir == Main.LEFT) {

        } else if (dir == Main.RIGHT) {

        }
    }
}
