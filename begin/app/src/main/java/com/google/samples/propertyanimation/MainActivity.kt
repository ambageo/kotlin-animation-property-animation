 /*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.samples.propertyanimation

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.core.animation.addListener
import androidx.databinding.DataBindingUtil
import com.google.samples.propertyanimation.databinding.ActivityMainBinding


 class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.rotateButton.setOnClickListener{
            rotater()
        }

        binding.translateButton.setOnClickListener {
            translater()
        }

        binding.scaleButton.setOnClickListener {
            scaler()
        }

        binding.fadeButton.setOnClickListener {
            fader()
        }

        binding.colorizeButton.setOnClickListener {
            colorizer()
        }

        binding.showerButton.setOnClickListener {
            shower()
        }
    }


     /**
      * Extension function for disabling/ enabling the animations buttons
      */
     private fun ObjectAnimator.disableViewDuringAnimation(view: View){
         addListener(object:AnimatorListenerAdapter(){
             override fun onAnimationStart(animation: Animator?) {
                 view.isEnabled = false
             }

             override fun onAnimationEnd(animation: Animator?, isReverse: Boolean) {
                view.isEnabled = true             }
         })
     }

    private fun rotater() {
        val animator = ObjectAnimator.ofFloat(binding.star, View.ROTATION, -360f, 0f)
        animator.duration = 1000
        animator.disableViewDuringAnimation(binding.rotateButton)
        animator.start()
    }

     private fun translater() {
         val animator = ObjectAnimator.ofFloat(binding.star, View.TRANSLATION_X, 200f)
         // Here we tell the animator to reverse the animation, once. So, after going to the right,
         // it will go back to the start
         animator.repeatCount = 1
         animator.repeatMode = ObjectAnimator.REVERSE
         animator.disableViewDuringAnimation(binding.translateButton)
         animator.start()
    }

    private fun scaler() {
        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 4f)
        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 4f)
        val animator = ObjectAnimator.ofPropertyValuesHolder(binding.star, scaleX, scaleY)
        animator.disableViewDuringAnimation(binding.scaleButton)
        // Again, reverse the animation to go back to the initial state of the star
        animator.repeatCount = 1
        animator.repeatMode = ObjectAnimator.REVERSE
        animator.start()
    }

    private fun fader() {
        val animator = ObjectAnimator.ofFloat(binding.star, View.ALPHA, 0f)
        animator.repeatCount = 1
        animator.repeatMode = ObjectAnimator.REVERSE
        animator.disableViewDuringAnimation(binding.fadeButton)
        animator.start()
    }

    private fun colorizer() {
        // Here we want to change the background color of the star, so star.parent is our view target.
        // The propertyName is what the system searches to find (getters and setters) using reflection.
        val animator = ObjectAnimator.ofArgb(binding.star.parent, "backgroundColor", Color.BLACK, Color.RED)
        animator.duration = 500
        animator.repeatCount = 1
        animator.repeatMode = ObjectAnimator.REVERSE
        animator.disableViewDuringAnimation(binding.colorizeButton)
        animator.start()
    }

    private fun shower() {
    }

}
