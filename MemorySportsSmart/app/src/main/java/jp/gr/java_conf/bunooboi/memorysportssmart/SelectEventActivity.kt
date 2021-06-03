package jp.gr.java_conf.bunooboi.memorysportssmart

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SelectEventActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val board1: Board = findViewById(R.id.board1)
        val board2: Board = findViewById(R.id.board2)

        board1.textView.text = "Cards"
        board2.textView.text = "Numbers"

        board1.imageButton.setImageResource(R.mipmap.card)
        board2.imageButton.setImageResource(R.mipmap.number)

        board1.imageButton.setOnClickListener {
            startActivity(Intent(this, SetRuleActivity::class.java))
        }

        board2.imageButton.setOnClickListener {
            startActivity(Intent(this, SetRuleActivity::class.java))
        }


    }
}