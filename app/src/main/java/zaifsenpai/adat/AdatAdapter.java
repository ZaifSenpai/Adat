package zaifsenpai.adat;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdatAdapter extends RecyclerView.Adapter<AdatAdapter.ViewHolder> {
    Context mContext;
    Adats adats;
    RecyclerMethods recyclerMethods;

    public AdatAdapter(Context mContext, Adats adats, RecyclerMethods recyclerMethods) {
        this.mContext = mContext;
        this.adats = adats;
        this.recyclerMethods = recyclerMethods;
    }

    public void setAdats(Adats adats) {
        this.adats = adats;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_adat, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerMethods.add(position);
            }
        });
        recyclerMethods.setRandomImage(holder.background, position);
        holder.count.setText(adats.getCount().get(position) + "");
        holder.adat.setText(adats.getNames().get(position));

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerMethods.delete(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return adats.getNames().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout layout;
        ImageView background;
        TextView count;
        TextView adat;
        TextView delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            layout = itemView.findViewById(R.id.layout);
            delete = itemView.findViewById(R.id.delete);
            adat = itemView.findViewById(R.id.adat);
            count = itemView.findViewById(R.id.count);
            background = itemView.findViewById(R.id.background);
        }
    }
}
