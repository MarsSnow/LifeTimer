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

	static String objectMetadataString;
	static ObjectMetadata objectMetadata;
	static String host = "bcs.duapp.com";
	static String accessKey = "2f4c285cff80949341438a10dbb774c9	";
	static String secretKey = "06db3c0343c1caa9094f75dc4c374611";
	static String bucket = "app-bbs-content";
	static File destFile = new File("test");
	static String object = "/life-time-bbs.txt";

	String birthday;
	DatePicker dt;
	global g;
	String name;
	String shoumingtemp;
	String temp;
	String savefile;
	String sex;
	String info;
	Button bbsbutton;
	Button button100;
	Button button90;
	Button button80;
	Button button70;
	Button button120;
	Button button110;
	Button button60;
	Button button50;
	EditText editname;

	private RadioGroup howlong = null;

	private RadioButton howlong100 = null;
	private RadioButton howlong90 = null;
	private RadioButton howlong80 = null;
	private RadioButton howlong70 = null;

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

		dt = (DatePicker) findViewById(R.id.datebirthday);
		dt.init(1989, 05, 10, null);
		// 新建一个global对象
		g = new global(this);
		// 保存目录名
		savefile = "pengbopluto2003ubsavetime";
		// name = g.getfile(savefile, "");
		info = g.getfile(savefile, "");

		if (this.info.equals("")) {
			this.sex = "男";
			this.name = "用户";

		}

		Button localButton1 = (Button) findViewById(R.id.buttonsave);
		Button localButton2 = (Button) findViewById(R.id.buttonback);
		// 确定按钮
		localButton1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View paramView) {

				shoumingtemp = editname.getText().toString();
				// 如果在编辑文本中有字符
				if (!(shoumingtemp.equals(""))) {

					try {

						name = editname.getText().toString();
						int intRet = Integer.parseInt(name);
						// int n=(int)name;

						if (intRet < 20) {
							B_SetActivity.this.g.toast("亲，路还长，没这么不自信吧！！");

						}
						if (intRet >= 20 & intRet < 30) {
							B_SetActivity.this.g.toast("二十弱冠！");

						}
						if (intRet >= 30 & intRet < 40) {
							B_SetActivity.this.g.toast("三十而立！");

						}
						if (intRet >= 40 & intRet < 50) {
							B_SetActivity.this.g.toast("四十不惑！");

						}
						if (intRet >= 50 & intRet < 60) {
							B_SetActivity.this.g.toast("五十而知天命！");

						}
						if (intRet >= 60 & intRet < 70) {
							B_SetActivity.this.g.toast("花甲之年！");

						}
						if (intRet >= 70 & intRet < 80) {
							B_SetActivity.this.g.toast("古稀之年！");

						}
						if (intRet >= 80 & intRet < 90) {
							B_SetActivity.this.g.toast("耄耋之年！");

						}
						if (intRet > 90 & intRet < 100) {
							B_SetActivity.this.g.toast("耄耋之年！");

						}
						if (intRet > 100 & intRet < 110) {
							B_SetActivity.this.g.toast("人瑞！");

						}
						if (intRet >= 110 & intRet < 150) {

							B_SetActivity.this.g.toast("大寿星！");

						}
						if (intRet >= 150 & intRet <= 200) {

							B_SetActivity.this.g.toast("哈哈！玩笑吧！");

						}
						if (intRet >= 200 & intRet < 500) {

							B_SetActivity.this.g.toast("这夸张了点吧！");

						}
						if (intRet == 500) {

							B_SetActivity.this.g
									.toast("五百年后，是否还会记得你的紫霞仙子或者至尊宝？");

						}
						if (intRet > 500 & intRet < 520) {

							B_SetActivity.this.g.toast("这夸张了点吧！");

						}

						if (intRet == 520) {

							B_SetActivity.this.g.toast("哈哈，碰到彩蛋了，告诉你作者名字：博~");

						}
						if (intRet > 520 & intRet < 800) {

							B_SetActivity.this.g.toast("这夸张了点吧！");

						}
						if (intRet >= 800 & intRet < 900) {

							B_SetActivity.this.g.toast("如彭祖高寿般！");

						}
						if (intRet >= 900 & intRet < 1000) {

							B_SetActivity.this.g.toast("怪物吧！");

						}
						if (intRet >= 1000 & intRet < 5201) {

							B_SetActivity.this.g.toast("成千年妖精了！！");

						}
						if (intRet == 5201) {

							B_SetActivity.this.g.toast("有些留给爱恋！");

						}
						if (intRet > 5201 & intRet < 10000) {

							B_SetActivity.this.g.toast("神仙？妖怪？谢谢！");

						}
						if (intRet >= 10000 & intRet < 201314) {

							B_SetActivity.this.g.toast("万年太久，只争朝夕。");

						}
						if (intRet == 201314) {

							B_SetActivity.this.g.toast("执子之手，与子偕老！");

						}
						if (intRet > 201314) {

							B_SetActivity.this.g.toast("不要乱试！！");

						}

					} catch (Exception e) {
						B_SetActivity.this.g.toast("小盆友，不要乱输入！！");
					}
				}

				B_SetActivity.this.birthday = (String
						.valueOf(B_SetActivity.this.dt.getYear())
						+ '-'
						+ String.valueOf(B_SetActivity.this.dt.getMonth() + 1)
						+ '-' + String.valueOf(B_SetActivity.this.dt
						.getDayOfMonth()));

				if (B_SetActivity.this.g.putfile("", B_SetActivity.this.name
						+ "\t" + B_SetActivity.this.sex + "\t"
						+ B_SetActivity.this.birthday,
						B_SetActivity.this.savefile)) {
					// SetActivity.this.g.toast("保存成功!");

					if (!birthday.equals("")) {

						// new int[3];
						int[] arrayOfInt = getdatebystr(birthday);
						dt.init(arrayOfInt[0], arrayOfInt[1], arrayOfInt[2],
								null);
						dt.init(2013, 04, 26, null);
					}

					B_SetActivity.this.finish();
				} else {
					B_SetActivity.this.g.toast("保存失败");

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
		bbsbutton.setOnClickListener(new OnClickListener() {

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
		bbsbutton = (Button) findViewById(R.id.bbsbutton);
		editname = (EditText) findViewById(R.id.editname);
		this.howlong = (RadioGroup) super.findViewById(R.id.howlong);

		this.howlong100 = (RadioButton) super.findViewById(R.id.howlong100);
		this.howlong90 = (RadioButton) super.findViewById(R.id.howlong90);
		this.howlong80 = (RadioButton) super.findViewById(R.id.howlong80);
		this.howlong70 = (RadioButton) super.findViewById(R.id.howlong70);

		this.howlong
				.setOnCheckedChangeListener(new OnCheckedChangeListenerImp());
	}

	private class OnCheckedChangeListenerImp implements OnCheckedChangeListener {

		public void onCheckedChanged(RadioGroup group, int checkedId) {
			String temp = null;
			if (B_SetActivity.this.howlong100.getId() == checkedId) {
				name = "100";
				Toast.makeText(getApplicationContext(), "人瑞！",
						Toast.LENGTH_SHORT).show();

			} else if (B_SetActivity.this.howlong90.getId() == checkedId) {
				name = "90";
				Toast.makeText(getApplicationContext(), "耄耋之年！",
						Toast.LENGTH_SHORT).show();
			} else if (B_SetActivity.this.howlong80.getId() == checkedId) {
				name = "80";
				Toast.makeText(getApplicationContext(), "耄耋之年！",
						Toast.LENGTH_SHORT).show();
			} else if (B_SetActivity.this.howlong70.getId() == checkedId) {
				name = "70";
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