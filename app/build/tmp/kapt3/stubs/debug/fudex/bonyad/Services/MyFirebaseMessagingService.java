package fudex.bonyad.Services;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010F\u001a\u00020GH\u0007J\u0010\u0010H\u001a\u00020I2\u0006\u0010J\u001a\u00020KH\u0017J(\u0010L\u001a\u00020I2\u0006\u0010M\u001a\u00020\r2\u0006\u00103\u001a\u00020\u00042\u0006\u0010N\u001a\u00020\u00042\u0006\u0010O\u001a\u00020AH\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001e\u001a\u00020\u0013X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0015\"\u0004\b \u0010\u0017R\u001c\u0010!\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0006\"\u0004\b#\u0010\bR\u001c\u0010$\u001a\u0004\u0018\u00010\u0019X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u001b\"\u0004\b&\u0010\u001dR\u001c\u0010\'\u001a\u0004\u0018\u00010(X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u001c\u0010-\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\u0006\"\u0004\b/\u0010\bR\u001c\u00100\u001a\u0004\u0018\u00010\u0019X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b1\u0010\u001b\"\u0004\b2\u0010\u001dR\u001c\u00103\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b4\u0010\u0006\"\u0004\b5\u0010\bR\u001c\u00106\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b7\u0010\u0006\"\u0004\b8\u0010\bR \u00109\u001a\b\u0018\u00010:R\u00020;X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?R\u001a\u0010@\u001a\u00020AX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bB\u0010C\"\u0004\bD\u0010E\u00a8\u0006P"}, d2 = {"Lfudex/bonyad/Services/MyFirebaseMessagingService;", "Lcom/google/firebase/messaging/FirebaseMessagingService;", "()V", "NOTIFICATION_CHANNEL_ID", "", "getNOTIFICATION_CHANNEL_ID", "()Ljava/lang/String;", "setNOTIFICATION_CHANNEL_ID", "(Ljava/lang/String;)V", "id", "getId", "setId", "intent", "Landroid/content/Intent;", "getIntent", "()Landroid/content/Intent;", "setIntent", "(Landroid/content/Intent;)V", "latitude", "", "getLatitude", "()D", "setLatitude", "(D)V", "loginFile", "Landroid/content/SharedPreferences;", "getLoginFile", "()Landroid/content/SharedPreferences;", "setLoginFile", "(Landroid/content/SharedPreferences;)V", "longitude", "getLongitude", "setLongitude", "messageBody", "getMessageBody", "setMessageBody", "messageshared", "getMessageshared", "setMessageshared", "obj", "Lorg/json/JSONObject;", "getObj", "()Lorg/json/JSONObject;", "setObj", "(Lorg/json/JSONObject;)V", "operationId", "getOperationId", "setOperationId", "storedata", "getStoredata", "setStoredata", "title", "getTitle", "setTitle", "type", "getType", "setType", "wakeLock", "Landroid/os/PowerManager$WakeLock;", "Landroid/os/PowerManager;", "getWakeLock", "()Landroid/os/PowerManager$WakeLock;", "setWakeLock", "(Landroid/os/PowerManager$WakeLock;)V", "x", "", "getX", "()I", "setX", "(I)V", "foregrounded", "", "onMessageReceived", "", "remoteMessage", "Lcom/google/firebase/messaging/RemoteMessage;", "show_not", "notificationIntent", "message", "order_id", "app_debug"})
public final class MyFirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {
    @org.jetbrains.annotations.Nullable()
    private java.lang.String operationId;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String messageBody;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String title;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String type;
    @org.jetbrains.annotations.Nullable()
    private android.content.Intent intent;
    private double latitude = 0.0;
    private double longitude = 0.0;
    @org.jetbrains.annotations.Nullable()
    private org.json.JSONObject obj;
    private int x = 0;
    @org.jetbrains.annotations.Nullable()
    private android.content.SharedPreferences storedata;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String id;
    @org.jetbrains.annotations.Nullable()
    private android.content.SharedPreferences loginFile;
    @org.jetbrains.annotations.Nullable()
    private android.content.SharedPreferences messageshared;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String NOTIFICATION_CHANNEL_ID = "10001";
    @org.jetbrains.annotations.Nullable()
    private android.os.PowerManager.WakeLock wakeLock;
    
    public MyFirebaseMessagingService() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getOperationId() {
        return null;
    }
    
    public final void setOperationId(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getMessageBody() {
        return null;
    }
    
    public final void setMessageBody(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getTitle() {
        return null;
    }
    
    public final void setTitle(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getType() {
        return null;
    }
    
    public final void setType(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.content.Intent getIntent() {
        return null;
    }
    
    public final void setIntent(@org.jetbrains.annotations.Nullable()
    android.content.Intent p0) {
    }
    
    public final double getLatitude() {
        return 0.0;
    }
    
    public final void setLatitude(double p0) {
    }
    
    public final double getLongitude() {
        return 0.0;
    }
    
    public final void setLongitude(double p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.json.JSONObject getObj() {
        return null;
    }
    
    public final void setObj(@org.jetbrains.annotations.Nullable()
    org.json.JSONObject p0) {
    }
    
    public final int getX() {
        return 0;
    }
    
    public final void setX(int p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.content.SharedPreferences getStoredata() {
        return null;
    }
    
    public final void setStoredata(@org.jetbrains.annotations.Nullable()
    android.content.SharedPreferences p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getId() {
        return null;
    }
    
    public final void setId(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.content.SharedPreferences getLoginFile() {
        return null;
    }
    
    public final void setLoginFile(@org.jetbrains.annotations.Nullable()
    android.content.SharedPreferences p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.content.SharedPreferences getMessageshared() {
        return null;
    }
    
    public final void setMessageshared(@org.jetbrains.annotations.Nullable()
    android.content.SharedPreferences p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getNOTIFICATION_CHANNEL_ID() {
        return null;
    }
    
    public final void setNOTIFICATION_CHANNEL_ID(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.os.PowerManager.WakeLock getWakeLock() {
        return null;
    }
    
    public final void setWakeLock(@org.jetbrains.annotations.Nullable()
    android.os.PowerManager.WakeLock p0) {
    }
    
    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    @java.lang.Override()
    @android.annotation.SuppressLint(value = {"InvalidWakeLockTag"})
    public void onMessageReceived(@org.jetbrains.annotations.NotNull()
    com.google.firebase.messaging.RemoteMessage remoteMessage) {
    }
    
    private final void show_not(android.content.Intent notificationIntent, java.lang.String title, java.lang.String message, int order_id) {
    }
    
    @androidx.annotation.RequiresApi(api = android.os.Build.VERSION_CODES.JELLY_BEAN)
    public final boolean foregrounded() {
        return false;
    }
}