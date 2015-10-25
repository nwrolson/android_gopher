/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;

import android.app.Application;
import android.net.ParseException;
import android.util.Log;
import android.widget.Toast;

import com.parse.FunctionCallback;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseCloud;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.ParseInstallation;

import java.util.HashMap;
import java.util.Map;


public class StarterApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    Parse.enableLocalDatastore(this);
    Parse.initialize(this, "i7qyeUIKYi1KT3rhTq9QOmTYaVAsuFxq44wRVYnW", "CWzNEsy1pp5acFxVVq2m9YEvTugNKEvxhE2ZUkCe");
    ParseInstallation.getCurrentInstallation().saveInBackground();

    //ParseObject testObject = new ParseObject("TestObject");
    //testObject.put("test", "hi friends");
    //testObject.saveInBackground();
    Log.d("omg android", "test");


      HashMap<String, Object> dict = new HashMap<String, Object>();

      //Toast.makeText(State.mainContext, "contacting...", Toast.LENGTH_SHORT).show();

      ParseCloud.callFunctionInBackground(
              "getCampuses",
              dict,
              new FunctionCallback<Object>() {
                  @Override
                  public void done(Object object, com.parse.ParseException e) {
                      if (e == null){
                          //HashMap<String, Object> campuses = (HashMap) object;
                          Log.d("omg android", "it worked I think");
                          /*for (String key : object.keySet()) {
                              Log.d("omg android", key + " " + object.get(key));
                          }*/
                      }
                      else {
                          e.printStackTrace();
                          Log.d("omg android", "function failed");
                      }
                  }
              });

      /*ParseCloud.callFunctionInBackground("getCampuses", dict, new FunctionCallback< Map<String, Object> >() {
          @Override
          public void done(Map<String, Object> object, com.parse.ParseException e) {
              if (e == null){
                  for (String key : object.keySet()) {
                      Log.d("omg android", key + " " + object.get(key));
                  }
              }
              else {
                  e.printStackTrace();
                  Log.d("omg android", "function failed");
              }
          }
      });*/


      ParseUser.enableAutomaticUser();
    ParseACL defaultACL = new ParseACL();
    // Optionally enable public read access.
    // defaultACL.setPublicReadAccess(true);
      ParseACL.setDefaultACL(defaultACL, true);
  }
}
