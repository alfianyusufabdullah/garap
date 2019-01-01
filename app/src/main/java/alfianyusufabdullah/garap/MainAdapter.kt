package alfianyusufabdullah.garap

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MainAdapter<H : RecyclerView.ViewHolder>(
    private val itemCount: Int,
    private val createHolder: (ViewGroup, Int) -> H,
    private val bindHolder: (H, Int) -> Unit
) : RecyclerView.Adapter<H>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): H {
        return createHolder(parent, viewType)
    }

    override fun getItemCount() = itemCount

    override fun onBindViewHolder(holder: H, position: Int) {
        bindHolder(holder, position)
    }
}