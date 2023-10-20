package tn.esprit.tourbuddy.ui.forum;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.util.ArrayList;
import java.util.HashMap;
import android.view.View;

import tn.esprit.tourbuddy.R;

public class ForumFragment extends Fragment {

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


        // Simulation de données
        String[] titres = {"Publication 1", "Publication 2", "Publication 3"};
        String[] contenus = {"Contenu de la publication 1", "Contenu de la publication 2", "Contenu de la publication 3"};
        String[] auteurs = {"Auteur 1", "Auteur 2", "Auteur 3"};

        ArrayList<HashMap<String, String>> listePublications = new ArrayList<>();

        for (int i = 0; i < titres.length; i++) {
            HashMap<String, String> publication = new HashMap<>();
            publication.put("titre", titres[i]);
            publication.put("contenu", contenus[i]);
            publication.put("auteur", auteurs[i]);
            listePublications.add(publication);
        }

        // Création d'un SimpleAdapter pour afficher les données dans la ListView
        SimpleAdapter adapter = new SimpleAdapter(
                getContext(),
                listePublications,
                android.R.layout.simple_list_item_2,
                new String[]{"titre", "contenu", "auteur"},
                new int[]{android.R.id.text1, android.R.id.text2}
        );

        listView = view.findViewById(R.id.listView);
        listView.setAdapter(adapter);

        // Ajout d'un écouteur de clic pour les éléments de la liste
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String, String> publication = listePublications.get(position);
                String titre = publication.get("titre");
                String contenu = publication.get("contenu");
                String auteur = publication.get("auteur");

                // Naviguer vers le Fragment de détails avec les données de la publication
                Bundle args = new Bundle();
                args.putString("titre", titre);
                args.putString("contenu", contenu);
                args.putString("auteur", auteur);

                NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
                navController.navigate(R.id.nav_publication_details, args);

            }
        });

        return view;

    }

    private void onAddPublicationClick(View v) {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
        navController.navigate(R.id.nav_new_publication);
    }


}
