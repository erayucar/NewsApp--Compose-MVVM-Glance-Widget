package com.erayucar.newsapp.widget.callback

import android.content.Context
import android.content.Intent
import androidx.glance.GlanceId
import androidx.glance.action.ActionParameters
import androidx.glance.appwidget.action.ActionCallback
import com.erayucar.newsapp.widget.receiver.NewsWidgetReciever

class NewsRefreshCallback() : ActionCallback {
    override suspend fun onAction(context: Context, glanceId: GlanceId, parameters: ActionParameters) {
        val intent = Intent(context, NewsWidgetReciever::class.java).apply {
            action = UPDATE_ACTION
        }
        context.sendBroadcast(intent)
    }

    companion object {
        const val UPDATE_ACTION = "UPDATE_ACTION"
    }
}