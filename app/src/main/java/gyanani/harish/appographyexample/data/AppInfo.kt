package gyanani.harish.appographyexample.data

data class AppInfo(
    val appName: String,
    val packageName: String,
    val versionCode: Long,
    val versionName: String,
    val installedOn: String?, // May be null if not available
    val lastUpdateTime: Long
)
