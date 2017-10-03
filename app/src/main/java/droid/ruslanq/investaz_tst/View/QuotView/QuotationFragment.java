package droid.ruslanq.investaz_tst.View.QuotView;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;

import droid.ruslanq.investaz_tst.R;
import droid.ruslanq.investaz_tst.View.QuotData.QuotDT_proc;
import droid.ruslanq.investaz_tst.View.QuotData.QuotDataTemplate;

/**
 * Created by Ruslanq on 01.10.2017.
 */

public class QuotationFragment extends Fragment {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    int state;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.quotos_list, container, false);


        return view;


        //RecyclerView to display value
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycl_quot);

        setAdapter();
        setRecyclerView();


    }

    private void setAdapter() {
        adapter = new QuotAdapter(new QuotDT_proc(getActivity()).getAll(),
                LayoutInflater.from(recyclerView.getContext()));
    }

    private void setRecyclerView() {
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    public void refreshValues() {
        state = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstCompletelyVisibleItemPosition();
        adapter = new QuotAdapter(new QuotDT_proc(getActivity()).getAll(),
                LayoutInflater.from(recyclerView.getContext()));
        recyclerView.setAdapter(adapter);
        ((LinearLayoutManager) recyclerView.getLayoutManager()).scrollToPosition(state);
    }

}