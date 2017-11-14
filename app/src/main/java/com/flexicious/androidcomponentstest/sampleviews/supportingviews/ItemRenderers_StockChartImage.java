package com.flexicious.androidcomponentstest.sampleviews.supportingviews;

import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import com.flexicious.example.model.organizations.Organization;

public class ItemRenderers_StockChartImage extends ImageView {

	public ItemRenderers_StockChartImage(Context context) {
		super(context);
	}

	public ItemRenderers_StockChartImage(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ItemRenderers_StockChartImage(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
	}
	
	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
		Organization org = (Organization) data;
		new DownloadImageTask(this).execute(org.chartUrl);
	}

	private Object data;
	
	//thanks stackoverflow
	private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
		  ImageView bmImage;

		  public DownloadImageTask(ImageView bmImage) {
		      this.bmImage = bmImage;
		  }

		  protected Bitmap doInBackground(String... urls) {
		      String urldisplay = urls[0];
		      Bitmap mIcon11 = null;
		      try {
		        InputStream in = new java.net.URL(urldisplay).openStream();
		        mIcon11 = BitmapFactory.decodeStream(in);
		      } catch (Exception e) {
		          Log.e("Error", e.getMessage());
		          e.printStackTrace();
		      }
		      return mIcon11;
		  }

		  protected void onPostExecute(Bitmap result) {
		      bmImage.setImageBitmap(result);
		  }
		}
}
