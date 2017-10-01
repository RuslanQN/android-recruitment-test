package droid.ruslanq.investaz_tst.View.QuotView;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import droid.ruslanq.investaz_tst.R;
import droid.ruslanq.investaz_tst.View.QuotData.SocketApp;

/**
 * Created by Ruslanq on 01.10.2017.
 */

public class QuotationFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.quotos_list, container, false);
        //RecyclerView to display value
    }
}