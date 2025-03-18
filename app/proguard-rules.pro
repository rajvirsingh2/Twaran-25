-keepclassmembers class * {
    @com.google.firebase.database.Exclude *;
}

-keepclassmembers class com.example.twaran25.data.models.** {
    <fields>;
    public <init>();
}

-keepnames class com.example.twaran25.data.models.** { *; }

-keep class com.google.firebase.** { *; }
-keep class com.google.android.gms.** { *; }
