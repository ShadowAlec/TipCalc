package com.luisortiz.tipcalc.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.luisortiz.tipcalc.R;

import butterknife.Bind;
import butterknife.ButterKnife;

import com.luisortiz.tipcalc.activities.AdvancedTipActivity;
import com.luisortiz.tipcalc.adapters.OnItemClickListener;
import com.luisortiz.tipcalc.adapters.TipAdapter;
import com.luisortiz.tipcalc.entity.TipRecord;
import com.luisortiz.tipcalc.utils.TipUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class TipHistoryListFragment extends Fragment implements TipHistoryListFragmentListener,OnItemClickListener {




    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    TipAdapter adapter;
    public final static String BILL_MESSAGE = "me.tipcalc.billmessage";
    public final static String TIP_MESSAGE = "me.tipcalc.tipmessage";
    public final static String DATE_MESSAGE = "me.tipcalc.datemessage";

    public TipHistoryListFragment() {
        // Required empty public constructo r
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
            adapter = new TipAdapter(getActivity().getApplicationContext(), this);
        }

    }

    private void initRecyclerView()
    {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void initList() {
        adapter.init();
    }

    @Override
    public void addToList(TipRecord record) {
       adapter.add(record);

    }

    @Override
    public void clearList()
    {
            adapter.clear();
    }

    @Override
    public void OnItemClick(TipRecord tipRecord) {


        // HW Implementar la lógica para implementar una actividad enviándole la información de la propina.
        //Log.v("Mensaje!!", tipRecord.getDateFormated());
        Intent intent = new Intent(getContext(),AdvancedTipActivity.class);
        intent.putExtra(BILL_MESSAGE,String.valueOf(tipRecord.getBill()));
        intent.putExtra(TIP_MESSAGE,String.valueOf(TipUtils.getTip(tipRecord)));
        intent.putExtra(DATE_MESSAGE,TipUtils.getDateFormated(tipRecord));
        startActivity(intent);


    }
}
