package jp.gr.java_conf.bunooboi.memorysportssmart

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.text.method.TextKeyListener.clear
import android.util.TypedValue
import android.view.*
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.TextViewCompat
import java.util.*


class PlayActivity : AppCompatActivity() {

    var startTimer: Timer? = null
    var playTimer: Timer? = null
    var autoTimer: Timer? = null

    var timeView: TextView? = null
    var cardView: MutableList<ImageView>? = mutableListOf()
    var numberView: TextView? = null

    var left: Button? = null
    var right: Button? = null

    var cardIndex = 0
    var numberIndex = 0

    var finish: Boolean = false


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
            numberView = TextView(this)
            numberView!!.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
            numberView!!.gravity = Gravity.CENTER

            val param = numberView!!.layoutParams as ViewGroup.MarginLayoutParams
            param.setMargins(15, 15, 15, 15)
            numberView!!.layoutParams = param
            TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(
                numberView!!, 1, 70, 1, TypedValue.COMPLEX_UNIT_DIP
            )
            mainLayout.addView(numberView)
        }


        left = findViewById(R.id.left)
        left!!.text = "<"
        left!!.setOnClickListener {
            change(Main.LEFT)
        }

        right = findViewById(R.id.right)
        right!!.text = ">"
        right!!.setOnClickListener {
            change(Main.RIGHT)
        }

        if (Main.operationType == Main.TYPE_AUTO) {
            left!!.visibility = View.INVISIBLE
            right!!.visibility = View.INVISIBLE
        } else if (Main.operationType == Main.TYPE_MANUAL) {
            left!!.isEnabled = false
            right!!.isEnabled = false
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

                        if (Main.operationType == Main.TYPE_MANUAL) {
                            right!!.isEnabled = true
                        }

                        if (Main.operationType == Main.TYPE_MANUAL) {
                            playTime()
                            if (Main.competitionType == Main.TYPE_CARD) {
                                for (i in 0 until Main.pairCard) {
                                    if (cardIndex < 52)
                                        cardView!![i].setImageResource(Main.cardData[Main.Card[cardIndex++]].img)
                                    else
                                        cardView!![i].visibility = View.INVISIBLE
                                }
                            } else if (Main.competitionType == Main.TYPE_NUMBER) {
                                when (Main.pairNum) {
                                    Main.TWO -> {
                                        numberView!!.text =
                                            Main.Number.substring(numberIndex, numberIndex + 2)
                                        numberIndex += 2
                                    }
                                    Main.THREE -> {
                                        if (numberIndex + 3 >= 100) {
                                            numberView!!.text =
                                                Main.Number.substring(numberIndex, 100)
                                            numberIndex = 100
                                        } else {
                                            numberView!!.text =
                                                Main.Number.substring(numberIndex, numberIndex + 3)
                                            numberIndex += 3
                                        }
                                    }
                                    Main.FOUR -> {
                                        numberView!!.text =
                                            Main.Number.substring(numberIndex, numberIndex + 4)
                                        numberIndex += 4
                                    }
                                    Main.TWOTWO -> {
                                        numberView!!.text =
                                            Main.Number.substring(
                                                numberIndex,
                                                numberIndex + 2
                                            ) + "  " + Main.Number.substring(
                                                numberIndex + 2,
                                                numberIndex + 4
                                            )
                                        numberIndex += 4
                                    }
                                    Main.THREETHREE -> {
                                        if (numberIndex + 6 >= 100) {
                                            numberView!!.text = Main.Number.substring(
                                                numberIndex, numberIndex + 3
                                            ) + "  " + Main.Number.substring(numberIndex + 3, 100)
                                            numberIndex = 100
                                        } else {
                                            numberView!!.text =
                                                Main.Number.substring(
                                                    numberIndex,
                                                    numberIndex + 3
                                                ) + "  " + Main.Number.substring(
                                                    numberIndex + 3,
                                                    numberIndex + 6
                                                )
                                            numberIndex += 6
                                        }
                                    }
                                }
                            }
                        } else if (Main.operationType == Main.TYPE_AUTO) {
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
        if (timer != null)
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
                    if (Main.competitionType == Main.TYPE_CARD) {
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
                    } else if (Main.competitionType == Main.TYPE_NUMBER) {
                        if (numberIndex >= 100) {
                            timeView!!.visibility = View.VISIBLE
                            timeView!!.text = "Done"
                            stop(autoTimer!!)
                        } else {
                            when (Main.pairNum) {
                                Main.TWO -> {
                                    numberView!!.text =
                                        Main.Number.substring(numberIndex, numberIndex + 2)
                                    numberIndex += 2
                                }
                                Main.THREE -> {
                                    if (numberIndex + 3 >= 100) {
                                        numberView!!.text =
                                            Main.Number.substring(numberIndex, 100)
                                        numberIndex = 100
                                    } else {
                                        numberView!!.text =
                                            Main.Number.substring(numberIndex, numberIndex + 3)
                                        numberIndex += 3
                                    }
                                }
                                Main.FOUR -> {
                                    numberView!!.text =
                                        Main.Number.substring(numberIndex, numberIndex + 4)
                                    numberIndex += 4
                                }
                                Main.TWOTWO -> {
                                    numberView!!.text =
                                        Main.Number.substring(
                                            numberIndex,
                                            numberIndex + 2
                                        ) + "  " + Main.Number.substring(
                                            numberIndex + 2,
                                            numberIndex + 4
                                        )
                                    numberIndex += 4
                                }
                                Main.THREETHREE -> {
                                    if (numberIndex + 6 >= 100) {
                                        numberView!!.text = Main.Number.substring(
                                            numberIndex, numberIndex + 3
                                        ) + "  " + Main.Number.substring(numberIndex + 3, 100)
                                        numberIndex = 100
                                    } else {
                                        numberView!!.text =
                                            Main.Number.substring(
                                                numberIndex,
                                                numberIndex + 3
                                            ) + "  " + Main.Number.substring(
                                                numberIndex + 3,
                                                numberIndex + 6
                                            )
                                        numberIndex += 6
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }, 0, (Main.autoTime * 1000).toLong())
    }

    private fun change(dir: Int) {
        if (dir == Main.LEFT) {
            if (finish) {
                finish = false
                right!!.text = "->"
                for (i in 0 until Main.pairCard) {
                    cardView!![i].visibility = View.VISIBLE
                }
            }
            if (Main.competitionType == Main.TYPE_CARD) {
                if (cardIndex - Main.pairCard <= 0) {
                    left!!.isEnabled = false
                } else {
                    left!!.isEnabled = true
                    right!!.isEnabled = true
                    cardIndex -= if (cardIndex >= 52 && Main.pairCard == 3) {
                        Main.pairCard + 1
                    } else
                        Main.pairCard * 2
                    println(cardIndex)
                    for (i in 0 until Main.pairCard) {
                        if (cardIndex in 0..51)
                            cardView!![i].setImageResource(Main.cardData[Main.Card[cardIndex++]].img)
                        else
                            cardView!![i].visibility = View.INVISIBLE
                    }
                    if (cardIndex - Main.pairCard <= 0) {
                        left!!.isEnabled = false
                    }
                }
            } else if (Main.competitionType == Main.TYPE_NUMBER) {
                if (numberIndex < 0) {
                    left!!.isEnabled = false
                } else {
                    left!!.isEnabled = true
                    right!!.isEnabled = true
                    when (Main.pairNum) {
                        Main.TWO -> {
                            numberIndex -= 4
                            if (numberIndex < 0) {
                                numberIndex = 2
                            } else {
                                if (numberIndex <= 0) {
                                    left!!.isEnabled = false
                                }
                                numberView!!.text =
                                    Main.Number.substring(numberIndex, numberIndex + 2)
                                numberIndex += 2
                            }
                        }
                        Main.THREE -> {
                            numberIndex -= if (numberIndex >= 100)
                                4
                            else
                                6
                            if (numberIndex < 0) {
                                numberIndex = 3
                            } else {
                                if (numberIndex <= 0) {
                                    left!!.isEnabled = false
                                }
                                if (numberIndex + 3 >= 100) {
                                    numberView!!.text =
                                        Main.Number.substring(numberIndex, 100)
                                    numberIndex = 100
                                } else {
                                    numberView!!.text =
                                        Main.Number.substring(numberIndex, numberIndex + 3)
                                    numberIndex += 3
                                }
                            }
                        }
                        Main.FOUR -> {
                            numberIndex -= 8
                            if (numberIndex < 0) {
                                numberIndex = 4
                            } else {
                                if (numberIndex <= 0) {
                                    left!!.isEnabled = false
                                }
                                numberView!!.text =
                                    Main.Number.substring(numberIndex, numberIndex + 4)
                                numberIndex += 4
                            }
                        }
                        Main.TWOTWO -> {
                            numberIndex -= 8
                            if (numberIndex < 0) {
                                numberIndex = 4
                            } else {
                                if (numberIndex <= 0) {
                                    left!!.isEnabled = false
                                }
                                numberView!!.text =
                                    Main.Number.substring(
                                        numberIndex,
                                        numberIndex + 2
                                    ) + "  " + Main.Number.substring(
                                        numberIndex + 2,
                                        numberIndex + 4
                                    )
                                numberIndex += 4
                            }
                        }
                        Main.THREETHREE -> {
                            numberIndex -= if (numberIndex >= 100)
                                10
                            else
                                12
                            if (numberIndex < 0) {
                                numberIndex = 6
                            } else {
                                if (numberIndex <= 0) {
                                    left!!.isEnabled = false
                                }

                                if (numberIndex + 6 >= 100) {
                                    numberView!!.text = Main.Number.substring(
                                        numberIndex, numberIndex + 3
                                    ) + "  " + Main.Number.substring(numberIndex + 3, 100)
                                    numberIndex = 100
                                } else {
                                    numberView!!.text =
                                        Main.Number.substring(
                                            numberIndex,
                                            numberIndex + 3
                                        ) + "  " + Main.Number.substring(
                                            numberIndex + 3,
                                            numberIndex + 6
                                        )
                                    numberIndex += 6
                                }
                            }
                        }
                    }
                }
            }
        } else if (dir == Main.RIGHT) {
            if (finish) {
                stop(playTimer!!)
                startActivity(Intent(this, AnsActivity::class.java))
                finish()
            }

            if (Main.competitionType == Main.TYPE_CARD) {
                left!!.isEnabled = true
                right!!.isEnabled = true
                for (i in 0 until Main.pairCard) {
                    if (cardIndex < 52)
                        cardView!![i].setImageResource(Main.cardData[Main.Card[cardIndex++]].img)
                    else
                        cardView!![i].visibility = View.INVISIBLE
                }
                if (cardIndex >= 52) {
                    setFin()
                }
            } else if (Main.competitionType == Main.TYPE_NUMBER) {
                if (numberIndex >= 100) {
                    right!!.isEnabled = false
                    timeView!!.setTextColor(Color.RED)
                    stop(playTimer!!)
                } else {
                    left!!.isEnabled = true
                    right!!.isEnabled = true
                    when (Main.pairNum) {
                        Main.TWO -> {
                            if (numberIndex + 2 >= 100) {
                                setFin()
                            }
                            numberView!!.text =
                                Main.Number.substring(numberIndex, numberIndex + 2)
                            numberIndex += 2
                        }
                        Main.THREE -> {
                            if (numberIndex + 3 >= 100) {
                                numberView!!.text =
                                    Main.Number.substring(numberIndex, 100)
                                numberIndex = 100
                                setFin()
                            } else {
                                numberView!!.text =
                                    Main.Number.substring(numberIndex, numberIndex + 3)
                                numberIndex += 3
                            }
                        }
                        Main.FOUR -> {
                            if (numberIndex + 4 >= 100) {
                                setFin()
                            }
                            numberView!!.text =
                                Main.Number.substring(numberIndex, numberIndex + 4)
                            numberIndex += 4
                        }
                        Main.TWOTWO -> {
                            if (numberIndex + 4 >= 100) {
                                numberView!!.text = Main.Number.substring(
                                    numberIndex, numberIndex + 2
                                ) + "  " + Main.Number.substring(numberIndex + 2, 100)
                                numberIndex = 100
                                setFin()
                            } else {
                                numberView!!.text =
                                    Main.Number.substring(
                                        numberIndex,
                                        numberIndex + 2
                                    ) + "  " + Main.Number.substring(
                                        numberIndex + 2,
                                        numberIndex + 4
                                    )
                                numberIndex += 4
                            }
                        }
                        Main.THREETHREE -> {
                            println(numberIndex)
                            if (numberIndex + 6 >= 100) {
                                numberView!!.text = Main.Number.substring(
                                    numberIndex, numberIndex + 3
                                ) + "  " + Main.Number.substring(numberIndex + 3, 100)
                                numberIndex = 100
                                setFin()
                            } else {
                                numberView!!.text =
                                    Main.Number.substring(
                                        numberIndex,
                                        numberIndex + 3
                                    ) + "  " + Main.Number.substring(
                                        numberIndex + 3,
                                        numberIndex + 6
                                    )
                                numberIndex += 6
                            }
                        }
                    }
                }
            }
        }
    }

    private fun setFin() {
        timeView!!.setTextColor(Color.RED)
        if (Main.methodType == Main.TYPE_TRAINING) {
            right!!.isEnabled = false
            stop(playTimer!!)
        } else if (Main.methodType == Main.TYPE_MEMORY) {
            right!!.text = "Fin"
            finish = true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.back, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.back -> {
                AlertDialog.Builder(this).setMessage("Are you sure you want to go back to main?")
                    .setPositiveButton("Yes") { dialog, which ->
                        startActivity(Intent(this, MainActivity::class.java))
                        if (autoTimer != null)
                            stop(autoTimer!!)
                        if (startTimer != null)
                            stop(startTimer!!)
                        if (playTimer != null)
                            stop(playTimer!!)
                        finish()
                    }
                    .setNegativeButton("No") { dialog, which ->
                    }
                    .show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            AlertDialog.Builder(this).setMessage("Are you sure you want to go back to main?")
                .setPositiveButton("Yes") { dialog, which ->
                    startActivity(Intent(this, MainActivity::class.java))
                    if (autoTimer != null)
                        stop(autoTimer!!)
                    if (startTimer != null)
                        stop(startTimer!!)
                    if (playTimer != null)
                        stop(playTimer!!)
                    finish()
                }
                .setNegativeButton("No") { dialog, which ->
                }
                .show()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}
