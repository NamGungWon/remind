package com.wony.remind.main.add

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wony.remind.base.BaseVM
import com.wony.remind.db.RemindData
import com.wony.remind.utils.AlarmUtils
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

    private var _selectHourLiveData = MutableLiveData<Int>()
    var selectHourLiveData: LiveData<Int> = _selectHourLiveData

    private var _selectMinLiveData = MutableLiveData<Int>()
    var selectMinLiveData: LiveData<Int> = _selectMinLiveData

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

    fun setSelectId(id: Long){
        ioScope.launch {
            selectItem = repo.selectItem(id)

            selectItem?.run{
                remindTitle = title

                uiScope.launch {
                    selectSoundUri = Uri.parse(bell)

                    Calendar.getInstance().run {
                        timeInMillis = alarmTime

                        _selectHourLiveData.value = get(Calendar.HOUR_OF_DAY)
                        _selectMinLiveData.value = get(Calendar.MINUTE)
                    }

                }
            }
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

            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)

            remindTime = timeInMillis
        }

        var item = RemindData.Item(
            alarmTime = remindTime,
            updateTime = remindTime,
            title = remindTitle,
            bell = selectSoundUri?.toString() ?: ""
        )

        item.id = selectItem?.id

        Log.e("test","insert before id =${item.id}, time=$remindTime")
        ioScope.launch {
            var id = repo.insertItem(item)
            Log.e("test","insert after id =${id}")

            AlarmUtils.register(id, remindTime)
        }

        backStackFlag.value = true
    }
}