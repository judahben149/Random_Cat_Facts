# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-keep class com.judahben149.retrofitcp.*
-keep interface com.judahben149.retrofitcp.api.ApiService
#-keep class com.judahben149.retrofitcp.api.*
#-keep class com.judahben149.retrofitcp.api.ApiRequests



-ignorewarnings
-keep class * {
    public private *;
}

-keep class com.google.android.gms.* { *; }
-dontwarn com.google.android.gms.*

-dontwarn org.xmlpull.v1.*
-dontnote org.xmlpull.v1.*
-keep class org.xmlpull.** { *; }

#retRofit
# Platform calls Class.forName on types which do not exist on Android to determine platform.
-dontnote retrofit2.Platform
# Platform used when running on RoboVM on iOS. Will not be used at runtime.
#-dontnote retrofit2.Platform$IOS$MainThreadExecutor
## Platform used when running on Java 8 VMs. Will not be used at runtime.
#-dontwarn retrofit2.Platform$Java8
# Retain generic type information for use by reflection by converters and adapters.
-keepattributes Signature
# Retain declared checked exceptions for use by a Proxy instance.
-keepattributes Exceptions
-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}

-keepclassmembers class * {
    native <methods>;
}
-dontwarn okio.*
-dontwarn com.squareup.okhttp.*
-dontwarn okhttp3.*
-dontwarn javax.annotation.*
-dontwarn com.android.volley.toolbox.*

# com.github.siyamed:android-shape-imageview
-dontwarn android.support.v7.*
-keepattributes *Annotation,Signature

#Image Cropper
-keep class androidx.appcompat.widget.* { *; }



# Keep source file names, line numbers, and Parse class/method names for easier debugging
 -keepattributes SourceFile,LineNumberTable
 -keepnames class com.parse.* { *; }


 # Required for Parse
 -keepattributes *Annotation*
 -keepattributes Signature
 -dontwarn com.squareup.*
 -dontwarn okio.*
