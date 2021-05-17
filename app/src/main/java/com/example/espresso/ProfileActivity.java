package com.example.espresso;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.parse.ParseUser;

public class ProfileActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);

        Button logoutButton = findViewById(R.id.logout_button);


        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog = new ProgressDialog(ProfileActivity.this);
                ParseUser.logOutInBackground(e -> {
                    progressDialog.dismiss();
                    if (e == null) {
                        alertDisplay(R.string.alert_title, R.string.logout);
                    }

                });
            }
        });
    }

    private void alertDisplay(final int title, final int message) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this)
                .setTitle(title)
                .setMessage(message)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, final int which) {
                        dialog.cancel();
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}