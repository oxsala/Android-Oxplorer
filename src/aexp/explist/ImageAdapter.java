package aexp.explist;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAdapter extends BaseAdapter {

	private final Activity activity;
	private static LayoutInflater inflater = null;

	public ImageAdapter(Activity a) {
		activity = a;
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public int getCount() {
		return data.length;
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	public static class ViewHolder {
		public TextView text;
		public ImageView image;
		public CheckBox checkbox_scr2;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View vi = convertView;
		ViewHolder holder;
		if (convertView == null) {
			vi = inflater.inflate(R.layout.custom_gallery, null);
			holder = new ViewHolder();
			holder.text = (TextView) vi.findViewById(R.id.textView1);
			holder.image = (ImageView) vi.findViewById(R.id.image);
			vi.setTag(holder);
		} else {
			holder = (ViewHolder) vi.getTag();
		}
		holder.text.setText(name[position]);
		final int stub_id = data[position];
		holder.image.setImageResource(stub_id);
		/*
		 * holder.image.setLayoutParams(new Gallery.LayoutParams(80, 70));
		 * holder.image.setScaleType(ImageView.ScaleType.FIT_XY);
		 */
		return vi;
	}

	private final int[] data = { R.drawable.i1, R.drawable.i2 };
	private final String[] name = { "1", "2" };
}
