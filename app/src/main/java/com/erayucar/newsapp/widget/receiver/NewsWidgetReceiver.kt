package com.erayucar.newsapp.widget.receiver

import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.glance.state.PreferencesGlanceStateDefinition
import com.erayucar.newsapp.domain.use_case.NewsTopHeadLinesUseCase
import com.erayucar.newsapp.widget.NewsWidget
import com.erayucar.newsapp.widget.callback.NewsRefreshCallback
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class NewsWidgetReciever : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = NewsWidget()
    private val coroutineScope = MainScope()

    @Inject
    lateinit var newsUseCase: NewsTopHeadLinesUseCase

    private fun observeData(context: Context) {
        coroutineScope.launch {
            val glanceId =
                GlanceAppWidgetManager(context).getGlanceIds(NewsWidget::class.java).firstOrNull()
            println(glanceId)
            glanceId?.let {
                newsUseCase.invoke("general", "tr").collect { articles ->
                    updateAppWidgetState(context, PreferencesGlanceStateDefinition, it) { pref ->
                        pref.toMutablePreferences().apply {
                            this[author] = setOf(
                                articles.data?.get(0)?.author ?: "",
                                articles.data?.get(1)?.author ?: "",
                                articles.data?.get(3)?.author ?: "",
                            )
                            this[title] = setOf(
                                articles.data?.get(0)?.title ?: "",
                                articles.data?.get(1)?.title ?: "",
                                articles.data?.get(3)?.title ?: ""
                            )
                            this[url] = setOf(
                                articles.data?.get(0)?.url ?: "",
                                articles.data?.get(1)?.url ?: "",
                                articles.data?.get(3)?.url ?: ""
                            )


                        }

                    }
                    NewsWidget().update(context, it)
                }
                glanceAppWidget.update(context, it)
            }
        }
    }

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        observeData(context)

    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
        if (intent.action == NewsRefreshCallback.UPDATE_ACTION) {
            observeData(context = context)
        }
    }


    companion object {
        val author = stringSetPreferencesKey("author")
        val title = stringSetPreferencesKey("title")
        val url = stringSetPreferencesKey("url")
    }

}
