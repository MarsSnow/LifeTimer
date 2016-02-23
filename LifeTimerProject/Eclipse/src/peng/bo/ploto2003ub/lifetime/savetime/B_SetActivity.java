/**���ý���**/

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

	// ��ȡ�ַ���
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

		// ǿ��ȫ��
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		// ��������ģʽ
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_BEHIND);

		setContentView(R.layout.set02);

		findView();
		setlistener();

		dt = (DatePicker) findViewById(R.id.datebirthday);
		dt.init(1989, 05, 10, null);
		// �½�һ��global����
		g = new global(this);
		// ����Ŀ¼��
		savefile = "pengbopluto2003ubsavetime";
		// name = g.getfile(savefile, "");
		info = g.getfile(savefile, "");

		if (this.info.equals("")) {
			this.sex = "��";
			this.name = "�û�";

		}

		Button localButton1 = (Button) findViewById(R.id.buttonsave);
		Button localButton2 = (Button) findViewById(R.id.buttonback);
		// ȷ����ť
		localButton1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View paramView) {

				shoumingtemp = editname.getText().toString();
				// ����ڱ༭�ı������ַ�
				if (!(shoumingtemp.equals(""))) {

					try {

						name = editname.getText().toString();
						int intRet = Integer.parseInt(name);
						// int n=(int)name;

						if (intRet < 20) {
							B_SetActivity.this.g.toast("�ף�·������û��ô�����Űɣ���");

						}
						if (intRet >= 20 & intRet < 30) {
							B_SetActivity.this.g.toast("��ʮ���ڣ�");

						}
						if (intRet >= 30 & intRet < 40) {
							B_SetActivity.this.g.toast("��ʮ������");

						}
						if (intRet >= 40 & intRet < 50) {
							B_SetActivity.this.g.toast("��ʮ����");

						}
						if (intRet >= 50 & intRet < 60) {
							B_SetActivity.this.g.toast("��ʮ��֪������");

						}
						if (intRet >= 60 & intRet < 70) {
							B_SetActivity.this.g.toast("����֮�꣡");

						}
						if (intRet >= 70 & intRet < 80) {
							B_SetActivity.this.g.toast("��ϡ֮�꣡");

						}
						if (intRet >= 80 & intRet < 90) {
							B_SetActivity.this.g.toast("���֮�꣡");

						}
						if (intRet > 90 & intRet < 100) {
							B_SetActivity.this.g.toast("���֮�꣡");

						}
						if (intRet > 100 & intRet < 110) {
							B_SetActivity.this.g.toast("����");

						}
						if (intRet >= 110 & intRet < 150) {

							B_SetActivity.this.g.toast("�����ǣ�");

						}
						if (intRet >= 150 & intRet <= 200) {

							B_SetActivity.this.g.toast("��������Ц�ɣ�");

						}
						if (intRet >= 200 & intRet < 500) {

							B_SetActivity.this.g.toast("������˵�ɣ�");

						}
						if (intRet == 500) {

							B_SetActivity.this.g
									.toast("�������Ƿ񻹻�ǵ������ϼ���ӻ������𱦣�");

						}
						if (intRet > 500 & intRet < 520) {

							B_SetActivity.this.g.toast("������˵�ɣ�");

						}

						if (intRet == 520) {

							B_SetActivity.this.g.toast("�����������ʵ��ˣ��������������֣���~");

						}
						if (intRet > 520 & intRet < 800) {

							B_SetActivity.this.g.toast("������˵�ɣ�");

						}
						if (intRet >= 800 & intRet < 900) {

							B_SetActivity.this.g.toast("��������ٰ㣡");

						}
						if (intRet >= 900 & intRet < 1000) {

							B_SetActivity.this.g.toast("����ɣ�");

						}
						if (intRet >= 1000 & intRet < 5201) {

							B_SetActivity.this.g.toast("��ǧ�������ˣ���");

						}
						if (intRet == 5201) {

							B_SetActivity.this.g.toast("��Щ����������");

						}
						if (intRet > 5201 & intRet < 10000) {

							B_SetActivity.this.g.toast("���ɣ����֣�лл��");

						}
						if (intRet >= 10000 & intRet < 201314) {

							B_SetActivity.this.g.toast("����̫�ã�ֻ����Ϧ��");

						}
						if (intRet == 201314) {

							B_SetActivity.this.g.toast("ִ��֮�֣��������ϣ�");

						}
						if (intRet > 201314) {

							B_SetActivity.this.g.toast("��Ҫ���ԣ���");

						}

					} catch (Exception e) {
						B_SetActivity.this.g.toast("С���ѣ���Ҫ�����룡��");
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
					// SetActivity.this.g.toast("����ɹ�!");

					if (!birthday.equals("")) {

						// new int[3];
						int[] arrayOfInt = getdatebystr(birthday);
						dt.init(arrayOfInt[0], arrayOfInt[1], arrayOfInt[2],
								null);
						dt.init(2013, 04, 26, null);
					}

					B_SetActivity.this.finish();
				} else {
					B_SetActivity.this.g.toast("����ʧ��");

				}

			}
		});
		// ���ذ�ť
		localButton2.setOnClickListener(new View.OnClickListener() {
			public void onClick(View paramView) {
				B_SetActivity.this.finish();
			}
		});
		//
		// ת���ַ�
		// \\ ��б��
		// \t ��� ('\u0009')
		// \n ����
		// \r �س�
		// \d ���� �ȼ��� [0-9]
		// \D ������ �ȼ��� [^0-9]
		// \s �հ׷��� [\t\n\x0B\f\r]
		// \S �ǿհ׷��� [^\t\n\x0B\f\r]
		// \w �����ַ� [a-zA-Z_0-9]
		// \W �ǵ����ַ� [^a-zA-Z_0-9]
		// \f ��ҳ��
		// \e Escape
		// \b һ�����ʵı߽�
		// \B һ���ǵ��ʵı߽�
		// \G ǰһ��ƥ��Ľ���

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
				Toast.makeText(getApplicationContext(), "����",
						Toast.LENGTH_SHORT).show();

			} else if (B_SetActivity.this.howlong90.getId() == checkedId) {
				name = "90";
				Toast.makeText(getApplicationContext(), "���֮�꣡",
						Toast.LENGTH_SHORT).show();
			} else if (B_SetActivity.this.howlong80.getId() == checkedId) {
				name = "80";
				Toast.makeText(getApplicationContext(), "���֮�꣡",
						Toast.LENGTH_SHORT).show();
			} else if (B_SetActivity.this.howlong70.getId() == checkedId) {
				name = "70";
				Toast.makeText(getApplicationContext(), "��ϡ֮�꣡",
						Toast.LENGTH_SHORT).show();

			}

		}

	}

	public void onPause() {
		super.onPause();
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
	}
}
