package com.chintankhetiya.mvvm.architectural.utils

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.chintankhetiya.mvvm.architectural.utils.RecyclerItemClickListener

@JvmOverloads
fun RecyclerView.affectOnItemClicks(
    onClick: ((position: Int, view: View) -> Unit)? = null,
    onLongClick: ((position: Int, view: View) -> Unit)? = null
) {
    this.addOnChildAttachStateChangeListener(
        RecyclerItemClickListener(
            this,
            onClick,
            onLongClick
        )
    )
}