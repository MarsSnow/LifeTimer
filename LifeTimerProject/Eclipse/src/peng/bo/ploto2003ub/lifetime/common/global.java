/**ȫ��ʹ�ã��ļ��洢**/
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
//ȫ����
public class global {

	String str1;
	Context c;
	Properties p;
	int i = 0;

	public global(Context paramContext) {
		this.c = paramContext;
	}

	// ͨ��ͳһ��Դ��ʶ����ȡ�ַ���
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

	// ��ȡ�ļ�
	// ��һ������Ϊ·����
	// �ڶ�������Ϊ�ļ���
	public String getfile(String paramString1, String paramString2) {
		if (paramString2.equals(""))
			paramString2 = "default";
		try {
			this.p = new Properties();
			FileInputStream localFileInputStream = this.c
					.openFileInput(paramString1);
			this.p.load(localFileInputStream);
			String str2 = this.p.get(paramString2).toString();
			str1 = str2;

			// return str1;
		} catch (FileNotFoundException localFileNotFoundException) {
			// while (true)
			// str1 = "";
		} catch (IOException localIOException) {
			// while (true)
			// str1 = "";
		}
		return str1;
	}

	// �����ļ�
	// ��һ������Ϊ�ļ���
	// �ڶ�������Ϊ�ļ�����
	// ����������Ϊ�ļ�·����
	public boolean putfile(String paramString1, String paramString2,
			String paramString3) {
		if (paramString1.equals(""))
			paramString1 = "default";
		try {
			this.p = new Properties();
			this.p.put(paramString1, paramString2);
			FileOutputStream localFileOutputStream = this.c.openFileOutput(
					paramString3, 2);
			this.p.store(localFileOutputStream, "");
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

	//��ʾ��
	public void toast(String paramString) {
		Toast.makeText(this.c, paramString, Toast.LENGTH_SHORT).show();
	}
}