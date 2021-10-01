package com.wony.remind.main.alarm

import android.media.MediaPlayer
import android.net.Uri
import android.text.TextUtils
import android.util.Log
import androidx.navigation.fragment.navArgs
import com.wony.remind.R
import com.wony.remind.base.BaseFragment
import com.wony.remind.databinding.FragmentAlarmBinding
import com.wony.remind.main.add.AddFragmentArgs
import org.koin.androidx.viewmodel.ext.android.viewModel

class AlarmFragment: BaseFragment<FragmentAlarmBinding, AlarmVM>() {
    override val layoutId = R.layout.fragment_alarm
    override val viewModel: AlarmVM by viewModel()

    private val args: AlarmFragmentArgs by navArgs()

    private var mediaPlayer: MediaPlayer? = null

    override fun init() {

        binding.vm = viewModel
        binding.view = this

        viewModel.setId(args.id)

        viewModel.remindSound.observe(viewLifecycleOwner, {
            if(!TextUtils.isEmpty(it)){
                mediaPlayer = MediaPlayer.create(context, Uri.parse(it))
                mediaPlayer?.start()
            }
        })
    }

    override fun backStack() {
        viewModel.completeAlarm(args.id)
        mediaPlayer?.pause()
        super.backStack()
    }

}