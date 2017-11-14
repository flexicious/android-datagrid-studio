package com.flexicious.androidcomponentstest.sampleviews;

import java.util.List;

import android.os.Bundle;

import com.flexicious.androidcomponentstest.R;
import com.flexicious.controls.core.ClassFactory;
import com.flexicious.controls.core.Function;
import com.flexicious.example.serviceproxies.BusinessService;
import com.flexicious.nestedtreedatagrid.events.FlexDataGridEvent;


public class LevelItemRenderers extends ExampleActivityBase{
	@Override
	protected void onCreate(Bundle savedInstanceState) {		 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.example_levelitem_renderers);
		this.buildGrid(this.flexDataGrid, R.raw.flxslevelrenderers);
	}
	
	public void levelRenderers_creationCompleteHandler(FlexDataGridEvent ns)
	{
	    //evt1 = (ExtendedFilterPageSortChangeEvent*)[ ns.userInfo objectForKey:"event"];
	
	     BusinessService.getInstance().getDeepOrgList(new Function(this,"getDeepOrgList_result"),null);
	}
	
	public ClassFactory levelRenderers_getNextLevelRenderer()
	{
		ClassFactory classFactory = new ClassFactory(com.flexicious.androidcomponentstest.sampleviews.supportingviews.SampleLevelRendererView.class);
		return classFactory;
	    /*return [[ClassFactory alloc] initWithNibName:"SampleLevelRendererViewController"
	                           andControllerClass:[SampleLevelRendererViewController class]
	                               withProperties:nil];
*/	
	}
	
	public void getDeepOrgList_result(final List<Object> result)
	{
		this.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				flexDataGrid.setDataProvider(result);
				
			}
		});
	    
	}
} 
