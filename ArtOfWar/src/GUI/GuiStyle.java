package GUI;

/**
 * Easy tool to help set the style of pane
 *
 * @author <Xiaoyue Zhang>
 * @since <pre>Nov 9 2020</pre>
 * @version 1.3
 */
public class GuiStyle {

    enum COLOR{ red, pink, black, white, brown, dark_blue, light_gray}
    enum TYPE{ solid}

    /**
     * color pattern for other method
     *
     * @param color the name of color
     * */
    public static String colorString(COLOR color){
        if(color.equals(COLOR.red))
            return "#C6584B";
        else if(color.equals(COLOR.pink))
            return "#D3876F";
        else if(color.equals(COLOR.black))
            return "#000000";
        else if(color.equals(COLOR.white))
            return "#FFFFFF";
        else if(color.equals(COLOR.brown))
            return "#554336";
        else if(color.equals(COLOR.dark_blue))
            return "#2F2F44";
        else if(color.equals(COLOR.light_gray))
            return "#595350";
        else
            return "";
    }

    /**
     * background color quick setter
     *
     * @param color the name of color needed to set as background color
     * */
    public static String backgroundColor(COLOR color){
        return "-fx-background-color: "+colorString(color)+";\n";
    }

    /**
     * border quick builder
     *
     * @param width the width of borderline as int
     * @param color the color of borderline as COLOR
     * @param type the type of borderline as TYPE
     * */
    public static String borderlineSet(int width, COLOR color, TYPE type){
        // width set
        String borderline = "-fx-border-width: "+ width+";\n";

        // color set
        borderline += "-fx-border-color: "+colorString(color)+";\n";

        // type set
        if(type.equals(TYPE.solid))
            borderline += "-fx-border-type: solid;\n";

        return borderline;

    }

    /**
     * Rounded border quick builder
     *
     * @param width the width of borderline as int
     * @param color the color of borderline as COLOR
     * @param type the type of borderline as TYPE
     * @param corner the size of rounded border corners as int
     * */
    public static String borderlineSet(int width, COLOR color, TYPE type, int corner){
        return "-fx-background-radius: "+1.5*corner+"px;\n-fx-border-radius: "+corner+"px;\n" + borderlineSet(width,color,type);
    }
}
