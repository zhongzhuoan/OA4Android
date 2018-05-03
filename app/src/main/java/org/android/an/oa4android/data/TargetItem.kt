package org.android.an.oa4android.data

import com.chad.library.adapter.base.entity.AbstractExpandableItem
import com.chad.library.adapter.base.entity.MultiItemEntity
import org.android.an.oa4android.TargetAdapter
import java.util.ArrayList

object TargetContent {
    /**
     * An array of sample (dummy) items.
     */
    val ITEMS: MutableList<MultiItemEntity> = ArrayList()

    private val COUNT = 5

    init {
        // Add some sample items.
        addItem(createTargetHeadItem("本年度目标"))
        addItem(createTargetHeadItem("本季度目标"))
        addItem(createTargetHeadItem("本月目标"))
        addItem(createTargetHeadItem("本周目标"))
    }

    private fun addItem(item: MultiItemEntity) {
        ITEMS.add(item)
    }

    private fun createTargetHeadItem(title: String): MultiItemEntity {
        return TargetHeadItem(title).apply {
            for (i in 1..COUNT) {
                addSubItem(createTargetItem(i))
            }
        }
    }

    private fun createTargetItem(position: Int): TargetItem {
        val builder = StringBuilder()
        builder.append("Details about Item: ").append(position)
        for (i in 0..position - 1) {
            builder.append("\nMore details information here.")
        }
        return TargetItem(builder.toString(), position*10.toFloat(), "2018.05.03")
    }
}

class TargetHeadItem(var title: String) : AbstractExpandableItem<TargetItem>(), MultiItemEntity {
    override fun getLevel(): Int = 0
    override fun getItemType(): Int = TargetAdapter.TYPE_HEAD
}

class TargetItem(var content: String, var progress: Float, var date: String) : MultiItemEntity {
    override fun getItemType(): Int = TargetAdapter.TYPE_ITEM
}