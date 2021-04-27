package com.example.espresso;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameView;

    private EditText passwordView;

    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.login_activity);

        final TextView swipeRight = findViewById(R.id.swipeRight);
        swipeRight.bringToFront();

        swipeRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        usernameView = findViewById(R.id.et_email);
        passwordView = findViewById(R.id.et_password);

        final Button loginButton = findViewById(R.id.btn_login);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                boolean validationError = false;

                final StringBuilder builder = new StringBuilder("Please, insert ");
                if (isEmpty(usernameView)) {
                    validationError = true;
                    builder.append("an Email");
                }
                if (isEmpty(passwordView)) {
                    if (validationError) {
                        builder.append(" and ");
                    }
                    validationError = true;
                    builder.append("a Password");
                }
                builder.append(".");

                if (validationError) {
                    alertView(R.string.alert_error, builder.toString());
                    return;
                }

                final ProgressDialog dlg = new ProgressDialog(LoginActivity.this);
                dlg.setTitle("Please, wait a moment.");
                dlg.setMessage("Logging in...");
                dlg.show();

                ParseUser.logInInBackground(usernameView.getText().toString(), passwordView.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(final ParseUser parseUser, final ParseException parseEx) {
                        if (parseUser != null) {
                            dlg.dismiss();
                            Intent intent = new Intent(LoginActivity.this, LogoutActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        } else {
                            dlg.dismiss();
                            ParseUser.logOut();
                            Toast.makeText(LoginActivity.this, parseEx.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }

    private boolean isEmpty(final EditText text) {
        if (text.getText().toString().trim().length() > 0) {
            return false;
        } else {
            return true;
        }
    }

    private void alertView(final int title, final String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}