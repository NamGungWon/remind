package com.wony.remind.main.alarm
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wony.remind.base.BaseVM
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class AlarmVM(private var repo: AlarmRepo) : BaseVM() {

    private var _remindTitle = MutableLiveData<String>()
    var remindTitle : LiveData<String> = _remindTitle

    private var _remindTime = MutableLiveData<String>()
    var remindTime : LiveData<String> = _remindTime

    private var _remindSound = MutableLiveData<String>()
    var remindSound : LiveData<String> = _remindSound

    fun completeAlarm(id: Long){

        ioScope.launch {
            repo.updateRemindActive(id, false)
        }
    }

    fun setId(id: Long) {
        ioScope.launch {
            var item = repo.selectItem(id)

            uiScope.launch {
                _remindTitle.value = item?.title ?: ""

                var alarmTime = item?.alarmTime ?: System.currentTimeMillis()
                _remindTime.value = SimpleDateFormat("HH:mm").format(Date(alarmTime))

                _remindSound.value = item?.bell
            }
        }
    }
}