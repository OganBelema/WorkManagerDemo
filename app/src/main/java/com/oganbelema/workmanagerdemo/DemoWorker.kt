package com.oganbelema.workmanagerdemo

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters

/**
 * Created by Belema Ogan on 2019-08-09.
 */
class DemoWorker(private val context: Context, private val workerParameters: WorkerParameters):
    Worker(context, workerParameters) {

    private val tag = this::class.simpleName


    override fun doWork(): Result {

        val count = inputData.getInt(KEY_COUNT_VALUE, 500)

        for (i in 1..count){
            Log.i(tag, "Count is $i")
        }

        val message = "Task successful"

        val dataToSend = Data.Builder().putString(KEY_WORKER_MESSAGE, message).build()

        return Result.success(dataToSend)
    }


}