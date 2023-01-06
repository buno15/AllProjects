package jp.gr.java_conf.bunooboi.smartmemorysports

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.floor
import kotlin.math.round


class SetRuleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Main.setIcon(this)
        setContentView(R.layout.activity_rule)

        Main.operationType = Main.TYPE_AUTO

        val board1: Board = findViewById(R.id.board1)
        val board2: Board = findViewById(R.id.board2)

        val settingLayout1: LinearLayout = findViewById(R.id.settingLayout1)
        val settingLayout2: LinearLayout = findViewById(R.id.settingLayout2)

        board1.textView.text = "Auto"
        board2.textView.text = "Manual"

        board1.imageButton.setImageResource(R.mipmap.auto)
        board2.imageButton.setImageResource(R.mipmap.manual)

        setDisable(board1.imageButton)

        board1.imageButton.setOnClickListener {
            Main.operationType = Main.TYPE_AUTO
            setDisable(board1.imageButton)
            setEnable(board2.imageButton)
            settingLayout1.visibility = View.VISIBLE
            settingLayout2.visibility = View.VISIBLE
        }

        board2.imageButton.setOnClickListener {
            Main.operationType = Main.TYPE_MANUAL
            setDisable(board2.imageButton)
            setEnable(board1.imageButton)
            settingLayout1.visibility = View.INVISIBLE
            settingLayout2.visibility = View.INVISIBLE
        }

        val spinner: Spinner = findViewById(R.id.pair)
        val cardItems = arrayOf(
            "1",
            "2",
            "3"
        )
        val numberItems = arrayOf(
            "2",
            "3",
            "4",
            "2\t\t\t2",
            "3\t\t\t3"
        )
        val cardAdapter = ArrayAdapter(
            applicationContext,
            R.layout.spinner_item,
            cardItems
        )
        val numberAdapter = ArrayAdapter(
            applicationContext,
            R.layout.spinner_item,
            numberItems
        )
        cardAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
        numberAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item)


        if (Main.competitionType == Main.TYPE_CARD) {
            spinner.adapter = cardAdapter
            when (Main.pairCard) {
                1 -> spinner.setSelection(0)
                2 -> spinner.setSelection(1)
                3 -> spinner.setSelection(2)
            }
        } else if (Main.competitionType == Main.TYPE_NUMBER) {
            spinner.adapter = numberAdapter
            when (Main.pairNum) {
                Main.TWO -> spinner.setSelection(0)
                Main.THREE -> spinner.setSelection(1)
                Main.FOUR -> spinner.setSelection(2)
                Main.TWOTWO -> spinner.setSelection(3)
                Main.THREETHREE -> spinner.setSelection(4)
            }
        }
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (Main.competitionType == Main.TYPE_CARD) {
                    when (position) {
                        0 -> Main.pairCard = 1
                        1 -> Main.pairCard = 2
                        2 -> Main.pairCard = 3
                    }
                } else if (Main.competitionType == Main.TYPE_NUMBER) {
                    when (position) {
                        0 -> Main.pairNum = Main.TWO
                        1 -> Main.pairNum = Main.THREE
                        2 -> Main.pairNum = Main.FOUR
                        3 -> Main.pairNum = Main.TWOTWO
                        4 -> Main.pairNum = Main.THREETHREE
                    }
                }
            }

        }


        val editText1: EditText = findViewById(R.id.editText1)
        editText1.setText((round((Main.autoTime) * 10.0) / 10.0).toString())
        editText1.textSize = 26 * DisplayManager.getScaleSize(this)

        val editText2: EditText = findViewById(R.id.editText2)
        editText2.setText(Main.startTime.toString())
        editText2.textSize = 26 * DisplayManager.getScaleSize(this)


        var button = listOf<Button>(
            findViewById(R.id.button1),
            findViewById(R.id.button2),
            findViewById(R.id.button3),
            findViewById(R.id.button4)
        )

        button[0].text = "- 1"
        button[1].text = "- 0.1"
        button[2].text = "+ 0.1"
        button[3].text = "+ 1"

        button[0].setOnClickListener {
            val nTime = editText1.text.toString().toDouble()
            if (nTime - 1.0 > 0) {
                var aTime = round((editText1.text.toString().toDouble() - 1.0) * 10.0) / 10.0
                editText1.setText(aTime.toString())
            }
        }

        button[1].setOnClickListener {
            val nTime = editText1.text.toString().toDouble()
            if (nTime - 0.1 > 0) {
                var aTime = round((editText1.text.toString().toDouble() - 0.1) * 10.0) / 10.0
                editText1.setText(aTime.toString())
            }
        }

        button[2].setOnClickListener {
            val aTime = round((editText1.text.toString().toDouble() + 0.1) * 10.0) / 10.0
            editText1.setText(aTime.toString())
        }

        button[3].setOnClickListener {
            val aTime = round((editText1.text.toString().toDouble() + 1.0) * 10.0) / 10.0
            editText1.setText(aTime.toString())
        }

        val startButton: Button = findViewById(R.id.start)
        startButton.text = "Start"
        startButton.setOnClickListener {
            when (Main.competitionType) {
                Main.TYPE_CARD -> Main.initCard()
                Main.TYPE_NUMBER -> Main.initNumber()
            }
            try {
                val aTime: String = editText1.text.toString()
                Main.autoTime = floor(aTime.toDouble() * 10.0) / 10.0
                if (Main.autoTime <= 0)
                    Main.autoTime = 1.0
            } catch (nfe: NumberFormatException) {
                nfe.printStackTrace()
                Main.autoTime = 1.0
            }
            try {
                Main.startTime = editText2.text.toString().toInt()
                if (Main.startTime > 60 || Main.startTime <= 0) {
                    Main.startTime = 60
                }
            } catch (nfe: NumberFormatException) {
                Main.startTime = 5
            }
            Main.output(this)
            startActivity(Intent(this, PlayActivity::class.java))
        }

    }

    private fun setDisable(ib: ImageButton) {
        ib.isEnabled = false
        ib.setBackgroundColor(Color.parseColor("#bbbbbb"))
    }

    private fun setEnable(ib: ImageButton) {
        ib.isEnabled = true
        ib.setBackgroundColor(Color.parseColor("#eeeeee"))
    }
}
