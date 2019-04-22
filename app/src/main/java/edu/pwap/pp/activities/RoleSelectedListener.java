package edu.pwap.pp.activities;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

public class RoleSelectedListener implements OnItemSelectedListener
{
    private String selectedRole;

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        selectedRole = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),"Selected role: " + parent.getItemAtPosition(position).toString(),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {

    }

    public String getSelectedRole()
    {
        return this.selectedRole;
    }
}
