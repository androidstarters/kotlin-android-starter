package io.mvpstarter.sample.util

import android.app.Activity
import android.content.Context
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Point
import android.graphics.Rect
import android.graphics.drawable.Drawable
import com.google.android.material.snackbar.Snackbar
import androidx.fragment.app.Fragment
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by shivam on 8/7/17.
 */

fun ImageView.loadImageFromUrl(url: String) {
    Glide.with(context)
            .load(url)
            .into(this)
}

fun Activity.hideKeyboard() {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(window.decorView.windowToken, 0)
}

/**
 * Extension method to remove the required boilerplate for running code after a view has been
 * inflated and measured.
 *
 * @author Antonio Leiva
 * @see <a href="https://antonioleiva.com/kotlin-ongloballayoutlistener/>Kotlin recipes: OnGlobalLayoutListener</a>
 */
inline fun <T : View> T.afterMeasured(crossinline f: T.() -> Unit) {
    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            if (measuredWidth > 0 && measuredHeight > 0) {
                viewTreeObserver.removeOnGlobalLayoutListener(this)
                f()
            }
        }
    })
}

/**
 * Extension method to simplify the code needed to apply spans on a specific sub string.
 */
inline fun SpannableStringBuilder.withSpan(vararg spans: Any, action: SpannableStringBuilder.() -> Unit):
        SpannableStringBuilder {
    val from = length
    action()

    for (span in spans) {
        setSpan(span, from, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    }

    return this
}

/**
 * Extension method to provide simpler access to {@link ContextCompat#getColor(int)}.
 */
fun Context.getColorCompat(color: Int) = ContextCompat.getColor(this, color)

/**
 *  Extension method to provide simpler access to {@link ContextCompat#getColor(int)}
 *  from a [Fragment].
 */
fun Fragment.getColorCompat(color: Int) = context?.getColorCompat(color)

/**
 * Extension method to provide simpler access to {@link ContextCompat#getDrawableCompat(int)}.
 */
fun Context.getDrawableCompat(drawableResId: Int): Drawable? = ContextCompat
        .getDrawable(this, drawableResId)

/**
 * Extension method to provide simpler access to {@link ContextCompat#getDrawableCompat(int)}
 * from a [Fragment].
 */
fun Fragment.getDrawableCompat(drawableResId: Int) = context?.getDrawableCompat(drawableResId)!!

/**
 * Extension method to provide simpler access to {@link View#getResources()#getString(int)}.
 */
fun View.getString(stringResId: Int): String = resources.getString(stringResId)

/**
 * Extension method to provide show keyboard for View.
 */
fun View.showKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    this.requestFocus()
    imm.showSoftInput(this, 0)
}

/**
 * Extension method to provide hide keyboard for [Activity].
 */
fun Activity.hideSoftKeyboard() {
    if (currentFocus != null) {
        val inputMethodManager = getSystemService(Context
                .INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
    }
}

/**
 * Extension method to provide hide keyboard for [Fragment].
 */
fun Fragment.hideSoftKeyboard() {
    activity?.hideSoftKeyboard()
}

/**
 * Extension method to provide hide keyboard for [View].
 */
fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

/**
 * Extension method to int time to 2 digit String
 */
fun Int.twoDigitTime() = if (this < 10) "0" + toString() else toString()

/**
 * Extension method to provide quicker access to the [LayoutInflater] from [Context].
 */
fun Context.getLayoutInflater() = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

/**
 * Extension method to provide quicker access to the [LayoutInflater] from a [View].
 */
fun View.getLayoutInflater() = context.getLayoutInflater()

/**
 * Extension method to replace all text inside an [Editable] with the specified [newValue].
 */
fun Editable.replaceAll(newValue: String) {
    replace(0, length, newValue)
}

/**
 * Extension method to replace all text inside an [Editable] with the specified [newValue] while
 * ignoring any [android.text.InputFilter] set on the [Editable].
 */
fun Editable.replaceAllIgnoreFilters(newValue: String) {
    val currentFilters = filters
    filters = emptyArray()
    replaceAll(newValue)
    filters = currentFilters
}

/**
 * Extension method to cast a char with a decimal value to an [Int].
 */
fun Char.decimalValue(): Int {
    if (!isDigit())
        throw IllegalArgumentException("Out of range")
    return this.toInt() - '0'.toInt()
}

/**
 * Extension method to simplify view binding.
 */
fun <T : ViewDataBinding> View.bind() = DataBindingUtil.bind<T>(this) as T

/**
 * Extension method to simplify view inflating and binding inside a [ViewGroup].
 *
 * e.g.
 * This:
 *<code>
 *     binding = bind(R.layout.widget_card)
 *</code>
 *
 * Will replace this:
 *<code>
 *     binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.widget_card, this, true)
 *</code>
 */
fun <T : ViewDataBinding> ViewGroup.bind(layoutId: Int): T {
    return DataBindingUtil.inflate(getLayoutInflater(), layoutId, this, true)
}

fun <T : ViewDataBinding> Activity.bind(layoutId: Int): T {
    return DataBindingUtil.setContentView(this, layoutId)
}

fun String.dateInFormat(format: String): Date? {
    val dateFormat = SimpleDateFormat(format, Locale.US)
    var parsedDate: Date? = null
    try {
        parsedDate = dateFormat.parse(this)
    } catch (ignored: ParseException) {
        ignored.printStackTrace()
    }
    return parsedDate
}

fun getClickableSpan(color: Int, action: (view: View) -> Unit): ClickableSpan {

    return object : ClickableSpan() {
        override fun onClick(view: View) {
            action(view)
        }

        override fun updateDrawState(ds: TextPaint) {
            super.updateDrawState(ds)
            ds.color = color
        }
    }
}

/**
 * Extension method to be used as the body for functions that are not yet implemented, which will
 * display a [Toast] with the specified [message].
 */
fun Fragment.NOT_IMPL(message: String = "This action is not implemented yet!") {
    TOAST(message)
}

/**
 * Extension method used to display a [Toast] message to the user.
 */
fun Fragment.TOAST(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, message, duration).show()
}

/**
 * Extension method used to display a [Toast] message to the user.
 */
fun Fragment.TOAST(messageResId: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, messageResId, duration).show()
}

/**
 * Extension method use to display a [Snackbar] message to the user.
 */
fun View.displaySnakbar(message: String, duration: Int = Snackbar.LENGTH_SHORT): Snackbar {
    val snakbar = Snackbar.make(this, message, duration)
    snakbar.show()
    return snakbar
}

/**
 * Extension method use to display a [Snackbar] message to the user.
 */
fun View.displaySnakbar(messageResId: Int, duration: Int = Snackbar.LENGTH_SHORT): Snackbar {
    val snakbar = Snackbar.make(this, messageResId, duration)
    snakbar.show()
    return snakbar
}

/**
 * Extension method to return the view location on screen as a [Point].
 */
fun View.locationOnScreen(): Point {
    val location = IntArray(2)
    getLocationOnScreen(location)
    return Point(location[0], location[1])
}

/**
 * Extension method to return the view location in window as a [Point].
 */
fun View.locationInWindow(): Point {
    val location = IntArray(2)
    getLocationInWindow(location)
    return Point(location[0], location[1])
}

/**
 * Extension method used to return the value of the specified float raised to the power
 * of the specified [exponent].
 */
fun Float.pow(exponent: Float) = Math.pow(this.toDouble(), exponent.toDouble()).toFloat()

/**
 * Extension method to provide show keyboard for View.
 */
fun View.gone() {
    if (visibility != View.GONE) {
        visibility = View.GONE
    }
}

/**
 * Extension method to provide show keyboard for View.
 */
fun View.visible() {
    if (visibility != View.VISIBLE) {
        visibility = View.VISIBLE
    }
}

/**
 * Convert a [Boolean] value to a view visibility [Int].
 */
fun Boolean.toViewVisibility(valueForFalse: Int = View.GONE): Int {
    return if (this) {
        View.VISIBLE
    } else {
        valueForFalse
    }
}

/**
 * Method used to easily retrieve [WindowManager] from [Context].
 */
fun Context.getWindowManager() = getSystemService(Context.WINDOW_SERVICE) as WindowManager

/**
 * Method used to easily retrieve display size from [Context].
 */
fun Context.getDisplaySize() = Point().apply {
    getWindowManager().defaultDisplay.getSize(this)
}

/**
 * Method used to easily retrieve display size from [View].
 */
fun View.getDisplaySize() = context.getDisplaySize()

/**
 * Provide the ability to snap to a specified [position] in the specified [recyclerView]
 * using [SnapHelper].
 */
fun SnapHelper.snapToPosition(recyclerView: RecyclerView, position: Int) {
    recyclerView.apply {
        val view = findViewHolderForAdapterPosition(position)?.itemView
        val snapPositions = view?.let {
            calculateDistanceToFinalSnap(layoutManager as RecyclerView.LayoutManager, it)
        }

        snapPositions?.let { smoothScrollBy(it[0], it[1]) }
    }
}

/**
 * Return whether Keyboard is currently visible on screen or not.
 *
 * @return true if keyboard is visible.
 */
fun Activity.isKeyboardVisible(): Boolean {
    val r = Rect()

    //r will be populated with the coordinates of your view that area still visible.
    window.decorView.getWindowVisibleDisplayFrame(r)

    //get screen height and calculate the difference with the usable area from the r
    val height = getDisplaySize().y
    val diff = height - r.bottom

    // If the difference is not 0 we assume that the keyboard is currently visible.
    return diff != 0
}

/**
 * Provides simpler access to the [ViewTreeObserver] inside a fragment.
 *
 * @return the [ViewTreeObserver] of the [Activity] this fragment currently attached to, or null
 * if the fragment is detached.
 */
fun Fragment.getViewTreeObserver() = activity?.window?.decorView?.viewTreeObserver

/**
 * Extension method to set width for View.
 */
fun View.setWidth(value: Int) {
    val lp = layoutParams
    lp?.let {
        lp.width = value
        layoutParams = lp
    }
}

/**
 * Extension method to display Width for Context.
 */
fun Context.displayWidth(): Int = getDisplaySize().x

/**
 * Retrieve a decoded bitmap from resources, or null if the image could not be decoded.
 */
fun Context.decodeBitmap(resId: Int): Bitmap? = BitmapFactory.decodeResource(resources, resId)
