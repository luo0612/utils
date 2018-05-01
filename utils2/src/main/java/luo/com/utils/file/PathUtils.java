package luo.com.utils.file;

import android.content.ContentUris;
import android.content.CursorLoader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;

import java.io.File;

import luo.com.utils.Utils;

/**
 * 文件路径相关工具类
 */
public class PathUtils {

    public final static String SDCARD_MNT = "/mnt/sdcard";
    public final static String SDCARD = Environment.getExternalStorageDirectory().getPath();

    /**
     * 获取SDCard的路径
     *
     * @return String of path
     */
    public static String getSDCardPath() {
        return Environment.getExternalStorageDirectory().getPath();
    }

    /**
     * 通过非标准的Uri的获取文件的绝对路径
     *
     * @param uri
     * @return
     */
    public static String getAbsolutePathFromNoStandardUri(Uri uri) {
        String filePath = null;
        String mUriString = uri.toString();
        mUriString = Uri.decode(mUriString);

        String pre1 = "file://" + SDCARD + File.separator;
        String pre2 = "file://" + SDCARD_MNT + File.separator;

        if (mUriString.startsWith(pre1)) {
            filePath = Environment.getExternalStorageDirectory().getPath()
                    + File.separator + mUriString.substring(pre1.length());
        } else if (mUriString.startsWith(pre2)) {
            filePath = Environment.getExternalStorageDirectory().getPath()
                    + File.separator + mUriString.substring(pre2.length());
        }
        return filePath;
    }

    /**
     * 通过Uri获取文件的绝对路径
     *
     * @param uri
     * @return
     */
    public static String getAbsoluteUriPath(Uri uri) {
        String imgPath = "";
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = new CursorLoader(Utils.getContext(), uri, proj, null, null, null).loadInBackground();
        try {
            if (cursor != null) {
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                if (cursor.getCount() > 0 && cursor.moveToFirst()) {
                    imgPath = cursor.getString(column_index);
                }
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }


        return imgPath;
    }

    /**
     * 获取应用外部存储卡的缓存目录路径, Android/data/PACKAGE_NAME/cache/
     *
     * @return
     */
    public static String getExternalCacheDirPath() {
        //判断是否有缓存目录
        if (hasExternalCacheDir()) {
            return Utils.getContext().getExternalCacheDir().getAbsolutePath();
        }
        final String cacheDir = "/Android/data/" + Utils.getContext().getPackageName() + "/cache/";
        File file = new File(cacheDir);
        file.mkdirs();
        return file.getAbsolutePath();
    }

    /**
     * 获取应用外部存储卡的文件目录路径, Android/data/PACKAGE_NAME/files/
     *
     * @return
     */
    public static String getExternalFilesDirPath() {
        final String filesDir = "/Android/data/" + Utils.getContext().getPackageName() + "/files/";
        File file = new File(filesDir);
        file.mkdirs();
        return file.getAbsolutePath();
    }

    /**
     * 查找或者创建目录
     *
     * @param parent
     * @param dirName
     * @return
     */
    public static File findOrCreateDir(File parent, String dirName) {
        File directory = new File(parent, dirName);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        return directory;
    }

    private static boolean hasExternalCacheDir() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO;
    }

    /**
     * 通过Uri获取媒体文件的路径
     *
     * @param uri
     * @return
     */
    public static String getMediaFilePath(Uri uri) {
        return getPath(uri);
    }

    /**
     * 通过Uri获取文件路径, 图片,视频,音频等文件路径的获取需要通过该方法
     *
     * @param uri
     * @return
     */
    public static String getPath(final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(Utils.getContext(), uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

                // TODO handle non-primary volumes
            } else if (isDownloadsDocument(uri)) {  // DownloadsProvider

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(contentUri, null, null);
            } else if (isMediaDocument(uri)) {  // MediaProvider
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                switch (type) {
                    case "image":
                        contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                        break;
                    case "video":
                        contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                        break;
                    case "audio":
                        contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                        break;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };

                return getDataColumn(contentUri, selection, selectionArgs);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) { // MediaStore (and general)

            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();
            return getDataColumn(uri, null, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) { // File

            return uri.getPath();
        }
        return null;
    }

    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param uri           The Uri to query.
     * @param selection     (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    static String getDataColumn(Uri uri, String selection,
                                String[] selectionArgs) {
        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };
        try {
            cursor = new CursorLoader(Utils.getContext(), uri, projection, selection, selectionArgs, null).loadInBackground();
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }


    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }
}
