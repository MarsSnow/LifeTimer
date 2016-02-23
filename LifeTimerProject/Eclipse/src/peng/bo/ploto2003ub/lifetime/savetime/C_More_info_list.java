/**����ѡ�����**/

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

	Context ThisContext = C_More_info_list.this;
	protected static final int MENU_ABOUT = 1;
	protected static final int MENU_SETTING = 3;

	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);

		// ǿ��ȫ��
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		// ��������ģʽ
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_BEHIND);

		setContentView(R.layout.all_hero_list);

		// ���ü���listView
		this.LoadListView();

	}

	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, 1, 0, "���ƽ���").setIcon(android.R.drawable.ic_menu_help);
		menu.add(0, 2, 0, "���ֲ�ѯ").setIcon(android.R.drawable.ic_menu_search);

		return super.onCreateOptionsMenu(menu);
	}

	public boolean onOptionsItemSelected(MenuItem menuitem) {
		switch (menuitem.getItemId()) {
		// ���ƽ���
		case 1: //
			AppConnect.getInstance(this).showFeedback();

			break;
		// ���ֲ�ѯ
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

	// ����listView����
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
					
					 intent.setClass(ThisContext,
					 D_ContextActivity.class);
					 startActivity(intent);

					break;

				}

				case 1: {
					OpenSuggestDialog();
					break;
				}

				case 2: {
					AppConnect.getInstance(ThisContext).showMore(ThisContext);
					//AppConnect.getInstance(getApplicationContext()).showMore(getApplicationContext());
					break;
				}

				case 3: {
					Intent intent = new Intent(Intent.ACTION_SEND); // ���������͵�����
					intent.setType("text/plain"); // �����͵���������
					intent.putExtra(Intent.EXTRA_SUBJECT, "subject"); // ���������
					intent.putExtra(
							Intent.EXTRA_TEXT,
							"�����Ƽ�һ�����[������ʱ��]�����ص�ַ��http://pan.baidu.com/share/link?shareid=436786&uk=3590869423"); // ���������
					intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					ThisContext.startActivity(Intent
							.createChooser(intent, "����"));// Ŀ��Ӧ��ѡ��Ի���ı���

					break;
				}

				case 4: {
					AppConnect.getInstance(ThisContext)
					.checkUpdate(ThisContext);
					break;
				}

				case 5: {
					 Intent intent = new Intent();
						
					 intent.putExtra("mygonglue03text",
					 R.raw.wight_info113);
					
					 intent.setClass(ThisContext,
					 D_ContextActivity.class);
					 startActivity(intent);
					break;
				}

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
					AppConnect.getInstance(ThisContext).showMore(ThisContext);
					//AppConnect.getInstance(this).showMore(this);
					break;
				}
				//
				case 9: {

					AppConnect.getInstance(ThisContext)
							.checkUpdate(ThisContext);
					break;
				}
				//
				case 10: {
					Toast.makeText(ThisContext, "���Ͻ�Ϊ���Ľ�Ҷ�ȣ�",
							Toast.LENGTH_LONG).show();
					AppConnect.getInstance(ThisContext).showOffers(ThisContext);
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
		hashmap.put("title", "1.�汾������Ϣ");
		hashmap.put("info", "");

		arraylist.add(hashmap);

		HashMap hashmap1 = new HashMap();
		hashmap1.put("title", "2.����");
		hashmap1.put("info", " ");
		//
		 arraylist.add(hashmap1);
		//
		 HashMap hashmap2 = new HashMap();
		 hashmap2.put("title", "3.�����ҵ�����Ӧ��");
		 hashmap2.put("info", " ");
		
		 arraylist.add(hashmap2);
		 HashMap hashmap3 = new HashMap();
		 hashmap3.put("title", "4.���������");
		 hashmap3.put("info", " ");
		//
		 arraylist.add(hashmap3);
		 HashMap hashmap4 = new HashMap();
		 hashmap4.put("title", "5.������");
		 hashmap4.put("info", "");
		
		 arraylist.add(hashmap4);
		//
		 HashMap hashmap5 = new HashMap();
		 hashmap5.put("title", "6.����С�ؼ�");
		 hashmap5.put("info", "");
		
		 arraylist.add(hashmap5);
		 HashMap hashmap6 = new HashMap();
		 hashmap6.put("title", "7.����������");
		 hashmap6.put("info", "");
		//
		 arraylist.add(hashmap6);
		// HashMap hashmap7 = new HashMap();
		// hashmap7.put("title", "8.���������");
		// hashmap7.put("info", "");
		//
		// arraylist.add(hashmap7);
		// HashMap hashmap8 = new HashMap();
		// hashmap8.put("title", "9.�����ҵ�����Ӧ��");
		// hashmap8.put("info", "");
		//
		// arraylist.add(hashmap8);
		// HashMap hashmap9 = new HashMap();
		// hashmap9.put("title", "10.������");
		// hashmap9.put("info", "");
		//
		// arraylist.add(hashmap9);
		// HashMap hashmap10 = new HashMap();
		// hashmap10.put("title", "11.��Ҳ�ѯ");
		// hashmap10.put("info", "");
		//
		// arraylist.add(hashmap10);
		// HashMap hashmap11 = new HashMap();
		// hashmap11.put("title", "12.�����Ƶ");
		// hashmap11.put("info", "");
		//
		// arraylist.add(hashmap11);
		// HashMap hashmap12 = new HashMap();
		// hashmap12.put("title", "13.����ģ����");
		// hashmap12.put("info", "");
		//
		// arraylist.add(hashmap12);
		// HashMap hashmap13 = new HashMap();
		// hashmap13.put("title", "14.����Ķ���־");
		// hashmap13.put("info", "");
		// hashmap13.put("img", Integer.valueOf(R.drawable.pvl1014));
		// arraylist.add(hashmap13);
		// HashMap hashmap14 = new HashMap();
		// hashmap14.put("title", "15.��Դ����");
		// hashmap14.put("info", "");
		// hashmap14.put("img", Integer.valueOf(R.drawable.pvl1015));
		// arraylist.add(hashmap14);
		// HashMap hashmap15 = new HashMap();
		// hashmap15.put("title", "16.�ɳ�����չ");
		// hashmap15.put("info", "");
		// hashmap15.put("img", Integer.valueOf(R.drawable.pvl1016));
		// arraylist.add(hashmap15);
		// HashMap hashmap16 = new HashMap();
		// hashmap16.put("title", "17.��");
		// hashmap16.put("info", "");
		// hashmap16.put("img", Integer.valueOf(R.drawable.pvl1017));
		// arraylist.add(hashmap16);
		// HashMap hashmap17 = new HashMap();
		// hashmap17.put("title", "18.��еЧ��");
		// hashmap17.put("info", "");
		// hashmap17.put("img", Integer.valueOf(R.drawable.pvl1018));
		// arraylist.add(hashmap17);
		// HashMap hashmap18 = new HashMap();
		// hashmap18.put("title", "19.����");
		// hashmap18.put("info", "");
		// hashmap18.put("img", Integer.valueOf(R.drawable.pvl1019));
		// arraylist.add(hashmap18);
		// HashMap hashmap19 = new HashMap();
		// hashmap19.put("title", "20.����");
		// hashmap19.put("info", "");
		// hashmap19.put("img", Integer.valueOf(R.drawable.pvl1020));
		// arraylist.add(hashmap19);
		// HashMap hashmap20 = new HashMap();
		// hashmap20.put("title", "21.����");
		// hashmap20.put("info", "");
		// hashmap20.put("img", Integer.valueOf(R.drawable.pvl1021));
		// arraylist.add(hashmap20);
		// HashMap hashmap21 = new HashMap();
		// hashmap21.put("title", "22.˫ͷ��");
		// hashmap21.put("info", " �׳ƣ�˫ͷ��   ��ƣ�THD");
		// hashmap21.put("img", Integer.valueOf(R.drawable.thd));
		// arraylist.add(hashmap21);
		// HashMap hashmap22 = new HashMap();
		// hashmap22.put("title", "23.��ҽ");
		// hashmap22.put("info", " �׳ƣ�51   ��ƣ�WD");
		// hashmap22.put("img", Integer.valueOf(R.drawable.wd));
		// arraylist.add(hashmap22);
		// HashMap hashmap23 = new HashMap();
		// hashmap23.put("title", "24.�����Ļ�");
		// hashmap23.put("info", " �׳ƣ�����   ��ƣ�AA");
		// hashmap23.put("img", Integer.valueOf(R.drawable.aa));
		// arraylist.add(hashmap23);
		// HashMap hashmap24 = new HashMap();
		// hashmap24.put("title", "25.�ڰ�����");
		// hashmap24.put("info", " �׳ƣ�����   ��ƣ�DS");
		// hashmap24.put("img", Integer.valueOf(R.drawable.ds));
		// arraylist.add(hashmap24);
		// HashMap hashmap25 = new HashMap();
		// hashmap25.put("title", "26.������֪");
		// hashmap25.put("info", " �׳ƣ�������   ��ƣ�DP");
		// hashmap25.put("img", Integer.valueOf(R.drawable.dp));
		// arraylist.add(hashmap25);
		// HashMap hashmap26 = new HashMap();
		// hashmap26.put("title", "27.��ħ��ʦ");
		// hashmap26.put("info", " �׳ƣ�����   ��ƣ�LION");
		// hashmap26.put("img", Integer.valueOf(R.drawable.lion));
		// arraylist.add(hashmap26);
		// HashMap hashmap27 = new HashMap();
		// hashmap27.put("title", "28.����");
		// hashmap27.put("info", " �׳ƣ�����   ��ƣ�ENI");
		// hashmap27.put("img", Integer.valueOf(R.drawable.eni));
		// arraylist.add(hashmap27);
		// HashMap hashmap28 = new HashMap();
		// hashmap28.put("title", "29.����");
		// hashmap28.put("info", " �׳ƣ�����   ��ƣ�LICH");
		// hashmap28.put("img", Integer.valueOf(R.drawable.lich));
		// arraylist.add(hashmap28);
		// HashMap hashmap29 = new HashMap();
		// hashmap29.put("title", "30.���鷨ʦ");
		// hashmap29.put("info", " �׳ƣ�����   ��ƣ�NEC");
		// hashmap29.put("img", Integer.valueOf(R.drawable.nec));
		// arraylist.add(hashmap29);
		// HashMap hashmap30 = new HashMap();
		// hashmap30.put("title", "31.������ʦ");
		// hashmap30.put("info", " �׳ƣ��Ƿ�   ��ƣ�PUGNA");
		// hashmap30.put("img", Integer.valueOf(R.drawable.pugna));
		// arraylist.add(hashmap30);
		// HashMap hashmap31 = new HashMap();
		// hashmap31.put("title", "32.���׻�����");
		// hashmap31.put("info", " �׳ƣ�����   ��ƣ�OD");
		// hashmap31.put("img", Integer.valueOf(R.drawable.od));
		// arraylist.add(hashmap31);
		// HashMap hashmap32 = new HashMap();
		// hashmap32.put("title", "33.ʹ��Ů��");
		// hashmap32.put("info", " �׳ƣ�����ɯ   ��ƣ�QOP");
		// hashmap32.put("img", Integer.valueOf(R.drawable.qop));
		// arraylist.add(hashmap32);
		// HashMap hashmap33 = new HashMap();
		// hashmap33.put("title", "34.��ʿ");
		// hashmap33.put("info", " �׳ƣ���ʿ   ��ƣ�WL");
		// hashmap33.put("img", Integer.valueOf(R.drawable.wl));
		// arraylist.add(hashmap33);
		// HashMap hashmap34 = new HashMap();
		// hashmap34.put("title", "35.��Ӱ��ħ");
		// hashmap34.put("info", " �׳ƣ�����   ��ƣ�SD");
		// hashmap34.put("img", Integer.valueOf(R.drawable.sd));
		// arraylist.add(hashmap34);
		// HashMap hashmap35 = new HashMap();
		// hashmap35.put("title", "36.ʹ��֮Դ");
		// hashmap35.put("info", " �׳ƣ�ʹ��   ��ƣ�BE");
		// hashmap35.put("img", Integer.valueOf(R.drawable.bane));
		// arraylist.add(hashmap35);
		//
		// HashMap hashmap36 = new HashMap();
		// hashmap36.put("title", "37.��ħ��ʦ");
		// hashmap36.put("info", " �׳ƣ���ħ��   ��ƣ�GM");
		// hashmap36.put("img", Integer.valueOf(R.drawable.gm));
		// arraylist.add(hashmap36);
		//
		// HashMap hashmap37 = new HashMap();
		// hashmap37.put("title", "38.������˹");
		// hashmap37.put("info", " �׳ƣ���ŭ��ʦ   ��ƣ�SM");
		// hashmap37.put("img", Integer.valueOf(R.drawable.tiannufashi));
		// arraylist.add(hashmap37);
		//
		// HashMap hashmap38 = new HashMap();
		// hashmap38.put("title", "39.����˹ ");
		// hashmap38.put("info", " �׳ƣ���������   ��ƣ�WW");
		// hashmap38.put("img", Integer.valueOf(R.drawable.ice_dragen));
		// arraylist.add(hashmap38);

		return arraylist;
	}

}
