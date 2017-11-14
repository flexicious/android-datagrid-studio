package com.flexicious.androidcomponentstest.sampleviews;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.Format;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import com.flexicious.androidcomponentstest.R;
import com.flexicious.androidcomponentstest.sampleviews.supportingviews.StyleManager;
import com.flexicious.controls.core.Function;
import com.flexicious.controls.core.TimerEvent;
import com.flexicious.controls.interfaces.IPager;
import com.flexicious.nestedtreedatagrid.FlexDataGrid;
import com.flexicious.nestedtreedatagrid.FlexDataGridColumn;
import com.flexicious.nestedtreedatagrid.events.FlexDataGridEvent;
import com.flexicious.nestedtreedatagrid.pager.PagerControlAS;
import com.flexicious.nestedtreedatagrid.utils.ExtendedUIUtils;
import com.flexicious.utils.UIUtils;
/**
 * Activity that provides a number of reuseable methods for all the examples
 * @author flexicious
 */
public class ExampleActivityBase extends Activity {
	FlexDataGrid flexDataGrid;
	Function faultHandler = new Function(this, "onFault");
	/**
	 * Generic fault handler
	 * @param fault
	 */
	public void onFault(Object fault) {

	}

	public void setContentView(int layoutResID) {
		super.setContentView(layoutResID);
		this.flexDataGrid = (FlexDataGrid) findViewById(R.id.flexDataGrid1);
		Intent intent = getIntent();
	    String title = intent.getStringExtra("title");
	    if(title!=null){
	    	this.setTitle(title);
	    }
		applyTheme();
	}
	/**
	 * Takes a grid, and an XML configuration, loads the XML configuration into the Grid.
	 * @param grid
	 * @param resource
	 */
	public void buildGrid(FlexDataGrid grid, Integer resource) {
		grid.delegate = this;
		BufferedReader reader = new BufferedReader(new InputStreamReader(this
				.getResources().openRawResource(resource)));
		StringBuilder builder = new StringBuilder();
		String aux = "";
		try {
			while ((aux = reader.readLine()) != null) {
				builder.append(aux);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		String text = builder.toString();
		flexDataGrid.buildFromXml(text);
	}
	/**
	 * Parses json data and passes it to the grid to display
	 * @param grid
	 * @param resource
	 */
	public void loadJsonData(FlexDataGrid grid, Integer resource) {
		BufferedReader jsonReader = new BufferedReader(new InputStreamReader(
				this.getResources().openRawResource(resource)));
		StringBuilder jsonBuilder = new StringBuilder();
		try {
			for (String line = null; (line = jsonReader.readLine()) != null;) {
				jsonBuilder.append(line).append("\n");
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		// Parse Json
		this.flexDataGrid.setDataProviderJson(jsonBuilder.toString());
	}
	/**
	 * Converts XML to JSON, and calls loadJsonData
	 * @param grid
	 * @param resource
	 * @return
	 * @throws org.json.JSONException
	 */
	public JSONObject convertXmlToJson(FlexDataGrid grid, Integer resource)
			throws JSONException {
		BufferedReader xmlReader = new BufferedReader(new InputStreamReader(
				this.getResources().openRawResource(resource)));
		StringBuilder xmlBuilder = new StringBuilder();
		try {
			for (String line = null; (line = xmlReader.readLine()) != null;) {
				xmlBuilder.append(line).append("\n");
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		JSONObject json = XML.toJSONObject(xmlBuilder.toString());
		return json;
	}
	/**
	 * A Generic labelfunction for currency formatting.
	 * @param rowData
	 * @param col
	 * @return
	 */
	public String dataGridFormatCurrencyLabelFunction(Object rowData,
			FlexDataGridColumn col) {
		String string = UIUtils.toString(ExtendedUIUtils.resolveExpression(
				rowData, col.getDataField(), null, false, false));
		if (string.length() == 0)
			return "";
		return UIUtils.formatCurrency(Float.parseFloat(string), "");
	}
	/**
	 * A generic label function for date formatting
	 * @param rowData
	 * @param col
	 * @return
	 */
	public String dataGridFormatDateLabelFunction(Object rowData,
			FlexDataGridColumn col) {

		return UIUtils.formatDate(
				(Date) ExtendedUIUtils.resolveExpression(rowData,
						col.getDataField(), null, false, false), "MM-dd-yyyy");
	}
	/**
	 * Reusable currency formatter.
	 * @return
	 */
	public Format getCurrencyFormatter() {
		NumberFormat cf = NumberFormat.getCurrencyInstance();
		cf.setCurrency(Currency.getInstance(Locale.US));
		cf.setMaximumFractionDigits(2);
		return cf;
	}

	// end some generic data formatting functions

	// adding support to autofit left locked columns
	Boolean set = false;

	public void onPlacingSectionsHandler(FlexDataGridEvent event) {
		// by default, locked columns do not support column width mode
		// attribute. In this section, we calculate the locked section widths
		// dynamically
		// by adding support for locked modes.
		FlexDataGrid grid = this.flexDataGrid;
		// a demonstration of a new 2.9 feature on how to add column width mode
		// = fit to content to left locked columns
		if (grid.getDataProvider() != null && !set) {
			for (FlexDataGridColumn col : grid.getColumnLevel()
					.getLeftLockedColumns()) {
				if (col.getColumnWidthMode().equals(
						FlexDataGridColumn.COLUMN_WIDTH_MODE_FIT_TO_CONTENT))
					grid.getColumnLevel().applyColumnWidthFromContent(col,
							grid.getDataProvider());
			}
			set = true;// do this only once otherwise user will not be able to
						// resize.
		}

	}

	public void autoRefreshHandler(FlexDataGridEvent event) {
		// for now we just show the spinner but in reality we would go back to
		// the server
		// grid = ((FlexDataGridEvent *)[ns.userInfo
		// objectForKey:"event"]).grid;
		//
		// [NSTimer scheduledTimerWithTimeInterval:2
		// target:self
		// selector,new Function(this,"onTimer:)
		// userInfo:nil
		// repeats,false);

	}

	public void onTimer(TimerEvent timer) {
		this.flexDataGrid.getBodyContainer().hideRefreshControl();
		// grid= null;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public static int theme=R.id.default_theme;

	@Override
	/**
	 * Theme change handler
	 */
	public boolean onOptionsItemSelected(MenuItem item) {
		if(!item.getTitle().equals("Themes")){
			ExampleActivityBase.theme = item.getItemId();
			applyTheme();
			if(this.flexDataGrid != null)
			this.flexDataGrid.rebuild();
		}
		return super.onOptionsItemSelected(item);
	}
	/**
	 * Applies the current theme to the grid.
	 */
	private void applyTheme() {
		if(this.flexDataGrid == null){
			return;
		}
		if(theme == -1){
			return;
		}
		FlexDataGrid grid = this.flexDataGrid;

		StyleManager.instance().applyDefaultStyles(grid);
		switch (ExampleActivityBase.theme) {
		case R.id.default_theme:
			StyleManager.instance().applyDefaultStyles(grid);
			break;
		case R.id.officeblue_theme:
			StyleManager.instance().applyOfficeBlueStyle(grid);
			break;
		case R.id.officegray_theme:
			StyleManager.instance().applyOfficeGrayStyle(grid);
			break;
		case R.id.officeblack_theme:
			StyleManager.instance().applyOfficeBlackStyle(grid);
			break;
		case R.id.androidgray_theme:
			StyleManager.instance().applyAndroidGrayStyle(grid);
			break;
		case R.id.applegray_theme:
			StyleManager.instance().applyGrayStyle(grid);
			break;
		case R.id.appleivory_theme:
			StyleManager.instance().applyIvoryStyle(grid);
			break;
		case R.id.greencolorful_theme:
			StyleManager.instance().applyGreenColorfulStyle(grid);
			break;
		case R.id.pinkcolorful_theme:
			StyleManager.instance().applyPinkColorfulStyles(grid);
			break;
		case R.id.itunes_theme:
			StyleManager.instance().applyITunesStyles(grid);
			break;
		case R.id.ios7_theme:
			StyleManager.instance().applyMINIMALStyle(grid);
			break;

		default:
			break;
		}
		IPager pager = grid.getPager();
		if(pager instanceof PagerControlAS)
		((PagerControlAS) pager).reBuild();
		grid.invalidate();
	}
}
