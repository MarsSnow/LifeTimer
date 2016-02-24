/**全局使用：文件存储**/
package peng.bo.ploto2003ub.lifetime.common;

import android.content.Context;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.Properties;

import peng.bo.ploto2003ub.lifetime.savetime.B_SetActivity;
//全局类
public class global {

	String m_str1;
	Context m_context;
	Properties m_properties;
	int i = 0;

	public global(Context paramContext) {
		this.m_context = paramContext;
	}

	// 通过统一资源标识符获取字符串
	public String getStringByUrl(URL paramURL) throws Exception {
		HttpURLConnection localHttpURLConnection = (HttpURLConnection) paramURL
				.openConnection();
		localHttpURLConnection.connect();
		BufferedReader localBufferedReader = new BufferedReader(
				new InputStreamReader(localHttpURLConnection.getInputStream()));
		StringBuffer localStringBuffer = new StringBuffer();
		while (true) {
			String str = localBufferedReader.readLine();
			if (str == null)
				return localStringBuffer.toString();
			localStringBuffer.append(str);
		}
	}

	// 获取文件
	// 第一个参数为路径名
	// 第二个参数为文件名
	public String getfile(String paramString1, String paramString2) {
		if (paramString2.equals(""))
			paramString2 = "default";
		try {
			this.m_properties = new Properties();
			FileInputStream localFileInputStream = this.m_context
					.openFileInput(paramString1);
			this.m_properties.load(localFileInputStream);
			String str2 = this.m_properties.get(paramString2).toString();
			m_str1 = str2;

			// return str1;
		} catch (FileNotFoundException localFileNotFoundException) {
			// while (true)
			// str1 = "";
		} catch (IOException localIOException) {
			// while (true)
			// str1 = "";
		}
		return m_str1;
	}

	// 放入文件
	// 第一个参数为文件名
	// 第二个参数为文件内容
	// 第三个参数为文件路径名
	public boolean putfile(String paramString1, String paramString2,
			String paramString3) {
		if (paramString1.equals(""))
			paramString1 = "default";
		try {
			this.m_properties = new Properties();
			this.m_properties.put(paramString1, paramString2);
			FileOutputStream localFileOutputStream = this.m_context.openFileOutput(
					paramString3, 2);
			this.m_properties.store(localFileOutputStream, "");
			i = 1;
			return true;
		} catch (FileNotFoundException localFileNotFoundException) {
			// while (true)
			// i = 0;
		} catch (IOException localIOException) {
			// while (true)
			// i = 0;
		}
		return false;
	}

	//提示框
	public void toast(String paramString) {
		Toast.makeText(this.m_context, paramString, Toast.LENGTH_SHORT).show();
	}
}
