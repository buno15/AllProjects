package jp.gr.java_conf.bunooboi.memorysportssmart

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

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

        val startButton: Button = findViewById(R.id.start)
        startButton.text = "Start"

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
