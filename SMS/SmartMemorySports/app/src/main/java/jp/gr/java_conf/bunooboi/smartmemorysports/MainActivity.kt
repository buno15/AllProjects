package jp.gr.java_conf.bunooboi.smartmemorysports

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Main.setIcon(this)
        setContentView(R.layout.activity_main)


        Main.init()
        Main.input(this)

        val board1: Board = findViewById(R.id.board1)
        val board2: Board = findViewById(R.id.board2)

        board1.textView.text = "Memory"
        board2.textView.text = "Training"

        board1.imageButton.setImageResource(R.mipmap.memory)
        board2.imageButton.setImageResource(R.mipmap.training)

        board1.imageButton.setOnClickListener {
            Main.methodType = Main.TYPE_MEMORY
            startActivity(Intent(this, SelectCompetitionActivity::class.java))
        }

        board2.imageButton.setOnClickListener {
            Main.methodType = Main.TYPE_TRAINING
            startActivity(Intent(this, SelectCompetitionActivity::class.java))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.setting, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.setting -> {
                startActivity(Intent(this, SettingActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
