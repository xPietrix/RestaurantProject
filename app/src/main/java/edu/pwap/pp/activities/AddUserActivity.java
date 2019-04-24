package edu.pwap.pp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import android.view.View.OnClickListener;

import edu.pwap.pp.R;
import edu.pwap.pp.activities.listeners.RoleSelectedListener;
import edu.pwap.pp.models.User;
import edu.pwap.pp.repositories.UserRepository;
import edu.pwap.pp.services.UserService;

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
        roleSpinner = findViewById(R.id.roleSpinner);

        listener = new RoleSelectedListener();

        addItemsOnSpinner();
        setAddButtonListener();
        addListenerOnSpinnerItemSelection();
    }

    public void addUser(String role, User user)
    {
        UserService userService = new UserService(new UserRepository());
        userService.addUser(role, user);
    }

    public void addItemsOnSpinner()
    {
        List<String> list = new ArrayList<String>();
        list.add("admin");
        list.add("waiter");
        list.add("chef");
        list.add("dietican");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roleSpinner.setAdapter(dataAdapter);
    }

    public void addListenerOnSpinnerItemSelection()
    {
        roleSpinner.setOnItemSelectedListener(listener);
    }

    public void setAddButtonListener()
    {
        addButton.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String username = userNameText.getText().toString();
                String password = passwordText.getText().toString();
                String selectedRole = listener.getSelectedRole();
                User userToAdd = new User(username, password);
                addUser(selectedRole, userToAdd);
                Toast.makeText(v.getContext(),"User added to database", Toast.LENGTH_SHORT).show();
                userNameText.setText("");
                passwordText.setText("");
            }
        });
    }
}
