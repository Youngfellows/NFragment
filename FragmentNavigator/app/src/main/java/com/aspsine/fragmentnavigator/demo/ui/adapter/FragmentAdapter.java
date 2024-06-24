package com.aspsine.fragmentnavigator.demo.ui.adapter;

import androidx.fragment.app.Fragment;

import com.aspsine.fragmentnavigator.FragmentNavigatorAdapter;
import com.aspsine.fragmentnavigator.demo.ui.fragment.ContactsFragment;
import com.aspsine.fragmentnavigator.demo.ui.fragment.MainFragment;
import com.aspsine.fragmentnavigator.demo.ui.fragment.SettingsFragment;

/**
 * Created by aspsine on 16/3/31.
 */
public class FragmentAdapter implements FragmentNavigatorAdapter {

    private static final String TABS[] = {"Chats", "Contacts", "Circle", "Settings"};

    @Override
    public Fragment onCreateFragment(int position) {
        if (position == 1) {
            return ContactsFragment.newInstance(position);
        } else if (position == 3) {
            return SettingsFragment.newInstance(position);
        }
        return MainFragment.newInstance(TABS[position]);
    }

    @Override
    public String getTag(int position) {
        if (position == 1) {
            return ContactsFragment.TAG;
        }
        return MainFragment.TAG + TABS[position];
    }

    @Override
    public int getCount() {
        return TABS.length;
    }
}
