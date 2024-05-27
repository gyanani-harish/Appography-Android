package gyanani.harish.appographyexample.ui

import android.annotation.SuppressLint
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import gyanani.harish.appographyexample.R
import gyanani.harish.appographyexample.data.AppInfo
import gyanani.harish.appographyexample.ui.adapter.AppAdapter
import kotlin.reflect.full.memberProperties

class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val appRecyclerView = findViewById<RecyclerView>(R.id.appRecyclerView)

        val installedApps = getInstalledApps()
        val adapter = AppAdapter(installedApps)
        appRecyclerView.adapter = adapter
    }
    private fun getInstalledApps(): List<AppInfo> {
        val installTime = "firstInstallTime"
        val packageManager = packageManager
        val applications = packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
        val appList = mutableListOf<AppInfo>()

        for (applicationInfo in applications) {
            try {
                val packageInfo = packageManager.getPackageInfo(applicationInfo.packageName, 0)
                val versionCode = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    packageInfo.longVersionCode
                } else {
                    @Suppress("DEPRECATION")
                    packageInfo.versionCode.toLong()
                }
                val installPropertyValue = PackageInfo::class.memberProperties.firstOrNull {
                    it.name == installTime
                }?.get(packageInfo)
                val appInfo = AppInfo(
                    appName = applicationInfo.loadLabel(packageManager).toString(),
                    packageName = packageInfo.packageName,
                    versionCode = versionCode, // Use longVersionCode for API 30+
                    versionName = packageInfo.versionName ?: "N/A",
                    installedOn = installPropertyValue.toString(),
                    lastUpdateTime = packageInfo.lastUpdateTime
                )
                appList.add(appInfo)
            } catch (e: PackageManager.NameNotFoundException) {
                // Handle exception gracefully (e.g., log the error)
            }
        }
        return appList
    }
}