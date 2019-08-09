package com.oganbelema.workmanagerdemo

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

/**
 * Created by Belema Ogan on 2019-08-09.
 */
class DemoWorker(private val context: Context, private val workerParameters: WorkerParameters):
    Worker(context, workerParameters) {

    private val tag = this::class.simpleName


    override fun doWork(): Result {
        for (i in 1..1000){
            Log.i(tag, "Count is $i")
        }

        return Result.success()
    }


}