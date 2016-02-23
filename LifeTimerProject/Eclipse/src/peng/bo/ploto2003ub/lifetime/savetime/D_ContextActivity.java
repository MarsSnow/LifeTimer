/**�ı����ݽ���**/

package peng.bo.ploto2003ub.lifetime.savetime;
//
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PowerManager;
import android.text.Html;
import android.util.Log;
import android.view.*;
import android.widget.*;

import java.io.InputStream;
import java.util.*;

import org.apache.http.util.ByteArrayBuffer;
import org.apache.http.util.EncodingUtils;

import cn.waps.AdView;
import cn.waps.AppConnect;
import cn.waps.MiniAdView;

public class D_ContextActivity extends Activity {

	
	TextView NewCharacterIntroduction01=null;

	InputStream in = null;
	String str = "";
	int mygonglue03text;
	protected static final int MENU_ABOUT = 1;
	protected static final int MENU_SETTING = 3;
	LinearLayout adLayout;
	android.widget.LinearLayout.LayoutParams adLp;

	boolean isShowAdView;
	PowerManager powerManager1;
	android.os.PowerManager.WakeLock wakeLock1;

	// exitTime���ڰ����ؼ��˳�ʱ�����ε������ʱ���ж�
	private long exitTime = 0;
	
	public D_ContextActivity() {
		powerManager1 = null;
		wakeLock1 = null;
		adLp = new android.widget.LinearLayout.LayoutParams(-1, -2);
		isShowAdView = false;
		
	}
	
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);

		// ǿ��ȫ��
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		
		// ��������ģʽ
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_BEHIND);
		setContentView(R.layout.zz_new_character_introductionlayout01v6_7_6);
	
		//���������汳����ɫ 
		AppConnect.getInstance(this).setAdBackColor(0x000000); 
		//����������������ɫ 
		AppConnect.getInstance(this).setAdForeColor(Color.YELLOW); 
		//��δ��������������ɫ����Ĭ��Ϊ�ڵװ��� 
		LinearLayout miniLayout =(LinearLayout)findViewById(R.id.miniAdLinearLayout); 
		new MiniAdView(this, miniLayout).DisplayAd(60); //Ĭ��10���л�һ�ι��
		
//		LinearLayout container =(LinearLayout)findViewById(R.id.AdLinearLayout); 
//		new AdView(this,container).DisplayAd();
		
		powerManager1 = (PowerManager) getSystemService("power");
		wakeLock1 = powerManager1.newWakeLock(26, "My Lock");

		Intent intent = getIntent();
		mygonglue03text = intent.getExtras().getInt("mygonglue03text");
		
		NewCharacterIntroduction01 = (TextView) findViewById(R.id.zz_NewCharacterIntroduction01v6_7_6);
	
		in = getResources().openRawResource(mygonglue03text);
		Mygonlue02();
		NewCharacterIntroduction01.setText(str);
		


	}
	
	//�ļ���IO����
		private void Mygonlue02() {

			try {
				

				ByteArrayBuffer bb = new ByteArrayBuffer(500);
				
				int current = 0;

			
				//String str = null;//?
				

				//û����ͻ�һֱ��bb�д�����
				while ((current = in.read()) != -1) {
					bb.append(current);
				}
				
				str = EncodingUtils.getString(bb.toByteArray(), "UTF-8");
				in.close();


			} catch (Exception e) {
			}
		}
		
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, 1, 0, "���ƽ���").setIcon(android.R.drawable.ic_menu_help);
		menu.add(0, 2, 0, "���ֲ�ѯ").setIcon(android.R.drawable.ic_menu_search);
		return super.onCreateOptionsMenu(menu);
	}


	public boolean onOptionsItemSelected(MenuItem menuitem) {
		switch (menuitem.getItemId()) {
		//���ƽ���
		case 1: // 
			OpenSuggestDialog();
			break;
		//���ֲ�ѯ
		case 2: // 
			getpoint();
			break;
		}
		while (true)
			return super.onOptionsItemSelected(menuitem);
	}
	

	
	// ��ѡ��˵������ƽ��飩�������Ի���
	private void OpenSuggestDialog() {
		(new android.app.AlertDialog.Builder(this))

				.setIcon(android.R.drawable.ic_menu_help)
				// ����ͼ��
				.setTitle("����")
				// �����ı�
				.setMessage(
						"�����ߣ�" + "\n" + "pluto2003ub" + "\n" + "\n" + "��ϵ���䣺"
								+ "\n" + "851104757@qq.com" + "\n"
								+ "2278048953@qq.com").show();

	}
	
	
	// ��ȡ����ҳ�棨���ǽ��
	private void getpoint() {
		
		AppConnect.getInstance(this).showOffers(this);
	}

	protected void onPause() {

			wakeLock1.release();
		super.onPause();

	}

	protected void onResume() {

			wakeLock1.acquire();
		super.onResume();

	}
	
	
	

	
}
