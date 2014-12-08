package ua.kture.pi1311.electrotrain;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Adapter_for_trains extends ArrayAdapter<String> {
  private final Context context;
  private final String[] values;

  public Adapter_for_trains(Context context, String[] values) {
    super(context, R.layout.train_item, values);
    this.context = context;
    this.values = values;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    LayoutInflater inflater = (LayoutInflater) context
        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    View rowView = inflater.inflate(R.layout.stop_item, parent, false);
    TextView textView1 = (TextView) rowView.findViewById(R.id.label);
    TextView textView2 = (TextView) rowView.findViewById(R.id.label2);
    textView1.setText(values[position]);
    textView2.setText("Huipoezda");
    // Change the icon for Windows and iPhone
    return rowView;
  }
} 