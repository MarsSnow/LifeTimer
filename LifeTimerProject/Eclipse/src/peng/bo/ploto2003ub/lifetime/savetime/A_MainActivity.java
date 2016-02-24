/**主界面**/
package peng.bo.ploto2003ub.lifetime.savetime;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
//import android.content.DialogInterface;
//import android.content.DialogInterface.OnClickListener;
import android.content.Intent.ShortcutIconResource;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.util.DisplayMetrics;
//import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
//import android.view.ViewGroup;
import android.view.Window;
//import android.view.View.OnClickListener;
//import android.view.View.OnClickListener;
//import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
//import android.widget.ImageView;
import android.widget.LinearLayout;
//import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.baidu.inf.iis.bcs.BaiduBCS;
import com.baidu.inf.iis.bcs.auth.BCSCredentials;
import com.baidu.inf.iis.bcs.model.BCSClientException;
import com.baidu.inf.iis.bcs.model.BCSServiceException;
import com.baidu.inf.iis.bcs.model.ObjectMetadata;
import com.baidu.inf.iis.bcs.request.PutObjectRequest;
import com.baidu.inf.iis.bcs.response.BaiduBCSResponse;

import cn.waps.AppConnect;
import cn.waps.MiniAdView;

import peng.bo.ploto2003ub.lifetime.common.global;

public class A_MainActivity extends Activity {
	
	/** BBS参数 **/
	static String kHost = "bcs.duapp.com";
	static String kAccessKey = "2f4c285cff80949341438a10dbb774c9	";
	static String kSecretKey = "06db3c0343c1caa9094f75dc4c374611";
	static String kBucket = "pluto2003ub";
	static String kObject = "/pengbo-first-object";

	Context m_context;//句柄
	
	// 快捷方式判断存储
	SharedPreferences m_sharedPreferences;

	boolean m_isFirst;//是否是第一次启动

	String m_birthday;
	String m_birthday_temp001;
	global m_global;
	Handler m_handler;
	long m_lifetime;
	long m_lifetime02;

	String m_canLiveTime = "";
	Runnable m_runnable;
	String m_savefile;
	String m_sex;
	int m_sheight;
	int m_showtype;
	TextView m_showview;
	TextView m_showviewp;
	int m_swidth;

	Button m_note_text001;
	long m_tempjudge001;
	long m_tempjudge002;
	long m_costtemp002;
	long m_howLong = 70;
	// 屏幕常亮管理
	android.os.PowerManager.WakeLock m_wakeLock = null;
	ImageButton m_button001;
	/** 起点按钮 **/
	Button m_qidian;
	/** 寿命 **/
	TextView m_shouming;
	/** 秒 **/
	TextView m_textview001;
	TextView m_textview002;
	/** 时 **/
	TextView m_textview003;
	TextView m_textview004;
	/** 天 **/
	TextView m_textview005;
	TextView m_textview006;
	/** 周 **/
	TextView m_textview007;
	TextView m_textview008;
	/** 月 **/
	TextView m_textview009;
	TextView m_textview010;
	/** 年 **/
	TextView m_textview011;
	TextView m_textview012;
		
	//生命周期
	public void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);//调用父类的OnCreate方法
		
		// 声明使用自定义标题
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		// 强制全屏
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		// 设置竖屏模式
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_BEHIND);
		
		//设置布局文件
		setContentView(R.layout.main02);
		//使用自定义标题
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar);// 自定义布局赋值

		// 启动广告平台SDK
		new AdThread().StartMyAd();
		
//		Toast.makeText(this, "您可在手机设置里面往桌面添加倒计时小控件！", Toast.LENGTH_SHORT)
//				.show();

		Toast.makeText(this, CommonLang.kSetWidgetInfo, Toast.LENGTH_SHORT).show();
		
		// 保持屏幕始终常亮的情况
		acquireWakeLock();
		global localglobal = new global(this);
		this.m_global = localglobal;

		this.m_showtype = 0;
		
		// 获取手机屏幕
		DisplayMetrics localDisplayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
		this.m_swidth = localDisplayMetrics.widthPixels;
		this.m_sheight = localDisplayMetrics.heightPixels;
		FindView();

		// 产生随机数，随机取出的提示
		String str[] = CommonLang.str;
		int randomIndex = Math.abs(new Random().nextInt()) % str.length;
		m_note_text001.setText(str[randomIndex]);
		SetListener();// 名言监听器

		this.m_handler = new Handler();

		//线程
		this.m_runnable = new Runnable() {
			public void run() {

				m_lifetime -= 1L;		// 每秒减1
				m_lifetime02 += 1L;		// 每秒减1
				A_MainActivity.this.showtime80(A_MainActivity.this.m_lifetime, m_lifetime02);
				A_MainActivity.this.m_handler.postDelayed(this, 1000L);
			}
		};
		this.m_handler.postDelayed(this.m_runnable, 1000L);

		whether_add_shortcut();//判断是否添加快捷方式
		
		//初始化广告相关
		showminiad();//显示迷你广告
		AppConnect.getInstance(m_context).initPopAd(getApplicationContext());
		String value = AppConnect.getInstance(m_context).getConfig("pop_ad_life_time", "1");
		
		if (value.equals("0")) {
			AppConnect.getInstance(m_context).showPopAd(m_context);
		} else {

		}
	}

	//判断是否添加快捷方式
	private void whether_add_shortcut() {
		// 添加快捷方式
		// 获得SharedPreference
		m_sharedPreferences = this.getSharedPreferences("shortcutdata", MODE_WORLD_READABLE);
		// 从SharedPreference取值，若键为“first”的值不存在，则默认为true
		m_isFirst = m_sharedPreferences.getBoolean("first", true);
		// 第一次运行，使用默认的true
		if (m_isFirst) {

			// 创建一个SharedPreference的编辑器
			Editor editor = m_sharedPreferences.edit();
			// 往编辑器中赋值，赋为false，第二次运行就不会在到这里来
			editor.putBoolean("first", false);
			// 编辑器数据提交
			editor.commit();
			// 增加快捷方式
			addShortcut();
			return;
		} else {
			// Toast.makeText(getApplicationContext(), "第二次！", 0).show();
		}
	}

	//显示迷你广告
	private void showminiad() {
		// 设置迷你广告背景颜色
		AppConnect.getInstance(this).setAdBackColor(Color.GREEN);
		// 设置迷你广告广告语颜色
		AppConnect.getInstance(this).setAdForeColor(Color.YELLOW);
		// 若未设置以上两个颜色，则默认为黑底白字
		LinearLayout miniLayout = (LinearLayout) findViewById(R.id.miniAdLinearLayout);
		new MiniAdView(this, miniLayout).DisplayAd(10);
	}

	/**
	 *  * 为程序创建桌面快捷方式  
	 */
	private void addShortcut() {
		Intent shortcut = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");

		// 快捷方式的名称 
		shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, getString(R.string.app_name));
		shortcut.putExtra("duplicate", false); // 不允许重复创建 

		// 指定当前的Activity为快捷方式启动的对象: 如 com.everest.video.VideoPlayer 
		// 注意: ComponentName的第二个参数必须加上点号(.)，否则快捷方式无法启动相应程序 
		ComponentName comp = new ComponentName(this.getPackageName(), "." + this.getLocalClassName());
		shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, new Intent(Intent.ACTION_MAIN).setComponent(comp));

		// 快捷方式的图标 
		ShortcutIconResource iconRes = Intent.ShortcutIconResource.fromContext(this, R.drawable.icon);
		shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, iconRes);

		sendBroadcast(shortcut);//向系统发送广告，添加快捷方式
	}

	//找到相应的控件
	private void FindView() {

		m_button001 = (ImageButton) findViewById(R.id.button001);
		m_note_text001 = (Button) findViewById(R.id.note_text001);
		m_qidian = (Button) findViewById(R.id.qidian);
		m_shouming = (TextView) findViewById(R.id.shouming);
		m_textview001 = (TextView) findViewById(R.id.t1);
		m_textview002 = (TextView) findViewById(R.id.t2);
		m_textview003 = (TextView) findViewById(R.id.t3);
		m_textview004 = (TextView) findViewById(R.id.t4);
		m_textview005 = (TextView) findViewById(R.id.t5);
		m_textview006 = (TextView) findViewById(R.id.t6);
		m_textview007 = (TextView) findViewById(R.id.t7);
		m_textview008 = (TextView) findViewById(R.id.t8);
		m_textview009 = (TextView) findViewById(R.id.t9);
		m_textview010 = (TextView) findViewById(R.id.t10);
		m_textview011 = (TextView) findViewById(R.id.t11);
		m_textview012 = (TextView) findViewById(R.id.t12);
	}

	//添加监听器
	private void SetListener() {

		//分析按钮
		ImageButton ShareButton = (ImageButton) findViewById(R.id.share_button);
		
		ShareButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_SEND); // 启动分享发送的属性
				intent.setType("text/plain"); // 分享发送的数据类型
				intent.putExtra(Intent.EXTRA_SUBJECT, "subject"); // 分享的主题
				intent.putExtra(Intent.EXTRA_TEXT,CommonLang.kShareInfo); // 分享的内容
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(Intent.createChooser(intent, "分享"));// 目标应用选择对话框的标题
			}
		});
		//更多信息
		m_button001.setOnClickListener(new View.OnClickListener() {
			public void onClick(View paramView) {
				// openOptionsMenu();
				Intent intent = new Intent();
				intent.setClass(getApplicationContext(), C_More_info_list.class);
				startActivity(intent);
			}
		});
		//名言警句button
		m_note_text001.setOnClickListener(new View.OnClickListener() {
			public void onClick(View paramView) {
				String str[] = CommonLang.str;
				m_note_text001.setText(str[Math.abs(new Random().nextInt())
						% str.length]);
			}
		});
		//人生起点按钮
		m_qidian.setOnClickListener(new View.OnClickListener() {
			public void onClick(View paramView) {
				Intent localIntent = new Intent();
				localIntent.setClass(A_MainActivity.this, B_SetActivity.class);
				A_MainActivity.this.startActivity(localIntent);
				A_MainActivity.this.overridePendingTransition(2130968576,
						2130968577);
			}
		});

	}
	//下午 09:56 2014/12/27，1年后再来看代码，好累。。
	private void JudgeCanLiveTime() {
		//如果是第一次
		if (m_canLiveTime.equals("first")) {

			m_global.putfile("", "70" + "\t" + "男" + "\t" + "1989-06-10  00:00:00",
					"pengbopluto2003ubsavetime");

			m_canLiveTime = "70";

			m_canLiveTime = m_global.getfile("pengbopluto2003ubsavetime", "default");
			String as[] = m_canLiveTime.split("\t");
			m_canLiveTime = as[0];
			m_sex = as[1];
			m_birthday = as[2];

			if (m_birthday.equals("")) {
				m_birthday = "1989-06-10 00:00:00";
			} else {
				// 增加小时，分钟，秒
				m_birthday = (new StringBuilder(String.valueOf(m_birthday)))
						.append("00:00:00").toString();
			}
			// 获取基准时间
			m_lifetime = getRemainTime(m_birthday);
			m_lifetime02 = getCostTime(m_birthday);
			showtime80(m_lifetime, m_lifetime02);
			
		} else {//不是第一次

			// 从自己定义的global类中获取文件，路径为savetime，文件名为default
			m_canLiveTime = m_global.getfile("pengbopluto2003ubsavetime", "default");
			String as[] = m_canLiveTime.split("\t");
			m_canLiveTime = as[0];
			m_sex = as[1];
			m_birthday = as[2];

			m_birthday_temp001 = m_birthday;

			if (m_birthday.equals("")) {
				m_birthday = "1989-06-10 00:00:00";
			} else {
				// 增加小时，分钟，秒
				m_birthday = (new StringBuilder(String.valueOf(m_birthday)))
						.append(" 00:00:00").toString();
			}
			if (m_canLiveTime.equals("null")) {
				m_canLiveTime = "80";
			}

			// 获取剩余时间
			m_lifetime = getRemainTime(m_birthday);
			m_lifetime02 = getCostTime(m_birthday);
			showtime80(m_lifetime, m_lifetime02);
			m_qidian.setText(m_birthday_temp001);
			m_shouming.setText(m_canLiveTime);

			try {

				m_tempjudge001 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.parse((new StringBuilder(String.valueOf(m_birthday)))
								.append(" 00:00:00").toString()).getTime();
				m_tempjudge002 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.parse("1960-00-00 00:00:00").getTime();

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// 获取剩余时间
	long getRemainTime(String paramString) {
		long temp1 = 0L;
		try {
			// 把字符串解析成特定的日期格式（转换出生日期）
			temp1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(paramString).getTime();
			// 2522880000秒=80岁 一个人，一生只能活25亿秒
			// long temp2 = 2522880000L + temp1 / 1000L;
			if (m_canLiveTime.equals("120")) {
				m_howLong = 120;
			}
			if (m_canLiveTime.equals("110")) {
				m_howLong = 110;
			}
			if (m_canLiveTime.equals("100")) {
				m_howLong = 100;
			}
			if (m_canLiveTime.equals("90")) {
				m_howLong = 90;
			}
			if (m_canLiveTime.equals("80")) {
				m_howLong = 80;
			}
			if (m_canLiveTime.equals("70")) {
				m_howLong = 70;
			}
			if (m_canLiveTime.equals("60")) {
				m_howLong = 60;
			}
			if (m_canLiveTime.equals("50")) {
				m_howLong = 50;
			}
			//若输入年龄为空
			if (!(m_canLiveTime.equals(""))) {
				try {
					m_howLong = Integer.parseInt(m_canLiveTime);
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(), "小盆友，不要乱输入！！", 1)
							.show();
					m_canLiveTime = "70";
				}
			}

			long totalTime = 31556926 * m_howLong + temp1 / 1000L;
			// 365 31556926
			// 120
			// 110
			// 100
			// 90
			// 80 2524554080
			// 70
			// 60
			// 50

			// getTime返回的是毫秒，除以1000变成秒
			long costTime = new Date().getTime() / 1000L;
			// 剩余时间 = 总时间 - 消耗的时间
			// 总时间 = 80岁总秒数 + 出生基准偏移量（从0000年00月00日开始到出生的秒）
			// 消耗时间 = 现在时间（从0000年00月00日开始到现在）
			temp1 = totalTime - costTime;

			return temp1;
		} catch (ParseException localParseException) {

		}
		return temp1;
	}

	// 获得消耗时间
	long getCostTime(String paramString) {
		long temp1 = 0L;
		long temp02 = 0L;
		try {
			// 把字符串解析成特定的日期格式（转换出生日期）
			temp1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(
					paramString).getTime();
			// 2522880000秒=80岁 一个人，一生只能活25亿秒
			// long temp2 = 2522880000L + temp1 / 1000L;
			// getTime返回的是毫秒，除以1000变成秒

			// 当前时间
			long temp3 = new Date().getTime() / 1000L;
			// 剩余时间 = 总时间 - 消耗的时间
			// 总时间 = 80岁总秒数 + 出生基准偏移量（从0000年00月00日开始到出生的秒）
			// 消耗时间 = 现在时间（从0000年00月00日开始到现在）
			temp02 = temp3 - temp1 / 1000L;
			m_costtemp002 = temp02;
			return temp02;
		} catch (ParseException localParseException) {

		}
		return temp02;
	}

	// 显示时间的方法
	void showtime80(long remainTime, long costTime) {

		//textview001.setText(time - 2680000 + "秒");
		m_textview001.setText(costTime + "秒");
		// showviewp.setText("秒");

		m_textview002.setText((remainTime) + "秒");
		// showviewp.setText("秒");

		m_textview003.setText((costTime / 3600L) + "小时");

		m_textview004.setText((remainTime / 3600L) + "小时");
		// showviewp.setText("小时");

		m_textview005.setText((costTime / 0x15180L)  + "天");
		// showviewp.setText("天");

		m_textview006.setText((remainTime / 0x15180L) + "天");
		// showviewp.setText("天");

		
		/*
		 * 0x93a80L
		 * 
		 *4.35X24*60*60=375840
		 *
		 ***/
		
		m_textview007.setText((costTime / 0x93a80L)  + "周");
		// showviewp.setText("星期");

		m_textview008.setText((remainTime / 0x93a80L) + "周");
		// showviewp.setText("星期");

		/**
		 * 30.5天
		 * 30.5*24*60*60=2635200L
		 * 30.45*24*60*60=2630880L
		 * **/
		
		m_textview009.setText((costTime / 2630880L)  + "月");
		// showviewp.setText("月");

		m_textview010.setText(((remainTime / 2630880L)+1) + "月");
		// showviewp.setText("月");

		m_textview011.setText((costTime / 31556926L) + "年");
		// showviewp.setText("年");
		// textview011.setText(String.valueOf(time / 31536000L) + "年");
		// 365.24
		// 365天5小时48分46秒
		// 18000+2880+46
		m_textview012.setText(((remainTime / 31556926L) + 1) + "年");
		//textview012.setText(((time2 / 31536000L) + 1) + "年");
		// showviewp.setText("年");

		// 365天5小时48分46秒 = 31556926
		// 365T 31536000L
		// 18000+2880+46 = 20926
	}

	//屏幕常亮
	private void acquireWakeLock() {
		if (m_wakeLock == null) {

			PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
			m_wakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK, this
					.getClass().getCanonicalName());
			m_wakeLock.acquire();
		}
	}

	//取消屏幕常亮
	private void releaseWakeLock() {
		if (m_wakeLock != null && m_wakeLock.isHeld()) {
			m_wakeLock.release();
			m_wakeLock = null;
		}

	}

	// 返回按钮的监听
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			// AppConnect.getInstance(this).showPopAd(this);//
			// 以下方法将用于释放SDK占用的系统资源

			// 调用退屏广告
			QuitPopAd.getInstance().show(this);

			AppConnect.getInstance(this).finalize();

		}
		return false;
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
								+ "\n" + "851104757@qq.com" + "\n"
								+ "2278048953@qq.com").show();

	}

	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, 1, 0, "关于").setIcon(android.R.drawable.ic_menu_help);
		menu.add(0, 2, 0, "试试我的其他应用")
				.setIcon(android.R.drawable.ic_menu_search);
		return super.onCreateOptionsMenu(menu);
	}

	public boolean onOptionsItemSelected(MenuItem menuitem) {
		
		switch (menuitem.getItemId()) {
		// 改善建议
		case 1: //
			OpenSuggestDialog();

			break;
		//
		case 2: //
			AppConnect.getInstance(this).showMore(this);
			break;
		}
		while (true)
			return super.onOptionsItemSelected(menuitem);
	}

	// 获取积分页面（广告墙）
	private void getpoint() {

		AppConnect.getInstance(this).showOffers(this);

	}

	// 新建一个线程，用于广告的加载
	private class AdThread extends Thread {
		// 该类默认有一个Thread构造方法
		// 线程运行方法
		// @Override
		public void run() {
			// // 初始化统计器
			AppConnect.getInstance(getApplicationContext());
			// AppConnect.getInstance(getApplicationContext()).initPopAd(getApplicationContext());
			//
			// AppConnect.getInstance(context).initPopAd(getApplicationContext());
			// String value = AppConnect.getInstance(context).getConfig(
			// "pop_ad_life_time", "1");
			// if (value.equals("0")) {
			// AppConnect.getInstance(context).showPopAd(context);
			// } else {
			//
			// }

			//

			//
			// AppConnect.getInstance(getApplicationContext()).initPopAd(getApplicationContext());
			// String value =
			// AppConnect.getInstance(getApplicationContext()).getConfig(
			// "pop_ad_life_time", "1");
			// if (value.equals("0")) {
			// AppConnect.getInstance(getApplicationContext()).showPopAd(getApplicationContext());
			// } else {
			//
			// }
		}

		public void StartMyAd() {
			// 自己启动自己的线程
			this.start();
		}
	}

	// 刷新Activity使用
	// 从不可操控返回后（重新开始）
	public void onResume() {
		super.onResume();
		// savefile = "pengbopluto2003ubsavetime";`
		acquireWakeLock();
		m_canLiveTime = m_global.getfile("pengbopluto2003ubsavetime", "default");

		// name = "";
		if (m_canLiveTime != null) {
			
			JudgeCanLiveTime();

			// 名言，50年，要记得照顾老人
			if (m_costtemp002 >= 31536000 * 50) {

				String str002[] = CommonLang.str002;
				m_note_text001.setText(str002[Math.abs(new Random().nextInt())% str002.length]);
			}
		} else {
			m_canLiveTime = "first";
			JudgeCanLiveTime();
		}
	}
}