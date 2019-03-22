package net.emrekalkan.instagramlikebackstack;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import net.emrekalkan.instagramlikebackstack.fragments.CreateFragment;
import net.emrekalkan.instagramlikebackstack.fragments.FollowingFragment;
import net.emrekalkan.instagramlikebackstack.fragments.HomeFragment;
import net.emrekalkan.instagramlikebackstack.fragments.ProfileFragment;
import net.emrekalkan.instagramlikebackstack.fragments.SearchFragment;

public class MainActivity extends AppCompatActivity {

    HandleBackStack handleBackStack;

    //Base fragment for every menu
    Fragment homeFragment = new HomeFragment();
    Fragment searchFragment = new SearchFragment();
    Fragment createFragment = new CreateFragment();
    Fragment followingFragment = new FollowingFragment();
    Fragment profileFragment = new ProfileFragment();
    //active Fragment
    Fragment activeFragment;
    //Active menu item's id
    int activeMenuId;

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        handleBackStack = new HandleBackStack(getSupportFragmentManager());
        // clear back stack history for safer back stack handle
        handleBackStack.clearBackStackHistory();
        //Add base fragments when app first start
        activeFragment = handleBackStack.addBaseFragments(
                homeFragment,
                searchFragment,
                createFragment,
                followingFragment,
                profileFragment
        );
        //Set active menu id
        activeMenuId = R.id.menu_home;
        //Update menu stack count with main menu item which home
        handleBackStack.updateMenuStackCount(activeMenuId);


        bottomNavigationView = findViewById(R.id.bottom_navigation_view);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                //return TRUE if menu item selected, return FALSE if not

                switch (menuItem.getItemId()) {

                    case R.id.menu_home:
                        switchMenu(activeMenuId, R.id.menu_home);
                        activeMenuId = R.id.menu_home;
                        return true;

                    case R.id.menu_search:
                        switchMenu(activeMenuId, R.id.menu_search);
                        activeMenuId = R.id.menu_search;
                        return true;

                    case R.id.menu_create:
                        switchMenu(activeMenuId, R.id.menu_create);
                        activeMenuId = R.id.menu_create;
                        return true;

                    case R.id.menu_following:
                        switchMenu(activeMenuId, R.id.menu_following);
                        activeMenuId = R.id.menu_following;
                        return true;

                    case R.id.menu_profile:
                        switchMenu(activeMenuId, R.id.menu_profile);
                        activeMenuId = R.id.menu_profile;
                        return true;

                }

                return false;
            }
        });
    }

    // Switch menus
    public void switchMenu(int activeMenuId, int targetMenuId) {
        // handle menu switch with this method
        handleBackStack.handleMenuSwitch(activeMenuId, targetMenuId);
        // update menu stack count when target menu showed
        handleBackStack.updateMenuStackCount(targetMenuId);
    }

    // when back button pressed
    @Override
    public void onBackPressed() {
        // get active menu's fragment stack count. size is at least 1 because base fragments always will be shown
        int fragmentBackStackSize = handleBackStack.getFragmentStackCount(activeMenuId);

        // checks if there is a fragment that is not base fragment for active menu
        if (fragmentBackStackSize > 1) {
            // remove active menu's last fragment
            handleBackStack.popFragmentBackStack(activeMenuId);
        }
        else {
            // get menu stack size. size is at least 1 because main menu item which is home
            int menuStackSize = handleBackStack.getMenuStackCount();

            // checks if there is an another menu in the stack
            if (menuStackSize > 1) {
                // save previous menu id because when active menu is popped, we must show previous menu
                int previousMenuId = handleBackStack.popMenuStack();
                if (activeMenuId != previousMenuId)
                    bottomNavigationView.setSelectedItemId(previousMenuId);
                else {

                    bottomNavigationView.setSelectedItemId(previousMenuId);
                }
            }else{
                // if there is no fragment stack and no menu stack besides base items,
                // then calling method this will close app
                // or you could simply use finish()
                super.onBackPressed();
            }

        }
    }
}
