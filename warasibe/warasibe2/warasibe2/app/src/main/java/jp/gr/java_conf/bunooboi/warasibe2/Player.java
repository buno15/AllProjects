package jp.gr.java_conf.bunooboi.warasibe2;

public class Player {
	public static int		hp;									// 体力
	public static int		damage;								// ダメージ
	public static int		power;								// 力
	public static int		insight;							// 洞察力
	public static int		money;								// 所持金 １億まで
																// 単位はGSB
	public static int[]		add		= new int[3];				// アイテムによる付加
	public static int		probability;
	public static String	item;								// アイテム １６文字まで
	public static String[]	addadd	= new String[3];			// アイテムによるステータス表記
	public static String	probailitytext1, probailitytext2;

	public static int gethp() {
		return hp;
	}

	public static int getdamage() {
		return damage;
	}

	public static int getpower() {
		return power;
	}

	public static int getinsight() {
		return insight;
	}

	public static int getmoney() {
		return money;
	}

	public static String getitem() {
		return item;
	}

	public static int getadd(int add) {
		if (add == 0)
			return Player.add[0];
		else if (add == 1)
			return Player.add[1];
		else if (add == 2)
			return Player.add[2];
		else
			return 0;
	}

	public static void getprobability(int random) {
		probability = (int) Math.floor(Math.random() * 8);
		if (getinsight() >= 0 && getinsight() <= 25) {
			if (probability == 0) {
				probailitytext1 = "この人の言うことは信じられる。";
				probailitytext2 = "勝てる気がする。";
			} else if (probability == 1) {
				probailitytext1 = "この人の言うことは嘘だ。";
				probailitytext2 = "負ける気がする。";
			} else if (probability == 2) {
				probailitytext1 = "この人の言うことは信じられる。";
				probailitytext2 = "勝てる気がする。";
			} else if (probability == 3) {
				probailitytext1 = "この人の言うことは嘘だ。";
				probailitytext2 = "負ける気がする。";
			} else if (probability == 4) {
				probailitytext1 = "この人の言うことは信じられる。";
				probailitytext2 = "勝てる気がする。";
			} else if (probability == 5) {
				probailitytext1 = "この人の言うことは嘘だ。";
				probailitytext2 = "負ける気がする。";
			} else if (probability == 6) {
				if (random == 0) {
					probailitytext1 = "この人の言うことは信じられる。";
					probailitytext2 = "勝てる気がする。";
				} else if (random == 1) {
					probailitytext1 = "この人の言うことは嘘だ。";
					probailitytext2 = "負ける気がする。";
				}
			} else if (probability == 7) {
				if (random == 0) {
					probailitytext1 = "この人の言うことは信じられる。";
					probailitytext2 = "勝てる気がする。";
				} else if (random == 1) {
					probailitytext1 = "この人の言うことは嘘だ。";
					probailitytext2 = "負ける気がする。";
				}
			}
		} else if (getinsight() >= 26 && getinsight() <= 50) {
			if (probability == 0) {
				probailitytext1 = "この人の言うことは信じられる。";
				probailitytext2 = "勝てる気がする。";
			} else if (probability == 1) {
				probailitytext1 = "この人の言うことは嘘だ。";
				probailitytext2 = "負ける気がする。";
			} else if (probability == 2) {
				probailitytext1 = "この人の言うことは信じられる。";
				probailitytext2 = "勝てる気がする。";
			} else if (probability == 3) {
				probailitytext1 = "この人の言うことは嘘だ。";
				probailitytext2 = "負ける気がする。";
			} else if (probability == 4) {
				if (random == 0) {
					probailitytext1 = "この人の言うことは信じられる。";
					probailitytext2 = "勝てる気がする。";
				} else if (random == 1) {
					probailitytext1 = "この人の言うことは嘘だ。";
					probailitytext2 = "負ける気がする。";
				}
			} else if (probability == 5) {
				if (random == 0) {
					probailitytext1 = "この人の言うことは信じられる。";
					probailitytext2 = "勝てる気がする。";
				} else if (random == 1) {
					probailitytext1 = "この人の言うことは嘘だ。";
					probailitytext2 = "負ける気がする。";
				}
			} else if (probability == 6) {
				if (random == 0) {
					probailitytext1 = "この人の言うことは信じられる。";
					probailitytext2 = "勝てる気がする。";
				} else if (random == 1) {
					probailitytext1 = "この人の言うことは嘘だ。";
					probailitytext2 = "負ける気がする。";
				}
			} else if (probability == 7) {
				if (random == 0) {
					probailitytext1 = "この人の言うことは信じられる。";
					probailitytext2 = "勝てる気がする。";
				} else if (random == 1) {
					probailitytext1 = "この人の言うことは嘘だ。";
					probailitytext2 = "負ける気がする。";
				}
			}
		} else if (getinsight() >= 51 && getinsight() <= 70) {
			if (probability == 0) {
				probailitytext1 = "この人の言うことは信じられる。";
				probailitytext2 = "勝てる気がする。";
			} else if (probability == 1) {
				probailitytext1 = "この人の言うことは嘘だ。";
				probailitytext2 = "負ける気がする。";
			} else if (probability == 2) {
				if (random == 0) {
					probailitytext1 = "この人の言うことは信じられる。";
					probailitytext2 = "勝てる気がする。";
				} else if (random == 1) {
					probailitytext1 = "この人の言うことは嘘だ。";
					probailitytext2 = "負ける気がする。";
				}
			} else if (probability == 3) {
				if (random == 0) {
					probailitytext1 = "この人の言うことは信じられる。";
					probailitytext2 = "勝てる気がする。";
				} else if (random == 1) {
					probailitytext1 = "この人の言うことは嘘だ。";
					probailitytext2 = "負ける気がする。";
				}
			} else if (probability == 4) {
				if (random == 0) {
					probailitytext1 = "この人の言うことは信じられる。";
					probailitytext2 = "勝てる気がする。";
				} else if (random == 1) {
					probailitytext1 = "この人の言うことは嘘だ。";
					probailitytext2 = "負ける気がする。";
				}
			} else if (probability == 5) {
				if (random == 0) {
					probailitytext1 = "この人の言うことは信じられる。";
					probailitytext2 = "勝てる気がする。";
				} else if (random == 1) {
					probailitytext1 = "この人の言うことは嘘だ。";
					probailitytext2 = "負ける気がする。";
				}
			} else if (probability == 6) {
				if (random == 0) {
					probailitytext1 = "この人の言うことは信じられる。";
					probailitytext2 = "勝てる気がする。";
				} else if (random == 1) {
					probailitytext1 = "この人の言うことは嘘だ。";
					probailitytext2 = "負ける気がする。";
				}
			} else if (probability == 7) {
				if (random == 0) {
					probailitytext1 = "この人の言うことは信じられる。";
					probailitytext2 = "勝てる気がする。";
				} else if (random == 1) {
					probailitytext1 = "この人の言うことは嘘だ。";
					probailitytext2 = "負ける気がする。";
				}
			}
		} else if (getinsight() >= 71 && getinsight() <= 200) {
			if (probability == 0) {
				if (random == 0) {
					probailitytext1 = "この人の言うことは嘘だ。";
					probailitytext2 = "負ける気がする。";
				} else if (random == 1) {
					probailitytext1 = "この人の言うことは信じられる。";
					probailitytext2 = "勝てる気がする。";
				}
			} else if (probability == 1) {
				if (random == 0) {
					probailitytext1 = "この人の言うことは信じられる。";
					probailitytext2 = "勝てる気がする。";
				} else if (random == 1) {
					probailitytext1 = "この人の言うことは嘘だ。";
					probailitytext2 = "負ける気がする。";
				}
			} else if (probability == 2) {
				if (random == 0) {
					probailitytext1 = "この人の言うことは信じられる。";
					probailitytext2 = "勝てる気がする。";
				} else if (random == 1) {
					probailitytext1 = "この人の言うことは嘘だ。";
					probailitytext2 = "負ける気がする。";
				}
			} else if (probability == 3) {
				if (random == 0) {
					probailitytext1 = "この人の言うことは信じられる。";
					probailitytext2 = "勝てる気がする。";
				} else if (random == 1) {
					probailitytext1 = "この人の言うことは嘘だ。";
					probailitytext2 = "負ける気がする。";
				}
			} else if (probability == 4) {
				if (random == 0) {
					probailitytext1 = "この人の言うことは信じられる。";
					probailitytext2 = "勝てる気がする。";
				} else if (random == 1) {
					probailitytext1 = "この人の言うことは嘘だ。";
					probailitytext2 = "負ける気がする。";
				}
			} else if (probability == 5) {
				if (random == 0) {
					probailitytext1 = "この人の言うことは信じられる。";
					probailitytext2 = "勝てる気がする。";
				} else if (random == 1) {
					probailitytext1 = "この人の言うことは嘘だ。";
					probailitytext2 = "負ける気がする。";
				}
			} else if (probability == 6) {
				if (random == 0) {
					probailitytext1 = "この人の言うことは信じられる。";
					probailitytext2 = "勝てる気がする。";
				} else if (random == 1) {
					probailitytext1 = "この人の言うことは嘘だ。";
					probailitytext2 = "負ける気がする。";
				}
			} else if (probability == 7) {
				if (random == 0) {
					probailitytext1 = "この人の言うことは信じられる。";
					probailitytext2 = "勝てる気がする。";
				} else if (random == 1) {
					probailitytext1 = "この人の言うことは嘘だ。";
					probailitytext2 = "負ける気がする。";
				}
			}
		}
	}

	public static void sethp(int hp) {
		Player.hp = hp;
	}

	public static void setdamage(int damage) {
		Player.damage = damage;
	}

	public static void setpower(int power) {
		Player.power = power;
	}

	public static void setinsight(int insight) {
		Player.insight = insight;
	}

	public static void setmoney(int money) {
		Player.money = money;
	}

	public static void setitem(String item) {
		Player.item = item;
	}

	public static void setadd(int add, int id) {
		if (id == 0) {
			damage -= Player.add[0];
			power -= Player.add[1];
			insight -= Player.add[2];
			Player.add[0] = add;
			damage += Player.add[0];
			Player.add[1] = 0;
			Player.add[2] = 0;
			Player.addadd[0] = "+<font color=\"#a67e4e\">" + Player.add[0] + "</font>";
			Player.addadd[1] = "";
			Player.addadd[2] = "";
		} else if (id == 1) {
			damage -= Player.add[0];
			power -= Player.add[1];
			insight -= Player.add[2];
			Player.add[1] = add;
			power += Player.add[1];
			Player.add[0] = 0;
			Player.add[2] = 0;
			Player.addadd[0] = "";
			Player.addadd[1] = "+<font color=\"#a67e4e\">" + Player.add[1] + "</font>";
			Player.addadd[2] = "";
		} else if (id == 2) {
			damage -= Player.add[0];
			power -= Player.add[1];
			insight -= Player.add[2];
			Player.add[2] = add;
			insight += Player.add[2];
			Player.add[0] = 0;
			Player.add[1] = 0;
			Player.addadd[0] = "";
			Player.addadd[1] = "";
			Player.addadd[2] = "+<font color=\"#a67e4e\">" + Player.add[2] + "</font>";
		} else if (id == 3) {
			damage -= Player.add[0];
			power -= Player.add[1];
			insight -= Player.add[2];
			Player.add[0] = 0;
			Player.add[1] = 0;
			Player.add[2] = 0;
			Player.addadd[0] = "";
			Player.addadd[1] = "";
			Player.addadd[2] = "";
		} else if (id == 4) {
			damage -= Player.add[0];
			power -= Player.add[1];
			insight -= Player.add[2];
			Player.add[0] = 0;
			Player.add[1] = 0;
			Player.add[2] = 0;
			Player.addadd[0] = "";
			Player.addadd[1] = "";
			Player.addadd[2] = "";
		}
	}

}
