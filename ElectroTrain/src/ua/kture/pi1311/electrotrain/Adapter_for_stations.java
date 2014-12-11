package ua.kture.pi1311.electrotrain;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Adapter_for_stations extends ArrayAdapter<String> {
  private final Context context;
  private final String[] stationsFrom;
  private final String[] stationsTo;
  private final String[] arrivals;
  private final String[] depatures;
  private final String[] trainsIds;
  

  public Adapter_for_stations(Context context, String[] stationsFrom, String[] stationsTo,
		String[] arrivals, String[] depatures, String[] trainsIds) 
  {
    super(context, R.layout.train_item, stationsFrom);
    this.context = context;
    this.stationsFrom = stationsFrom;
    this.stationsTo = stationsTo;
    this.arrivals = arrivals;
    this.depatures = depatures;
    this.trainsIds = trainsIds;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) 
  {
    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    View rowView = inflater.inflate(R.layout.train_item_s, parent, false);
    TextView textView1 = (TextView) rowView.findViewById(R.id.label);
    TextView textView2 = (TextView) rowView.findViewById(R.id.label2);
    TextView textView3 = (TextView) rowView.findViewById(R.id.textView3);
    TextView textView5 = (TextView) rowView.findViewById(R.id.textViewTrainId);
    textView1.setText(stationsFrom[position]);
    textView2.setText(stationsTo[position]);
    textView3.setText(arrivals[position]);
    textView5.setText(trainsIds[position]);
    // Change the icon for Windows and iPhone
    return rowView;
  }
} 