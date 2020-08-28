package com.architect.zhang;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.archiect.zhang.base.BaseServiceLoader;
import com.architect.zhang.common.autoservice.IWebViewService;

import java.util.ServiceLoader;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.hello).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                MainActivity.this.startActivity(new Intent(MainActivity.this, WebActivity.class));
            //    IWebViewService service = ServiceLoader.load(IWebViewService.class).iterator().next();
                IWebViewService service = BaseServiceLoader.load(IWebViewService.class);
                if (service != null) {
                    service.startWebViewActivity(MainActivity.this, "", "");
                }
            }
        });
    }
}
