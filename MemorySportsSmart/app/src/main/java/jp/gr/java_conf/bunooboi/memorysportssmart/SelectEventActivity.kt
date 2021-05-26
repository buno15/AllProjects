package jp.gr.java_conf.bunooboi.memorysportssmart

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SelectEventActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val board1: Board = findViewById(R.id.board1)
        val board2: Board = findViewById(R.id.board2)

        board1.textView.text = "Memory"
        board2.textView.text = "Training"

        board1.imageButton.setImageResource(R.mipmap.memory)
        board2.imageButton.setImageResource(R.mipmap.training)
    }
}