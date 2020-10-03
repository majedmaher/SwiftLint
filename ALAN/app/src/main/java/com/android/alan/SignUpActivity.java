package com.android.alan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {

    //
    EditText editText_sign_up_name, editText_sign_up_password, editText_sign_up_confirm;
    Button button_sign_up, button_sign_up_sign_in;

    //
    private FirebaseAuth firebaseAuth;

    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        //
        firebaseAuth = FirebaseAuth.getInstance();
        //
        editText_sign_up_name = findViewById(R.id.editText_sign_up_name);
        editText_sign_up_password = findViewById(R.id.editText_sign_up_password);
        editText_sign_up_confirm = findViewById(R.id.editText_sign_up_confirm);
        button_sign_up = findViewById(R.id.button_sign_up);
        button_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                firebaseAuth.createUserWithEmailAndPassword(editText_sign_up_name.getText().toString(), editText_sign_up_password.getText().toString()).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //
                        if (task.isSuccessful()) {
                            //
                            setResult(RESULT_OK);
                            finish();
                        } else {
                            //
                        }
                    }
                });
            }
        });
        button_sign_up_sign_in = findViewById(R.id.button_sign_up_sign_in);
        button_sign_up_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }
}
