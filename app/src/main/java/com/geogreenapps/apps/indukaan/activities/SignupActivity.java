package com.geogreenapps.apps.indukaan.activities;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Html;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.geogreenapps.apps.indukaan.AppController;
import com.geogreenapps.apps.indukaan.GPS.GPStracker;
import com.geogreenapps.apps.indukaan.R;
import com.geogreenapps.apps.indukaan.animation.ImageLoaderAnimation;
import com.geogreenapps.apps.indukaan.appconfig.AppContext;
import com.geogreenapps.apps.indukaan.appconfig.Constances;
import com.geogreenapps.apps.indukaan.classes.Setting;
import com.geogreenapps.apps.indukaan.classes.User;
import com.geogreenapps.apps.indukaan.controllers.SettingsController;
import com.geogreenapps.apps.indukaan.controllers.sessions.GuestController;
import com.geogreenapps.apps.indukaan.controllers.sessions.SessionsController;
import com.geogreenapps.apps.indukaan.helper.CommunFunctions;
import com.geogreenapps.apps.indukaan.network.ServiceHandler;
import com.geogreenapps.apps.indukaan.network.VolleySingleton;
import com.geogreenapps.apps.indukaan.network.api_request.SimpleRequest;
import com.geogreenapps.apps.indukaan.parser.api_parser.UserParser;
import com.geogreenapps.apps.indukaan.parser.tags.Tags;
import com.geogreenapps.apps.indukaan.utils.ImageUtils;
import com.geogreenapps.apps.indukaan.utils.MessageDialog;
import com.geogreenapps.apps.indukaan.utils.Translator;
import com.geogreenapps.apps.indukaan.utils.Utils;
import com.geogreenapps.apps.indukaan.views.CustomDialog;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.geogreenapps.apps.indukaan.appconfig.AppConfig.APP_DEBUG;

public class SignupActivity extends GlobalActivity implements View.OnClickListener, AdapterView.OnItemClickListener, ImageUtils.PrepareImagesData.OnCompressListner {

    private Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar_description)
    TextView toolbarDescription;


    @BindView(R.id.userimage)
    CircularImageView userimage;
    @BindView(R.id.getImage)
    ImageView getImage;
    @BindView(R.id.phone)
    MaterialEditText phone;
    @BindView(R.id.next)
    Button next;
    @BindView(R.id.infos_layout)
    LinearLayout infosLayout;
    @BindView(R.id.back)
    Button back;
    @BindView(R.id.email)
    MaterialEditText email;
    @BindView(R.id.name)
    MaterialEditText name;
    @BindView(R.id.login)
    MaterialEditText login;
    @BindView(R.id.password)
    MaterialEditText password;
    @BindView(R.id.cpassword)
    MaterialEditText cpassword;
    @BindView(R.id.signup)
    Button signup;
    @BindView(R.id.connect_layout)
    LinearLayout connectLayout;
    //init request http
    private RequestQueue queue;
    private CustomDialog mDialogError;
    private ProgressDialog mPdialog;
    private GPStracker gps;
    private final int GALLERY_REQUEST = 103;
    private Uri imageToUpload = null;
    private final String loadedImageId = "";

    public static String createImageFile(Context contxt) throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = contxt.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents

        return image.getAbsolutePath();
    }

    public void initToolbar() {

        toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(false);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbarTitle = toolbar.findViewById(R.id.toolbar_title);
        toolbarDescription = toolbar.findViewById(R.id.toolbar_description);

        toolbarDescription.setVisibility(View.GONE);
        toolbarTitle.setText(getResources().getText(R.string.sign_up));
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);

        initToolbar();

        gps = new GPStracker(this);

        if (SessionsController.isLogged()) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

        AppController application = (AppController) getApplication();

        queue = VolleySingleton.getInstance(this).getRequestQueue();
        signup.setOnClickListener(this);
        next.setOnClickListener(this);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connectLayout.setVisibility(View.GONE);
                infosLayout.setVisibility(View.VISIBLE);
            }
        });

        getImage = findViewById(R.id.getImage);
        getImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFromGallery();
            }
        });

        ActivityCompat.requestPermissions(SignupActivity.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

    }

    private boolean checkUserConnect() {

        return !login.getText().toString().equals("") &&
                !password.getText().toString().equals("");
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.signup) {

            if (checkUserConnect()) {

                if (ServiceHandler.isNetworkAvailable(this)) {

                    doSignup();

                } else {
                    ServiceHandler.showSettingsAlert(this);
                }

            } else
                Toast.makeText(this, getString(R.string.fill_all_fields), Toast.LENGTH_LONG).show();

        }

    }

    private void doSignup() {

        signup.setEnabled(false);

        mPdialog = new ProgressDialog(this);
        mPdialog.setMessage("Loading ...");
        mPdialog.setCancelable(false);
        mPdialog.show();

        int guest_id = 0;

        if (GuestController.isStored())
            guest_id = GuestController.getGuest().getId();


        final double lat = gps.getLatitude();
        final double lng = gps.getLongitude();

        final int finalGuest_id = guest_id;
        SimpleRequest request = new SimpleRequest(Request.Method.POST,
                Constances.API.API_USER_SIGNUP, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                signup.setEnabled(true);

                mPdialog.dismiss();

                try {

                    if (APP_DEBUG) {
                        Log.e("response", response);
                    }

                    JSONObject js = new JSONObject(response);
                    UserParser mUserParser = new UserParser(js);
                    int success = Integer.parseInt(mUserParser.getStringAttr(Tags.SUCCESS));

                    if (success == 1) {

                        List<User> list = mUserParser.getUser();
                        if (list.size() > 0) {

                            if (APP_DEBUG)
                                Log.e("__", "logged " + list.get(0).getUsername());

                            if (imageToUpload != null)
                                uploadImage(list.get(0).getId());


                            SessionsController.createSession(list.get(0));

                            Setting defaultAppSetting = SettingsController.findSettingFiled("USER_PHONE_VERIFICATION");
                            if (defaultAppSetting != null && defaultAppSetting.getValue().equals("1"))
                                startActivity(new Intent(SignupActivity.this, SMSVerificationActivity.class));
                            else {
                                startActivity(new Intent(SignupActivity.this, MainActivity.class));                                ActivityCompat.finishAffinity(SignupActivity.this);
                                ActivityCompat.finishAffinity(SignupActivity.this);

                            }
                        }

                    } else {


                        Map<String, String> errors = mUserParser.getErrors();


                        MessageDialog.newDialog(SignupActivity.this).onCancelClick(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                MessageDialog.getInstance().hide();
                            }
                        }).onOkClick(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                MessageDialog.getInstance().hide();
                            }
                        }).setContent(Translator.print(convertMessages(errors), "Message error")).show();

                    }


                } catch (JSONException e) {
                    e.printStackTrace();

                    Map<String, String> errors = new HashMap<String, String>();
                    errors.put("JSONException:", "Try later \"Json parser\"");

                    MessageDialog.newDialog(SignupActivity.this).onCancelClick(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            MessageDialog.getInstance().hide();
                        }
                    }).onOkClick(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            MessageDialog.getInstance().hide();
                        }
                    }).setContent(Translator.print(convertMessages(errors), "Message error")).show();


                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (APP_DEBUG) {
                    Log.e("ERROR", error.toString());
                }

                mPdialog.dismiss();
                Map<String, String> errors = new HashMap<String, String>();

                errors.put("NetworkException:", getString(R.string.check_nework));
                mDialogError = showErrors(errors);
                mDialogError.setTitle(R.string.network_error);

                signup.setEnabled(true);

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("password", password.getText().toString().trim());
                params.put("username", login.getText().toString().trim());

                params.put("name", name.getText().toString().trim());
                params.put("phone", phone.getText().toString().trim());
                params.put("email", email.getText().toString());

                params.put("image", loadedImageId);
                params.put("lat", String.valueOf(lat));
                params.put("lng", String.valueOf(lng));


                params.put("token", Utils.getToken(AppController.getInstance()));
                params.put("mac_adr", ServiceHandler.getMacAddr());
                params.put("auth_type", "mobile");


                params.put("guest_id", String.valueOf(finalGuest_id));

                if (APP_DEBUG) {
                    Log.e("__params__", params.toString());
                }

                return params;

            }

        };


        request.setRetryPolicy(new DefaultRetryPolicy(SimpleRequest.TIME_OUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(request);

    }

    private void uploadImage(final int uid) {

        Toast.makeText(this, getString(R.string.fileUploading), Toast.LENGTH_LONG).show();

        SimpleRequest request = new SimpleRequest(Request.Method.POST,
                Constances.API.API_USER_UPLOAD64, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                // pdialog.dismiss();
                try {

                    if (APP_DEBUG)
                        Log.e("signup", response);
                    JSONObject js = new JSONObject(response);

                    UserParser mUserParser = new UserParser(js);
                    int success = Integer.parseInt(mUserParser.getStringAttr(Tags.SUCCESS));
                    if (success == 1) {

                        final List<User> list = mUserParser.getUser();
                        if (list.size() > 0) {
                            SessionsController.updateSession(list.get(0));
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (APP_DEBUG) {
                    Log.e("ERROR", error.toString());
                    Toast.makeText(SignupActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                }
                //pdialog.dismiss();

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                Bitmap bm = BitmapFactory.decodeFile(imageToUpload.getPath());
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
                byte[] b = baos.toByteArray();
                String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
                params.put("image", encodedImage);

                params.put("int_id", String.valueOf(uid));
                params.put("type", "user");

                //do else
                params.put("module_id", String.valueOf(uid));
                params.put("module", "user");

                return params;
            }

        };


        request.setRetryPolicy(new DefaultRetryPolicy(SimpleRequest.TIME_OUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(request);

    }

    public String convertMessages(Map<String, String> errors) {
        String text = "";
        for (String key : errors.keySet()) {
            if (!text.equals(""))
                text = text + "<br>";


            text = text + "#" + errors.get(key);
        }

        return text;
    }

    public CustomDialog showErrors(Map<String, String> errors) {
        final CustomDialog dialog = new CustomDialog(this);

        dialog.setContentView(R.layout.fragment_dialog_costum);

        dialog.setCancelable(false);


        String text = "";
        for (String key : errors.keySet()) {
            if (!text.equals(""))
                text = text + "<br>";


            text = text + "#" + errors.get(key);
        }

        Button ok = dialog.findViewById(R.id.ok);
        Button cancel = dialog.findViewById(R.id.cancel);

        TextView msgbox = dialog.findViewById(R.id.msgbox);

        if (!text.equals("")) {
            msgbox.setText(Html.fromHtml(text));
        }
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        cancel.setVisibility(View.GONE);


        dialog.show();

        return dialog;

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onBackPressed() {

        if (connectLayout.isShown() && !infosLayout.isShown()) {
            connectLayout.setVisibility(View.GONE);
            infosLayout.setVisibility(View.VISIBLE);
        } else {
            super.onBackPressed();
            Intent i = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(i);
            overridePendingTransition(R.anim.righttoleft_enter, R.anim.righttoleft_exit);

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK && requestCode == GALLERY_REQUEST) {

            try {

                if (resultCode == Activity.RESULT_OK) {
                    try {
                        final Uri imageUri = data.getData();
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        Cursor cursor = getContentResolver().query(imageUri, filePathColumn, null, null, null);
                        cursor.moveToFirst();
                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        String picturePath = cursor.getString(columnIndex);
                        cursor.close();

                        String createNewFileDest = CommunFunctions.createImageFile(this);
                        new ImageUtils.PrepareImagesData(
                                this,
                                picturePath,
                                bitmap,
                                createNewFileDest,
                                this).execute();

                        userimage.setImageBitmap(bitmap);
                    } catch (IOException e) {

                        if (AppContext.DEBUG)
                            e.printStackTrace();
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "You haven't picked Image", Toast.LENGTH_LONG).show();
                }

            } catch (Exception e) {
                if (AppContext.DEBUG)
                    e.printStackTrace();
            }


        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (android.R.id.home == item.getItemId()) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    protected void getFromGallery() {
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, GALLERY_REQUEST);
    }

    @Override
    public void onCompressed(String newPath, String oldPath) {

        if (APP_DEBUG)
            Toast.makeText(this, "PATH:" + newPath, Toast.LENGTH_SHORT).show();

        File mFile = new File(newPath);

        Glide.with(AppController.getInstance()).load(mFile).centerCrop()
                .placeholder(R.drawable.profile_placeholder)
                .placeholder(ImageLoaderAnimation.glideLoader(this))
                .into(userimage);

        imageToUpload = Uri.parse(newPath);
    }

}
