package com.s.karpardaz.home.costdialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.s.karpardaz.base.BaseDialogFragment;
import com.s.karpardaz.base.BaseInteractionListener;
import com.s.karpardaz.databinding.DialogCostDetailBinding;

public class CostDetailDialog extends
        BaseDialogFragment<CostDetailDialog.OnCostDetailDialogInteractionListener, DialogCostDetailBinding> {

    private static final String KEY_COST_ID = "COST_ID";

    public static CostDetailDialog newCostDialog() {
        return new CostDetailDialog();
    }

    public static CostDetailDialog editCostDialog(String costId) {
        Bundle args = new Bundle();
        args.putString(KEY_COST_ID, costId);
        CostDetailDialog fragment = new CostDetailDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        setBinding(DialogCostDetailBinding.inflate(inflater, container, false));
        return getBinding().getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if (getArguments() != null){
            String costId = getArguments().getString(KEY_COST_ID, null);
            if (costId == null) newCostInsertion();
            else showCostInfo(costId);
        }
    }

    private void showCostInfo(String costId) {

    }

    private void newCostInsertion() {

    }

    public interface OnCostDetailDialogInteractionListener extends BaseInteractionListener {

    }
}
