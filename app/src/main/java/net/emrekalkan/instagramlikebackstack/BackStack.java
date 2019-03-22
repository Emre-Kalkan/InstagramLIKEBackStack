package net.emrekalkan.instagramlikebackstack;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.Stack;

public enum BackStack {
    INSTANCE;

    // Fragment back history stacks for every menu item
    private Stack<Fragment> homeStack = new Stack<>();
    private Stack<Fragment> searchStack = new Stack<>();
    private Stack<Fragment> createStack = new Stack<>();
    private Stack<Fragment> followingStack = new Stack<>();
    private Stack<Fragment> profileStack = new Stack<>();

    // menu stack for back history
    private Stack<Integer> menuStack = new Stack<>();

    // returns fragment stack for given menu item id
    public Stack<Fragment> getFragmentStack (int menuId) {
        switch (menuId) {
            case R.id.menu_home:
                return homeStack;

            case R.id.menu_search:
                return searchStack;

            case R.id.menu_create:
                return createStack;

            case R.id.menu_following:
                return followingStack;

            case R.id.menu_profile:
                return profileStack;

            default:
                return null;
        }
    }

    // returns fragment stack size for given menu item id
    public int getFragmentStackCount(int menuId) {
        return getFragmentStack(menuId).size();

    }

    // pushes a fragment to top of given menu item's fragment stack
    public void pushFragmentToStack(int menuId, Fragment fragment) {
        if (getFragmentStack(menuId) != null) {
            getFragmentStack(menuId).push(fragment);
        }
    }

    // removes last item on fragment stack for given menu item id
    public boolean popFragmentBackStack(int menuId, FragmentManager fragmentManager) {

        if (getFragmentStack(menuId) != null) {

            if (getFragmentStack(menuId).size() > 1) {

                fragmentManager.beginTransaction()
                        .remove(getFragmentStack(menuId).pop())
                        .commit();

                return true;
            }

        }

        return false;
    }

    // returns menu stack size
    public int getMenuStackCount() {
        return menuStack.size();
    }

    // checks if given menu id is already in the menu stack. TRUE if has, FALSE if not
    private boolean isHaveSameItem(int menuId) {
        return menuStack.contains(menuId);
    }

    // pushes menuid to menu stack
    public void updateMenuStack(int menuId) {

        if (!isHaveSameItem(menuId)) {
            menuStack.push(menuId);
        }
    }

    // removes last menu on the menu stack and
    public int popMenuBackStack() {
        int stackSize = menuStack.size();
        if (stackSize > 1) {
            menuStack.pop();
            return menuStack.peek();
        }

        return menuStack.peek();
    }


    public void clearBackStackHistories() {
        homeStack.clear();
        searchStack.clear();
        createStack.clear();
        followingStack.clear();
        profileStack.clear();
        menuStack.clear();
    }

}
