package com.luo.utils;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

import luo.com.utils.view.textview.InputFilterUtils;
import luo.com.utils.view.textview.edittext.EditTextUtils;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText etInput = findViewById(R.id.et_main_input);
        //EditTextUtils.setMaxLength(etInput, 5);
        //EditTextUtils.setFilterEmoji(etInput);
        EditTextUtils.setFilters(etInput, InputFilterUtils.getEmojiFilter(), InputFilterUtils.getLengthFilter(5));

    }
}
