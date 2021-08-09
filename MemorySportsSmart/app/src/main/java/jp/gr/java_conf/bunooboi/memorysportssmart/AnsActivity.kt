package jp.gr.java_conf.bunooboi.memorysportssmart

import android.os.Bundle
import android.os.Handler
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.TextViewCompat
import java.security.AccessController.getContext
import java.util.*


class AnsActivity : AppCompatActivity() {

    var ansTimer: Timer? = null

    var timeView: TextView? = null
    var left: Button? = null
    var right: Button? = null

    var text: MutableList<TextView>? = mutableListOf()

    var index: Int = 0

    var row = 0
    var col = 0
    var size = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Main.competitionType == Main.TYPE_CARD) {
            setContentView(R.layout.activity_ans1)
            row = 6
            col = 9
            size = 52
        } else if (Main.competitionType == Main.TYPE_NUMBER) {
            setContentView(R.layout.activity_ans2)
            row = 10
            col = 10
            size = 100
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
                when {
                    (i < 6) -> tableRow[0].addView(text!![i])
                    (i < 12) -> tableRow[1].addView(text!![i])
                    (i < 18) -> tableRow[2].addView(text!![i])
                    (i < 24) -> tableRow[3].addView(text!![i])
                    (i < 30) -> tableRow[4].addView(text!![i])
                    (i < 36) -> tableRow[5].addView(text!![i])
                    (i < 42) -> tableRow[6].addView(text!![i])
                    (i < 48) -> tableRow[7].addView(text!![i])
                    (i < 54) -> tableRow[8].addView(text!![i])
                }
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
        } else if (Main.competitionType == Main.TYPE_NUMBER) {
            for (i in 0 until size) {
                when {
                    (i < 10) -> tableRow[0].addView(text!![i])
                    (i < 20) -> tableRow[1].addView(text!![i])
                    (i < 30) -> tableRow[2].addView(text!![i])
                    (i < 40) -> tableRow[3].addView(text!![i])
                    (i < 50) -> tableRow[4].addView(text!![i])
                    (i < 60) -> tableRow[5].addView(text!![i])
                    (i < 70) -> tableRow[6].addView(text!![i])
                    (i < 80) -> tableRow[7].addView(text!![i])
                    (i < 90) -> tableRow[8].addView(text!![i])
                    (i < 100) -> tableRow[9].addView(text!![i])
                }
            }
        }







        text!![0].setBackgroundResource(R.drawable.edittext2_custom)


        ansTime()
    }

    private fun clearFocus(size: Int) {
        for (i in 0 until size) {
            text!![i].setBackgroundResource(R.drawable.edittext1_custom)
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
}
