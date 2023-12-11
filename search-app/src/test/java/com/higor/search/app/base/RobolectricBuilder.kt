package com.higor.search.app.base

import android.app.Activity
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import org.junit.Assert
import org.robolectric.Robolectric
import org.robolectric.shadows.ShadowActivity
import kotlin.reflect.KClass

internal inline fun <reified T : Activity> robolectricBuilder(intent: Intent = Intent()): T =
    Robolectric.buildActivity(T::class.java, intent).create().resume().get()

internal fun RecyclerView.onExpand(): RecyclerView {
    this.measure(0, 0)
    this.layout(0, 0, 1000, 1000)
    return this
}

internal fun ShadowActivity?.assertNextActivity(kClass: KClass<*>) {
    val nextActivity = this?.peekNextStartedActivity()?.component?.className
    kClass.qualifiedName.assertEquals(nextActivity)

}

internal infix fun <T, A> T.assertEquals(obj: A) {
    Assert.assertEquals(this, obj)
}
