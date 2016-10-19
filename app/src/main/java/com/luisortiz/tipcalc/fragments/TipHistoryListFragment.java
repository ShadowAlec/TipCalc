package com.luisortiz.tipcalc.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.luisortiz.tipcalc.R;

import java.util.ArrayList;
import butterknife.Bind;
import butterknife.ButterKnife;

import com.luisortiz.tipcalc.adapters.TipAdapter;
import com.luisortiz.tipcalc.models.TipRecord;

/**
 * A simple {@link Fragment} subclass.
 */
public class TipHistoryListFragment extends Fragment implements TipHistoryListFragmentListener {

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    TipAdapter adapter;

    public TipHistoryListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_tip_history_list,container,false);
        ButterKnife.bind(this,view);
        initAdapter();
        initRecyclerView();
        return view;

    }

    private void initAdapter()
    {
        if(adapter==null) {
            adapter = new TipAdapter(getActivity().getApplicationContext(), new ArrayList<TipRecord>());
        }

    }

    private void initRecyclerView()
    {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void addToList(TipRecord record)
    {
        adapter.add(record);
    }

    @Override
    public void clearList()
    {

    }

}
