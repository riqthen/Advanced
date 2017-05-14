package com.riq.mvc.view;

import android.app.Activity;
import android.widget.TextView;

import com.riq.mvc.R;

/**
 * Created by 锐 on 2017/3/18.
 */

public class CaOutputView {
    TextView textView;

    public CaOutputView(Activity activity) {
        textView = (TextView) activity.findViewById(R.id.tv);
    }

   public void outputData(String s) {
        textView.setText(s);
    }
}
