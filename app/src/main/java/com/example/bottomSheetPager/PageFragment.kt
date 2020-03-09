package com.example.bottomSheetPager

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.fragment_content.view.*

class PageFragment : Fragment() {

    private lateinit var pageTitle: String

    companion object {
        private const val ITEM_COUNT = 30

        fun newInstance(): PageFragment {
            return PageFragment()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_content, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initArguments(savedInstanceState)
        val recyclerView = view.recycler_view
        recyclerView.adapter = Adapter(requireContext())
    }

    private fun initArguments(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            return
        }
    }

    class Adapter(context: Context) : RecyclerView.Adapter<ViewHolder>() {
        private val inflater = LayoutInflater.from(context)

        override fun getItemCount(): Int {
            return ITEM_COUNT
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = inflater.inflate(R.layout.list_item, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            @SuppressLint("SetTextI18n")
            val listTex = holder.containerView.findViewById<TextView>(R.id.listText)
            listTex.text = "Item ${position + 1}"
        }
    }

    class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer


}