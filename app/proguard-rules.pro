####--------Retrofit------####
-dontnote retrofit2.Platform
-dontwarn retrofit2.Platform$Java8
-keepattributes Signature
-keepattributes Exceptions

####--------Okio----------####
-dontwarn okio.**

####--------Gson----------####
-keepattributes Signature
-keepattributes *Annotation*
-keep class sun.misc.Unsafe { *; }
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer

####-----Model classes---####
-keep class io.mvpstarter.sample.data.model.** { *; }
