package p1p.se.megacivilization;


import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class AdvancementItemDialog {

    AlertDialog alertDialog;

    public AdvancementItemDialog(AppCompatActivity activity, AdvancementItem item) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity.getSupportActionBar().getThemedContext(), R.style.MyAlertDialogStyle);
        alertDialogBuilder.setTitle(item.name + ": " + item.price());

        View titleLayout = activity.getLayoutInflater().inflate(R.layout.custom_dialog_title, null);
        ((TextView) titleLayout.findViewById(R.id.dialog_title)).setText(item.name);
        ((TextView) titleLayout.findViewById(R.id.dialog_title_price)).setText(item.price() + "");
        titleLayout.setBackgroundResource(item.color);
        alertDialogBuilder.setCustomTitle(titleLayout);

//        if (!item.owned) {
//            alertDialogBuilder.setPositiveButton("Buy", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface arg0, int arg1) {
//                    MainActivity.this.addValues(item);
//                    MainActivity.this.updateList();
//                    item.owned = true;
//                    MainActivity.this.adapter.notifyDataSetChanged();
//                    selectBonus(item);
//                }
//            });
//        }

        alertDialogBuilder.setNegativeButton("Back", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Do nothing
            }
        });

        View dialogLayout = activity.getLayoutInflater().inflate(R.layout.custom_dialog, null);
        ViewHelper.setDialogValue(dialogLayout, R.id.dialog_description, "<b>Description</b><br>", item.description);
        ViewHelper.setDialogValue(dialogLayout, R.id.dialog_bonus, "<b>Bonus</b><br>", item.bonus);
        ViewHelper.setDialogValue(dialogLayout, R.id.dialog_next, "<b>Discount</b> ", item.next);
        ViewHelper.setDialogValue(dialogLayout, R.id.dialog_special, "<b>Special ability</b><br>", item.special);

        ViewHelper.setCreditValue((TextView) dialogLayout.findViewById(R.id.blue_credit), item.blue);
        ViewHelper.setCreditValue((TextView) dialogLayout.findViewById(R.id.red_credit), item.red);
        ViewHelper.setCreditValue((TextView) dialogLayout.findViewById(R.id.orange_credit), item.orange);
        ViewHelper.setCreditValue((TextView) dialogLayout.findViewById(R.id.green_credit), item.green);
        ViewHelper.setCreditValue((TextView) dialogLayout.findViewById(R.id.yellow_credit), item.yellow);
        ViewHelper.setCalamityList(dialogLayout, R.id.calamityList, item.calamities);
        alertDialogBuilder.setView(dialogLayout);
        alertDialog = alertDialogBuilder.create();
    }



    public void show(){
        alertDialog.show();
    }
}
