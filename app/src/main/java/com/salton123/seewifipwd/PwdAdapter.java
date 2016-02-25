package com.salton123.seewifipwd;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PwdAdapter extends AdapterBase<Network>{

	public PwdAdapter(Context pContext) {
		super(pContext);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(convertView ==null){
			convertView = GetLayoutInflater().inflate(R.layout.list_item, null);
		}
		TextView pskTv = ViewHolder.get(convertView, R.id.pskTv);
		TextView ssidTv = ViewHolder.get(convertView, R.id.ssidTv);
		Network _Network = GetList().get(position);
		pskTv.setText(_Network.getPsk());
		ssidTv.setText(_Network.getSsid());
		return convertView;
	}

}
