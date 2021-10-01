package com.wony.remind.main.add

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wony.remind.base.BaseVM
import com.wony.remind.db.RemindData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.*

class AddVM(private val repo: AddRepo) : BaseVM() {

    // 선택한 벨소리
    var selectSoundUri: Uri? = null
        set(value) {
            field = value

            var title = value?.getQueryParameter("title") ?: ""
            _selectSoundStr.value = title
        }

    private var _selectSoundStr = MutableLiveData("")
    var selectSoundStr: LiveData<String> = _selectSoundStr

    var selectHour = 0
    var selectMinute = 0

    var remindTitle = ""

    var selectItem: RemindData.Item? = null

    init {
        Calendar.getInstance().run {
            selectHour = get(Calendar.HOUR_OF_DAY)
            selectMinute = get(Calendar.MINUTE)
        }
    }

    fun saveSound() {

        var remindTime: Long

        Calendar.getInstance().run {
            var nowHour = get(Calendar.HOUR_OF_DAY)
            var nowMinute = get(Calendar.MINUTE)

            if (nowHour < selectHour) {

                set(Calendar.HOUR_OF_DAY, selectHour)
                set(Calendar.MINUTE, selectMinute)

            } else if (nowHour == selectHour && nowMinute < selectMinute) {

                set(Calendar.HOUR_OF_DAY, selectHour)
                set(Calendar.MINUTE, selectMinute)

            } else {
                // 이미 시간이 지난 경우
                add(Calendar.DAY_OF_YEAR, 1)

                set(Calendar.HOUR_OF_DAY, selectHour)
                set(Calendar.MINUTE, selectMinute)
            }

            remindTime = timeInMillis
        }

        var item = RemindData.Item(
            alarmTime = remindTime,
            updateTime = remindTime,
            title = remindTitle,
            bell = selectSoundUri?.toString() ?: ""
        )

        item.id = selectItem?.id

        ioScope.launch {
            repo.insertItem(item)
        }

        backStackFlag.value = true
    }
}