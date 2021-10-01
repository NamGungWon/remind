package com.wony.remind.main.add

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.media.MediaPlayer
import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import com.wony.remind.R
import com.wony.remind.base.BaseFragment
import com.wony.remind.databinding.FragmentAddBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddFragment : BaseFragment<FragmentAddBinding, AddVM>() {

    override val layoutId = R.layout.fragment_add
    override val viewModel: AddVM by viewModel()


    private val REQ_SOUND = 1001
    private var resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK) {
            it.data?.let {
                var uri = it.getParcelableExtra<Uri>(RingtoneManager.EXTRA_RINGTONE_PICKED_URI)
                viewModel.selectSoundUri = uri
//                var title = uri?.getQueryParameter("title")?: ""
//                binding.tvSelectSound.text = title
                Log.e("uri","path=${uri}")
//                var mediaPlayer = MediaPlayer.create(context, Uri.parse("content://media/external/audio/media/6855"))
//                mediaPlayer.start()
//                var sound = RingtoneManager.getRingtone(context, uri)
//                sound.play()
            }
        }
    }


    override fun init() {


        binding.vm = viewModel

        viewModel.selectSoundUri = RingtoneManager.getActualDefaultRingtoneUri(context, RingtoneManager.TYPE_ALL)

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