package com.simplyillustrate.simplyillustrate.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.simplyillustrate.simplyillustrate.R;
import com.simplyillustrate.simplyillustrate.entity.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionsFeedAdapter extends RecyclerView.Adapter<QuestionsFeedAdapter.QuestionsFeedViewHolder> {

    final private QuestionsFeedAdapter.ListItemClickListener mOnClickListener;
    private ArrayList<Question> questionsList;

    public QuestionsFeedAdapter(ArrayList<Question> questionsList, QuestionsFeedAdapter.ListItemClickListener listener) {
        mOnClickListener = listener;
        this.questionsList = questionsList;
    }

    @NonNull
    @Override
    public QuestionsFeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_item, parent, false);
        return new QuestionsFeedAdapter.QuestionsFeedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionsFeedViewHolder holder, int position) {
        holder.title.setText(questionsList.get(position).getTitle());
        holder.difficulty.setText(questionsList.get(position).getDifficulty());
        List<String> tagNames = questionsList.get(position).getTagNames();
        if(tagNames.size() > 2) {
            String displayName = tagNames.get(0)+","+tagNames.get(1)+" and "+ (tagNames.size() - 2) +" more ...";
            holder.category.setText(displayName);
        } else {
            holder.category.setText(tagNames.get(0));
        }
    }

    @Override
    public int getItemCount() {
        return questionsList.size();
    }

    public interface ListItemClickListener {
        void onListItemClick(View view, int clickedItemIndex);
    }

    public class QuestionsFeedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title;
        TextView difficulty;
        TextView category;

        public QuestionsFeedViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_title);
            difficulty = itemView.findViewById(R.id.tv_diff);
            category = itemView.findViewById(R.id.tv_tag);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(v, clickedPosition);
        }
    }
}
