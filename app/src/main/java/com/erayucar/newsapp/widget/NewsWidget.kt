package com.erayucar.newsapp.widget

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.Preferences
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.LocalSize
import androidx.glance.action.ActionParameters
import androidx.glance.action.actionParametersOf
import androidx.glance.action.actionStartActivity
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.SizeMode
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.lazy.LazyColumn
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.currentState
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.text.FontFamily
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.unit.ColorProvider
import com.erayucar.newsapp.MainActivity
import com.erayucar.newsapp.R
import com.erayucar.newsapp.widget.callback.NewsRefreshCallback
import com.erayucar.newsapp.widget.receiver.NewsWidgetReciever

private val destinationKey = ActionParameters.Key<String>("destinationKey")
private val categoryKey = ActionParameters.Key<String>("categoryKey")

class NewsWidget() : GlanceAppWidget() {
    companion object {
        private val BIG_BOX = DpSize(180.dp, 180.dp)
        private val LARGE_COLUMN = DpSize(300.dp, 48.dp)
    }

    override val sizeMode = SizeMode.Responsive(
        setOf(BIG_BOX, LARGE_COLUMN)
    )

    override suspend fun provideGlance(context: Context, id: GlanceId) {


        provideContent {
            val localSize = LocalSize.current
            val prefs = currentState<Preferences>()
            val authorSet: Set<String> = prefs[NewsWidgetReciever.author] ?: setOf()
            val titleSet: Set<String> = prefs[NewsWidgetReciever.title] ?: setOf()
            val urlSet: Set<String> = prefs[NewsWidgetReciever.url] ?: setOf()
            val authorList = authorSet.toMutableList()
            val titleList = titleSet.toMutableList()
            val urlList = urlSet.toMutableList()






            Column(
                GlanceModifier.background(
                   ColorProvider(
                         Color(0xFF302929)
                    )
                )
                    .fillMaxSize()
                    .cornerRadius(20.dp)
            ) {
                Row(
                    modifier = GlanceModifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.End,
                    verticalAlignment = Alignment.Top
                ) {


                    Image(
                        provider = ImageProvider(R.drawable.baseline_refresh_24),
                        contentDescription = "Refresh",
                        modifier = GlanceModifier
                            .clickable(
                                onClick =
                                actionRunCallback(
                                    NewsRefreshCallback::class.java,
                                    /*actionParametersOf(
                                                                           categoryKey to "business"
                                                                       )*/
                                )
                            )
                            .padding(15.dp)
                    )
                }
                val isWidgetSizeOk = localSize == BIG_BOX || localSize == LARGE_COLUMN
                if (isWidgetSizeOk) {
                    LazyColumn(modifier = GlanceModifier.fillMaxSize()) {
                        items(authorList.size) { index ->
                            NewsWidgetScreen(
                                author = authorList[index],
                                title = titleList[index],
                                url = urlList[index]
                            )
                        }
                    }
                }


                /*   Spacer(modifier = GlanceModifier.height(4.dp))
                   NewsWidgetScreen(author = author.elementAt(1), title = title.elementAt(1))
                   Spacer(modifier = GlanceModifier.height(4.dp))

                   NewsWidgetScreen(author = author.last(), title = title.last())*/

                /* Button(
                    colors = ButtonDefaults.buttonColors(ColorProvider(Color.White)),
                    onClick = actionRunCallback(NewsRefreshCallback::class.java),
                    text = "Get News",
                    style = TextStyle(ColorProvider(Color.Blue), fontSize = 16.sp)
                )*/


            }
        }


    }


}


@Composable
fun NewsWidgetScreen(author: String, title: String, url: String) {
    androidx.glance.layout.Column(
        modifier = GlanceModifier
            .clickable(
                onClick = actionStartActivity(
                    activity = MainActivity::class.java,
                    actionParametersOf(
                        destinationKey to url
                    )

                )

            ).padding(20.dp),
        verticalAlignment = androidx.glance.layout.Alignment.Top,
        horizontalAlignment = androidx.glance.layout.Alignment.Start,
    ) {

        Text(
            text = author,
            style = androidx.glance.text.TextStyle(
                color = ColorProvider(Color.LightGray),
                fontSize = 16.sp,
            ),
        )
        Text(
            text = title, style = androidx.glance.text.TextStyle(
                color = ColorProvider(
                    Color.White
                ), fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily("sans-serif")
            )
        )
        androidx.glance.layout.Spacer(modifier = GlanceModifier.height(10.dp))


    }

}
