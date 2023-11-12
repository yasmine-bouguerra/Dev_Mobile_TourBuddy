package tn.esprit.tourbuddy.ui.forum;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;
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

    private PublicationDetailFragment.OnPublicationClickListener publicationClickListener;

    private ListView listView;

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

            View view = getView();
            if (view != null) {
                listView = view.findViewById(R.id.listView);
                if (listView != null) {
                    ArrayList<HashMap<String, String>> listePublications = new ArrayList<>();

                    for (Publication publication : publications) {
                        HashMap<String, String> map = new HashMap<>();
                        map.put("titre", publication.getTitre());
                        map.put("contenu", publication.getContenu());
                        map.put("image", publication.getImageP());
                        // Ajoutez d'autres données si nécessaire
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

                    // Ajouter un OnItemClickListener pour gérer le clic sur un élément de la liste
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            // Récupérer l'ID de la publication sélectionnée si nécessaire
                            long publicationId = publications.get(position).getPid();

                            // Créer un bundle pour passer des données à PublicationDetailFragment
                            Bundle bundle = new Bundle();
                            bundle.putString("titre", publications.get(position).getTitre());
                            bundle.putString("contenu", publications.get(position).getContenu());

                            // Naviguer vers le PublicationDetailFragment en utilisant l'action définie dans le fichier de navigation
                            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
                            navController.navigate(R.id.action_forumFragment_to_publicationDetailFragment, bundle);
                        }
                    });

                }
            }
        }


    }

    private void onAddPublicationClick(View v) {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
        navController.navigate(R.id.nav_new_publication);
    }
}