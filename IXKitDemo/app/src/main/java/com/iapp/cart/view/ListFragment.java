package com.iapp.cart.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.iapp.R;
import com.iapp.base.IDataProvider;
import com.iapp.cart.adapter.DataAdapter;

import eu.erikw.PullToRefreshListView;
import eu.erikw.PullToRefreshListView.OnRefreshListener;

public class ListFragment extends Fragment {
	/**
	 * The fragment argument representing the section number for this fragment.
	 */
	private static final String ARG_SECTION_NUMBER = "section_number";

	private PullToRefreshListView listView;
	private DataAdapter adapter;

	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	public static ListFragment newInstance(int sectionNumber) {
		ListFragment fragment = new ListFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public ListFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// View rootView = inflater.inflate(R.layout.fragment_home, container,
		// false);
		View rootView = inflater.inflate(R.layout.list_fragment, container,
				false);

		this.setupListView(rootView);

		return rootView;
	}

	private void setupListView(View v) {

		listView = (PullToRefreshListView) v
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

				// Make sure you call listView.onRefreshComplete()
				// when the loading is done. This can be done from here or any
				// other place, like on a broadcast receive from your loading
				// service or the onPostExecute of your AsyncTask.

				// For the sake of this sample, the code will pause here to
				// force a delay when invoking the refresh
				listView.postDelayed(new Runnable() {

					@Override
					public void run() {
						listView.onRefreshComplete();
					}
				}, 50);

			}

		});

		// @step

		// @step
		adapter = new DataAdapter() ;
		adapter.ownerActivity = this.getActivity();
		listView.setAdapter(adapter);

		// Request the adapter to load the data
		// @adapter.loadData();
		this.startRefresh();
		// click listener
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				// ViewHolder viewHolder = (ViewHolder) arg1.getTag();
				// if (viewHolder.title != null){
				// Toast.makeText(UserCenterActivity.this,
				// viewHolder.title.getText(), Toast.LENGTH_SHORT).show();
				// }
				// String Id = viewHolder.id;
				// int index = Integer.parseInt(Id);
				// onSelectRow(index);
			}
		});

	}

	public IDataProvider getDataProvider() {
		return dataProvider;
	}

	public void setDataProvider(IDataProvider dataProvider) {
		this.dataProvider = dataProvider;
	}

	private IDataProvider dataProvider;
	
	public void startRefresh() {
		
		adapter.loadData(this.getDataProvider());

		// Make sure you call listView.onRefreshComplete()
		// when the loading is done. This can be done from here or any
		// other place, like on a broadcast receive from your loading
		// service or the onPostExecute of your AsyncTask.

		// For the sake of this sample, the code will pause here to
		// force a delay when invoking the refresh
		listView.postDelayed(new Runnable() {

			@Override
			public void run() {
				listView.onRefreshComplete();
			}
		}, 50);

	}

	public void onRefreshDone() {
		// @step

	}
}
