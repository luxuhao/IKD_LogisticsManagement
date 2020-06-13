package com.example.sendapp.ui.app;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;

import com.example.sendapp.BuildConfig;
import com.example.sendapp.ui.db.AssetsDatabaseManager;
import com.example.sendapp.ui.db.CookieDB;
import com.example.sendapp.ui.util.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AppConText extends Application {
    // 版本发布的目
    public static String mSUpgradePath = "release"; // 版本发布的服务器目录

    public static final int NETTYPE_WIFI = 0x01;
    public static final int NETTYPE_CMWAP = 0x02;
    public static final int NETTYPE_CMNET = 0x03;
    public static final String STORE_PIC_PATH = "tn_pic";
    public String msAppName = "SendApp";
    public static final String S_REMOTE_PIC_PATH = "/upfile/upload/";
    public String mUserName = "admin";
    private Map<String, String> mSQLMap = new HashMap<String, String>();


    @Override
    public void onCreate() {
        super.onCreate();

        if(!Build.BRAND.equalsIgnoreCase("Honeywell")) {
            CrashHandler.getInstance().init(this);
        }
    }
    public AppConText() {
        super();

    }
    // 用于保存配置信息
    public static CookieDB mCookieDB = null;
    public void initApp() {

        mCookieDB = new CookieDB(this);

        AssetsDatabaseManager.initManager(this);

    }

    public void convertSQLMap(String sContent) {
        if (sContent == null)
            return;

        mSQLMap.clear();
        String[] vsAll = sContent.split(";");

        for (int i = 0; i < vsAll.length; i++) {
            String[] vSub = vsAll[i].split("@");

            if (vSub.length >= 2) {

                mSQLMap.put(vSub[0].trim(), vSub[1]);
            }
        }
    }

    public String getSQL(String sType) {
        return mSQLMap.get(sType);
    }
    /**
     * 设置Cookie，可以持续保存�??
     *
     * @return
     */
    public void setCookie(String k, String v) {
        mCookieDB.putString(k, v);
    }

    public void setCookieInt(String k, int v) {
        mCookieDB.putInt(k, v);
    }

    public void setCookieBoolean(String k, boolean v) {
        mCookieDB.putBoolean(k, v);
    }

    public void setCookieList(String k, ArrayList<String> v) {
        mCookieDB.putList(k, v);
    }

    /**
     * 查询 Cookie
     *
     * @return 返回查到的Cookie
     */
    public String getCookie(String k) {
        if(mCookieDB == null){
            initApp();
        }
        return mCookieDB.getString(k);
    }

    public int getCookieInt(String k) {
        return mCookieDB.getInt(k);
    }

    public boolean getCookieBoolean(String k) {
        return mCookieDB.getBoolean(k);
    }

    public boolean getCookieBooleanDefTrue(String k) {
        return mCookieDB.getBooleanDefTrue(k);
    }

    public ArrayList<String> getCookieList(String k) {
        return mCookieDB.getList(k);
    }


    public boolean isCheckUp() {
        if (Integer.parseInt(getCookie("is_check_up")) == 0) {
            return false;
        }
        return true;
    }
    /**
     * �?测网络是否可�?
     *
     * @return
     */
    public boolean isNetworkConnected() {
        if(BuildConfig.DEBUG){
            return true;
        }
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni != null && ni.isConnectedOrConnecting();
    }

    /**
     * 获取当前网络类型
     *
     * @return 0：没有网�? 1：WIFI网络 2：WAP网络 3：NET网络
     */
    @SuppressLint("DefaultLocale")
    public int getNetworkType() {
        int netType = 0;
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null) {
            return netType;
        }
        int nType = networkInfo.getType();
        if (nType == ConnectivityManager.TYPE_MOBILE) {
            String extraInfo = networkInfo.getExtraInfo();
            if (!StringUtils.isEmpty(extraInfo)) {
                if (extraInfo.toLowerCase().equals("cmnet")) {
                    netType = NETTYPE_CMNET;
                } else {
                    netType = NETTYPE_CMWAP;
                }
            }
        } else if (nType == ConnectivityManager.TYPE_WIFI) {
            netType = NETTYPE_WIFI;
        }
        return netType;
    }

    /**
     * 判断当前版本是否兼容目标版本的方�?
     *
     * @param VersionCode
     * @return
     */
    public static boolean isMethodsCompat(int VersionCode) {
        int currentVersion = android.os.Build.VERSION.SDK_INT;
        return currentVersion >= VersionCode;
    }

    /**
     * 获取App安装包信�?
     *
     * @return
     */
    public PackageInfo getPackageInfo() {
        PackageInfo info = null;
        try {
            info = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace(System.err);
        }
        if (info == null)
            info = new PackageInfo();
        return info;
    }

    public static boolean isSDAviable() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            // sd card 可用
            return true;

        }

        return false;
    }

    public String getPictureDirectory() {

        if (isSDAviable()) {
            File directory = null;
            directory = new File(Environment.getExternalStorageDirectory()
                    .getAbsolutePath()
                    + File.separator
                    + STORE_PIC_PATH);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            return directory.getAbsolutePath() + "/";
        }

        // 只能存在本地文件�?
        String sDest = getApplicationContext().getFilesDir().getPath()
                + File.separator + STORE_PIC_PATH + File.separator;

        File directory = new File(sDest);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        return sDest;

    }

    // ///////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////

    public static void popMsg(Context ac, String title, String msg) {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(ac);

        alertDialog.setTitle(title);
        //todo
//        alertDialog.setIcon(R.drawable.ic);

        alertDialog.setMessage(msg);

        alertDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                    }
                });

        alertDialog.show();

    }
    // 获取服务器上Android App的版本号
    public int getServerAppVersion(String appName) {
        String sVersion = "";
       //todo 获取版本号

        if (sVersion.length() <= 0)
            return -1;

        return Integer.parseInt(sVersion);
    }

    public String RemoteFileURL() {
//        String sUpURL = "http://";
//        sUpURL += getCookie("http_server");
//        sUpURL += ":";
//        sUpURL += getCookie("http_port");
//        sUpURL += S_REMOTE_PIC_PATH;
        String sUpURL = "http://10.2.4.162:8080/SSMBase_war_exploded/login";
        return sUpURL;
    }


}
