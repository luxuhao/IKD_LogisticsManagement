package com.example.sendapp.ui.our;

/***************************************************************************
*
* 项目名称
_          __  _____   _____    _   _        ___  ___       ___   __   _  
| |        / / /  _  \ |  _  \  | | / /      /   |/   |     /   | |  \ | | 
| |  __   / /  | | | | | |_| |  | |/ /      / /|   /| |    / /| | |   \| | 
| | /  | / /   | | | | |  _  /  | |\ \     / / |__/ | |   / / | | | |\   | 
| |/   |/ /    | |_| | | | \ \  | | \ \   / /       | |  / /  | | | | \  | 
|___/|___/     \_____/ |_|  \_\ |_|  \_\ /_/        |_| /_/   |_| |_|  \_| 
*
*
* Copyright (C) 2015, 谷伟年 [Eric Goo] , <guwncn@gmail.com>.
* 文件修改记录:
*          2015 10.2 新建   by Eric Goo
*		        启用界面
**/

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sendapp.R;


/**
 * 应用程序启动类：显示欢迎界面并跳转到主界
 * 
 * @author eric goo
 * @version 1.0
 * @created 2014-09-01
 */
public class AppStart extends AppCompatActivity {

	// private static final String TAG = "AppStart";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	
		final View view = View.inflate(this, R.layout.start, null);

		setContentView(view);

		// 渐变展示启动
		AlphaAnimation aa = new AlphaAnimation(0.3f, 1.0f);
		aa.setDuration(2000);
		view.startAnimation(aa);
		aa.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationEnd(Animation arg0) {
				redirectTo();
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationStart(Animation animation) {
			}

		});

	}

	/**
	 * 分析显示的时�??
	 * 
	 * @param time
	 * @return
	 */
	private long[] getTime(String time) {
		long res[] = new long[2];
		try {
			time = time.substring(0, time.indexOf("."));
			String t[] = time.split("-");
			res[0] = Long.parseLong(t[0]);
			if (t.length >= 2) {
				res[1] = Long.parseLong(t[1]);
			} else {
				res[1] = Long.parseLong(t[0]);
			}
		} catch (Exception e) {
		}
		return res;
	}

	/**
	 * 跳转�??...
	 */
	private void redirectTo() {
		Intent intent = new Intent(this, LoginActivity.class);
		startActivity(intent);
		finish();
	}


}