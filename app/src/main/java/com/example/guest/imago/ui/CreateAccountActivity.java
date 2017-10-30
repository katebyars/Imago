package com.example.guest.imago.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.BinderThread;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.guest.imago.R;
import butterknife.ButterKnife;
import butterknife.Bind;

public class CreateAccountActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.createAccountTextView)
    TextView mCreateAccountTextView;

    @Bind(R.id.nameEditText)
    EditText mNameEditText;

    @Bind(R.id.emailEditText)
    EditText mEmailEditText;

    @Bind(R.id.passwordEditText)
    EditText mPasswordEditText;

    @Bind(R.id.createUserButton)
    Button mCreateUserButton;

    @Bind(R.id.loginTextView)
    TextView mLoginTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        ButterKnife.bind(this);

        Typeface blackTinBox = Typeface.createFromAsset(getAssets(), "fonts/Walkwayrounded.ttf");
        mCreateAccountTextView.setTypeface(blackTinBox);

        mLoginTextView.setOnClickListener(this);
        mCreateUserButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view == mLoginTextView) {
            Intent intent = new Intent(CreateAccountActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }

        if (view == mCreateUserButton) {
            createNewUser();
        }

    }
}