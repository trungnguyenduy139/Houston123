package com.trungnguyen.android.houston123.widget;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.trungnguyen.android.houston123.R;
import com.trungnguyen.android.houston123.anotation.ToastType;

public class ToastCustom {


    public static final int LENGTH_SHORT = 0;
    public static final int LENGTH_LONG = 1;

    @UiThread
    public static void makeText(@NonNull final Context context, final CharSequence text, final int duration, @NonNull final @ToastType String type) {
        Toast toast = Toast.makeText(context, text, duration);

        LayoutInflater inflate = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflate == null) {
            return;
        }
        View view = inflate.inflate(R.layout.transient_notification, null);
        TextView textView = view.findViewById(R.id.message);
        textView.setText(text);

        switch (type) {
            case ToastType.TYPE_ERROR:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
                    textView.setBackground(ContextCompat.getDrawable(context, R.drawable.rounded_view_toast_error));
                else
                    //noinspection deprecation
                    textView.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.rounded_view_toast_error));
                textView.setTextColor(ContextCompat.getColor(context, R.color.toast_text));

                break;
            case ToastType.TYPE_GENERAL:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
                    textView.setBackground(ContextCompat.getDrawable(context, R.drawable.rounded_view_toast_warning));
                else
                    //noinspection deprecation
                    textView.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.rounded_view_toast_warning));
                textView.setTextColor(ContextCompat.getColor(context, R.color.toast_text));

                break;
            case ToastType.TYPE_OK:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
                    textView.setBackground(ContextCompat.getDrawable(context, R.drawable.rounded_view_toast_info));
                else
                    //noinspection deprecation
                    textView.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.rounded_view_toast_info));
                textView.setTextColor(ContextCompat.getColor(context, R.color.toast_text));
                break;
        }
        toast.setView(view);
        toast.show();
    }
}
