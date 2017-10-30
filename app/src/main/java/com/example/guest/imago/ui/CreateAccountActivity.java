package com.example.guest.imago.ui;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.BinderThread;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.guest.imago.R;
import butterknife.ButterKnife;
import butterknife.Bind;

public class CreateAccountActivity extends AppCompatActivity {

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


    }
}