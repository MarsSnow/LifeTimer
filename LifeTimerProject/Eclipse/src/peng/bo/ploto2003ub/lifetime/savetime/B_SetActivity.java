package peng.bo.ploto2003ub.lifetime.savetime;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.baidu.inf.iis.bcs.BaiduBCS;
import com.baidu.inf.iis.bcs.auth.BCSCredentials;
import com.baidu.inf.iis.bcs.model.BCSClientException;
import com.baidu.inf.iis.bcs.model.BCSServiceException;
import com.baidu.inf.iis.bcs.model.DownloadObject;
import com.baidu.inf.iis.bcs.model.ObjectMetadata;
import com.baidu.inf.iis.bcs.request.GetObjectRequest;
import com.baidu.inf.iis.bcs.response.BaiduBCSResponse;

import peng.bo.ploto2003ub.lifetime.common.global;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.*;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class B_SetActivity extends Activity {

	static String m_objectMetadataString;
	static ObjectMetadata m_objectMetadata;
	static String kHost = "bcs.duapp.com";
	static String kAccessKey = "2f4c285cff80949341438a10dbb774c9	";
	static String kSecretKey = "06db3c0343c1caa9094f75dc4c374611";
	static String kBucket = "app-bbs-content";
	static File m_destFile = new File("test");
	static String kObject = "/life-time-bbs.txt";

	String m_birthday;
	DatePicker m_datePicker;
	global m_global;
	String m_name;
	String m_shoumingtemp;
	String m_temp;
	String m_savefile;
	String m_sex;
	String m_info;
	Button m_bbsbutton;
	Button m_button100;
	Button m_button90;
	Button m_button80;
	Button m_button70;
	Button m_button120;
	Button m_button110;
	Button m_button60;
	Button m_button50;
	EditText m_editname;

	private RadioGroup m_howlong = null;

	private RadioButton m_howlong100 = null;
	private RadioButton m_howlong90 = null;
	private RadioButton m_howlong80 = null;
	private RadioButton m_howlong70 = null;

	public B_SetActivity() {
	}

	// 获取字符串
	int[] getdatebystr(String s) {
		int ai[] = new int[3];
		String as[] = s.split("-");
		ai[0] = Integer.parseInt(as[0]);
		ai[1] = Integer.parseInt(as[1]);
		ai[2] = Integer.parseInt(as[2]);
		return ai;
	}

	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);

		// 强制全屏
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		// 设置竖屏模式
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_BEHIND);

		setContentView(R.layout.set02);

		findView();
		setlistener();

		m_datePicker = (DatePicker) findViewById(R.id.datebirthday);
		m_datePicker.init(1989, 06, 10, null);
		// 新建一个global对象
		m_global = new global(this);
		// 保存目录名
		m_savefile = "pengbopluto2003ubsavetime";
		// name = g.getfile(savefile, "");
		m_info = m_global.getfile(m_savefile, "");

		if (this.m_info.equals("")) {
			this.m_sex = "男";
			this.m_name = "用户";
		}

		Button localButton1 = (Button) findViewById(R.id.buttonsave);
		Button localButton2 = (Button) findViewById(R.id.buttonback);
		// 确定按钮
		localButton1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View paramView) {

				m_shoumingtemp = m_editname.getText().toString();
				// 如果在编辑文本中有字符
				if (!(m_shoumingtemp.equals(""))) {

					try {

						m_name = m_editname.getText().toString();
						int intRet = Integer.parseInt(m_name);
						// int n=(int)name;

						if (intRet < 20) {
							B_SetActivity.this.m_global.toast("亲，路还长，没这么不自信吧！！");

						}
						if (intRet >= 20 & intRet < 30) {
							B_SetActivity.this.m_global.toast("二十弱冠！");

						}
						if (intRet >= 30 & intRet < 40) {
							B_SetActivity.this.m_global.toast("三十而立！");

						}
						if (intRet >= 40 & intRet < 50) {
							B_SetActivity.this.m_global.toast("四十不惑！");

						}
						if (intRet >= 50 & intRet < 60) {
							B_SetActivity.this.m_global.toast("五十而知天命！");

						}
						if (intRet >= 60 & intRet < 70) {
							B_SetActivity.this.m_global.toast("花甲之年！");

						}
						if (intRet >= 70 & intRet < 80) {
							B_SetActivity.this.m_global.toast("古稀之年！");

						}
						if (intRet >= 80 & intRet < 90) {
							B_SetActivity.this.m_global.toast("耄耋之年！");

						}
						if (intRet > 90 & intRet < 100) {
							B_SetActivity.this.m_global.toast("耄耋之年！");

						}
						if (intRet > 100 & intRet < 110) {
							B_SetActivity.this.m_global.toast("人瑞！");

						}
						if (intRet >= 110 & intRet < 150) {

							B_SetActivity.this.m_global.toast("大寿星！");

						}
						if (intRet >= 150 & intRet <= 200) {

							B_SetActivity.this.m_global.toast("哈哈！玩笑吧！");

						}
						if (intRet >= 200 & intRet < 500) {

							B_SetActivity.this.m_global.toast("这夸张了点吧！");

						}
						if (intRet == 500) {

							B_SetActivity.this.m_global
									.toast("五百年后，是否还会记得你的紫霞仙子或者至尊宝？");

						}
						if (intRet > 500 & intRet < 520) {

							B_SetActivity.this.m_global.toast("这夸张了点吧！");

						}

						if (intRet == 520) {

							B_SetActivity.this.m_global.toast("哈哈，碰到彩蛋了，告诉你作者名字：博~");

						}
						if (intRet > 520 & intRet < 800) {

							B_SetActivity.this.m_global.toast("这夸张了点吧！");

						}
						if (intRet >= 800 & intRet < 900) {

							B_SetActivity.this.m_global.toast("如彭祖高寿般！");

						}
						if (intRet >= 900 & intRet < 1000) {

							B_SetActivity.this.m_global.toast("怪物吧！");

						}
						if (intRet >= 1000 & intRet < 5201) {

							B_SetActivity.this.m_global.toast("成千年妖精了！！");

						}
						if (intRet == 5201) {

							B_SetActivity.this.m_global.toast("有些留给爱恋！");

						}
						if (intRet > 5201 & intRet < 10000) {

							B_SetActivity.this.m_global.toast("神仙？妖怪？谢谢！");

						}
						if (intRet >= 10000 & intRet < 201314) {

							B_SetActivity.this.m_global.toast("万年太久，只争朝夕。");

						}
						if (intRet == 201314) {

							B_SetActivity.this.m_global.toast("执子之手，与子偕老！");

						}
						if (intRet > 201314) {

							B_SetActivity.this.m_global.toast("不要乱试！！");

						}

					} catch (Exception e) {
						B_SetActivity.this.m_global.toast("小盆友，不要乱输入！！");
					}
				}

				B_SetActivity.this.m_birthday = (String
						.valueOf(B_SetActivity.this.m_datePicker.getYear())
						+ '-'
						+ String.valueOf(B_SetActivity.this.m_datePicker.getMonth() + 1)
						+ '-' + String.valueOf(B_SetActivity.this.m_datePicker
						.getDayOfMonth()));

				if (B_SetActivity.this.m_global.putfile("", B_SetActivity.this.m_name
						+ "\t" + B_SetActivity.this.m_sex + "\t"
						+ B_SetActivity.this.m_birthday,
						B_SetActivity.this.m_savefile)) {
					// SetActivity.this.g.toast("保存成功!");

					if (!m_birthday.equals("")) {

						// new int[3];
						int[] arrayOfInt = getdatebystr(m_birthday);
						m_datePicker.init(arrayOfInt[0], arrayOfInt[1], arrayOfInt[2],
								null);
						m_datePicker.init(2013, 04, 26, null);
					}

					B_SetActivity.this.finish();
				} else {
					B_SetActivity.this.m_global.toast("保存失败");
				}
			}
		});
		// 返回按钮
		localButton2.setOnClickListener(new View.OnClickListener() {
			public void onClick(View paramView) {
				B_SetActivity.this.finish();
			}
		});
		//
		// 转义字符
		// \\ 反斜杠
		// \t 间隔 ('\u0009')
		// \n 换行
		// \r 回车
		// \d 数字 等价于 [0-9]
		// \D 非数字 等价于 [^0-9]
		// \s 空白符号 [\t\n\x0B\f\r]
		// \S 非空白符号 [^\t\n\x0B\f\r]
		// \w 单独字符 [a-zA-Z_0-9]
		// \W 非单独字符 [^a-zA-Z_0-9]
		// \f 换页符
		// \e Escape
		// \b 一个单词的边界
		// \B 一个非单词的边界
		// \G 前一个匹配的结束

	}

	private void setlistener() {
		m_bbsbutton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent();
				intent.putExtra("ObjectName", "/life-time-bbs.txt");
				intent.setClass(getApplicationContext(), E_BBSActivity.class);
				startActivity(intent);
			}
		});
	}

	private void findView() {
		m_bbsbutton = (Button) findViewById(R.id.bbsbutton);
		m_editname = (EditText) findViewById(R.id.editname);
		this.m_howlong = (RadioGroup) super.findViewById(R.id.howlong);

		this.m_howlong100 = (RadioButton) super.findViewById(R.id.howlong100);
		this.m_howlong90 = (RadioButton) super.findViewById(R.id.howlong90);
		this.m_howlong80 = (RadioButton) super.findViewById(R.id.howlong80);
		this.m_howlong70 = (RadioButton) super.findViewById(R.id.howlong70);

		this.m_howlong
				.setOnCheckedChangeListener(new OnCheckedChangeListenerImp());
	}

	private class OnCheckedChangeListenerImp implements OnCheckedChangeListener {

		public void onCheckedChanged(RadioGroup group, int checkedId) {
			String temp = null;
			if (B_SetActivity.this.m_howlong100.getId() == checkedId) {
				m_name = "100";
				Toast.makeText(getApplicationContext(), "人瑞！",
						Toast.LENGTH_SHORT).show();

			} else if (B_SetActivity.this.m_howlong90.getId() == checkedId) {
				m_name = "90";
				Toast.makeText(getApplicationContext(), "耄耋之年！",
						Toast.LENGTH_SHORT).show();
			} else if (B_SetActivity.this.m_howlong80.getId() == checkedId) {
				m_name = "80";
				Toast.makeText(getApplicationContext(), "耄耋之年！",
						Toast.LENGTH_SHORT).show();
			} else if (B_SetActivity.this.m_howlong70.getId() == checkedId) {
				m_name = "70";
				Toast.makeText(getApplicationContext(), "古稀之年！",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	public void onPause() {
		super.onPause();
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
	}
}