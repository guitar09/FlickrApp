package com.higor.search.app.utils

import android.os.Build
import android.view.View
import android.widget.Button
import androidx.core.view.AccessibilityDelegateCompat
import androidx.core.view.ViewCompat
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat

internal object AccessibilityUtils {

    private fun setClassAccessibilityView(view: View?, className: String?) {

        view?.let { viewComponent ->
            ViewCompat.setAccessibilityDelegate(
                viewComponent,
                object : AccessibilityDelegateCompat() {
                    override fun onInitializeAccessibilityNodeInfo(
                        host: View,
                        info: AccessibilityNodeInfoCompat
                    ) {
                        super.onInitializeAccessibilityNodeInfo(host, info)
                        info.className = className
                    }
                })
        }
    }

    fun View.setViewAsButtonForAccessibility() {
        setClassAccessibilityView(this, Button::class.java.name)
    }

    fun View.setAsHeadingForAccessibility() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            this.isAccessibilityHeading = true
        } else {
            ViewCompat.setAccessibilityDelegate(
                this,
                object : AccessibilityDelegateCompat() {
                    override fun onInitializeAccessibilityNodeInfo(
                        host: View,
                        info: AccessibilityNodeInfoCompat
                    ) {
                        super.onInitializeAccessibilityNodeInfo(host, info)
                        info.isHeading = true
                    }
                })
        }
    }
}
