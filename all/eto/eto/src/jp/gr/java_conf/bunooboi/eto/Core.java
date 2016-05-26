package jp.gr.java_conf.bunooboi.eto;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

public class Core extends SurfaceView implements SurfaceHolder.Callback {
	SurfaceHolder	holder;
	Resources		res;
	Canvas			canvas;
	final int		dispw;
	final int		disph;
	int				w		= 50, h = 20;
	Bitmap			map[][]	= new Bitmap[h][w];
	int				x, y;

	int				oldx;
	int				oldy;

	public Core(Context context) {
		super(context);

		res = this.getContext().getResources();
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display disp = wm.getDefaultDisplay();
		Point size = new Point();
		disp.getSize(size);
		dispw = size.x;
		disph = size.y;

		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if (j % 2 == 0)
					map[i][j] = BitmapFactory.decodeResource(res, R.drawable.chip3);
				else
					map[i][j] = BitmapFactory.decodeResource(res, R.drawable.chip2);
				map[i][j] = Bitmap.createScaledBitmap(map[i][j], dispw / 32, disph / 16, true);
			}
		}

		holder = getHolder();
		holder.addCallback(this);
		setFocusable(true);
		requestFocus();
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		Draw();
	}

	public void Draw() {
		canvas = holder.lockCanvas();
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				canvas.drawBitmap(map[i][j], x, y, null);
				x += map[i][j].getWidth();
			}
			x = 0;
			y += map[i][i].getHeight();
		}
		holder.unlockCanvasAndPost(canvas);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int touchx = (int) event.getX();
		int touchy = (int) event.getY();
		if (event.getAction() == MotionEvent.ACTION_MOVE) {
			int left = Core.this.getLeft() + (touchx - oldx);
			int top = Core.this.getTop() + (touchy - oldy);
			// Viewを移動する
			Core.this.layout(left, top, left + Core.this.getWidth(), top + Core.this.getHeight());
		}
		oldx = touchx;
		oldy = touchy;
		return true;
	}
}
