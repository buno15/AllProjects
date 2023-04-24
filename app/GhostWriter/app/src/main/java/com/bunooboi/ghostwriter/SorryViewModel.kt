package com.bunooboi.ghostwriter

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.util.Log
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SorryViewModel : ViewModel() {

    private val _selected = MutableLiveData<List<Int>>(listOf(0, 0, 0))
    val selected: LiveData<List<Int>> = _selected


    init {
        _selected.value = listOf(0, 0, 0)
    }

    fun onSelected(index: Int, selected: Int): String {
        // listはtmpを経由して行う。MutableLiveDataのままの変更はしない？
        val tmpSelected = _selected.value!!.toMutableList()
        tmpSelected[index] = selected
        _selected.value = tmpSelected

        var flag = 0
        for (i in tmpSelected) {
            if (i != 0) flag++
        }

        if (flag == 3) {
            when (convert(tmpSelected)) {
                111 -> {
                    return "この度は、大変申し訳ございませんでした。\n" + "遅刻する事態となり、お詫びのしようもございません。\n" + "今後、二度とこのようなことのないように十分注意いたします。\n" + "重ねて申し訳ございませんでした。"
                }
                112 -> {
                    return "大変申し訳ございませんが、休暇をいただきたいと思っております。\n" + "休暇中、ご迷惑をお掛けいたしますが、よろしくお願いを申し上げます。\n" + "業務に支障をきたさないようにしてまいりますので、よろしくお願いを申し上げます。\n" + "重ねて申し訳ございませんでした。"
                }
                113 -> {
                    return "この度は、大変申し訳ございませんでした。\n" + "私のミスで、ご迷惑をお掛けし、お詫びのしようもございません。\n" + "今後、二度とこのようなことのないように十分注意いたします。\n" + "重ねて申し訳ございませんでした。"
                }
                121 -> {
                    return "遅刻してしまい、申し訳ありませんでした。\n" + "今後、十分に注意いたします。\n" + "申し訳ありませんでした。"
                }
                122 -> {
                    return "休暇をいただきたいと思っております。\n" + "申し訳ありませんが、休暇中、よろしくお願いいたします。\n" + "ご迷惑をお掛けいたします。"
                }
                123 -> {
                    return "申し訳ありませんでした。\n" + "私のミスでご迷惑をお掛けしました。\n" + "今後、十分に注意いたします。\n" + "申し訳ありませんでした。"
                }
                131 -> {
                    return "遅刻して、申し訳ありません。\n" + "次回は、遅くならないように、十分に注意いたします。\n" + "すいませんでした。"
                }
                132 -> {
                    return "申し訳ありません。\n" + "休暇をいただきます。\n" + "休暇中、よろしくお願いします。"
                }
                133 -> {
                    return "申し訳ありません。\n" + "この度は、ご迷惑をお掛けいたしました。\n" + "今後十分注意いたします。"
                }
                211 -> {
                    return "この度は、大変申し訳ございませんでした。\n" + "遅刻してしまい、お詫び申し上げます。\n" + "今後、このようなことのないように十分注意いたします。\n" + "申し訳ございませんでした。"
                }
                212 -> {
                    return "申し訳ありませんが、休暇をいただきたいと思っております。\n" + "申し訳ありません、休暇中、よろしくお願いいたします。\n" + "ご迷惑をお掛けいたします。"
                }
                213 -> {
                    return "この度は、大変申し訳ございませんでした。\n" + "私のミスで、ご迷惑をお掛けし、お詫び申し上げます。\n" + "今後、このようなことのないように十分注意いたします。\n" + "申し訳ございませんでした。"
                }
                221 -> {
                    return "遅刻して、申し訳ありません。\n" + "次回は、遅くならないように十分注意いたします。\n" + "すいませんでした。"
                }
                222 -> {
                    return "申し訳ありません。\n" + "休暇をいただきます。\n" + "休暇中、よろしくお願いします。"
                }
                223 -> {
                    return "申し訳ありません。\n" + "私のミスでご迷惑をお掛けしました。\n" + "次回は、十分注意いたします。\n" + "すいませんでした。"
                }
                231 -> {
                    return "遅刻して、ごめんなさい。\n" + "次からは遅れないようにします。\n" + "ごめんなさい。"
                }
                232 -> {
                    return "すいません。\n" + "休暇をいただきます。\n" + "よろしくお願いします。"
                }
                233 -> {
                    return "すいません。\n" + "この度は、ご迷惑をお掛けいたしました。\n" + "今後十分注意いたします。"
                }
                311 -> {
                    return "本当に、ごめんなさい。\n" + "遅刻してしまい、申し訳なかったです。\n" + "今後、このようなことのないように十分注意します。\n" + "申し訳ありませんでした。"
                }
                312 -> {
                    return "本当にごめんなさい。\n" + "休暇をいただきます。\n" + "休暇中、よろしくお願いします。\n" + "申し訳ありません。"
                }
                313 -> {
                    return "本当に、ごめんなさい。\n" + "私のミスで、迷惑をかけてしまい、申し訳なかったです。\n" + "今後、このようなことのないように十分注意します。\n" + "申し訳ありませんでした。"
                }
                321 -> {
                    return "遅刻して、ごめんなさい。\n" + "次からは遅れないようにします。\n" + "ごめんなさい。"
                }
                322 -> {
                    return "ごめんね。\n" + "休暇をいただきます。\n" + "よろしくお願いします。"
                }
                323 -> {
                    return "ごめんなさい。\n" + "私のミスで、迷惑をお掛けしました。\n" + "次回は、十分注意いたします。"
                }
                331 -> {
                    return "遅刻ごめんなさい。\n" + "遅れないようにします。"
                }
                332 -> {
                    return "お休みします。\n" + "よろしくね。"
                }
                333 -> {
                    return "ごめんなさい。\n" + "私のミスです。\n" + "すいませんでした。"
                }
            }
        }
        return ""
    }

    fun convert(tmp: List<Int>): Int {
        return tmp[0] * 100 + tmp[1] * 10 + tmp[2]
    }

    fun getSelected(index: Int): Int {
        return _selected.value!!.toMutableList()[index]
    }
}