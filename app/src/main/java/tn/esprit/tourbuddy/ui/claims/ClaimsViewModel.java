package tn.esprit.tourbuddy.ui.claims;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ClaimsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ClaimsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is claims fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}