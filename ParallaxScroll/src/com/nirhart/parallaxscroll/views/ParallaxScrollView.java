package com.nirhart.parallaxscroll.views;

import java.util.ArrayList;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.nirhart.parallaxscroll.R;

public class ParallaxScrollView extends ScrollView {

    private static final int DEFAULT_PARALLAX_VIEWS = 1;
    private static final float DEFAULT_INNER_PARALLAX_FACTOR = 1.9F;
    private static final float DEFAULT_PARALLAX_FACTOR = 1.9F;
    private int numOfParallaxViews = DEFAULT_PARALLAX_VIEWS;
    private float innerParallaxFactor = DEFAULT_PARALLAX_FACTOR;
    private float parallaxFactor = DEFAULT_PARALLAX_FACTOR;
    private ArrayList<ParallaxedView> parallaxedViews = new ArrayList<ParallaxedView>();
    private ViewGroup mHeaderContainer;
    private View mColorLayer;
    private View mHeaderContent;
    private OnEnableStickyViewListener onEnableStickyViewListener;

    public ParallaxScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    public ParallaxScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ParallaxScrollView(Context context) {
        super(context);
    }

    protected void init(Context context, AttributeSet attrs) {
        TypedArray typeArray = context.obtainStyledAttributes(attrs, R.styleable.ParallaxScroll);
        this.parallaxFactor = typeArray.getFloat(R.styleable.ParallaxScroll_parallax_factor, DEFAULT_PARALLAX_FACTOR);
        this.innerParallaxFactor = typeArray.getFloat(R.styleable.ParallaxScroll_inner_parallax_factor, DEFAULT_INNER_PARALLAX_FACTOR);
        this.numOfParallaxViews = typeArray.getInt(R.styleable.ParallaxScroll_parallax_views_num, DEFAULT_PARALLAX_VIEWS);
        typeArray.recycle();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        makeViewsParallax();

        mHeaderContainer = (ViewGroup) findViewById(R.id.header_container);
        mColorLayer = new View(getContext());
        mColorLayer.setLayoutParams(
                new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mHeaderContainer.addView(mColorLayer, 2);

        mHeaderContent = LayoutInflater.from(getContext()).inflate(R.layout.header_content, this, false);
        ViewGroup headerContentContainer = (ViewGroup) findViewById(R.id.header_content_container);
        headerContentContainer.addView(mHeaderContent);
    }

    private void makeViewsParallax() {
        if (getChildCount() > 0 && getChildAt(0) instanceof ViewGroup) {
            ViewGroup viewsHolder = (ViewGroup)((ViewGroup) getChildAt(0)).getChildAt(0);
            int numOfParallaxViews = Math.min(this.numOfParallaxViews, viewsHolder.getChildCount());
            for (int i = 0; i < numOfParallaxViews; i++) {
                View child = viewsHolder.getChildAt(i);
                if (child.getId() == R.id.header_content_container) {
                    continue;
                }

                ParallaxedView parallaxedView = new ScrollViewParallaxedItem(child);
                parallaxedViews.add(parallaxedView);
            }
        }
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);


        int headerHeight = mHeaderContainer.getHeight();
        headerHeight = headerHeight - mHeaderContent.getHeight();


        System.out.println("t = " + t);
        float factor = parallaxFactor;
        for (ParallaxedView parallaxedView : parallaxedViews) {
            parallaxedView.setOffset((float) t / factor);
            factor *= innerParallaxFactor;
        }

        t = t < 0 ? 0 : t;
        float p = (float)t/headerHeight;
        if (p > 1) {
            p = 1;
        }

        if (p == 1) {
            enableSticky(true);
        } else {
            enableSticky(false);
        }

        int i = (int) (0xff * p);
        System.out.println("i = " + i);
        int color = (i << 24) + 0x66ccff;
        mColorLayer.setBackgroundColor(color);
    }

    private void enableSticky(boolean b) {
//        mStickyHeaderContent.setVisibility(b ? View.VISIBLE : View.GONE);
        if (onEnableStickyViewListener != null) {
            onEnableStickyViewListener.onEnableStikyView(b);
        }
    }

    public OnEnableStickyViewListener getOnEnableStickyViewListener() {
        return onEnableStickyViewListener;
    }

    public void setOnEnableStickyViewListener(OnEnableStickyViewListener onEnableStickyViewListener) {
        this.onEnableStickyViewListener = onEnableStickyViewListener;
    }

    protected class ScrollViewParallaxedItem extends ParallaxedView {

        public ScrollViewParallaxedItem(View view) {
            super(view);
        }

        @Override
        protected void translatePreICS(View view, float offset) {
            view.offsetTopAndBottom((int) offset - lastOffset);
            lastOffset = (int) offset;
        }
    }

    public interface OnEnableStickyViewListener {
        public void onEnableStikyView(boolean enable);
    }
}
