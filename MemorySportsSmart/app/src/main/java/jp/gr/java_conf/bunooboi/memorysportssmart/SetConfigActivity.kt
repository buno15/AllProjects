package jp.gr.java_conf.bunooboi.memorysportssmart

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.floor
import kotlin.math.round

class SetConfigActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config)

        Main.operationType = Main.TYPE_MANUAL
        val board1: Board = findViewById(R.id.board1)

        if (Main.competitionType == Main.TYPE_CARD) {
            board1.textView.text = "Cards"
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
        } else if (Main.competitionType == Main.TYPE_NUMBER) {
            spinner.adapter = numberAdapter
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
                val spinnerParent = parent as Spinner
                val item = spinnerParent.selectedItem as String
            }
        }


        val startButton: Button = findViewById(R.id.start)
        startButton.text = "Start"
        startButton.setOnClickListener {
            when (Main.competitionType) {
                Main.TYPE_CARD -> Main.initCard()
                Main.TYPE_NUMBER -> Main.initNumber()
            }
            startActivity(Intent(this, PlayActivity::class.java))
        }

    }
}
