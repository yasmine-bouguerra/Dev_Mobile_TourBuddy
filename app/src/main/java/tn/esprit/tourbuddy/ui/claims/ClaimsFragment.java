package tn.esprit.tourbuddy.ui.claims;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import tn.esprit.tourbuddy.R;
import tn.esprit.tourbuddy.adapter.ClaimsAdapter;
import tn.esprit.tourbuddy.database.AppDataBase;
import tn.esprit.tourbuddy.databinding.FragmentClaimsBinding;
import tn.esprit.tourbuddy.entity.Claims;
import tn.esprit.tourbuddy.entity.Publication;

public class ClaimsFragment extends Fragment {

    private FragmentClaimsBinding binding;
    private View view;
    private RecyclerView recyclerView;
    private ClaimsAdapter claimsAdapter;
    private ListView listView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentClaimsBinding.inflate(inflater, container, false);
        view = binding.getRoot();

        recyclerView = view.findViewById(R.id.recyclerViewClaims);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(claimsAdapter);

        Button btnAddClaims = view.findViewById(R.id.buttonNewClaim);
        btnAddClaims.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddClaimsClick(v);
            }
        });

        ClaimsViewModel slideshowViewModel =
                new ViewModelProvider(this).get(ClaimsViewModel.class);

        final TextView textView = binding.textTitle;
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    private class LoadClaimsAsync extends AsyncTask<Void, Void, List<Claims>> {
        @Override
        protected List<Claims> doInBackground(Void... voids) {
            return AppDataBase.getInstance(requireContext()).claimsDao().ListClaims();
        }

        @Override
        protected void onPostExecute(List<Claims> claims) {
            super.onPostExecute(claims);

            ArrayList<HashMap<String, String>> listePublications = new ArrayList<>();

            for (Claims claims1 : claims) {
                HashMap<String, String> map = new HashMap<>();
                map.put("sujet", claims1.getSujet());
                map.put("reclamation", claims1.getRec());
                listePublications.add(map);
            }
            SimpleAdapter adapter = new SimpleAdapter(
                    getContext(),
                    listePublications,
                    android.R.layout.simple_list_item_2,
                    new String[]{"sujet", "reclamation"},
                    new int[]{android.R.id.text1, android.R.id.text2}
            );

            listView = getView().findViewById(R.id.listView);
            listView.setAdapter(adapter);
        }
    }

    private void onAddClaimsClick(View v) {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
        navController.navigate(R.id.nav_new_claims);
    }
}
