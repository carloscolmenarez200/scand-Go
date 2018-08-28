package com.cencosud.scanandgo.wallet.presentation.widget;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * A quick and easy flip view through which you can create views with two sides like credit cards,
 * poker cards, flash cards etc.
 * <p>
 * Add com.wajahatkarim3.easyflipview.EasyFlipView into your XML layouts with two direct children
 * views and you are done!
 * For more information, check http://github.com/wajahatkarim3/EasyFlipView
 *
 * @author Wajahat Karim (http://wajahatkarim.com)
 * @version 2.0.4 18/12/2017
 */
public class FlipView extends FrameLayout {

    public static final String TAG = FlipView.class.getSimpleName();

    public static final int DEFAULT_FLIP_DURATION = 400;
    private int animFlipHorizontalOutId = com.wajahatkarim3.easyflipview.R.animator.animation_horizontal_flip_out;
    private int animFlipHorizontalInId = com.wajahatkarim3.easyflipview.R.animator.animation_horizontal_flip_in;
    private int animFlipVerticalOutId = com.wajahatkarim3.easyflipview.R.animator.animation_vertical_flip_out;
    private int animFlipVerticalInId = com.wajahatkarim3.easyflipview.R.animator.animation_vertical_flip_in;

    public enum FlipState {
        FRONT_SIDE, BACK_SIDE
    }

    public static class FlipType {
        public static int HORIZONTAL = 0;
        public static int VERTICAL = 1;
    }

    private AnimatorSet mSetRightOut;
    private AnimatorSet mSetLeftIn;
    private AnimatorSet mSetTopOut;
    private AnimatorSet mSetBottomIn;
    private boolean mIsBackVisible = false;
    private View mCardFrontLayout;
    private View mCardBackLayout;
    private int flipType = com.wajahatkarim3.easyflipview.EasyFlipView.FlipType.VERTICAL;

    private int flipDuration;
    private boolean flipEnabled;

    private Context context;
    private float x1;
    private float y1;

    private FlipView.FlipState mFlipState = FlipView.FlipState.FRONT_SIDE;

    private FlipView.OnFlipAnimationListener onFlipListener = null;

    public FlipView(Context context) {
        super(context);
        this.context = context;
        init(context, null);
    }

    public FlipView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        // Setting Defaul Values
        flipDuration = DEFAULT_FLIP_DURATION;
        flipEnabled = true;
        flipType = com.wajahatkarim3.easyflipview.EasyFlipView.FlipType.VERTICAL;

        // Check for the attributes
        if (attrs != null) {
            // Attribute initialization
            final TypedArray attrArray =
                    context.obtainStyledAttributes(attrs, com.wajahatkarim3.easyflipview.R.styleable.easy_flip_view, 0, 0);
            try {
                flipDuration =
                        attrArray.getInt(com.wajahatkarim3.easyflipview.R.styleable.easy_flip_view_flipDuration, DEFAULT_FLIP_DURATION);
                flipEnabled = attrArray.getBoolean(com.wajahatkarim3.easyflipview.R.styleable.easy_flip_view_flipEnabled, true);

                flipType = attrArray.getInt(com.wajahatkarim3.easyflipview.R.styleable.easy_flip_view_flipType, com.wajahatkarim3.easyflipview.EasyFlipView.FlipType.HORIZONTAL);

                //flipType = attrArray.getString(R.styleable.easy_flip_view_flipType);
                //animFlipInId = attrArray.getResourceId(R.styleable.easy_flip_view_animFlipInId, R.animator.animation_horizontal_flip_in);
                //animFlipOutId = attrArray.getResourceId(R.styleable.easy_flip_view_animFlipOutId, R.animator.animation_horizontal_flip_out);
            } finally {
                attrArray.recycle();
            }
        }

        loadAnimations();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        if (getChildCount() > 2) {
            throw new IllegalStateException("EasyFlipView can host only two direct children!");
        }

        findViews();
        changeCameraDistance();
    }

    @Override
    public void addView(View v, int pos, ViewGroup.LayoutParams params) {
        if (getChildCount() == 2) {
            throw new IllegalStateException("EasyFlipView can host only two direct children!");
        }

        super.addView(v, pos, params);

        findViews();
        changeCameraDistance();
    }

    @Override
    public void removeView(View v) {
        super.removeView(v);

        findViews();
    }

    @Override
    public void removeAllViewsInLayout() {
        super.removeAllViewsInLayout();

        // Reset the state
        mFlipState = FlipView.FlipState.FRONT_SIDE;

        findViews();
    }

    private void findViews() {
        // Invalidation since we use this also on removeView
        mCardBackLayout = null;
        mCardFrontLayout = null;

        int childs = getChildCount();
        if (childs < 1) {
            return;
        }

        if (childs < 2) {
            // Only invalidate flip state if we have a single child
            mFlipState = FlipView.FlipState.FRONT_SIDE;

            mCardFrontLayout = getChildAt(0);
        } else if (childs == 2) {
            mCardFrontLayout = getChildAt(1);
            mCardBackLayout = getChildAt(0);
        }

        mCardFrontLayout.setVisibility(VISIBLE);

            if (mCardBackLayout != null) {
                mCardBackLayout.setVisibility(GONE);
            }
    }

    private void loadAnimations() {
        if (flipType == com.wajahatkarim3.easyflipview.EasyFlipView.FlipType.HORIZONTAL) {
            mSetRightOut =
                    (AnimatorSet) AnimatorInflater.loadAnimator(this.context, animFlipHorizontalOutId);
            mSetLeftIn =
                    (AnimatorSet) AnimatorInflater.loadAnimator(this.context, animFlipHorizontalInId);
            if (mSetRightOut == null || mSetLeftIn == null) {
                throw new RuntimeException(
                        "No Animations Found! Please set Flip in and Flip out animation Ids.");
            }

            mSetRightOut.removeAllListeners();
            mSetRightOut.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {

                    if (mFlipState == FlipView.FlipState.FRONT_SIDE) {
                        mCardBackLayout.setVisibility(GONE);
                        mCardFrontLayout.setVisibility(VISIBLE);

                        if (onFlipListener != null)
                            onFlipListener.onViewFlipCompleted(FlipView.this,FlipView.FlipState.FRONT_SIDE);
                    } else {
                        mCardBackLayout.setVisibility(VISIBLE);
                        mCardFrontLayout.setVisibility(GONE);

                        if (onFlipListener != null)
                            onFlipListener.onViewFlipCompleted(FlipView.this, FlipView.FlipState.BACK_SIDE);
                    }
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
            setFlipDuration(flipDuration);
        } else {
            mSetTopOut = (AnimatorSet) AnimatorInflater.loadAnimator(this.context, animFlipVerticalOutId);
            mSetBottomIn =
                    (AnimatorSet) AnimatorInflater.loadAnimator(this.context, animFlipVerticalInId);

            if (mSetTopOut == null || mSetBottomIn == null) {
                throw new RuntimeException(
                        "No Animations Found! Please set Flip in and Flip out animation Ids.");
            }

            mSetTopOut.removeAllListeners();
            mSetTopOut.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {

                    if (mFlipState == FlipView.FlipState.FRONT_SIDE) {
                        mCardBackLayout.setVisibility(GONE);
                        mCardFrontLayout.setVisibility(VISIBLE);

                        if (onFlipListener != null)
                            onFlipListener.onViewFlipCompleted(FlipView.this, FlipView.FlipState.FRONT_SIDE);
                    } else {
                        mCardBackLayout.setVisibility(VISIBLE);
                        mCardFrontLayout.setVisibility(GONE);

                        if (onFlipListener != null)
                            onFlipListener.onViewFlipCompleted(FlipView.this, FlipView.FlipState.BACK_SIDE);
                    }
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
            setFlipDuration(flipDuration);
        }
    }

    private void changeCameraDistance() {
        int distance = 8000;
        float scale = getResources().getDisplayMetrics().density * distance;

        if (mCardFrontLayout != null) {
            mCardFrontLayout.setCameraDistance(scale);
        }
        if (mCardBackLayout != null) {
            mCardBackLayout.setCameraDistance(scale);
        }
    }

    /**
     * Play the animation of flipping and flip the view for one side!
     */
    public void flipTheView() {
        if (!flipEnabled || getChildCount() < 2) return;

        if (flipType == com.wajahatkarim3.easyflipview.EasyFlipView.FlipType.HORIZONTAL) {
            if (mSetRightOut.isRunning() || mSetLeftIn.isRunning()) return;

            mCardBackLayout.setVisibility(VISIBLE);
            mCardFrontLayout.setVisibility(VISIBLE);

            if (mFlipState == FlipView.FlipState.FRONT_SIDE) {
                // From front to back
                mSetRightOut.setTarget(mCardFrontLayout);
                mSetLeftIn.setTarget(mCardBackLayout);
                mSetRightOut.start();
                mSetLeftIn.start();
                mIsBackVisible = true;
                mFlipState = FlipView.FlipState.BACK_SIDE;
            } else {
                // from back to front
                mSetRightOut.setTarget(mCardBackLayout);
                mSetLeftIn.setTarget(mCardFrontLayout);
                mSetRightOut.start();
                mSetLeftIn.start();
                mIsBackVisible = false;
                mFlipState = FlipView.FlipState.FRONT_SIDE;
            }
        } else {
            if (mSetTopOut.isRunning() || mSetBottomIn.isRunning()) return;

            mCardBackLayout.setVisibility(VISIBLE);
            mCardFrontLayout.setVisibility(VISIBLE);

            if (mFlipState == FlipView.FlipState.FRONT_SIDE) {
                // From front to back
                mSetTopOut.setTarget(mCardFrontLayout);
                mSetBottomIn.setTarget(mCardBackLayout);
                mSetTopOut.start();
                mSetBottomIn.start();
                mIsBackVisible = true;
                mFlipState = FlipView.FlipState.BACK_SIDE;
            } else {
                // from back to front
                mSetTopOut.setTarget(mCardBackLayout);
                mSetBottomIn.setTarget(mCardFrontLayout);
                mSetTopOut.start();
                mSetBottomIn.start();
                mIsBackVisible = false;
                mFlipState = FlipView.FlipState.FRONT_SIDE;
            }
        }
    }

    /**
     * Flip the view for one side with or without animation.
     *
     * @param withAnimation true means flip view with animation otherwise without animation.
     */
    public void flipTheView(boolean withAnimation) {
        if (getChildCount() < 2) return;

        if (flipType == com.wajahatkarim3.easyflipview.EasyFlipView.FlipType.HORIZONTAL) {
            if (!withAnimation) {
                mSetLeftIn.setDuration(0);
                mSetRightOut.setDuration(0);
                boolean oldFlipEnabled = flipEnabled;
                flipEnabled = true;

                flipTheView();

                mSetLeftIn.setDuration(flipDuration);
                mSetRightOut.setDuration(flipDuration);
                flipEnabled = oldFlipEnabled;
            } else {
                flipTheView();
            }
        } else {
            if (!withAnimation) {
                mSetBottomIn.setDuration(0);
                mSetTopOut.setDuration(0);
                boolean oldFlipEnabled = flipEnabled;
                flipEnabled = true;

                flipTheView();

                mSetBottomIn.setDuration(flipDuration);
                mSetTopOut.setDuration(flipDuration);
                flipEnabled = oldFlipEnabled;
            } else {
                flipTheView();
            }
        }
    }

    /**
     * Returns duration of flip in milliseconds!
     *
     * @return duration in milliseconds
     */
    public int getFlipDuration() {
        return flipDuration;
    }

    /**
     * Sets the flip duration (in milliseconds)
     *
     * @param flipDuration duration in milliseconds
     */
    public void setFlipDuration(int flipDuration) {
        this.flipDuration = flipDuration;
        if (flipType == com.wajahatkarim3.easyflipview.EasyFlipView.FlipType.HORIZONTAL) {
            //mSetRightOut.setDuration(flipDuration);
            mSetRightOut.getChildAnimations().get(0).setDuration(flipDuration);
            mSetRightOut.getChildAnimations().get(1).setStartDelay(flipDuration / 2);

            //mSetLeftIn.setDuration(flipDuration);
            mSetLeftIn.getChildAnimations().get(1).setDuration(flipDuration);
            mSetLeftIn.getChildAnimations().get(2).setStartDelay(flipDuration / 2);
        } else {
            mSetTopOut.getChildAnimations().get(0).setDuration(flipDuration);
            mSetTopOut.getChildAnimations().get(1).setStartDelay(flipDuration / 2);

            mSetBottomIn.getChildAnimations().get(1).setDuration(flipDuration);
            mSetBottomIn.getChildAnimations().get(2).setStartDelay(flipDuration / 2);
        }
    }

    /**
     * Returns whether flip is enabled or not!
     *
     * @return true or false
     */
    public boolean isFlipEnabled() {
        return flipEnabled;
    }

    /**
     * Enable / Disable flip view.
     *
     * @param flipEnabled true or false
     */
    public void setFlipEnabled(boolean flipEnabled) {
        this.flipEnabled = flipEnabled;
    }

    /**
     * Returns which flip state is currently on of the flip view.
     *
     * @return current state of flip view
     */
    public FlipView.FlipState getCurrentFlipState() {
        return mFlipState;
    }

    /**
     * Returns true if the front side of flip view is visible.
     *
     * @return true if the front side of flip view is visible.
     */
    public boolean isFrontSide() {
        return (mFlipState == FlipView.FlipState.FRONT_SIDE);
    }

    /**
     * Returns true if the back side of flip view is visible.
     *
     * @return true if the back side of flip view is visible.
     */
    public boolean isBackSide() {
        return (mFlipState == FlipView.FlipState.BACK_SIDE);
    }

    /**
     * Returns the current OnFlipAnimationListener. Null if no listener is set.
     * @return Returns the current OnFlipAnimationListener. Null if no listener is set.
     */
    public FlipView.OnFlipAnimationListener getOnFlipListener() {
        return onFlipListener;
    }

    /**
     * Sets the OnFlipAnimationListener for the view
     * @param onFlipListener
     */
    public void setOnFlipListener(FlipView.OnFlipAnimationListener onFlipListener) {
        this.onFlipListener = onFlipListener;
    }

    /**
     * Returns true if the Flip Type of animation is FlipType.HORIZONTAL?
     */
    public boolean isHorizontalType()
    {
        return flipType == com.wajahatkarim3.easyflipview.EasyFlipView.FlipType.HORIZONTAL;
    }

    /**
     * Returns true if the Flip Type of animation is FlipType.VERTICAL?
     */
    public boolean isVerticalType()
    {
        return flipType == com.wajahatkarim3.easyflipview.EasyFlipView.FlipType.VERTICAL;
    }

    /**
     * Sets the Flip Type of animation to FlipType.HORIZONTAL
     */
    public void setToHorizontalType()
    {
        flipType = com.wajahatkarim3.easyflipview.EasyFlipView.FlipType.HORIZONTAL;
    }

    /**
     * Sets the Flip Type of animation to FlipType.VERTICAL
     */
    public void setToVerticalType()
    {
        flipType = com.wajahatkarim3.easyflipview.EasyFlipView.FlipType.VERTICAL;
    }

    /*
    public @AnimatorRes int getAnimFlipOutId() {
        return animFlipOutId;
    }

    public void setAnimFlipOutId(@AnimatorRes int animFlipOutId) {
        this.animFlipOutId = animFlipOutId;
        loadAnimations();
    }

    public @AnimatorRes int getAnimFlipInId() {
        return animFlipInId;
    }

    public void setAnimFlipInId(@AnimatorRes int animFlipInId) {
        this.animFlipInId = animFlipInId;
        loadAnimations();
    }
    */

    /**
     * The Flip Animation Listener for animations and flipping complete listeners
     */
    public interface OnFlipAnimationListener {
        /**
         * Called when flip animation is completed.
         *
         * @param easyFlipView The current EasyFlipView instance
         * @param newCurrentSide After animation, the new side of the view. Either can be
         *                       FlipState.FRONT_SIDE or FlipState.BACK_SIDE
         */
        void onViewFlipCompleted(FlipView easyFlipView, FlipView.FlipState newCurrentSide);
    }

    /*
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (isEnabled() && flipOnTouch) {
            this.getParent().requestDisallowInterceptTouchEvent(true);
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    x1 = event.getX();
                    y1 = event.getY();
                    return true;
                case MotionEvent.ACTION_UP:
                    float x2 = event.getX();
                    float y2 = event.getY();
                    float dx = x2 - x1;
                    float dy = y2 - y1;
                    float MAX_CLICK_DISTANCE = 0.5f;
                    if ((dx >= 0 && dx < MAX_CLICK_DISTANCE) && (dy >= 0 && dy < MAX_CLICK_DISTANCE)) {
                        flipTheView();
                    }
                    return true;
            }
        } else {
            return super.onTouchEvent(event);
        }
        return super.onTouchEvent(event);
    }*/
}
