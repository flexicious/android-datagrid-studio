package com.flexicious.androidcomponentstest;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 
 * @author manish.s
 *
 */
public class CustomGridViewAdapter extends ArrayAdapter<ExampleData> {
	Context context;
	int layoutResourceId;
	ArrayList<ExampleData> data = new ArrayList<ExampleData>();

	public CustomGridViewAdapter(Context context, int layoutResourceId,
			ArrayList<ExampleData> data) {
		super(context, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		RecordHolder holder = null;

		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);

			holder = new RecordHolder();
			holder.txtTitle = (TextView) row.findViewById(R.id.item_text);
			holder.txtDescription = (TextView) row.findViewById(R.id.item_description);
			holder.imageItem = (ImageView) row.findViewById(R.id.item_image);
			row.setTag(holder);
		} else {
			holder = (RecordHolder) row.getTag();
		}

		ExampleData item = data.get(position);
		holder.txtTitle.setText(item.name);
		holder.txtDescription.setText(item.description);
		int drawableResourceId = this.getContext().getResources().getIdentifier(item.id.toLowerCase(), "drawable", this.getContext().getPackageName());
		if(drawableResourceId!=0)
			holder.imageItem.setImageDrawable(this.getContext().getResources().getDrawable(drawableResourceId));
		else
			holder.imageItem.setImageDrawable(this.getContext().getResources().getDrawable(R.drawable.simple));
		return row;

	}

	static class RecordHolder {
		TextView txtTitle;
		TextView txtDescription;
		ImageView imageItem;

	}
}