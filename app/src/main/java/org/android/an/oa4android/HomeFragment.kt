package org.android.an.oa4android

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 *
 */
class HomeFragment : Fragment() {

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val fragments = ArrayList<TargetsFragment>()
        fragments.add(TargetsFragment.newInstance("公司目标", 1))
        fragments.add(TargetsFragment.newInstance("部门目标", 1))
        fragments.add(TargetsFragment.newInstance("我的目标", 1))

        home_pager.adapter = MyPagerAdapter(childFragmentManager, fragments)

        home_tab.setupWithViewPager(home_pager)
    }

    fun onButtonPressed() {
        listener?.onFragmentInteraction()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     *
     */
    interface OnFragmentInteractionListener {
        fun onFragmentInteraction()
    }

    private inner class MyPagerAdapter(fragmentManager: FragmentManager, fragments: ArrayList<TargetsFragment>) : FragmentPagerAdapter(fragmentManager) {
        private val mFragmentList: List<TargetsFragment>

        init {
            mFragmentList = fragments
        }

        override fun getItem(position: Int): Fragment {
            return mFragmentList[position]
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return mFragmentList[position].title
        }
    }

    companion object {
        /**
         *
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                HomeFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
