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
	static String host = "bcs.duapp.com";
	static String accessKey = "2f4c285cff80949341438a10dbb774c9	";
	static String secretKey = "06db3c0343c1caa9094f75dc4c374611";
	static String bucket = "pluto2003ub";
	static String object = "/pengbo-first-object";

	Context context;//句柄
	
	// 快捷方式判断存储
	SharedPreferences sp;//存储类

	boolean isFirst;//是否是第一次启动

	String birthday;
	String birthday_temp001;
	global m_global;
	Handler handler;
	long lifetime;
	long lifetime02;

	String canLiveTime = "";
	Runnable runnable;
	String savefile;
	String sex;
	int sheight;
	int showtype;
	TextView showview;
	TextView showviewp;
	int swidth;

	Button note_text001;
	long tempjudge001;
	long tempjudge002;
	long costtemp002;
	long HowLong = 70;
	// 屏幕常亮管理
	android.os.PowerManager.WakeLock wakeLock = null;
	ImageButton button001;
	/** 起点按钮 **/
	Button qidian;
	/** 寿命 **/
	TextView shouming;
	/** 秒 **/
	TextView textview001;
	TextView textview002;
	/** 时 **/
	TextView textview003;
	TextView textview004;
	/** 天 **/
	TextView textview005;
	TextView textview006;
	/** 周 **/
	TextView textview007;
	TextView textview008;
	/** 月 **/
	TextView textview009;
	TextView textview010;
	/** 年 **/
	TextView textview011;
	TextView textview012;
		
	/**名言字符串**/
	//设置父母年龄时
	String str002[] = { "多陪陪父母，爷爷奶奶，多陪陪亲人们！", "父母老了，请多陪陪他们！", "父母老了，请多陪陪他们！",
			"这个世界上有很多事情，你以为明天一定可以再继续做的，但可以继续做的机会是有限的，请多陪陪亲人！",

			"不要以为日子既然这样一天一天地过来的，就理所当然地认为会一直这样下去，请多陪陪亲人！", "父母老了，请多陪陪他们！"

	};
	//随机出现的名言警句
	String str[] = {
			"珍惜现在，因为会有那么一次，在你一放手，一转身不久，有的事情就完全改变了！",
			"记着也好，忘掉也罢，短暂人生中有一个一直都在的人或事物，总是好的。",
			"有很长的路要走的时候，反倒不能太用力--老古竹斋",
			"梦里出现的人，醒来时就该去见她，生活就是那么简单。--《新桥恋人》",
			"每一个不曾起舞的日子，都是对生命的辜负--尼采",
			" 有些东西，失去了就永远也无法得到了,请珍惜……",
			"不要等快要失去时，才无限懊悔地想起要珍惜 ",
			"难道生命在片刻欢聚之后真的只能剩下离散与凋零？ ",
			"一寸光阴一寸金,寸金难买寸光阴。",
			"不饱食以终日,不弃功于寸阴。",
			"在今天和明天之间，有一段很长的时间；趁你还有精神的时候，学习迅速办事.－－歌德",
			"我们若要生活，就该为自己建造一种充满感受、思索和行动的时钟，用它来代替这个枯燥、单调、以愁闷来扼杀心灵，带有责备意味和冷冷地滴答着的时间。－－高尔基",
			"完成工作的方法是爱惜每一分钟。－－达尔文",
			"合理安排时间，就等于节约时间。－－培根",
			"应当仔细地观察，为的是理解；应当努力地理解，为的是行动。－－罗曼罗兰",
			"每一点滴的进展都是缓慢而艰巨的，一个人一次只能着手解决一项有限的目标。－－贝弗里奇",
			"成功＝艰苦劳动＋正确的方法＋少说空话。－－爱因斯坦",
			"放弃时间的人，时间也放弃他。——莎士比亚",
			"没有方法能使时钏为我敲已过去了的钟点。——拜伦",
			"人的全部本领无非是耐心和时间的混合物。——巴尔扎克",
			"任何节约归根到底是时间的节约。——马克思",
			"时间就是能力等等发展的地盘。——马克思",
			"时间是世界上一切成就的土壤。时间给空想者痛苦，给创造者幸福。——麦金西",
			"时间是伟大的导师。——伯克",
			"时间是一个伟大的作者，它会给每个人写出完美的结局来。——卓别林",
			"时间最不偏私，给任何人都是二十四小时；时间也是偏私，给任何人都不是二十四小时。——赫胥黎",
			"忘掉今天的人将被明天忘掉。——歌德",
			"辛勤的蜜蜂永没有时间的悲哀。——布莱克",
			"在所有的批评中，最伟大、最正确、最天才的是时间。——别林斯基",
			"从不浪费时间的人，没有工夫抱怨时间不够。—杰弗逊",
			"时间是我的财产，我的田亩是时间。—歌德",
			"合理安排时间，就等于节约时间。——培根",
			"春光不自留，莫怪东风恶。——莎士比亚",
			"抛弃今天的人，不会有明天；而昨天，不过是行去流水。——约翰•洛克",
			"抛弃时间的人，时间也抛弃他。—莎士比亚",
			"一切节省，归根到底都归结为时间的节省。---马克思",
			"利用时间是一个极其高级的规律。---恩格斯",
			"合理安排时间，就等于节约时间。---培根",
			"今天所做之事勿候明天，自己所做之事勿候他人。---歌德",
			"今天应做的事没有做，明天再早也是耽误了。---裴斯泰洛齐",
			"浪费时间是一桩大罪过。---卢梭",
			"你热爱生命吗？那么别浪费时间，因为时间是组成生命的材料.－－富兰克林",
			"把活着的每一天看作生命的最后一天.－－海伦•凯勒",
			"迁延蹉跎，来日无多，二十丽姝，请来吻我，衰草枯杨，青春易过。---英国剧作家莎士比亚",
			"普通人只想到如何度过时间，有才能的人设法利用时间。---德国哲学家叔本华",
			"黄金时代在我们面前而不在我们背后。---美国作家马克•吐温",
			"人生苦短，若虚度年华，则短暂的人生就太长了。---英国剧作家莎士比亚",
			"只要我们能善用时间,就永远不愁时间不够用。---德国诗人歌德",
			"不管饕餮的时间怎样吞噬着一切，我们要在这一息尚存的时候，努力博取我们的声誉，使时间的不能伤害我们。——莎士比亚",
			"不要老叹息过去，它是不再回来的；要明智地改善现在。要以不忧不惧的坚决意志投入扑朔迷离的未来。——朗费罗【美】",
			"不要为已消尽之年华叹息，必须正视匆匆溜走的时光。——布莱希特【德】",
			"当许多人在一条路上徘徊不前时，他们不得不让开一条大路，让那珍惜时间的人赶到他们的前面去。——苏格拉底【古希腊】",
			"敢于浪费哪怕一个钟头时间的人，说明他还不懂得珍惜生命的全部价值。——达尔文【英】",
			"即将来临的一天，比过去的一年更为悠长。——福尔斯特【英】",
			"集腋成裘，聚沙成塔。几秒钟虽然不长，却构成永恒长河中的伟大时代。——弗莱彻【英】",
			"少壮不努力,老大徒伤悲。",
			"黑发不知勤学早,白首方悔读书迟。",
			"花儿还有重开日,人生没有再少年",
			"逝者如斯夫，不舍昼夜（孔子）",
			"人生天地之间，若白驹过隙，忽然而已。（庄子）",
			"盛年不重来，一日难再晨。及时当勉励，岁月不待人。（陶渊明）",
			"明日复明日，明日何其多，我生待明日，万事成蹉跎。世人若被明日累，春去秋来老将至。朝看水东流，暮看日西坠。百年明日能几何，请君听我明日歌。",
			"今日复今日，今日何其少！今日又不为，此事何时了！人生百年几今日，今日不为真可惜！若言姑待明朝至，明朝又有明朝事。为君聊赋今日诗，努力请从今日始。",
			"在隆冬季节,我终于意识到我心中的夏季不可磨灭。",
			"请珍惜，在这世间迟来的，却又偏要急急落幕的幸福。",
			"静待时光逝去，然后把这一切都逐渐忘记。",
			"在人类任何一段美丽的记载里，是否会有你的足迹？",
			"一生中所坚持的爱，难道早在千年前就已是书里写完了的故事？ ",
			"你是否在无垠的天空中选定了自己的方向？",
			"原来我们可以从流走的岁月里学到这么多的东西。虽然时光不再！时光已不再！ ",
			"对生命,对内里的激情 我们从来没有人能够真正知足",
			"行走在人群之中,我们的热血慢慢流空,逐渐开始怀疑起 ,今日与昨日自己真正的面容",
			"时光可以浪掷着一切的美， 一切的爱,一切对我们曾经是那样珍贵难求的温柔的记忆",
			"当所有的一切转瞬成空，留在眼前的即如那盛宴过后的杯盘狼藉，停箸间化作一缕相思。",
			"不再回头的,不再是古老的辰光,也不只是那些个夜晚的,星群和月亮。",
			"在拥挤的市街前,在仓皇下降的暮色中,我年轻的心啊,永不在相逢",
			"人在年轻的时候一定要好好的生活，不要等时光流逝，才发现原来什么都没有留下，留下的只有那一颗已经苍老的心。",
			"一定要在暮色前，多多的去享受茉莉的清香 ，青草的清新，多多的存储美丽。",
			"如果能，深深地爱过一次再别离 ,那么 再长久的一生 ,不也就只是,就只是 ,回首时 ,那短短的一瞬",
			"这世界不是绝对的好,它有离别 有衰老",
			"人生只有一次机会，上主啊，请给我一个长长的夏季，给我一段无瑕的回忆，给我一颗温柔的心，给我一份洁白的恋情 ",
			"我只能来这世上一次 所以,请让我一个美丽的名字,好让她能在夜里低唤我,在奔驰的岁月里,永远记得我们曾经相爱的事",
			"不要等蓦然回首时，才忽然记起了，一些没能 实现的诺言 ，一些 无法解释的悲伤 ",
			"在年轻的时候，如果你爱上了一个人， 请你，请你一定要温柔地对待她。 不管你们相爱的时间有多长或多短， 若你们能始终温柔地相待，那么， 所有的时刻都将是一种无瑕的美丽。 若不得不分离，也要好好地说声再见， 也要在心里存着感谢， 感谢她给了你一份记忆。 长大了以后，你才会知道， 在蓦然回首的刹那， 没有怨恨的青春才会了无遗憾， 如山冈上那轮静静的满月。",
			"年轻的你,有足够的理由相信,你将会得到这世间最幸福的一份爱,所以要耐心地等待,果实要成熟了以后才会香甜幸福也是一样",
			"当生命娇嫩的时候,我们如此鲜明地快乐与痛苦,当生命成熟的时候，我们变得厚重忍耐",
			"青春有时候极为短暂，有时候却极为冗长。",
			"利用寸阴是任何种类的战斗中博得胜利的秘诀（美国）   ",
			"钉子是敲进去的，时间是挤出来的  ",
			"大豆不挤出油，时间不挤白会溜  ",
			"善于利用时间的人，永远找得到充裕的时间  ",
			"谁对时间越吝啬，时间对谁就越慷慨  ",
			"时间最不偏私，给任何人都是二十四小时；时间也最偏私，给任何人都不是二十四小时（赫胥黎）",
			"三更灯火五更鸡，正是男儿读书时，黑发不知勤学早，白发方悔读书迟。－－颜真卿",
			"劝君莫惜金缕衣，劝君惜取少年时。花开堪折直须折，莫待无花空折枝。",
			"时间就象海绵里的水一样，只要你愿挤，总还是有的（鲁迅）",
			"时间是由分秒积成的，善于利用零星时间的人，才会做出更大的成绩来（华罗庚）  ",
			"用“分”来计算时间的人，比用“时来计算时间的人，时间多五十九倍。（雷巴柯夫）",
			"假如我来世上一遭,只为与你相聚一次, 只为了亿万光年里的那一刹那,一刹那里所有的甜蜜和悲凄,那么就让一切该发生的,都在瞬间出现,让我俯首感谢所有星球的相助,让我与你相遇与你别离,完成了上帝所作的一首诗,然后再缓缓地老去",
			"是否有在日落之前也从来未曾放弃过的理想？", "那被时光所焚尽了的日子，也能重新回来吗？ ",
			"当我们老去的时候，我们独自品尝悠悠岁月中所沉淀下的醇厚味道",

	};

	//生命周期
	public void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);//调用父类的OnCreate方法
		
		// 声明使用自定义标题
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		// 强制全屏
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		// 设置竖屏模式
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_BEHIND);
		//设置布局文件
		setContentView(R.layout.main02);
		//使用自定义标题
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.title_bar);// 自定义布局赋值

		// 启动广告平台SDK
		new AdThread().StartMyAd();
		Toast.makeText(this, "您可在手机设置里面往桌面添加倒计时小控件！", Toast.LENGTH_SHORT)
				.show();
	
		// 保持屏幕始终常亮的情况
		acquireWakeLock();
		global localglobal = new global(this);
		this.m_global = localglobal;

		this.showtype = 0;// 没有吧？
		
		// 获取手机屏幕
		DisplayMetrics localDisplayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
		this.swidth = localDisplayMetrics.widthPixels;
		this.sheight = localDisplayMetrics.heightPixels;
		FindView();

		// 产生随机数，随机取出的提示
		note_text001
				.setText(str[Math.abs(new Random().nextInt()) % str.length]);
		SetListener();// 名言监听器

		this.handler = new Handler();

		//线程
		this.runnable = new Runnable() {
			public void run() {

				lifetime -= 1L;// 每秒减1
				lifetime02 += 1L;// 每秒减1
				A_MainActivity.this.showtime80(A_MainActivity.this.lifetime,
						lifetime02);
				A_MainActivity.this.handler.postDelayed(this, 1000L);
			}
		};
		this.handler.postDelayed(this.runnable, 1000L);

		whether_add_shortcut();//判断是否添加快捷方式
		
		//初始化广告相关
		showminiad();//显示迷你广告
		AppConnect.getInstance(context).initPopAd(getApplicationContext());
		String value = AppConnect.getInstance(context).getConfig("pop_ad_life_time", "1");
		
		if (value.equals("0")) {
			AppConnect.getInstance(context).showPopAd(context);
		} else {

		}
	}

	//判断是否添加快捷方式
	private void whether_add_shortcut() {
		// 添加快捷方式
		// 获得SharedPreference
		sp = this.getSharedPreferences("shortcutdata", MODE_WORLD_READABLE);
		// 从SharedPreference取值，若键为“first”的值不存在，则默认为true
		isFirst = sp.getBoolean("first", true);
		// 第一次运行，使用默认的true
		if (isFirst) {

			// 创建一个SharedPreference的编辑器
			Editor editor = sp.edit();
			// 往编辑器中赋值，赋为false，第二次运行就不会在到这里来
			editor.putBoolean("first", false);
			// 编辑器数据提交
			editor.commit();
			// 增加快捷方式
			addShortcut();
			// Toast.makeText(getApplicationContext(), "第一次！", 0).show();

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
		Intent shortcut = new Intent(
				"com.android.launcher.action.INSTALL_SHORTCUT");

		// 快捷方式的名称 
		shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME,
				getString(R.string.app_name));
		shortcut.putExtra("duplicate", false); // 不允许重复创建 

		// 指定当前的Activity为快捷方式启动的对象: 如 com.everest.video.VideoPlayer 
		// 注意: ComponentName的第二个参数必须加上点号(.)，否则快捷方式无法启动相应程序 
		ComponentName comp = new ComponentName(this.getPackageName(), "."
				+ this.getLocalClassName());
		shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, new Intent(
				Intent.ACTION_MAIN).setComponent(comp));

		// 快捷方式的图标 
		ShortcutIconResource iconRes = Intent.ShortcutIconResource.fromContext(
				this, R.drawable.icon);
		shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, iconRes);

		sendBroadcast(shortcut);//向系统发送广告，添加快捷方式
	}

	//找到相应的控件
	private void FindView() {

		button001 = (ImageButton) findViewById(R.id.button001);
		note_text001 = (Button) findViewById(R.id.note_text001);
		qidian = (Button) findViewById(R.id.qidian);
		shouming = (TextView) findViewById(R.id.shouming);
		textview001 = (TextView) findViewById(R.id.t1);
		textview002 = (TextView) findViewById(R.id.t2);
		textview003 = (TextView) findViewById(R.id.t3);
		textview004 = (TextView) findViewById(R.id.t4);
		textview005 = (TextView) findViewById(R.id.t5);
		textview006 = (TextView) findViewById(R.id.t6);
		textview007 = (TextView) findViewById(R.id.t7);
		textview008 = (TextView) findViewById(R.id.t8);
		textview009 = (TextView) findViewById(R.id.t9);
		textview010 = (TextView) findViewById(R.id.t10);
		textview011 = (TextView) findViewById(R.id.t11);
		textview012 = (TextView) findViewById(R.id.t12);

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
				intent.putExtra(
						Intent.EXTRA_TEXT,
						"向你推荐一款软件《人生计时器》！下载地址：http://pan.baidu.com/share/link?shareid=436786&uk=3590869423"); // 分享的内容
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(Intent.createChooser(intent, "分享"));// 目标应用选择对话框的标题

			}

		});
		//更多信息
		button001.setOnClickListener(new View.OnClickListener() {
			public void onClick(View paramView) {
				// openOptionsMenu();
				Intent intent = new Intent();
				intent.setClass(getApplicationContext(), C_More_info_list.class);
				startActivity(intent);
			}
		});
		//名言警句button
		note_text001.setOnClickListener(new View.OnClickListener() {
			public void onClick(View paramView) {
				note_text001.setText(str[Math.abs(new Random().nextInt())
						% str.length]);
			}
		});
		//人生起点按钮
		qidian.setOnClickListener(new View.OnClickListener() {
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
		if (canLiveTime.equals("first")) {

			m_global.putfile("", "70" + "\t" + "男" + "\t" + "1989-06-10  00:00:00",
					"pengbopluto2003ubsavetime");

			canLiveTime = "70";
			// AlertDialog alertdialog = (new
			// android.app.AlertDialog.Builder(this))
			// .create();
			// Toast.makeText(this, "珍惜现在，因为会有那么一次，在你一放手，一转身不久，有的事情就完全改变了！",
			// Toast.LENGTH_LONG).show();

			canLiveTime = m_global.getfile("pengbopluto2003ubsavetime", "default");
			String as[] = canLiveTime.split("\t");
			canLiveTime = as[0];
			sex = as[1];
			birthday = as[2];

			if (birthday.equals("")) {
				birthday = "1989-06-10 00:00:00";
			} else {
				// 增加小时，分钟，秒
				birthday = (new StringBuilder(String.valueOf(birthday)))
						.append("00:00:00").toString();
			}
			// 获取基准时间
			lifetime = getRemainTime(birthday);
			lifetime02 = getCostTime(birthday);
			showtime80(lifetime, lifetime02);
			
		} else {//不是第一次

			// 从自己定义的global类中获取文件，路径为savetime，文件名为default
			canLiveTime = m_global.getfile("pengbopluto2003ubsavetime", "default");
			String as[] = canLiveTime.split("\t");
			canLiveTime = as[0];
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
			if (canLiveTime.equals("null")) {
				canLiveTime = "80";
			}

			// 获取剩余时间
			lifetime = getRemainTime(birthday);
			lifetime02 = getCostTime(birthday);
			showtime80(lifetime, lifetime02);
			qidian.setText(birthday_temp001);
			shouming.setText(canLiveTime);

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

	}

	// 获取剩余时间
	long getRemainTime(String paramString) {
		long temp1 = 0L;
		try {
			// 把字符串解析成特定的日期格式（转换出生日期）
			temp1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(paramString).getTime();
			// 2522880000秒=80岁 一个人，一生只能活25亿秒
			// long temp2 = 2522880000L + temp1 / 1000L;
			if (canLiveTime.equals("120")) {
				HowLong = 120;
			}
			if (canLiveTime.equals("110")) {
				HowLong = 110;
			}
			if (canLiveTime.equals("100")) {
				HowLong = 100;
			}
			if (canLiveTime.equals("90")) {
				HowLong = 90;
			}
			if (canLiveTime.equals("80")) {
				HowLong = 80;
			}
			if (canLiveTime.equals("70")) {
				HowLong = 70;
			}
			if (canLiveTime.equals("60")) {
				HowLong = 60;
			}
			if (canLiveTime.equals("50")) {
				HowLong = 50;
			}
			//若输入年龄为空
			if (!(canLiveTime.equals(""))) {
				try {
					HowLong = Integer.parseInt(canLiveTime);
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(), "小盆友，不要乱输入！！", 1)
							.show();
					canLiveTime = "70";
				}
			}

			long totalTime = 31556926 * HowLong + temp1 / 1000L;
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
			costtemp002 = temp02;
			return temp02;
		} catch (ParseException localParseException) {

		}
		return temp02;
	}

	// 显示时间的方法
	void showtime80(long remainTime, long costTime) {

		//textview001.setText(time - 2680000 + "秒");
		textview001.setText(costTime + "秒");
		// showviewp.setText("秒");

		textview002.setText((remainTime) + "秒");
		// showviewp.setText("秒");

		textview003.setText((costTime / 3600L) + "小时");

		textview004.setText((remainTime / 3600L) + "小时");
		// showviewp.setText("小时");

		textview005.setText((costTime / 0x15180L)  + "天");
		// showviewp.setText("天");

		textview006.setText((remainTime / 0x15180L) + "天");
		// showviewp.setText("天");

		
		/*
		 * 0x93a80L
		 * 
		 *4.35X24*60*60=375840
		 *
		 ***/
		
		textview007.setText((costTime / 0x93a80L)  + "周");
		// showviewp.setText("星期");

		textview008.setText((remainTime / 0x93a80L) + "周");
		// showviewp.setText("星期");

		/**
		 * 30.5天
		 * 30.5*24*60*60=2635200L
		 * 30.45*24*60*60=2630880L
		 * **/
		
		textview009.setText((costTime / 2630880L)  + "月");
		// showviewp.setText("月");

		textview010.setText(((remainTime / 2630880L)+1) + "月");
		// showviewp.setText("月");

		textview011.setText((costTime / 31556926L) + "年");
		// showviewp.setText("年");
		// textview011.setText(String.valueOf(time / 31536000L) + "年");
		// 365.24
		// 365天5小时48分46秒
		// 18000+2880+46
		textview012.setText(((remainTime / 31556926L) + 1) + "年");
		//textview012.setText(((time2 / 31536000L) + 1) + "年");
		// showviewp.setText("年");

		// 365天5小时48分46秒 = 31556926
		// 365T 31536000L
		// 18000+2880+46 = 20926
	}

	//屏幕常亮
	private void acquireWakeLock() {
		if (wakeLock == null) {

			PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
			wakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK, this
					.getClass().getCanonicalName());
			wakeLock.acquire();
		}
	}

	//取消屏幕常亮
	private void releaseWakeLock() {
		if (wakeLock != null && wakeLock.isHeld()) {
			wakeLock.release();
			wakeLock = null;
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
		canLiveTime = m_global.getfile("pengbopluto2003ubsavetime", "default");

		// name = "";
		if (canLiveTime != null) {
			
			JudgeCanLiveTime();

			// 名言，50年，要记得照顾老人
			if (costtemp002 >= 31536000 * 50) {

				note_text001.setText(str002[Math.abs(new Random().nextInt())
						% str002.length]);
			}
		} else {
			canLiveTime = "first";
			JudgeCanLiveTime();
		}

	}
}