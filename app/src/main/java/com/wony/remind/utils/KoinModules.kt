package com.wony.remind.utils

import com.wony.remind.main.add.AddRepo
import com.wony.remind.main.add.AddVM
import com.wony.remind.main.alarm.AlarmRepo
import com.wony.remind.main.alarm.AlarmVM
import com.wony.remind.main.list.ListRepo
import com.wony.remind.main.list.ListVM
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    factory { AddRepo(androidContext()) }
    factory { AlarmRepo(androidContext()) }
    factory { ListRepo(androidContext()) }

}

val vmModule = module {
    viewModel { AddVM(get()) }
    viewModel { AlarmVM(get()) }
    viewModel { ListVM(get()) }
}
