package com.geogreenapps.apps.indukaan.adapter.order;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geogreenapps.apps.indukaan.R;
import com.geogreenapps.apps.indukaan.classes.TimeLine;
import com.github.vipulasri.timelineview.TimelineView;
import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.iconics.IconicsDrawable;

import java.util.ArrayList;
import java.util.List;

public class TimeLineAdapter extends RecyclerView.Adapter<TimeLineAdapter.mViewHolder> {

    // public TimelineView mTimelineView;
    private List<TimeLine> items = new ArrayList<>();
    private final Context context;


    public TimeLineAdapter(Context context, List<TimeLine> items) {
        this.items = items;
        this.context = context;
    }


    @Override
    public int getItemCount() {
        if (items == null) return 0;
        return items.size();
    }

    @NonNull
    @Override
    public mViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_timeline, null);
        return new mViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull mViewHolder holder, int position) {

        if (position == getItemCount() - 1) {
            if (items.get(position).getStatus().equalsIgnoreCase(context.getResources().getString(R.string.ondelivery))) {
                Drawable motorbike = new IconicsDrawable(context)
                        .icon(CommunityMaterial.Icon.cmd_motorbike)
                        .sizeDp(22);
                holder.timeline.setMarker(motorbike, context.getResources().getColor(R.color.colorPrimary));
            } else {
                Drawable checked = new IconicsDrawable(context)
                        .icon(CommunityMaterial.Icon.cmd_check)
                        .sizeDp(22);
                holder.timeline.setMarker(checked, context.getResources().getColor(R.color.colorPrimary));
            }
        } else {
            Drawable checked = new IconicsDrawable(context)
                    .icon(CommunityMaterial.Icon.cmd_check)
                    .sizeDp(22);
            holder.timeline.setMarker(checked, context.getResources().getColor(R.color.green));
        }


        holder.text_timeline_date.setText(items.get(position).getDate());

        if (!items.get(position).getStatus().trim().equals("") && !items.get(position).getStatus().equals("null")) {
            String status = items.get(position).getStatus().toUpperCase();
            if (items.get(position).getMessage() != null && !items.get(position).getMessage().trim().equals("")
                    && !items.get(position).getMessage().equals("null")) {
                holder.text_timeline_title.setText(status + " : " + items.get(position).getMessage());
            } else {
                holder.text_timeline_title.setText(status);
            }
        }

    }


    public int getItemViewType(int position) {
        return TimelineView.getTimeLineViewType(position, getItemCount());
    }


    public class mViewHolder extends RecyclerView.ViewHolder {

        public TextView text_timeline_date;
        public TextView text_timeline_title;
        public TimelineView timeline;

        public mViewHolder(View itemView, int viewType) {
            super(itemView);
            text_timeline_date = itemView.findViewById(R.id.text_timeline_date);
            text_timeline_title = itemView.findViewById(R.id.text_timeline_title);
            timeline = itemView.findViewById(R.id.timeline);
            timeline.initLine(viewType);
        }

    }

}
