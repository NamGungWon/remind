package com.wony.remind.main.add

import com.wony.remind.R
import com.wony.remind.base.BaseFragment
import com.wony.remind.databinding.FragmentAddBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddFragment: BaseFragment<FragmentAddBinding, AddVM>() {

    override val layoutId = R.layout.fragment_add
    override val viewModel: AddVM by viewModel()

    override fun init() {

    }
}