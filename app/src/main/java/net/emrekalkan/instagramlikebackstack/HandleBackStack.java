package net.emrekalkan.instagramlikebackstack;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.Stack;

public class HandleBackStack {

    // fragment manager for fragment handling
    private FragmentManager fragmentManager;
    // BackStack class instance
    private BackStack backStack = BackStack.INSTANCE;

    // constructor with parameter FragmentManager
    public HandleBackStack(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    /*Add's new fragment and updates stack count for active menu
    * @param menuId - active menu id for stack update
    * @param fragment - fragment that will be add
    * */
    public void loadFragmentAndAddToBackStack(int menuId, Fragment fragment) {

        switch (menuId) {

            case R.id.menu_home:
                addFragmentAndBackStack(fragment, menuId);
                break;

            case R.id.menu_search:
                addFragmentAndBackStack(fragment, menuId);
                break;

            case R.id.menu_create:
                addFragmentAndBackStack(fragment, menuId);
                break;

            case R.id.menu_following:
                addFragmentAndBackStack(fragment, menuId);
                break;

            case R.id.menu_profile:
                addFragmentAndBackStack(fragment, menuId);
                break;
        }
    }

    // adds fragment and pushes fragment to stack
    private void addFragmentAndBackStack(Fragment fragment, int menuId) {

        addFragment(fragment);
        backStack.pushFragmentToStack(menuId, fragment);
    }

    // adds fragment
    private void addFragment(Fragment fragment) {

        fragmentManager.beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit();

    }

    // adds base fragments for every menu item and pushes fragments to menu's fragment stack
    public Fragment addBaseFragments(
            Fragment homeFragment,
            Fragment searchFragment,
            Fragment createFragment,
            Fragment followingFragment,
            Fragment profileFragment
    ) {

        fragmentManager.beginTransaction().add(R.id.fragment_container, homeFragment, "home").commit();
        fragmentManager.beginTransaction().add(R.id.fragment_container, searchFragment, "search").hide(searchFragment).commit();
        fragmentManager.beginTransaction().add(R.id.fragment_container, createFragment, "create").hide(createFragment).commit();
        fragmentManager.beginTransaction().add(R.id.fragment_container, followingFragment, "following").hide(followingFragment).commit();
        fragmentManager.beginTransaction().add(R.id.fragment_container, profileFragment, "profile").hide(profileFragment).commit();

        backStack.pushFragmentToStack(R.id.menu_home, homeFragment);
        backStack.pushFragmentToStack(R.id.menu_search, searchFragment);
        backStack.pushFragmentToStack(R.id.menu_create, createFragment);
        backStack.pushFragmentToStack(R.id.menu_following, followingFragment);
        backStack.pushFragmentToStack(R.id.menu_profile, profileFragment);

        return homeFragment;
    }

    // returns stack size for given menu item
    public int getFragmentStackCount(int menuId) {
        return backStack.getFragmentStackCount(menuId);
    }

    // removes last fragment for given menu item
    public boolean popFragmentBackStack(int menuId) {
        return backStack.popFragmentBackStack(menuId, fragmentManager);
    }

    // updates menu stack count
    public void updateMenuStackCount(int menuId) {
        backStack.updateMenuStack(menuId);
    }

    // returns back previous menu item
    public int popMenuStack() {
        return backStack.popMenuBackStack();
    }

    // returns menu stack size
    public int getMenuStackCount() {
        return backStack.getMenuStackCount();
    }

    // when different menu item selected, this method hides previous menu item's fragments and shows target menu item's fragments
    public void handleMenuSwitch(int activeMenuId, int targetMenuId) {

        if (activeMenuId != targetMenuId) {

            Stack<Fragment> targetFragments = backStack.getFragmentStack(targetMenuId);
            if (targetFragments != null) {
                for (Fragment fragment : targetFragments) {
                    fragmentManager.beginTransaction().show(fragment).commit();
                }
            }



            Stack<Fragment> activeFragments = backStack.getFragmentStack(activeMenuId);
            if (activeFragments != null) {
                for (Fragment fragment : activeFragments) {
                    fragmentManager.beginTransaction().hide(fragment).commit();
                }
            }

        }
    }

    // resets stacks
    public void clearBackStackHistory() {
        backStack.clearBackStackHistories();
    }
}
