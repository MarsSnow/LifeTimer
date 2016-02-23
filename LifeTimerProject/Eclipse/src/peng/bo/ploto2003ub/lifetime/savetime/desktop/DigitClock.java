/**����С�ؼ�**/
package peng.bo.ploto2003ub.lifetime.savetime.desktop;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import peng.bo.ploto2003ub.lifetime.common.global;
import peng.bo.ploto2003ub.lifetime.savetime.R;
import peng.bo.ploto2003ub.lifetime.savetime.A_MainActivity;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

public class DigitClock extends AppWidgetProvider

{
	
	long tempjudge001;
	long tempjudge002;
	long costtemp002;
	long HowLong = 70;

	String birthday;
	String birthday_temp001;
	String sex;
	String name = "";
	RemoteViews views;
	long lifetime;
	long lifetime02;

	Runnable runnable;
	global g;
	private Timer timer = new Timer();
	private AppWidgetManager appWidgetManager;
	private Context context;

	// ����ʾСʱ�����ӡ����ӵ�ImageView���������
	private int[] digitViews = new int[] { R.id.desktopt1, R.id.desktopt2,
			R.id.desktopt3, R.id.desktopt4, R.id.desktopt5, R.id.desktopt6,
			R.id.desktopt7, R.id.desktopt8, R.id.desktopt9, R.id.desktopt10,
			R.id.desktopt11, R.id.desktopt12 };

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		this.appWidgetManager = appWidgetManager;
		this.context = context;
//		// �����ʱ��
		timer = new Timer();
		// ���������Ե���
		timer.schedule(new TimerTask() {
			public void run() {
				// ���Ϳ���Ϣ��֪ͨ�������
				handler.sendEmptyMessage(0x123);
			}
		}, 0, 1000);



	}

	// ��ʾʱ��ķ���
	void showtime80(long time2, long time) {

		views.setTextViewText(digitViews[0], time - 2680000 + "��");

		// showviewp.setText("��");

		views.setTextViewText(digitViews[1], (time2) + "��");

		// showviewp.setText("��");

		views.setTextViewText(digitViews[2], (time / 3600L) - 744 + "Сʱ");
		

		
		views.setTextViewText(digitViews[3], (time2 / 3600L) + "Сʱ");

		// showviewp.setText("Сʱ");

		views.setTextViewText(digitViews[4], (time / 0x15180L) - 31 + "��");

		// showviewp.setText("��");

		views.setTextViewText(digitViews[5], (time2 / 0x15180L) + "��");

		// showviewp.setText("��");

		views.setTextViewText(digitViews[6], (time / 0x93a80L) - 4 + "��");

		// showviewp.setText("����");

		views.setTextViewText(digitViews[7], (time2 / 0x93a80L) + "��");

		// showviewp.setText("����");
		
		views.setTextViewText(digitViews[8], (time / 2635200L) - 1 + "��");

		// showviewp.setText("��");

		views.setTextViewText(digitViews[9],(time2 / 2635200L) + "��");

		// showviewp.setText("��");

		views.setTextViewText(digitViews[10], (time / 31536000L) + "��");

		// showviewp.setText("��");
		// textview011.setText(String.valueOf(time / 31536000L) + "��");
		// 365.24
		// 365��5Сʱ48��46��
		// 18000+2880+46
		
		views.setTextViewText(digitViews[11],((time2 / 31536000L) + 1) + "��");

		// showviewp.setText("��");

		// 365��5Сʱ48��46�� = 31556926
		// 365T 31536000L
		// 18000+2880+46 = 20926
	}

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.what == 0x123) {

				
				
				views = new RemoteViews(context.getPackageName(),
						R.layout.desktopmain);
				global localglobal = new global(context);
				g = localglobal;
				name = g.getfile("pengbopluto2003ubsavetime", "default");
				getnameinfo();
				
				lifetime -= 1L;// ÿ���1
				lifetime02 += 1L;// ÿ���1
	
				showtime80(
						lifetime, lifetime02);
//				// ����SimpleDateFormat����
//				SimpleDateFormat df = new SimpleDateFormat("HHmmss");// 170533
//				// ����ǰʱ���ʽ����HHmmss����ʽ
//				String timeStr = df.format(new Date());
//				for (int i = 0; i < timeStr.length(); i++) {
//					// ����i�������ַ�ת��Ϊ��Ӧ������
//					int num = timeStr.charAt(i) - 48;// ΪʲôҪ��ȥ48
//					// ����i��ͼƬ����Ϊ��Ӧ��Һ������ͼƬ
//
//					views.setTextViewText(digitViews[i], timeStr.charAt(i) + "");
//				}
				// ��AppWidgetProvider����ʵ����װ��ComponentName����
				ComponentName componentName = new ComponentName(context,
						DigitClock.class);
				// ����AppWidgetManager��remoteViews��ӵ�ComponentName��
				appWidgetManager.updateAppWidget(componentName, views);
			}
			super.handleMessage(msg);
		}

	
	};
	
	private void getnameinfo() {
		// ���Լ������global���л�ȡ�ļ���·��Ϊsavetime���ļ���Ϊdefault
		name = g.getfile("pengbopluto2003ubsavetime", "default");
		String as[] = name.split("\t");
		name = as[0];
		sex = as[1];
		birthday = as[2];

		birthday_temp001 = birthday;

		if (birthday.equals("")) {
			birthday = "1989-06-10 00:00:00";
		} else {
			// ����Сʱ�����ӣ���
			birthday = (new StringBuilder(String.valueOf(birthday)))
					.append(" 00:00:00").toString();
		}
		if (name.equals("null")) {
			name = "80";
		}

		// ��ȡʣ��ʱ��
		lifetime = getbasetime(birthday);
		lifetime02 = getCostTime(birthday);
		showtime80(lifetime, lifetime02);
		

		try {

			tempjudge001 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.parse((new StringBuilder(String.valueOf(birthday)))
							.append(" 00:00:00").toString()).getTime();
			tempjudge002 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.parse("1960-00-00 00:00:00").getTime();

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	// �������ʱ��
	long getCostTime(String paramString) {
		long temp1 = 0L;
		long temp02 = 0L;
		try {
			// ���ַ����������ض������ڸ�ʽ��ת���������ڣ�
			temp1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(
					paramString).getTime();
			// 2522880000��=80�� һ���ˣ�һ��ֻ�ܻ�25����
			// long temp2 = 2522880000L + temp1 / 1000L;
			// getTime���ص��Ǻ��룬����1000�����

			// ��ǰʱ��
			long temp3 = new Date().getTime() / 1000L;
			// ʣ��ʱ�� = ��ʱ�� - ���ĵ�ʱ��
			// ��ʱ�� = 80�������� + ������׼ƫ��������0000��00��00�տ�ʼ���������룩
			// ����ʱ�� = ����ʱ�䣨��0000��00��00�տ�ʼ�����ڣ�
			temp02 = temp3 - temp1 / 1000L;
			costtemp002 = temp02;
			return temp02;
		} catch (ParseException localParseException) {

		}
		return temp02;
	}
	
	// ��ȡʣ��ʱ��
	long getbasetime(String paramString) {
		long temp1 = 0L;
		try {
			// ���ַ����������ض������ڸ�ʽ��ת���������ڣ�
			temp1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(
					paramString).getTime();
			// 2522880000��=80�� һ���ˣ�һ��ֻ�ܻ�25����
			// long temp2 = 2522880000L + temp1 / 1000L;
			if (name.equals("120")) {
				HowLong = 120;
			}
			if (name.equals("110")) {
				HowLong = 110;
			}
			if (name.equals("100")) {
				HowLong = 100;
			}
			if (name.equals("90")) {
				HowLong = 90;
			}
			if (name.equals("80")) {
				HowLong = 80;
			}
			if (name.equals("70")) {
				HowLong = 70;
			}
			if (name.equals("60")) {
				HowLong = 60;
			}
			if (name.equals("50")) {
				HowLong = 50;
			}
			if (!(name.equals(""))) {
				try {
					HowLong = Integer.parseInt(name);
				} catch (Exception e) {
					//Toast.makeText(getApplicationContext(), "С���ѣ���Ҫ�����룡��", 1)
						//	.show();
					name = "70";
				}
			}

			long temp2 = 31556926 * HowLong + temp1 / 1000L;
			// 365 31556926
			// 120
			// 110
			// 100
			// 90
			// 80 2524554080
			// 70
			// 60
			// 50

			// getTime���ص��Ǻ��룬����1000�����
			long temp3 = new Date().getTime() / 1000L;
			// ʣ��ʱ�� = ��ʱ�� - ���ĵ�ʱ��
			// ��ʱ�� = 80�������� + ������׼ƫ��������0000��00��00�տ�ʼ���������룩
			// ����ʱ�� = ����ʱ�䣨��0000��00��00�տ�ʼ�����ڣ�
			temp1 = temp2 - temp3;

			return temp1;
		} catch (ParseException localParseException) {

		}
		return temp1;
	}
}