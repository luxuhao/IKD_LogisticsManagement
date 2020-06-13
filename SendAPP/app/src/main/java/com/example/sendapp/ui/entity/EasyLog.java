package com.example.sendapp.ui.entity;

import android.util.Log;

/**
 * 该项目统一日志打印管理类。 使用统一的日志标签。
 * 
 */
public class EasyLog {

	/** 统一使用的日志标签。 */
	private static String TAG = "com.winso.break_law.log";

	/**
	 * 打印调试类型日志信息。
	 * 
	 * @param message
	 *            要打印的内容。
	 */
	@Deprecated
	public static void debug(String message) {
		android.util.Log.d(TAG, message);
	}

	/**
	 * 打印错乱类型日志信息。
	 *
	 * @param message
	 *            要打印的内容。
	 */
	@Deprecated
	public static void error(String message) {
		android.util.Log.e(TAG, message);
	}

	/**
	 * 打印普通类型日志信息。
	 *
	 * @param message
	 *            要打印的内容。
	 */
	@Deprecated
	public static void info(String message) {
		android.util.Log.i(TAG, message);
	}

	/**
	 * 打印长日志。
	 *
	 * @param message
	 *            要打印的内容。
	 */
	@Deprecated
	public static void verbose(String message) {
		android.util.Log.v(TAG, message);
	}

	/**
	 * 打印警告类型日志信息。
	 *
	 * @param message
	 *            要打印的内容。
	 */
	@Deprecated
	public static void warn(String message) {
		android.util.Log.w(TAG, message);
	}

	public static String customTagPrefix = "TNMes_APP";



	private static String generateTag(StackTraceElement caller) {
		String tag = ":%s[%s, %d]";
		String callerClazzName = caller.getClassName();
		callerClazzName = callerClazzName.substring(callerClazzName
				.lastIndexOf(".") + 1);
		tag = String.format(
				tag,
				callerClazzName, caller.getMethodName(),
				Integer.valueOf(caller.getLineNumber()));
		tag = customTagPrefix + ":" + tag;
		return tag;
	}

	public static void e(String content) {
		StackTraceElement caller = getCallerStackTraceElement();
		String tag = generateTag(caller);
		Log.e(tag, content);
	}

	public static void e(String content, Throwable tr) {
		StackTraceElement caller = getCallerStackTraceElement();
		String tag = generateTag(caller);
		Log.e(tag, content, tr);
	}

	public static void i(String content) {
		StackTraceElement caller = getCallerStackTraceElement();
		String tag = generateTag(caller);
		Log.i(tag, content);
	}

	public static void i(String content, Throwable tr) {
		StackTraceElement caller = getCallerStackTraceElement();
		String tag = generateTag(caller);
		Log.i(tag, content, tr);
	}

	public static void v(String content) {
		StackTraceElement caller = getCallerStackTraceElement();
		String tag = generateTag(caller);
		Log.v(tag, content);
	}

	public static void v(String content, Throwable tr) {
		StackTraceElement caller = getCallerStackTraceElement();
		String tag = generateTag(caller);
		Log.v(tag, content, tr);
	}

	public static void w(String content) {
		StackTraceElement caller = getCallerStackTraceElement();
		String tag = generateTag(caller);
		Log.w(tag, content);
	}

	public static void w(String content, Throwable tr) {
		StackTraceElement caller = getCallerStackTraceElement();
		String tag = generateTag(caller);
		Log.w(tag, content, tr);
	}

	public static void w(Throwable tr) {
		StackTraceElement caller = getCallerStackTraceElement();
		String tag = generateTag(caller);
		Log.w(tag, tr);
	}

	public static StackTraceElement getCallerStackTraceElement() {
		return Thread.currentThread().getStackTrace()[4];
	}
}
