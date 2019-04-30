package edu.pwap.pp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import edu.pwap.pp.R;
import edu.pwap.pp.models.User;
import edu.pwap.pp.repositories.UserRepository;
import edu.pwap.pp.services.UserService;

public class LoggingActivity extends AppCompatActivity
{
    private EditText userNameText;
    private EditText passwordText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loging);

        userNameText = findViewById(R.id.editTextUsernameLogging);
        passwordText = findViewById(R.id.editTextPasswordLogging);
        loginButton = findViewById(R.id.buttonLogin);

        loginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String username = userNameText.getText().toString();
                String password = passwordText.getText().toString();
                User userToLogin = new User(username, password);
                login(userToLogin);
            }
        });
    }

    private void login(User user)
    {
        UserService userService = new UserService(new UserRepository());
        userService.login(user);
    }
}
