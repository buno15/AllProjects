package jp.gr.java_conf.bunooboi.englishtower;

public class Word {
    final String WORD;                    // 単語
    final String HEAD;                    // 頭文字
    final String HEAD_JP;//頭文字、日本語
    final String JAPANESE;                // 日本語
    final int IMAGE;                    // 画像パス
    final int ID;                        // 品詞ID

    static final int NOUN = 0;    // 名詞
    static final int PRONOUN = 1;    // 代名詞
    static final int VERB = 2;    // 動詞
    static final int ADJECTIVE = 3;    // 形容詞
    static final int ADVERB = 4;    // 副詞
    static final int ARTICLE = 5;    // 冠詞
    static final int AUXILIARY_VERB = 6;    // 助動詞
    static final int PREPOSITION = 7;    // 前置詞
    static final int INTERROGATIVE = 8;    // 疑問詞
    static final int INTERJECTION = 9;    // 感動詞
    static final int CONJUNCTION = 10;    // 接続詞

    public Word(String word, String japanese, String head_jp, int image, int id) {
        WORD = word;
        JAPANESE = japanese;
        HEAD_JP = head_jp;
        HEAD = WORD.substring(0, 1);
        IMAGE = image;
        ID = id;
    }

    public int getID() {
        return ID;
    }
}
