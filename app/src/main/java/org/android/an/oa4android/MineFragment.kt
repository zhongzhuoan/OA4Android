package org.android.an.oa4android

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.android.an.oa4android.dummy.DummyContent
import org.android.an.oa4android.dummy.DummyContent.DummyItem

/**
 *
 */
class MineFragment : Fragment() {

    private lateinit var columnCount : String

    private var listener: OnListFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            columnCount = it.getString(ARG_TYPE)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_mine, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
                adapter = MineAdapter(DummyContent.ITEMS, listener)
            }
        }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     *
     */
    interface OnListFragmentInteractionListener {
        fun onListFragmentInteraction(item: DummyItem?)
    }

    companion object {

        const val TYPE_ANNUAL = "annual"    //年度
        const val TYPE_QUARTER = "quarter"  //季度
        const val TYPE_MONTHLY = "monthly"  //月度
        const val TYPE_WEEKLY = "weekly"    //周

        private const val ARG_TYPE = "type"

        @JvmStatic
        fun newInstance(type: String) =
                MineFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_TYPE, type)
                    }
                }
    }
}
