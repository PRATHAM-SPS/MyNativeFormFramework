package com.form;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.EditText;
import android.widget.Button;
import android.content.Context;
import androidx.annotation.NonNull;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;

public class MyFormViewManager extends SimpleViewManager<View> {
    public static final String REACT_CLASS = "MyFormNative";

    @NonNull
    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @NonNull
    @Override
    public View createViewInstance(@NonNull ThemedReactContext reactContext) {
        Context context = reactContext;
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);

        EditText nameInput = new EditText(context);
        nameInput.setHint("Enter Name");
        layout.addView(nameInput);

        EditText emailInput = new EditText(context);
        emailInput.setHint("Enter Email");
        layout.addView(emailInput);

        Button submitButton = new Button(context);
        submitButton.setText("Submit");
        layout.addView(submitButton);

        return layout;
    }
}
