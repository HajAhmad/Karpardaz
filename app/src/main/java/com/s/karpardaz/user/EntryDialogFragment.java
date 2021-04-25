package com.s.karpardaz.user;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.google.android.material.tabs.TabLayoutMediator;
import com.s.karpardaz.R;
import com.s.karpardaz.base.model.KTab;
import com.s.karpardaz.base.ui.BaseDialogFragment;
import com.s.karpardaz.base.ui.BaseInteractionListener;
import com.s.karpardaz.databinding.FragmentEntryBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class EntryDialogFragment extends BaseDialogFragment<EntryDialogFragment.OnEntryFragmentInteractionListener,
        FragmentEntryBinding> {

    public static final String TAG = EntryDialogFragment.class.getSimpleName();

    public static EntryDialogFragment newInstance() {
        return new EntryDialogFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.AppTheme_FullScreenDialog);
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            dialog.getWindow().setWindowAnimations(R.style.AppTheme_Slide);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        setBinding(FragmentEntryBinding.inflate(inflater, container, false));
        return getBinding().getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initPager();
    }

    private void initPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        getBinding().fragmentEntryPager.setAdapter(adapter);
        getBinding().fragmentEntryTab.setTabRippleColorResource(R.color.colorPrimaryLight);

        new TabLayoutMediator(getBinding().fragmentEntryTab, getBinding().fragmentEntryPager,
                (tab, position) -> tab.setText(adapter.getPageTitle(position)))
                .attach();
        getBinding().fragmentEntryPager.setCurrentItem(adapter.getItemCount() - 1);
    }

    private static class ViewPagerAdapter extends FragmentStateAdapter {
        private final KTab[] fragments;
        private final Context context;

        public ViewPagerAdapter(@NonNull Fragment fragment) {
            super(fragment);
            fragments = new KTab[]{
                    new KTab(RegisterFragment.newInstance(), R.string.all_register_title_long, RegisterFragment.TAG),
                    new KTab(LoginFragment.newInstance(), R.string.all_login_title_long, LoginFragment.TAG)
            };
            context = fragment.getContext();
        }

        public CharSequence getPageTitle(int position) {
            return context.getString(fragments[position].getTitleResId());
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return fragments[position].getFragment();
        }

        @Override
        public int getItemCount() {
            return fragments != null ? fragments.length : 0;
        }
    }

    public interface OnEntryFragmentInteractionListener extends BaseInteractionListener {

    }

}
