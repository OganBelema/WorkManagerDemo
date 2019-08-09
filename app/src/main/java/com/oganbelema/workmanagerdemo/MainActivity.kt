package com.oganbelema.workmanagerdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val workData = Data.Builder().putInt(KEY_COUNT_VALUE, 20000).build()

        val constraints = Constraints.Builder().setRequiresCharging(true).build()

        val oneTimeWorkRequest = OneTimeWorkRequest.Builder(DemoWorker::class.java).setConstraints(constraints)
            .setInputData(workData).build()

        floatingActionButton.setOnClickListener {
            WorkManager.getInstance(this).enqueue(oneTimeWorkRequest)
        }

        WorkManager.getInstance(this).getWorkInfoByIdLiveData(oneTimeWorkRequest.id)
            .observe(this, Observer { workInfo ->
                workInfo?.let {
                    statusTv.text = it.state.name

                    if (it.state.isFinished){
                        val messageFromWorker = it.outputData.getString(KEY_WORKER_MESSAGE)

                        messageFromWorker?.let {
                            Snackbar.make(floatingActionButton, messageFromWorker, Snackbar.LENGTH_LONG).show()
                        }
                    }
                }
            })
    }
}
