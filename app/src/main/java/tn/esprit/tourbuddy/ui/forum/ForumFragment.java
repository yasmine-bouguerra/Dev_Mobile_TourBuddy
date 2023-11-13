package tn.esprit.tourbuddy.ui.forum;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import tn.esprit.tourbuddy.R;
import tn.esprit.tourbuddy.database.AppDataBase;
import tn.esprit.tourbuddy.entity.Publication;
public class ForumFragment extends Fragment {

    private ListView listView;
    private EditText editTextSearch;
    private List<Publication> allPublications;
    private List<Publication> displayedPublications;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forum, container, false);

        Button btnAddPublication = view.findViewById(R.id.btn_add_publication);
        btnAddPublication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddPublicationClick(v);
            }
        });

        listView = view.findViewById(R.id.listView);
        editTextSearch = view.findViewById(R.id.editTextSearch);

        new LoadPublicationsAsync().execute();

        return view;
    }

    private class LoadPublicationsAsync extends AsyncTask<Void, Void, List<Publication>> {
        @Override
        protected List<Publication> doInBackground(Void... voids) {
            return AppDataBase.getAppDatabase(requireContext()).publicationDao().getAll();
        }

        @Override
        protected void onPostExecute(List<Publication> publications) {
            super.onPostExecute(publications);

            allPublications = publications;
            displayedPublications = new ArrayList<>(allPublications);

            setupListView(displayedPublications);
            setupSearchBar();
        }
    }

    private void setupListView(List<Publication> publications) {
        ArrayList<HashMap<String, String>> listePublications = new ArrayList<>();

        for (Publication publication : publications) {
            HashMap<String, String> map = new HashMap<>();
            map.put("titre", publication.getTitre());
            map.put("contenu", publication.getContenu());
            map.put("image", publication.getImageP());
            map.put("date", publication.getDatePub());
            listePublications.add(map);
        }

        SimpleAdapter adapter = new SimpleAdapter(
                getContext(),
                listePublications,
                R.layout.list_item_forum,
                new String[]{"titre", "contenu", "imageP"},
                new int[]{R.id.textViewTitre, R.id.textViewContenu, R.id.imageViewPublication}
        );

        listView.setAdapter(adapter);

        // Add an OnItemClickListener for handling clicks on list items
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                long publicationId = displayedPublications.get(position).getPid();

                Bundle bundle = new Bundle();
                bundle.putString("titre", displayedPublications.get(position).getTitre());
                bundle.putString("contenu", displayedPublications.get(position).getContenu());
                bundle.putString("date", displayedPublications.get(position).getDatePub());
                bundle.putLong("publicationId", publicationId);

                NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
                navController.navigate(R.id.action_forumFragment_to_publicationDetailFragment, bundle);
            }
        });

        // Add a long click listener to show options like modify or delete
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                long publicationId = displayedPublications.get(position).getPid();
                Toast.makeText(requireContext(), "Long Click: Modify or Delete Publication", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    private void setupSearchBar() {
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                filterPublications(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    private void filterPublications(String query) {
        displayedPublications.clear();

        for (Publication publication : allPublications) {
            if (publication.getTitre().toLowerCase().contains(query.toLowerCase()) ||
                    publication.getContenu().toLowerCase().contains(query.toLowerCase())) {
                displayedPublications.add(publication);
            }
        }

        setupListView(displayedPublications);
    }

    private void onAddPublicationClick(View v) {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
        navController.navigate(R.id.nav_new_publication);
    }
}
