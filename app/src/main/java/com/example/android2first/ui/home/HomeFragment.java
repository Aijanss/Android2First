package com.example.android2first.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android2first.App;
import com.example.android2first.Note;
import com.example.android2first.OnItemClickListener;
import com.example.android2first.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomeFragment extends Fragment {
    private ItemAdapter adapter;
    private RecyclerView recyclerView;
    private boolean update = false;
    private int pos;
    FloatingActionButton f_button;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        adapter = new ItemAdapter();
        adapter.setList(App.getAppDataBase().noteDao().getAll());


    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        new ViewModelProvider(this).get(HomeViewModel.class);
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_edit, menu);


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.editText) {
            openEdit();
        }
        if (item.getItemId() == R.id.sort) {
            sortData();

        }

        return super.onOptionsItemSelected(item);
    }

    private void sortData() {
        if (App.getPrefs().isSorted()) {
           adapter.addNewList(App.getAppDataBase().noteDao().getAll());
            App.getPrefs().sortName(false);

        } else {
          adapter.addNewList(App.getAppDataBase().noteDao().getSortedList());
            App.getPrefs().sortName(true);

        }

    }

    private void openEdit() {
        NavController navController = Navigation.findNavController(requireActivity(),
                R.id.nav_host_fragment);
        navController.navigate(R.id.editFragment);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        f_button = view.findViewById(R.id.f_button);
        f_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update = false;
                openForm(null);

            }
        });
        setFragmentListener();

        initList();
    }


    private void openForm(Note note) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("note", note);
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        navController.navigate(R.id.formFragment, bundle);
    }


    private void setFragmentListener() {
        getParentFragmentManager().setFragmentResultListener(
                "rk_form",
                getViewLifecycleOwner(),
                new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        Note note = (Note) result.getSerializable("note_updated");
                        if (update) {
                            adapter.updateItem(pos, note);
                        } else

                            adapter.addItem(note);
                    }
                });
    }

    private void initList() {
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(int position) {
                pos = position;
                update = true;
                Note note = adapter.getItem(position);
                openForm(note);
            }

            @Override
            public void longClick(int position, Note note) {
                final int which_item = position;

                new AlertDialog.Builder(getContext())
                        .setIcon(android.R.drawable.ic_delete)
                        .setTitle("Are you sure?")
                        .setMessage("Do you want to delete this item?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                adapter.delete(which_item);
                                App.getAppDataBase().noteDao().delete((note));
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();

            }
        });
    }


}

