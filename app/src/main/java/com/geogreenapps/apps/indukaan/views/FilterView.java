package com.geogreenapps.apps.indukaan.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.geogreenapps.apps.indukaan.R;
import com.geogreenapps.apps.indukaan.animation.Animation;


public class FilterView extends LinearLayout {

    private ClickListener clickListener;

    public void setClickListener(ClickListener clicklistener) {
        this.clickListener = clicklistener;
    }

    private FrameLayout leftLayout;
    private FrameLayout rightLayout;

    private ImageView leftIcon;
    private ImageView rightIcon;

    private TextView leftTitle;
    private TextView rightTitle;

    private Drawable leftDrawable;
    private Drawable rightDrawable;

    public FilterView(Context context) {
        super(context);
        setup();
    }

    public FilterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setup();
    }

    public FilterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup();
    }

    private boolean leftIsEnabled = false;
    private boolean rightIsEnabled = false;

    public boolean isLeftEnabled() {
        return leftIsEnabled;
    }

    public void setLeftIsEnabled(boolean leftIsEnabled) {
        this.leftIsEnabled = leftIsEnabled;
    }

    public boolean isRightEnabled() {
        return rightIsEnabled;
    }

    public void setRightIsEnabled(boolean rightIsEnabled) {
        this.rightIsEnabled = rightIsEnabled;
    }

    public void setEnabledLeft(){

        leftIsEnabled = true;
        rightIsEnabled = false;

        leftLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        rightLayout.setBackgroundColor(getResources().getColor(R.color.colorAccentTransparent));

        //leftLayout.setBackgroundColor(getContext().getResources().getColor(R.color.tab_colorActiveIcon));
        /*leftDrawable.setColorFilter(getContext().getResources().getColor(R.color.colorWhite) ,  PorterDuff.Mode.MULTIPLY );
        leftIcon.setImageDrawable(leftDrawable);*/
        leftTitle.setTextColor(getContext().getResources().getColor(R.color.colorWhite));

        Animation.startZoomEffect(leftTitle,100);
        Animation.startZoomEffect(leftIcon,100);

        //rightLayout.setBackgroundColor(getContext().getResources().getColor(R.color.colorPrimary));
        /*rightDrawable.setColorFilter(getContext().getResources().getColor(R.color.colorPrimary) ,  PorterDuff.Mode.MULTIPLY );
        rightIcon.setImageDrawable(rightDrawable);*/
        rightTitle.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));


    }

    public void setEnabledRight(){

        leftIsEnabled = false;
        rightIsEnabled = true;

        leftLayout.setBackgroundColor(getResources().getColor(R.color.colorAccentTransparent));
        rightLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        /*rightDrawable.setColorFilter(getContext().getResources().getColor(R.color.colorWhite) ,  PorterDuff.Mode.MULTIPLY );
        rightIcon.setImageDrawable(rightDrawable);*/
        rightTitle.setTextColor(getContext().getResources().getColor(R.color.colorWhite));

        Animation.startZoomEffect(rightTitle,100);
        Animation.startZoomEffect(rightIcon,100);

       /* leftDrawable.setColorFilter(getContext().getResources().getColor(R.color.colorPrimary) ,  PorterDuff.Mode.MULTIPLY );
        leftIcon.setImageDrawable(leftDrawable);*/
        leftTitle.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
    }


    public void setupLeft(Drawable icon,String title){

        /*leftDrawable = icon;
        leftIcon.setBackgroundDrawable(icon);*/
        leftTitle.setText(title);
    }

    public void setupRight(Drawable icon, String title){
        /*rightDrawable = icon;
        rightIcon.setBackgroundDrawable(icon);*/
        rightTitle.setText(title);
    }

    private void setup(){

        this.removeAllViews();

        LayoutInflater inflater = LayoutInflater.from(getContext());
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.filter_view2, null);
        layout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

        leftLayout = layout.findViewById(R.id.left_layout);
        rightLayout = layout.findViewById(R.id.right_layout);

        leftTitle = layout.findViewById(R.id.left_layout_title);
        rightTitle = layout.findViewById(R.id.right_layout_title);

        leftIcon = layout.findViewById(R.id.left_layout_icon);
        rightIcon = layout.findViewById(R.id.right_layout_icon);


        //Utils.setFontBold(getContext(),leftTitle);
        //Utils.setFontBold(getContext(),rightTitle);


        leftLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isLeftEnabled())
                    return;

                setEnabledLeft();
                if(clickListener!=null){
                    clickListener.leftClicked(FilterView.this);
                }
            }

        });

        rightLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isRightEnabled())
                    return;

                setEnabledRight();
                if(clickListener!=null){
                    clickListener.rightClicked(FilterView.this);
                }
            }
        });

        addView(layout);

    }

    public interface ClickListener {
        void leftClicked(FilterView f);
        void rightClicked(FilterView f);
    }

}

