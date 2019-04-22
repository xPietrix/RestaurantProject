package edu.pwap.pp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.pwap.pp.R;
import edu.pwap.pp.models.User;
import edu.pwap.pp.repositories.UserRepository;
import edu.pwap.pp.services.UserService;
import retrofit2.Response;

public class AddUserActivity extends AppCompatActivity
{
    private TextView addingUserTextView;
    private TextView userNameTextView;
    private TextView passwordTextView;
    private TextView roleTextView;
    private EditText userNameText;
    private EditText passwordText;
    private Button addButton;
    private Spinner roleSpinner;
    private RoleSelectedListener listener;
    private String selectedRole;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        addingUserTextView = findViewById(R.id.textViewAddingUser);
        userNameTextView = findViewById(R.id.textViewUsername);
        passwordTextView = findViewById(R.id.textViewPassword);
        userNameText = findViewById(R.id.editTextUsername);
        passwordText = findViewById(R.id.editTextPassword);
        addButton = findViewById(R.id.buttonAddUser);
        roleTextView = findViewById(R.id.textViewRole);
        listener = new RoleSelectedListener();

        addItemsOnSpinner();
        selectedRole = listener.getSelectedRole();
        setAddButtonListener(selectedRole);

    }

    public void addUser(String role, User user)
    {
        UserService userService = new UserService(new UserRepository());
        userService.addUser(role, user);
    }

    public void addItemsOnSpinner() {

        roleSpinner = (Spinner) findViewById(R.id.roleSpinner);
        List<String> list = new ArrayList<String>();
        list.add("admin");
        list.add("waiter");
        list.add("chef");
        list.add("dietican");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roleSpinner.setAdapter(dataAdapter);
    }

    public String getSelectedRole()
    {
        String selectedRole = "";
        roleSpinner = findViewById(R.id.roleSpinner);
        RoleSelectedListener listener = new RoleSelectedListener();
        roleSpinner.setOnItemSelectedListener(listener);
        selectedRole = listener.getSelectedRole();

        return selectedRole;
    }

    public void setAddButtonListener(final String selectedRole)
    {
        addButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String username = userNameText.getText().toString();
                String password = passwordText.getText().toString();
                User userToAdd = new User(username, password);
                String role = selectedRole;
                addUser(role, userToAdd);
            }
        });
    }

    public void showNoResponseError(Response<User> response)
    {
        Toast.makeText(AddUserActivity.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
    }

    public void showOnFailureError(Throwable t)
    {
        Toast.makeText(AddUserActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
    }

    public void showSuccessfulCall()
    {
        Toast.makeText(AddUserActivity.this, "Successful call", Toast.LENGTH_SHORT).show();
    }
}
