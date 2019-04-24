package edu.pwap.pp.activities.listeners;

import android.widget.AdapterView.OnItemSelectedListener;

public abstract class CustomListener implements OnItemSelectedListener
{
    protected String selectedItem;

    public String getSelectedItem()
    {
        return this.selectedItem;
    }
}
