package com.dialog;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.dialogLibray.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by diaoyonglong on 2019/1/29
 *
 * @desc 底部弹起动画dialog
 */
public class showMenuDialog extends Dialog implements View.OnClickListener {
    private Context context;
    private menuCallBack callBack;
    private LinearLayout interviewLL, waitLL, offerLL, passLL, entryLL;
    private RelativeLayout showMenuRL;
    private Handler handler;
    private ImageView closeMenuIv, interviewIv, waitIv, offerIv, passIv, entryIv;
    private TextView interviewTv;
    private List<View> showViews;
    private List<View> hideViews;
    private int candidateType;

    public showMenuDialog(Context context, menuCallBack callBack, int candidateType) {
        super(context);
        this.context = context;
        this.callBack = callBack;
        this.candidateType = candidateType;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_bottom_menu);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        try {
            int dividerID = getContext().getResources().getIdentifier("android:id/titleDivider", null, null);
            View divider = findViewById(dividerID);
            divider.setBackgroundColor(Color.TRANSPARENT);
        } catch (Exception e) {
            //上面的代码，是用来去除Holo主题的蓝色线条
            e.printStackTrace();
        }
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        handler = new Handler();
        showViews = new ArrayList<>();
        hideViews = new ArrayList<>();

        showMenuRL = (RelativeLayout) findViewById(R.id.showMenuRL);

        interviewLL = (LinearLayout) findViewById(R.id.dialog_menu_interview);
        waitLL = (LinearLayout) findViewById(R.id.dialog_menu_wait);
        offerLL = (LinearLayout) findViewById(R.id.dialog_menu_offer);
        entryLL = (LinearLayout) findViewById(R.id.dialog_menu_entry);
        passLL = (LinearLayout) findViewById(R.id.dialog_menu_pass);

        closeMenuIv = (ImageView) findViewById(R.id.dialog_show_menu);

        interviewIv = (ImageView) findViewById(R.id.dialog_menu_interview_iv);
        waitIv = (ImageView) findViewById(R.id.dialog_menu_wait_iv);
        offerIv = (ImageView) findViewById(R.id.dialog_menu_offer_iv);
        passIv = (ImageView) findViewById(R.id.dialog_menu_pass_iv);
        entryIv = (ImageView) findViewById(R.id.dialog_menu_entry_iv);

        interviewTv = (TextView) findViewById(R.id.dialog_menu_interview_text);

        showMenuRL.setOnClickListener(this);

        interviewLL.setOnClickListener(this);
        waitLL.setOnClickListener(this);
        offerLL.setOnClickListener(this);
        entryLL.setOnClickListener(this);
        passLL.setOnClickListener(this);

        interviewIv.setOnClickListener(this);
        waitIv.setOnClickListener(this);
        offerIv.setOnClickListener(this);
        passIv.setOnClickListener(this);
        entryIv.setOnClickListener(this);

        closeMenuIv.setOnClickListener(this);

        showMenu();

    }

    /**
     * 根据进入的类型控制显示隐藏的按钮
     */
    private void showMenu() {
        switch (candidateType) {
            case 0://所有选项卡
                showAllMenu();
                break;
            case 1://推荐选项卡
                showRecommendMenu();
                break;
            case 2://面试选项卡
                showInterviewMenu();
                break;
            case 3://待定选项卡
                showWaiteMenu();
                break;
            case 4://Offer选项卡
                showOfferMenu();
                break;
            case 5://入职选项卡
                showEntryMenu();
                break;
            case 6://Pass选项卡
                showPassMenu();
                interviewTv.setText("约初试");
                break;
            default:
                break;
        }
    }

    /**
     * 推荐页面该显示的按钮，约初试、PASS
     */
    private void showAllMenu() {
        showViews.clear();
        hideViews.clear();

        interviewTv.setText("约初试");
        showViews.add(interviewLL);
        showViews.add(waitLL);
        showViews.add(offerLL);
        showViews.add(entryLL);
        showViews.add(passLL);
        hideMenu();
    }

    /**
     * 推荐页面该显示的按钮，约初试、PASS
     */
    private void showRecommendMenu() {
        showViews.clear();
        hideViews.clear();

        interviewTv.setText("约初试");
        showViews.add(interviewLL);
        showViews.add(passLL);

        hideViews.add(waitLL);
        hideViews.add(offerLL);
        hideViews.add(entryLL);
        hideMenu();
    }

    /**
     * 面试页面该显示的按钮，约复试、待定、OFFER、PASS
     */
    private void showInterviewMenu() {
        showViews.clear();
        hideViews.clear();

        interviewTv.setText("约复试");
        showViews.add(interviewLL);
        showViews.add(waitLL);

        hideViews.add(entryLL);
        hideViews.add(waitLL);
        hideViews.add(offerLL);
        hideMenu();
    }

    /**
     * 待定页面该显示的按钮，约复试、OFFER、PASS
     */
    private void showWaiteMenu() {
        showViews.clear();
        hideViews.clear();

        interviewTv.setText("约复试");
        showViews.add(interviewLL);
        showViews.add(offerLL);
        showViews.add(passLL);

        hideViews.add(entryLL);
        hideViews.add(waitLL);
        hideMenu();
    }

    /**
     * Offer页面该显示的按钮，入职、PASS
     */
    private void showOfferMenu() {
        showViews.clear();
        hideViews.clear();

        showViews.add(entryLL);
        showViews.add(passLL);

        hideViews.add(interviewLL);
        hideViews.add(offerLL);
        hideViews.add(waitLL);
        hideMenu();
    }

    /**
     * 入职页面该显示的按钮，入职、PASS
     */
    private void showEntryMenu() {
        showViews.clear();
        hideViews.clear();

        showViews.add(passLL);

        hideViews.add(interviewLL);
        hideViews.add(offerLL);
        hideViews.add(waitLL);
        hideViews.add(entryLL);
        hideMenu();
    }

    /**
     * Pass页面该显示的按钮，约初试
     */
    private void showPassMenu() {
        showViews.clear();
        hideViews.clear();

        interviewTv.setText("约初试");
        showViews.add(interviewLL);

        hideViews.add(offerLL);
        hideViews.add(waitLL);
        hideViews.add(entryLL);
        hideViews.add(passLL);
        hideMenu();
    }

    private void hideMenu() {
        for (int i = 0; i < hideViews.size(); i++) {
            View child = hideViews.get(i);
            child.setVisibility(View.GONE);
        }
    }

    /**
     * 进入对话框（带动画）
     */
    private void inputDialog() {
        //进入动画
        closeMenuIv.startAnimation(AnimationUtils.loadAnimation(context, R.anim.mainactivity_rotate_right));

        for (int i = 0; i < showViews.size(); i++) {
            final View child = showViews.get(i);
            child.setVisibility(View.INVISIBLE);
            handler.postDelayed(new Runnable() {

                @Override
                public void run() {
                    child.setVisibility(View.VISIBLE);
                    ValueAnimator fadeAnim = ObjectAnimator.ofFloat(child, "translationY", 800, 0);
                    fadeAnim.setDuration(350);
                    fadeAnim.start();
                }
            }, i * 70);
        }
    }

    /**
     * 取消对话框（带动画）
     */
    private void outputDialog() {
        //退出动画
        closeMenuIv.startAnimation(AnimationUtils.loadAnimation(context, R.anim.mainactivity_rotate_left));
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                dismiss();
            }
        }, 500);

        for (int i = 0; i < showViews.size(); i++) {
            final View child = showViews.get(i);
            handler.postDelayed(new Runnable() {

                @Override
                public void run() {
                    child.setVisibility(View.VISIBLE);
                    ValueAnimator fadeAnim = ObjectAnimator.ofFloat(child, "translationY", 0, 800);
                    fadeAnim.setDuration(350);
                    fadeAnim.start();
                }
            }, i * 70);
        }

    }


    @Override
    public void show() {
        super.show();
        inputDialog();
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.showMenuRL) {
            outputDialog();
            callBack.onPressBtn(0);

        } else if (i == R.id.dialog_menu_interview) {
            outputDialog();
            callBack.onPressBtn(0);

        } else if (i == R.id.dialog_menu_wait) {
            outputDialog();
            callBack.onPressBtn(0);

        } else if (i == R.id.dialog_menu_offer) {
            outputDialog();
            callBack.onPressBtn(0);

        } else if (i == R.id.dialog_menu_entry) {
            outputDialog();
            callBack.onPressBtn(0);

        } else if (i == R.id.dialog_menu_pass) {
            outputDialog();
            callBack.onPressBtn(0);

        } else if (i == R.id.dialog_show_menu) {
            outputDialog();
            callBack.onPressBtn(0);

        } else if (i == R.id.dialog_menu_interview_iv) {
            outputDialog();
            callBack.onPressBtn(interViewStatus(candidateType));

        } else if (i == R.id.dialog_menu_wait_iv) {
            outputDialog();
            callBack.onPressBtn(waitStatus(candidateType));

        } else if (i == R.id.dialog_menu_offer_iv) {
            outputDialog();
            callBack.onPressBtn(offerStatus(candidateType));

        } else if (i == R.id.dialog_menu_pass_iv) {
            callBack.onPressBtn(passStatus(candidateType));
            outputDialog();

        } else if (i == R.id.dialog_menu_entry_iv) {
            callBack.onPressBtn(entryStatus(candidateType));
            outputDialog();

        } else {
        }
    }

    /**
     * 面试状态
     *
     * @param candidateType
     * @return
     */
    private int interViewStatus(int candidateType) {
        if (candidateType == 1 || candidateType == 6) {//推荐，pass列表点进来的
            return 2;//初试
        }
        return 3;//复试
    }

    /**
     * pass状态
     *
     * @param candidateType
     * @return
     */
    private int passStatus(int candidateType) {
        if (candidateType == 5) {//入职列表点进来的
            return 7;//离职
        }
        return 8;//pass
    }

    /**
     * offer状态
     *
     * @param candidateType
     * @return
     */
    private int offerStatus(int candidateType) {//面试，待定列表点进来的
        return 5;//offer
    }

    /**
     * 入职状态
     *
     * @param candidateType
     * @return
     */
    private int entryStatus(int candidateType) {//offer列表点进来的
        return 6;//入职
    }

    /**
     * 待定状态
     *
     * @param candidateType
     * @return
     */
    private int waitStatus(int candidateType) {//面试列表点进来的
        return 4;//待定
    }

    public interface menuCallBack {
        void onPressBtn(int id);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return true;
    }

}
