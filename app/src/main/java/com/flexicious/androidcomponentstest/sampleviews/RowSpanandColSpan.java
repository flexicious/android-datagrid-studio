package com.flexicious.androidcomponentstest.sampleviews;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.flexicious.androidcomponentstest.R;
import com.flexicious.example.FlexiciousMockGenerator;
import com.flexicious.nestedtreedatagrid.events.FlexDataGridEvent;
import com.flexicious.nestedtreedatagrid.interfaces.IFlexDataGridCell;
import com.flexicious.utils.UIUtils;


public class RowSpanandColSpan extends ExampleActivityBase{
	
	RadioGroup radioGroup;
	RadioButton usecolspan_rb, userowspan_rb;
	ArrayList<HashMap<String, Object>> questions;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {		 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.example_row_spanand_col_span);
		questions = new ArrayList<HashMap<String,Object>>();
		radioGroup = (RadioGroup) findViewById(R.id.rowspancolspan_radiogroup);
		userowspan_rb = (RadioButton) findViewById(R.id.rowspancolspan_rowspanrb);
		usecolspan_rb = (RadioButton) findViewById(R.id.rowspancolspan_colspanrb);
		this.buildGrid(this.flexDataGrid, R.raw.flxsrowspancolspan);
		this.flexDataGrid.setDataProvider(FlexiciousMockGenerator.instance().getFlatOrgList());
		
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup paramRadioGroup, int paramInt) {
				flexDataGrid.rebuild();
			}
		});
	}

	public int gettotalCount(){
		return 10;
	}
	public void addQuestion(String questionText){
		HashMap<String, Object> q =new HashMap<String,Object>();
		q.put("question", questionText);
		q.put("answers", new ArrayList<HashMap<String, Object>>());
		//addAnswers(q);
		this.addAnswers(q);
		questions.add(q);
	}
	@SuppressWarnings("unchecked")
	public void addAnswers(HashMap<String, Object> q)
	{

		ArrayList<HashMap<String, Object>> answers = (ArrayList<HashMap<String, Object>>) q.get("answers");
		answers.add(createAnswer("Very Satisfied"));
		answers.add(createAnswer("Moderately Satisfied"));
		answers.add(createAnswer("No Opinion/NA"));
		answers.add(createAnswer("Moderately Dissatisfied"));
		answers.add(createAnswer("Very Satisfied"));
		float total=UIUtils.sum(answers,"totalCount");
		for(HashMap<String, Object> ans : answers){
			ans.put("totalPercent", 100*(Float)ans.get("totalCount")/total);
		}
		q.put("freshmanCount", UIUtils.sum(answers, "freshmanCount"));
		q.put("sophomoreCount", UIUtils.sum(answers, "sophomoreCount"));
		q.put("juniorCount", UIUtils.sum(answers, "juniorCount"));
		q.put("seniorCount", UIUtils.sum(answers, "seniorCount"));

		q.put("totalCount", (Float)q.get("freshmanCount")+(Float)q.get("sophomoreCount")+(Float)q.get("juniorCount")+(Float)q.get("seniorCount"));
		q.put("freshmanPercent", (100*(Float)q.get("freshmanCount")/(Float)q.get("totalCount")));
		q.put("sophomorePercent", (100*(Float)q.get("sophomoreCount")/(Float)q.get("totalCount")));
		q.put("juniorPercent", (100*(Float)q.get("juniorCount")/(Float)q.get("totalCount")));
		q.put("seniorPercent", (100*(Float)q.get("seniorCount")/(Float)q.get("totalCount")));
		q.put("totalPercent", Float.valueOf(100));
	}
	public HashMap<String, Object> createAnswer(String answerOption)
	{
		HashMap<String, Object> a=new HashMap<String, Object>();
		a.put("answerOption", answerOption);
		a.put("freshmanCount", FlexiciousMockGenerator.getRandom(100, 400).floatValue());
		a.put("sophomoreCount", FlexiciousMockGenerator.getRandom(100, 400).floatValue());
		a.put("juniorCount", FlexiciousMockGenerator.getRandom(100, 400).floatValue());
		a.put("seniorCount", FlexiciousMockGenerator.getRandom(100, 400).floatValue());

		a.put("totalCount", (Float)a.get("freshmanCount")+(Float)a.get("sophomoreCount")+(Float)a.get("juniorCount")+(Float)a.get("seniorCount"));
		a.put("freshmanPercent", (Float)a.get("freshmanCount")/(Float)a.get("totalCount"));
		a.put("sophomorePercent", (Float)a.get("sophomoreCount")/(Float)a.get("totalCount"));
		a.put("juniorPercent", (Float)a.get("juniorCount")/(Float)a.get("totalCount"));
		a.put("seniorPercent", (Float)a.get("seniorCount")/(Float)a.get("totalCount"));
		
		return a;
	}

	public void rowSpanColSpan_CreationComplete(FlexDataGridEvent ns)
	{
		addQuestion("Please rate your level of satisfaction with the sense of safety and security as experienced in your residential college/housing campus");
		addQuestion("Please rate your level of satisfaction with the availability of public transportation to and from the University Campus");
		addQuestion("Please rate your level of satisfaction with the quality of the Intramural sports and recreation programs");
		addQuestion("Please rate your level of satisfaction with your sense of acceptance, belonging, and community");
		flexDataGrid.setDataProvider(questions);
		this.flexDataGrid.validateNow();
		this.flexDataGrid.expandAll();
	}
	@SuppressWarnings("unchecked")
	public Object rowSpanColSpan_getRowSpan(IFlexDataGridCell cell){
		if(radioGroup.getCheckedRadioButtonId() != R.id.rowspancolspan_rowspanrb) return Integer.valueOf(1);
		if(cell.getLevel().getNestDepth() == 1
				&& cell.getLevel().isItemOpen(cell.getRowInfo().getData())
				&& cell.getColumn() != null
				&& cell.getColumn().getDataField().equals("question")
				&& cell.getRowInfo().getIsDataRow() //TODO sal&& !cell.getRowInfo().isFillRowsince 
				){
					HashMap<String, Object> dic = (HashMap<String, Object>) cell.getRowInfo().getData();
					HashMap<String, Object> arr = (HashMap<String, Object>) dic.get("answers");
					return arr.size()+1;
				}
				return Integer.valueOf(1);
	}
	public Object rowSpanColSpan_getColSpan(IFlexDataGridCell cell){
		if(radioGroup.getCheckedRadioButtonId() != R.id.rowspancolspan_colspanrb) return Integer.valueOf(1);
		if(cell.getLevel().getNestDepth()==1// top level
				&& cell.getColumn() != null
				&& cell.getColumn().getDataField().equals("question") //its the first column
				&& cell.getRowInfo().getIsDataRow() //its the data row, not the header or the footer row
				//&& !cell.getRowInfo().isFillRowsince the filler is also considered a data row
						)
						return Integer.valueOf(flexDataGrid.getColumns().size());

						return Integer.valueOf(1);
	}
	public Object rowSpanColSpan_getColor(IFlexDataGridCell cell){
		if(cell.getLevel().getNestDepth()==1 //top level
				&& cell.getColumn() != null  
				&& cell.getColumn().getDataField().equals("question")// its the first column
				&& cell.getRowInfo().getIsDataRow()// its the data row, not the header or the footer row
						)
						return null;//[[StyleManager instance] getUIColorObjectFromHexString:0xF7F3F7];

				return null;
	}
	public void segment_Click(Object sender) {
		this.flexDataGrid.rebuild();
	}
} 
