package com.s.karpardaz.user;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.s.karpardaz.R;
import com.s.karpardaz.base.BaseViewHolder;
import com.s.karpardaz.base.model.BaseViewItem;
import com.s.karpardaz.base.ui.BaseDialogFragment;
import com.s.karpardaz.base.ui.BaseInteractionListener;
import com.s.karpardaz.base.ui.BaseItemInteractionListener;
import com.s.karpardaz.base.ui.BaseListAdapter;
import com.s.karpardaz.databinding.FragmentProfileBinding;
import com.s.karpardaz.databinding.LayoutProfileListItemBinding;

import java.util.ArrayList;

public class ProfileFragment extends BaseDialogFragment<ProfileFragment.OnProfileInteractionListener,
    FragmentProfileBinding> {

    public static final String TAG = ProfileFragment.class.getSimpleName();

    private static final int ACTION_LOGOUT = 1;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
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
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setBinding(FragmentProfileBinding.inflate(inflater, container, false));
        return getRoot();
    }

    @Override
    protected void onViewCreated(@Nullable Bundle savedInstanceState) {
        getBinding().fragmentProfileList.setLayoutManager(new LinearLayoutManager(getCtx(),
            LinearLayoutManager.VERTICAL, false));
        getBinding().fragmentProfileList.addItemDecoration(new DividerItemDecoration(getCtx(), DividerItemDecoration.VERTICAL));
        getBinding().fragmentProfileList.setItemAnimator(new DefaultItemAnimator());
        ListAdapter adapter = new ListAdapter((item, position) -> {
            switch (item.getAction()) {
                case ACTION_LOGOUT:
                    logout();
                    break;
            }
        });

        getBinding().fragmentProfileList.setAdapter(adapter);
        adapter.setItems(new ArrayList<ProfileListItem>() {{
            add(new ProfileListItem(getString(R.string.exit_title), ACTION_LOGOUT));
        }});

    }

    private void logout() {
        getListener().logout();
    }

    static class ListAdapter extends BaseListAdapter<ListAdapter.ItemViewHolder, ProfileListItem, ListAdapter.OnItemInteractionListener> {
        ListAdapter(OnItemInteractionListener listener) {
            super(listener);
        }

        @NonNull
        @Override
        public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ItemViewHolder(LayoutProfileListItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false), getListener());
        }

        @Override
        public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
            holder.bind(getItem(position), position);
        }

        @Override
        public int getItemPositionById(String id) {
            throw new UnsupportedOperationException();
        }

        static class ItemViewHolder extends BaseViewHolder<LayoutProfileListItemBinding, ProfileListItem, OnItemInteractionListener> {
            public ItemViewHolder(@NonNull LayoutProfileListItemBinding itemBinding, OnItemInteractionListener listener) {
                super(itemBinding, listener);
            }

            @Override
            public void bind(ProfileListItem item, int position) {
                getBinding().layoutProfileListItemTitle.setText(item.getText());
                getBinding().layoutProfileListItemRoot.setOnClickListener(v ->
                    getListener().onItemClicked(item, position));
            }
        }

        interface OnItemInteractionListener extends BaseItemInteractionListener<ProfileListItem> {
        }
    }

    static class ProfileListItem extends BaseViewItem {
        private final String text;
        private final int action;

        public ProfileListItem(String text, int action) {
            super("");
            this.text = text;
            this.action = action;
        }

        public String getText() {
            return text;
        }

        public int getAction() {
            return action;
        }
    }

    public interface OnProfileInteractionListener extends BaseInteractionListener {
        void logout();
    }

}
