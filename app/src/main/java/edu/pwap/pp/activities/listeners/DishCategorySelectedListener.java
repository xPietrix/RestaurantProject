package edu.pwap.pp.activities.listeners;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

public class DishCategorySelectedListener extends CustomListener
{
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        this.selectedItem = parent.getItemAtPosition(position).toString();

        Toast.makeText(parent.getContext(),"Selected role: " + parent.getItemAtPosition(position).toString(),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {

    }
}
