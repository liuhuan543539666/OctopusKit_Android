package com.iapp.cart.adapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.iapp.R;

import com.iapp.base.IDataProvider;

public class JsonDataAdapter<T> extends DataAdapter {

	public static final java.lang.String LOG_TAG = "JsonDataAdapter";
 
	private JSONArray items ;

	public class ViewHolder {
		public String id = "";
		public TextView title;
		public ImageView icon;
	}

	@Override
	public int getCount() {
		return null== items ? 0 : items.length();
	}

	@Override
	public Object getItem(int position) {
		try {
			return null== items ? null : items.get(position);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//@step
		return null;
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
		JSONObject  record = (JSONObject) getItem(position);
		System.out.println(record);
		
		try {
			holder.title.setText(record.getString("name"));
		
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// holder.icon.setBackgroundResource(this.getResourceIdByIndex(position));
		holder.id = "" + position ;

		return rowView;
	}

	public void loadData(IDataProvider provider) {
		// @step
 		if (null != provider) {
			  provider.loadData(onSuccess, onError);
		}

	}

	private void setData(JSONArray  list){
		items = list;
		
		this.notifyDataSetChanged();
	}
	/*
	 * 
	 * 
	 */
	protected Response.Listener<String> onSuccess = new Response.Listener<String>() {

		@Override
		public void onResponse(String response) {
			Log.d(LOG_TAG, response);
			try {
				JSONArray list = new JSONArray(response);
				
				setData(list);
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};

	protected Response.ErrorListener onError = new Response.ErrorListener() {

		@Override
		public void onErrorResponse(VolleyError error) {
			if (null!= error){
				Log.d(LOG_TAG, error.getMessage()+"patched error");
				error.printStackTrace();
				}

			//@step
			setData(null);
		}
	};

}
