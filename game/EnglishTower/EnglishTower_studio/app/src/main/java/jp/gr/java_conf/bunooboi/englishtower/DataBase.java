package jp.gr.java_conf.bunooboi.englishtower;

import java.util.ArrayList;

public class DataBase {
    static ArrayList<ArrayList<Word>> word = new ArrayList<ArrayList<Word>>();
    static ArrayList<ArrayList<Phrase>> phrase = new ArrayList<ArrayList<Phrase>>();
    static ArrayList<Character> character = new ArrayList<Character>();
    static int images[];

//listセッターfor文size指定、最終的に変更必要

    public static void clear() {
        word.clear();
        phrase.clear();
        character.clear();
    }

    public static void createDataBase() {
        for (int i = 0; i < 100; i++) {
            word.add(new ArrayList<Word>());
            phrase.add(new ArrayList<Phrase>());
        }
        //startword
        word.get(0).add(new Word("I", "私は・私が", "わ", R.drawable.i, Word.PRONOUN));
        word.get(0).add(new Word("ache", "痛む", "い", R.drawable.none, Word.VERB));
        word.get(0).add(new Word("am", "(一人称)be動詞", "い", R.drawable.none, Word.VERB));
        word.get(0).add(new Word("breathe", "呼吸する・息をする", "こ", R.drawable.none, Word.VERB));
        word.get(0).add(new Word("die", "死ぬ", "し", R.drawable.none, Word.VERB));
        word.get(0).add(new Word("drink", "飲む", "の", R.drawable.none, Word.VERB));
        word.get(0).add(new Word("eat", "食べる", "た", R.drawable.none, Word.VERB));
        word.get(0).add(new Word("help", "助ける・手伝う", "た", R.drawable.help, Word.VERB));
        word.get(0).add(new Word("human", "人間", "に", R.drawable.human, Word.NOUN));
        word.get(0).add(new Word("man", "男性", "た", R.drawable.man, Word.NOUN));
        word.get(0).add(new Word("me", "私を・私に", "わ", R.drawable.none, Word.PRONOUN));
        word.get(0).add(new Word("mine", "私のもの", "わ", R.drawable.none, Word.PRONOUN));
        word.get(0).add(new Word("my", "私の", "わ", R.drawable.none, Word.PRONOUN));
        word.get(0).add(new Word("need", "必要とする", "ひ", R.drawable.need, Word.VERB));
        word.get(0).add(new Word("oxygen", "酸素", "さ", R.drawable.none, Word.NOUN));
        word.get(0).add(new Word("pain", "痛み", "い", R.drawable.none, Word.NOUN));
        word.get(0).add(new Word("people", "人々", "ひ", R.drawable.none, Word.NOUN));
        word.get(0).add(new Word("respiration", "呼吸", "こ", R.drawable.none, Word.NOUN));
        word.get(0).add(new Word("water", "水", "み", R.drawable.none, Word.NOUN));
        word.get(0).add(new Word("woman", "女性", "し", R.drawable.woman, Word.NOUN));
        word.get(1).add(new Word("be", "ある・です", "あ", R.drawable.none, Word.VERB));
        word.get(1).add(new Word("be", "される・されている", "さ", R.drawable.none, Word.AUXILIARY_VERB));
        word.get(1).add(new Word("come", "来る・やってくる", "く", R.drawable.none, Word.VERB));
        word.get(1).add(new Word("do", "する", "す", R.drawable.none, Word.VERB));
        word.get(1).add(new Word("get", "もらう・受け取る", "も", R.drawable.none, Word.VERB));
        word.get(1).add(new Word("give", "あげる・与える", "あ", R.drawable.none, Word.VERB));
        word.get(1).add(new Word("go", "行く・向かう", "い", R.drawable.none, Word.VERB));
        word.get(1).add(new Word("keep", "取っておく", "と", R.drawable.none, Word.VERB));
        word.get(1).add(new Word("let", "させる", "さ", R.drawable.none, Word.VERB));
        word.get(1).add(new Word("make", "作る・組み立てる", "つ", R.drawable.none, Word.VERB));
        word.get(10).add(new Word("he", "彼は・彼が", "か", R.drawable.he, Word.PRONOUN));
        word.get(10).add(new Word("she", "彼女は", "か", R.drawable.none, Word.PRONOUN));
        word.get(10).add(new Word("such", "こんな・そんな", "こ", R.drawable.none, Word.ADJECTIVE));
        word.get(10).add(new Word("such", "こんな事・そんな事", "こ", R.drawable.none, Word.PRONOUN));
        word.get(10).add(new Word("that", "あの", "あ", R.drawable.none, Word.ADJECTIVE));
        word.get(10).add(new Word("that", "あれ", "あ", R.drawable.none, Word.PRONOUN));
        word.get(10).add(new Word("this", "この", "こ", R.drawable.none, Word.ADJECTIVE));
        word.get(10).add(new Word("this", "これ", "こ", R.drawable.none, Word.PRONOUN));
        word.get(10).add(new Word("who", "だれ", "た", R.drawable.none, Word.INTERROGATIVE));
        word.get(10).add(new Word("you", "あなた(たち)は・あなた(たち)を", "あ", R.drawable.none, Word.PRONOUN));
        word.get(11).add(new Word("and", "～と・および", "と", R.drawable.none, Word.CONJUNCTION));
        word.get(11).add(new Word("because", "なぜなら", "な", R.drawable.none, Word.CONJUNCTION));
        word.get(11).add(new Word("but", "ただ・ほんの", "た", R.drawable.none, Word.ADVERB));
        word.get(11).add(new Word("but", "だが・しかし", "た", R.drawable.none, Word.CONJUNCTION));
        word.get(11).add(new Word("how", "どんにふうに・どんな具合に", "と", R.drawable.none, Word.INTERROGATIVE));
        word.get(11).add(new Word("if", "もしも～ならば", "も", R.drawable.none, Word.CONJUNCTION));
        word.get(11).add(new Word("or", "または・あるいは", "ま", R.drawable.none, Word.CONJUNCTION));
        word.get(11).add(new Word("though", "～だけれども", "た", R.drawable.none, Word.CONJUNCTION));
        word.get(11).add(new Word("when", "いつ", "い", R.drawable.none, Word.INTERROGATIVE));
        word.get(11).add(new Word("where", "どこに・どこへ", "と", R.drawable.none, Word.INTERROGATIVE));
        word.get(11).add(new Word("where", "～する・～した", "す", R.drawable.none, Word.CONJUNCTION));
        word.get(11).add(new Word("while", "～する間・～する限り", "す", R.drawable.none, Word.CONJUNCTION));
        word.get(11).add(new Word("why", "なぜ・どうして", "な", R.drawable.none, Word.INTERROGATIVE));
        word.get(12).add(new Word("again", "再び・また", "ふ", R.drawable.none, Word.ADVERB));
        word.get(12).add(new Word("ever", "かつて・いつか", "か", R.drawable.none, Word.ADVERB));
        word.get(12).add(new Word("far", "遠くに・遠くへ", "と", R.drawable.none, Word.ADVERB));
        word.get(12).add(new Word("forward", "先へ・前方へ", "さ", R.drawable.none, Word.ADVERB));
        word.get(12).add(new Word("forward", "前方の", "ぜ", R.drawable.none, Word.ADJECTIVE));
        word.get(12).add(new Word("here", "ここ・ここに", "こ", R.drawable.none, Word.ADVERB));
        word.get(12).add(new Word("near", "の近くに", "の", R.drawable.none, Word.PREPOSITION));
        word.get(12).add(new Word("near", "近い・親しい", "ち", R.drawable.none, Word.ADJECTIVE));
        word.get(12).add(new Word("near", "近く", "ち", R.drawable.none, Word.ADVERB));
        word.get(12).add(new Word("now", "今・現在", "い", R.drawable.none, Word.ADVERB));
        word.get(13).add(new Word("out", "外に", "そ", R.drawable.none, Word.ADVERB));
        word.get(13).add(new Word("out", "外部・外側", "か", R.drawable.none, Word.NOUN));
        word.get(13).add(new Word("still", "それでも", "そ", R.drawable.none, Word.ADVERB));
        word.get(13).add(new Word("still", "静かな", "し", R.drawable.none, Word.ADJECTIVE));
        word.get(13).add(new Word("still", "静める・なだめる", "し", R.drawable.none, Word.VERB));
        word.get(13).add(new Word("then", "その時は・あの時は", "そ", R.drawable.none, Word.ADVERB));
        word.get(13).add(new Word("there", "そこ(に)・あそこ(に)", "そ", R.drawable.none, Word.ADVERB));
        word.get(13).add(new Word("there", "そら！・それ見ろ！", "そ", R.drawable.none, Word.INTERJECTION));
        word.get(13).add(new Word("together", "共に・一緒に", "と", R.drawable.none, Word.ADVERB));
        word.get(13).add(new Word("well", "満足に・申し分なく", "ま", R.drawable.none, Word.ADVERB));
        word.get(14).add(new Word("almost", "ほとんど・だいたい", "ほ", R.drawable.none, Word.ADVERB));
        word.get(14).add(new Word("enough", "十分", "し", R.drawable.none, Word.PRONOUN));
        word.get(14).add(new Word("enough", "十分な・不足のない", "し", R.drawable.none, Word.ADJECTIVE));
        word.get(14).add(new Word("even", "平らな・平坦な", "た", R.drawable.none, Word.ADJECTIVE));
        word.get(14).add(new Word("even", "～でさえ", "て", R.drawable.none, Word.ADVERB));
        word.get(14).add(new Word("not", "～でない", "て", R.drawable.none, Word.ADVERB));
        word.get(14).add(new Word("only", "たった～・わずか", "た", R.drawable.none, Word.ADVERB));
        word.get(14).add(new Word("only", "唯一の", "ゆ", R.drawable.none, Word.ADJECTIVE));
        word.get(14).add(new Word("quite", "まったく・すっかり", "ま", R.drawable.none, Word.ADVERB));
        word.get(14).add(new Word("so", "そこで・それで", "そ", R.drawable.none, Word.CONJUNCTION));
        word.get(14).add(new Word("so", "そのように・このように", "そ", R.drawable.none, Word.ADVERB));
        word.get(14).add(new Word("tomorrow", "明日", "あ", R.drawable.none, Word.NOUN));
        word.get(14).add(new Word("tomorrow", "明日は", "あ", R.drawable.none, Word.ADVERB));
        word.get(14).add(new Word("very", "非常に・大いに", "ひ", R.drawable.none, Word.ADVERB));
        word.get(14).add(new Word("yesterday", "昨日", "き", R.drawable.none, Word.NOUN));
        word.get(14).add(new Word("yesterday", "昨日は", "き", R.drawable.none, Word.ADVERB));
        word.get(15).add(new Word("east", "東", "ひ", R.drawable.none, Word.NOUN));
        word.get(15).add(new Word("east", "東の", "ひ", R.drawable.none, Word.ADJECTIVE));
        word.get(15).add(new Word("north", "北", "き", R.drawable.none, Word.NOUN));
        word.get(15).add(new Word("north", "北の", "き", R.drawable.none, Word.ADJECTIVE));
        word.get(15).add(new Word("please", "どうぞ・お願いします", "と", R.drawable.none, Word.ADVERB));
        word.get(15).add(new Word("please", "喜ばせる・楽しませる", "よ", R.drawable.none, Word.VERB));
        word.get(15).add(new Word("south", "南", "み", R.drawable.none, Word.NOUN));
        word.get(15).add(new Word("south", "南の", "み", R.drawable.none, Word.ADJECTIVE));
        word.get(15).add(new Word("west", "西", "に", R.drawable.none, Word.NOUN));
        word.get(15).add(new Word("west", "西の", "に", R.drawable.none, Word.ADJECTIVE));
        word.get(15).add(new Word("yes", "はい", "は", R.drawable.none, Word.ADVERB));
        word.get(16).add(new Word("angry", "怒って", "お", R.drawable.none, Word.ADJECTIVE));
        word.get(16).add(new Word("comfort", "慰める", "な", R.drawable.none, Word.VERB));
        word.get(16).add(new Word("credit", "クレジット・信用", "く", R.drawable.none, Word.NOUN));
        word.get(16).add(new Word("discussion", "討論・話し合い", "と", R.drawable.none, Word.NOUN));
        word.get(16).add(new Word("exchange", "交換する", "こ", R.drawable.none, Word.VERB));
        word.get(16).add(new Word("shoe", "靴", "く", R.drawable.none, Word.NOUN));
        word.get(16).add(new Word("soap", "せっけん", "せ", R.drawable.none, Word.NOUN));
        word.get(16).add(new Word("straight", "まっすぐな", "ま", R.drawable.none, Word.ADJECTIVE));
        word.get(16).add(new Word("tree", "木", "き", R.drawable.none, Word.NOUN));
        word.get(17).add(new Word("crack", "亀裂", "く", R.drawable.none, Word.NOUN));
        word.get(17).add(new Word("low", "低く", "ひ", R.drawable.none, Word.ADVERB));
        word.get(17).add(new Word("manager", "支配人・経営者", "し", R.drawable.none, Word.NOUN));
        word.get(17).add(new Word("serious", "真面目な", "ま", R.drawable.none, Word.ADJECTIVE));
        word.get(17).add(new Word("stand", "立場・見解・位置", "た", R.drawable.stand_1, Word.NOUN));
        word.get(17).add(new Word("way", "道路", "ど", R.drawable.none, Word.NOUN));
        word.get(18).add(new Word("abstract", "抽象", "ち", R.drawable.none, Word.NOUN));
        word.get(18).add(new Word("argument", "議論", "ぎ", R.drawable.none, Word.NOUN));
        word.get(18).add(new Word("camera", "カメラ", "か", R.drawable.none, Word.NOUN));
        word.get(18).add(new Word("change", "変化・移り変わり", "へ", R.drawable.none, Word.NOUN));
        word.get(18).add(new Word("coat", "コート", "こ", R.drawable.none, Word.NOUN));
        word.get(18).add(new Word("fowl", "鳥類・にわとり", "と", R.drawable.none, Word.NOUN));
        word.get(18).add(new Word("guide", "案内人", "あ", R.drawable.none, Word.NOUN));
        word.get(18).add(new Word("office", "事務所・オフィス", "し", R.drawable.none, Word.NOUN));
        word.get(18).add(new Word("porter", "ポーター", "ほ", R.drawable.none, Word.NOUN));
        word.get(18).add(new Word("procedure", "手順", "て", R.drawable.none, Word.NOUN));
        word.get(18).add(new Word("speak", "話す・ものを言う", "は", R.drawable.speak, Word.VERB));
        word.get(18).add(new Word("wing", "翼", "つ", R.drawable.none, Word.NOUN));
        word.get(18).add(new Word("worm", "虫", "む", R.drawable.none, Word.NOUN));
        word.get(18).add(new Word("yard", "庭・中庭", "に", R.drawable.none, Word.NOUN));
        word.get(19).add(new Word("behavior", "態度・行動", "た", R.drawable.none, Word.NOUN));
        word.get(19).add(new Word("boiling", "煮え立つ", "に", R.drawable.none, Word.ADJECTIVE));
        word.get(19).add(new Word("boy", "少年", "し", R.drawable.none, Word.NOUN));
        word.get(19).add(new Word("brother", "兄弟", "き", R.drawable.none, Word.NOUN));
        word.get(19).add(new Word("copy", "複写", "ふ", R.drawable.none, Word.NOUN));
        word.get(19).add(new Word("growth", "成長", "せ", R.drawable.none, Word.NOUN));
        word.get(19).add(new Word("idea", "思いつき・アイディア", "あ", R.drawable.none, Word.NOUN));
        word.get(19).add(new Word("last", "最後の", "さ", R.drawable.none, Word.ADJECTIVE));
        word.get(19).add(new Word("learning", "学ぶこと", "ま", R.drawable.none, Word.NOUN));
        word.get(19).add(new Word("loud", "騒々しい", "そ", R.drawable.none, Word.ADJECTIVE));
        word.get(19).add(new Word("metal", "金属", "き", R.drawable.none, Word.NOUN));
        word.get(19).add(new Word("nose", "鼻", "は", R.drawable.none, Word.NOUN));
        word.get(19).add(new Word("road", "道路", "と", R.drawable.none, Word.NOUN));
        word.get(19).add(new Word("size", "大きさ・規模", "お", R.drawable.none, Word.NOUN));
        word.get(19).add(new Word("stocking", "ストッキング", "す", R.drawable.none, Word.NOUN));
        word.get(19).add(new Word("switch", "スイッチ・転換・変更", "す", R.drawable.none, Word.NOUN));
        word.get(19).add(new Word("switch", "変える・切り替える", "か", R.drawable.none, Word.VERB));
        word.get(19).add(new Word("teach", "教える", "お", R.drawable.teach, Word.VERB));
        word.get(2).add(new Word("have", "持っている・所有する", "も", R.drawable.none, Word.VERB));
        word.get(2).add(new Word("may", "５月", "こ", R.drawable.none, Word.NOUN));
        word.get(2).add(new Word("may", "～してもよろしい", "し", R.drawable.none, Word.AUXILIARY_VERB));
        word.get(2).add(new Word("put", "置く・載せる", "お", R.drawable.none, Word.VERB));
        word.get(2).add(new Word("say", "言い分・発言権", "い", R.drawable.say, Word.NOUN));
        word.get(2).add(new Word("say", "話す・述べる", "は", R.drawable.say, Word.VERB));
        word.get(2).add(new Word("see", "見る・見える", "み", R.drawable.see, Word.VERB));
        word.get(2).add(new Word("seem", "～のように思われる", "の", R.drawable.none, Word.VERB));
        word.get(2).add(new Word("send", "送る・届ける", "お", R.drawable.none, Word.VERB));
        word.get(2).add(new Word("take", "取る・つかむ", "と", R.drawable.none, Word.VERB));
        word.get(20).add(new Word("fear", "恐怖", "き", R.drawable.none, Word.NOUN));
        word.get(20).add(new Word("grass", "草", "く", R.drawable.none, Word.NOUN));
        word.get(20).add(new Word("join", "加わる・結合する", "く", R.drawable.join, Word.VERB));
        word.get(20).add(new Word("mountain", "山", "や", R.drawable.none, Word.NOUN));
        word.get(20).add(new Word("rain", "雨", "あ", R.drawable.none, Word.NOUN));
        word.get(20).add(new Word("relational", "関係のある", "か", R.drawable.none, Word.ADJECTIVE));
        word.get(20).add(new Word("value", "価値", "か", R.drawable.none, Word.NOUN));
        word.get(20).add(new Word("worry", "心配する・苦しむ・悩む", "し", R.drawable.worry, Word.VERB));
        word.get(20).add(new Word("your", "あなた(たち)の", "あ", R.drawable.none, Word.PRONOUN));
        word.get(21).add(new Word("army", "陸軍", "り", R.drawable.none, Word.NOUN));
        word.get(21).add(new Word("automatic", "自動の", "じ", R.drawable.none, Word.ADJECTIVE));
        word.get(21).add(new Word("bed", "ベッド", "へ", R.drawable.none, Word.NOUN));
        word.get(21).add(new Word("expert", "熟練者", "し", R.drawable.none, Word.NOUN));
        word.get(21).add(new Word("form", "形", "か", R.drawable.none, Word.NOUN));
        word.get(21).add(new Word("live", "生きている", "い", R.drawable.none, Word.ADJECTIVE));
        word.get(21).add(new Word("mine", "採掘する", "さ", R.drawable.none, Word.VERB));
        word.get(21).add(new Word("parent", "親", "お", R.drawable.none, Word.NOUN));
        word.get(21).add(new Word("pencil", "鉛筆", "え", R.drawable.none, Word.NOUN));
        word.get(21).add(new Word("scale", "登る", "の", R.drawable.none, Word.VERB));
        word.get(21).add(new Word("wide", "幅の広い", "は", R.drawable.none, Word.ADJECTIVE));
        word.get(22).add(new Word("act", "行為・行い", "こ", R.drawable.none, Word.NOUN));
        word.get(22).add(new Word("answer", "答え・返事・回答", "こ", R.drawable.answer_1, Word.NOUN));
        word.get(22).add(new Word("dedicate", "ささげる", "さ", R.drawable.none, Word.VERB));
        word.get(22).add(new Word("family", "家族", "か", R.drawable.none, Word.NOUN));
        word.get(22).add(new Word("fork", "フォーク", "ふ", R.drawable.none, Word.NOUN));
        word.get(22).add(new Word("good", "良い", "よ", R.drawable.none, Word.ADJECTIVE));
        word.get(22).add(new Word("gray", "灰色の", "は", R.drawable.none, Word.ADJECTIVE));
        word.get(22).add(new Word("lock", "鍵", "か", R.drawable.none, Word.NOUN));
        word.get(22).add(new Word("love", "愛する", "あ", R.drawable.none, Word.VERB));
        word.get(22).add(new Word("machine", "機械", "き", R.drawable.none, Word.NOUN));
        word.get(22).add(new Word("quality", "質", "し", R.drawable.none, Word.NOUN));
        word.get(22).add(new Word("sail", "帆", "ほ", R.drawable.none, Word.NOUN));
        word.get(22).add(new Word("table", "テーブル", "て", R.drawable.none, Word.NOUN));
        word.get(23).add(new Word("bite", "噛む", "か", R.drawable.none, Word.VERB));
        word.get(23).add(new Word("both", "両方", "り", R.drawable.none, Word.PRONOUN));
        word.get(23).add(new Word("dress", "服装", "ふ", R.drawable.none, Word.NOUN));
        word.get(23).add(new Word("grandchiled", "孫", "ま", R.drawable.none, Word.NOUN));
        word.get(23).add(new Word("know", "知る", "し", R.drawable.none, Word.VERB));
        word.get(23).add(new Word("leg", "脚", "あ", R.drawable.none, Word.NOUN));
        word.get(23).add(new Word("mosque", "モスク", "も", R.drawable.none, Word.NOUN));
        word.get(23).add(new Word("news", "知らせ・報道", "し", R.drawable.none, Word.NOUN));
        word.get(23).add(new Word("polish", "磨く", "み", R.drawable.none, Word.VERB));
        word.get(23).add(new Word("sneeze", "くしゃみ", "く", R.drawable.none, Word.NOUN));
        word.get(23).add(new Word("street", "通り", "と", R.drawable.none, Word.NOUN));
        word.get(23).add(new Word("toe", "足指", "あ", R.drawable.none, Word.NOUN));
        word.get(24).add(new Word("acid", "すっぱい", "す", R.drawable.none, Word.ADJECTIVE));
        word.get(24).add(new Word("apple", "りんご", "り", R.drawable.apple, Word.NOUN));
        word.get(24).add(new Word("cat", "猫", "ね", R.drawable.none, Word.NOUN));
        word.get(24).add(new Word("cotton", "コットン・綿", "こ", R.drawable.none, Word.NOUN));
        word.get(24).add(new Word("first", "最初の", "さ", R.drawable.none, Word.ADJECTIVE));
        word.get(24).add(new Word("horse", "馬", "う", R.drawable.none, Word.NOUN));
        word.get(24).add(new Word("ill", "病気で", "び", R.drawable.none, Word.ADJECTIVE));
        word.get(24).add(new Word("is", "(三人称)be動詞", "さ", R.drawable.none, Word.VERB));
        word.get(24).add(new Word("linen", "リンネル", "り", R.drawable.none, Word.NOUN));
        word.get(24).add(new Word("male", "男性の", "だ", R.drawable.none, Word.ADJECTIVE));
        word.get(24).add(new Word("net", "網", "あ", R.drawable.none, Word.NOUN));
        word.get(24).add(new Word("our", "私たちの", "わ", R.drawable.none, Word.PRONOUN));
        word.get(24).add(new Word("physical", "物理的な", "ぶ", R.drawable.none, Word.ADJECTIVE));
        word.get(24).add(new Word("prose", "散文", "さ", R.drawable.none, Word.NOUN));
        word.get(24).add(new Word("question", "質問・問", "し", R.drawable.none, Word.NOUN));
        word.get(24).add(new Word("return", "帰り・帰還", "か", R.drawable.r_return, Word.NOUN));
        word.get(24).add(new Word("ride", "乗る・馬乗りになる", "の", R.drawable.none, Word.VERB));
        word.get(24).add(new Word("seat", "席", "せ", R.drawable.none, Word.NOUN));
        word.get(24).add(new Word("secret", "秘密の", "ひ", R.drawable.none, Word.ADJECTIVE));
        word.get(24).add(new Word("secretary", "秘書", "ひ", R.drawable.none, Word.NOUN));
        word.get(24).add(new Word("surprise", "驚かす", "お", R.drawable.none, Word.VERB));
        word.get(24).add(new Word("wash", "洗う", "あ", R.drawable.none, Word.VERB));
        word.get(25).add(new Word("brush", "ブラシ", "ぶ", R.drawable.none, Word.NOUN));
        word.get(25).add(new Word("expansion", "拡大・拡張", "か", R.drawable.none, Word.NOUN));
        word.get(25).add(new Word("government", "政治", "せ", R.drawable.none, Word.NOUN));
        word.get(25).add(new Word("harbor", "港", "み", R.drawable.none, Word.NOUN));
        word.get(25).add(new Word("loose", "自由な", "じ", R.drawable.none, Word.ADJECTIVE));
        word.get(25).add(new Word("low", "低い", "ひ", R.drawable.none, Word.ADJECTIVE));
        word.get(25).add(new Word("pilot", "操縦士", "そ", R.drawable.none, Word.NOUN));
        word.get(25).add(new Word("push", "押す", "お", R.drawable.none, Word.VERB));
        word.get(25).add(new Word("return", "帰る・戻る", "か", R.drawable.r_return, Word.VERB));
        word.get(25).add(new Word("star", "星", "ほ", R.drawable.none, Word.NOUN));
        word.get(26).add(new Word("art", "芸術", "げ", R.drawable.none, Word.NOUN));
        word.get(26).add(new Word("chair", "いす", "い", R.drawable.none, Word.NOUN));
        word.get(26).add(new Word("finger", "指", "ゆ", R.drawable.none, Word.NOUN));
        word.get(26).add(new Word("future", "未来の", "み", R.drawable.none, Word.ADJECTIVE));
        word.get(26).add(new Word("hole", "穴", "あ", R.drawable.none, Word.NOUN));
        word.get(26).add(new Word("insurance", "保険", "ほ", R.drawable.none, Word.NOUN));
        word.get(26).add(new Word("management", "管理・経営", "か", R.drawable.none, Word.NOUN));
        word.get(26).add(new Word("mouth", "口", "く", R.drawable.none, Word.NOUN));
        word.get(26).add(new Word("orange", "オレンジ・だいだい色", "お", R.drawable.none, Word.NOUN));
        word.get(26).add(new Word("racket", "ラケット", "ら", R.drawable.none, Word.NOUN));
        word.get(26).add(new Word("thin", "薄い", "う", R.drawable.none, Word.ADJECTIVE));
        word.get(26).add(new Word("whip", "むちを打つ", "む", R.drawable.none, Word.VERB));
        word.get(27).add(new Word("architecture", "建築", "け", R.drawable.none, Word.NOUN));
        word.get(27).add(new Word("arrive", "着く", "つ", R.drawable.none, Word.VERB));
        word.get(27).add(new Word("baby", "赤ちゃん", "あ", R.drawable.none, Word.NOUN));
        word.get(27).add(new Word("broken", "壊れた", "こ", R.drawable.none, Word.ADJECTIVE));
        word.get(27).add(new Word("class", "階級の・同級の", "か", R.drawable.none, Word.ADJECTIVE));
        word.get(27).add(new Word("clear", "明るい", "あ", R.drawable.none, Word.ADJECTIVE));
        word.get(27).add(new Word("crush", "押しつぶす・クラッシュ", "お", R.drawable.none, Word.VERB));
        word.get(27).add(new Word("dependent", "従属関係の", "じ", R.drawable.none, Word.ADJECTIVE));
        word.get(27).add(new Word("division", "分割", "ふ", R.drawable.none, Word.NOUN));
        word.get(27).add(new Word("guest", "客・来賓", "き", R.drawable.none, Word.NOUN));
        word.get(27).add(new Word("mass", "密集", "み", R.drawable.none, Word.NOUN));
        word.get(27).add(new Word("military", "軍隊の", "ぐ", R.drawable.none, Word.ADJECTIVE));
        word.get(27).add(new Word("music", "音楽", "お", R.drawable.none, Word.NOUN));
        word.get(27).add(new Word("pocket", "ポケット", "ぽ", R.drawable.none, Word.NOUN));
        word.get(27).add(new Word("river", "川", "か", R.drawable.none, Word.NOUN));
        word.get(27).add(new Word("smile", "わらう・にっこりする", "わ", R.drawable.smile, Word.VERB));
        word.get(27).add(new Word("support", "支える", "さ", R.drawable.none, Word.VERB));
        word.get(28).add(new Word("attack", "攻撃する", "こ", R.drawable.none, Word.VERB));
        word.get(28).add(new Word("board", "乗車する", "じ", R.drawable.none, Word.NOUN));
        word.get(28).add(new Word("care", "気にかけること", "き", R.drawable.none, Word.NOUN));
        word.get(28).add(new Word("dead", "死んだ", "し", R.drawable.none, Word.ADJECTIVE));
        word.get(28).add(new Word("library", "図書館", "と", R.drawable.none, Word.NOUN));
        word.get(28).add(new Word("position", "位置・場所", "い", R.drawable.none, Word.NOUN));
        word.get(28).add(new Word("root", "根・根元", "ね", R.drawable.none, Word.NOUN));
        word.get(28).add(new Word("true", "真実の", "し", R.drawable.none, Word.ADJECTIVE));
        word.get(28).add(new Word("wood", "木材", "も", R.drawable.none, Word.NOUN));
        word.get(29).add(new Word("brick", "レンガ", "れ", R.drawable.none, Word.NOUN));
        word.get(29).add(new Word("different", "異なった", "こ", R.drawable.none, Word.ADJECTIVE));
        word.get(29).add(new Word("fall", "落ちる・落下する", "お", R.drawable.fall, Word.VERB));
        word.get(29).add(new Word("hear", "聞く・聞こえる", "き", R.drawable.hear, Word.VERB));
        word.get(29).add(new Word("jelly", "ゼリー", "せ", R.drawable.none, Word.NOUN));
        word.get(29).add(new Word("religon", "宗教", "し", R.drawable.none, Word.NOUN));
        word.get(29).add(new Word("second", "２番目の", "に", R.drawable.none, Word.ADJECTIVE));
        word.get(29).add(new Word("uncle", "おじ", "お", R.drawable.none, Word.NOUN));
        word.get(29).add(new Word("yellow", "黄色い", "き", R.drawable.none, Word.ADJECTIVE));
        word.get(3).add(new Word("about", "およそ・ほぼ", "お", R.drawable.none, Word.ADVERB));
        word.get(3).add(new Word("about", "～について・～に対して", "に", R.drawable.none, Word.PREPOSITION));
        word.get(3).add(new Word("across", "横切って・渡して", "よ", R.drawable.none, Word.ADVERB));
        word.get(3).add(new Word("across", "～を横切って・～を渡って", "を", R.drawable.none, Word.PREPOSITION));
        word.get(3).add(new Word("after", "あとに", "あ", R.drawable.none, Word.ADVERB));
        word.get(3).add(new Word("after", "後に・してから", "あ", R.drawable.none, Word.CONJUNCTION));
        word.get(3).add(new Word("after", "～の後に", "の", R.drawable.none, Word.PREPOSITION));
        word.get(3).add(new Word("against", "～に反対して・～に逆らって", "に", R.drawable.none, Word.PREPOSITION));
        word.get(3).add(new Word("will", "意志・決意", "い", R.drawable.none, Word.NOUN));
        word.get(3).add(new Word("will", "～だろう", "た", R.drawable.none, Word.AUXILIARY_VERB));
        word.get(30).add(new Word("collect", "集める", "あ", R.drawable.none, Word.VERB));
        word.get(30).add(new Word("conscious", "意識して", "い", R.drawable.none, Word.ADJECTIVE));
        word.get(30).add(new Word("custom", "風習・習慣", "ふ", R.drawable.none, Word.NOUN));
        word.get(30).add(new Word("drink", "飲み物", "の", R.drawable.none, Word.NOUN));
        word.get(30).add(new Word("earth", "地球", "ち", R.drawable.none, Word.NOUN));
        word.get(30).add(new Word("grain", "穀物", "こ", R.drawable.none, Word.NOUN));
        word.get(30).add(new Word("interest", "関心・興味", "か", R.drawable.none, Word.NOUN));
        word.get(30).add(new Word("name", "名前", "な", R.drawable.none, Word.NOUN));
        word.get(30).add(new Word("property", "財産", "さ", R.drawable.none, Word.NOUN));
        word.get(30).add(new Word("slow", "遅い", "お", R.drawable.none, Word.ADJECTIVE));
        word.get(30).add(new Word("strong", "強い", "つ", R.drawable.none, Word.ADJECTIVE));
        word.get(30).add(new Word("verse", "詩", "し", R.drawable.none, Word.NOUN));
        word.get(30).add(new Word("view", "見える", "み", R.drawable.none, Word.VERB));
        word.get(31).add(new Word("chain", "鎖", "く", R.drawable.none, Word.NOUN));
        word.get(31).add(new Word("cloud", "雲", "く", R.drawable.none, Word.NOUN));
        word.get(31).add(new Word("grandfather", "祖父", "そ", R.drawable.none, Word.NOUN));
        word.get(31).add(new Word("husband", "夫", "お", R.drawable.none, Word.NOUN));
        word.get(31).add(new Word("listen", "聞く・聞こうとする", "き", R.drawable.listen, Word.VERB));
        word.get(31).add(new Word("poor", "貧しい", "ま", R.drawable.none, Word.ADJECTIVE));
        word.get(31).add(new Word("reason", "推論する", "す", R.drawable.none, Word.VERB));
        word.get(32).add(new Word("break", "壊す・砕く", "こ", R.drawable.none, Word.VERB));
        word.get(32).add(new Word("direction", "方向・方角", "ほ", R.drawable.none, Word.NOUN));
        word.get(32).add(new Word("eye", "目", "め", R.drawable.none, Word.NOUN));
        word.get(32).add(new Word("front", "前部・正面", "せ", R.drawable.none, Word.NOUN));
        word.get(32).add(new Word("her", "彼女の・彼女を・彼女に", "か", R.drawable.none, Word.PRONOUN));
        word.get(32).add(new Word("respect", "尊敬", "そ", R.drawable.none, Word.NOUN));
        word.get(32).add(new Word("young", "若い", "わ", R.drawable.none, Word.ADJECTIVE));
        word.get(33).add(new Word("company", "交際・付き合い", "こ", R.drawable.none, Word.NOUN));
        word.get(33).add(new Word("destruction", "破壊", "は", R.drawable.none, Word.NOUN));
        word.get(33).add(new Word("get", "もらう・受け取る", "も", R.drawable.none, Word.VERB));
        word.get(33).add(new Word("hanging", "絞首刑の", "こ", R.drawable.none, Word.ADJECTIVE));
        word.get(33).add(new Word("journey", "旅行", "り", R.drawable.none, Word.NOUN));
        word.get(33).add(new Word("mine", "鉱山", "こ", R.drawable.none, Word.NOUN));
        word.get(33).add(new Word("room", "部屋", "へ", R.drawable.none, Word.NOUN));
        word.get(33).add(new Word("servant", "使用人", "し", R.drawable.none, Word.NOUN));
        word.get(33).add(new Word("talk", "話す・話し合う", "は", R.drawable.talk, Word.VERB));
        word.get(33).add(new Word("waste", "浪費する", "ろ", R.drawable.none, Word.VERB));
        word.get(33).add(new Word("year", "年", "ね", R.drawable.none, Word.NOUN));
        word.get(34).add(new Word("advertisement", "広告", "こ", R.drawable.none, Word.NOUN));
        word.get(34).add(new Word("arch", "アーチ", "あ", R.drawable.none, Word.NOUN));
        word.get(34).add(new Word("current", "現在の", "げ", R.drawable.none, Word.ADJECTIVE));
        word.get(34).add(new Word("fly", "はえ", "は", R.drawable.none, Word.NOUN));
        word.get(34).add(new Word("give", "与える・提供する", "あ", R.drawable.none, Word.VERB));
        word.get(34).add(new Word("oil", "油", "あ", R.drawable.none, Word.NOUN));
        word.get(34).add(new Word("pin", "ピン・暗証番号", "ぴ", R.drawable.none, Word.NOUN));
        word.get(34).add(new Word("pleasure", "楽しみ・愉快", "た", R.drawable.none, Word.NOUN));
        word.get(34).add(new Word("simple", "簡単な", "か", R.drawable.none, Word.ADJECTIVE));
        word.get(34).add(new Word("slope", "坂", "さ", R.drawable.none, Word.NOUN));
        word.get(34).add(new Word("throat", "喉", "の", R.drawable.none, Word.NOUN));
        word.get(34).add(new Word("trouble", "心配事", "し", R.drawable.none, Word.NOUN));
        word.get(34).add(new Word("wax", "蝋", "ろ", R.drawable.none, Word.NOUN));
        word.get(35).add(new Word("common", "共通の", "き", R.drawable.none, Word.ADJECTIVE));
        word.get(35).add(new Word("cushion", "クッション", "く", R.drawable.none, Word.NOUN));
        word.get(35).add(new Word("cut", "切る", "き", R.drawable.none, Word.VERB));
        word.get(35).add(new Word("delicate", "繊細な", "せ", R.drawable.none, Word.ADJECTIVE));
        word.get(35).add(new Word("flat", "平らな", "た", R.drawable.none, Word.ADJECTIVE));
        word.get(35).add(new Word("laugh", "笑う", "わ", R.drawable.none, Word.VERB));
        word.get(35).add(new Word("prison", "刑務所", "け", R.drawable.none, Word.NOUN));
        word.get(35).add(new Word("sad", "悲しい", "か", R.drawable.none, Word.VERB));
        word.get(35).add(new Word("small", "小さい", "ち", R.drawable.none, Word.ADJECTIVE));
        word.get(36).add(new Word("fire", "火", "ひ", R.drawable.none, Word.NOUN));
        word.get(36).add(new Word("flame", "炎", "ほ", R.drawable.none, Word.NOUN));
        word.get(36).add(new Word("it", "それは・それを・それに", "そ", R.drawable.none, Word.PRONOUN));
        word.get(36).add(new Word("jewel", "宝石", "ほ", R.drawable.none, Word.NOUN));
        word.get(36).add(new Word("liquid", "液体", "え", R.drawable.none, Word.NOUN));
        word.get(36).add(new Word("observation", "観察", "か", R.drawable.none, Word.NOUN));
        word.get(36).add(new Word("protest", "抗議", "こ", R.drawable.none, Word.NOUN));
        word.get(36).add(new Word("sell", "売る", "う", R.drawable.none, Word.VERB));
        word.get(36).add(new Word("smoke", "煙", "け", R.drawable.none, Word.NOUN));
        word.get(36).add(new Word("station", "駅", "え", R.drawable.none, Word.NOUN));
        word.get(36).add(new Word("steel", "鋼", "は", R.drawable.none, Word.NOUN));
        word.get(36).add(new Word("waiting", "待っている", "ま", R.drawable.none, Word.ADJECTIVE));
        word.get(36).add(new Word("writing", "書くこと", "か", R.drawable.none, Word.NOUN));
        word.get(37).add(new Word("basket", "バスケット", "ば", R.drawable.none, Word.NOUN));
        word.get(37).add(new Word("butter", "バター", "ば", R.drawable.none, Word.NOUN));
        word.get(37).add(new Word("cheese", "チーズ", "ち", R.drawable.none, Word.NOUN));
        word.get(37).add(new Word("chief", "最高の", "さ", R.drawable.none, Word.ADJECTIVE));
        word.get(37).add(new Word("daughter", "娘", "む", R.drawable.none, Word.NOUN));
        word.get(37).add(new Word("hand", "手", "て", R.drawable.none, Word.NOUN));
        word.get(37).add(new Word("increase", "増加する", "そ", R.drawable.none, Word.VERB));
        word.get(37).add(new Word("nut", "ナッツ", "な", R.drawable.none, Word.NOUN));
        word.get(37).add(new Word("public", "公共の・大衆の", "こ", R.drawable.none, Word.ADJECTIVE));
        word.get(37).add(new Word("relatives", "親戚", "し", R.drawable.none, Word.NOUN));
        word.get(37).add(new Word("umbrella", "傘", "か", R.drawable.none, Word.NOUN));
        word.get(37).add(new Word("wind", "風", "か", R.drawable.none, Word.NOUN));
        word.get(38).add(new Word("car", "車", "く", R.drawable.none, Word.NOUN));
        word.get(38).add(new Word("comb", "櫛", "く", R.drawable.none, Word.NOUN));
        word.get(38).add(new Word("dust", "ごみ", "こ", R.drawable.none, Word.NOUN));
        word.get(38).add(new Word("fold", "折りたたむ", "お", R.drawable.none, Word.VERB));
        word.get(38).add(new Word("reason", "理由", "り", R.drawable.none, Word.NOUN));
        word.get(38).add(new Word("side", "側・側面", "か", R.drawable.none, Word.NOUN));
        word.get(38).add(new Word("tall", "背の高い", "せ", R.drawable.none, Word.ADJECTIVE));
        word.get(38).add(new Word("tight", "堅い・きつい", "か", R.drawable.none, Word.ADJECTIVE));
        word.get(38).add(new Word("wet", "ぬれた", "ぬ", R.drawable.none, Word.ADJECTIVE));
        word.get(39).add(new Word("bright", "明るい", "あ", R.drawable.none, Word.ADJECTIVE));
        word.get(39).add(new Word("disgust", "嫌気", "い", R.drawable.none, Word.NOUN));
        word.get(39).add(new Word("feeble", "弱々しい", "よ", R.drawable.none, Word.ADJECTIVE));
        word.get(39).add(new Word("fly", "飛ぶ", "と", R.drawable.fly, Word.VERB));
        word.get(39).add(new Word("request", "頼み", "た", R.drawable.none, Word.NOUN));
        word.get(39).add(new Word("science", "理科", "り", R.drawable.none, Word.NOUN));
        word.get(4).add(new Word("among", "～の間に・～に囲まれて", "の", R.drawable.none, Word.PREPOSITION));
        word.get(4).add(new Word("at", "～に・～で", "に", R.drawable.none, Word.PREPOSITION));
        word.get(4).add(new Word("before", "以前に・かつて", "い", R.drawable.none, Word.ADVERB));
        word.get(4).add(new Word("before", "～の前に", "の", R.drawable.none, Word.PREPOSITION));
        word.get(4).add(new Word("before", "～より前に", "よ", R.drawable.none, Word.CONJUNCTION));
        word.get(4).add(new Word("between", "の間に・～の中間で", "の", R.drawable.none, Word.PREPOSITION));
        word.get(4).add(new Word("by", "そばに・かたわらに", "そ", R.drawable.none, Word.ADVERB));
        word.get(4).add(new Word("by", "～のそばに", "の", R.drawable.none, Word.PREPOSITION));
        word.get(4).add(new Word("down", "下に", "し", R.drawable.none, Word.ADVERB));
        word.get(4).add(new Word("down", "下への", "し", R.drawable.none, Word.ADJECTIVE));
        word.get(4).add(new Word("down", "下り・下位", "く", R.drawable.none, Word.NOUN));
        word.get(4).add(new Word("down", "～を下って", "を", R.drawable.none, Word.PREPOSITION));
        word.get(40).add(new Word("fan", "扇風機・うちわ", "せ", R.drawable.fan_1, Word.NOUN));
        word.get(40).add(new Word("foolish", "愚かな", "お", R.drawable.none, Word.ADJECTIVE));
        word.get(40).add(new Word("information", "情報・知識", "し", R.drawable.none, Word.NOUN));
        word.get(40).add(new Word("light", "光", "ひ", R.drawable.none, Word.NOUN));
        word.get(40).add(new Word("opposite", "反対の", "は", R.drawable.none, Word.ADJECTIVE));
        word.get(40).add(new Word("pen", "ペン", "へ", R.drawable.none, Word.NOUN));
        word.get(40).add(new Word("pig", "豚", "ぶ", R.drawable.none, Word.NOUN));
        word.get(40).add(new Word("power", "力", "ち", R.drawable.none, Word.NOUN));
        word.get(40).add(new Word("social", "社会の・社会的な", "し", R.drawable.none, Word.ADJECTIVE));
        word.get(40).add(new Word("spring", "春", "は", R.drawable.none, Word.NOUN));
        word.get(40).add(new Word("swim", "泳ぐ・水泳", "お", R.drawable.swim, Word.VERB));
        word.get(41).add(new Word("balance", "均衡・調和", "き", R.drawable.none, Word.NOUN));
        word.get(41).add(new Word("building", "建物", "た", R.drawable.none, Word.NOUN));
        word.get(41).add(new Word("dog", "犬", "い", R.drawable.none, Word.NOUN));
        word.get(41).add(new Word("harmony", "調和", "ち", R.drawable.none, Word.NOUN));
        word.get(41).add(new Word("month", "１か月", "い", R.drawable.none, Word.NOUN));
        word.get(41).add(new Word("part", "部分・要素", "ふ", R.drawable.none, Word.NOUN));
        word.get(41).add(new Word("powder", "粉末", "ふ", R.drawable.none, Word.NOUN));
        word.get(41).add(new Word("salt", "塩", "し", R.drawable.none, Word.NOUN));
        word.get(41).add(new Word("short", "短い", "み", R.drawable.none, Word.ADJECTIVE));
        word.get(41).add(new Word("tax", "税金", "ぜ", R.drawable.none, Word.NOUN));
        word.get(42).add(new Word("angle", "角度", "か", R.drawable.none, Word.NOUN));
        word.get(42).add(new Word("blow", "吹く", "ふ", R.drawable.none, Word.VERB));
        word.get(42).add(new Word("cork", "コルク", "こ", R.drawable.none, Word.NOUN));
        word.get(42).add(new Word("danger", "危険", "き", R.drawable.none, Word.NOUN));
        word.get(42).add(new Word("effect", "効果・影響", "こ", R.drawable.none, Word.NOUN));
        word.get(42).add(new Word("hope", "望み", "の", R.drawable.none, Word.NOUN));
        word.get(42).add(new Word("left", "左の", "ひ", R.drawable.none, Word.ADJECTIVE));
        word.get(42).add(new Word("loss", "紛失", "ふ", R.drawable.none, Word.NOUN));
        word.get(42).add(new Word("rough", "ざらざらした", "ざ", R.drawable.none, Word.ADJECTIVE));
        word.get(42).add(new Word("sky", "空", "そ", R.drawable.none, Word.NOUN));
        word.get(42).add(new Word("thier", "彼(女)らの", "か", R.drawable.none, Word.PRONOUN));
        word.get(42).add(new Word("thread", "糸", "い", R.drawable.none, Word.NOUN));
        word.get(43).add(new Word("amdrpod", "も", "も", R.drawable.none, Word.NOUN));
        word.get(43).add(new Word("body", "身体", "か", R.drawable.none, Word.NOUN));
        word.get(43).add(new Word("bone", "骨", "ほ", R.drawable.none, Word.NOUN));
        word.get(43).add(new Word("circle", "円", "え", R.drawable.none, Word.NOUN));
        word.get(43).add(new Word("enjoy", "楽しむ", "た", R.drawable.enjoy, Word.VERB));
        word.get(43).add(new Word("error", "誤り", "あ", R.drawable.none, Word.NOUN));
        word.get(43).add(new Word("false", "間違った", "ま", R.drawable.none, Word.ADJECTIVE));
        word.get(43).add(new Word("mosquito", "蚊", "か", R.drawable.mosquito, Word.NOUN));
        word.get(43).add(new Word("nation", "国民・民族", "こ", R.drawable.none, Word.NOUN));
        word.get(43).add(new Word("person", "一個人としての人", "い", R.drawable.none, Word.NOUN));
        word.get(43).add(new Word("taste", "味覚", "み", R.drawable.none, Word.NOUN));
        word.get(43).add(new Word("train", "電車", "で", R.drawable.none, Word.NOUN));
        word.get(43).add(new Word("unit", "ユニット", "ゆ", R.drawable.none, Word.NOUN));
        word.get(43).add(new Word("war", "戦争", "せ", R.drawable.none, Word.NOUN));
        word.get(44).add(new Word("experience", "経験", "け", R.drawable.none, Word.NOUN));
        word.get(44).add(new Word("friend", "友人", "ゆ", R.drawable.none, Word.NOUN));
        word.get(44).add(new Word("girl", "少女", "し", R.drawable.none, Word.NOUN));
        word.get(44).add(new Word("learn", "学ぶ・習得する・身につける", "ま", R.drawable.learn, Word.VERB));
        word.get(44).add(new Word("letter", "手紙・文字", "て", R.drawable.none, Word.NOUN));
        word.get(44).add(new Word("look", "見る・注視する", "み", R.drawable.look, Word.VERB));
        word.get(44).add(new Word("map", "地図", "ち", R.drawable.none, Word.NOUN));
        word.get(44).add(new Word("muscle", "筋", "き", R.drawable.none, Word.NOUN));
        word.get(44).add(new Word("red", "赤い", "あ", R.drawable.none, Word.ADJECTIVE));
        word.get(44).add(new Word("sound", "音", "お", R.drawable.none, Word.NOUN));
        word.get(44).add(new Word("special", "特別の", "と", R.drawable.none, Word.ADJECTIVE));
        word.get(44).add(new Word("tail", "しっぽ", "し", R.drawable.none, Word.NOUN));
        word.get(44).add(new Word("water", "水", "み", R.drawable.none, Word.NOUN));
        word.get(44).add(new Word("wire", "針金", "は", R.drawable.none, Word.NOUN));
        word.get(45).add(new Word("canvas", "キャンバス", "き", R.drawable.none, Word.NOUN));
        word.get(45).add(new Word("catch", "～を捕まえる", "を", R.drawable.c_catch, Word.VERB));
        word.get(45).add(new Word("dirty", "汚れた", "か", R.drawable.none, Word.ADJECTIVE));
        word.get(45).add(new Word("egg", "卵", "た", R.drawable.none, Word.NOUN));
        word.get(45).add(new Word("money", "金・通貨", "か", R.drawable.none, Word.NOUN));
        word.get(45).add(new Word("place", "場所", "は", R.drawable.none, Word.NOUN));
        word.get(45).add(new Word("zoo", "動物園", "と", R.drawable.none, Word.NOUN));
        word.get(46).add(new Word("aunt", "おば", "お", R.drawable.none, Word.NOUN));
        word.get(46).add(new Word("base", "土台・基礎・基地", "ど", R.drawable.none, Word.NOUN));
        word.get(46).add(new Word("birth", "誕生", "た", R.drawable.none, Word.NOUN));
        word.get(46).add(new Word("deep", "深い", "ふ", R.drawable.none, Word.ADJECTIVE));
        word.get(46).add(new Word("detail", "詳細・細部", "し", R.drawable.none, Word.NOUN));
        word.get(46).add(new Word("existence", "存在", "そ", R.drawable.none, Word.NOUN));
        word.get(46).add(new Word("lemon", "レモン", "れ", R.drawable.none, Word.NOUN));
        word.get(46).add(new Word("monkey", "猿", "さ", R.drawable.none, Word.NOUN));
        word.get(46).add(new Word("night", "夜", "よ", R.drawable.none, Word.NOUN));
        word.get(46).add(new Word("pump", "ポンプ", "ぽ", R.drawable.none, Word.NOUN));
        word.get(46).add(new Word("volunteer", "有志の", "ゆ", R.drawable.volunteer, Word.ADJECTIVE));
        word.get(47).add(new Word("bent", "曲がった", "ま", R.drawable.none, Word.ADJECTIVE));
        word.get(47).add(new Word("measure", "測定する", "そ", R.drawable.none, Word.VERB));
        word.get(47).add(new Word("pilot", "操縦する", "そ", R.drawable.none, Word.VERB));
        word.get(47).add(new Word("reward", "報酬", "ほ", R.drawable.none, Word.NOUN));
        word.get(47).add(new Word("shake", "振る", "ふ", R.drawable.none, Word.VERB));
        word.get(47).add(new Word("spade", "スペード", "す", R.drawable.none, Word.NOUN));
        word.get(47).add(new Word("village", "村", "む", R.drawable.none, Word.NOUN));
        word.get(47).add(new Word("volunteer", "志願者・ボランティア", "し", R.drawable.volunteer, Word.NOUN));
        word.get(47).add(new Word("whistle", "口笛", "く", R.drawable.none, Word.NOUN));
        word.get(48).add(new Word("brown", "茶色の", "ち", R.drawable.none, Word.ADJECTIVE));
        word.get(48).add(new Word("character", "性格・特性", "せ", R.drawable.none, Word.NOUN));
        word.get(48).add(new Word("cook", "料理人", "り", R.drawable.none, Word.NOUN));
        word.get(48).add(new Word("dance", "踊る", "お", R.drawable.none, Word.VERB));
        word.get(48).add(new Word("head", "頭", "あ", R.drawable.none, Word.NOUN));
        word.get(48).add(new Word("hotel", "ホテル", "ほ", R.drawable.none, Word.NOUN));
        word.get(48).add(new Word("like", "同類", "と", R.drawable.none, Word.NOUN));
        word.get(48).add(new Word("love", "愛", "あ", R.drawable.none, Word.NOUN));
        word.get(48).add(new Word("operation", "作動・運営", "さ", R.drawable.none, Word.NOUN));
        word.get(48).add(new Word("ours", "私たちのもの", "わ", R.drawable.none, Word.PRONOUN));
        word.get(48).add(new Word("soft", "やわらかい", "や", R.drawable.none, Word.ADJECTIVE));
        word.get(49).add(new Word("brain", "脳", "の", R.drawable.none, Word.NOUN));
        word.get(49).add(new Word("carriage", "乗り物", "の", R.drawable.none, Word.NOUN));
        word.get(49).add(new Word("doctor", "医者", "い", R.drawable.none, Word.NOUN));
        word.get(49).add(new Word("horn", "角", "つ", R.drawable.none, Word.NOUN));
        word.get(49).add(new Word("language", "言語", "け", R.drawable.none, Word.NOUN));
        word.get(49).add(new Word("sea", "海", "う", R.drawable.none, Word.NOUN));
        word.get(49).add(new Word("shut", "閉める", "し", R.drawable.none, Word.VERB));
        word.get(49).add(new Word("smooth", "なめらか", "な", R.drawable.none, Word.ADJECTIVE));
        word.get(49).add(new Word("stand", "立つ・立ち上がる", "た", R.drawable.stand_2, Word.VERB));
        word.get(49).add(new Word("teaching", "教え", "お", R.drawable.none, Word.NOUN));
        word.get(49).add(new Word("thick", "厚い", "あ", R.drawable.none, Word.ADJECTIVE));
        word.get(49).add(new Word("top", "頂上", "ち", R.drawable.none, Word.NOUN));
        word.get(5).add(new Word("from", "～から", "か", R.drawable.none, Word.PREPOSITION));
        word.get(5).add(new Word("in", "中に・中へ", "な", R.drawable.none, Word.ADVERB));
        word.get(5).add(new Word("in", "内の・内部の", "う", R.drawable.none, Word.ADJECTIVE));
        word.get(5).add(new Word("in", "～の中に", "の", R.drawable.none, Word.PREPOSITION));
        word.get(5).add(new Word("off", "休みの", "や", R.drawable.none, Word.ADJECTIVE));
        word.get(5).add(new Word("off", "離れて・去って", "は", R.drawable.none, Word.ADVERB));
        word.get(5).add(new Word("off", "～から離れて", "か", R.drawable.none, Word.PREPOSITION));
        word.get(5).add(new Word("on", "上に・離れずに", "う", R.drawable.none, Word.ADVERB));
        word.get(5).add(new Word("on", "～の上に・～に接触して", "の", R.drawable.none, Word.PREPOSITION));
        word.get(5).add(new Word("over", "上に", "う", R.drawable.none, Word.ADVERB));
        word.get(5).add(new Word("over", "～の頭上を・～を覆って", "の", R.drawable.none, Word.PREPOSITION));
        word.get(50).add(new Word("back", "後ろ", "う", R.drawable.none, Word.NOUN));
        word.get(50).add(new Word("chance", "機会・好機", "き", R.drawable.none, Word.NOUN));
        word.get(50).add(new Word("group", "群れ・集団", "む", R.drawable.none, Word.NOUN));
        word.get(50).add(new Word("iron", "鉄の・鉄製の", "て", R.drawable.none, Word.ADJECTIVE));
        word.get(50).add(new Word("living", "生きている", "い", R.drawable.none, Word.ADJECTIVE));
        word.get(50).add(new Word("march", "行進・行軍", "こ", R.drawable.march, Word.NOUN));
        word.get(50).add(new Word("middle", "真ん中の", "ま", R.drawable.none, Word.ADJECTIVE));
        word.get(50).add(new Word("necessary", "必要な", "ひ", R.drawable.none, Word.ADJECTIVE));
        word.get(50).add(new Word("parcel", "小包", "こ", R.drawable.none, Word.NOUN));
        word.get(50).add(new Word("payment", "支払い", "し", R.drawable.none, Word.NOUN));
        word.get(50).add(new Word("sister", "姉妹", "し", R.drawable.none, Word.NOUN));
        word.get(50).add(new Word("sock", "靴下", "く", R.drawable.none, Word.NOUN));
        word.get(50).add(new Word("something", "何か", "な", R.drawable.none, Word.PRONOUN));
        word.get(50).add(new Word("space", "空間・宇宙", "く", R.drawable.none, Word.NOUN));
        word.get(50).add(new Word("watch", "腕時計", "う", R.drawable.watch_1, Word.NOUN));
        word.get(51).add(new Word("education", "学校教育", "か", R.drawable.none, Word.NOUN));
        word.get(51).add(new Word("grandchild", "孫", "ま", R.drawable.none, Word.NOUN));
        word.get(51).add(new Word("memory", "記憶", "き", R.drawable.none, Word.NOUN));
        word.get(51).add(new Word("new", "新しい", "あ", R.drawable.none, Word.ADJECTIVE));
        word.get(51).add(new Word("refuses", "拒絶する", "き", R.drawable.none, Word.VERB));
        word.get(51).add(new Word("snow", "雪", "ゆ", R.drawable.none, Word.NOUN));
        word.get(51).add(new Word("study", "勉強する", "へ", R.drawable.none, Word.VERB));
        word.get(52).add(new Word("band", "バンド", "ば", R.drawable.none, Word.NOUN));
        word.get(52).add(new Word("cough", "咳", "せ", R.drawable.none, Word.NOUN));
        word.get(52).add(new Word("equal", "等しい・対等の", "ひ", R.drawable.none, Word.ADJECTIVE));
        word.get(52).add(new Word("field", "野・原", "の", R.drawable.none, Word.NOUN));
        word.get(52).add(new Word("like", "好む・好き", "こ", R.drawable.none, Word.VERB));
        word.get(52).add(new Word("mark", "しみ・マーク", "し", R.drawable.none, Word.NOUN));
        word.get(52).add(new Word("pipe", "パイプ", "ぱ", R.drawable.none, Word.NOUN));
        word.get(52).add(new Word("produce", "生ずる・作り出す", "し", R.drawable.none, Word.VERB));
        word.get(52).add(new Word("rhythm", "リズム", "り", R.drawable.none, Word.NOUN));
        word.get(52).add(new Word("system", "システム", "し", R.drawable.none, Word.NOUN));
        word.get(52).add(new Word("week", "週", "し", R.drawable.none, Word.NOUN));
        word.get(53).add(new Word("bank", "銀行・土手", "き", R.drawable.none, Word.NOUN));
        word.get(53).add(new Word("book", "本", "ほ", R.drawable.none, Word.NOUN));
        word.get(53).add(new Word("button", "ボタン", "ぼ", R.drawable.none, Word.NOUN));
        word.get(53).add(new Word("digestion", "消化", "し", R.drawable.none, Word.NOUN));
        word.get(53).add(new Word("high", "高水準", "こ", R.drawable.none, Word.NOUN));
        word.get(53).add(new Word("instrument", "器具・器械", "き", R.drawable.none, Word.NOUN));
        word.get(53).add(new Word("myself", "私自身", "わ", R.drawable.none, Word.PRONOUN));
        word.get(53).add(new Word("rod", "棒", "ぼ", R.drawable.none, Word.NOUN));
        word.get(53).add(new Word("self", "自身・自我", "し", R.drawable.none, Word.NOUN));
        word.get(53).add(new Word("stitch", "縫い付ける", "ぬ", R.drawable.none, Word.VERB));
        word.get(53).add(new Word("trousers", "ズボン", "ず", R.drawable.none, Word.NOUN));
        word.get(53).add(new Word("wine", "ワイン・果実酒", "わ", R.drawable.none, Word.NOUN));
        word.get(54).add(new Word("amount", "総計", "そ", R.drawable.none, Word.NOUN));
        word.get(54).add(new Word("amusement", "娯楽", "ご", R.drawable.none, Word.NOUN));
        word.get(54).add(new Word("are", "(二人称)be動詞", "に", R.drawable.none, Word.VERB));
        word.get(54).add(new Word("bat", "バット", "は", R.drawable.none, Word.NOUN));
        word.get(54).add(new Word("beautiful", "美しい", "う", R.drawable.none, Word.ADJECTIVE));
        word.get(54).add(new Word("bread", "パン", "は", R.drawable.none, Word.NOUN));
        word.get(54).add(new Word("change", "変える", "か", R.drawable.none, Word.VERB));
        word.get(54).add(new Word("class", "クラス・種類", "く", R.drawable.none, Word.NOUN));
        word.get(54).add(new Word("cloth", "反物・服地", "た", R.drawable.none, Word.NOUN));
        word.get(54).add(new Word("judge", "裁判官・判事", "さ", R.drawable.none, Word.NOUN));
        word.get(54).add(new Word("lip", "唇", "く", R.drawable.none, Word.NOUN));
        word.get(54).add(new Word("mail", "郵便", "ゆ", R.drawable.none, Word.NOUN));
        word.get(54).add(new Word("rub", "こする", "こ", R.drawable.none, Word.VERB));
        word.get(54).add(new Word("soup", "スープ", "す", R.drawable.none, Word.NOUN));
        word.get(54).add(new Word("strange", "奇妙な", "き", R.drawable.none, Word.ADJECTIVE));
        word.get(54).add(new Word("town", "町", "ま", R.drawable.none, Word.NOUN));
        word.get(55).add(new Word("animal", "動物", "ど", R.drawable.none, Word.NOUN));
        word.get(55).add(new Word("clock", "時計", "と", R.drawable.none, Word.NOUN));
        word.get(55).add(new Word("death", "死", "し", R.drawable.none, Word.NOUN));
        word.get(55).add(new Word("knee", "膝", "ひ", R.drawable.none, Word.NOUN));
        word.get(55).add(new Word("nail", "つめ", "つ", R.drawable.none, Word.NOUN));
        word.get(55).add(new Word("nothing", "何も～ない", "な", R.drawable.none, Word.PRONOUN));
        word.get(55).add(new Word("owner", "持ち主", "も", R.drawable.none, Word.NOUN));
        word.get(55).add(new Word("public ", "公衆・国民", "こ", R.drawable.none, Word.VERB));
        word.get(55).add(new Word("run", "走る", "は", R.drawable.none, Word.VERB));
        word.get(55).add(new Word("song", "歌", "う", R.drawable.none, Word.NOUN));
        word.get(55).add(new Word("stop", "停止", "て", R.drawable.none, Word.NOUN));
        word.get(55).add(new Word("thumb", "親指", "お", R.drawable.none, Word.NOUN));
        word.get(55).add(new Word("voice", "声", "こ", R.drawable.none, Word.NOUN));
        word.get(55).add(new Word("wave", "波", "な", R.drawable.none, Word.NOUN));
        word.get(56).add(new Word("blade", "刀", "か", R.drawable.none, Word.NOUN));
        word.get(56).add(new Word("branch", "枝", "え", R.drawable.none, Word.NOUN));
        word.get(56).add(new Word("cake", "ケーキ", "け", R.drawable.none, Word.NOUN));
        word.get(56).add(new Word("certain", "確信して", "か", R.drawable.none, Word.ADJECTIVE));
        word.get(56).add(new Word("city", "都市・都会", "と", R.drawable.none, Word.NOUN));
        word.get(56).add(new Word("kind", "親切な", "し", R.drawable.none, Word.ADJECTIVE));
        word.get(56).add(new Word("old", "古い", "ふ", R.drawable.none, Word.ADJECTIVE));
        word.get(56).add(new Word("plate", "皿・平皿", "さ", R.drawable.none, Word.NOUN));
        word.get(56).add(new Word("present", "現在の", "げ", R.drawable.none, Word.ADJECTIVE));
        word.get(56).add(new Word("protect", "保護する・守る", "ほ", R.drawable.none, Word.VERB));
        word.get(56).add(new Word("relative", "相対的な", "そ", R.drawable.none, Word.ADJECTIVE));
        word.get(56).add(new Word("stamp", "切手", "き", R.drawable.none, Word.NOUN));
        word.get(56).add(new Word("themselves", "彼(女)ら自身", "か", R.drawable.none, Word.PRONOUN));
        word.get(56).add(new Word("wise", "賢い", "か", R.drawable.none, Word.ADJECTIVE));
        word.get(57).add(new Word("bridge", "橋", "は", R.drawable.none, Word.NOUN));
        word.get(57).add(new Word("business", "職業", "し", R.drawable.none, Word.NOUN));
        word.get(57).add(new Word("definition", "定義", "て", R.drawable.none, Word.NOUN));
        word.get(57).add(new Word("delimit", "範囲を定める", "は", R.drawable.none, Word.VERB));
        word.get(57).add(new Word("excuse", "許す", "ゆ", R.drawable.excuse, Word.VERB));
        word.get(57).add(new Word("ink", "インク", "い", R.drawable.none, Word.NOUN));
        word.get(57).add(new Word("opinion", "意見・見解", "い", R.drawable.none, Word.NOUN));
        word.get(57).add(new Word("paper", "紙", "か", R.drawable.none, Word.NOUN));
        word.get(57).add(new Word("rail", "レール", "れ", R.drawable.none, Word.NOUN));
        word.get(57).add(new Word("security", "安全の", "あ", R.drawable.none, Word.ADJECTIVE));
        word.get(57).add(new Word("store", "店", "み", R.drawable.none, Word.NOUN));
        word.get(57).add(new Word("think", "思う・考える", "お", R.drawable.think, Word.VERB));
        word.get(57).add(new Word("us", "私たちを・私たちに", "わ", R.drawable.none, Word.PRONOUN));
        word.get(57).add(new Word("violent", "暴力的な", "ぼ", R.drawable.none, Word.ADJECTIVE));
        word.get(57).add(new Word("wool", "羊毛", "よ", R.drawable.none, Word.NOUN));
        word.get(58).add(new Word("ball", "ボール", "ぼ", R.drawable.none, Word.NOUN));
        word.get(58).add(new Word("complex", "複合の", "ふ", R.drawable.none, Word.ADJECTIVE));
        word.get(58).add(new Word("design", "設計", "せ", R.drawable.none, Word.NOUN));
        word.get(58).add(new Word("flag", "旗", "は", R.drawable.none, Word.NOUN));
        word.get(58).add(new Word("fruit", "果物", "く", R.drawable.none, Word.NOUN));
        word.get(58).add(new Word("needle", "針", "は", R.drawable.none, Word.NOUN));
        word.get(58).add(new Word("rate", "割合・率", "わ", R.drawable.none, Word.NOUN));
        word.get(58).add(new Word("transport", "輸送する", "ゆ", R.drawable.none, Word.VERB));
        word.get(58).add(new Word("write", "書く・字を書く", "か", R.drawable.write, Word.VERB));
        word.get(59).add(new Word("bitter", "苦い", "に", R.drawable.none, Word.ADJECTIVE));
        word.get(59).add(new Word("everyone", "みんな", "み", R.drawable.none, Word.PRONOUN));
        word.get(59).add(new Word("full", "いっぱいの", "い", R.drawable.none, Word.ADJECTIVE));
        word.get(59).add(new Word("leaf", "葉", "は", R.drawable.none, Word.NOUN));
        word.get(59).add(new Word("married", "結婚している", "け", R.drawable.none, Word.ADJECTIVE));
        word.get(59).add(new Word("motion", "動き", "う", R.drawable.none, Word.NOUN));
        word.get(59).add(new Word("regret", "残念", "さ", R.drawable.none, Word.NOUN));
        word.get(59).add(new Word("smell", "におい", "に", R.drawable.none, Word.NOUN));
        word.get(59).add(new Word("stay", "とどまる・居残る", "と", R.drawable.stay, Word.VERB));
        word.get(59).add(new Word("stretch", "引き伸ばす", "ひ", R.drawable.none, Word.VERB));
        word.get(6).add(new Word("through", "通して・貫いて", "と", R.drawable.none, Word.ADVERB));
        word.get(6).add(new Word("through", "通しの", "と", R.drawable.none, Word.ADJECTIVE));
        word.get(6).add(new Word("through", "～を通って", "を", R.drawable.none, Word.PREPOSITION));
        word.get(6).add(new Word("to", "～へ・～に向いて", "へ", R.drawable.none, Word.PREPOSITION));
        word.get(6).add(new Word("under", "下に", "し", R.drawable.none, Word.ADVERB));
        word.get(6).add(new Word("under", "～の下に", "の", R.drawable.none, Word.PREPOSITION));
        word.get(6).add(new Word("up", "上へ", "う", R.drawable.none, Word.ADVERB));
        word.get(6).add(new Word("up", "上りの", "の", R.drawable.none, Word.ADJECTIVE));
        word.get(6).add(new Word("up", "上昇", "し", R.drawable.none, Word.NOUN));
        word.get(6).add(new Word("up", "～の上へ", "の", R.drawable.none, Word.PREPOSITION));
        word.get(6).add(new Word("with", "～とともに・～と一緒に", "と", R.drawable.none, Word.PREPOSITION));
        word.get(60).add(new Word("country", "国・地域・地方", "く", R.drawable.none, Word.NOUN));
        word.get(60).add(new Word("herself", "彼女自身", "か", R.drawable.none, Word.PRONOUN));
        word.get(60).add(new Word("leave", "去る・やめる", "さ", R.drawable.none, Word.VERB));
        word.get(60).add(new Word("organization", "団体", "た", R.drawable.none, Word.NOUN));
        word.get(60).add(new Word("shade", "陰", "か", R.drawable.none, Word.NOUN));
        word.get(60).add(new Word("stomach", "胃", "い", R.drawable.none, Word.NOUN));
        word.get(60).add(new Word("temple", "神殿・寺院", "し", R.drawable.none, Word.NOUN));
        word.get(60).add(new Word("thing", "事", "こ", R.drawable.none, Word.NOUN));
        word.get(60).add(new Word("thunder", "雷", "か", R.drawable.none, Word.NOUN));
        word.get(60).add(new Word("wait", "待つ", "ま", R.drawable.wait, Word.VERB));
        word.get(61).add(new Word("edge", "刃", "や", R.drawable.none, Word.NOUN));
        word.get(61).add(new Word("else", "そのほかの", "そ", R.drawable.none, Word.ADJECTIVE));
        word.get(61).add(new Word("face", "顔", "か", R.drawable.none, Word.NOUN));
        word.get(61).add(new Word("hollow", "中身のない・くぼんだ", "な", R.drawable.none, Word.ADJECTIVE));
        word.get(61).add(new Word("its", "それの", "そ", R.drawable.none, Word.PRONOUN));
        word.get(61).add(new Word("like", "～のような", "の", R.drawable.none, Word.PREPOSITION));
        word.get(61).add(new Word("low", "低水準", "て", R.drawable.none, Word.NOUN));
        word.get(61).add(new Word("moon", "月", "つ", R.drawable.none, Word.NOUN));
        word.get(61).add(new Word("plough", "プラウ", "ぷ", R.drawable.none, Word.NOUN));
        word.get(61).add(new Word("reading", "読むこと", "よ", R.drawable.none, Word.NOUN));
        word.get(61).add(new Word("roof", "屋根", "や", R.drawable.none, Word.NOUN));
        word.get(61).add(new Word("what", "何", "な", R.drawable.none, Word.INTERROGATIVE));
        word.get(61).add(new Word("white", "白い", "し", R.drawable.none, Word.ADJECTIVE));
        word.get(62).add(new Word("awake", "目が覚めて", "め", R.drawable.none, Word.ADJECTIVE));
        word.get(62).add(new Word("copper", "銅", "ど", R.drawable.none, Word.NOUN));
        word.get(62).add(new Word("desire", "欲望", "よ", R.drawable.none, Word.NOUN));
        word.get(62).add(new Word("everything", "すべてのもの", "す", R.drawable.none, Word.PRONOUN));
        word.get(62).add(new Word("fact", "事実", "し", R.drawable.none, Word.NOUN));
        word.get(62).add(new Word("island", "島", "し", R.drawable.none, Word.NOUN));
        word.get(62).add(new Word("kitchen", "台所", "た", R.drawable.none, Word.NOUN));
        word.get(62).add(new Word("level", "水面", "す", R.drawable.none, Word.NOUN));
        word.get(62).add(new Word("piano", "ピアノ", "ひ", R.drawable.none, Word.NOUN));
        word.get(62).add(new Word("same", "同じ", "お", R.drawable.none, Word.ADJECTIVE));
        word.get(62).add(new Word("sweet", "甘い", "あ", R.drawable.none, Word.ADJECTIVE));
        word.get(62).add(new Word("wall", "壁", "か", R.drawable.none, Word.NOUN));
        word.get(63).add(new Word("answer", "答える", "こ", R.drawable.answer_2, Word.VERB));
        word.get(63).add(new Word("church", "教会", "き", R.drawable.none, Word.NOUN));
        word.get(63).add(new Word("drop", "しずく", "し", R.drawable.none, Word.NOUN));
        word.get(63).add(new Word("fan", "風を送る", "か", R.drawable.fan_2, Word.VERB));
        word.get(63).add(new Word("hate", "憎む", "に", R.drawable.none, Word.VERB));
        word.get(63).add(new Word("him", "彼を・彼に", "か", R.drawable.none, Word.PRONOUN));
        word.get(63).add(new Word("hour", "時・1時間", "し", R.drawable.none, Word.NOUN));
        word.get(63).add(new Word("insect", "虫", "む", R.drawable.none, Word.NOUN));
        word.get(63).add(new Word("plant", "植え付けられた植物・工場", "う", R.drawable.none, Word.NOUN));
        word.get(63).add(new Word("scale", "目盛り・規模", "め", R.drawable.none, Word.NOUN));
        word.get(63).add(new Word("security", "安全・無事", "あ", R.drawable.none, Word.NOUN));
        word.get(63).add(new Word("structure", "構造・機構・組織", "こ", R.drawable.none, Word.NOUN));
        word.get(63).add(new Word("volunteer", "申し出る・進んで言う", "も", R.drawable.volunteer, Word.VERB));
        word.get(64).add(new Word("able", "することができる", "で", R.drawable.none, Word.ADJECTIVE));
        word.get(64).add(new Word("bird", "鳥", "と", R.drawable.bird, Word.NOUN));
        word.get(64).add(new Word("cheap", "安い", "や", R.drawable.none, Word.ADJECTIVE));
        word.get(64).add(new Word("chest", "胸", "む", R.drawable.none, Word.NOUN));
        word.get(64).add(new Word("crime", "犯罪", "は", R.drawable.none, Word.NOUN));
        word.get(64).add(new Word("end", "最後・終わり", "さ", R.drawable.none, Word.NOUN));
        word.get(64).add(new Word("process", "経過・過程", "け", R.drawable.none, Word.NOUN));
        word.get(64).add(new Word("sign", "署名する", "し", R.drawable.none, Word.VERB));
        word.get(64).add(new Word("tell", "話す・告げる・伝える", "は", R.drawable.tell, Word.VERB));
        word.get(64).add(new Word("twist", "ねじれる", "ね", R.drawable.none, Word.VERB));
        word.get(65).add(new Word("basin", "洗面器", "せ", R.drawable.none, Word.NOUN));
        word.get(65).add(new Word("bee", "蜂", "は", R.drawable.none, Word.NOUN));
        word.get(65).add(new Word("bottle", "ボトル", "ぼ", R.drawable.none, Word.NOUN));
        word.get(65).add(new Word("buy", "買う", "か", R.drawable.none, Word.VERB));
        word.get(65).add(new Word("close", "閉める", "し", R.drawable.close, Word.VERB));
        word.get(65).add(new Word("collar", "襟", "え", R.drawable.none, Word.NOUN));
        word.get(65).add(new Word("lift", "持ち上げる", "も", R.drawable.none, Word.VERB));
        word.get(65).add(new Word("meeting", "会・集会", "か", R.drawable.none, Word.NOUN));
        word.get(65).add(new Word("minute", "分", "ふ", R.drawable.none, Word.NOUN));
        word.get(65).add(new Word("mist", "霧・もや", "き", R.drawable.none, Word.NOUN));
        word.get(65).add(new Word("number", "数・数字", "か", R.drawable.none, Word.NOUN));
        word.get(65).add(new Word("solid", "固体の", "こ", R.drawable.none, Word.ADJECTIVE));
        word.get(65).add(new Word("son", "息子", "む", R.drawable.none, Word.NOUN));
        word.get(66).add(new Word("account", "計算・勘定", "け", R.drawable.none, Word.NOUN));
        word.get(66).add(new Word("box", "箱", "は", R.drawable.none, Word.NOUN));
        word.get(66).add(new Word("cap", "帽子・キャップ", "ほ", R.drawable.none, Word.NOUN));
        word.get(66).add(new Word("discovery", "発見", "は", R.drawable.none, Word.NOUN));
        word.get(66).add(new Word("disease", "病気・弊害", "ひ", R.drawable.none, Word.NOUN));
        word.get(66).add(new Word("guest", "招待された・ゲストの", "し", R.drawable.none, Word.ADJECTIVE));
        word.get(66).add(new Word("mixed", "混ざった", "ま", R.drawable.none, Word.ADJECTIVE));
        word.get(66).add(new Word("niece", "めい", "め", R.drawable.none, Word.NOUN));
        word.get(66).add(new Word("sing", "歌う", "う", R.drawable.none, Word.VERB));
        word.get(67).add(new Word("bath", "風呂", "ふ", R.drawable.none, Word.NOUN));
        word.get(67).add(new Word("burn", "燃える", "も", R.drawable.none, Word.VERB));
        word.get(67).add(new Word("distance", "距離・間隔", "き", R.drawable.none, Word.NOUN));
        word.get(67).add(new Word("ear", "耳", "み", R.drawable.none, Word.NOUN));
        word.get(67).add(new Word("fixed", "固定の", "こ", R.drawable.none, Word.ADJECTIVE));
        word.get(67).add(new Word("industry", "工業", "こ", R.drawable.none, Word.NOUN));
        word.get(67).add(new Word("law", "法律", "ほ", R.drawable.none, Word.NOUN));
        word.get(67).add(new Word("level", "平坦", "へ", R.drawable.none, Word.ADJECTIVE));
        word.get(67).add(new Word("parallel", "平行の", "へ", R.drawable.none, Word.ADJECTIVE));
        word.get(67).add(new Word("ring", "指輪", "ゆ", R.drawable.none, Word.NOUN));
        word.get(67).add(new Word("sign", "符号", "ふ", R.drawable.none, Word.NOUN));
        word.get(67).add(new Word("thought", "思考", "し", R.drawable.none, Word.NOUN));
        word.get(67).add(new Word("which", "どちら・どれ", "と", R.drawable.none, Word.INTERROGATIVE));
        word.get(67).add(new Word("whose", "誰の・誰のもの", "た", R.drawable.none, Word.INTERROGATIVE));
        word.get(68).add(new Word("chin", "あご", "あ", R.drawable.none, Word.NOUN));
        word.get(68).add(new Word("cold", "冷たい", "つ", R.drawable.none, Word.ADJECTIVE));
        word.get(68).add(new Word("cook", "料理する", "り", R.drawable.none, Word.VERB));
        word.get(68).add(new Word("flight", "飛行", "ひ", R.drawable.none, Word.NOUN));
        word.get(68).add(new Word("force", "力", "ち", R.drawable.none, Word.NOUN));
        word.get(68).add(new Word("hat", "帽子", "ぼ", R.drawable.none, Word.NOUN));
        word.get(68).add(new Word("live", "住む", "す", R.drawable.none, Word.VERB));
        word.get(68).add(new Word("pull", "引く", "ひ", R.drawable.none, Word.VERB));
        word.get(68).add(new Word("statement", "述べること", "の", R.drawable.none, Word.NOUN));
        word.get(68).add(new Word("step", "ステップ", "す", R.drawable.none, Word.NOUN));
        word.get(68).add(new Word("warm", "暖かい", "あ", R.drawable.none, Word.ADJECTIVE));
        word.get(69).add(new Word("agreement", "契約", "け", R.drawable.none, Word.NOUN));
        word.get(69).add(new Word("green", "緑の", "み", R.drawable.none, Word.ADJECTIVE));
        word.get(69).add(new Word("impulse", "衝動・はずみ", "し", R.drawable.none, Word.NOUN));
        word.get(69).add(new Word("meet", "会う", "あ", R.drawable.meet, Word.VERB));
        word.get(69).add(new Word("page", "ページ", "へ", R.drawable.none, Word.NOUN));
        word.get(69).add(new Word("punishment", "処罰・刑罰", "し", R.drawable.none, Word.NOUN));
        word.get(7).add(new Word("as", "同じ程度に・同様に", "お", R.drawable.none, Word.ADVERB));
        word.get(7).add(new Word("as", "～と同じく", "ｔ", R.drawable.none, Word.CONJUNCTION));
        word.get(7).add(new Word("as", "～のような", "の", R.drawable.none, Word.PRONOUN));
        word.get(7).add(new Word("food", "食べ物", "た", R.drawable.none, Word.NOUN));
        word.get(7).add(new Word("for", "～のために・～にとって", "の", R.drawable.none, Word.PREPOSITION));
        word.get(7).add(new Word("of", "～の・～が・～に属する", "の", R.drawable.none, Word.PREPOSITION));
        word.get(7).add(new Word("than", "～よりも", "よ", R.drawable.none, Word.PREPOSITION));
        word.get(7).add(new Word("than", "～よりも・～するより", "よ", R.drawable.none, Word.CONJUNCTION));
        word.get(7).add(new Word("till", "～まで", "ま", R.drawable.none, Word.CONJUNCTION));
        word.get(7).add(new Word("till", "～まで・～になるまで", "ま", R.drawable.none, Word.PREPOSITION));
        word.get(70).add(new Word("adjustment", "調整", "ち", R.drawable.none, Word.NOUN));
        word.get(70).add(new Word("air", "空気", "く", R.drawable.none, Word.NOUN));
        word.get(70).add(new Word("cause", "原因", "げ", R.drawable.none, Word.NOUN));
        word.get(70).add(new Word("come", "来る・やってくる", "く", R.drawable.none, Word.VERB));
        word.get(70).add(new Word("day", "日中", "に", R.drawable.none, Word.NOUN));
        word.get(70).add(new Word("dear", "信愛な", "し", R.drawable.none, Word.ADJECTIVE));
        word.get(70).add(new Word("feel", "触れる・感じる", "ふ", R.drawable.feel, Word.VERB));
        word.get(70).add(new Word("great", "大きい", "お", R.drawable.none, Word.ADJECTIVE));
        word.get(70).add(new Word("high", "高い", "た", R.drawable.none, Word.ADJECTIVE));
        word.get(70).add(new Word("kick", "ける", "け", R.drawable.none, Word.VERB));
        word.get(70).add(new Word("ray", "光線", "こ", R.drawable.none, Word.NOUN));
        word.get(70).add(new Word("shirt", "シャツ", "し", R.drawable.none, Word.NOUN));
        word.get(70).add(new Word("turn", "回転する", "か", R.drawable.none, Word.VERB));
        word.get(70).add(new Word("walk", "歩く・歩行", "あ", R.drawable.walk, Word.VERB));
        word.get(71).add(new Word("authority", "権威", "け", R.drawable.none, Word.NOUN));
        word.get(71).add(new Word("brake", "ブレーキ", "ぶ", R.drawable.none, Word.NOUN));
        word.get(71).add(new Word("event", "出来事・行事", "て", R.drawable.none, Word.NOUN));
        word.get(71).add(new Word("father", "父", "ち", R.drawable.none, Word.NOUN));
        word.get(71).add(new Word("fight", "戦闘・戦い", "せ", R.drawable.none, Word.NOUN));
        word.get(71).add(new Word("frame", "骨組み", "ほ", R.drawable.none, Word.NOUN));
        word.get(71).add(new Word("humor", "ユーモア", "ゆ", R.drawable.none, Word.NOUN));
        word.get(71).add(new Word("iron", "鉄・鉄製の器具", "て", R.drawable.none, Word.NOUN));
        word.get(71).add(new Word("mind", "精神", "せ", R.drawable.none, Word.NOUN));
        word.get(71).add(new Word("slip", "滑る", "す", R.drawable.none, Word.VERB));
        word.get(71).add(new Word("sudden", "突然の", "と", R.drawable.none, Word.ADJECTIVE));
        word.get(71).add(new Word("wife", "妻", "つ", R.drawable.none, Word.NOUN));
        word.get(72).add(new Word("black", "黒い", "く", R.drawable.none, Word.ADJECTIVE));
        word.get(72).add(new Word("burst", "爆発する", "ば", R.drawable.none, Word.VERB));
        word.get(72).add(new Word("coal", "石炭", "せ", R.drawable.none, Word.NOUN));
        word.get(72).add(new Word("like", "同様な", "と", R.drawable.none, Word.ADJECTIVE));
        word.get(72).add(new Word("ornament", "装飾", "そ", R.drawable.none, Word.NOUN));
        word.get(72).add(new Word("play", "遊ぶ・行う", "あ", R.drawable.none, Word.VERB));
        word.get(72).add(new Word("request", "頼む", "た", R.drawable.none, Word.VERB));
        word.get(72).add(new Word("scissors", "はさみ", "は", R.drawable.none, Word.NOUN));
        word.get(72).add(new Word("touch", "触れる", "さ", R.drawable.none, Word.VERB));
        word.get(72).add(new Word("want", "欲する", "ほ", R.drawable.none, Word.VERB));
        word.get(73).add(new Word("attention", "注意", "ち", R.drawable.none, Word.NOUN));
        word.get(73).add(new Word("card", "カード", "か", R.drawable.none, Word.NOUN));
        word.get(73).add(new Word("chemical", "化学の", "か", R.drawable.none, Word.ADJECTIVE));
        word.get(73).add(new Word("open", "開いた", "あ", R.drawable.open, Word.VERB));
        word.get(73).add(new Word("poison", "毒", "と", R.drawable.none, Word.NOUN));
        word.get(73).add(new Word("vessel", "容器", "よ", R.drawable.none, Word.NOUN));
        word.get(73).add(new Word("watch", "じっと見る・観察する", "し", R.drawable.watch_2, Word.VERB));
        word.get(74).add(new Word("blue", "青い", "あ", R.drawable.none, Word.ADJECTIVE));
        word.get(74).add(new Word("bus", "バス", "は", R.drawable.none, Word.NOUN));
        word.get(74).add(new Word("comparison", "比較・対象", "ひ", R.drawable.none, Word.NOUN));
        word.get(74).add(new Word("expression", "表現・言い回し", "ひ", R.drawable.none, Word.NOUN));
        word.get(74).add(new Word("fish", "魚", "さ", R.drawable.none, Word.NOUN));
        word.get(74).add(new Word("hearing", "聴力", "ち", R.drawable.none, Word.NOUN));
        word.get(74).add(new Word("house", "家", "い", R.drawable.none, Word.NOUN));
        word.get(74).add(new Word("knife", "ナイフ", "な", R.drawable.none, Word.NOUN));
        word.get(74).add(new Word("list", "一覧表", "い", R.drawable.none, Word.NOUN));
        word.get(74).add(new Word("oven", "オープン", "お", R.drawable.none, Word.NOUN));
        word.get(74).add(new Word("range", "範囲", "は", R.drawable.none, Word.NOUN));
        word.get(74).add(new Word("ready", "準備ができた", "じ", R.drawable.none, Word.ADJECTIVE));
        word.get(74).add(new Word("spoon", "スプーン", "す", R.drawable.none, Word.NOUN));
        word.get(74).add(new Word("tired", "疲れた", "つ", R.drawable.none, Word.ADJECTIVE));
        word.get(75).add(new Word("blood", "血液", "け", R.drawable.none, Word.NOUN));
        word.get(75).add(new Word("bulb", "電球", "で", R.drawable.none, Word.NOUN));
        word.get(75).add(new Word("desk", "机・勉強机", "つ", R.drawable.none, Word.NOUN));
        word.get(75).add(new Word("door", "ドア・扉", "と", R.drawable.none, Word.NOUN));
        word.get(75).add(new Word("everybody", "だれもみな", "た", R.drawable.none, Word.PRONOUN));
        word.get(75).add(new Word("himself", "彼自身", "か", R.drawable.none, Word.PRONOUN));
        word.get(75).add(new Word("itself", "それ自身", "そ", R.drawable.none, Word.PRONOUN));
        word.get(75).add(new Word("modifier", "修飾語句・修飾子", "し", R.drawable.none, Word.NOUN));
        word.get(75).add(new Word("neck", "首", "く", R.drawable.none, Word.NOUN));
        word.get(75).add(new Word("quiet", "静かな", "し", R.drawable.none, Word.ADJECTIVE));
        word.get(75).add(new Word("reaction", "反応", "は", R.drawable.none, Word.NOUN));
        word.get(75).add(new Word("rice", "米", "こ", R.drawable.none, Word.NOUN));
        word.get(75).add(new Word("shame", "恥", "は", R.drawable.none, Word.NOUN));
        word.get(75).add(new Word("substance", "物質", "ぶ", R.drawable.none, Word.NOUN));
        word.get(76).add(new Word("bag", "かばん・袋", "か", R.drawable.none, Word.NOUN));
        word.get(76).add(new Word("complete", "完璧な", "か", R.drawable.none, Word.ADJECTIVE));
        word.get(76).add(new Word("cousin", "いとこ", "い", R.drawable.none, Word.NOUN));
        word.get(76).add(new Word("doubt", "疑う", "う", R.drawable.none, Word.VERB));
        word.get(76).add(new Word("kettle", "やかん", "や", R.drawable.none, Word.NOUN));
        word.get(76).add(new Word("key", "鍵", "か", R.drawable.none, Word.NOUN));
        word.get(76).add(new Word("mother", "母", "は", R.drawable.none, Word.NOUN));
        word.get(76).add(new Word("move", "移動させる・動かす", "い", R.drawable.move, Word.VERB));
        word.get(76).add(new Word("natural", "自然の", "し", R.drawable.none, Word.ADJECTIVE));
        word.get(76).add(new Word("pasta", "パスタ", "は", R.drawable.none, Word.NOUN));
        word.get(76).add(new Word("sleep", "寝る", "ね", R.drawable.none, Word.VERB));
        word.get(76).add(new Word("study", "勉強・学問", "へ", R.drawable.none, Word.NOUN));
        word.get(77).add(new Word("decision", "決定", "け", R.drawable.none, Word.NOUN));
        word.get(77).add(new Word("driving", "推進の", "す", R.drawable.none, Word.ADJECTIVE));
        word.get(77).add(new Word("farm", "農場", "の", R.drawable.none, Word.NOUN));
        word.get(77).add(new Word("front", "向かう", "む", R.drawable.none, Word.VERB));
        word.get(77).add(new Word("hers", "彼女のもの", "か", R.drawable.none, Word.PRONOUN));
        word.get(77).add(new Word("match", "マッチ", "ま", R.drawable.none, Word.NOUN));
        word.get(77).add(new Word("nobody", "だれも～ない", "た", R.drawable.none, Word.PRONOUN));
        word.get(77).add(new Word("past", "過去の", "か", R.drawable.none, Word.ADJECTIVE));
        word.get(77).add(new Word("rat", "ねずみ", "ね", R.drawable.none, Word.NOUN));
        word.get(77).add(new Word("repository", "倉庫・貯蔵所", "そ", R.drawable.none, Word.NOUN));
        word.get(77).add(new Word("separate", "切り離す", "き", R.drawable.none, Word.VERB));
        word.get(77).add(new Word("sharp", "鋭い", "す", R.drawable.none, Word.ADJECTIVE));
        word.get(77).add(new Word("steam", "蒸気", "じ", R.drawable.none, Word.NOUN));
        word.get(77).add(new Word("stone", "石", "い", R.drawable.none, Word.NOUN));
        word.get(77).add(new Word("tendency", "傾向", "け", R.drawable.none, Word.NOUN));
        word.get(77).add(new Word("tray", "トレー", "と", R.drawable.none, Word.NOUN));
        word.get(77).add(new Word("tree", "木", "き", R.drawable.none, Word.NOUN));
        word.get(78).add(new Word("break", "割れ目・裂け目・ひび", "わ", R.drawable.none, Word.NOUN));
        word.get(78).add(new Word("elastic", "弾力のある", "だ", R.drawable.none, Word.ADJECTIVE));
        word.get(78).add(new Word("goat", "ヤギ", "や", R.drawable.none, Word.NOUN));
        word.get(78).add(new Word("grip", "つかむ", "つ", R.drawable.none, Word.NOUN));
        word.get(78).add(new Word("hair", "髪", "か", R.drawable.none, Word.NOUN));
        word.get(78).add(new Word("host", "主人役を務める", "し", R.drawable.host, Word.VERB));
        word.get(78).add(new Word("market", "市場", "い", R.drawable.none, Word.NOUN));
        word.get(78).add(new Word("meal", "食事", "し", R.drawable.none, Word.NOUN));
        word.get(78).add(new Word("narrow", "狭い・細い", "せ", R.drawable.none, Word.ADJECTIVE));
        word.get(78).add(new Word("receipt", "領収書", "り", R.drawable.none, Word.NOUN));
        word.get(78).add(new Word("tape", "テープ", "て", R.drawable.none, Word.NOUN));
        word.get(78).add(new Word("use", "使う", "つ", R.drawable.none, Word.VERB));
        word.get(78).add(new Word("watch", "時計", "と", R.drawable.none, Word.NOUN));
        word.get(79).add(new Word("competition", "競争", "き", R.drawable.none, Word.NOUN));
        word.get(79).add(new Word("cord", "綱", "つ", R.drawable.none, Word.NOUN));
        word.get(79).add(new Word("dark", "暗い", "く", R.drawable.none, Word.ADJECTIVE));
        word.get(79).add(new Word("final", "最後の・最終の", "さ", R.drawable.none, Word.ADJECTIVE));
        word.get(79).add(new Word("line", "綱", "つ", R.drawable.none, Word.NOUN));
        word.get(79).add(new Word("silver", "銀", "き", R.drawable.none, Word.NOUN));
        word.get(79).add(new Word("society", "社会・共同体", "し", R.drawable.none, Word.NOUN));
        word.get(79).add(new Word("stage", "舞台", "ふ", R.drawable.none, Word.NOUN));
        word.get(79).add(new Word("trick", "トリックにかける", "と", R.drawable.none, Word.VERB));
        word.get(79).add(new Word("try", "ためす", "た", R.drawable.none, Word.VERB));
        word.get(79).add(new Word("wheel", "車輪", "し", R.drawable.none, Word.NOUN));
        word.get(8).add(new Word("a", "ひとつの", "ひ", R.drawable.none, Word.ARTICLE));
        word.get(8).add(new Word("all", "すべて", "す", R.drawable.none, Word.PRONOUN));
        word.get(8).add(new Word("all", "全体の", "せ", R.drawable.none, Word.ADJECTIVE));
        word.get(8).add(new Word("any", "どれでも～", "と", R.drawable.none, Word.ADJECTIVE));
        word.get(8).add(new Word("any", "なんでも・どれでも", "な", R.drawable.none, Word.PRONOUN));
        word.get(8).add(new Word("every", "あらゆる・ことごとくの", "あ", R.drawable.none, Word.ADJECTIVE));
        word.get(8).add(new Word("little", "ほとんど～しない", "ほ", R.drawable.none, Word.ADVERB));
        word.get(8).add(new Word("little", "小さい", "ち", R.drawable.none, Word.ADJECTIVE));
        word.get(8).add(new Word("little", "少々", "し", R.drawable.none, Word.PRONOUN));
        word.get(8).add(new Word("the", "その・例の", "そ", R.drawable.none, Word.ARTICLE));
        word.get(8).add(new Word("well", "まあ・おや", "ま", R.drawable.none, Word.INTERJECTION));
        word.get(80).add(new Word("bad", "悪い", "わ", R.drawable.none, Word.ADJECTIVE));
        word.get(80).add(new Word("bucket", "バケツ", "ば", R.drawable.none, Word.NOUN));
        word.get(80).add(new Word("chalk", "チョーク", "ち", R.drawable.none, Word.NOUN));
        word.get(80).add(new Word("hammer", "金づち", "か", R.drawable.none, Word.NOUN));
        word.get(80).add(new Word("his", "彼の・彼のもの", "か", R.drawable.none, Word.PRONOUN));
        word.get(80).add(new Word("medical", "医療の", "い", R.drawable.none, Word.ADJECTIVE));
        word.get(80).add(new Word("nerve", "神経", "し", R.drawable.none, Word.NOUN));
        word.get(80).add(new Word("noise", "雑音・騒音", "さ", R.drawable.none, Word.NOUN));
        word.get(80).add(new Word("peace", "平和", "へ", R.drawable.none, Word.NOUN));
        word.get(80).add(new Word("school", "学校", "か", R.drawable.none, Word.NOUN));
        word.get(81).add(new Word("continue", "続ける・継続する", "つ", R.drawable.none, Word.VERB));
        word.get(81).add(new Word("dry", "乾いた", "か", R.drawable.none, Word.ADJECTIVE));
        word.get(81).add(new Word("hook", "留め金", "と", R.drawable.none, Word.NOUN));
        word.get(81).add(new Word("mass", "大衆向けの", "た", R.drawable.none, Word.ADJECTIVE));
        word.get(81).add(new Word("shelf", "棚", "た", R.drawable.none, Word.NOUN));
        word.get(82).add(new Word("abstract", "抽象的な", "ち", R.drawable.none, Word.ADJECTIVE));
        word.get(82).add(new Word("damage", "損害", "き", R.drawable.none, Word.NOUN));
        word.get(82).add(new Word("early", "早い", "は", R.drawable.none, Word.ADJECTIVE));
        word.get(82).add(new Word("fat", "太った", "ふ", R.drawable.none, Word.ADJECTIVE));
        word.get(82).add(new Word("floor", "床", "ゆ", R.drawable.none, Word.NOUN));
        word.get(82).add(new Word("gun", "拳銃", "け", R.drawable.none, Word.NOUN));
        word.get(82).add(new Word("hold", "持つ・保つ", "も", R.drawable.hold, Word.VERB));
        word.get(82).add(new Word("part", "分ける・引き離す", "わ", R.drawable.none, Word.VERB));
        word.get(82).add(new Word("regular", "正規兵・レギュラー", "せ", R.drawable.none, Word.NOUN));
        word.get(82).add(new Word("start", "出発する", "し", R.drawable.none, Word.VERB));
        word.get(82).add(new Word("sun", "太陽", "た", R.drawable.none, Word.NOUN));
        word.get(83).add(new Word("belief", "信念・信仰", "し", R.drawable.none, Word.NOUN));
        word.get(83).add(new Word("degree", "程度", "て", R.drawable.none, Word.NOUN));
        word.get(83).add(new Word("equal", "匹敵する・劣らない", "ひ", R.drawable.none, Word.VERB));
        word.get(83).add(new Word("glass", "ガラス", "か", R.drawable.none, Word.NOUN));
        word.get(83).add(new Word("need", "～する必要がある", "す", R.drawable.need, Word.AUXILIARY_VERB));
        word.get(83).add(new Word("note", "メモ・記録", "め", R.drawable.none, Word.NOUN));
        word.get(83).add(new Word("potato", "じゃがいも", "じ", R.drawable.none, Word.NOUN));
        word.get(83).add(new Word("sort", "種類・ソート", "し", R.drawable.none, Word.NOUN));
        word.get(83).add(new Word("them", "彼(女)らを・彼(女)らに", "か", R.drawable.none, Word.PRONOUN));
        word.get(83).add(new Word("these", "これら", "こ", R.drawable.none, Word.PRONOUN));
        word.get(83).add(new Word("time", "時", "と", R.drawable.none, Word.NOUN));
        word.get(84).add(new Word("ant", "蟻", "あ", R.drawable.none, Word.NOUN));
        word.get(84).add(new Word("bell", "ベル", "べ", R.drawable.none, Word.NOUN));
        word.get(84).add(new Word("berry", "ベリー", "べ", R.drawable.none, Word.NOUN));
        word.get(84).add(new Word("bit", "小片", "し", R.drawable.none, Word.NOUN));
        word.get(84).add(new Word("doll", "人形", "に", R.drawable.none, Word.NOUN));
        word.get(84).add(new Word("engine", "エンジン", "え", R.drawable.none, Word.NOUN));
        word.get(84).add(new Word("heat", "熱", "ね", R.drawable.none, Word.NOUN));
        word.get(84).add(new Word("history", "歴史", "れ", R.drawable.none, Word.NOUN));
        word.get(84).add(new Word("march", "行進する", "こ", R.drawable.march, Word.VERB));
        word.get(84).add(new Word("most", "大部分", "た", R.drawable.none, Word.PRONOUN));
        word.get(84).add(new Word("point", "先・点", "さ", R.drawable.none, Word.NOUN));
        word.get(84).add(new Word("record", "記録する", "き", R.drawable.none, Word.VERB));
        word.get(84).add(new Word("responsible", "責任のある", "せ", R.drawable.none, Word.ADJECTIVE));
        word.get(84).add(new Word("smile", "ほほえみ", "ほ", R.drawable.smile, Word.NOUN));
        word.get(84).add(new Word("tooth", "歯", "は", R.drawable.none, Word.NOUN));
        word.get(85).add(new Word("addition", "付加・追加", "ふ", R.drawable.none, Word.NOUN));
        word.get(85).add(new Word("feather", "羽毛", "う", R.drawable.none, Word.NOUN));
        word.get(85).add(new Word("free", "自由な", "じ", R.drawable.none, Word.ADJECTIVE));
        word.get(85).add(new Word("frequent", "頻繁な", "ひ", R.drawable.none, Word.ADJECTIVE));
        word.get(85).add(new Word("glove", "手袋", "て", R.drawable.none, Word.NOUN));
        word.get(85).add(new Word("late", "遅れた", "お", R.drawable.none, Word.ADJECTIVE));
        word.get(85).add(new Word("mind", "注意する", "ち", R.drawable.none, Word.VERB));
        word.get(85).add(new Word("price", "価格・値段", "か", R.drawable.none, Word.NOUN));
        word.get(85).add(new Word("probable", "確実な", "か", R.drawable.none, Word.ADJECTIVE));
        word.get(85).add(new Word("rose", "バラ", "は", R.drawable.none, Word.NOUN));
        word.get(85).add(new Word("skirt", "スカート", "す", R.drawable.none, Word.NOUN));
        word.get(85).add(new Word("smash", "打ち壊す", "う", R.drawable.none, Word.VERB));
        word.get(85).add(new Word("stamp", "踏みつける", "ふ", R.drawable.none, Word.VERB));
        word.get(85).add(new Word("stem", "茎", "く", R.drawable.none, Word.NOUN));
        word.get(86).add(new Word("ask", "尋ねる・質問する", "た", R.drawable.none, Word.VERB));
        word.get(86).add(new Word("distance", "抜く・遠ざかる", "ぬ", R.drawable.none, Word.VERB));
        word.get(86).add(new Word("female", "女性の", "じ", R.drawable.none, Word.ADJECTIVE));
        word.get(86).add(new Word("grandmother", "祖母", "そ", R.drawable.none, Word.NOUN));
        word.get(86).add(new Word("heart", "心臓", "し", R.drawable.none, Word.NOUN));
        word.get(86).add(new Word("lead", "導く", "み", R.drawable.none, Word.VERB));
        word.get(86).add(new Word("like", "たぶん", "た", R.drawable.none, Word.ADVERB));
        word.get(86).add(new Word("need", "必要", "ひ", R.drawable.need, Word.NOUN));
        word.get(86).add(new Word("normal", "標準の", "ひ", R.drawable.none, Word.ADJECTIVE));
        word.get(86).add(new Word("paint", "ペンキ", "へ", R.drawable.none, Word.NOUN));
        word.get(86).add(new Word("picture", "絵・写真", "え", R.drawable.none, Word.NOUN));
        word.get(86).add(new Word("plane", "飛行機", "ひ", R.drawable.none, Word.NOUN));
        word.get(86).add(new Word("round", "丸い", "ま", R.drawable.none, Word.ADJECTIVE));
        word.get(86).add(new Word("sponge", "スポンジ", "す", R.drawable.none, Word.NOUN));
        word.get(86).add(new Word("square", "正方形", "せ", R.drawable.none, Word.NOUN));
        word.get(86).add(new Word("stick", "棒", "ぼ", R.drawable.none, Word.NOUN));
        word.get(86).add(new Word("sticky", "ねばねばする", "ね", R.drawable.none, Word.ADJECTIVE));
        word.get(86).add(new Word("ticket", "チケット", "ち", R.drawable.none, Word.NOUN));
        word.get(86).add(new Word("visit", "訪れる", "お", R.drawable.none, Word.VERB));
        word.get(86).add(new Word("weather", "天気", "て", R.drawable.none, Word.NOUN));
        word.get(86).add(new Word("wound", "傷", "き", R.drawable.none, Word.NOUN));
        word.get(87).add(new Word("debt", "債務", "さ", R.drawable.none, Word.NOUN));
        word.get(87).add(new Word("description", "記述・解説", "き", R.drawable.none, Word.NOUN));
        word.get(87).add(new Word("driving", "運転", "う", R.drawable.none, Word.NOUN));
        word.get(87).add(new Word("fragment", "破片・かけら", "は", R.drawable.none, Word.NOUN));
        word.get(87).add(new Word("host", "主人・ホスト・主催者", "し", R.drawable.host, Word.NOUN));
        word.get(87).add(new Word("improved", "改善された", "か", R.drawable.none, Word.VERB));
        word.get(87).add(new Word("milk", "牛乳", "き", R.drawable.none, Word.NOUN));
        word.get(87).add(new Word("rest", "休息", "き", R.drawable.none, Word.NOUN));
        word.get(87).add(new Word("seed", "種", "た", R.drawable.none, Word.NOUN));
        word.get(87).add(new Word("skin", "肌", "は", R.drawable.none, Word.NOUN));
        word.get(87).add(new Word("stop", "止める", "と", R.drawable.none, Word.VERB));
        word.get(87).add(new Word("theory", "理論", "り", R.drawable.none, Word.NOUN));
        word.get(87).add(new Word("tin", "缶", "か", R.drawable.none, Word.NOUN));
        word.get(87).add(new Word("weight", "重量", "じ", R.drawable.none, Word.NOUN));
        word.get(87).add(new Word("yours", "あなた(たち)のもの", "あ", R.drawable.none, Word.PRONOUN));
        word.get(88).add(new Word("electric", "電気の", "で", R.drawable.none, Word.ADJECTIVE));
        word.get(88).add(new Word("kiss", "キス", "き", R.drawable.none, Word.NOUN));
        word.get(88).add(new Word("range", "並べる", "な", R.drawable.none, Word.VERB));
        word.get(88).add(new Word("these", "これらの", "こ", R.drawable.none, Word.ADJECTIVE));
        word.get(88).add(new Word("they", "彼(女)らは", "か", R.drawable.none, Word.PRONOUN));
        word.get(88).add(new Word("winter", "冬", "ふ", R.drawable.none, Word.NOUN));
        word.get(89).add(new Word("connection", "結合・連結", "け", R.drawable.none, Word.NOUN));
        word.get(89).add(new Word("garden", "庭・庭園", "に", R.drawable.none, Word.NOUN));
        word.get(89).add(new Word("go", "行く・向かう", "い", R.drawable.none, Word.VERB));
        word.get(89).add(new Word("regular", "規則正しい", "き", R.drawable.none, Word.ADJECTIVE));
        word.get(89).add(new Word("sand", "砂", "す", R.drawable.none, Word.NOUN));
        word.get(89).add(new Word("ship", "船", "ふ", R.drawable.none, Word.NOUN));
        word.get(89).add(new Word("snake", "ヘビ", "へ", R.drawable.none, Word.NOUN));
        word.get(89).add(new Word("summer", "夏", "な", R.drawable.none, Word.NOUN));
        word.get(9).add(new Word("much", "おおいに・たいそう", "お", R.drawable.none, Word.ADVERB));
        word.get(9).add(new Word("much", "多くの・たくさんの", "お", R.drawable.none, Word.ADJECTIVE));
        word.get(9).add(new Word("much", "多量・たくさん", "た", R.drawable.none, Word.PRONOUN));
        word.get(9).add(new Word("no", "いいえ", "い", R.drawable.none, Word.ADVERB));
        word.get(9).add(new Word("no", "否定・否認", "ひ", R.drawable.none, Word.NOUN));
        word.get(9).add(new Word("no", "～ではない", "て", R.drawable.none, Word.ADJECTIVE));
        word.get(9).add(new Word("other", "ほかの", "ほ", R.drawable.none, Word.ADJECTIVE));
        word.get(9).add(new Word("other", "ほかのもの", "ほ", R.drawable.none, Word.PRONOUN));
        word.get(9).add(new Word("some", "いくらかの・多少の", "い", R.drawable.none, Word.ADJECTIVE));
        word.get(9).add(new Word("some", "多少", "た", R.drawable.none, Word.PRONOUN));
        word.get(90).add(new Word("cart", "カート", "か", R.drawable.none, Word.NOUN));
        word.get(90).add(new Word("cruel", "残忍な", "ざ", R.drawable.none, Word.ADJECTIVE));
        word.get(90).add(new Word("hospital", "病院", "ひ", R.drawable.none, Word.NOUN));
        word.get(90).add(new Word("limit", "限度・限界", "け", R.drawable.none, Word.NOUN));
        word.get(90).add(new Word("notebook", "ノート", "の", R.drawable.none, Word.NOUN));
        word.get(90).add(new Word("private", "私的な・個人の", "し", R.drawable.none, Word.ADJECTIVE));
        word.get(90).add(new Word("profit", "利益", "り", R.drawable.none, Word.NOUN));
        word.get(90).add(new Word("selection", "選択", "せ", R.drawable.none, Word.NOUN));
        word.get(90).add(new Word("suggestion", "提案", "て", R.drawable.none, Word.NOUN));
        word.get(90).add(new Word("those", "あれらの", "あ", R.drawable.none, Word.ADJECTIVE));
        word.get(90).add(new Word("window", "窓", "ま", R.drawable.none, Word.NOUN));
        word.get(91).add(new Word("beer", "ビール　", "ひ", R.drawable.none, Word.NOUN));
        word.get(91).add(new Word("committee", "委員会", "い", R.drawable.none, Word.NOUN));
        word.get(91).add(new Word("cup", "カップ", "か", R.drawable.none, Word.NOUN));
        word.get(91).add(new Word("general", "一般的な", "い", R.drawable.none, Word.ADJECTIVE));
        word.get(91).add(new Word("host", "主人役の・主催者の", "し", R.drawable.host, Word.ADJECTIVE));
        word.get(91).add(new Word("jump", "跳ぶ・ジャンプ", "と", R.drawable.none, Word.VERB));
        word.get(91).add(new Word("land", "陸", "り", R.drawable.none, Word.NOUN));
        word.get(91).add(new Word("relation", "関係", "か", R.drawable.none, Word.NOUN));
        word.get(91).add(new Word("someone", "だれか", "た", R.drawable.none, Word.PRONOUN));
        word.get(91).add(new Word("stiff", "堅い", "か", R.drawable.none, Word.ADJECTIVE));
        word.get(92).add(new Word("cheese", "チーズ", "ち", R.drawable.none, Word.NOUN));
        word.get(92).add(new Word("distribution", "配給・配分", "は", R.drawable.none, Word.NOUN));
        word.get(92).add(new Word("like", "～のように", "の", R.drawable.none, Word.CONJUNCTION));
        word.get(92).add(new Word("long", "長い", "な", R.drawable.none, Word.ADJECTIVE));
        word.get(92).add(new Word("morning", "朝", "あ", R.drawable.none, Word.NOUN));
        word.get(92).add(new Word("offer", "申し出る", "も", R.drawable.none, Word.VERB));
        word.get(92).add(new Word("order", "順序", "し", R.drawable.none, Word.NOUN));
        word.get(92).add(new Word("political", "政治上の", "せ", R.drawable.none, Word.ADJECTIVE));
        word.get(93).add(new Word("happy", "幸福な", "こ", R.drawable.none, Word.ADJECTIVE));
        word.get(93).add(new Word("healthy", "健康な", "け", R.drawable.none, Word.ADJECTIVE));
        word.get(93).add(new Word("high", "高く", "た", R.drawable.none, Word.ADVERB));
        word.get(93).add(new Word("knot", "結び", "む", R.drawable.none, Word.NOUN));
        word.get(93).add(new Word("material", "物質的な", "ぶ", R.drawable.none, Word.ADJECTIVE));
        word.get(93).add(new Word("park", "公園", "こ", R.drawable.none, Word.NOUN));
        word.get(93).add(new Word("quick", "すばやい", "す", R.drawable.none, Word.ADJECTIVE));
        word.get(93).add(new Word("sense", "感覚・勘", "か", R.drawable.none, Word.NOUN));
        word.get(93).add(new Word("story", "物語", "も", R.drawable.none, Word.NOUN));
        word.get(93).add(new Word("we", "私たちは", "わ", R.drawable.none, Word.PRONOUN));
        word.get(93).add(new Word("yourself", "あなた自身", "あ", R.drawable.none, Word.PRONOUN));
        word.get(94).add(new Word("boat", "ボート", "ほ", R.drawable.none, Word.NOUN));
        word.get(94).add(new Word("clean", "きれいな", "き", R.drawable.none, Word.ADJECTIVE));
        word.get(94).add(new Word("control", "管理・管制", "か", R.drawable.none, Word.NOUN));
        word.get(94).add(new Word("cover", "カバー・覆う", "か", R.drawable.none, Word.VERB));
        word.get(94).add(new Word("fertile", "肥沃な", "ひ", R.drawable.none, Word.ADJECTIVE));
        word.get(94).add(new Word("important", "重要な", "じ", R.drawable.none, Word.ADJECTIVE));
        word.get(94).add(new Word("type", "型・種類", "か", R.drawable.none, Word.NOUN));
        word.get(95).add(new Word("apparatus", "器具", "き", R.drawable.none, Word.NOUN));
        word.get(95).add(new Word("condition", "健康状態・体調", "け", R.drawable.none, Word.NOUN));
        word.get(95).add(new Word("cow", "牛", "う", R.drawable.none, Word.NOUN));
        word.get(95).add(new Word("cry", "泣く・叫ぶ", "な", R.drawable.none, Word.VERB));
        word.get(95).add(new Word("curve", "曲線", "き", R.drawable.none, Word.NOUN));
        word.get(95).add(new Word("fiction", "創作", "そ", R.drawable.none, Word.NOUN));
        word.get(95).add(new Word("guide", "案内する", "あ", R.drawable.none, Word.VERB));
        word.get(95).add(new Word("ourselves", "私たち自身", "わ", R.drawable.none, Word.PRONOUN));
        word.get(95).add(new Word("protest", "主張する", "し", R.drawable.none, Word.VERB));
        word.get(95).add(new Word("purpose", "意図・目的", "い", R.drawable.none, Word.NOUN));
        word.get(95).add(new Word("right", "正しい", "た", R.drawable.none, Word.ADJECTIVE));
        word.get(95).add(new Word("shock", "衝撃・振動", "し", R.drawable.none, Word.NOUN));
        word.get(95).add(new Word("word", "単語", "た", R.drawable.none, Word.NOUN));
        word.get(96).add(new Word("curtain", "カーテン", "か", R.drawable.none, Word.NOUN));
        word.get(96).add(new Word("drawer", "製図家", "せ", R.drawable.none, Word.NOUN));
        word.get(96).add(new Word("example", "例", "れ", R.drawable.none, Word.NOUN));
        word.get(96).add(new Word("ice", "氷", "こ", R.drawable.none, Word.NOUN));
        word.get(96).add(new Word("order", "指図する", "さ", R.drawable.none, Word.VERB));
        word.get(96).add(new Word("possible", "可能な", "か", R.drawable.none, Word.ADJECTIVE));
        word.get(96).add(new Word("pot", "ポット", "ほ", R.drawable.none, Word.NOUN));
        word.get(96).add(new Word("print", "印刷する", "い", R.drawable.none, Word.VERB));
        word.get(96).add(new Word("rule", "規則・ルール", "き", R.drawable.none, Word.NOUN));
        word.get(96).add(new Word("safe", "安全な", "あ", R.drawable.none, Word.ADJECTIVE));
        word.get(96).add(new Word("screw", "ネジ", "ね", R.drawable.none, Word.NOUN));
        word.get(96).add(new Word("sheep", "羊", "ひ", R.drawable.none, Word.NOUN));
        word.get(96).add(new Word("sit", "座る・腰かける", "す", R.drawable.sit, Word.VERB));
        word.get(96).add(new Word("sugar", "砂糖", "さ", R.drawable.none, Word.NOUN));
        word.get(96).add(new Word("textbook", "教科書", "き", R.drawable.none, Word.NOUN));
        word.get(97).add(new Word("arm", "腕", "う", R.drawable.none, Word.NOUN));
        word.get(97).add(new Word("boot", "ブーツ", "ぶ", R.drawable.none, Word.NOUN));
        word.get(97).add(new Word("float", "浮く・漂う", "う", R.drawable.none, Word.VERB));
        word.get(97).add(new Word("flower", "花", "は", R.drawable.none, Word.NOUN));
        word.get(97).add(new Word("gold", "金", "き", R.drawable.none, Word.NOUN));
        word.get(97).add(new Word("hard", "堅い", "か", R.drawable.none, Word.ADJECTIVE));
        word.get(97).add(new Word("leather", "革・革製品", "か", R.drawable.none, Word.NOUN));
        word.get(97).add(new Word("nephew", "おい", "お", R.drawable.none, Word.NOUN));
        word.get(97).add(new Word("representative", "代表する", "た", R.drawable.none, Word.ADJECTIVE));
        word.get(97).add(new Word("test", "テスト", "て", R.drawable.none, Word.NOUN));
        word.get(97).add(new Word("work", "仕事をする", "し", R.drawable.work, Word.VERB));
        word.get(98).add(new Word("approval", "賛成", "さ", R.drawable.none, Word.NOUN));
        word.get(98).add(new Word("child", "子供・児童", "こ", R.drawable.none, Word.NOUN));
        word.get(98).add(new Word("drain", "流れ出る", "が", R.drawable.none, Word.VERB));
        word.get(98).add(new Word("float", "浮遊物・いかだ", "ふ", R.drawable.none, Word.NOUN));
        word.get(98).add(new Word("knowkedge", "知識", "ち", R.drawable.none, Word.NOUN));
        word.get(98).add(new Word("respect", "尊敬する", "そ", R.drawable.none, Word.VERB));
        word.get(98).add(new Word("silk", "絹", "き", R.drawable.none, Word.NOUN));
        word.get(98).add(new Word("smell", "においがする", "に", R.drawable.none, Word.VERB));
        word.get(98).add(new Word("trade", "貿易", "ぼ", R.drawable.none, Word.NOUN));
        word.get(98).add(new Word("woman", "女性", "じ", R.drawable.none, Word.NOUN));
        word.get(98).add(new Word("work", "仕事・労働・任務", "し", R.drawable.work, Word.NOUN));
        word.get(99).add(new Word("attempt", "試みる", "こ", R.drawable.none, Word.VERB));
        word.get(99).add(new Word("brass", "真ちゅう", "し", R.drawable.none, Word.NOUN));
        word.get(99).add(new Word("color", "色", "い", R.drawable.none, Word.NOUN));
        word.get(99).add(new Word("dance", "ダンス", "た", R.drawable.none, Word.NOUN));
        word.get(99).add(new Word("equal", "同等のもの・同等の人", "と", R.drawable.none, Word.NOUN));
        word.get(99).add(new Word("foot", "足", "ふ", R.drawable.none, Word.NOUN));
        word.get(99).add(new Word("invention", "発明", "は", R.drawable.none, Word.NOUN));
        word.get(99).add(new Word("meat", "肉", "に", R.drawable.none, Word.NOUN));
        word.get(99).add(new Word("none", "なにも～ない", "な", R.drawable.none, Word.PRONOUN));
        word.get(99).add(new Word("paste", "生地・練り物", "き", R.drawable.none, Word.NOUN));
        word.get(99).add(new Word("roll", "ころがる", "こ", R.drawable.none, Word.VERB));
        word.get(99).add(new Word("theirs", "彼(女)らのもの", "か", R.drawable.none, Word.PRONOUN));
        word.get(99).add(new Word("those", "あれら", "あ", R.drawable.none, Word.PRONOUN));
        word.get(99).add(new Word("tongue", "舌", "し", R.drawable.none, Word.NOUN));
        word.get(99).add(new Word("wrong", "違う", "ち", R.drawable.none, Word.ADJECTIVE));
        //endword
        images = new int[]{R.drawable.ic_launcher,};
        phrase.get(0).add(new Phrase("Help me", "助けて", images));
        phrase.get(0).add(new Phrase("I have a stomachache", "お腹が痛い", images));
        phrase.get(0).add(new Phrase("I have a pain in my arm", "腕が痛い", images));
        phrase.get(0).add(new Phrase("I am Tom", "私はトムです", images));
        phrase.get(1).add(new Phrase("I am from Japan", "私は日本出身です", images));
        phrase.get(2).add(new Phrase("I have an apple", "私はリンゴを持っています", images));
        phrase.get(3).add(new Phrase("I study English", "私は英語を勉強します", images));
        phrase.get(4).add(new Phrase("I play the piano", "私はピアノを弾きます", images));
        phrase.get(5).add(new Phrase("You play soccer", "あなたはサッカーをしています", images));
        phrase.get(6).add(new Phrase("I do not play soccer", "私はサッカーをしていません", images));
        phrase.get(7).add(new Phrase("Do you play soccer?", "あなたはサッカーをしていますか？", images));
        phrase.get(8).add(new Phrase("My father and I play soccer", "私の父と私はサッカーをしています", images));
        phrase.get(9).add(new Phrase("Be careful", "気を付けて", images));
        phrase.get(10).add(new Phrase("We are boys", "私たちは男の子です", images));
        phrase.get(11).add(new Phrase("The apple is blue", "そのリンゴは青色です", images));
        createCharacter();
    }

    public static int getPartIDCount(int id) {
        int ids = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < word.get(i).size(); j++) {
                if (word.get(i).get(j).ID == id)
                    ids++;
            }
        }
        return ids;
    }

    public static int getHeadCount(int id) {
        int heads = 0;
        String head = getHead(id);
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < word.get(i).size(); j++) {
                if (word.get(i).get(j).HEAD.equals(head))
                    heads++;
            }
        }
        return heads;
    }

    public static int getHeadCountJp(int id) {
        int heads = 0;
        String head = getHeadJp(id);
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < word.get(i).size(); j++) {
                if (word.get(i).get(j).HEAD_JP.equals(head))
                    heads++;
            }
        }
        return heads;
    }

    public static ArrayList<Word> getPartIDsAll(int id) {
        ArrayList<Word> ids = new ArrayList<Word>();
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < word.get(i).size(); j++) {
                if (word.get(i).get(j).ID == id)
                    ids.add(word.get(i).get(j));
            }
        }
        return ids;
    }

    public static ArrayList<Word> getHeadAll(int id) {
        ArrayList<Word> ids = new ArrayList<Word>();
        String head = getHead(id);
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < word.get(i).size(); j++) {
                if (word.get(i).get(j).HEAD.equals(head))
                    ids.add(word.get(i).get(j));
            }
        }
        return ids;
    }

    public static ArrayList<Word> getHeadAllJp(int id) {
        ArrayList<Word> ids = new ArrayList<Word>();
        String head = getHeadJp(id);
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < word.get(i).size(); j++) {
                if (word.get(i).get(j).HEAD_JP.equals(head))
                    ids.add(word.get(i).get(j));
            }
        }
        return ids;
    }

    static String getHead(int id) {
        switch (id) {
            case 0:
                return "a";
            case 1:
                return "b";
            case 2:
                return "c";
            case 3:
                return "d";
            case 4:
                return "e";
            case 5:
                return "f";
            case 6:
                return "g";
            case 7:
                return "h";
            case 8:
                return "i";
            case 9:
                return "j";
            case 10:
                return "k";
            case 11:
                return "l";
            case 12:
                return "m";
            case 13:
                return "n";
            case 14:
                return "o";
            case 15:
                return "p";
            case 16:
                return "q";
            case 17:
                return "r";
            case 18:
                return "s";
            case 19:
                return "t";
            case 20:
                return "u";
            case 21:
                return "v";
            case 22:
                return "w";
            case 23:
                return "x";
            case 24:
                return "y";
            case 25:
                return "z";
            default:
                return "";
        }
    }

    static String getHeadJp(int id) {
        switch (id) {
            case 0:
                return "あ";
            case 1:
                return "い";
            case 2:
                return "う";
            case 3:
                return "え";
            case 4:
                return "お";
            case 5:
                return "か";
            case 6:
                return "き";
            case 7:
                return "く";
            case 8:
                return "け";
            case 9:
                return "こ";
            case 10:
                return "さ";
            case 11:
                return "し";
            case 12:
                return "す";
            case 13:
                return "せ";
            case 14:
                return "そ";
            case 15:
                return "た";
            case 16:
                return "ち";
            case 17:
                return "つ";
            case 18:
                return "て";
            case 19:
                return "と";
            case 20:
                return "な";
            case 21:
                return "に";
            case 22:
                return "ぬ";
            case 23:
                return "ね";
            case 24:
                return "の";
            case 25:
                return "は";
            case 26:
                return "ひ";
            case 27:
                return "ふ";
            case 28:
                return "へ";
            case 29:
                return "ほ";
            case 30:
                return "ま";
            case 31:
                return "み";
            case 32:
                return "む";
            case 33:
                return "め";
            case 34:
                return "も";
            case 35:
                return "や";
            case 36:
                return "ゆ";
            case 37:
                return "よ";
            case 38:
                return "ら";
            case 39:
                return "り";
            case 40:
                return "る";
            case 41:
                return "れ";
            case 42:
                return "ろ";
            case 43:
                return "わ";
            case 44:
                return "を";
            default:
                return "";
        }
    }

    static void createCharacter() {
        character.add(new Character("a", 0));
        character.add(new Character("b", 1));
        character.add(new Character("c", 2));
        character.add(new Character("d", 3));
        character.add(new Character("e", 4));
        character.add(new Character("f", 5));
        character.add(new Character("g", 6));
        character.add(new Character("h", 7));
        character.add(new Character("i", 8));
        character.add(new Character("j", 9));
        character.add(new Character("k", 10));
        character.add(new Character("l", 11));
        character.add(new Character("m", 12));
        character.add(new Character("n", 13));
        character.add(new Character("o", 14));
        character.add(new Character("p", 15));
        character.add(new Character("q", 16));
        character.add(new Character("r", 17));
        character.add(new Character("s", 18));
        character.add(new Character("t", 19));
        character.add(new Character("u", 20));
        character.add(new Character("v", 21));
        character.add(new Character("w", 22));
        character.add(new Character("x", 23));
        character.add(new Character("y", 24));
        character.add(new Character("z", 25));
    }
}
