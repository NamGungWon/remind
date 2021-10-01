package com.wony.remind.main.add

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.media.MediaPlayer
import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.navArgs
import com.wony.remind.R
import com.wony.remind.base.BaseFragment
import com.wony.remind.databinding.FragmentAddBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddFragment : BaseFragment<FragmentAddBinding, AddVM>() {

    override val layoutId = R.layout.fragment_add
    override val viewModel: AddVM by viewModel()

    private val args: AddFragmentArgs by navArgs()

    private var resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK) {
            it.data?.let {
                var uri = it.getParcelableExtra<Uri>(RingtoneManager.EXTRA_RINGTONE_PICKED_URI)
                viewModel.selectSoundUri = uri
            }
        }
    }

    override fun init() {
        binding.vm = viewModel

        viewModel.setSelectId(args.id)

        viewModel.selectSoundUri = RingtoneManager.getActualDefaultRingtoneUri(context, RingtoneManager.TYPE_ALL)

        viewModel.selectHourLiveData.observe(viewLifecycleOwner, {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                binding.timePicker.hour = it
            }else{
                binding.timePicker.currentHour = it
            }
        })

        viewModel.selectMinLiveData.observe(viewLifecycleOwner, {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                binding.timePicker.minute = it
            }else{
                binding.timePicker.currentMinute = it
            }
        })

        binding.tvSelectSound.setOnClickListener {
            Intent(RingtoneManager.ACTION_RINGTONE_PICKER).apply {
                putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, "벨소리 선택")
                putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_SILENT, false)
                putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_DEFAULT, true)
                putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, RingtoneManager.TYPE_RINGTONE)

                resultLauncher.launch(this)
            }
        }

    }


}