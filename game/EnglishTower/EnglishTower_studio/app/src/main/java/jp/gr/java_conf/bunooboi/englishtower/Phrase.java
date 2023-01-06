package jp.gr.java_conf.bunooboi.englishtower;

public class Phrase {
    final String PHRASE;        // フレーズ
    final String HEAD;        // 頭文字
    final String JAPANESE;    // 日本語
    final String WORD[];        // 単語区切り
    final int IMAGES[];    // イメージ

    public Phrase(String phrase, String japanese, int images[]) {
        PHRASE = phrase;
        JAPANESE = japanese;
        HEAD = PHRASE.substring(0, 1);
        WORD = PHRASE.split(" ");
        IMAGES = images;
    }
}
