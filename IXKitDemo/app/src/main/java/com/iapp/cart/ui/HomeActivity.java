package com.iapp.cart.ui;

import java.util.ArrayList;
import java.util.Locale;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.iapp.R;
import com.iapp.cart.AppManager;
import com.iapp.cart.adapter.JsonDataAdapter;
import com.iapp.cart.adapter.SettingDataProvider;
import com.iapp.cart.model.CategoryModel;
import com.iapp.cart.view.DataListFragment;
import com.iapp.cart.view.ListFragment;
 
import net.ixkit.octopus.core.DataManager;

public class HomeActivity extends ActionBarActivity implements
		ActionBar.TabListener {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a {@link FragmentPagerAdapter}
	 * derivative, which will keep every loaded fragment in memory. If this
	 * becomes too memory intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_home);

		// Set up the action bar.
		final ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
	 
		// Create the adapter that will return a fragment for each of the three
		// primary sections of the activity.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
				});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by
			// the adapter. Also specify this Activity object, which implements
			// the TabListener interface, as the callback (listener) for when
			// this tab is selected.
			actionBar.addTab(actionBar.newTab()
					.setText(mSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}
		// @step
		try{
			DataManager.getInstance().registerContext(this);
			AppManager.getInstance().setContext(this);
			AppManager.getInstance().start();
		
			this.setUpActionBar();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	protected void onDestroy() {   
		Log.d("Home", isFinishing() + ""); 
		super.onDestroy();  
	}
//	private void setupActionBar() {
//	    ActionBar actionBar = getSupportActionBar();
//
//	    ViewGroup v = (ViewGroup)LayoutInflater.from(this)
//	        .inflate(R.layout.conversation_list_actionbar, null);
//	    actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM,
//	            ActionBar.DISPLAY_SHOW_CUSTOM);
//	    actionBar.setCustomView(v,
//	            new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT,
//	                    ActionBar.LayoutParams.WRAP_CONTENT,
//	                    Gravity.CENTER_VERTICAL | Gravity.RIGHT));
//
//	  //  mUnreadConvCount = (TextView)v.findViewById(R.id.unread_conv_count);
//	}
	//@Override
	public boolean onCreateOptionsMenu_discard(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_home, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		// noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {

	 
	}

	static final String FRAGMENTS[] = {
		DataListFragment.class.getName(),
	    ListFragment.class.getName()
	};
	
	ArrayList<Fragment> frameList = null;
	Fragment getItemFrame(int position){
		if (null == frameList){
			return null;
		}else
			return frameList.get(position);
	}
	void addItemFrame(Fragment item){
		if (null == frameList){
			frameList = new ArrayList<Fragment>();
		}
		frameList.add(item);
	}
	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		private Fragment createCategoryFrame(){
			JsonDataAdapter adapter = new JsonDataAdapter();
			DataListFragment listFrame = DataListFragment.newInstance("Categories");;
			listFrame.setAdapter(adapter);
			CategoryModel model = new CategoryModel();
			
			listFrame.setDataProvider(model);
			
			return listFrame;
		}
		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a PlaceholderFragment (defined as a static inner class
			// below).
			// @ return PageFragment.newInstance(position + 1);
			//ListFragment frame = (ListFragment)getItemFrame(position);
			//ListFragment frame =  ListFragment.newInstance(position + 1);
			Fragment frame = null;
			if (0 == position){
				frame = getItemFrame(0);
				if (null == frame){
					frame = this.createCategoryFrame();
					addItemFrame(frame);
				}
				
				
			}
			//@case
			if (0 != position)
			{
				ListFragment listFrame =  ListFragment.newInstance(position + 1);
				  SettingDataProvider dataProvider = new SettingDataProvider();
				  listFrame.setDataProvider(dataProvider);
				  
				  frame = listFrame;
			}
			//@step
			
			return frame;
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 3;
		}

		@Override
		public String getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return "Categories";
			case 1:
				return "Orders";
			case 2:
				return "Settings";
			}
			return null;
		}
	}
	private void setUpActionBar() {
		//if (1>0)return;
		
        final ActionBar actionBar = this.getSupportActionBar(); 
        actionBar.setHomeButtonEnabled(false);  
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);  
        actionBar.setDisplayShowTitleEnabled(false);  
        actionBar.setDisplayShowHomeEnabled(false);  
    }  
      
}
