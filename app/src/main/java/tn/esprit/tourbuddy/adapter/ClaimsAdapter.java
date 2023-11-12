package tn.esprit.tourbuddy.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tn.esprit.tourbuddy.R;
import tn.esprit.tourbuddy.entity.Claims;

public class ClaimsAdapter extends RecyclerView.Adapter<ClaimsAdapter.ClaimViewHolder> {

    public List<Claims> claimList;

    public ClaimsAdapter(List<Claims> claimList) {
        this.claimList = claimList;
    }

    @NonNull
    @Override
    public ClaimViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_claims, parent, false);
        return new ClaimViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClaimViewHolder holder, int position) {
        Claims claim = claimList.get(position);
        holder.bind(claim);
    }

    @Override
    public int getItemCount() {
        return claimList.size();
    }

    public class ClaimViewHolder extends RecyclerView.ViewHolder {

        private TextView textSubject;
        private TextView textDescription;

        public ClaimViewHolder(@NonNull View itemView) {
            super(itemView);
            textSubject = itemView.findViewById(R.id.editTextSubject);
            textDescription = itemView.findViewById(R.id.editTextDescription);
        }

        public void bind(Claims claim) {
            textSubject.setText(claim.getSujet());
            textDescription.setText(claim.getRec());
        }
    }
}
