package com.example.sendapp.ui.util;

/**
 * 主要用于进行与远程ICE进行通讯
 * 
 * @author ericgoo
 * 
 */


import com.example.sendapp.ui.app.IntHolder;
import com.example.sendapp.ui.app.inter.MQInterfacePrx;



/**
 *
 */
public class ICEUtil {
	public MQInterfacePrx m_iceObject;
	private String m_sError = null;
	private int m_iError = 0;

	// getFileCompressed
	public byte[] getFileCompressed(String sFile, int pos, int num,
									IntHolder iResult) {
		// IntHolder iResult = new IntHolder();
		byte[] bytes = null;

		m_iError = -1;

		try {
			//todo 文件下载
//			bytes = m_iceObject.getFileCompressed(fromUnicode(sFile), pos, num,
//					iResult);
		} catch (Exception e) {
			e.printStackTrace();

			m_sError = e.toString();
			m_iError = -1;

		}
		return bytes;

	}

	// 用于文件下载

//	public SelectHelp getFileInfo(String sFilePath) {
//		SelectHelp help = new SelectHelp();
//		StringHolder set = new StringHolder();

//		try {

//			boolean bok = m_iceObject.getFileInfo(fromUnicode(sFilePath), set);
//
//			if (bok) {
//				help.mReturnCode = 1;
//				help.fromString(toUnicode(set.value));
//			} else {
//				help.mReturnCode = -1;
//			}
//			return help;
//
//		} catch (Exception e) {
//			e.printStackTrace();
//
//			m_sError = e.toString();
//			m_iError = -1;
//			help.mReturnCode = -1;
//			help.mReturnError = m_sError;
//			return help;
//
//		}
//	}


}
