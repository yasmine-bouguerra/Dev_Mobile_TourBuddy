package tn.esprit.tourbuddy.ui.forum;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.squareup.picasso.Picasso;

import tn.esprit.tourbuddy.R;
import tn.esprit.tourbuddy.database.AppDataBase;
import tn.esprit.tourbuddy.entity.Publication;

public class PublicationDetailFragment extends Fragment {

    private long publicationId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detailpub, container, false);

        Bundle bundle = getArguments();
        if (bundle != null) {
            String titre = bundle.getString("titre");
            String contenu = bundle.getString("contenu");
            String imageUrl = bundle.getString("imageUrl");
            publicationId = bundle.getLong("publicationId");

            TextView textViewTitre = view.findViewById(R.id.text_view_titre);
            TextView textViewDate = view.findViewById(R.id.text_view_date);
            EditText editTextContenu = view.findViewById(R.id.edit_text_contenu);
            ImageView imageView = view.findViewById(R.id.image_view); // Ajout de l'ImageView
            Button btnModifyPublication = view.findViewById(R.id.btn_modify_publication);
            Button btnDeletePublication = view.findViewById(R.id.btn_delete_publication);

            textViewTitre.setText(titre);
            editTextContenu.setText(contenu);

            Publication publication = AppDataBase.getAppDatabase(requireContext()).publicationDao().getPublicationById(publicationId);
            if (publication != null) {
                // Set the date in the TextView
                textViewDate.setText("Date: " + publication.getDatePub());

                Picasso.get()
                        .load(imageUrl)
                        .into(imageView);
            }

            btnModifyPublication.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onModifyPublicationClick(editTextContenu.getText().toString(), publicationId);
                }
            });

            btnDeletePublication.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onDeletePublicationClick(publicationId);
                }
            });
        }

        return view;
    }

    private void onModifyPublicationClick(String updatedContent, long publicationId) {
        Publication publication = AppDataBase.getAppDatabase(requireContext()).publicationDao().getPublicationById(publicationId);

        if (publication != null) {
            publication.setContenu(updatedContent);
            AppDataBase.getAppDatabase(requireContext()).publicationDao().modifierPublication(publication);
            Toast.makeText(requireContext(), "Publication Modified", Toast.LENGTH_SHORT).show();
        }

        navigateToForumFragment();
    }

    private void onDeletePublicationClick(long publicationId) {
        Publication publication = AppDataBase.getAppDatabase(requireContext()).publicationDao().getPublicationById(publicationId);

        if (publication != null) {
            AppDataBase.getAppDatabase(requireContext()).publicationDao().supprimerPublication(publication);
            Toast.makeText(requireContext(), "Publication Deleted", Toast.LENGTH_SHORT).show();
        }

        navigateToForumFragment();
    }

    private void navigateToForumFragment() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
        navController.popBackStack(R.id.nav_forum, false);
    }
}