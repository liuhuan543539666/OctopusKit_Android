package com.iapp.cart.ui;

import org.json.JSONObject;

import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.iapp.R;
import com.iapp.base.BaseActivity;
import com.iapp.cart.AppConfig;
import com.iapp.cart.adapter.CategoriesAdapter;
import com.iapp.cart.model.CategoryModel;
import com.iapp.utils.JSONHelper;

import eu.erikw.PullToRefreshListView;
import eu.erikw.PullToRefreshListView.OnRefreshListener;

public class CategoriesActivity extends BaseActivity {
	
	private PullToRefreshListView listView;
	private CategoriesAdapter adapter;
	private CategoryModel mModel;
	
	private String getIntentStringValue(String name){
		Intent intent = getIntent();
		return intent.getStringExtra(name);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.categories);
		
		this.setupListView();
	}
	
	private void setupListView( ) {

		adapter = new CategoriesAdapter();
				
		listView = (PullToRefreshListView) this
				.findViewById(R.id.pull_to_refresh_listview);

		// OPTIONAL: Disable scrolling when list is refreshing
		// listView.setLockScrollWhileRefreshing(false);

		// OPTIONAL: Uncomment this if you want the Pull to Refresh header to
		// show the 'last updated' time
		// listView.setShowLastUpdatedText(true);

		// OPTIONAL: Uncomment this if you want to override the date/time format
		// of the 'last updated' field
		// listView.setLastUpdatedDateFormat(new
		// SimpleDateFormat("yyyy/MM/dd HH:mm:ss"));

		// OPTIONAL: Uncomment this if you want to override the default strings
		//listView.setTextPullToRefresh("");
		//listView.setTextReleaseToRefresh("");
		//listView.setTextRefreshing("refreshing...");
		listView.setTextColor(Color.BLACK);
		// MANDATORY: Set the onRefreshListener on the list. You could also use
		// listView.setOnRefreshListener(this); and let this Activity
		// implement OnRefreshListener.
		listView.setOnRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh() {
				// Your code to refresh the list contents goes here

				// for example:
				// If this is a webservice call, it might be asynchronous so
				// you would have to call listView.onRefreshComplete(); when
				// the webservice returns the data
				
				startRefresh();

		 
			}

		});

		// @step

		// @step
	 
		adapter.ownerActivity = this;
		try{
			adapter.unregisterDataSetObserver(this.mObserver);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			adapter.registerDataSetObserver(this.mObserver);
		}
		
		listView.setAdapter(adapter);
		
		// Request the adapter to load the data
		// @adapter.loadData();
		this.startRefresh();
		// click listener
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {

				// ViewHolder viewHolder = (ViewHolder) arg1.getTag();
				// if (viewHolder.title != null){
				// Toast.makeText(UserCenterActivity.this,
				// viewHolder.title.getText(), Toast.LENGTH_SHORT).show();
				// }
				// String Id = viewHolder.id;
				// int index = Integer.parseInt(Id);
				// onSelectRow(index);
				JSONObject json = (JSONObject) adapter.getItem(position);
				String url = JSONHelper.getString(json, "href");
				
				 
			}
		});

	}
	
	public void startRefresh() {
		
		String categoryId = this.getIntentStringValue(AppConfig.FieldDef.CategoryId);
		this.adapter.loadData(categoryId);
		 
	}

	public void onRefreshDone() {
		// @step
		listView.onRefreshComplete();
	}

	DataSetObserver mObserver = new DataSetObserver(){
		public void onChanged(){
			onRefreshDone();
		}
	};
}
