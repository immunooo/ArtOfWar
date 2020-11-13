package GUI;

/**
 * Easy tool to help set the style of pane
 *
 * @author <Xiaoyue Zhang>
 * @since <pre>Nov 11 2020</pre>
 * @version 1.4
 */
public class GuiStyle {

    enum COLOR{ red, pink, black, white, brown, dark_blue, light_gray, warm_yellow}
    enum TYPE{ solid}
    enum FONT{}

    /**
     * color pattern for other method
     *
     * @param color the name of color
     * */
    public static String colorString(COLOR color){

        switch (color){
            default:
            case black:
                return "#000000";
            case red:
                return "#C6584B";
            case pink:
                return "#D3876F";
            case white:
                return "#FFFFFF";
            case brown:
                return "#554336";
            case dark_blue:
                return "#2F2F44";
            case light_gray:
                return "#595350";
            case warm_yellow:
                return "#EADBB1";
        }
    }

    /**
     * Font output for other method
     *
     * @param
     * */
    

    /**
     * background color quick setter
     *
     * @param color the name of color needed to set as background color
     * */
    public static String backgroundColor(COLOR color){
        return "-fx-background-color: "+colorString(color)+";\n";
    }

    /**
     *
     * */
    public static String fontSet(int size, COLOR color, FONT font){
        String style = "-fx-font-size: " +size + "px;\n";

        style += "-fx-font-color: " + colorString(color) +";\n";

        return style;
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
