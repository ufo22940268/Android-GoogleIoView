package me.biubiubiu.iolistview.library;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

/**
 * Created by ccheng on 6/25/14.
 */
public class IOListView extends ListView {

    public IOListView(Context context) {
        super(context);
    }

    public IOListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public IOListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void addHeaderView(int header) {
        View headerView = LayoutInflater.from(getContext()).inflate(header, this, false);
        addHeaderView(headerView);
    }
}
