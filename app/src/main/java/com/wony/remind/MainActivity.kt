package com.wony.remind

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.wony.remind.databinding.ActivityMainBinding
import com.wony.remind.main.list.ListFragmentDirections

class MainActivity : AppCompatActivity() {

    companion object{
        private val KEY_ID = "KEY_ID"

        fun newIntent(context: Context, id: Long): Intent{
            return Intent(context, MainActivity::class.java).apply {
                putExtra(KEY_ID, id)
            }
        }
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        var alarmId = intent.getLongExtra(KEY_ID,0L)
        moveAlarm(alarmId)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        var alarmId = intent?.getLongExtra(KEY_ID,0L) ?: 0L
        moveAlarm(alarmId)
    }

    fun moveAlarm(alarmId: Long){

        if(alarmId != 0L){
            // 알람 울렸을때
            var action = ListFragmentDirections.actionListFragmentToAlarmFragment(alarmId)
            var navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            navHostFragment.navController.navigate(action)
        }
    }
}