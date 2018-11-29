package org.chromium.chrome.browser.preferencedemo;

import android.app.Fragment;
import android.content.Intent;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends PreferenceActivity implements
        PreferenceFragment.OnPreferenceStartFragmentCallback {


    public static final String EXTRA_SHOW_FRAGMENT = "show_fragment";
    public static final String EXTRA_SHOW_FRAGMENT_ARGUMENTS = "show_fragment_args";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //由此处开启初始参数
        String initialFragment = getIntent().getStringExtra(EXTRA_SHOW_FRAGMENT);
        Bundle initialArguments = getIntent().getBundleExtra(EXTRA_SHOW_FRAGMENT_ARGUMENTS);



        if (savedInstanceState == null) {
            //如果初始参数为null,initialFragment为Fragment
            if (initialFragment == null)
                initialFragment = TestPreferenceFragment.class.getName();
            Fragment fragment = Fragment.instantiate(this, initialFragment, initialArguments);
            getFragmentManager().beginTransaction()
                    .replace(android.R.id.content, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onPreferenceStartFragment(PreferenceFragment caller, Preference pref) {
        startFragment(pref.getFragment(), pref.getExtras());
        return true;
    }

    /**
     * 通过传递不同的参数来进行Fragment的切换，其实质还是通过Activity的切换
     * @param fragmentClass
     * @param args
     */
    public void startFragment(String fragmentClass, Bundle args) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setClass(this, getClass());
        intent.putExtra(EXTRA_SHOW_FRAGMENT, fragmentClass);
        intent.putExtra(EXTRA_SHOW_FRAGMENT_ARGUMENTS, args);
        startActivity(intent);
    }

}
