package tn.esprit.tourbuddy.ui.claims;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import tn.esprit.tourbuddy.databinding.FragmentActivityBinding;
import tn.esprit.tourbuddy.databinding.FragmentClaimsBinding;

public class ClaimsFragment extends Fragment {

    private FragmentClaimsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ClaimsViewModel slideshowViewModel =
                new ViewModelProvider(this).get(ClaimsViewModel.class);

        binding = FragmentClaimsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textClaims;
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}