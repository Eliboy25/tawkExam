private const val versionMajor = 0
private const val versionMinor = 1
private const val versionPatch = 0

val VERSION_NAME = generateVersionName()
val VERSION_CODE = generateVersionCode()

private fun generateVersionName() = "${versionMajor}.${versionMinor}.${versionPatch}"

private fun generateVersionCode() =
    AndroidSdk.version_sdk_min * 10000000 + versionMajor * 10000 + versionMinor * 100 + versionPatch

