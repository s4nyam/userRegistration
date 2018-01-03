package user.sanyam.userregistration;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText username;
    private ProgressDialog progressDialog;
    private EditText passwd;
    private Button register;
    private TextView textViewsignup;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        register = (Button) findViewById(R.id.buttonSignup);
        username = (EditText) findViewById(R.id.editTextEmail);
        passwd = (EditText) findViewById(R.id.editTextPassword);
        textViewsignup = (TextView) findViewById(R.id.signIn);
        register.setOnClickListener(this);
        textViewsignup.setOnClickListener(this);

    }

    private void register() {
        String usernameemail = username.getText().toString().trim();
        String password = passwd.getText().toString().trim();

        if (TextUtils.isEmpty(usernameemail)) {

            Toast.makeText(this, "please enter valid email", Toast.LENGTH_SHORT).show();
            //write again email is empty
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "please enter password", Toast.LENGTH_SHORT).show();
            //password is empty
            return;
        }
        progressDialog.setMessage("Registering the user...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(usernameemail, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "User Successfully registered!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
        finish();

    }



    @Override
    public void onClick(View view) {
    if(view==register){
        register();
    }
    if(view==textViewsignup){
        //move to the login activity;
    }
    if(view != register){
        //dont run register() and remain on the current activity.
    }

    }
}
