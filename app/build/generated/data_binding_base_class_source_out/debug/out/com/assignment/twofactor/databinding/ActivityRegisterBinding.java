// Generated by view binder compiler. Do not edit!
package com.assignment.twofactor.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.assignment.twofactor.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityRegisterBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final Button btnRegister;

  @NonNull
  public final EditText etConfirmPassword;

  @NonNull
  public final EditText etEmail;

  @NonNull
  public final EditText etPassword;

  @NonNull
  public final EditText etPhoneNumber;

  @NonNull
  public final EditText etUsername;

  private ActivityRegisterBinding(@NonNull RelativeLayout rootView, @NonNull Button btnRegister,
      @NonNull EditText etConfirmPassword, @NonNull EditText etEmail, @NonNull EditText etPassword,
      @NonNull EditText etPhoneNumber, @NonNull EditText etUsername) {
    this.rootView = rootView;
    this.btnRegister = btnRegister;
    this.etConfirmPassword = etConfirmPassword;
    this.etEmail = etEmail;
    this.etPassword = etPassword;
    this.etPhoneNumber = etPhoneNumber;
    this.etUsername = etUsername;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityRegisterBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityRegisterBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_register, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityRegisterBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnRegister;
      Button btnRegister = ViewBindings.findChildViewById(rootView, id);
      if (btnRegister == null) {
        break missingId;
      }

      id = R.id.etConfirmPassword;
      EditText etConfirmPassword = ViewBindings.findChildViewById(rootView, id);
      if (etConfirmPassword == null) {
        break missingId;
      }

      id = R.id.etEmail;
      EditText etEmail = ViewBindings.findChildViewById(rootView, id);
      if (etEmail == null) {
        break missingId;
      }

      id = R.id.etPassword;
      EditText etPassword = ViewBindings.findChildViewById(rootView, id);
      if (etPassword == null) {
        break missingId;
      }

      id = R.id.etPhoneNumber;
      EditText etPhoneNumber = ViewBindings.findChildViewById(rootView, id);
      if (etPhoneNumber == null) {
        break missingId;
      }

      id = R.id.etUsername;
      EditText etUsername = ViewBindings.findChildViewById(rootView, id);
      if (etUsername == null) {
        break missingId;
      }

      return new ActivityRegisterBinding((RelativeLayout) rootView, btnRegister, etConfirmPassword,
          etEmail, etPassword, etPhoneNumber, etUsername);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
