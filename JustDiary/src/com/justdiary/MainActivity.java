package com.justdiary;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.TabHost;
import android.widget.Toast;

public class MainActivity extends Activity {

	 ViewPager pager; //ViewPager 참조변수
	 ActionBar actionBar;  //ActionBar 참조변수
	 TabHost host;
	 CalendarView cal;
	 Intent i = null;
		
	 @Override
	 protected void onCreate(Bundle savedInstanceState) {
		 
		  super.onCreate(savedInstanceState);
		  setContentView(R.layout.activity_main);
		  
		  //Activity 객체는 이미 ActionBar를 가지고 있으므로
		  //이미 존해하는 ActionBar 객체를 얻어오기.(API 10버전 이상)
		  actionBar= getActionBar();      
		  //ActionBar가 Tab를 보여줄 수 있는 모양이 되도록 설정
		  actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		  
		  
		  //ViewPager 객체 참조
		  pager= (ViewPager)findViewById(R.id.pager);
		      
		  //ViewPager에 설정할 Adapter 객체 생성
		  //ListView에서 사용하는 Adapter와 같은 역할.
		  //다만. ViewPager로 스크롤 될 수 있도록 되어 있다는 것이 다름
		  //PagerAdapter를 상속받은 CustomAdapter 객체 생성
		  //CustomAdapter에게 LayoutInflater 객체 전달
		  CustomAdapter adapter= new CustomAdapter(getLayoutInflater());
		    
		  //ViewPager에 Adapter 설정
		  pager.setAdapter(adapter);
		      
		  //ViewPager에게 Page의 변경을 인지하는 Listener 세팅.
		  //마치 클릭상황을 인지하는 OnClickListener와 같은 역할..
		  pager.setOnPageChangeListener(new OnPageChangeListener() {		       
			   //Page가 일정부분 넘겨져서 현재 Page가 바뀌었을 때 호출
			   //이전 Page의 80%가 이동했을때 다음 Page가 현재 Position으로 설정됨.
			   //파라미터 : 현재 변경된 Page의 위치
			   @Override
			   public void onPageSelected(int position) {
				    // TODO Auto-generated method stub
				        
				    //ViewPager는 3개의 View를 가지고 있도록 설계하였으므로.
				    //Position도 역시 가장 왼쪽 처음부터(0,1,2 순으로 되어있음)
				    //현재 전면에 놓여지는 ViewPager의 Page에 해당하는 Position으로
				    //ActionBar의 Tab위치를 변경.
				    actionBar.setSelectedNavigationItem(position);
			   }
			       
			   @Override
			   public void onPageScrolled(int arg0, float arg1, int arg2) {
				   // TODO Auto-generated method stub
			        
			   }
			       
			   @Override
			   public void onPageScrollStateChanged(int arg0) {
				   // TODO Auto-generated method stub
			        
			   }
		  });  
		  
		     
		  //ActionBar에 추가 될 Tab 참조변수
		  Tab tab = null;
		    
		  //첫번째 Tab 객체 생성 및 ActionBar에 추가하기
		  tab = actionBar.newTab(); //ActionBar에 붇는 Tab객체 생성
		  tab.setText("Analog");    //Tab1에 보여지는 글씨  
		  //Tab의 선택이 변경되는 것을 인지하는 TabListener 설정(아래쪽 객체 생성 코드 참고)
		  tab.setTabListener(listener);   
		  //ActionBar에 Tab 추가
		  actionBar.addTab(tab);
		    
		  //두번째 Tab 객체 생성 및 ActionBar에 추가하기
		  tab = actionBar.newTab(); //ActionBar에 붇는 Tab객체 생성
		  tab.setText("Calendar");     //Tab2에 보여지는 글씨    
		  //Tab의 선택이 변경되는 것을 인지하는 TabListener 설정(아래쪽 객체 생성 코드 참고)
		  tab.setTabListener(listener);    
		  //ActionBar에 Tab 추가
		  actionBar.addTab(tab);
		    
		  //세번째 Tab 객체 생성 및 ActionBar에 추가하기
		  tab = actionBar.newTab(); //ActionBar에 붇는 Tab객체 생성
		  tab.setText("Diary");   //Tab3에 보여지는 글씨    
		  //Tab의 선택이 변경되는 것을 인지하는 TabListener 설정(아래쪽 객체 생성 코드 참고)
		  tab.setTabListener(listener);     
		  //ActionBar에 Tab 추가
		  actionBar.addTab(tab);
	    
	  
	 }//onCreate Method...
	 
	 
	 //ActionBar의 Tab 선택에 변화가 생기는 것을 인지하는 리스너(Listener)
	 TabListener listener= new TabListener() {
	  
		  //Tab의 선택이 벗어날 때 호출
		  //첫번째 파라미터 : 선택에서 벗어나는 Tab 객체
		  //두번째 파라미터 : Tab에 해당하는 View를 Fragment로 만들때 사용하는 트랜젝션.(여기서는 사용X)
		  @Override
		  public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			   // TODO Auto-generated method stub
		    
		  }
		    
		  //Tab이 선택될 때 호출
		  //첫번째 파라미터 : 선택된 Tab 객체
		  //두번째 파라미터 : Tab에 해당하는 View를 Fragment로 만들때 사용하는 트랜젝션.(여기서는 사용X)
		  @Override
		  public void onTabSelected(Tab tab, FragmentTransaction ft) {
			  // TODO Auto-generated method stub
			  
			  //선택된 Tab객체의 위치값(왼족 처음부터 0,1,2....순으로 됨)
			  int position = tab.getPosition();
			   
    		   //Tab의 선택 위치에 따라 ViewPager에서 보여질 Item(View)를 설정
			   //첫번째 파라미터: ViewPager가 현재 보여줄 View의 위치
			   //두번째 파라미터: 변경할 때 부드럽게 이동하는가? false면 팍팍 바뀜
			   pager.setCurrentItem(position,true);     			   
			   
			   Toast.makeText(getBaseContext(), "tab->" + tab.getPosition(), Toast.LENGTH_LONG).show();
			   
			   switch(tab.getPosition()){
				       case 0:
						   break;
						   
					   case 1:
						   
						   cal = (CalendarView) findViewById(R.id.calendarView01);
					        
					       cal.setOnDateChangeListener(new OnDateChangeListener() {
					    	   @Override
					    	   public void onSelectedDayChange(CalendarView view, int year, int month,
					    			   int dayOfMonth) {
								// TODO Auto-generated method stub
								
    							Toast.makeText(getBaseContext(),"Selected Date is\n\n"
									+dayOfMonth+" : "+month+" : "+year , 
									Toast.LENGTH_LONG).show();
	    						}
					       });
					        
                           i = new Intent(getApplicationContext(), NoteEdit.class);
					        
					       break;
					   case 2:
						   break;
						   
					   default:
						   break;
			   }
					        	
			   
		  }
		    
		  //Tab이 재 선택될 때 호출
		  //첫번째 파라미터 : 재 선택된 Tab 객체
		  //두번째 파라미터 : Tab에 해당하는 View를 Fragment로 만들때 사용하는 트랜젝션.(여기서는 사용X)
		  @Override
		  public void onTabReselected(Tab tab, FragmentTransaction ft) {
		   // TODO Auto-generated method stub
		     
		  }
	 }; 
	 
}
