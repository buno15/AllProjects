package jp.gr.java_conf.bunooboi.memorysportssmart

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.floor

class SetRuleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rule)

        val board1: Board = findViewById(R.id.board1)
        val board2: Board = findViewById(R.id.board2)

        val settingLayout: LinearLayout = findViewById(R.id.settingLayout)

        board1.textView.text = "Auto"
        board2.textView.text = "Manual"

        board1.imageButton.setImageResource(R.mipmap.auto)
        board2.imageButton.setImageResource(R.mipmap.manual)

        setDisable(board1.imageButton)

        board1.imageButton.setOnClickListener {
            setDisable(board1.imageButton)
            setEnable(board2.imageButton)
            settingLayout.visibility = View.VISIBLE
        }

        board2.imageButton.setOnClickListener {
            setDisable(board2.imageButton)
            setEnable(board1.imageButton)
            settingLayout.visibility = View.INVISIBLE
        }

        val textView1: TextView = findViewById(R.id.text1)
        textView1.text = "seconds"

        val editText: EditText = findViewById(R.id.editText)
        editText.setText(Main.autoTime.toString())


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
            val nTime = editText.text.toString().toDouble()
            if (nTime - 1.0 > 0) {
                var aTime = floor((editText.text.toString().toDouble() - 1.0) * 10.0) / 10.0
                editText.setText(aTime.toString())
            }
        }

        button[1].setOnClickListener {
            val nTime = editText.text.toString().toDouble()
            if (nTime - 0.1 > 0) {
                var aTime = floor((editText.text.toString().toDouble() - 0.1) * 10.0) / 10.0
                editText.setText(aTime.toString())
            }
        }

        button[2].setOnClickListener {
            val aTime = floor((editText.text.toString().toDouble() + 0.1) * 10.0) / 10.0
            editText.setText(aTime.toString())
        }

        button[3].setOnClickListener {
            val aTime = floor((editText.text.toString().toDouble() + 1.0) * 10.0) / 10.0
            editText.setText(aTime.toString())
        }

        val startButton: Button = findViewById(R.id.start)
        startButton.text = "Start"
        startButton.setOnClickListener {
            try {
                val aTime: String = editText.text.toString()
                Main.autoTime = floor(aTime.toDouble() * 10.0) / 10.0
                if (Main.autoTime <= 0)
                    Main.autoTime = 1.0
            } catch (nfe: NumberFormatException) {
                nfe.printStackTrace()
                Main.autoTime = 1.0
            }
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
