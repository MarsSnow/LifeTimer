/**桌面小控件**/
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

	// 将显示小时、分钟、秒钟的ImageView定义成数组
	private int[] digitViews = new int[] { R.id.desktopt1, R.id.desktopt2,
			R.id.desktopt3, R.id.desktopt4, R.id.desktopt5, R.id.desktopt6,
			R.id.desktopt7, R.id.desktopt8, R.id.desktopt9, R.id.desktopt10,
			R.id.desktopt11, R.id.desktopt12 };

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		this.appWidgetManager = appWidgetManager;
		this.context = context;
//		// 定义计时器
		timer = new Timer();
		// 启动周期性调度
		timer.schedule(new TimerTask() {
			public void run() {
				// 发送空消息，通知界面更新
				handler.sendEmptyMessage(0x123);
			}
		}, 0, 1000);



	}

	// 显示时间的方法
	void showtime80(long time2, long time) {

		views.setTextViewText(digitViews[0], time - 2680000 + "秒");

		// showviewp.setText("秒");

		views.setTextViewText(digitViews[1], (time2) + "秒");

		// showviewp.setText("秒");

		views.setTextViewText(digitViews[2], (time / 3600L) - 744 + "小时");
		

		
		views.setTextViewText(digitViews[3], (time2 / 3600L) + "小时");

		// showviewp.setText("小时");

		views.setTextViewText(digitViews[4], (time / 0x15180L) - 31 + "天");

		// showviewp.setText("天");

		views.setTextViewText(digitViews[5], (time2 / 0x15180L) + "天");

		// showviewp.setText("天");

		views.setTextViewText(digitViews[6], (time / 0x93a80L) - 4 + "周");

		// showviewp.setText("星期");

		views.setTextViewText(digitViews[7], (time2 / 0x93a80L) + "周");

		// showviewp.setText("星期");
		
		views.setTextViewText(digitViews[8], (time / 2635200L) - 1 + "月");

		// showviewp.setText("月");

		views.setTextViewText(digitViews[9],(time2 / 2635200L) + "月");

		// showviewp.setText("月");

		views.setTextViewText(digitViews[10], (time / 31536000L) + "年");

		// showviewp.setText("年");
		// textview011.setText(String.valueOf(time / 31536000L) + "年");
		// 365.24
		// 365天5小时48分46秒
		// 18000+2880+46
		
		views.setTextViewText(digitViews[11],((time2 / 31536000L) + 1) + "年");

		// showviewp.setText("年");

		// 365天5小时48分46秒 = 31556926
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
				
				lifetime -= 1L;// 每秒减1
				lifetime02 += 1L;// 每秒减1
	
				showtime80(
						lifetime, lifetime02);
//				// 定义SimpleDateFormat对象
//				SimpleDateFormat df = new SimpleDateFormat("HHmmss");// 170533
//				// 将当前时间格式化成HHmmss的形式
//				String timeStr = df.format(new Date());
//				for (int i = 0; i < timeStr.length(); i++) {
//					// 将第i个数字字符转换为对应的数字
//					int num = timeStr.charAt(i) - 48;// 为什么要减去48
//					// 将第i个图片的设为对应的液晶数字图片
//
//					views.setTextViewText(digitViews[i], timeStr.charAt(i) + "");
//				}
				// 将AppWidgetProvider子类实例包装成ComponentName对象
				ComponentName componentName = new ComponentName(context,
						DigitClock.class);
				// 调用AppWidgetManager将remoteViews添加到ComponentName中
				appWidgetManager.updateAppWidget(componentName, views);
			}
			super.handleMessage(msg);
		}

	
	};
	
	private void getnameinfo() {
		// 从自己定义的global类中获取文件，路径为savetime，文件名为default
		name = g.getfile("pengbopluto2003ubsavetime", "default");
		String as[] = name.split("\t");
		name = as[0];
		sex = as[1];
		birthday = as[2];

		birthday_temp001 = birthday;

		if (birthday.equals("")) {
			birthday = "1989-06-10 00:00:00";
		} else {
			// 增加小时，分钟，秒
			birthday = (new StringBuilder(String.valueOf(birthday)))
					.append(" 00:00:00").toString();
		}
		if (name.equals("null")) {
			name = "80";
		}

		// 获取剩余时间
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
			costtemp002 = temp02;
			return temp02;
		} catch (ParseException localParseException) {

		}
		return temp02;
	}
	
	// 获取剩余时间
	long getbasetime(String paramString) {
		long temp1 = 0L;
		try {
			// 把字符串解析成特定的日期格式（转换出生日期）
			temp1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(
					paramString).getTime();
			// 2522880000秒=80岁 一个人，一生只能活25亿秒
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
					//Toast.makeText(getApplicationContext(), "小盆友，不要乱输入！！", 1)
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

			// getTime返回的是毫秒，除以1000变成秒
			long temp3 = new Date().getTime() / 1000L;
			// 剩余时间 = 总时间 - 消耗的时间
			// 总时间 = 80岁总秒数 + 出生基准偏移量（从0000年00月00日开始到出生的秒）
			// 消耗时间 = 现在时间（从0000年00月00日开始到现在）
			temp1 = temp2 - temp3;

			return temp1;
		} catch (ParseException localParseException) {

		}
		return temp1;
	}
}