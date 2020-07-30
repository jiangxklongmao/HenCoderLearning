package com.jiangxk.annotationprocessing;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.jiangxk.lib.Binding;
import com.jiangxk.lib_annotations.BindView;

/**
 * @author jiangxk
 */
public class MainActivity extends AppCompatActivity {
    @BindView(R.id.constraintLayout)
    ConstraintLayout constraintLayout;
    @BindView(R.id.textView)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Binding.bind(this);

        constraintLayout.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        textView.setText("冷兔宝宝");

    }


}
