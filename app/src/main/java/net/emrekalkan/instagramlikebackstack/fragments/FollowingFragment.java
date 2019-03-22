package net.emrekalkan.instagramlikebackstack.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import net.emrekalkan.instagramlikebackstack.HandleBackStack;
import net.emrekalkan.instagramlikebackstack.R;

public class FollowingFragment extends Fragment {

    final int MENU_FOLLOWING = R.id.menu_following;

    HandleBackStack handleBackStack;

    Button btnAdd;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_following, container, false);

        handleBackStack = new HandleBackStack(getFragmentManager());

        btnAdd = view.findViewById(R.id.following_btn_add);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleBackStack.loadFragmentAndAddToBackStack(MENU_FOLLOWING, new PlaceHolderFragment());
            }
        });

        return view;
    }
}
