package jp.gr.java_conf.bunooboi.memorysportssmart

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.TextViewCompat


class ResultActivity : AppCompatActivity() {

    var correct: Int = 0

    var row = 0
    var col = 0
    var size = 0
    var numberSize = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val timeView: TextView = findViewById(R.id.time)
        val correctView: TextView = findViewById(R.id.correct)

        TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(
            timeView!!, 1, 25, 1, TypedValue.COMPLEX_UNIT_DIP
        )
        TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(
            correctView!!, 1, 25, 1, TypedValue.COMPLEX_UNIT_DIP
        )

        val pminute = String.format("%02d", (Main.playTime / 60))
        val psecond = String.format("%02d", (Main.playTime % 60))
        val aminute = String.format("%02d", (Main.ansTime / 60))
        val asecond = String.format("%02d", (Main.ansTime % 60))

        timeView!!.text =
            "Memorization Time: $pminute:$psecond \t\t\t Recall Time: $aminute:$asecond"

        correct = Main.checkAns()
        correctView.text = "Score: $correct"


        if (Main.competitionType == Main.TYPE_CARD) {
            row = 6
            col = 9
            size = 52
            numberSize = 13
        } else if (Main.competitionType == Main.TYPE_NUMBER) {
            row = 10
            col = 10
            size = 100
            numberSize = 10
        }

        val table1: TableLayout = findViewById(R.id.table1)
        val tableRow1: MutableList<TableRow> = mutableListOf()
        val text1: MutableList<TextView> = mutableListOf()

        val table2: TableLayout = findViewById(R.id.table2)
        val tableRow2: MutableList<TableRow> = mutableListOf()
        val text2: MutableList<TextView>? = mutableListOf()

        for (i in 0 until col) {
            tableRow1.add(TableRow(this))
            tableRow1!![i].layoutParams = TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT, 0, 1F
            )
            table1.addView(tableRow1!![i])
        }
        for (i in 0 until col) {
            tableRow2.add(TableRow(this))
            tableRow2!![i].layoutParams = TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT, 0, 1F
            )
            table2.addView(tableRow2!![i])
        }


        for (i in 0 until size) {
            text1!!.add(TextView(this))
            text1!![i].setTextColor(Color.GRAY)
            text1!![i].setBackgroundResource(R.drawable.edittext1_custom)
            text1!![i].layoutParams = TableRow.LayoutParams(
                0, TableRow.LayoutParams.MATCH_PARENT, 1F
            )
            text1!![i].gravity = Gravity.CENTER
            text1!![i].setSingleLine(true)

            TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(
                text1!![i],
                1,
                16,
                1,
                TypedValue.COMPLEX_UNIT_DIP
            )
        }

        for (i in 0 until size) {
            text2!!.add(TextView(this))
            text2!![i].setTextColor(Color.GRAY)
            text2!![i].setBackgroundResource(R.drawable.edittext2_custom)
            text2!![i].layoutParams = TableRow.LayoutParams(
                0, TableRow.LayoutParams.MATCH_PARENT, 1F
            )
            text2!![i].gravity = Gravity.CENTER
            text2!![i].setSingleLine(true)

            TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(
                text2!![i],
                1,
                16,
                1,
                TypedValue.COMPLEX_UNIT_DIP
            )
        }


        if (Main.competitionType == Main.TYPE_CARD) {
            for (i in 0 until size) {
                text1!![i].text = Main.cardData[Main.Card[i]].text

                var mark: String = Main.ansCard[i].substring(0, 1)
                mark = when (mark) {
                    "d" -> "♦"
                    "c" -> "♣"
                    "h" -> "♥"
                    "s" -> "♠"
                    else -> "error"
                }
                val s: String =
                    if (mark == "error") "" else mark + Main.ansCard[i].substring(
                        1,
                        Main.ansCard[i].length
                    )
                text2!![i].text = s

                if (Main.cardData[Main.Card[i]].text == s && s != "") {
                    text1!![i].setBackgroundResource(R.drawable.edittext3_custom)
                } else {
                    text1!![i].setBackgroundResource(R.drawable.edittext4_custom)
                }


                tableRow1[i / 6].addView(text1!![i])
                tableRow2[i / 6].addView(text2!![i])
            }

            val space1 = Space(this)
            space1.layoutParams = TableRow.LayoutParams(
                0, LinearLayout.LayoutParams.MATCH_PARENT, 1F
            )
            tableRow1[8].addView(space1)

            val space2 = Space(this)
            space2.layoutParams = TableRow.LayoutParams(
                0, LinearLayout.LayoutParams.MATCH_PARENT, 1F
            )
            tableRow1[8].addView(space2)


            val space3 = Space(this)
            space3.layoutParams = TableRow.LayoutParams(
                0, LinearLayout.LayoutParams.MATCH_PARENT, 1F
            )
            tableRow2[8].addView(space3)

            val space4 = Space(this)
            space4.layoutParams = TableRow.LayoutParams(
                0, LinearLayout.LayoutParams.MATCH_PARENT, 1F
            )
            tableRow2[8].addView(space4)
        } else if (Main.competitionType == Main.TYPE_NUMBER) {
            var Num = Main.Number.toCharArray()
            var ansNum = Main.ansNumber.toCharArray()

            for (i in 0 until size) {
                tableRow1[i / 10].addView(text1!![i])
                tableRow2[i / 10].addView(text2!![i])

                if (Num[i] == ansNum[i] && ansNum[i].toString() != "?") {
                    text1!![i].setBackgroundResource(R.drawable.edittext3_custom)
                } else {
                    text1!![i].setBackgroundResource(R.drawable.edittext4_custom)
                }

                text1!![i].text = if (Num[i].toString() == "?") "" else Num[i].toString()
                text2!![i].text = if (ansNum[i].toString() == "?") "" else ansNum[i].toString()
            }
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean { //アクションバー作成
        menuInflater.inflate(R.menu.back, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean { //アクションバーのイベント
        when (item.itemId) {
            R.id.back -> {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}
