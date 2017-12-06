package cs_3354.calendar_dbsf;

import android.graphics.Color;

/**
 * Created by Alec and Hajung on 12/2/2017.
 */

public class Theme
{
    private String name;
    private Color primaryColor;
    private Color secondaryColor;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Color getPrimaryColor()
    {
        return primaryColor;
    }

    public void setPrimaryColor(Color primaryColor)
    {
        this.primaryColor = primaryColor;
    }

    public Color getSecondaryColor()
    {
        return secondaryColor;
    }

    public void setSecondaryColor(Color secondaryColor)
    {
        this.secondaryColor = secondaryColor;
    }
}
