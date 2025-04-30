package fudex.bonyad.Helper

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Movie
import android.os.Build
import android.os.SystemClock
import android.util.AttributeSet
import android.view.View


class PlayGifView @SuppressLint("NewApi") constructor(context: Context?, attrs: AttributeSet?) :
    View(context, attrs) {
    private var mMovieResourceId = 0
    private var mMovie: Movie? = null
    private var mMovieStart: Long = 0
    private var mCurrentAnimationTime = 0

    init {
        /**
         * Starting from HONEYCOMB have to turn off HardWare acceleration to draw
         * Movie on Canvas.
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            setLayerType(LAYER_TYPE_SOFTWARE, null)
        }
    }

    fun setImageResource(mvId: Int) {
        mMovieResourceId = mvId
        mMovie = Movie.decodeStream(resources.openRawResource(mMovieResourceId))
        requestLayout()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (mMovie != null) {
            setMeasuredDimension(mMovie!!.width(), mMovie!!.height())
        } else {
            setMeasuredDimension(suggestedMinimumWidth, suggestedMinimumHeight)
        }
    }

    override fun onDraw(canvas: Canvas) {
        if (mMovie != null) {
            updateAnimtionTime()
            drawGif(canvas)
            invalidate()
        } else {
            drawGif(canvas)
        }
    }

    private fun updateAnimtionTime() {
        val now = SystemClock.uptimeMillis()
        if (mMovieStart == 0L) {
            mMovieStart = now
        }
        var dur = mMovie!!.duration()
        if (dur == 0) {
            dur = DEFAULT_MOVIEW_DURATION
        }
        mCurrentAnimationTime = ((now - mMovieStart) % dur).toInt()
    }

    private fun drawGif(canvas: Canvas) {
        mMovie!!.setTime(mCurrentAnimationTime)
        mMovie!!.draw(canvas, 0f, 0f)
        canvas.restore()
    }

    companion object {
        private const val DEFAULT_MOVIEW_DURATION = 1000
    }
}