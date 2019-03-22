package net.emrekalkan.instagramlikebackstack.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.emrekalkan.instagramlikebackstack.R;

public class PlaceHolderFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_place_holder, container, false);

        // if this layout's background is null then beacuse of we added fragment with .add(...) method,
        // fragment's background will be transparent and previous fragment's layout will be visible.
        // Therefore we set background color #fff

        return view;
    }
}
