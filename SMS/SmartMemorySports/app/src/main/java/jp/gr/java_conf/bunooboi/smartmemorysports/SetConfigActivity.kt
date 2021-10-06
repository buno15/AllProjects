package jp.gr.java_conf.bunooboi.smartmemorysports

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class SetConfigActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Main.setIcon(this)
        setContentView(R.layout.activity_config)

        Main.operationType = Main.TYPE_MANUAL
        val board1: Board = findViewById(R.id.board1)

        if (Main.competitionType == Main.TYPE_CARD) {
            board1.textView.text = "Unko"
            board1.imageButton.setImageResource(R.mipmap.card)
        } else if (Main.competitionType == Main.TYPE_NUMBER) {
            board1.textView.text = "Numbers"
            board1.imageButton.setImageResource(R.mipmap.number)
        }

        val textView1: TextView = findViewById(R.id.text1)
        textView1.text = "Groups:"

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

        val editText: EditText = findViewById(R.id.editText)
        editText.setText(Main.startTime.toString())
        editText.textSize = 26 * DisplayManager.getScaleSize(this)


        val startButton: Button = findViewById(R.id.start)
        startButton.text = "Start"
        startButton.setOnClickListener {
            when (Main.competitionType) {
                Main.TYPE_CARD -> Main.initCard()
                Main.TYPE_NUMBER -> Main.initNumber()
            }
            try {
                Main.startTime = editText.text.toString().toInt()
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
}
