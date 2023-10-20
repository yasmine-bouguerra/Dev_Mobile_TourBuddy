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

    private Button btnSubmitPublication;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_publication, container, false);

        editTextTitle = view.findViewById(R.id.edit_text_title);
        editTextContent = view.findViewById(R.id.edit_text_content);
        btnSubmitPublication = view.findViewById(R.id.btn_add_publication);

        btnSubmitPublication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editTextTitle.getText().toString();
                String content = editTextContent.getText().toString();
                getParentFragmentManager().popBackStack();
            }
        });

        return view;
    }
}
