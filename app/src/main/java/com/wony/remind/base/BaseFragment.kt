package com.wony.remind.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController

abstract class BaseFragment<B : ViewDataBinding, V: BaseVM>: Fragment() {

    lateinit var binding : B
    abstract val layoutId: Int
    abstract val viewModel: V

    private var toast: Toast? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.toastFlag.observe(viewLifecycleOwner, {
            toast?.cancel()

            toast = Toast.makeText(context, it, Toast.LENGTH_SHORT)
            toast?.show()
        })

        viewModel.backStackFlag.observe(viewLifecycleOwner, {
            backStack()
        })

        init()
    }

    fun moveNavi(id: Int){
        findNavController().navigate(id)
    }

    fun moveNavi(action: NavDirections){
        findNavController().navigate(action)
    }

    fun backStack(){
        findNavController().popBackStack()
    }

    abstract fun init()
}