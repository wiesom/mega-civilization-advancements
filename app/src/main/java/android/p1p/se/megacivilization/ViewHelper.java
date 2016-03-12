package android.p1p.se.megacivilization;

import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Iterator;

public class ViewHelper {
    private static final String TAG = "ViewHelper";
    public static void setCreditValue(TextView text, int value) {
        text.setText("" + value);
        if (value == 0)
            text.getBackground().setAlpha(10);
        else
            text.getBackground().setAlpha(255);
    }

    public static void setVisibility(View view, boolean show){
        if(show)
            view.setVisibility(View.VISIBLE);
        else
            view.setVisibility(View.INVISIBLE);
    }

    public static void setDialogValue(View view, int view_id, String addition, String value) {
        Log.v(TAG, "Addition:" + addition + " Value" + value);
        TextView text = ((TextView) view.findViewById(view_id));
        if(value != null){
            text.setVisibility(View.VISIBLE);
            text.setText(Html.fromHtml(addition + value));
        }else{
            text.setVisibility(View.GONE);
        }
    }

    public static void setCalamityList(View view, int view_id, HashMap<Integer, CalamityItem> calamities) {
        TextView listView = ((TextView) view.findViewById(view_id));
        if(calamities.isEmpty()){
            listView.setVisibility(View.GONE);
        }else{
            StringBuilder calamityPresentation = new StringBuilder();
            Iterator iter = calamities.values().iterator();
            while (iter.hasNext()) {
                CalamityItem item = (CalamityItem)iter.next();
                calamityPresentation.append(item.toHtml());
                if (iter.hasNext()) {
                    calamityPresentation.append("<br>");
                }
            }

            calamityPresentation.delete(calamityPresentation.length()-3,calamityPresentation.length()-1);
            listView.setText(Html.fromHtml(calamityPresentation.toString()));
            listView.setVisibility(View.VISIBLE);
            //text.setVisibility(View.VISIBLE);
            //text.setText(addition + value);
        }
    }

    static NumberPicker setPickeValues(View view, int view_id){
        final String[] selection = new String[]{"0", "5", "10", "15", "20", "25", "30"};
        NumberPicker picker = ((NumberPicker) view.findViewById(view_id));
        picker.setMinValue(0);
        picker.setMaxValue(6);
        picker.setDisplayedValues(selection);
        return picker;
    }

    static boolean picketCorrectValues(int bonus, NumberPicker blue, NumberPicker green,NumberPicker red,NumberPicker yellow,NumberPicker orange){
        int value = 0;
        value += blue.getValue();
        value += green.getValue();
        value += red.getValue();
        value += yellow.getValue();
        value += orange.getValue();
        if(value*5 == bonus)
            return true;
        return false;
    }
}
