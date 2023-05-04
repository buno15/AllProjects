package com.bunooboi.aaoj

import android.content.Context
import android.graphics.*
import android.util.Log
import android.view.*
import kotlin.collections.ArrayDeque


class NoteSurfaceView : SurfaceView, SurfaceHolder.Callback {
    private var surfaceHolder: SurfaceHolder? = null
    private var paint: Paint? = null
    private var path: Path? = null
    var color: Int? = null
    var prevBitmap: Bitmap? = null
    private var prevCanvas: Canvas? = null
    private var canvas: Canvas? = null

    var width: Int? = null
    var height: Int? = null

    private val mUndoStack: ArrayDeque<Path> = ArrayDeque<Path>()
    private val mRedoStack: ArrayDeque<Path> = ArrayDeque<Path>()


    constructor(context: Context, surfaceView: SurfaceView) : super(context) {

        surfaceHolder = surfaceView.holder

        /// display の情報（高さ 横）を取得
        val size = Point().also {
            (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.apply {
                getSize(it)
            }
        }

        /// surfaceViewのサイズ
        width = size.x
        height = size.y

        /// 背景を透過させ、一番上に表示
        surfaceHolder!!.setFormat(PixelFormat.TRANSPARENT)
        surfaceView.setZOrderOnTop(true)

        /// コールバック
        surfaceHolder!!.addCallback(this)

        /// ペイント関連の設定
        paint = Paint()
        color = Color.RED
        paint!!.color = color as Int
        paint!!.style = Paint.Style.STROKE
        paint!!.strokeCap = Paint.Cap.ROUND
        paint!!.isAntiAlias = true
        paint!!.strokeWidth = 10F
    }

    private fun draw(pathInfo: PathInfo) {
        canvas = Canvas()

        /// ロックしてキャンバスを取得
        canvas = surfaceHolder!!.lockCanvas()

        //// キャンバスのクリア
        canvas!!.drawColor(0, PorterDuff.Mode.CLEAR)

        /// 前回のビットマップをキャンバスに描画
        canvas!!.drawBitmap(prevBitmap!!, 0F, 0F, null)

        //// pathを描画
        paint!!.color = pathInfo.color
        canvas!!.drawPath(pathInfo.path, paint!!)

        /// ロックを解除
        surfaceHolder!!.unlockCanvasAndPost(canvas)
    }

    /// 画面をタッチしたときにアクションごとに関数を呼び出す
    fun onTouch(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> touchDown(event.x, event.y)
            MotionEvent.ACTION_MOVE -> touchMove(event.x, event.y)
            MotionEvent.ACTION_UP -> touchUp(event.x, event.y)
        }
        return true
    }

    ///// path クラスで描画するポイントを保持
    ///    ACTION_DOWN 時の処理
    private fun touchDown(x: Float, y: Float) {
        path = Path()
        path!!.moveTo(x, y)
    }

    ///    ACTION_MOVE 時の処理
    private fun touchMove(x: Float, y: Float) {
        /// pathクラスとdrawメソッドで線を書く
        path!!.lineTo(x, y)
        draw(PathInfo(path!!, color!!))
    }

    ///    ACTION_UP 時の処理
    private fun touchUp(x: Float, y: Float) {
        /// pathクラスとdrawメソッドで線を書く
        path!!.lineTo(x, y)
        draw(PathInfo(path!!, color!!))
        /// 前回のキャンバスを描画
        prevCanvas!!.drawPath(path!!, paint!!)
        mUndoStack.addLast(path!!);
        mRedoStack.clear();
    }


    private fun clearLastDrawBitmap() {
        if (prevBitmap == null) {
            prevBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888)
        }
        if (prevCanvas == null) {
            prevCanvas = Canvas(prevBitmap!!)
        }
        prevCanvas!!.drawColor(0, PorterDuff.Mode.CLEAR)
    }

    fun undo() {
        if (mUndoStack.isEmpty()) {
            return
        }

        // undoスタックからパスを取り出し、redoスタックに格納します。
        val lastUndoPath = mUndoStack.removeLast()
        mRedoStack.addLast(lastUndoPath)

        // ロックしてキャンバスを取得します。
        val canvas: Canvas = surfaceHolder!!.lockCanvas()

        // キャンバスをクリアします。
        canvas.drawColor(0, PorterDuff.Mode.CLEAR)

        // 描画状態を保持するBitmapをクリアします。
        clearLastDrawBitmap()

        // パスを描画します。
        for (path in mUndoStack) {
            canvas.drawPath(path, paint!!)
            prevCanvas!!.drawPath(path, paint!!)
        }

        // ロックを外します。
        surfaceHolder!!.unlockCanvasAndPost(canvas)
    }

    fun redo() {
        if (mRedoStack.isEmpty()) {
            return
        }

        // redoスタックからパスを取り出し、undoスタックに格納します。
        val lastRedoPath = mRedoStack.removeLast()
        mUndoStack.addLast(lastRedoPath)

        draw(PathInfo(lastRedoPath, color!!))
        // パスを描画します。
        prevCanvas!!.drawPath(lastRedoPath, paint!!)
    }

    fun reset() {
        mUndoStack.clear()
        mRedoStack.clear()
        clearLastDrawBitmap()
        val canvas: Canvas = surfaceHolder!!.lockCanvas()
        canvas.drawColor(0, PorterDuff.Mode.CLEAR)
        surfaceHolder!!.unlockCanvasAndPost(canvas)
    }

    fun invisible() {
        val canvas: Canvas = surfaceHolder!!.lockCanvas()
        canvas.drawColor(0, PorterDuff.Mode.CLEAR)
        surfaceHolder!!.unlockCanvasAndPost(canvas)
    }

    fun visible() {
        val canvas: Canvas = surfaceHolder!!.lockCanvas()

        // キャンバスをクリアします。
        canvas.drawColor(0, PorterDuff.Mode.CLEAR)

        // 描画状態を保持するBitmapをクリアします。
        clearLastDrawBitmap()

        // パスを描画します。
        for (path in mUndoStack) {
            Log.v("", path.toString())
            canvas.drawPath(path, paint!!)
            prevCanvas!!.drawPath(path, paint!!)
        }

        // ロックを外します。
        surfaceHolder!!.unlockCanvasAndPost(canvas)
    }


    override fun surfaceCreated(holder: SurfaceHolder) {
        initializeBitmap()
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        prevBitmap!!.recycle()
    }

    private fun initializeBitmap() {
        if (prevBitmap == null) {
            prevBitmap = Bitmap.createBitmap(width!!, height!!, Bitmap.Config.ARGB_8888)
        }

        if (prevCanvas == null) {
            prevCanvas = Canvas(prevBitmap!!)
        }

        prevCanvas!!.drawColor(0, PorterDuff.Mode.CLEAR)
    }
}

data class PathInfo(var path: Path, var color: Int)