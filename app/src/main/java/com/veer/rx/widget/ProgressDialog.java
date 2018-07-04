package com.veer.rx.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.veer.rx.R;


/**
 * 加载框
 */ 
public class ProgressDialog extends Dialog {

	private Animation anim;

    public ProgressDialog(Context context) {
        super(context, R.style.Theme_dialog);
        anim = AnimationUtils.loadAnimation(context, R.anim.round_loading);
    }

    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        setContentView(R.layout.dialog_loading_indicator);


        ImageView loadingIv = (ImageView)findViewById(R.id.loading_iv);
        loadingIv.startAnimation(anim);
    }
    

    public static ProgressDialog show(Activity context) {
    	if (context == null)
    		return null;
        try {
            ProgressDialog dialog = new ProgressDialog(context);
            dialog.show();
            return dialog;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static void dissmissDialog(ProgressDialog dialog) {
    	if (dialog != null && dialog.getWindow() != null &&
    			dialog.isShowing()) {
            try {
            	dialog.dismiss();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
