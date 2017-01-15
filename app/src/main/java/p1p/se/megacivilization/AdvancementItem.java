package p1p.se.megacivilization;

import android.util.Log;

import java.util.HashMap;

public class AdvancementItem {
    public static final String TAG = "AdvancementItem";

    public int price;
    public int originalPrice;
    public String name;
    public boolean owned;
    public boolean wantToBuy = false;
    public int color;
    public int extraCredit = 0;
    public String next;
    public String colorString;
    public String description;
    public String bonus;
    public String special;
    public HashMap<Integer,CalamityItem> calamities = new HashMap<>();

    public int red = 0;
    public int blue = 0;
    public int green = 0;
    public int yellow = 0;
    public int orange = 0;

    private int discount = 0;

    private static final HashMap<String,Integer> colors = new HashMap<String, Integer>(){{
        put("BLUE", R.color.blue);
        put("RED",R.color.red);
        put("GREEN",R.color.green);
        put("YELLOW",R.color.yellow);
        put("ORANGE",R.color.orange);
        put("GREEN_ORANGE",R.drawable.green_orange);
        put("ORANGE_YELLOW",R.drawable.orange_yellow);
        put("RED_BLUE",R.drawable.red_blue);
        put("GREEN_BLUE",R.drawable.green_blue);
        put("RED_GREEN",R.drawable.red_green);
        put("BLUE_YELLOW",R.drawable.blue_yellow);
        put("YELLOW_GREEN",R.drawable.yellow_green);
        put("RED_YELLOW",R.drawable.red_yellow);
        put("BLUE_ORANGE",R.drawable.blue_orange);
    }};

    public void setColor(String color){
        this.colorString = color;
        this.color = getColor(color);
    }

    private int getColor(String color){
        if(colors.containsKey(color))
            return colors.get(color);
        else {
            Log.v(TAG, "Not found: " + color);
            return R.color.colorAccent;
        }
    }

    public void setPrice(int price){
        this.price = price;
        this.originalPrice = price;

    }

    public int price(){
        return price - discount;
    }

    public String priceAsString(){
        return price() == originalPrice ? ""+price() : price() + " ("+originalPrice+")";
    }

    private boolean hasColor(String color){
        boolean result;
        if(colorString.contains(color))
            result = true;
        else
            result = false;
        //Log.v(TAG, colorString + " hasColor " + color + " " + result);
        return result;
    }

    public void updatePrice(int globalBlue,int globalGreen,int globalRed,int globalYellow,int globalOrange){
        int max = 0;
        //Log.v(TAG, "updatePrice:" + colorString);
        if(hasColor("BLUE"))
            max = globalBlue;
        if(hasColor("GREEN"))
            if(globalGreen > max)
                max = globalGreen;
        if(hasColor("RED"))
            if(globalRed > max)
                max = globalRed;
        if(hasColor("YELLOW"))
            if(globalYellow > max)
                max = globalYellow;
        if(hasColor("ORANGE"))
            if(globalOrange > max)
                max = globalOrange;

        price = originalPrice - max;
        if(price < 0)
            price = 0;
    }

    public void addCalamity(String name, String value){
        Integer id = new Integer(name.replaceAll("[^0-9]", ""));
        CalamityItem item;
        if(calamities.containsKey(id))
            item = calamities.get(id);
        else
            item = new CalamityItem();
        if (name.contains("Bonus"))
            item.bonus = value;
        if(name.contains("Penalty"))
            item.penalty = value;
        if(name.contains("Name"))
            item.name = value;
        Log.v(TAG, item.toString());
        calamities.put(id, item);
    }

    public String toString(){
        StringBuilder sb = new StringBuilder("Name: " + name);
        if(blue > 0)
            sb.append(" Blue: " + blue);
        if(green > 0)
            sb.append(" Green: " + green);
        if(red > 0)
            sb.append(" Red: " + red);
        if(yellow > 0)
            sb.append(" Yellow: " + yellow);
        if(orange > 0)
            sb.append(" Orange: " + orange);
        return sb.toString();
    }

    public void addDiscount(){
        if(originalPrice > 200)
          discount = 20;
        else if(originalPrice > 100)
          discount = 10;
    }

}
