package alfianyusufabdullah.garap

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_main.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        scrollContent.setOnScrollChangeListener { _: NestedScrollView?, _: Int, scrollY: Int, _: Int, _: Int ->
            val colorBackground: Int = if (scrollY < 255) {
                Color.argb(scrollY, 255, 255, 255)
            } else {
                Color.argb(255, 255, 255, 255)
            }

            mainToolbar.setBackgroundColor(colorBackground)
        }

        val layoutManager = LinearLayoutManager(this)
        layoutManager.isAutoMeasureEnabled

        val gridLayoutManager = GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false)

        listService.hasFixedSize()
        listService.layoutManager = gridLayoutManager
        listService.adapter = MainAdapter(
            itemCount = 7,
            createHolder = { parent, _ ->
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_sub_service, parent, false)
                ServiceHolder(view)
            },
            bindHolder = { holder, _ ->
                val width = holder.itemView.context.resources.displayMetrics.widthPixels / 3
                holder.itemView.layoutParams.width = width - 7.toPx(holder.itemView.context)
                holder.itemView.requestLayout()
            }
        )

        GravitySnapHelper(Gravity.START).attachToRecyclerView(listService)

        listItem.hasFixedSize()
        listItem.layoutManager = LinearLayoutManager(this)
        listItem.adapter = MainAdapter(
            itemCount = 5,
            createHolder = { parent, _ ->
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false)
                ItemHolder(view)
            },
            bindHolder = { holder, _ ->
                val listMain = holder.itemView.listMain
                listMain.hasFixedSize()
                listMain.layoutManager = LinearLayoutManager(listMain.context, LinearLayoutManager.HORIZONTAL, false)
                listMain.adapter = MainAdapter(
                    itemCount = 7,
                    createHolder = { parent, _ ->
                        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_sub_main, parent, false)
                        ItemHolder(view)
                    },
                    bindHolder = { subHolder, _ ->
                        val width = subHolder.itemView.context.resources.displayMetrics.widthPixels

                        subHolder.itemView.layoutParams.width = width - 90.toPx(subHolder.itemView.context)
                        subHolder.itemView.requestLayout()
                    }
                )

                PagerSnapHelper().attachToRecyclerView(listMain)
            }
        )
    }

    inner class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    inner class ServiceHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}