/**文本内容界面**/

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

	// exitTime用于按返回键退出时的两次点击存在时间判断
	private long exitTime = 0;
	
	public D_ContextActivity() {
		powerManager1 = null;
		wakeLock1 = null;
		adLp = new android.widget.LinearLayout.LayoutParams(-1, -2);
		isShowAdView = false;
		
	}
	
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);

		// 强制全屏
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		
		// 设置竖屏模式
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_BEHIND);
		setContentView(R.layout.zz_new_character_introductionlayout01v6_7_6);
	
		//设置迷你广告背景颜色 
		AppConnect.getInstance(this).setAdBackColor(0x000000); 
		//设置迷你广告广告语颜色 
		AppConnect.getInstance(this).setAdForeColor(Color.YELLOW); 
		//若未设置以上两个颜色，则默认为黑底白字 
		LinearLayout miniLayout =(LinearLayout)findViewById(R.id.miniAdLinearLayout); 
		new MiniAdView(this, miniLayout).DisplayAd(60); //默认10秒切换一次广告
		
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
	
	//文件的IO操作
		private void Mygonlue02() {

			try {
				ByteArrayBuffer bb = new ByteArrayBuffer(500);
				
				int current = 0;
			
				//String str = null;//?
				
				//没读完就会一直往bb中传数据
				while ((current = in.read()) != -1) {
					bb.append(current);
				}
				
				str = EncodingUtils.getString(bb.toByteArray(), "UTF-8");
				in.close();


			} catch (Exception e) {
			}
		}
		
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, 1, 0, "改善建议").setIcon(android.R.drawable.ic_menu_help);
		menu.add(0, 2, 0, "积分查询").setIcon(android.R.drawable.ic_menu_search);
		return super.onCreateOptionsMenu(menu);
	}


	public boolean onOptionsItemSelected(MenuItem menuitem) {
		switch (menuitem.getItemId()) {
		//改善建议
		case 1: // 
			OpenSuggestDialog();
			break;
		//积分查询
		case 2: // 
			getpoint();
			break;
		}
		while (true)
			return super.onOptionsItemSelected(menuitem);
	}
	
	// 打开选项菜单（改善建议）（警报对话框）
	private void OpenSuggestDialog() {
		(new android.app.AlertDialog.Builder(this))

				.setIcon(android.R.drawable.ic_menu_help)
				// 设置图标
				.setTitle("关于")
				// 设置文本
				.setMessage(
						"开发者：" + "\n" + "pluto2003ub" + "\n" + "\n" + "联系邮箱："
								+ "\n" + "851104757@qq.com").show();
	}
	
	// 获取积分页面（广告墙）
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
