package com.mfsoftware.home.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.mfsoftware.home.R;

import java.util.ArrayList;
import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<Category> list;

    public DataAdapter(Context context, ArrayList<Category> list) {
        this.inflater = LayoutInflater.from(context);
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.category_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        final Context context;
        final ImageView categoryPreview;
        final TextView title;

        ViewHolder(View view){
            super(view);
            view.setOnClickListener(this);

            context = view.getContext();
            categoryPreview = view.findViewById(R.id.category_preview);
            title = view.findViewById(R.id.category_title);
        }

        void bind(Category category) {
            categoryPreview.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent));
            categoryPreview.setImageResource(category.getPreview());
            title.setText(category.getTitle());
        }

        @Override
        public void onClick(View v) {
            // TODO: Производить переход к девайсам в выбранной категории
        }
    }
}
