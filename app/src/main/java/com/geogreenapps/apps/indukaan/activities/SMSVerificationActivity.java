package com.geogreenapps.apps.indukaan.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.geogreenapps.apps.indukaan.AppController;
import com.geogreenapps.apps.indukaan.R;
import com.geogreenapps.apps.indukaan.appconfig.AppContext;
import com.geogreenapps.apps.indukaan.appconfig.Constances;
import com.geogreenapps.apps.indukaan.classes.User;
import com.geogreenapps.apps.indukaan.controllers.sessions.SessionsController;
import com.geogreenapps.apps.indukaan.network.VolleySingleton;
import com.geogreenapps.apps.indukaan.network.api_request.SimpleRequest;
import com.geogreenapps.apps.indukaan.parser.api_parser.UserParser;
import com.geogreenapps.apps.indukaan.parser.tags.Tags;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

import static com.geogreenapps.apps.indukaan.appconfig.AppConfig.APP_DEBUG;

public class SMSVerificationActivity extends GlobalActivity implements SwipeRefreshLayout.OnRefreshListener {

    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar_description)
    TextView toolbarDescription;

    @BindView(R.id.confirm)
    Button confirm;
    @BindView(R.id.sms_code)
    MaterialEditText sms_code;
    @BindView(R.id.resendCode)
    TextView resendCode;

    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;

    private User currentUser;
    private FirebaseAuth mAuth;
    private PhoneAuthCredential credential;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private final String TAG = "SMSVerificationActivity";
    private String verificationId;
    private PhoneAuthProvider.ForceResendingToken token;

    @OnClick(R.id.confirm)
    void confirmAuth(View view) {
        if (sms_code.getText() != null && !sms_code.getText().toString().trim().equals("null")) {
            credential = PhoneAuthProvider.getCredential(verificationId, sms_code.getText().toString());
            signInWithPhoneAuthCredential(credential);
        } else {
            Toast.makeText(this, getString(R.string.sms_code_not_filled), Toast.LENGTH_LONG).show();
        }
    }

    @OnClick(R.id.resendCode)
    void resendVerifCode(View view) {
        verifyPhoneNumber();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        setContentView(R.layout.activity_sms_verification);
        ButterKnife.bind(this);

        initToolbar();

        initSwipeRefresh();


        //get current user from session
        if (SessionsController.isLogged())
            currentUser = Realm.getDefaultInstance().copyFromRealm(SessionsController.getSession().getUser());
        else {
            Toast.makeText(this, getString(R.string.error_try_later), Toast.LENGTH_LONG).show();
            finish();
        }

        //text style for html
        resendCode.setText(Html.fromHtml(resendCode.getText().toString()));


        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Initialize phone auth callbacks
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                sms_code.setText(phoneAuthCredential.getSmsCode());
                refresh.setRefreshing(false);
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                Log.e(TAG, "onVerificationFailed", e);

                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    Toast.makeText(SMSVerificationActivity.this, "The sms code has expired. Please re-send the verification code to try again", Toast.LENGTH_LONG).show();
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    Toast.makeText(SMSVerificationActivity.this, "FirebaseTooManyRequestsException", Toast.LENGTH_LONG).show();
                }

                refresh.setRefreshing(false);

            }

            @Override
            public void onCodeSent(@NonNull String _verificationId, @NonNull PhoneAuthProvider.ForceResendingToken _token) {

                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                if (AppContext.DEBUG)
                    Log.e(TAG, "onCodeSent:" + verificationId);

                // Save verification ID and resending token so we can use them later
                verificationId = _verificationId;
                token = _token;

                super.onCodeSent(verificationId, _token);


            }
        };


        //send sms
        verifyPhoneNumber();


    }

    public void initToolbar() {

        toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.close_vector);
        toolbarTitle = toolbar.findViewById(R.id.toolbar_title);
        toolbarDescription = toolbar.findViewById(R.id.toolbar_description);

        toolbarDescription.setVisibility(View.GONE);
        toolbarTitle.setText(getResources().getText(R.string.sign_up));
    }


    @Override
    protected void onStart() {
        super.onStart();
    }


    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            if (AppContext.DEBUG)
                                Log.e(TAG, "signInWithCredential:success");
                            //FirebaseUser user = task.getResult().getUser();
                            phoneVerificationAPI();
                        } else {
                            // Sign in failed, display a message and update the UI
                            if (AppContext.DEBUG)
                                Log.e(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                Toast.makeText(SMSVerificationActivity.this, getString(R.string.verif_code_error), Toast.LENGTH_LONG).show();

                            }
                        }

                        refresh.setRefreshing(false);
                    }
                });
    }


    private void phoneVerificationAPI() {

        refresh.setRefreshing(true);


        SimpleRequest request = new SimpleRequest(Request.Method.POST,
                Constances.API.API_PHONE_VERIFICATION, new Response.Listener<String>() {
            @Override
            public void onResponse(final String response) {

                if (APP_DEBUG)
                    Log.e("response", response);


                try {
                    JSONObject js = new JSONObject(response);
                    UserParser mUserParser = new UserParser(js);
                    int success = Integer.parseInt(mUserParser.getStringAttr(Tags.SUCCESS));


                    if (success == 1) {

                        currentUser.setPhone_verified(1);
                        //Realm.getDefaultInstance().copyToRealmOrUpdate(currentUser);
                        SessionsController.createSession(currentUser);

                        ActivityCompat.finishAffinity(SMSVerificationActivity.this);
                        startActivity(new Intent(SMSVerificationActivity.this, MainActivity.class));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                refresh.setRefreshing(false);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                if (APP_DEBUG)
                    Log.e("ERROR", error.toString());

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("phone", currentUser.getPhone());
                params.put("user_id", String.valueOf(currentUser.getId()));

                if (APP_DEBUG)
                    Log.e("onPhoneVerification", params.toString());

                return params;
            }

        };


        request.setRetryPolicy(new DefaultRetryPolicy(SimpleRequest.TIME_OUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        VolleySingleton.getInstance(AppController.getInstance()).getRequestQueue().add(request);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (android.R.id.home == item.getItemId()) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }


    private void verifyPhoneNumber() {

        refresh.setRefreshing(true);

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(currentUser.getPhone())       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }


    private void initSwipeRefresh() {

        refresh.setOnRefreshListener(this);
        refresh.setColorSchemeResources(
                R.color.colorPrimary,
                R.color.colorPrimary,
                R.color.colorPrimary,
                R.color.colorPrimary
        );

    }

    @Override
    public void onRefresh() {


    }
}