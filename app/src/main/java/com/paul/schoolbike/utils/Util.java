package com.paul.schoolbike.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.os.StatFs;
import android.provider.MediaStore;
import android.support.v4.content.CursorLoader;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Fengli
 * on 2017/7/16  10:58
 * E-mail:2045532295@qq.com
 * Address:北京理工大学珠海学院
 */

public class Util {
    static final String appName = "ZhenAiNi";
    static Gson gson = new Gson();

    /**
     * @param @param  mContext
     * @param @return
     * @return File
     * @throws
     * @Title: getCacheFile
     * @Description: 返回应用的缓存位置，默认返回外部SD卡的缓存位置，当SD卡不可用时返回内部存储缓存位置
     */
    public static File getCacheFile(Context mContext) {
        try {
            if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                    && mContext.getExternalCacheDir() != null) {
                return mContext.getExternalCacheDir();
            } else {

                return mContext.getCacheDir();
            }
        } catch (Exception e) {

            return null;
        }
    }

    /**
     * @param @param file
     * @return void
     * @throws
     * @Title: creatFlod
     * @Description: 创建目录，如果没有的话递归创建父目录
     */
    public static boolean creatFlod(File file) {
        try {
            if (!file.exists()) {
                if (!file.getParentFile().exists()) {
                    creatFlod(file.getParentFile());
                } else {
                    file.mkdir();
                }
                file.mkdir();
            }
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public static File getCacheAudioRecordFile(Context mContext) {
        File file = new File(getCacheFile(mContext).getPath() + "/audiorecord");
        if (!file.exists()) {
            creatFlod(file);
        }

        return file;
    }

    public static float convertDpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, metrics);
        return px;
    }

    public static float converPixelToDp(float px, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / (metrics.densityDpi / 160f);
        return dp;
    }

    public static float convertSpToPixel(float sp, Context context) {
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
        return px;
    }

    public static DisplayMetrics getDispalyMetrics(Context context) {
        DisplayMetrics metrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics;
    }

    /**
     * 获取版本号
     *
     * @return 当前应用的版本名
     */
    public static String getVersion(Context mContext) {
        try {
            PackageManager manager = mContext.getPackageManager();
            PackageInfo info = manager.getPackageInfo(mContext.getPackageName(), 0);
            String version = "V " + info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static int getVersionCode(Context context) {
        PackageInfo pinfo = null;
        try {
            pinfo = ((Activity) context).getPackageManager().getPackageInfo(((Activity) context).getPackageName(), PackageManager.GET_CONFIGURATIONS);
            int versionCode = pinfo.versionCode;
            return versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }
//    /**
//     * @Title: getTheFormateSendTime
//     * @Description: 格式化发送时间成：几分钟前、几小时前 这样
//     * @param @param timestamp
//     * @param @return
//     * @return String
//     * @throws
//     */
//    public static String  getTheFormateSendTimeWithoutHour(long timestamp){
//        long javatimestamp;
//        String s  = String.valueOf(timestamp);
//        if(s.length()==10){
//            s=s+"000";
//            javatimestamp = Long.parseLong(s);
//        }else{
//            javatimestamp = timestamp;
//        }
//
//        long  currentTimestamp  =  System.currentTimeMillis();
//        int i =  Math.abs((int) (currentTimestamp - javatimestamp));
//        int fiveMinuteInSecond  =  60*5;
//        int oneHourInSecond  = 60*60;
//        int oneDayInSecond  = 60*60*24;
//        int twoDayInSecond  = 60*60*24*2;
//        if(i<=fiveMinuteInSecond){
//            return "刚刚";
//        }
//        if(i>oneHourInSecond&&i<=fiveMinuteInSecond){
//            return   StringUtils.convertToRightFormat(String.valueOf((int)(i/60)))+"分钟前";
//        }
//        if(i>fiveMinuteInSecond&&i<=oneDayInSecond){
//            return   StringUtils.convertToRightFormat(String.valueOf((int)(i/(60*60))))+"小时前";
//        }
//        if(i>oneDayInSecond&&i<=twoDayInSecond){
//            return  "昨天";
//        }
//
//        SimpleDateFormat simpleDateFormat  = new SimpleDateFormat("yyyy-MM-dd");
//
//        return   simpleDateFormat.format(new Date(javatimestamp));
//    }

    /**
     * @param @param  timestamp
     * @param @return
     * @return String
     * @throws
     * @Title: getTheFormateSendTime
     * @Description: 格式化发送时间成：几分钟前、几小时前 这样
     */
    public static String getTheFormateSendTime(long timestamp) {
        long javatimestamp;
        String s = String.valueOf(timestamp);
        if (s.length() == 10) {
            s = s + "000";
            javatimestamp = Long.parseLong(s);
        } else {
            javatimestamp = timestamp;
        }

        long currentTimestamp = System.currentTimeMillis();
        int i = Math.abs((int) (currentTimestamp - javatimestamp));
        int fiveMinuteInSecond = 60 * 5;
        int oneHourInSecond = 60 * 60;
        int oneDayInSecond = 60 * 60 * 24;
        int twoDayInSecond = 60 * 60 * 24 * 2;
//        if (i <= fiveMinuteInSecond) {
//            return "刚刚";
//        }
//        if (i > oneHourInSecond && i <= fiveMinuteInSecond) {
//            return StringUtils.convertToRightFormat(String.valueOf((int) (i / 60))) + "分钟前";
//        }
//        if (i > fiveMinuteInSecond && i <= oneDayInSecond) {
//            return StringUtils.convertToRightFormat(String.valueOf((int) (i / (60 * 60)))) + "小时前";
//        }
//        if (i > oneDayInSecond && i <= twoDayInSecond) {
//            return "昨天";
//        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        return simpleDateFormat.format(new Date(javatimestamp));
    }

    /**
     * @param @param  timestamp
     * @param @return
     * @return String
     * @throws
     * @Title: getTheFormateSendTime
     * @Description: 格式化发送时间成：几分钟前、几小时前 这样
     */
    public static String getTheFormateSendTimeWithoutHour(long timestamp) {
        long javatimestamp;
        String s = String.valueOf(timestamp);
        if (s.length() == 10) {
            s = s + "000";
            javatimestamp = Long.parseLong(s);
        } else {
            javatimestamp = timestamp;
        }

        long currentTimestamp = System.currentTimeMillis();
        int i = Math.abs((int) (currentTimestamp - javatimestamp));
        int fiveMinuteInSecond = 60 * 5;
        int oneHourInSecond = 60 * 60;
        int oneDayInSecond = 60 * 60 * 24;
        int twoDayInSecond = 60 * 60 * 24 * 2;
//        if (i <= fiveMinuteInSecond) {
//            return "刚刚";
//        }
//        if (i > oneHourInSecond && i <= fiveMinuteInSecond) {
//            return StringUtils.convertToRightFormat(String.valueOf((int) (i / 60))) + "分钟前";
//        }
//        if (i > fiveMinuteInSecond && i <= oneDayInSecond) {
//            return StringUtils.convertToRightFormat(String.valueOf((int) (i / (60 * 60)))) + "小时前";
//        }
//        if (i > oneDayInSecond && i <= twoDayInSecond) {
//            return "昨天";
//        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        return simpleDateFormat.format(new Date(javatimestamp));
    }

    public static String LeftPad_Tow_Zero(int str) {
        java.text.DecimalFormat format = new java.text.DecimalFormat("00");
        return format.format(str);
    }

    public static long phpTimeStamp2Java(long timestamp) {
        return timestamp * 1000;
    }

    public static long JavaTimeStamp2php(long timestamp) {
        return Math.abs(timestamp / 1000);
    }

    //泛型解析gson
    public static <T> T fromJson(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }

    //泛型解析gson数组
    public static <T> List<T> fromJsonArray(String json, Class<T> clazz) {
        List<T> lst = new ArrayList<T>();
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        for (final JsonElement elem : array) {
            lst.add(new Gson().fromJson(elem, clazz));
        }
        return lst;
    }


    public static void showAlertDialog(Context mContext, String title, String message, String str1, String str2, final OnConfirmListener onConfirmListener) {
        new AlertDialog.Builder(mContext)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(str1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onConfirmListener.onConfirm();
                    }
                })
                .setNegativeButton(str2, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onConfirmListener.onCancel();
                    }
                })
                .show();
    }


    public static void showAlertConfirmDialog(Context mContext, String message, final OnConfirmListener onConfirmListener) {
        new AlertDialog.Builder(mContext)
                .setMessage(message)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onConfirmListener.onConfirm();
                    }
                })
                .show();
    }

    public static void showAlertDialog(Context mContext, String message, final OnConfirmListener onConfirmListener) {
        final AlertDialog alertDialog = new AlertDialog.Builder(mContext)
                .setMessage(message)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onConfirmListener.onConfirm();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onConfirmListener.onCancel();
                    }
                })
                .create();

//        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
//
//            @Override
//            public void onShow(DialogInterface dialog) {
//                Button neg = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
//                Button pos = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
//                neg.setTextColor(Color.BLUE);
//                neg.setBackgroundColor(Color.WHITE);
//                pos.setTextColor(Color.BLUE);
//                pos.setBackgroundColor(Color.WHITE);
//
//            }
//        });

        alertDialog.show();
    }

    public static void showCustomViewAlertDialog(Context mContext, final View view, String title, String message, String str1, String str2, final OnSelectDialogListener onConfirmListener) {
        AlertDialog alertDialog = new AlertDialog.Builder(mContext)
                .setView(view)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(str1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onConfirmListener.onConfirm("");
                    }
                })
                .setNegativeButton(str2, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onConfirmListener.onCancel();
                    }
                }).create();
        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                ((ViewGroup) view.getParent()).removeView(view);
            }
        });
        alertDialog.show();
    }


    public static boolean isCellPhoneNo(String telephone) {

        if (telephone.length() != 11) {
            return false;
        }
        Pattern pattern = Pattern.compile("^1[3,5]\\d{9}||18[6,8,9]\\d{8}$");
        Matcher matcher = pattern.matcher(telephone);

        if (matcher.matches()) {
            return true;
        }
        return true;
    }

    public static boolean isTelephoneNo(String telephone) {


        boolean flag = false;
        try {
            String check = "\\d{3}-\\d{8}|\\d{4}-\\d{7,8}";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(telephone);
            flag = matcher.matches();
        } catch (Exception e) {
            DebugLog.e("请输入正确格式固定电话");
            flag = false;
        }

        return flag;
    }

    public static String saveMyBitmapInRoot(String bitName, Bitmap mBitmap) {
        File f = new File("mnt/sdcard/漫画说/share/" + System.currentTimeMillis() + ".png");
        try {
            f.createNewFile();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            DebugLog.e("在保存图片时出错：" + e.toString());
        }
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
        try {
            fOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return f.getAbsolutePath();
    }

    public static void saveMyBitmap(String bitName, Bitmap mBitmap) {
        File f = new File("mnt/sdcard/漫画说/" + bitName + ".png");
        try {
            f.createNewFile();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            DebugLog.e("在保存图片时出错：" + e.toString());
        }
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
        try {
            fOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 验证邮箱地址是否正确
     *
     * @param email
     * @return
     */
    public static boolean checkEmail(String email) {
        boolean flag = false;
        try {
            String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        } catch (Exception e) {
            DebugLog.e("验证邮箱地址错误");
            flag = false;
        }

        return flag;
    }

    /**
     * 验证手机号码
     *
     * @param mobiles
     * @return
     */
    public static boolean isMobileNO(String mobiles) {
        boolean flag = false;
        try {
            Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
            Matcher m = p.matcher(mobiles);
            flag = m.matches();
        } catch (Exception e) {
            DebugLog.e("验证手机号码错误");
            flag = false;
        }
        return flag;
    }


    /**
     * 验证合法密码
     *
     * @param mobiles
     * @return
     */
    public static boolean isPassWord(String mobiles) {
        boolean flag = false;
        try {
            Pattern p = Pattern.compile("^[a-zA-Z]\\w{5,17}$");
            Matcher m = p.matcher(mobiles);
            flag = m.matches();
        } catch (Exception e) {
            DebugLog.e("非法密码");
            flag = false;
        }
        return flag;
    }

    //保存场景头像图片
    public static void saveAvatarBitmap(String bitName, Bitmap mBitmap) {
        File f = new File("mnt/sdcard/漫画说/avatar/" + bitName + ".png");
        try {
            f.createNewFile();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            DebugLog.e("在保存图片时出错：" + e.toString());
        }
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
        try {
            fOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取文件夹大小
     *
     * @param file File实例
     * @return long
     */
    public static long getFolderSize(File file) {

        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                if (fileList[i].isDirectory()) {
                    size = size + getFolderSize(fileList[i]);

                } else {
                    size = size + fileList[i].length();

                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //return size/1048576;
        return size;
    }

    /**
     * 格式化单位
     *
     * @param size
     * @return
     */
    public static String getFormatSize(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            return size + "Byte(s)";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB";
    }

    public static File writeBitmap(Context context, Bitmap b, String mFileName) {
        long currentAvailByteSize = 0;
        ByteArrayOutputStream by = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG, 100, by);
        byte[] stream = by.toByteArray();
        String mPath = figoRootPath(context);
        if (!mPath.equals("")) {
            if (mPath.contains("figo")) {
                // 查看
                currentAvailByteSize = getAvailableExternalMemorySize();
            } else {
                currentAvailByteSize = getAvailableInternalMemorySize();
            }
            if (currentAvailByteSize > stream.length) {
                return writeToSdcard(stream, mPath, mFileName);
            } else {
                return null;
            }
        }
        return null;
    }

    public static File saveMyBitmap(Context context, Bitmap mBitmap, String bitName) {
        String mPath = figoRootPath(context);
        File f = new File(mPath + bitName);
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
        try {
            fOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fOut.close();
            return f;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /***
     * 乐行本地存储根目录
     */
    public static String figoRootPath(Context context) {
        String path = "";
        // 判断是后有sd卡
        if (SDCardExists()) {
            path = Environment.getExternalStorageDirectory() + "/figo/";
        }
        // 没有sd卡,默认保存在文件的安装路径下
        else {
            path = context.getApplicationContext().getFilesDir()
                    .getAbsolutePath()
                    + "/";
        }
        try {
            File dir = new File(path);
            if (!dir.exists()) {
                dir.mkdirs();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return path;
    }

    public static boolean SDCardExists() {
        return (Environment.getExternalStorageState()
                .equals(Environment.MEDIA_MOUNTED));
    }

    /**
     * 获取手机内部剩余存储空间
     *
     * @return
     */
    public static long getAvailableInternalMemorySize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        return availableBlocks * blockSize;
    }

    /**
     * 获取SDCARD剩余存储空间
     *
     * @return
     */
    public static long getAvailableExternalMemorySize() {
        if (SDCardExists()) {
            File path = Environment.getExternalStorageDirectory();
            StatFs stat = new StatFs(path.getPath());
            long blockSize = stat.getBlockSize();
            long availableBlocks = stat.getAvailableBlocks();
            return availableBlocks * blockSize;
        } else {
            return -1;
        }
    }

    /**
     * @param data     数据
     * @param path     路径
     * @param fileName 文件名
     * @return true成功 false失败
     */
    public static File writeToSdcard(byte[] data, String path, String fileName) {
        FileOutputStream fos = null;
        boolean isSuccess = false;
        try {
            // 判断有没有文件夹
            File filePath = new File(path);
            if (!filePath.exists()) {
                // 创建文件夹
                isSuccess = filePath.mkdirs();
            }
            // 判断有没有同名的文件
            File file = new File(filePath, fileName);
            if (!file.exists()) {
                isSuccess = file.createNewFile();
            }
            //
            // // 有的话，删除
            // if (file.exists()) {
            // file.delete();
            // }
            // 写文件
            fos = new FileOutputStream(file);
            fos.write(data);
            fos.flush();
            return file;
            // }

        } catch (Exception e) {
            Log.e("tag", "" + e.getMessage());
            return null;
            // TODO: handle exception
        } finally {
            try {
                if (fos != null)
                    fos.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /**
     * 检查网络是否可用
     *
     * @return
     */
    public static boolean isConnected(Context context) {
        ConnectivityManager conn = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = conn.getActiveNetworkInfo();
        return (info != null && info.isConnected());
    }

    /**
     * 检查网络是否可用
     *
     * @return
     */
    public static boolean checkNetWork(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            for (NetworkInfo info : connectivity.getAllNetworkInfo()) {
                if (info.isConnected()) {
                    Log.i("checkNetWork", "the " + info.getTypeName() + " is on;");
                    return true;
                } else {
                    Log.i("checkNetWork", "the " + info.getTypeName() + " is off;");
                }
            }
        }
        return false;
    }

    /**
     * 是否开启WIFI
     *
     * @param context
     * @return
     */
    public static boolean isWifiCon(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (mWifi.isConnected()) {
            // Do whatever
            return true;
        }
        return false;
    }

    /**
     * 检查手机上是否安装了指定的软件
     *
     * @param context
     * @param packageName：应用包名
     * @return
     */
    public static boolean isAvilible(Context context, String packageName) {
        //获取packagemanager
        final PackageManager packageManager = context.getPackageManager();
        //获取所有已安装程序的包信息
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        //用于存储所有已安装程序的包名
        List<String> packageNames = new ArrayList<String>();
        //从pinfo中将包名字逐一取出，压入pName list中
        if (packageInfos != null) {
            for (int i = 0; i < packageInfos.size(); i++) {
                String packName = packageInfos.get(i).packageName;
                packageNames.add(packName);
            }
        }
        //判断packageNames中是否有目标程序的包名，有TRUE，没有FALSE
        return packageNames.contains(packageName);
    }

    /**
     * 获取imei
     *
     * @param context
     * @return
     */
    public static String getImei(Context context) {
        String imei = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
        return imei;
    }

    /**
     * 获取mac地址
     *
     * @param context
     * @return
     */
   /* public static String getMac(Context context) {
        WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        return info.getMacAddress();
    }*/

    /**
     * 拨打电话
     *
     * @param context
     * @param number
     */
    public static void dial(Context context, String number) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + number));
        context.startActivity(intent);
    }

    /**
     * 呼叫电话
     *
     * @param context
     * @param number
     */
    public static void call(Context context, String number) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number));
        context.startActivity(intent);
    }

    /**
     * 发送短信
     *
     * @param context
     * @param number  目的电话
     * @param content 内容
     */
    public static void sendSms(Context context, String number, String content) {
        Uri uri = Uri.parse("smsto:" + number);
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        intent.putExtra("sms_body", content);
        context.startActivity(intent);
    }

    /**
     * 获取版本号
     *
     * @param context
     * @return
     * @throws Exception
     */
    public static String getVersionName(Context context) throws Exception {
        // 获取packagemanager的实例
        PackageManager packageManager = context.getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
        String version = packInfo.versionName;
        return version;
    }

    /**
     * 关闭输入法
     *
     * @param acitivity
     */
    public static void hideSoftInput(Activity acitivity) {
        if (acitivity != null && acitivity.getWindow() != null && acitivity.getWindow().getDecorView() != null) {
            InputMethodManager imm = (InputMethodManager) acitivity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(acitivity.getWindow().getDecorView().getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 获取状态栏高度
     *
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        Rect frame = new Rect();
        ((Activity) context).getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        return statusBarHeight;
    }

    /**
     * 分享
     *
     * @param context
     * @param uri
     * @param content
     */
    public static void sendShare(Context context, Uri uri, String content) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Share");
        if (uri != null) {
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_STREAM, uri);
            intent.putExtra("sms_body", content);
        } else {
            intent.setType("text/plain");
        }

        intent.putExtra(Intent.EXTRA_TEXT, content);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //context.startActivity(Intent.createChooser(intent, "分享"));
        context.startActivity(intent);
    }

    /**
     * 获取耗费内存
     *
     * @return 已用内存
     */
    public static long getUsedMemory() {
        long total = Runtime.getRuntime().totalMemory();
        long free = Runtime.getRuntime().freeMemory();
        return total - free;
    }

    /**
     * 打开输入法
     *
     * @param context
     */
    public static void openInput(Context context, EditText submitBt) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(submitBt, InputMethodManager.SHOW_IMPLICIT);
    }

    /**
     * 获取包名
     *
     * @param context
     * @return
     */
    public static String getPackageName(Context context) {
        try {
            String pkName = context.getPackageName();
            String versionName = context.getPackageManager().getPackageInfo(
                    pkName, 0).versionName;
            int versionCode = context.getPackageManager()
                    .getPackageInfo(pkName, 0).versionCode;
            return pkName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getAppName() {
        return appName;
    }

    //压缩上传的头像
    public static Bitmap compressImage(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 10) {    //循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;//每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
        return bitmap;
    }

    /**
     * 根据日期获得星期
     *
     * @param date
     * @return
     */
    public static String getWeekOfDate(Date date) {
        String[] weekDaysName = {"星期六", "星期日", "星期一", "星期二", "星期三", "星期四", "星期五"};
        String[] weekDaysCode = {"0", "1", "2", "3", "4", "5", "6"};
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        return weekDaysName[intWeek];
    }

    public static String getWeek(String pTime) {


        String Week = "星期";


        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {

            c.setTime(format.parse(pTime));

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            Week += "天";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 2) {
            Week += "一";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 3) {
            Week += "二";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 4) {
            Week += "三";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 5) {
            Week += "四";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 6) {
            Week += "五";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 7) {
            Week += "六";
        }


        return Week;
    }

    public static void callService(Context mContext, String phone_number) {
        TelephonyManager telephony = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);

        if (telephony != null) {
            //封装一个拨打电话的intent，并且将电话号码包装成一个Uri对象传入
            Uri telUri = Uri.parse("tel:" + phone_number);
            Intent intent = new Intent(Intent.ACTION_DIAL, telUri);
            mContext.startActivity(intent);
            // 加入动画
            //overridePendingTransition(R.anim.translate_to_left, R.anim.translate_to_left_hide);
        }
    }

    public static String getPathFromUri(Context mContext, Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(mContext, contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    /**
     * 删除指定目录下文件及目录
     *
     * @param deleteThisPath
     * @param filepath
     * @return
     */
    public void deleteFolderFile(String filePath, boolean deleteThisPath) {
        if (!TextUtils.isEmpty(filePath)) {
            try {
                File file = new File(filePath);
                if (file.isDirectory()) {// 处理目录
                    File files[] = file.listFiles();
                    for (int i = 0; i < files.length; i++) {
                        deleteFolderFile(files[i].getAbsolutePath(), true);
                    }
                }
                if (deleteThisPath) {
                    if (!file.isDirectory()) {// 如果是文件，删除
                        file.delete();
                    } else {// 目录
                        if (file.listFiles().length == 0) {// 目录下没有文件或者目录，删除
                            file.delete();
                        }
                    }
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


    //根据uri获取图片路径

    private void abortTM(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        tm.getCallState();//int

        tm.getCellLocation();//CellLocation

        tm.getDeviceId();//String

        tm.getDeviceSoftwareVersion();//String

        tm.getLine1Number();//String

        tm.getNeighboringCellInfo();//List<NeighboringCellInfo>

        tm.getNetworkCountryIso();//String

        tm.getNetworkOperator();//String

        tm.getNetworkOperatorName();//String

        tm.getNetworkType();//int

        tm.getPhoneType();//int

        tm.getSimCountryIso();//String

        tm.getSimOperator();//String

        tm.getSimOperatorName();//String

        tm.getSimSerialNumber();//String

        tm.getSimState();//int

        tm.getSubscriberId();//String

        tm.getVoiceMailAlphaTag();//String

        tm.getVoiceMailNumber();//String

        tm.hasIccCard();//boolean

        tm.isNetworkRoaming();//
    }

}
