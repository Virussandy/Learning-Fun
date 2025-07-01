package com.mollosradix.deals.viewmodel

import android.Manifest
import android.app.Application
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.annotation.RequiresPermission
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mollosradix.deals.MainActivity
import com.mollosradix.deals.R
import com.mollosradix.deals.model.DealItem
import com.mollosradix.deals.repository.NotificationsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.temporal.ChronoUnit


class NotificationViewModel(
    application: Application               // need context -> extend AndroidViewModel
) : AndroidViewModel(application) {

    private val repository = NotificationsRepository()

    private val _deals= MutableStateFlow<List<DealItem>>(emptyList())
    val deals: StateFlow<List<DealItem>>   = _deals.asStateFlow()

    // Keep track so we don’t notify twice if config-changes recreate the VM
    private val alreadyNotified = mutableSetOf<String>()

    init {
        repository.listenForNewNotifications { newDeal ->
            // 1️⃣ add to in-app list
            _deals.update { listOf(newDeal) + it }

            // 2️⃣ show notification only once and only if permission is granted
            if (alreadyNotified.add(newDeal.deal_id)) {
                if (ActivityCompat.checkSelfPermission(
                        getApplication(),
                        Manifest.permission.POST_NOTIFICATIONS
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    showSystemNotification(getApplication(), newDeal)
                }
            }
        }
    }


    /** unchanged helper for time-ago & showSystemNotification(...) */
    fun getTimeAgo(timestamp: String?): String {
        if (timestamp == null) return ""

        return try {
            val instant = Instant.parse(timestamp)
            val now = Instant.now()
            val diff = ChronoUnit.MINUTES.between(instant, now)

            when {
                diff < 60 -> "$diff minutes ago"
                diff < 1440 -> "${diff / 60} hours ago"
                else -> "${diff / 1440} days ago"
            }
        } catch (e: Exception) {
            ""
        }
    }

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    fun showSystemNotification(context: Context, deal: DealItem) {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("deal_id", deal.deal_id)
        }

        val pendingIntent = PendingIntent.getActivity(
            context, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val builder = NotificationCompat.Builder(context, "good_deals_channel")
            .setSmallIcon(R.drawable.ic_app_icon) // your icon
            .setContentTitle("🔥 ${deal.title.take(30)}")
            .setContentText("Now only ₹${deal.price} (${deal.discount})")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        NotificationManagerCompat.from(context).notify(deal.deal_id.hashCode(), builder.build())
    }
}


