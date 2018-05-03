package org.android.an.oa4android

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.entity.MultiItemEntity
import org.android.an.oa4android.data.TargetHeadItem
import org.android.an.oa4android.data.TargetItem

class TargetAdapter(data: MutableList<MultiItemEntity>?) : BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder>(data) {


    override fun convert(holder: BaseViewHolder?, item: MultiItemEntity?) {
        holder?.apply {
            when(item){
                is TargetHeadItem -> {
                    setText(R.id.item_head, item.title)
                }
                is TargetItem -> {
                    setText(R.id.item_number, adapterPosition.toString())
                    setText(R.id.content, item.content)
                    setProgress(R.id.progress_bar, item.progress.toInt(), 100)
                    setText(R.id.progress_text, item.progress.toString() + "%")
                    setText(R.id.date, item.date)
                }
            }
        }

    }

    init {
        addItemType(TYPE_HEAD, R.layout.item_target_head)
        addItemType(TYPE_ITEM, R.layout.item_target)
    }

    companion object {
        const val TYPE_HEAD: Int = 0
        const val TYPE_ITEM: Int = 1
    }

}