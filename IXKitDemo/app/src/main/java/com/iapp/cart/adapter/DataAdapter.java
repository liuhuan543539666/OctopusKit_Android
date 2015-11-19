package com.iapp.cart.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.iapp.R;


import com.iapp.base.IDataProvider;

public class DataAdapter<T> extends BaseAdapter {

	public static final java.lang.String LOG_TAG = "DataAdapter";

	public Activity ownerActivity;

	private ArrayList<T> items = new ArrayList<T>();

	public class ViewHolder {
		public String id = "";
		public TextView title;
		public ImageView icon;
	}

	@Override
	public int getCount() {
		return items.size();
		
	}

	@Override
	public Object getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	private final static int indexOfId = 0;
	private final static int indexOfTitle = 1;
	private final static int indexOfImage = 2;

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		// @step
		 
		View rowView = convertView;
		// @step
		Object[] record = (Object[]) getItem(position);
		System.out.println(record);
		LayoutInflater inflater = this.ownerActivity.getLayoutInflater();

		ViewHolder holder = null;

		if (rowView == null) {
			rowView = inflater.inflate(R.layout.list_row, null);
			holder = new ViewHolder();
			rowView.setTag(holder);
		}

		holder = (ViewHolder) rowView.getTag();
		holder.title = (TextView) rowView.findViewById(R.id.text_title);
		holder.icon = (ImageView) rowView.findViewById(R.id.icon_left);

		// @step

		holder.title.setText((String) record[indexOfTitle]);
		// holder.icon.setBackgroundResource(this.getResourceIdByIndex(position));
		holder.id = (String) record[indexOfId];

		return rowView;
	}

	public void loadData(IDataProvider provider) {
		// @step
		items = null;// new ArrayList<String>();

		if (null != provider) {
			items = (ArrayList<T>) provider.loadData(null,null);
		}

	}

	 

}
