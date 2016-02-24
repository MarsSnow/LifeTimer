/**更多选项界面**/
package peng.bo.ploto2003ub.lifetime.savetime;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

import java.util.*;

import cn.waps.AppConnect;

public class C_More_info_list extends Activity {

	Context m_context = C_More_info_list.this;
	protected static final int MENU_ABOUT = 1;
	protected static final int MENU_SETTING = 3;

	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);

		// 强制全屏
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		// 设置竖屏模式
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_BEHIND);

		setContentView(R.layout.all_hero_list);

		// 调用加载listView
		this.LoadListView();
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, 1, 0, "改善建议").setIcon(android.R.drawable.ic_menu_help);
		menu.add(0, 2, 0, "积分查询").setIcon(android.R.drawable.ic_menu_search);

		return super.onCreateOptionsMenu(menu);
	}

	public boolean onOptionsItemSelected(MenuItem menuitem) {
		switch (menuitem.getItemId()) {
		// 改善建议
		case 1: //
			AppConnect.getInstance(this).showFeedback();

			break;
		// 积分查询
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
								+ "\n" + "851104757@qq.com" + "\n"
								+ "2278048953@qq.com").show();

	}

	// 获取积分页面（广告墙）
	private void getpoint() {

		AppConnect.getInstance(this).showOffers(this);
	}

	protected void onResume() {
		super.onResume();

	}

	protected void onDestroy() {
		super.onDestroy();
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

			finish();
			return false;
		}
		return false;
	}

	// 加载listView方法
	private void LoadListView() {

		// TODO Auto-generated method stub
		List list = getData();

		String as[] = new String[2];
		as[0] = "title";
		as[1] = "info";

		int ai[] = new int[2];
		ai[0] = R.id.title;
		ai[1] = R.id.info;

		SimpleAdapter simpleadapter = new SimpleAdapter(this, list,
				R.layout.simplelist, as, ai);
		ListView listview = (ListView) findViewById(R.id.list_agility);
		listview.setAdapter(simpleadapter);

		listview.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

			public void onItemClick(AdapterView adapterview, View view, int i,
					long l) {
				switch (i) {

				case 0: {
					 Intent intent = new Intent();
					
					 intent.putExtra("mygonglue03text",
					 R.raw.version_info113);
					
					 intent.setClass(m_context,
					 D_ContextActivity.class);
					 startActivity(intent);

					break;
				}

				case 1: {
					OpenSuggestDialog();
					break;
				}

				case 2: {
					AppConnect.getInstance(m_context).showMore(m_context);
					//AppConnect.getInstance(getApplicationContext()).showMore(getApplicationContext());
					break;
				}

				case 3: {
					Intent intent = new Intent(Intent.ACTION_SEND); // 启动分享发送的属性
					intent.setType("text/plain"); // 分享发送的数据类型
					intent.putExtra(Intent.EXTRA_SUBJECT, "subject"); // 分享的主题
					intent.putExtra(Intent.EXTRA_TEXT, CommonLang.kShareInfo); // 分享的内容
					intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					m_context.startActivity(Intent.createChooser(intent, "分享"));// 目标应用选择对话框的标题
					break;
				}

				case 4: {
					AppConnect.getInstance(m_context).checkUpdate(m_context);
					break;
				}

				case 5: {
					 Intent intent = new Intent();
						
					 intent.putExtra("mygonglue03text",R.raw.wight_info113);
					
					 intent.setClass(m_context,
					 D_ContextActivity.class);
					 startActivity(intent);
					break;
				}
				//去BBS
				case 6: {
					Intent intent = new Intent();
					intent.putExtra("ObjectName", "/life-time-bbs.txt");
					intent.setClass(getApplicationContext(), E_BBSActivity.class);
					startActivity(intent);
					break;
				}

				case 7: {
					
					break;
				}
				//
				case 8: {
					AppConnect.getInstance(m_context).showMore(m_context);
					//AppConnect.getInstance(this).showMore(this);
					break;
				}
				//
				case 9: {

					AppConnect.getInstance(m_context)
							.checkUpdate(m_context);
					break;
				}
				//
				case 10: {
					Toast.makeText(m_context, "左上角为您的金币额度！",
							Toast.LENGTH_LONG).show();
					AppConnect.getInstance(m_context).showOffers(m_context);
					break;
				}

				//
				case 11: {

					break;
				}
				//
				case 12: {

					break;
				}

				//
				case 13: {

					break;
				}
				}
			}
		});

	}

	private List getData() {
		ArrayList arraylist = new ArrayList();

		HashMap hashmap = new HashMap();
		hashmap.put("title", "1.版本更新信息");
		hashmap.put("info", "");

		arraylist.add(hashmap);

		HashMap hashmap1 = new HashMap();
		hashmap1.put("title", "2.关于");
		hashmap1.put("info", " ");
		//
		 arraylist.add(hashmap1);
		//
		 HashMap hashmap2 = new HashMap();
		 hashmap2.put("title", "3.试试我的其他应用");
		 hashmap2.put("info", " ");
		
		 arraylist.add(hashmap2);
		 HashMap hashmap3 = new HashMap();
		 hashmap3.put("title", "4.分享给好友");
		 hashmap3.put("info", " ");
		//
		 arraylist.add(hashmap3);
		 HashMap hashmap4 = new HashMap();
		 hashmap4.put("title", "5.检测更新");
		 hashmap4.put("info", "");
		
		 arraylist.add(hashmap4);
		//
		 HashMap hashmap5 = new HashMap();
		 hashmap5.put("title", "6.桌面小控件");
		 hashmap5.put("info", "");		
		 arraylist.add(hashmap5);
		 
//		 HashMap hashmap6 = new HashMap();
//		 hashmap6.put("title", "7.交流反馈版");
//		 hashmap6.put("info", "");
// 		arraylist.add(hashmap6);
		 
		// HashMap hashmap7 = new HashMap();
		// hashmap7.put("title", "8.分享给好友");
		// hashmap7.put("info", "");
		//
		// arraylist.add(hashmap7);
		// HashMap hashmap8 = new HashMap();
		// hashmap8.put("title", "9.试试我的其他应用");
		// hashmap8.put("info", "");
		//
		// arraylist.add(hashmap8);
		// HashMap hashmap9 = new HashMap();
		// hashmap9.put("title", "10.检测更新");
		// hashmap9.put("info", "");
		//
		// arraylist.add(hashmap9);
		// HashMap hashmap10 = new HashMap();
		// hashmap10.put("title", "11.金币查询");
		// hashmap10.put("info", "");
		//
		// arraylist.add(hashmap10);
		// HashMap hashmap11 = new HashMap();
		// hashmap11.put("title", "12.相关音频");
		// hashmap11.put("info", "");
		//
		// arraylist.add(hashmap11);
		// HashMap hashmap12 = new HashMap();
		// hashmap12.put("title", "13.技能模拟器");
		// hashmap12.put("info", "");
		//
		// arraylist.add(hashmap12);
		// HashMap hashmap13 = new HashMap();
		// hashmap13.put("title", "14.程序改动日志");
		// hashmap13.put("info", "");
		// hashmap13.put("img", Integer.valueOf(R.drawable.pvl1014));
		// arraylist.add(hashmap13);
		// HashMap hashmap14 = new HashMap();
		// hashmap14.put("title", "15.能源革命");
		// hashmap14.put("info", "");
		// hashmap14.put("img", Integer.valueOf(R.drawable.pvl1015));
		// arraylist.add(hashmap14);
		// HashMap hashmap15 = new HashMap();
		// hashmap15.put("title", "16.可持续发展");
		// hashmap15.put("info", "");
		// hashmap15.put("img", Integer.valueOf(R.drawable.pvl1016));
		// arraylist.add(hashmap15);
		// HashMap hashmap16 = new HashMap();
		// hashmap16.put("title", "17.功");
		// hashmap16.put("info", "");
		// hashmap16.put("img", Integer.valueOf(R.drawable.pvl1017));
		// arraylist.add(hashmap16);
		// HashMap hashmap17 = new HashMap();
		// hashmap17.put("title", "18.机械效率");
		// hashmap17.put("info", "");
		// hashmap17.put("img", Integer.valueOf(R.drawable.pvl1018));
		// arraylist.add(hashmap17);
		// HashMap hashmap18 = new HashMap();
		// hashmap18.put("title", "19.功率");
		// hashmap18.put("info", "");
		// hashmap18.put("img", Integer.valueOf(R.drawable.pvl1019));
		// arraylist.add(hashmap18);
		// HashMap hashmap19 = new HashMap();
		// hashmap19.put("title", "20.动能");
		// hashmap19.put("info", "");
		// hashmap19.put("img", Integer.valueOf(R.drawable.pvl1020));
		// arraylist.add(hashmap19);
		// HashMap hashmap20 = new HashMap();
		// hashmap20.put("title", "21.势能");
		// hashmap20.put("info", "");
		// hashmap20.put("img", Integer.valueOf(R.drawable.pvl1021));
		// arraylist.add(hashmap20);
		// HashMap hashmap21 = new HashMap();
		// hashmap21.put("title", "22.双头龙");
		// hashmap21.put("info", " 俗称：双头龙   简称：THD");
		// hashmap21.put("img", Integer.valueOf(R.drawable.thd));
		// arraylist.add(hashmap21);
		// HashMap hashmap22 = new HashMap();
		// hashmap22.put("title", "23.巫医");
		// hashmap22.put("info", " 俗称：51   简称：WD");
		// hashmap22.put("img", Integer.valueOf(R.drawable.wd));
		// arraylist.add(hashmap22);
		// HashMap hashmap23 = new HashMap();
		// hashmap23.put("title", "24.极寒幽魂");
		// hashmap23.put("info", " 俗称：冰魂   简称：AA");
		// hashmap23.put("img", Integer.valueOf(R.drawable.aa));
		// arraylist.add(hashmap23);
		// HashMap hashmap24 = new HashMap();
		// hashmap24.put("title", "25.黑暗贤者");
		// hashmap24.put("info", " 俗称：兔子   简称：DS");
		// hashmap24.put("img", Integer.valueOf(R.drawable.ds));
		// arraylist.add(hashmap24);
		// HashMap hashmap25 = new HashMap();
		// hashmap25.put("title", "26.死亡先知");
		// hashmap25.put("info", " 俗称：吹风婆   简称：DP");
		// hashmap25.put("img", Integer.valueOf(R.drawable.dp));
		// arraylist.add(hashmap25);
		// HashMap hashmap26 = new HashMap();
		// hashmap26.put("title", "27.恶魔巫师");
		// hashmap26.put("info", " 俗称：莱恩   简称：LION");
		// hashmap26.put("img", Integer.valueOf(R.drawable.lion));
		// arraylist.add(hashmap26);
		// HashMap hashmap27 = new HashMap();
		// hashmap27.put("title", "28.谜团");
		// hashmap27.put("info", " 俗称：谜团   简称：ENI");
		// hashmap27.put("img", Integer.valueOf(R.drawable.eni));
		// arraylist.add(hashmap27);
		// HashMap hashmap28 = new HashMap();
		// hashmap28.put("title", "29.巫妖");
		// hashmap28.put("info", " 俗称：巫妖   简称：LICH");
		// hashmap28.put("img", Integer.valueOf(R.drawable.lich));
		// arraylist.add(hashmap28);
		// HashMap hashmap29 = new HashMap();
		// hashmap29.put("title", "30.死灵法师");
		// hashmap29.put("info", " 俗称：死灵   简称：NEC");
		// hashmap29.put("img", Integer.valueOf(R.drawable.nec));
		// arraylist.add(hashmap29);
		// HashMap hashmap30 = new HashMap();
		// hashmap30.put("title", "31.遗忘法师");
		// hashmap30.put("info", " 俗称：骨法   简称：PUGNA");
		// hashmap30.put("img", Integer.valueOf(R.drawable.pugna));
		// arraylist.add(hashmap30);
		// HashMap hashmap31 = new HashMap();
		// hashmap31.put("title", "32.黑曜毁灭者");
		// hashmap31.put("info", " 俗称：黑鸟   简称：OD");
		// hashmap31.put("img", Integer.valueOf(R.drawable.od));
		// arraylist.add(hashmap31);
		// HashMap hashmap32 = new HashMap();
		// hashmap32.put("title", "33.痛苦女王");
		// hashmap32.put("info", " 俗称：阿卡莎   简称：QOP");
		// hashmap32.put("img", Integer.valueOf(R.drawable.qop));
		// arraylist.add(hashmap32);
		// HashMap hashmap33 = new HashMap();
		// hashmap33.put("title", "34.术士");
		// hashmap33.put("info", " 俗称：术士   简称：WL");
		// hashmap33.put("img", Integer.valueOf(R.drawable.wl));
		// arraylist.add(hashmap33);
		// HashMap hashmap34 = new HashMap();
		// hashmap34.put("title", "35.暗影恶魔");
		// hashmap34.put("info", " 俗称：毒狗   简称：SD");
		// hashmap34.put("img", Integer.valueOf(R.drawable.sd));
		// arraylist.add(hashmap34);
		// HashMap hashmap35 = new HashMap();
		// hashmap35.put("title", "36.痛苦之源");
		// hashmap35.put("info", " 俗称：痛苦   简称：BE");
		// hashmap35.put("img", Integer.valueOf(R.drawable.bane));
		// arraylist.add(hashmap35);
		//
		// HashMap hashmap36 = new HashMap();
		// hashmap36.put("title", "37.大魔导师");
		// hashmap36.put("info", " 俗称：大魔导   简称：GM");
		// hashmap36.put("img", Integer.valueOf(R.drawable.gm));
		// arraylist.add(hashmap36);
		//
		// HashMap hashmap37 = new HashMap();
		// hashmap37.put("title", "38.扎贡纳斯");
		// hashmap37.put("info", " 俗称：天怒法师   简称：SM");
		// hashmap37.put("img", Integer.valueOf(R.drawable.tiannufashi));
		// arraylist.add(hashmap37);
		//
		// HashMap hashmap38 = new HashMap();
		// hashmap38.put("title", "39.傲洛斯 ");
		// hashmap38.put("info", " 俗称：寒冬飞龙   简称：WW");
		// hashmap38.put("img", Integer.valueOf(R.drawable.ice_dragen));
		// arraylist.add(hashmap38);

		return arraylist;
	}

}
