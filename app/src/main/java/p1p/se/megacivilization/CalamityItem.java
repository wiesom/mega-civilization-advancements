package p1p.se.megacivilization;

public class CalamityItem {
    public String name;
    public String bonus;
    public String penalty;

    public String toString(){
        StringBuilder sb = new StringBuilder("");
        if(name != null)
            sb.append(" Name:").append(name).append(" ");
        if(bonus != null)
            sb.append(" Bonus:").append(bonus).append(" ");
        if(penalty != null)
            sb.append(" Penalty:").append(penalty).append(" ");
        return sb.toString();
    }

    public String toHtml(){
        StringBuilder sb = new StringBuilder("");
        if(name != null)
            sb.append("<b>").append(name).append("</b><br>");
        if(bonus != null)
            sb.append("Bonus: ").append(bonus).append("<br>");
        if(penalty != null)
            sb.append("Penalty: ").append(penalty).append("<br>");
        return sb.toString();
    }
}
