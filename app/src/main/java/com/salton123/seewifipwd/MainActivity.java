package com.salton123.seewifipwd;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private ListView list;
	private PwdAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		list = (ListView) findViewById(R.id.list);
		mAdapter = new PwdAdapter(MainActivity.this);
		list.setAdapter(mAdapter);
		initData();
	}

	private void initData() {
		WifiPsd();
	}

	List<Network> dataList;
	final String cmd = "cat /data/misc/wifi/*.conf";
	String out = null;

	private void WifiPsd() {
		// 获取root并得到wifi列表
		boolean flag;
		// 首先取得root权限
		// flag=RootCmd.haveRoot();
		// flag=RootCmd.getRootAhth();
		flag = RootCmd.isRootSystem();
		// 然后读取/data/misc/wifi/wpa_supplicant.conf文件
		if (flag) {
			// new Thread(){
			// @SuppressWarnings("deprecation")
			// public void run()
			// {
				StringBuffer sb = new StringBuffer();
				DataInputStream dis = RootCmd.execRootCmd(cmd);
				try {
					mAdapter.AddAll(Parser.getNetworks(InputStreamTOString(dis,"UTF-8")));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}else{
			Toast.makeText(getApplicationContext(), "请先Root你的手机，再使用本服务！", 1).show();
		}
	}
	
    public static String InputStreamTOString(DataInputStream in,String encoding) throws Exception{  
        
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
        byte[] data = new byte[8192];  
        int count = -1;  
        while((count = in.read(data,0,8192)) != -1)  
            outStream.write(data, 0, count);  
        data = null;  
        return new String(outStream.toByteArray(),encoding);  
    }  
}
