package jp.gr.java_conf.bunooboi.smartmemorysports

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.util.TypedValue
import android.view.Gravity
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.TextViewCompat
import java.util.*


class AnsActivity : AppCompatActivity() {

    var ansTimer: Timer? = null

    var timeView: TextView? = null

    var left: Button? = null
    var right: Button? = null
    var delete: Button? = null

    var number: MutableList<Button>? = mutableListOf()
    var mark: MutableList<Button>? = mutableListOf()


    var text: MutableList<TextView>? = mutableListOf()

    var index: Int = 0

    var row = 0
    var col = 0
    var size = 0
    var numberSize = 0

    private val usedNum = Array(52) { false }
    private val usedMark = Array(52) { false }

    val usedNumber = Array(100) { false }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Main.setIcon(this)

        if (Main.competitionType == Main.TYPE_CARD) {
            setContentView(R.layout.activity_ans1)
            row = 6
            col = 9
            size = 52
            numberSize = 13
        } else if (Main.competitionType == Main.TYPE_NUMBER) {
            setContentView(R.layout.activity_ans2)
            row = 10
            col = 10
            size = 100
            numberSize = 10
        }

        timeView = findViewById(R.id.time)

        val table: TableLayout = findViewById(R.id.table)
        val tableRow: MutableList<TableRow> = mutableListOf()


        for (i in 0 until col) {
            tableRow.add(TableRow(this))
            tableRow!![i].layoutParams = TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT, 0, 1F
            )
            table.addView(tableRow!![i])
        }
        for (i in 0 until size) {
            text!!.add(TextView(this))
            text!![i].text = (i + 1).toString()
            text!![i].setTextColor(Color.GRAY)
            text!![i].setBackgroundResource(R.drawable.edittext1_custom)
            text!![i].layoutParams = TableRow.LayoutParams(
                0, TableRow.LayoutParams.MATCH_PARENT, 1F
            )
            text!![i].gravity = Gravity.CENTER
            text!![i].setSingleLine(true)

            TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(
                text!![i],
                1,
                18,
                1,
                TypedValue.COMPLEX_UNIT_DIP
            )
            text!![i].setOnClickListener {
                text!![index].setBackgroundResource(R.drawable.edittext1_custom)
                text!![i].setBackgroundResource(R.drawable.edittext2_custom)
                index = i
            }
        }


        if (Main.competitionType == Main.TYPE_CARD) {
            for (i in 0 until size) {
                tableRow[i / 6].addView(text!![i])
            }

            val space1 = Space(this)
            space1.layoutParams = TableRow.LayoutParams(
                0, LinearLayout.LayoutParams.MATCH_PARENT, 1F
            )
            tableRow[8].addView(space1)

            val space2 = Space(this)
            space2.layoutParams = TableRow.LayoutParams(
                0, LinearLayout.LayoutParams.MATCH_PARENT, 1F
            )
            tableRow[8].addView(space2)

            number!!.add(findViewById(R.id.b1))
            number!!.add(findViewById(R.id.b2))
            number!!.add(findViewById(R.id.b3))
            number!!.add(findViewById(R.id.b4))
            number!!.add(findViewById(R.id.b5))
            number!!.add(findViewById(R.id.b6))
            number!!.add(findViewById(R.id.b7))
            number!!.add(findViewById(R.id.b8))
            number!!.add(findViewById(R.id.b9))
            number!!.add(findViewById(R.id.b10))
            number!!.add(findViewById(R.id.b11))
            number!!.add(findViewById(R.id.b12))
            number!!.add(findViewById(R.id.b13))

            for (i in 0 until 13) {
                number!![i].setOnClickListener {
                    var num = ""
                    when {
                        (i < 10) -> num = (i + 1).toString()
                        (i == 10) -> num = "J"
                        (i == 11) -> num = "Q"
                        (i == 12) -> num = "K"
                    }
                    text!![index].setTextColor(Color.BLUE)
                    if (!usedMark[index] && !usedNum[index]) {
                        text!![index].text = num
                    } else if (usedMark[index] && !usedNum[index]) {
                        text!![index].text = text!![index].text.toString() + num
                    } else if (!usedMark[index] && usedNum[index]) {
                        text!![index].text = num
                    } else if (usedNum[index] && usedMark[index]) {
                        text!![index].text = text!![index].text.toString() + num
                    }
                    usedNum[index] = true
                    if (usedNum[index] && usedMark[index]) {
                        usedNum[index] = false
                        usedMark[index] = false
                        right()
                    }
                }
            }

            mark!!.add(findViewById(R.id.daiya))
            mark!!.add(findViewById(R.id.kurabu))
            mark!!.add(findViewById(R.id.heart))
            mark!!.add(findViewById(R.id.supedo))
            for (i in 0 until 4) {
                mark!![i].setOnClickListener {
                    text!![index].setTextColor(Color.BLUE)
                    if (!usedMark[index] && !usedNum[index]) {
                        when (i) {
                            0 -> text!![index].text = "♦"
                            1 -> text!![index].text = "♣"
                            2 -> text!![index].text = "♥"
                            3 -> text!![index].text = "♠"
                        }
                    } else if (!usedMark[index] && usedNum[index]) {
                        when (i) {
                            0 -> text!![index].text = "♦" + text!![index].text
                            1 -> text!![index].text = "♣" + text!![index].text
                            2 -> text!![index].text = "♥" + text!![index].text
                            3 -> text!![index].text = "♠" + text!![index].text
                        }
                    } else if (usedMark[index] && !usedNum[index]) {
                        when (i) {
                            0 -> text!![index].text = "♦"
                            1 -> text!![index].text = "♣"
                            2 -> text!![index].text = "♥"
                            3 -> text!![index].text = "♠"
                        }
                    } else if (usedNum[index] && usedMark[index]) {
                        when (i) {
                            0 -> text!![index].text = "♦" + text!![index].text
                            1 -> text!![index].text = "♣" + text!![index].text
                            2 -> text!![index].text = "♥" + text!![index].text
                            3 -> text!![index].text = "♠" + text!![index].text
                        }
                    }
                    usedMark[index] = true
                    if (usedNum[index] && usedMark[index]) {
                        usedNum[index] = false
                        usedMark[index] = false
                        right()
                    }
                }
            }

            delete = findViewById(R.id.delete)
            delete!!.setOnClickListener {
                text!![index].text = (index + 1).toString()
                text!![index].setTextColor(Color.GRAY)
                usedNum[index] = false
                usedMark[index] = false
            }

        } else if (Main.competitionType == Main.TYPE_NUMBER) {
            for (i in 0 until size) {
                tableRow[i / 10].addView(text!![i])
            }

            number!!.add(findViewById(R.id.b10))
            number!!.add(findViewById(R.id.b1))
            number!!.add(findViewById(R.id.b2))
            number!!.add(findViewById(R.id.b3))
            number!!.add(findViewById(R.id.b4))
            number!!.add(findViewById(R.id.b5))
            number!!.add(findViewById(R.id.b6))
            number!!.add(findViewById(R.id.b7))
            number!!.add(findViewById(R.id.b8))
            number!!.add(findViewById(R.id.b9))


            for (i in 0 until 10) {
                number!![i].setOnClickListener {
                    usedNumber[index] = true
                    text!![index].text = i.toString()
                    text!![index].setTextColor(Color.BLUE)
                    text!![index].setBackgroundResource(R.drawable.edittext1_custom)
                    if (index < 99) {
                        index++
                    }
                    text!![index].setBackgroundResource(R.drawable.edittext2_custom)
                }
            }
        }

        left = findViewById(R.id.left)
        left!!.setOnClickListener {
            text!![index].setBackgroundResource(R.drawable.edittext1_custom)
            if (index == 0) {
                index = size - 1
            } else {
                index--
            }
            text!![index].setBackgroundResource(R.drawable.edittext2_custom)
        }

        right = findViewById(R.id.right)
        right!!.setOnClickListener {
            text!![index].setBackgroundResource(R.drawable.edittext1_custom)
            if (index == size - 1) {
                index = 0
            } else {
                index++
            }
            text!![index].setBackgroundResource(R.drawable.edittext2_custom)
        }

        val fin: Button = findViewById(R.id.Fin)
        fin.setOnClickListener {
            stop(ansTimer!!)
            makeAns()
            startActivity(Intent(this, ResultActivity::class.java))
            finish()
        }

        text!![0].setBackgroundResource(R.drawable.edittext2_custom)

        ansTime()
    }

    private fun right() {
        text!![index].setBackgroundResource(R.drawable.edittext1_custom)
        if (index < size - 1) {
            index++
        }
        text!![index].setBackgroundResource(R.drawable.edittext2_custom)
    }


    private fun makeAns() {
        if (Main.competitionType == Main.TYPE_CARD) {
            var ans = mutableListOf<String>()
            for (i in 0 until size) {
                var mark: String = text!![i].text.toString().substring(0, 1)
                mark = when (mark) {
                    "♦" -> "d"
                    "♣" -> "c"
                    "♥" -> "h"
                    "♠" -> "s"
                    else -> "error"
                }
                val s: String =
                    if (mark == "error") "none" else mark + text!![i].text.toString()
                        .substring(1, text!![i].text.length)
                ans.add(s)
            }
            Main.ansCard = ans
        } else if (Main.competitionType == Main.TYPE_NUMBER) {
            var ansNum = ""
            for (i in 0 until size) {
                ansNum += if (usedNumber[i]) text!![i].text.toString() else "?"
            }
            Main.ansNumber = ansNum
        }

    }

    private fun stop(timer: Timer) {
        timer?.cancel()
    }

    private fun ansTime() {
        val handler = Handler()
        ansTimer = Timer()
        ansTimer!!.schedule(object : TimerTask() {
            override fun run() {
                handler.post {
                    Main.ansTime++

                    val minute = String.format("%02d", (Main.ansTime / 60))
                    val second = String.format("%02d", (Main.ansTime % 60))

                    timeView!!.text = "$minute:$second"
                }
            }
        }, 0, 1000)
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
                        if (ansTimer != null)
                            stop(ansTimer!!)
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
                    if (ansTimer != null)
                        stop(ansTimer!!)
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
