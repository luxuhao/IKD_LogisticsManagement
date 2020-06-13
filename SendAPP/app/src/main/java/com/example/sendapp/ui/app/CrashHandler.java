package com.example.sendapp.ui.app;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by shunzi on 2018/3/12.
 * edit:2018-3-12
 *      存储crash信息到本地/sdcard/crash目录下
 *      信息可提交到云平台
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    /**
     * 系统默认UncaughtExceptionHandler
     */
    private Thread.UncaughtExceptionHandler mDefaultHandler;

    /**
     * context
     */
    private Context mContext;

    /**
     * 存储异常和参数信息
     */
    private Map<String, String> paramsMap = new HashMap<>();

    /**
     * 格式化时间
     */
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

    private String TAG = this.getClass().getSimpleName();

    private static CrashHandler mInstance;

    private ISendCarch mSendCarch;
    private CrashHandler() {

    }

    /**
     * 获取CrashHandler实例
     */
    public static synchronized CrashHandler getInstance() {
        if (null == mInstance) {
            mInstance = new CrashHandler();
        }
        return mInstance;
    }

    public void init(Context context) {
        mContext = context;
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        //设置该CrashHandler为系统默认的
        Thread.setDefaultUncaughtExceptionHandler(this);


        initTalkingDataCrash();
    }

    /**
     * uncaughtException 回调函数
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
//        Log.e("xsy", ex.getMessage());
//        ex.printStackTrace();
//        if (!handleException(ex) && mDefaultHandler != null) {//如果自己没处理交给系统处理
//            mDefaultHandler.uncaughtException(thread, ex);
//        } else {//自己处理
//            try {//延迟2秒杀进程
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                Log.e(TAG, "error : ", e);
//            }
//            //退出程序
//            android.os.Process.killProcess(android.os.Process.myPid());
//            System.exit(1);
//        }

        try {
            String crashMessage = saveToSDCard(ex);

            ChangeErrorMessage(ex, crashMessage);


        } catch (Exception e) {
            e.printStackTrace();
        }
        if (mDefaultHandler != null) {
            mDefaultHandler.uncaughtException(thread, ex);
        }
    }

    public Throwable ChangeErrorMessage(Throwable ex, String errorMessage){
        try {
            Class<?> clazz = Class.forName("java.lang.Throwable");
            Field mCallback = clazz.getDeclaredField("detailMessage");
            mCallback.setAccessible(true);
            mCallback.set(ex, errorMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ex;
    }

    /**
     * 收集错误信息.发送到服务器
     *
     * @return 处理了该异常返回true, 否则false
     */
    @Deprecated
    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }
        //收集设备参数信息
        collectDeviceInfo(mContext);
        //添加自定义信息
        addCustomInfo();
        //使用Toast来显示异常信息
//        new Thread() {
//            @Override
//            public void run() {
//                Looper.prepare();
//                Toast.makeText(mContext, "程序开小差了呢..", Toast.LENGTH_SHORT).show();
//                Looper.loop();
//            }
//        }.start();
        //保存日志文件
        saveCrashInfo2File(ex);
        return true;
    }


    /**
     * 收集设备参数信息
     *
     * @param ctx
     */
    @Deprecated
    public void collectDeviceInfo(Context ctx) {
        //获取versionName,versionCode
        try {
            PackageManager pm = ctx.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(), PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                String versionName = pi.versionName == null ? "null" : pi.versionName;
                String versionCode = pi.versionCode + "";

                paramsMap.put("versionName", versionName);
                paramsMap.put("versionCode", versionCode);
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "an error occured when collect package info", e);
        }
        //获取所有系统信息
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                paramsMap.put(field.getName(), field.get(null).toString());
            } catch (Exception e) {
                Log.e(TAG, "an error occured when collect crash info", e);
            }
        }
    }

    /**
     * 添加自定义参数
     */
    @Deprecated
    private void addCustomInfo() {

    }

    /**
     * 保存错误信息到文件中
     *
     * @param ex
     * @return 返回文件名称, 便于将文件传送到服务器
     */
    @Deprecated
    private String saveCrashInfo2File(Throwable ex) {

        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key + "=" + value + "\n");
        }

        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        String result = writer.toString();
        sb.append(result);
        try {
            long timestamp = System.currentTimeMillis();
            String time = format.format(new Date());
            String fileName = "crash-" + time + "-" + timestamp + ".log";
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/crash/";
                File dir = new File(path);
                if (dir.exists()){
                    FileOutputStream fos = new FileOutputStream(path + fileName);
                    fos.write(sb.toString().getBytes());
                    fos.flush();
                    fos.close();
                }else {
                    if (dir.mkdirs()) {
                        FileOutputStream fos = new FileOutputStream(path + fileName);
                        fos.write(sb.toString().getBytes());
                        fos.flush();
                        fos.close();
                    } else {
                        Log.e(TAG, "log file create dir fail.");
                    }
                }
            }
            return fileName;
        } catch (Exception e) {
            Log.e(TAG, "an error occured while writing file...", e);
        }
        return null;
    }

    private File getCrashFile(){
        return new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "crash");
    }
    private String saveToSDCard(Throwable ex) throws Exception {
        String result = "";
        Writer writer = new StringWriter();

        File logFolder = getCrashFile();
        if(!logFolder.exists()){
            logFolder.mkdir();
        }

        if(!logFolder.exists()){
            throw new Exception("创建文件夹失败：" + logFolder.getAbsolutePath() + "\r\n" + ex.getMessage());
        }

        List<File> list = listPathFiles(logFolder.getAbsolutePath());
        if (list.size() > 9) {
            long tempTime = list.get(0).lastModified();
            File tempF = list.get(0);
            for (File f : list) {
                if (f.lastModified() < tempTime) {
                    tempTime = f.lastModified();
                    tempF = f;
                }
            }
            tempF.delete();
        }

        //创建文件
        SimpleDateFormat df = new SimpleDateFormat("MM_dd_HH_mm_ss");
        String newFileName = df.format(new Date());
        File file = new File(logFolder.getAbsolutePath(), newFileName + ".txt");
        file.createNewFile();

        PrintWriter pw = new PrintWriter(writer);
//        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(
//                file, append)));
        // 导出发生异常的时间
        pw.println("Date[MM_dd_HH_mm_ss]: " + newFileName);
        // 导出手机信息
        dumpPhoneInfo(pw);
        pw.println();
        // 导出异常的调用栈信息
        ex.printStackTrace(pw);
        //pw.println();

        result = writer.toString();
        pw.close();

        //写入文件
        FileOutputStream outputStream = new FileOutputStream(file);
        outputStream.write(result.getBytes());
        outputStream.close();

        return result;
    }

    private static List<File> listPathFiles(String root){
        List<File> allDir = new ArrayList<>();
        File path = new File(root);
        if(!path.exists()){
            path.mkdir();
        }
        File[] files = path.listFiles();
        if(null != files) {
            for (File f : files) {
                if (f.isFile()) {
                    allDir.add(f);
                }
            }
        }
        return allDir;
    }

    private String getPackageName() {
        try {
            return mContext.getPackageName();
        }catch (Exception e){

        }
        return "unknown";
//        PackageManager pm = mContext.getPackageManager();
//        PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(),
//                PackageManager.GET_ACTIVITIES);
//        return pi.packageName;
    }
    private void dumpPhoneInfo(PrintWriter pw) throws PackageManager.NameNotFoundException {
        // 应用的版本名称和版本号
        PackageManager pm = mContext.getPackageManager();
        PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(),
                PackageManager.GET_ACTIVITIES);

//        pw.print("Environment: ");  //当前环境
//        pw.print(GameCatSDK.CurrentEnvironment);
//        pw.println();

        pw.print("App Version[versionName_versionCode]: ");
        pw.print(pi.versionName);
        pw.print('_');
        pw.println(pi.versionCode);
        pw.println();

        pw.print("App PackageName: ");
        pw.print(getPackageName()); //pi.packageName
        pw.println();

        pw.print("App Name: ");
        pw.print(pi.applicationInfo.loadLabel(pm));
        pw.println();

        // android版本号
        pw.print("OS Version[versionName_versionCode]: ");
        pw.print(Build.VERSION.RELEASE);
        pw.print("_");
        pw.println(Build.VERSION.SDK_INT);
        //pw.println();

        // 手机制造商
        pw.print("Vendor: ");
        pw.println(Build.MANUFACTURER);
        //pw.println();

        // 手机型号
        pw.print("Model: ");
        pw.println(Build.MODEL);
        //pw.println();

        // cpu架构
        pw.print("CPU ABI: ");
        pw.println(Build.CPU_ABI);
        //pw.println();

//        pw.println(version);
    }


    interface ISendCarch{
        void ActionCrash(Throwable e);
    }
    private void initTalkingDataCrash(){
        try {
            String packageName = getPackageName();
            //com.tendcloud.tenddata.TCAgent.LOG_ON = true;
//            com.tendcloud.tenddata.TCAgent.init(mContext, "7E267152ADF1404B9EED4614F39F1F36", packageName);
//
//            //com.tendcloud.tenddata.TCAgent.setReportUncaughtExceptions(true);
//            mSendCarch = new ISendCarch() {
//                @Override
//                public void ActionCrash(Throwable e) {
//                    com.tendcloud.tenddata.TCAgent.onError(mContext,e);
//                }
//            };

            try {
                //反射方式调用异常上报
                final Class<?> clazz = Class.forName("com.tendcloud.tenddata.TCAgent");
                if(null == clazz){return;}
                Method init_method = clazz.getMethod("init", new Class[]{Context.class, String.class, String.class});
                init_method.invoke(clazz, new Object[]{mContext,"710E313589F742BBBF852E256F10B755", packageName});

                mSendCarch = new ISendCarch() {
                    @Override
                    public void ActionCrash(Throwable e) {
                        try {
                            Method onError_method = clazz.getMethod("onError", new Class[]{Context.class, Throwable.class});
                            onError_method.invoke(clazz,new Object[]{mContext, e});
                        } catch (NoSuchMethodException e1) {
                            e1.printStackTrace();
                        } catch (IllegalAccessException e1) {
                            e1.printStackTrace();
                        } catch (InvocationTargetException e1) {
                            e1.printStackTrace();
                        }
                    }
                };
            } catch (Exception e) {
                e.printStackTrace();
            }

            new CusAsyncTask().execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class CusAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                SendCrash();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private void SendCrash() throws IOException {
        //读取文件上传
        if(null != mSendCarch){
            List<File> list = listPathFiles(getCrashFile().getAbsolutePath());
            int length = 0;
            for (File f : list) {
                FileInputStream fin = new FileInputStream(f.getAbsolutePath());
                length = fin.available();
                if(length < 2048) {
                    byte[] buffers = new byte[length];
                    fin.read(buffers);
                    String res = new String(buffers, "UTF-8");

                    Throwable throwable = new Throwable(res);
                    mSendCarch.ActionCrash(throwable);
                }
                fin.close();
                f.delete();
            }
        }
    }
}
