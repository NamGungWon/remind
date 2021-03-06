package com.wony.remind.main.list

import androidx.navigation.fragment.findNavController
import com.wony.remind.R
import com.wony.remind.base.BaseFragment
import com.wony.remind.databinding.FragmentAddBinding
import com.wony.remind.databinding.FragmentListBinding
import com.wony.remind.main.add.AddVM
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListFragment : BaseFragment<FragmentListBinding, ListVM>() {

    override val layoutId = R.layout.fragment_list
    override val viewModel: ListVM by viewModel()

    private var adapter = ListItemAdapter(this)

    override fun init() {

        binding.rvList.adapter = adapter

        viewModel.remindItems.observe(viewLifecycleOwner, {
            adapter.items = it
        })

        binding.btAdd.setOnClickListener {
            moveNavi(R.id.action_listFragment_to_addFragment)
        }
    }

    fun moveAdd(id: Long){
        var action = ListFragmentDirections.actionListFragmentToAddFragment(id)
        moveNavi(action)
    }
}