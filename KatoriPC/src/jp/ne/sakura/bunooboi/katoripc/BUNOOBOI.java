package jp.ne.sakura.bunooboi.katoripc;

public class BUNOOBOI {
	KA ka; // 自分
	KA other_ka[] = new KA[20]; // ほかの蚊

	int go; // 動作、前進
	int back; // 動作、後進
	int up; // 動作、上がる
	int down; // 動作、下がる
	int none; // 動作、何もしない

	final int KACLASH; // 蚊との衝突回避、得点
	final int WALLCLASH; // 画面端との衝突回避、得点
	final int SPRAYCLASH; // スプレーとの衝突回避、得点
	final int TOUCHCLASH;
	final int INTAKE; // 血の摂取、得点

	final int KA_RANGELIMIT; // 対象との距離
	final int WALL_RANGELIMIT; // 対象との距離
	final int SPRAY_RANGELIMIT; // 対象との距離
	final int TOUCH_RANGELIMIT;

	int kaCount; // 蚊の数

	public BUNOOBOI(KA ka, int kaCount, int ka_rangelimit, int wall_rangelimit, int spray_rangelimit, int touch_rangelimit, int kaclash, int wallclash, int sprayclash,
			int touchclash, int intake) {
		this.ka = ka;
		this.kaCount = kaCount;
		KA_RANGELIMIT = ka_rangelimit;
		WALL_RANGELIMIT = wall_rangelimit;
		SPRAY_RANGELIMIT = spray_rangelimit;
		TOUCH_RANGELIMIT = touch_rangelimit;
		KACLASH = kaclash;
		WALLCLASH = wallclash;
		SPRAYCLASH = sprayclash;
		TOUCHCLASH = touchclash;
		INTAKE = intake;
	}

	public void move(KA ka[]) {
		other_ka = ka;
		SuperEgo();
		System.out.println("go:" + go);
		System.out.println("back:" + back);
		System.out.println("up:" + up);
		System.out.println("down:" + down);
		System.out.println("none:" + none);
		if (Play.incense != null) {
			if (Play.incense.smoke_tf) {
				go = (int) Math.floor(Math.random() * 100);
				back = (int) Math.floor(Math.random() * 100);
				up = (int) Math.floor(Math.random() * 100);
				down = (int) Math.floor(Math.random() * 100);
			}
		}
		Run();
	}

	void SuperEgo() {
		for (int i = 0; i < kaCount; i++) {// 蚊同士の衝突回避
			if (!(other_ka[i].equals(ka)))
				Clash(ka.ka_x, ka.ka_y, ka.ka_w / 2, ka.ka_h / 2, other_ka[i].ka_x, other_ka[i].ka_y, other_ka[i].ka_w / 2, other_ka[i].ka_h / 2, KA_RANGELIMIT, KACLASH);
		}
		Clash(ka.ka_x, ka.ka_y, ka.ka_w / 2, ka.ka_h / 2, Play.touch_x, Play.touch_y, 10, 10, TOUCH_RANGELIMIT, TOUCHCLASH);
		ClashX(ka.ka_x, ka.ka_w / 2, 0, Play.disp_w, WALL_RANGELIMIT, WALLCLASH);// 画面回避
		ClashX(ka.ka_x, ka.ka_w / 2, 0, 0, WALL_RANGELIMIT, WALLCLASH);// 画面回避
		ClashY(ka.ka_y, ka.ka_h / 2, 0, Play.ui_h, WALL_RANGELIMIT, WALLCLASH);// 画面回避
		ClashY(ka.ka_y, ka.ka_h / 2, 0, Play.disp_h, WALL_RANGELIMIT, WALLCLASH);// 画面回避
		if (Play.spray != null)
			Clash(ka.ka_x, ka.ka_y, ka.ka_w / 2, ka.ka_h / 2, Play.spray.smoke_x, Play.spray.smoke_y, Play.spray.smoke_w, Play.spray.smoke_h / 2, SPRAY_RANGELIMIT, SPRAYCLASH);// スプレー回避
		if ((int) Math.floor(Math.random() * 10) < INTAKE) {// 摂取欲
			go += 100;
		}
	}

	void Run() {
		if (none < go || none < back || none < up || none < down) {
			if (go < back) {
				ka.kav(ka.X, ka.PLUS);
				go--;
			} else if (back < go) {
				ka.kav(ka.X, ka.MINUS);
				back--;
			}
			if (up < down) {
				ka.kav(ka.Y, ka.PLUS);
				up--;
			} else if (down < up) {
				ka.kav(ka.Y, ka.MINUS);
				down--;
			}
		} else {
			ka.kav(ka.X, 0);
			ka.kav(ka.Y, 0);
			go--;
			back--;
			up--;
			down--;
		}
		if (go < 0) {
			go = 0;
		}
		if (back < 0) {
			back = 0;
		}
		if (up < 0) {
			up = 0;
		}
		if (down < 0) {
			down = 0;
		}
		if (none < 0) {
			none = 0;
		}
	}

	void ClashX(int my_x, int my_w, int you_x, int you_w, int rangelimit, int yoku) {
		int x_dif = (my_x + my_w) - (you_x + you_w);
		if (Math.abs(x_dif) <= rangelimit) {
			if ((int) Math.signum(x_dif) == 1) {
				back += Position(x_dif, yoku, rangelimit);
			} else if ((int) Math.signum(x_dif) == -1) {
				go += Position(x_dif, yoku, rangelimit);
			} else if ((int) Math.signum(x_dif) == 0) {
				go += yoku;
			}
		}
	}

	void ClashY(int my_y, int my_h, int you_y, int you_h, int rangelimit, int yoku) {
		int y_dif = (my_y + my_h) - (you_y + you_h);
		if (Math.abs(y_dif) <= rangelimit) {
			if ((int) Math.signum(y_dif) == 1) {
				down += Position(y_dif, yoku, rangelimit);
			} else if ((int) Math.signum(y_dif) == -1) {
				up += Position(y_dif, yoku, rangelimit);
			} else if ((int) Math.signum(y_dif) == 0) {
				up += yoku;
			}
		}
	}

	void Clash(int my_x, int my_y, int my_w_quo, int my_h_quo, int you_x, int you_y, int you_w_quo, int you_h_quo, int rangelimit, int yoku) {
		int x_dif = (my_x + my_w_quo) - (you_x + you_w_quo);
		int y_dif = (my_y + my_h_quo) - (you_y + you_h_quo);
		if (Math.abs(x_dif) <= rangelimit && Math.abs(y_dif) <= rangelimit) {
			if ((int) Math.signum(x_dif) == 1) {
				back += Position(x_dif, yoku, rangelimit);
			} else if ((int) Math.signum(x_dif) == -1) {
				go += Position(x_dif, yoku, rangelimit);
			} else if ((int) Math.signum(x_dif) == 0) {
				go += yoku;
			}
			if ((int) Math.signum(y_dif) == 1) {
				down += Position(y_dif, yoku, rangelimit);
			} else if ((int) Math.signum(y_dif) == -1) {
				up += Position(y_dif, yoku, rangelimit);
			} else if ((int) Math.signum(y_dif) == 0) {
				up += yoku;
			}
		}
	}

	int Position(int dif, int max, int rangelimit) {
		int absdif = Math.abs(dif);
		int punctuation = 0;
		if (absdif > 0 && absdif <= rangelimit) {
			none += -10;
			if (rangelimit <= 50) {
				punctuation = 5;
			} else if (rangelimit <= 100) {
				punctuation = 10;
			} else if (rangelimit <= 500) {
				punctuation = 50;
			} else if (rangelimit <= 1000) {
				punctuation = 100;
			}
			if (absdif > 0 && absdif <= punctuation) {
				return max;
			} else if (punctuation > punctuation * 2 && punctuation * 2 <= punctuation * 3) {
				return max - 2;
			} else if (punctuation * 3 > punctuation * 4 && punctuation * 4 <= punctuation * 5) {
				return max - 4;
			} else if (punctuation * 5 > punctuation * 6 && punctuation * 6 <= punctuation * 7) {
				return max - 6;
			} else if (punctuation * 7 > punctuation * 8 && punctuation * 8 <= punctuation * 9) {
				return max - 8;
			} else if (punctuation * 9 > rangelimit) {
				return max - 10;
			}
		} else {
			none += 1;
			return -max * 2;
		}
		return 0;
	}
}
