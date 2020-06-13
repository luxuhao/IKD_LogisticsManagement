package com.example.sendapp.ui.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import com.example.sendapp.ui.util.DownloadFileTask;

import java.io.File;

/**
 * 应用程序Activity的基类
 * 
 * @author july
 * @version 1.0
 * @created 2020-06-12
 */
public class TNUpgradeActivity extends Activity {
	private String mSApkPath = ""; // APk保存目录
	private String sApkName = "";
	private int miServerVersion = -1;
	private String updateMsg = "您有新的版本";
	public String mSApkName = "sendapp";
	public String mReturnMessage = "";
	public AppConText appContext;
	
	private boolean mNeedPopInfo = false;

	public void enablePopInfo(boolean bOK) {
		mNeedPopInfo = bOK;
	}

	// 设置App名称
	public void setAppName(String sApp) {
		mSApkName = sApp;
	}

	MyCallBackUpgrade myCallback;

	public class MyCallBackUpgrade implements CallbackInterface {
		TNUpgradeActivity mAc;

		public void func(String responseText) {

			if (responseText == null || responseText == "") {
				AppConText.popMsg(TNUpgradeActivity.this, "",
						"下载失败或者服务器文件不存在");
				return;
			}

			File ApkFile = new File(responseText);

			// 是否已下载更新文件
			if (ApkFile.exists()) {

				installApk(responseText);
				return;
			}

		}

		public void cancel(String responseText) {
			AppConText.popMsg(TNUpgradeActivity.this, "", "文件不存在或者下载失败");
		}

		MyCallBackUpgrade(TNUpgradeActivity ac) {
			mAc = ac;
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		myCallback = new MyCallBackUpgrade(this);
		mSApkPath = Environment.getExternalStorageDirectory().getAbsolutePath();
		// 判断是否挂载了SD卡
		String storageState = Environment.getExternalStorageState();
		if (storageState.equals(Environment.MEDIA_MOUNTED)) {
			mSApkPath = Environment.getExternalStorageDirectory()
					.getAbsolutePath() + "/touchnet/Update/";
			File file = new File(mSApkPath);
			if (!file.exists()) {
				file.mkdirs();
			}
		}

	}

	public boolean checkVersion(int iVersion) {
		// 检查新版本
		// 网络连接判断
		if (!appContext.isNetworkConnected())
			AppConText.popMsg(this, "", "网络未连接");

		if (appContext.isCheckUp()) {
			Dialog noticeDialog;

			int iC = getNeedUpgrade(iVersion);
			if (iC == 1) {
				mReturnMessage = "有新的版本";
				AlertDialog.Builder builder = new Builder(this);
				builder.setTitle("软件版本更新");
				builder.setMessage(updateMsg + " " + miServerVersion);
				builder.setPositiveButton("立即更新",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
                                                int which) {
								dialog.dismiss();
								downloadApk();
							}
						});
				builder.setNegativeButton("以后再说",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
                                                int which) {
								dialog.dismiss();
							}
						});
				noticeDialog = builder.create();
				noticeDialog.show();
			} else if (iC == 2) {

				installApk(mSApkPath + "/" + sApkName);
			} else {
				if (mNeedPopInfo) {
					//TNAppContext.popMsg(this, "", "没有新的版本");
					mReturnMessage = "没有新的版本";
					Toast.makeText(getApplicationContext(), "没有新的版本",
							Toast.LENGTH_SHORT).show();
				}

			}

		}
		return true;
	}

	void downloadApk() {
		sApkName = mSApkName + ".apk";
	// todo 文件下载
		new DownloadFileTask( this, "release/" + sApkName,
				mSApkPath, myCallback).execute();
	}

	/*
	 * 用于下载Apk 返回０代表没有新版本，１代表有新版本则没有下载，２代表有新版本但已经下载
	 */

	private int getNeedUpgrade(int iVersion) {
		try {

			PackageInfo info = getPackageManager().getPackageInfo(
					getPackageName(), 0);
			// curVersionCode = info.versionCode;
			miServerVersion = iVersion;
			//appContext.getServerAppVersion(mSApkName);

			sApkName = mSApkName + ".apk";

			if (miServerVersion <= 0)
				return 0;

			if (miServerVersion > info.versionCode) {
				File ApkFile = new File(mSApkPath + "/" + sApkName);
				if (ApkFile.exists()) {
					//todo 版本更新使用
//					SelectHelp help =m_iceUp
//							.getFileInfo(AppConText.mSUpgradePath + "/"
//									+ sApkName);
//					if (help.size() > 0) {
//						if (FileUtils.getFileSize(mSApkPath + "/" + sApkName) == Integer
//								.valueOf(help.valueStringByName(0, "size"))) {
//							return 2;
//						}
//					}

					return 1;
				}
				return 1;
			}

		} catch (NameNotFoundException e) {
			e.printStackTrace(System.err);
		}

		return 0;
	}

	/**
	 * 安装apk
	 * 
	 * @param sFile
	 */
	private void installApk(String sFile) {
		File apkfile = new File(sFile);
		if (!apkfile.exists()) {
			return;
		}

		// UIHelper.showMsg(MainActivity.this, "", apkfile.toString());

		Intent i = new Intent(Intent.ACTION_VIEW);

		// i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

		i.setDataAndType(Uri.parse("file://" + apkfile.toString()),
				"application/vnd.android.package-archive");
		startActivity(i);
	}

}
