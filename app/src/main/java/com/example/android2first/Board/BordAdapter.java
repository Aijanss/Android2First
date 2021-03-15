package com.example.android2first.Board;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.android2first.OnItemClickListener;
import com.example.android2first.R;

public class BordAdapter extends RecyclerView.Adapter<BordAdapter.ViewHolder> {
    private OnItemClickListener onItemClickListener;
    public String[] titles= {"Alert","My gear","Bike "};
    public   Integer[] animation={R.raw.alert,R.raw.bike,R.raw.del};
    public BordAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.page_board,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(position);

    }

    @Override
    public int getItemCount()
    {
        return 3;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        TextView textTitle;
       LottieAnimationView lottie;
        Button buttonStart;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
           lottie=itemView.findViewById(R.id.animationView);
            textTitle=itemView.findViewById(R.id.textTitle);
            buttonStart=itemView.findViewById(R.id.button_start);
            buttonStart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    onItemClickListener.onClick(getAdapterPosition());
                }
            });
        }

        public void bind(int position) { if(position==2) buttonStart.setVisibility(View.VISIBLE);
           lottie.setAnimation(animation[position]);
            textTitle.setText(titles[position]);

        }
    }
}
