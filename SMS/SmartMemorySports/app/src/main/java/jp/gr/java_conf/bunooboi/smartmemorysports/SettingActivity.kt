package jp.gr.java_conf.bunooboi.smartmemorysports

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity


class SettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Main.setIcon(this)
        setContentView(R.layout.activity_setting)

        val list1: List = findViewById(R.id.list1)

        list1.textView.text = "Hide Timer"

        list1.aSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            Main.timerHide = list1.aSwitch.isChecked
            println(Main.timerHide)
            Main.outputSetting(this)
        }
        list1.aSwitch.isChecked = Main.timerHide
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.back, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.back -> {
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
