package org.satima.sbooks.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;
import org.satima.sbooks.AuthActivity;
import org.satima.sbooks.Constant;
import org.satima.sbooks.HomeActivity;
import org.satima.sbooks.R;
import org.satima.sbooks.User.UserInfoActivity;

import java.util.HashMap;
import java.util.Map;

public class SignUpFragment extends Fragment {

    private View view;
    private TextInputLayout layoutName, layoutEmail, layoutPhone, layoutOccupation, layoutInstituion, layoutPassword, layoutConfirm;
    private TextInputEditText txtName, txtEmail, txtPhone, txtOccupation, txtInstitution, txtPassword, txtConfirm;
    private TextView txtSignIn;
    private Button btnSignUp;
    private ProgressDialog dialog;

    public SignUpFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_sign_up,container,false);
        init();
        return view;
    }

    private void init() {
        layoutName = view.findViewById(R.id.txtLayoutNameSignUp);
        layoutEmail = view.findViewById(R.id.txtLayoutEmailSignUp);
        layoutPhone = view.findViewById(R.id.txtLayoutPhoneSignUp);
        layoutOccupation = view.findViewById(R.id.txtLayoutOccupationSignUp);
        layoutInstituion = view.findViewById(R.id.txtLayoutInstitutionSignUp);
        layoutPassword = view.findViewById(R.id.txtLayoutPasswordSignUp);
        layoutConfirm = view.findViewById(R.id.txtLayoutConfirmSignUp);

        txtName = view.findViewById(R.id.txtNameSignUp);
        txtEmail = view.findViewById(R.id.txtEmailSignUp);
        txtPhone = view.findViewById(R.id.txtPhoneSignUp);
        txtOccupation = view.findViewById(R.id.txtOccupationSignUp);
        txtInstitution = view.findViewById(R.id.txtInstitutionSignUp);
        txtPassword = view.findViewById(R.id.txtPasswordSignUp);
        txtConfirm = view.findViewById(R.id.txtConfirmSignUp);

        txtSignIn = view.findViewById(R.id.txtSignIn);
        btnSignUp = view.findViewById(R.id.btnSignUp);
        dialog = new ProgressDialog(getContext());
        dialog.setCancelable(false);

        txtSignIn.setOnClickListener(v -> {
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameAuthContainer,new SignInFragment()).commit();
        });

        btnSignUp.setOnClickListener(v -> {
            if (validate()) {
                register();
            }
        });

        txtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!txtName.getText().toString().isEmpty()){
                    layoutName.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!txtEmail.getText().toString().isEmpty()){
                    layoutEmail.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (txtPhone.getText().toString().length()>11){
                    layoutPhone.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtOccupation.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!txtOccupation.getText().toString().isEmpty()){
                    layoutOccupation.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtInstitution.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!txtInstitution.getText().toString().isEmpty()){
                    layoutInstituion.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (txtPassword.getText().toString().length()>7){
                    layoutPassword.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtConfirm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (txtConfirm.getText().toString().equals(txtPassword.getText().toString())){
                    layoutConfirm.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private boolean validate() {
        if (txtName.getText().toString().isEmpty()){
            layoutName.setErrorEnabled(true);
            layoutName.setError("Full Name is required");
            return false;
        }
        if (txtEmail.getText().toString().isEmpty()){
            layoutEmail.setErrorEnabled(true);
            layoutEmail.setError("Email is required");
            return false;
        }
        if (txtPhone.getText().toString().length()<12){
            layoutPhone.setErrorEnabled(true);
            layoutPhone.setError("Invalid format");
            return false;
        }
        if (txtOccupation.getText().toString().isEmpty()){
            layoutOccupation.setErrorEnabled(true);
            layoutOccupation.setError("Occupation is required");
            return false;
        }
        if (txtInstitution.getText().toString().isEmpty()){
            layoutInstituion.setErrorEnabled(true);
            layoutInstituion.setError("Institution is required");
            return false;
        }
        if (txtPassword.getText().toString().length()<8){
            layoutPassword.setErrorEnabled(true);
            layoutPassword.setError("Should be at least 8 characters");
            return false;
        }
        if (!txtConfirm.getText().toString().equals(txtPassword.getText().toString())){
            layoutConfirm.setErrorEnabled(true);
            layoutConfirm.setError("Doesn't match password");
            return false;
        }
        return true;
    }

    private void register() {
        dialog.setMessage("Registering");
        dialog.show();
        StringRequest request = new StringRequest(Request.Method.POST, Constant.REGISTER, response -> {
            try {
                JSONObject object = new JSONObject(response);
                if (object.getBoolean("success")){
                    JSONObject user = object.getJSONObject("user");
                    SharedPreferences userPref = getActivity().getApplicationContext().getSharedPreferences("user",getContext().MODE_PRIVATE);
                    SharedPreferences.Editor editor = userPref.edit();
                    editor.putString("token",object.getString("token"));
                    editor.putString("photo",user.getString("photo"));
                    editor.putBoolean("isLoggedIn",true);
                    editor.apply();
                    startActivity(new Intent(((AuthActivity)getContext()), UserInfoActivity.class));
                    ((AuthActivity)getContext()).finish();
                    Toast.makeText(getContext(),"Sign Up Successful", Toast.LENGTH_SHORT).show();
                }
            }catch (JSONException e) {
                e.printStackTrace();
            }
            dialog.dismiss();
        }, error -> {
            error.printStackTrace();
            dialog.dismiss();
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                map.put("name", txtName.getText().toString().trim());
                map.put("email", txtEmail.getText().toString().trim());
                map.put("phone", txtPhone.getText().toString());
                map.put("occupation", txtOccupation.getText().toString().trim());
                map.put("institution", txtInstitution.getText().toString().trim());
                map.put("password", txtPassword.getText().toString());
                return map;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(request);
    }


}
