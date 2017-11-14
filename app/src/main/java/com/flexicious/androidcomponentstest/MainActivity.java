package com.flexicious.androidcomponentstest;

import java.util.ArrayList;
import java.util.Collections;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.flexicious.androidcomponentstest.sampleviews.AutoResizingGrid;
import com.flexicious.androidcomponentstest.sampleviews.ChangeTrackingAPI;
import com.flexicious.androidcomponentstest.sampleviews.ColumnLockModes;
import com.flexicious.androidcomponentstest.sampleviews.ColumnWidthMode;
import com.flexicious.androidcomponentstest.sampleviews.CustomFooter;
import com.flexicious.androidcomponentstest.sampleviews.CustomItemRenderers;
import com.flexicious.androidcomponentstest.sampleviews.CustomMatchFilterControl;
import com.flexicious.androidcomponentstest.sampleviews.DisclosureIcon_GroupedUI;
import com.flexicious.androidcomponentstest.sampleviews.DisclosureIcon_NestedUI;
import com.flexicious.androidcomponentstest.sampleviews.DynamicColumns;
import com.flexicious.androidcomponentstest.sampleviews.DynamicGrouping;
import com.flexicious.androidcomponentstest.sampleviews.DynamicLevels;
import com.flexicious.androidcomponentstest.sampleviews.ExampleActivityBase;
import com.flexicious.androidcomponentstest.sampleviews.ExternalFilter;
import com.flexicious.androidcomponentstest.sampleviews.Filtercomboboxdataprovider;
import com.flexicious.androidcomponentstest.sampleviews.FullRowEdit;
import com.flexicious.androidcomponentstest.sampleviews.GroupedData;
import com.flexicious.androidcomponentstest.sampleviews.GroupedDataOutlookStyle;
import com.flexicious.androidcomponentstest.sampleviews.GroupedData_2;
import com.flexicious.androidcomponentstest.sampleviews.IconColumns;
import com.flexicious.androidcomponentstest.sampleviews.LargeDataset;
import com.flexicious.androidcomponentstest.sampleviews.LargeDynamicGrid;
import com.flexicious.androidcomponentstest.sampleviews.LevelItemRenderers;
import com.flexicious.androidcomponentstest.sampleviews.LevelItemRenderers_2;
import com.flexicious.androidcomponentstest.sampleviews.Localization;
import com.flexicious.androidcomponentstest.sampleviews.MultiSelectSetFilterValue;
import com.flexicious.androidcomponentstest.sampleviews.MultipleGroupingManual;
import com.flexicious.androidcomponentstest.sampleviews.Nested;
import com.flexicious.androidcomponentstest.sampleviews.NestedDataFullyLazyLoaded;
import com.flexicious.androidcomponentstest.sampleviews.NestedDataPartialLazyLoaded;
import com.flexicious.androidcomponentstest.sampleviews.OnlyOneItemOpen;
import com.flexicious.androidcomponentstest.sampleviews.ProgramaticCellFormatting;
import com.flexicious.androidcomponentstest.sampleviews.SelectionMode;
import com.flexicious.androidcomponentstest.sampleviews.Simple;
import com.flexicious.androidcomponentstest.sampleviews.SortNumeric;
import com.flexicious.androidcomponentstest.sampleviews.TraderView;
import com.flexicious.androidcomponentstest.sampleviews.VariableHeaderRowHeight;
import com.flexicious.androidcomponentstest.sampleviews.VariableRowHeight;
import com.flexicious.androidcomponentstest.sampleviews.XmlData;
/**
 * This is the main activity for the demo console. This loads all the available samples. 
 * Each sample inherits from ExampleActivityBase, which provides some utility methods to 
 * load grid, parse json, parse xml, and some general use formatting functions.
 */
public class MainActivity extends ExampleActivityBase {

    GridView gridView;
    ArrayList<ExampleData> gridArray = new ArrayList<ExampleData>();
    CustomGridViewAdapter customGridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        gridArray.clear();
        this.setTitle("Android JetPack - Demo Console");
        Collections
                .addAll(gridArray,
                        new ExampleData[] {
                                new ExampleData(
                                        "Simple",
                                        "This example demonstrates a number of features of the iOS Tree grid, including  filter, footer, paging, multi column sort, locked columns, grouped headers, print, export, and preference persistence",
                                        "Simple", Simple.class),
                                new ExampleData(
                                        "Nested",
                                        "This example demonstrates support of nested tree grids, where each level contains its own set of columns.",
                                        "Nested", Nested.class),
                                new ExampleData(
                                        "PartialLazyLoaded",
                                        "This example demonstrates support for loading inner level details in a lazy fashion. It loads each item completely on demand (when the user clicks the expand icon.",
                                        "Partial lazy loaded",
                                        NestedDataPartialLazyLoaded.class),
                                new ExampleData(
                                        "FullyLazyLoaded",
                                        "This example demonstrates further extends the lazy loading concept to load each hierarchical level in a lazy fasion.",
                                        "Fully lazy loaded",
                                        NestedDataFullyLazyLoaded.class),
                                new ExampleData(
                                        "GroupedData",
                                        "This example demonstrates inner levels that share the same set of columns as the top level",
                                        "Grouped data", GroupedData.class),
                                new ExampleData(
                                        "GroupedData2",
                                        "This example demonstrates inner levels that share the same set of columns as the top level, as well a common name column.",
                                        "Grouped data 2", GroupedData_2.class),
                                new ExampleData(
                                        "OutlookGroupedData",
                                        "This example shows usage of the built in styles to achieve an Outlook style grouping UI",
                                        "Outlook grouped data",
                                        GroupedDataOutlookStyle.class),
                                new ExampleData(
                                        "LevelRenderers",
                                        "This example demonstrates inner levels that render a detail area as opposed to a inner table",
                                        "Level renderers",
                                        LevelItemRenderers.class),
                                new ExampleData(
                                        "LevelRenderers2",
                                        "This example demonstrates immediate inner level that renders details.",
                                        "Level renderers 2",
                                        LevelItemRenderers_2.class),
                                new ExampleData(
                                        "ProgramaticCellFormatting",
                                        "This example demonstrates support for programmatic formatting of cell text color,background color, etc.",
                                        "Programatic cell formatting",
                                        ProgramaticCellFormatting.class),
                                new ExampleData(
                                        "ItemRenderers",
                                        "This example demonstrates usage of item renderers to generate the contents of cells programmatically.",
                                        "Item renderers",
                                        CustomItemRenderers.class),
                                new ExampleData(
                                        "EditableCells",
                                        "This example demonstrates support for inline cell editing. Each column supports an item editor which is a class factory that creates an editor component used to edit the data object assoicated with each cell.",
                                        "Editable cells", FullRowEdit.class),
                                new ExampleData(
                                        "DynamicColumns",
                                        "This example demonstrates modifying the columns collection of the grid at runtime in a dynamic fasion.",
                                        "Dynamic columns", DynamicColumns.class),
                                new ExampleData(
                                        "LargeDataSet",
                                        "This example demonstrates rendering a large dataset in the grid. The grid supports horizontal and vertical renderer recycling (Drawing only the visible area), there by achieving blazing fast performance, even with large datasets",
                                        "Large data set", LargeDataset.class),
                                new ExampleData(
                                        "XmlData",
                                        "This example demonstrates support for parsing flat XML data and rendering it inside the grid.",
                                        "Xml data", XmlData.class),
                                new ExampleData(
                                        "AutoResizingGrid",
                                        "This example demonstrates support of autosizing the grid based upon number of rows being displayed.",
                                        "Auto resizing grid",
                                        AutoResizingGrid.class),
                                new ExampleData(
                                        "SelectionModes",
                                        "This example demonstrates support for various selection modes like  Single cell, Multiple cell, Single row, multiple rows, multiple rows (CTRL-Click), and  None.",
                                        "Selection modes", SelectionMode.class),
                                new ExampleData(
                                        "CustomMatchFilterControl",
                                        "This example demonstrates usage of a custom filter control to embed custom logic in the filtering mechanism",
                                        "Custom match filter control",
                                        CustomMatchFilterControl.class),
                                new ExampleData(
                                        "ColumnLockMode",
                                        "This example demonstrates support for various column lock modes, left locked, right locked and unlocked columns",
                                        "Column lock modes",
                                        ColumnLockModes.class),
                                new ExampleData(
                                        "LargeDynamicGrid",
                                        "This example demonstrates support for a large number of columns and rows ",
                                        "Large dynamic grid",
                                        LargeDynamicGrid.class),
                                new ExampleData(
                                        "DynamicLevels",
                                        "This example demonstrates the concept of dynamic levels, where the hierarchical levels are created on basis of the data at runtime, as opposed to in markup at compile time.",
                                        "Dynamic levels", DynamicLevels.class),
                                new ExampleData(
                                        "IconColumns",
                                        "This example demonstrates support for icons as well as showing icons by default,on row rollover, or on cell rollover",
                                        "Icon columns", IconColumns.class),
                                new ExampleData(
                                        "DynamicGrouping",
                                        "This example shows how to group data dynamically on basis of a property chosen by the user.",
                                        "Dynamic grouping",
                                        DynamicGrouping.class),
                                new ExampleData(
                                        "SelectionUI1",
                                        "This example demonstrates support changing the column in which the disclosure icon (expand collapse icon) appears.",
                                        "Selection UI 1",
                                        DisclosureIcon_NestedUI.class),
                                new ExampleData(
                                        "SelectionUI2",
                                        "This example demonstrates support changing the column in which the disclosure icon (expand collapse icon) appears, as well as showing labels along side the default checkboxes.",
                                        "Selection UI 2",
                                        DisclosureIcon_GroupedUI.class),
                                new ExampleData(
                                        "ExternalFilter",
                                        "This example demonstrates support applying an external filter to the grid.",
                                        "External filter", ExternalFilter.class),
                                new ExampleData(
                                        "ChangeTrackingAPI",
                                        "This example demonstrates the Change tracking API. The grid tracks changes to the underlying data provider via the cell editors. This can be then used to synchronize with the backend datastore.",
                                        "Change tracking API",
                                        ChangeTrackingAPI.class),
                                new ExampleData(
                                        "TraderView",
                                        "This example demonstrates refreshing the grid with rapidly changing data.",
                                        "Trader view", TraderView.class),
                                new ExampleData(
                                        "VariableRowHeight",
                                        "This example demonstrates support for dynamic row height that is calculated on basis of the underlying data.",
                                        "Variable row height",
                                        VariableRowHeight.class),
                                new ExampleData(
                                        "FilterComboBoxDataProvider",
                                        "This example demonstrates how to provide a custom data provider for the filter combobox.",
                                        "Filter combo box data provider",
                                        Filtercomboboxdataprovider.class),
                                new ExampleData(
                                        "Localization",
                                        "This example demonstrates how to customize various places in the grid where we render text.",
                                        "Localization", Localization.class),
                                new ExampleData(
                                        "OnlyOneItemOpen",
                                        "This example demonstrates how to collapse all other items when a node is opened",
                                        "Only one item open",
                                        OnlyOneItemOpen.class),
                                new ExampleData(
                                        "SortNumeric",
                                        "This example demonstrates how to sort string data on a numeric basis",
                                        "Sort numeric", SortNumeric.class),
                                new ExampleData(
                                        "MultiSelectSetFilterValue",
                                        "This example demonstrates prepopulating filter values.",
                                        "Multi select set filter Value",
                                        MultiSelectSetFilterValue.class),
                                new ExampleData(
                                        "VariableHeaderRowHeight",
                                        "This example demonstrates support for headers of a dynamic size, where the size of the header is determined dynamically on basis of the header text and column width.",
                                        "Variable header row height",
                                        VariableHeaderRowHeight.class),
                                new ExampleData(
                                        "MultipleGroupingManual",
                                        "This example demonstrates transposing a flat dataprovider into a hierarchica one while at the same time gathering data to render at parent levels.",
                                        "Multiple grouping Manual",
                                        MultipleGroupingManual.class),
                                new ExampleData(
                                        "CustomFooter",
                                        "This example demonstrates support for creating dynamic column footers",
                                        "Custom footer", CustomFooter.class),
                                new ExampleData(
                                        "ColumnWidthMode",
                                        "This example demonstrates support for various column lock modes, none,fixed,percent,and fitToContent",
                                        "Column width mode",
                                        ColumnWidthMode.class) });

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = (GridView) findViewById(R.id.gridView1);
        customGridAdapter = new CustomGridViewAdapter(this, R.layout.row_grid,
                gridArray);
        gridView.setAdapter(customGridAdapter);
        addListenerToGrid();
    }

    public void addListenerToGrid() {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view,
                                    int position, long l) {
                // Access text in the cell, or the object itself
                ExampleData s = (ExampleData) gridView
                        .getItemAtPosition(position);
                Class<?> targetActivity = s.activityClass;
                Intent intent = new Intent(MainActivity.this, targetActivity);
                intent.putExtra("title", s.name);
                MainActivity.this.startActivity(intent);

            }
        });
        //
        // gridView.setOnTouchListener(new OnTouchListener() {
        // public boolean onTouch(View v, MotionEvent me) {
        // int action = me.getActionMasked(); // MotionEvent types such as
        // ACTION_UP, ACTION_DOWN
        // float currentXPosition = me.getX();
        // float currentYPosition = me.getY();
        // int position = gridView.pointToPosition((int) currentXPosition, (int)
        // currentYPosition);
        // if(me.getAction() == MotionEvent.ACTION_UP ){
        // // Access text in the cell, or the object itself
        // ExampleData s = (ExampleData) gridView.getItemAtPosition(position);
        // View tv = gridView.getChildAt(position);
        // }
        // return true;
        // }
        // });
    }

}
