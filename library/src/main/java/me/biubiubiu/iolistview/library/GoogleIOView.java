package me.biubiubiu.iolistview.library;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

/**
 * Created by ccheng on 6/25/14.
 */
public class GoogleIOView extends RelativeLayout {

    private View mStickyView;
    private View mScrollContainer;
    private ParallaxScrollView mParallaxView;

    public GoogleIOView(Context context) {
        super(context);
    }

    public GoogleIOView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GoogleIOView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        LayoutInflater layoutInflater = LayoutInflater.from(getContext());

        mScrollContainer = layoutInflater.inflate(R.layout.io_scroll_view, this, false);
        addView(mScrollContainer);
        mParallaxView = (ParallaxScrollView) mScrollContainer.findViewById(R.id.parallax);
        mParallaxView.setColor(getColor());

        mStickyView = layoutInflater.inflate(R.layout.io_sticky_view, this, false);
        mStickyView.setVisibility(View.GONE);
        mStickyView.setBackgroundColor(getColor());
        addView(mStickyView);

        mParallaxView.setOnEnableStickyViewListener(new ParallaxScrollView.OnEnableStickyViewListener() {
            @Override
            public void onEnableStikyView(boolean enable) {
                mStickyView.setVisibility(enable ? View.VISIBLE : View.GONE);
            }
        });
    }

    public int getColor() {
        return Color.RED;
    }
}
