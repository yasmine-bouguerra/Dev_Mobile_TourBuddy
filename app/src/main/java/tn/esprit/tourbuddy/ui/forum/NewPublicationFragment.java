package tn.esprit.tourbuddy.ui.forum;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import tn.esprit.tourbuddy.R;
import androidx.fragment.app.Fragment;

public class NewPublicationFragment extends Fragment {
    private EditText editTextTitle;
    private EditText editTextContent;
    private EditText editTextAuthor;
    private Button btnSubmitPublication;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_publication, container, false);

        editTextTitle = view.findViewById(R.id.edit_text_title);
        editTextContent = view.findViewById(R.id.edit_text_content);
        editTextAuthor = view.findViewById(R.id.edit_text_author);
        btnSubmitPublication = view.findViewById(R.id.btn_submit_publication);

        btnSubmitPublication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editTextTitle.getText().toString();
                String content = editTextContent.getText().toString();
                String author = editTextAuthor.getText().toString();

                // Ici, vous pouvez traiter les données saisies par l'utilisateur
                // Par exemple, vous pouvez les enregistrer dans une base de données ou les envoyer à un serveur

                // Une fois le traitement terminé, vous pouvez naviguer vers le fragment précédent
                getParentFragmentManager().popBackStack();
            }
        });

        return view;
    }
}
