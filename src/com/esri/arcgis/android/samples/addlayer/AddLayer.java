/* Copyright 2010 ESRI
 * 
 * All rights reserved under the copyright laws of the United States
 * and applicable international laws, treaties, and conventions.
 * 
 * You may freely redistribute and use this sample code, with or
 * without modification, provided you include the original copyright
 * notice and use restrictions.
 * 
 * See the 锟絊ample code usage restrictions锟�document for further information.
 * 
 */
package com.esri.arcgis.android.samples.addlayer;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.esri.android.map.GraphicsLayer;
import com.esri.android.map.Layer;
import com.esri.android.map.MapView;
import com.esri.android.map.ags.ArcGISTiledMapServiceLayer;
import com.esri.android.map.ags.ArcGISDynamicMapServiceLayer;
import com.esri.android.map.event.OnSingleTapListener;
import com.esri.android.map.event.OnStatusChangedListener;
import com.esri.android.map.event.OnLongPressListener;
import com.esri.android.map.event.OnStatusChangedListener.STATUS;
import com.esri.core.geometry.Envelope;
import com.esri.core.geometry.Point;
import com.esri.core.geometry.SpatialReference;
import com.esri.core.map.FeatureSet;
import com.esri.core.map.Graphic;
import com.esri.core.renderer.SimpleRenderer;
import com.esri.core.symbol.SimpleFillSymbol;
import com.esri.core.tasks.ags.identify.IdentifyParameters;
import com.esri.core.tasks.ags.query.Query;
import com.esri.core.tasks.ags.query.QueryTask;
/**
 * Adds a layer statically and dynamically and toggles the visibility of top layer 
 * with a single tap 
 *
 */
public class AddLayer extends Activity {
	
	private MapView map = null;
	TextView label = null;
	public static final int ITEM0 = Menu.FIRST;
	public static final int ITEM1 = Menu.FIRST + 1;
	Button button1;
	Button button2;
	Button button3;
	IdentifyParameters params;
	//Dynamic layer URL from ArcGIS online
	String dynamicURL = "http://58.198.173.41:8399/arcgis/rest/services/ZJZH/MapServer";
	int usaLayerId;
	GraphicsLayer gl;
	Graphic graphic;
	Graphic fillGraphic;
	Button querybt;
	boolean blQuery = true;
	ProgressDialog progress;
	OnClickListener listener1 = null;
	final static int HAS_RESULTS = 1;
	final static int NO_RESULT = 2;
	final static int CLEAR_RESULT = 3;
	
	@SuppressWarnings("serial")
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		listener1 = new OnClickListener() {
        	public void onClick(View v) {
        		Intent intent1 = new Intent(AddLayer.this, Search.class);
        		startActivity(intent1);
        	}
        };
		// Retrieve the map and initial extent from XML layout
		map = (MapView)findViewById(R.id.map);
		map.setExtent(new Envelope(117.84855183800885,27.432551876029365,126.18888138947315,32.07917131603));
		map.addLayer(new ArcGISTiledMapServiceLayer("http://server.arcgisonline.com/ArcGIS/rest/services/ESRI_Imagery_World_2D/MapServer"));
		
		//Creates a dynamic layer using service URL 
		ArcGISDynamicMapServiceLayer dynamicLayer = new ArcGISDynamicMapServiceLayer(dynamicURL);
		//Adds layer into the 'MapView'
		map.addLayer(dynamicLayer);
		this.usaLayerId = 1234;
		label = (TextView) findViewById(R.id.label);
		
		//按钮设置
		button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);
		button1.setVisibility(View.INVISIBLE);
		button2.setVisibility(View.INVISIBLE);
		Toast.makeText(this, "Long Press to check coordination",
				Toast.LENGTH_SHORT).show();		
		
		button3=(Button)findViewById(R.id.button3);
        button3.setOnClickListener(listener1);
		

		map.setOnLongPressListener(new OnLongPressListener() {
			public void onLongPress(float x, float y) {
				Point pt = map.toMapPoint(x, y); 
				
				label.setText("X:"+pt.getX() + " Y:" + pt.getY()); 
				} 
			});
		

	}
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(0, ITEM0, 0, "属性查询");
		menu.add(0, ITEM1, 0, "空间查询");
		menu.findItem(ITEM1);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case ITEM0: 
			actionClickMenuItem1();
		break;
		case ITEM1: 
			actionClickMenuItem2(); break;

		}
		return super.onOptionsItemSelected(item);}
	/*
	 * menu.first
	 */
	private void actionClickMenuItem1(){
		setTitle("属性查询");
		button1.setVisibility(View.VISIBLE);
		button2.setVisibility(View.INVISIBLE);
		//事件处理
	}
	/*
	 *menu.first+1
	 */
	private void actionClickMenuItem2(){
		setTitle("空间查询");
		button1.setVisibility(View.INVISIBLE);
		button2.setVisibility(View.VISIBLE);
		//event
		//this.map.
	}
    

}