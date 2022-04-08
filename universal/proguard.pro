-verbose
-keepdirectories
-dontnote
-dontwarn
-dontshrink
-dontoptimize
-ignorewarnings
-forceprocessing

-keepattributes '*Annotation*'

#-repackageclasses 'ru.unicorecms.unicoreconnect'

#-keep class ru.unicorecms.unicoreconnect.bukkit.UnicoreConnectBukkit  { *; }
#-keepclassmembers class ru.unicorecms.unicoreconnect.bukkit.UnicoreConnectBukkit
#
#-keep class ru.unicorecms.unicoreconnect.sponge.UnicoreConnectSponge  { *; }
#-keepclassmembers class ru.unicorecms.unicoreconnect.bukkit.UnicoreConnectSponge
#
#
#-keep class ru.unicorecms.unicoreconnect.libs.** { *; }
#-keep, allowobfuscation class * extends org.bukkit.event.Listener {
#    @org.bukkit.event.EventHandler <methods>;
#}