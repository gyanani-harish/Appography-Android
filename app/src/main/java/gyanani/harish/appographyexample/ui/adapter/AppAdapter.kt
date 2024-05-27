package gyanani.harish.appographyexample.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import gyanani.harish.appographyexample.R
import gyanani.harish.appographyexample.data.AppInfo
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AppAdapter(private val appList: List<AppInfo>) :
    RecyclerView.Adapter<AppAdapter.AppViewHolder>() {

    class AppViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val appNameTextView: TextView = itemView.findViewById(R.id.appNameTextView)
        val packageNameTextView: TextView = itemView.findViewById(R.id.packageNameTextView)
        val versionCodeTextView: TextView = itemView.findViewById(R.id.versionCodeTextView)
        val versionNameTextView: TextView = itemView.findViewById(R.id.versionNameTextView)
        val installedOnTextView: TextView = itemView.findViewById(R.id.installedOnTextView)
        val lastUpdatedTextView: TextView = itemView.findViewById(R.id.lastUpdatedTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.app_list_item, parent, false)
        return AppViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AppViewHolder, position: Int) {
        val currentApp = appList[position]

        holder.appNameTextView.text = "App Name: ${currentApp.appName}"
        holder.packageNameTextView.text = "Package: ${currentApp.packageName}"
        holder.versionCodeTextView.text = "Version Code: ${currentApp.versionCode}"
        holder.versionNameTextView.text = "Version Name: ${currentApp.versionName}"
        holder.installedOnTextView.text = "Installed On: ${currentApp.installedOn}"
        holder.lastUpdatedTextView.text = "Last Updated: ${SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(
            Date(currentApp.lastUpdateTime)
        )}"
    }

    override fun getItemCount() = appList.size
}
