package droid.ruslanq.investaz_tst.View.QuotView;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import droid.ruslanq.investaz_tst.R;
import droid.ruslanq.investaz_tst.View.QuotData.QuotDataTemplate;

/**
 * Created by Ruslanq on 03.10.2017.
 */

public class QuotAdapter extends RecyclerView.Adapter<QuotAdapter.QViewHolder> {
    List<QuotDataTemplate> data;
    @NonNull
    private final LayoutInflater layoutInflater;

    public QuotAdapter(List<QuotDataTemplate> data, LayoutInflater layoutInflater) {
        this.layoutInflater = layoutInflater;
        this.data = data;

    }

    @Override
    public QViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new QViewHolder(layoutInflater.inflate(R.layout.quot_cards, parent, false), parent);
    }

    @Override
    public void onBindViewHolder(QViewHolder holder, int position) {

        holder.name.setText(data.get(position).name);
        holder.val_1.setText(data.get(position).val_1);
        holder.val_2.setText(data.get(position).val_2);
        holder.val_3.setText(data.get(position).val_3);
        holder.val_4.setText(data.get(position).val_4);
        holder.datetime.setText(data.get(position).datetime.replace("T", " ").substring(0, data.get(position).datetime.lastIndexOf(".")));
        if (data.get(position).direction.equals("down")) {
            holder.direction.setImageResource(R.drawable.ic_arrow_drop_down_black_24dp);
        } else {
            holder.direction.setImageResource(R.drawable.ic_arrow_drop_up_black_24dp);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class QViewHolder extends RecyclerView.ViewHolder {

        TextView name, datetime, val_1, val_2, val_3, val_4;
        ImageView direction;

        public QViewHolder(View itemView, ViewGroup viewGroup) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            datetime = (TextView) itemView.findViewById(R.id.datetime);
            val_1 = (TextView) itemView.findViewById(R.id.val_1);
            val_2 = (TextView) itemView.findViewById(R.id.val_2);
            val_3 = (TextView) itemView.findViewById(R.id.val_3);
            val_4 = (TextView) itemView.findViewById(R.id.val_4);
            direction = (ImageView) itemView.findViewById(R.id.direction);
        }
    }
}
