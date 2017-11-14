////
////  iPadTraderViewViewController.m
////  IOSComponentsSample
////
////  Created by Flexicious-110 on 7/10/13.
////  Copyright (c) 2013 ___IOSComponents___. All rights reserved.
////
package com.flexicious.androidcomponentstest.sampleviews;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ToggleButton;

import com.flexicious.androidcomponentstest.R;
import com.flexicious.androidcomponentstest.sampleviews.supportingviews.StyleManager;
import com.flexicious.example.FlexiciousMockGenerator;
import com.flexicious.nestedtreedatagrid.events.FlexDataGridEvent;
import com.flexicious.nestedtreedatagrid.interfaces.IFlexDataGridCell;
import com.flexicious.nestedtreedatagrid.valueobjects.RowInfo;

public class TraderView extends ExampleActivityBase {

	ToggleButton toggleButton;
	ArrayList<StockTicker> stocks;
	int repeatRate=12;
	Timer timer;

	public class StockTicker {
		public Integer ident;
		public String ticker;
		public String name;
		public float last;
		public float change;
		public boolean tickUp;

		public StockTicker(int ident, String ticker, String name, float last,
				float change, boolean tickup) {
			this.ident = ident;
			this.ticker = ticker;
			this.name = name;
			this.last = last;
			this.change = change;
			this.tickUp = tickup;
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.example_trader_view);
		stocks = new ArrayList<StockTicker>();
		toggleButton = (ToggleButton) findViewById(R.id.traderview_togglebutton);
		this.buildGrid(this.flexDataGrid, R.raw.flxstraderview);

		toggleButton.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				toggleTimer(null);
			}
		});

	}

	public void initializeDataGrid() {
		for (int i = 0; i < 10000; i++) {
			int chg = FlexiciousMockGenerator.getRandom(-10, 10);
			StockTicker ticker = new StockTicker(i, "TICK",
					"Ticker with symbol\t " + i,
					FlexiciousMockGenerator.getRandom(20, 30),
					Float.valueOf(chg + "\t"), chg > 0);
			stocks.add(ticker);
		}

		Collections.sort(stocks, new Comparator<StockTicker>() {
			@Override
			public int compare(StockTicker stockTicker1,
					StockTicker stockTicker2) {

				return stockTicker1.ident.compareTo(stockTicker2.ident);
			}
		});

		this.flexDataGrid.setDataProvider(stocks);
	}

	public void toggleTimer(FlexDataGridEvent ns) {
		if (timer != null) {
			timer.cancel();
			timer = null;
		}
		if (toggleButton.isChecked()) {
			double rate = 1.0 / repeatRate;
			rate = rate * 1000;
			timer = new Timer();
			timer.scheduleAtFixedRate(new TimerTask() {

				@Override
				public void run() {
					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							updateTimerHandler();
						}
					});

				}
			}, 0, (long) rate);

		}

	}

	public void updateTimerHandler() {
		// when this happens, we get a batch from the server that says tickers
		// with XX ids have
		// new values...
		ArrayList<StockTicker> affectedItems = new ArrayList<StockTicker>();
		// we just randomly update some 25 items out of the 100.
		for (int i = 0; i < 250; i++) {
			int random = FlexiciousMockGenerator.getRandom(0, this.flexDataGrid
					.getDataProvider().size() - 1);

			int chg = FlexiciousMockGenerator.getRandom(-10, 10);
			StockTicker temp = (StockTicker) this.flexDataGrid
					.getDataProvider().get(random);
			temp.last = FlexiciousMockGenerator.getRandom(20, 30);
			temp.change = chg;
			temp.tickUp = chg > 0;
			affectedItems.add(0, temp);
			((StockTicker) this.flexDataGrid.getDataProvider().get(random)).last = FlexiciousMockGenerator
					.getRandom(20, 30);
			((StockTicker) this.flexDataGrid.getDataProvider().get(random)).change = chg;
			((StockTicker) this.flexDataGrid.getDataProvider().get(random)).tickUp = chg > 0;
			affectedItems.add((StockTicker) this.flexDataGrid.getDataProvider()
					.get(random));
		}

		// now the key here is to only update the cells that are affected.
		// this means we navigate to the row, get the affected cell, and
		// invalidate it...
		// we go through the affectedItems, but keep in mind not all of the
		// affectedItems could be in view. So we check to see if anything is
		// drawn and if something is drawn, only then refresh it...

		for (StockTicker item : affectedItems) {
			// now there is a function call - getCellByRowColumn on the grid.
			// that will quickly get you the cell to update. but in this case
			// since we are updating multiple cells in each row, we will just
			// get the row to update and use its cells collection to quickly
			// update them

			for (RowInfo row : this.flexDataGrid.getBodyContainer().rows) {
				if (row.getData() == item) {
					row.cells.get(2).component.refreshCell();
					row.cells.get(3).component.refreshCell();

				}
			}
		}
	}

	public int TraderView_getCellTextColor(IFlexDataGridCell cell) {
		if (((StockTicker) cell.getRowInfo().getData()).tickUp)
			return StyleManager.instance().getUIColorObjectFromHexString(
					"0x000000");
		else
			return StyleManager.instance().getUIColorObjectFromHexString(
					"0xFFFFFF");
	}

	public int TraderView_getCellBackgroundColor(IFlexDataGridCell cell) {
		if (((StockTicker) cell.getRowInfo().getData()).tickUp)
			return StyleManager.instance().getUIColorObjectFromHexString(
					"0x00FF00");
		else
			return StyleManager.instance().getUIColorObjectFromHexString(
					"0xFF0000");
	}

	public void tradingView_CreationComplete(FlexDataGridEvent event) {
		this.initializeDataGrid();
	}
}
 
