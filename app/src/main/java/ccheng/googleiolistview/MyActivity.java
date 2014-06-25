package ccheng.googleiolistview;

import android.app.Activity;
import android.os.Bundle;

import me.biubiubiu.iolistview.library.GoogleIOView;

public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        GoogleIOView ioView = (GoogleIOView) findViewById(R.id.io);
        ioView.getImageBackground().setImageResource(R.drawable.go_lang_item);

        ioView.setTitle("hongbosb");
    }

}
