package com.s.karpardaz.base.finance

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.s.karpardaz.base.ui.BaseFragment
import com.s.karpardaz.cost.model.CostViewItem
import com.s.karpardaz.databinding.FragmentFinListBinding

abstract class BaseFinFragment<L : BaseFinanceInteractionListener> : BaseFragment<L, FragmentFinListBinding>() {

    private lateinit var adapter: FinanceListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentFinListBinding.inflate(inflater, container, false)
        return binding.root
    }

    public override fun onViewCreated(savedInstanceState: Bundle?) {
        initList()
        initView()
    }

    private fun initList() {
        adapter = FinanceListAdapter(this::onItemClicked)
        binding.fragmentCostListList.layoutManager = LinearLayoutManager(ctx);
        binding.fragmentCostListList.itemAnimator = DefaultItemAnimator()
        binding.fragmentCostListList.addItemDecoration(DividerItemDecoration(ctx, DividerItemDecoration.HORIZONTAL))
        binding.fragmentCostListList.adapter = adapter
        binding.fragmentCostListList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0 && binding.fragmentCostListAddAction.isShown)
                    binding.fragmentCostListAddAction.hide()
                else if (dy < 0 && !binding.fragmentCostListAddAction.isShown)
                    binding.fragmentCostListAddAction.show()
            }
        })

    }

    private fun onItemClicked(item: CostViewItem, position: Int){

    }

    private fun initView() {}

}