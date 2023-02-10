package com.example.githubtubes.ui.main.settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.githubtubes.databinding.ActivitySettingsBinding
import com.example.githubtubes.ui.main.model.Reminder
import com.example.githubtubes.ui.main.preference.ReminderPreference
import com.example.githubtubes.ui.main.receiver.AlarmReceiver

class SettingsActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySettingsBinding
    private lateinit var reminder : Reminder
    private lateinit var  alarmReceiver : AlarmReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val reminderPreference = ReminderPreference(this)
        if (reminderPreference.getReminder().isReminded){
            binding.switch1.isChecked = true
        }else{
            binding.switch1.isChecked = false
        }
        alarmReceiver = AlarmReceiver()

        binding.switch1.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                saveReminder(true)
                alarmReceiver.setRepeatingAlarm(this,"RepeatingAlarm","07:45", "Github reminder")
            }else{
                saveReminder(false)
                alarmReceiver.cancelAlarm(this)
            }
        }
    }

    private fun saveReminder(state: Boolean) {
        val reminderPreference = ReminderPreference(this)
        reminder = Reminder()

        reminder.isReminded = state
        reminderPreference.setReminder(reminder)
    }
}