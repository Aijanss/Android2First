package com.example.android2first.ui.home;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android2first.App;
import com.example.android2first.Note;
import com.example.android2first.OnItemClickListener;
import com.example.android2first.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private final List<Note> list;
    private OnItemClickListener onItemClickListener;

   private static String date = java.text.DateFormat.getDateTimeInstance().format(new Date());

    public ItemAdapter() {
        this.list = new ArrayList<>();
       // list.addAll(App.getAppDataBase().noteDao().getAll());

   }

   //public ItemAdapter(LifecycleOwner owner ) {
     //  list=new ArrayList<>();
     //  App.getAppDataBase().noteDao().getAllLive().observe(owner, new Observer<List<Note>>() {
       //    @Override
         //  public void onChanged(List<Note> notes) {
          //     list.clear();
          //     list.addAll(notes);
         //  }
      // });

  //}


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                 .inflate(R.layout.item_task, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(position%2==2){
            holder.itemView.setBackgroundColor(Color.BLACK);
        }else{
            holder.itemView.setBackgroundColor(Color.WHITE);
        }
        holder.bind(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    @Override
    public void registerAdapterDataObserver(@NonNull RecyclerView.AdapterDataObserver observer) {
        super.registerAdapterDataObserver(observer);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public Note getItem(int position) {
        return list.get(position);
    }


    public void addItem(Note note) {
        list.add(0,note);
       notifyItemInserted(list.indexOf(note));

    }
    public  void  delete(int position){
        list.remove(position);
        notifyItemRemoved(position);
    }
       public void updateItem(int position,Note note){
        list.set(position,note);
       notifyItemChanged(position);
       }
    public void setList(List<Note> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }


    public void addNewList(List<Note> list) {
       this.list.clear();
       this.list.addAll(list);
       notifyDataSetChanged();

    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        TextView date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date=itemView.findViewById(R.id.date);
            textView = itemView.findViewById(R.id.text);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onClick(getAdapterPosition());



                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemClickListener.longClick(getAdapterPosition(), list.get(getAdapterPosition()));
                    return true;
                }
            });
        }


        public void bind(Note note) {
            date.setText(note.getData());
            textView.setText(note.getTitle());



        }


    }
}
