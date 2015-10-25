/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.parse.ParseAnalytics;

import java.util.ArrayList;


public class MainActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {



  private static final int PROGRESS = 0x1;

  private ProgressBar mProgress;
  private int mProgressStatus = 0;

  private Handler mHandler = new Handler();


  TextView mainTextView;
  Button mainButton;
  Button parseButton;
  EditText mainEditText;
  ListView mainListView;
  ArrayAdapter mArrayAdapter;
  ArrayList mNameList = new ArrayList();
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ParseAnalytics.trackAppOpenedInBackground(getIntent());

    mProgress = (ProgressBar) findViewById(R.id.progressBar);

    new Thread(new Runnable() {
      public void run() {
        while (mProgressStatus < 100) {
          mProgressStatus++;

          // Update the progress bar
          mHandler.post(new Runnable() {
            public void run() {
              mProgress.setProgress(mProgressStatus);
            }
          });
        }
      }
    }).start();


    mainTextView = (TextView) findViewById(R.id.textView);
    mainTextView.setText("Testing");
    mainButton = (Button) findViewById(R.id.button);
    mainButton.setOnClickListener(this);
    //parseButton = (Button) findViewById(R.id.parseButton);
    //parseButton.setOnClickListener(this);
    mainEditText = (EditText) findViewById(R.id.editText);

    // 4. Access the ListView
    mainListView = (ListView) findViewById(R.id.listView);
    // Create an ArrayAdapter for the ListView
    mArrayAdapter = new ArrayAdapter(this,
            android.R.layout.simple_list_item_1,
            mNameList);

    // Set the ListView to use the ArrayAdapter
    mainListView.setAdapter(mArrayAdapter);
    mainListView.setOnItemClickListener(this);
  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @Override
  public void onClick(View v) {
    if (v == mainButton){
      mainTextView.setText(mainEditText.getText().toString()
              + " is learning Android development!");;
      mNameList.add(mainEditText.getText().toString());
      mArrayAdapter.notifyDataSetChanged();
    }
    if (v == parseButton){
     // Intent i = new Intent(activity, StarterApplication.class);
     // activity.startActivity(i);
    }
  }

  @Override
  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    Log.d("omg android", position + ": " + mNameList.get(position));
    //mainTextView.setText(mNameList.get(position).toString());
  }
}
