package com.cqu.shixun.tingwoshuo.widget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.cqu.shixun.tingwoshuo.R;

/**
 * Created by engineer on 2016/9/21.
 */

public class ListItemMenu extends PopupWindow {
    private Context mContext ;
    private LinearLayout quit,share;
    public boolean touched;

    public ListItemMenu(int width, int height, Context mContext) {
        super(width, height);
        this.mContext = mContext;
        InitView();
    }

    private void InitView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_menu, null);
        setContentView(view);
        setFocusable(true);
        setBackgroundDrawable(new ColorDrawable(00000000));
        quit = (LinearLayout) view.findViewById(R.id.hidequestion);
        share = (LinearLayout) view.findViewById(R.id.share);
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                touched=true;
                dismiss();
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }
}
