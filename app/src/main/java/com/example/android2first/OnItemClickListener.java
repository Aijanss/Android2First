package com.example.android2first;

public interface OnItemClickListener {
    void onClick(int position);
    void longClick(int position, Note note);
}
