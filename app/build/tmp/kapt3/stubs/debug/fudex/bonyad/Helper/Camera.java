package fudex.bonyad.Helper;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB\u0005\u00a2\u0006\u0002\u0010\u0002J+\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010\u0016\u001a\u00020\u0017H\u0016\u00a2\u0006\u0002\u0010\u0018J\u0010\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u0010\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\u001bH\u0016R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e\u00a8\u0006\u001f"}, d2 = {"Lfudex/bonyad/Helper/Camera;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "inputStream", "Ljava/io/InputStream;", "getInputStream", "()Ljava/io/InputStream;", "setInputStream", "(Ljava/io/InputStream;)V", "selectedImage", "Landroid/graphics/Bitmap;", "getSelectedImage", "()Landroid/graphics/Bitmap;", "setSelectedImage", "(Landroid/graphics/Bitmap;)V", "onRequestPermissionsResult", "", "requestCode", "", "permissions", "", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "onRestoreInstanceState", "savedInstanceState", "Landroid/os/Bundle;", "onSaveInstanceState", "outState", "Companion", "app_debug"})
public final class Camera extends androidx.appcompat.app.AppCompatActivity {
    @org.jetbrains.annotations.Nullable()
    private android.graphics.Bitmap selectedImage;
    @org.jetbrains.annotations.Nullable()
    private java.io.InputStream inputStream;
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    private static final int CAMERA_CAPTURE_VIDEO_REQUEST_CODE = 200;
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 100;
    private static final int PICK_PHOTO_FOR_AVATAR = 1;
    @org.jetbrains.annotations.Nullable()
    private static android.net.Uri fileUri;
    private static final int MEDIA_TYPE_IMAGE = 1;
    private static final int MEDIA_TYPE_VIDEO = 2;
    @org.jetbrains.annotations.Nullable()
    private static android.graphics.Bitmap myBitmap;
    @org.jetbrains.annotations.Nullable()
    private static android.app.Activity activity;
    @org.jetbrains.annotations.Nullable()
    private static java.lang.String pictureImagePath = "";
    @org.jetbrains.annotations.Nullable()
    private static java.io.File sourceFile;
    
    /**
     * Receiving activity result method will be called after closing the camera
     */
    @org.jetbrains.annotations.NotNull()
    private static java.lang.String selectedImagePath = "";
    private static final int permessionConstant = 0;
    @org.jetbrains.annotations.NotNull()
    public static final fudex.bonyad.Helper.Camera.Companion Companion = null;
    
    public Camera() {
        super();
    }
    
    /**
     * Here we store the file url as it will be null after returning from camera
     * app
     */
    @java.lang.Override()
    public void onSaveInstanceState(@org.jetbrains.annotations.NotNull()
    android.os.Bundle outState) {
    }
    
    @java.lang.Override()
    public void onRestoreInstanceState(@org.jetbrains.annotations.NotNull()
    android.os.Bundle savedInstanceState) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.graphics.Bitmap getSelectedImage() {
        return null;
    }
    
    public final void setSelectedImage(@org.jetbrains.annotations.Nullable()
    android.graphics.Bitmap p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.io.InputStream getInputStream() {
        return null;
    }
    
    public final void setInputStream(@org.jetbrains.annotations.Nullable()
    java.io.InputStream p0) {
    }
    
    @java.lang.Override()
    public void onRequestPermissionsResult(int requestCode, @org.jetbrains.annotations.NotNull()
    java.lang.String[] permissions, @org.jetbrains.annotations.NotNull()
    int[] grantResults) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0010\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001e\u00103\u001a\u00020\u00042\u0006\u00104\u001a\u0002052\u0006\u00106\u001a\u00020\u00042\u0006\u00107\u001a\u00020\u0004J\u0006\u00108\u001a\u000209J\u000e\u0010:\u001a\u00020\u001c2\u0006\u0010;\u001a\u00020\u001cJ\u0012\u0010<\u001a\u0004\u0018\u00010 2\b\u0010=\u001a\u0004\u0018\u00010.J\u0016\u0010>\u001a\u00020\u00162\u0006\u0010?\u001a\u00020@2\u0006\u0010A\u001a\u00020 J\u0012\u0010B\u001a\u0004\u0018\u00010.2\u0006\u0010C\u001a\u00020\u0004H\u0002J\u000e\u0010D\u001a\u00020\u00162\u0006\u0010C\u001a\u00020\u0004J\u0014\u0010E\u001a\u0004\u0018\u00010\u001c2\b\u0010F\u001a\u0004\u0018\u00010\u0016H\u0007J\u0010\u0010G\u001a\u00020\u001c2\b\u0010F\u001a\u0004\u0018\u00010\u0016J\u0012\u0010G\u001a\u0004\u0018\u00010\u001c2\u0006\u0010H\u001a\u00020\u001cH\u0002J\u0006\u0010I\u001a\u000209J\u0006\u0010J\u001a\u000209J\u0010\u0010K\u001a\u0004\u0018\u00010.2\u0006\u0010L\u001a\u00020.J\u0010\u0010M\u001a\u0002092\b\u0010N\u001a\u0004\u0018\u00010\u0010J\u0018\u0010O\u001a\u00020\u00162\b\u0010?\u001a\u0004\u0018\u00010@2\u0006\u0010A\u001a\u00020 R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\u0004X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0006R\u0014\u0010\n\u001a\u00020\u0004X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0006R\u000e\u0010\f\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\u00020\u0004X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006R\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u0011\u0010\u001b\u001a\u00020\u001c8F\u00a2\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u001c\u0010\u001f\u001a\u0004\u0018\u00010 X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u000e\u0010%\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u001c\u0010&\u001a\u0004\u0018\u00010\u001cX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\'\u0010\u001e\"\u0004\b(\u0010)R\u001a\u0010*\u001a\u00020\u001cX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\u001e\"\u0004\b,\u0010)R\u001c\u0010-\u001a\u0004\u0018\u00010.X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b/\u00100\"\u0004\b1\u00102\u00a8\u0006P"}, d2 = {"Lfudex/bonyad/Helper/Camera$Companion;", "", "()V", "CAMERA_CAPTURE_IMAGE_REQUEST_CODE", "", "getCAMERA_CAPTURE_IMAGE_REQUEST_CODE", "()I", "CAMERA_CAPTURE_VIDEO_REQUEST_CODE", "MEDIA_TYPE_IMAGE", "getMEDIA_TYPE_IMAGE", "MEDIA_TYPE_VIDEO", "getMEDIA_TYPE_VIDEO", "MY_PERMISSIONS_REQUEST_READ_CONTACTS", "PICK_PHOTO_FOR_AVATAR", "getPICK_PHOTO_FOR_AVATAR", "activity", "Landroid/app/Activity;", "getActivity", "()Landroid/app/Activity;", "setActivity", "(Landroid/app/Activity;)V", "fileUri", "Landroid/net/Uri;", "getFileUri", "()Landroid/net/Uri;", "setFileUri", "(Landroid/net/Uri;)V", "filename", "", "getFilename", "()Ljava/lang/String;", "myBitmap", "Landroid/graphics/Bitmap;", "getMyBitmap", "()Landroid/graphics/Bitmap;", "setMyBitmap", "(Landroid/graphics/Bitmap;)V", "permessionConstant", "pictureImagePath", "getPictureImagePath", "setPictureImagePath", "(Ljava/lang/String;)V", "selectedImagePath", "getSelectedImagePath", "setSelectedImagePath", "sourceFile", "Ljava/io/File;", "getSourceFile", "()Ljava/io/File;", "setSourceFile", "(Ljava/io/File;)V", "calculateInSampleSize", "options", "Landroid/graphics/BitmapFactory$Options;", "reqWidth", "reqHeight", "cameraOperation", "", "compressImage", "imageUri", "decodeFile", "f", "getImageUri", "inContext", "Landroid/content/Context;", "inImage", "getOutputMediaFile", "type", "getOutputMediaFileUri", "getPath", "uri", "getRealPathFromURI", "contentURI", "openCamera", "pickImage", "saveBitmapToFile", "file", "startInstalledAppDetailsActivity", "context", "writeToTempImageAndGetPathUri", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        public final int getCAMERA_CAPTURE_IMAGE_REQUEST_CODE() {
            return 0;
        }
        
        public final int getPICK_PHOTO_FOR_AVATAR() {
            return 0;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final android.net.Uri getFileUri() {
            return null;
        }
        
        public final void setFileUri(@org.jetbrains.annotations.Nullable()
        android.net.Uri p0) {
        }
        
        public final int getMEDIA_TYPE_IMAGE() {
            return 0;
        }
        
        public final int getMEDIA_TYPE_VIDEO() {
            return 0;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final android.graphics.Bitmap getMyBitmap() {
            return null;
        }
        
        public final void setMyBitmap(@org.jetbrains.annotations.Nullable()
        android.graphics.Bitmap p0) {
        }
        
        @org.jetbrains.annotations.Nullable()
        public final android.app.Activity getActivity() {
            return null;
        }
        
        public final void setActivity(@org.jetbrains.annotations.Nullable()
        android.app.Activity p0) {
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getPictureImagePath() {
            return null;
        }
        
        public final void setPictureImagePath(@org.jetbrains.annotations.Nullable()
        java.lang.String p0) {
        }
        
        public final void cameraOperation() {
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.io.File getSourceFile() {
            return null;
        }
        
        public final void setSourceFile(@org.jetbrains.annotations.Nullable()
        java.io.File p0) {
        }
        
        public final void pickImage() {
        }
        
        /**
         * Receiving activity result method will be called after closing the camera
         */
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getSelectedImagePath() {
            return null;
        }
        
        /**
         * Receiving activity result method will be called after closing the camera
         */
        public final void setSelectedImagePath(@org.jetbrains.annotations.NotNull()
        java.lang.String p0) {
        }
        
        @android.annotation.SuppressLint(value = {"NewApi"})
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getPath(@org.jetbrains.annotations.Nullable()
        android.net.Uri uri) {
            return null;
        }
        
        private final java.io.File getOutputMediaFile(int type) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.net.Uri getOutputMediaFileUri(int type) {
            return null;
        }
        
        public final void startInstalledAppDetailsActivity(@org.jetbrains.annotations.Nullable()
        android.app.Activity context) {
        }
        
        public final void openCamera() {
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.io.File saveBitmapToFile(@org.jetbrains.annotations.NotNull()
        java.io.File file) {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final android.graphics.Bitmap decodeFile(@org.jetbrains.annotations.Nullable()
        java.io.File f) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.net.Uri writeToTempImageAndGetPathUri(@org.jetbrains.annotations.Nullable()
        android.content.Context inContext, @org.jetbrains.annotations.NotNull()
        android.graphics.Bitmap inImage) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String compressImage(@org.jetbrains.annotations.NotNull()
        java.lang.String imageUri) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getFilename() {
            return null;
        }
        
        private final java.lang.String getRealPathFromURI(java.lang.String contentURI) {
            return null;
        }
        
        public final int calculateInSampleSize(@org.jetbrains.annotations.NotNull()
        android.graphics.BitmapFactory.Options options, int reqWidth, int reqHeight) {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.net.Uri getImageUri(@org.jetbrains.annotations.NotNull()
        android.content.Context inContext, @org.jetbrains.annotations.NotNull()
        android.graphics.Bitmap inImage) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getRealPathFromURI(@org.jetbrains.annotations.Nullable()
        android.net.Uri uri) {
            return null;
        }
    }
}