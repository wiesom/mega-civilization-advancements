package p1p.se.megacivilization;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class AdvancementArrayAdaptor extends ArrayAdapter<AdvancementItem> {
    private static final String TAG = "AdvancementArrayAdaptor";
    private final MainActivity activity;
    private final ArrayList<AdvancementItem> list;

    public AdvancementArrayAdaptor(MainActivity activity, ArrayList<AdvancementItem> list) {
        super(activity, R.layout.advancement_item, list);
        this.activity = activity;
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final AdvancementItem item = list.get(position);
        ViewHolder holder;
        if (convertView == null) {
            //Log.v(TAG, "Creating new layoutView");
            LayoutInflater inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.advancement_item, parent, false);
            holder = new ViewHolder();
            holder.checkBox = (CheckBox) convertView.findViewById(R.id.buy_box);
            holder.nameView = (TextView) convertView.findViewById(R.id.advance_name);
            holder.costView = (TextView) convertView.findViewById(R.id.advance_price);
            holder.blue_credit = ((TextView) convertView.findViewById(R.id.blue_credit));
            holder.red_credit = ((TextView) convertView.findViewById(R.id.red_credit));
            holder.orange_credit = ((TextView) convertView.findViewById(R.id.orange_credit));
            holder.green_credit = ((TextView) convertView.findViewById(R.id.green_credit));
            holder.yellow_credit = ((TextView) convertView.findViewById(R.id.yellow_credit));
            convertView.setTag(holder);
        } else {
            //Log.v(TAG, "Reusing layoutView");
            holder = (ViewHolder) convertView.getTag();
        }
        convertView.setBackgroundResource(item.color);
        holder.checkBox.setChecked(item.wantToBuy);
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox checkBox = (CheckBox) v;
                boolean isChecked = checkBox.isChecked();
                Log.v(TAG, "Checked:" + isChecked + " item: " + item.name);
                item.wantToBuy = isChecked;
                activity.calculatePrice(item.price(), isChecked);
            }
        });
        ViewHelper.setVisibility(holder.checkBox, !item.owned);
        holder.nameView.setText(item.name);
        if (item.owned)
            holder.costView.setText("");
        else
            holder.costView.setText("" + item.price());
        ViewHelper.setCreditValue(holder.blue_credit, item.blue);
        ViewHelper.setCreditValue(holder.red_credit, item.red);
        ViewHelper.setCreditValue(holder.orange_credit, item.orange);
        ViewHelper.setCreditValue(holder.green_credit, item.green);
        ViewHelper.setCreditValue(holder.yellow_credit, item.yellow);
        return convertView;
    }

    static class ViewHolder {
        TextView nameView;
        TextView costView;
        CheckBox checkBox;
        TextView blue_credit;
        TextView red_credit;
        TextView orange_credit;
        TextView green_credit;
        TextView yellow_credit;
    }

    // Comparator for Ascending Order
    public static Comparator<AdvancementItem> PriceAscComparator = new Comparator<AdvancementItem>() {

        public int compare(AdvancementItem app1, AdvancementItem app2) {

            int price1 = app1.price();
            int price2 = app2.price();

            if(app1.owned)
                price1 +=1000;
            if(app2.owned)
                price2 += 1000;

            return price1 > price2 ? +1 : price1 < price2 ? -1 : 0;
        }
    };

    //Comparator for Descending Order
    public static Comparator<AdvancementItem> PriceDescComparator = new Comparator<AdvancementItem>() {

        public int compare(AdvancementItem app1, AdvancementItem app2) {

            int price1 = app1.price();
            int price2 = app2.price();

            return price2 > price1 ? +1 : price2 < price1 ? -1 : 0;
        }
    };

    public void sort() {
        Collections.sort(list,PriceAscComparator);
    }
}