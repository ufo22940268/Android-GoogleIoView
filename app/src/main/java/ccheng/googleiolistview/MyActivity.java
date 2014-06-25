package ccheng.googleiolistview;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import me.biubiubiu.iolistview.library.GoogleIOView;
import me.biubiubiu.iolistview.library.ParallaxScrollView;

public class MyActivity extends Activity {

    private GoogleIOView mListView;
    private View mStickyView;
    private ParallaxScrollView mParallaxView;
    private int color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

//        mParallaxView = (ParallaxScrollView) findViewById(R.id.parallax);
//        mStickyView = findViewById(R.id.sticky_header_content);
//        mStickyView.setVisibility(View.GONE);
//        mStickyView.setBackgroundColor(getColor());
//        mParallaxView.setOnEnableStickyViewListener(new ParallaxScrollView.OnEnableStickyViewListener() {
//            @Override
//            public void onEnableStikyView(boolean enable) {
//                View v = findViewById(R.id.sticky_header_content);
//                v.setVisibility(enable ? View.VISIBLE : View.GONE);
//            }
//        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public int getColor() {
        return 0xff66ccff;
    }
}
