package com.gamerguide.android.starterapp.helpers

import android.animation.Animator
import android.app.Activity
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.BlurTransformation
import com.squareup.picasso.MemoryPolicy
import android.util.TypedValue
import android.animation.ObjectAnimator
import android.animation.AnimatorSet
import android.animation.AnimatorListenerAdapter
import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView

class BlurHelper {
    fun setupImageBlurBackground(activity: Activity?, background: ImageView) {
        val id = ThemeHelper.getRandomTheme().toString();
        Picasso.get()
            .load(getGameImageURL(id))
            .transform(BlurTransformation(activity))
            .into(background)
        setAlphaAnimation(background)
    }

    fun picassoLoad(imageID: Int, H: Int, V: Int, activity: Activity, image: ImageView?) {
        Picasso.get().load(imageID)
            .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
            .resize(
                getPixelfromDP(activity, H),
                getPixelfromDP(activity, V)
            ).into(image)
    }

    fun getPixelfromDP(activity: Activity, dp: Int): Int {
        val r = activity.resources
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            r.displayMetrics
        ).toInt()
    }

    @SuppressLint("Recycle")
    private fun setAlphaAnimation(v: View) {
        val fadeOut = ObjectAnimator.ofFloat(v, "alpha", 1f, 0f)
        fadeOut.duration = 2000
        val fadeIn = ObjectAnimator.ofFloat(v, "alpha", 0f, 1f)
        fadeIn.duration = 2000
        val mAnimationSet = AnimatorSet()
        mAnimationSet.play(fadeIn)
        mAnimationSet.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
            }
        })
        mAnimationSet.start()
    }

    companion object {
        fun getGameImageURL(gameID: String): String {
            return "https://steamcdn-a.akamaihd.net/steam/apps/$gameID/header.jpg"
        }
    }
}