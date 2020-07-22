package com.kirchhoff.movies.utils.binding

import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

abstract class ViewBindingProperty<in R, T : ViewBinding>(
    private val viewBinder: (R) -> T
) : ReadOnlyProperty<R, T> {

    internal var viewBinding: T? = null
    private val lifecycleObserver = BindingLifecycleObserver()

    protected abstract fun getLifecycleOwner(thisRef: R): LifecycleOwner

    @MainThread
    override fun getValue(thisRef: R, property: KProperty<*>): T {
        checkIsMainThread()
        viewBinding?.let { return it }

        getLifecycleOwner(thisRef).lifecycle.addObserver(lifecycleObserver)
        return viewBinder(thisRef).also { viewBinding = it }
    }

    private inner class BindingLifecycleObserver : LifecycleObserver {

        private val mainHandler = Handler(Looper.getMainLooper())

        @MainThread
        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun onDestroy(owner: LifecycleOwner) {
            owner.lifecycle.removeObserver(this)
            mainHandler.post {
                viewBinding = null
            }
        }
    }
}

@PublishedApi
internal class ActivityViewBindingProperty<A : AppCompatActivity, T : ViewBinding>(
    viewBinder: (A) -> T
) : ViewBindingProperty<A, T>(viewBinder) {

    override fun getLifecycleOwner(thisRef: A) = thisRef
}

@PublishedApi
internal class FragmentViewBindingProperty<F : Fragment, T : ViewBinding>(
    viewBinder: (F) -> T
) : ViewBindingProperty<F, T>(viewBinder) {

    override fun getLifecycleOwner(thisRef: F) = thisRef.viewLifecycleOwner
}

/**
 * Create new [ViewBinding] associated with the [Activity][this]
 *
 * @param viewBindingRootId Root view's id that will be used as root for the view binding
 */
@Suppress("unused")
inline fun <A : AppCompatActivity, reified T : ViewBinding> A.viewBinding(
    @IdRes viewBindingRootId: Int
): ViewBindingProperty<A, T> {
    val activityViewBinder =
        ActivityViewBinder(T::class.java) { it.requireViewByIdCompat(viewBindingRootId) }
    return viewBinding(activityViewBinder::bind)
}

/**
 * Create new [ViewBinding] associated with the [Activity][this] and allow customize how
 * a [View] will be bounded to the view binding.
 */
@Suppress("unused")
@JvmName("viewBindingActivity")
fun <A : AppCompatActivity, T : ViewBinding> A.viewBinding(viewBinder: (A) -> T): ViewBindingProperty<A, T> {
    return ActivityViewBindingProperty(viewBinder)
}

/**
 * Create new [ViewBinding] associated with the [Fragment][this]
 */
@Suppress("unused")
@JvmName("viewBindingFragment")
inline fun <F : Fragment, reified T : ViewBinding> F.viewBinding(): ViewBindingProperty<Fragment, T> {
    return viewBinding(FragmentViewBinder(T::class.java)::bind)
}

/**
 * Create new [ViewBinding] associated with the [Fragment][this]
 */
@Suppress("unused")
@JvmName("viewBindingFragment")
fun <F : Fragment, T : ViewBinding> F.viewBinding(viewBinder: (F) -> T): ViewBindingProperty<F, T> {
    return FragmentViewBindingProperty(viewBinder)
}
