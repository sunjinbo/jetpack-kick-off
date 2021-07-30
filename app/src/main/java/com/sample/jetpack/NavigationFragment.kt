package com.sample.jetpack

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation

class NavigationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_navigation, container, false)
        view.findViewById<Button>(R.id.deep_link).setOnClickListener {
            sendNotification()
        }
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun sendNotification() {
        if (activity == null) {
            return
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "1"
            var channelName = "channel_name"

            var importance = NotificationManager.IMPORTANCE_DEFAULT
            var channel = NotificationChannel(channelId, channelName, importance)
            channel.description = "description"
            var notificationManager = requireActivity().getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)

            var builder = NotificationCompat.Builder(requireActivity(), channelId)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("DeepLink")
                .setContentText("Jetpack is good!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(getPendingIntent())
                .setAutoCancel(true)

            var manager = NotificationManagerCompat.from(requireActivity())
            manager.notify(0, builder.build())
        }
    }

    private fun getPendingIntent() : PendingIntent? {
        if (activity != null) {
            var bundle = Bundle()
            bundle.putString("params", "ParamsFromNotification_navigation")

            return Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                .createDeepLink()
                .setGraph(R.navigation.nav_graph)
                .setDestination(R.id.navigationFragment)
                .setArguments(bundle)
                .createPendingIntent()
        }
        return null
    }
}
