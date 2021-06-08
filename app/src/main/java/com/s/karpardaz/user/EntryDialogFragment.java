package com.s.karpardaz.user;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class EntryDialogFragment extends BaseDialogFragment<EntryDialogFragment.OnEntryFragmentInteractionListener,
    FragmentEntryBinding> implements OnRegisterInteractionListener,
    OnLoginInteractionListener,
    RecoveryPasswordFragment.OnRecoveryPasswordInteractionListener {

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
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
        @Nullable Bundle savedInstanceState) {
        setBinding(FragmentEntryBinding.inflate(inflater, container, false));
        return getBinding().getRoot();
    }

    @Override
    public void onViewCreated(@Nullable Bundle savedInstanceState) {
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener((v, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_BACK){
                Toast.makeText(getCtx(), "on back pressed.", Toast.LENGTH_SHORT).show();
                requireActivity().finishAffinity();
                return true;
            }
            return false;
        });

        initPager();
    }

    private void initPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        getBinding().fragmentEntryTab.setTabRippleColorResource(R.color.colorPrimaryLight);
        getBinding().fragmentEntryPager.setAdapter(adapter);
        new TabLayoutMediator(getBinding().fragmentEntryTab, getBinding().fragmentEntryPager,
            (tab, position) -> tab.setText(adapter.getPageTitle(position)))
            .attach();

        final RegisterFragment registerFragment = RegisterFragment.newInstance();
        registerFragment.setInteractionListener(this);
        final LoginFragment loginFragment = LoginFragment.newInstance();
        loginFragment.setInteractionListener(this);
        adapter.addPage(new KTab(registerFragment, R.string.all_register_title_long, RegisterFragment.TAG));
        adapter.addPage(new KTab(loginFragment, R.string.all_login_title_long, LoginFragment.TAG));

        getBinding().fragmentEntryPager.setCurrentItem(adapter.getItemCount() - 1);
    }

    @Override
    public void proceed() {
        getListener().proceed();
    }

    @Override
    public void openRecoverPassword() {
        RecoveryPasswordFragment fragment = RecoveryPasswordFragment.newInstance();
        fragment.setInteractionListener(this);
        fragment.show(getChildFragmentManager(), RecoveryPasswordFragment.TAG);
    }

    private static class ViewPagerAdapter extends FragmentStateAdapter {
        private final List<KTab> fragments;
        private final Context context;

        public ViewPagerAdapter(@NonNull Fragment fragment) {
            super(fragment);
            fragments = new ArrayList<>();
            notifyDataSetChanged();
            context = fragment.getContext();
        }

        public CharSequence getPageTitle(int position) {
            return context.getString(fragments.get(position).getTitleResId());
        }

        public void addPage(KTab ktab) {
            fragments.add(ktab);
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return fragments.get(position).getFragment();
        }

        @Override
        public int getItemCount() {
            return fragments != null ? fragments.size() : 0;
        }
    }

    public interface OnEntryFragmentInteractionListener extends BaseInteractionListener {

        void proceed();
    }

}
