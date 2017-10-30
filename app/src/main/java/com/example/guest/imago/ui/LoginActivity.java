package com.example.guest.imago.ui;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.guest.imago.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.emailEditText)
    EditText mEmailEditText;

    @Bind(R.id.passwordEditText)
    EditText mPasswordEditText;

    @Bind(R.id.passwordLoginButton)
    Button mPasswordLoginButton;

    @Bind(R.id.registerTextView)
    TextView mRegisterTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        Typeface blackTinBox = Typeface.createFromAsset(getAssets(), "fonts/Walkwayrounded.ttf");
        mRegisterTextView.setTypeface(blackTinBox);

        mRegisterTextView.setOnClickListener(this);
        mPasswordLoginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view == mRegisterTextView) {
            Intent intent = new Intent(LoginActivity.this, CreateAccountActivity.class);
            startActivity(intent);
            finish();
        }

        if (view == mPasswordLoginButton) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
