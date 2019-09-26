package com.simplyillustrate.simplyillustrate.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.simplyillustrate.simplyillustrate.R;
import com.simplyillustrate.simplyillustrate.entity.Answer;

import java.util.ArrayList;

public class AnswerAdapter extends RecyclerView.Adapter<AnswerAdapter.AnswerViewHolder> {

    private ArrayList<Answer> answerList;

    public AnswerAdapter(ArrayList<Answer> answerList) {
        this.answerList = answerList;
    }

    @NonNull
    @Override
    public AnswerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.answer_item, parent, false);
        return new AnswerAdapter.AnswerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnswerViewHolder holder, int position) {
        holder.text.setText(answerList.get(position).getText());
        holder.created_by.setText(answerList.get(position).getCreatedBy());
    }

    @Override
    public int getItemCount() {
        return answerList.size();
    }

    public class AnswerViewHolder extends RecyclerView.ViewHolder {

        TextView text;
        TextView created_by;

        public AnswerViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.text);
            created_by = itemView.findViewById(R.id.created_by);
        }
    }
}
