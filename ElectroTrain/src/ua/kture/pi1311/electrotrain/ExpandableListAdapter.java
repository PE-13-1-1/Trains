package ua.kture.pi1311.electrotrain;

import java.util.HashMap;
import java.util.List;
 
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
 
public class ExpandableListAdapter extends BaseExpandableListAdapter 
{ 
    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<String>> _listDataChild;
 
    public ExpandableListAdapter(Context context, List<String> listDataHeader,
            HashMap<String, List<String>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }
 
    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
    }
 
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }
 
    @Override
    public View getChildView(int groupPosition, final int childPosition,
    											boolean isLastChild, View convertView, ViewGroup parent) 
    {
        final String childText = (String) getChild(groupPosition, childPosition);
 
        if (convertView == null) 
        {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item, null);
        }
 
        TextView txtListChild = (TextView) convertView.findViewById(R.id.lblListItem);
 
        txtListChild.setText(childText);
        ImageButton button = (ImageButton)convertView.findViewById(R.id.fav_but);
        
        
        //button.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View view) {
        //        view.setBackgroundColor(Color.CYAN);    	
        //    }
        //});
        
        final View myConvertView = convertView;
        final int externalCount = 0;
        //final int count = externalCount;
        
        // I donno how to change any external variable =(
        // I tried, but it seems, Java doesn't have any ref vars.
        /*
        button.setOnLongClickListener(new View.OnLongClickListener()
    	{
			public boolean onLongClick(View v)
			{
				int count = externalCount;
				count = listItemPaintMethod(externalCount, myConvertView);
				return false;
			}
		});
        */
        return convertView;
    }
    
    private int listItemPaintMethod(int count, View myConvertView)
    {
    	/*if(count == 0)
		{
			myConvertView.setBackgroundColor(Color.parseColor("#CBE8BA"));
			count++;
		}
		else if (count == 1)
		{
			myConvertView.setBackgroundColor(Color.parseColor("#5aa532"));
			count++;
		}
		else if (count == 2)
		{
			myConvertView.setBackgroundColor(Color.parseColor("#355723"));
			count++;
		}
		else if (count == 3)
		{
			myConvertView.setBackgroundColor(Color.RED);
			count = 0;
		}*/
    	myConvertView.setBackgroundColor(Color.RED);
    	return count;
    }
    
 
    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }
 
    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }
 
    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }
 
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
 
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) 
    {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }
 
        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);
        lblListHeader.setFocusable(false);
        
        return convertView;
    }
 
    @Override
    public boolean hasStableIds() {
        return false;
    }
 
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
