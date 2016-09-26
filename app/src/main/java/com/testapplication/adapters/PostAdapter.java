package com.testapplication.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.testapplication.R;
import com.testapplication.model_adapter.Post;
import com.testapplication.model_db.OperationUser;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.SimpleViewHolder> {
    private final Context context;
    private List<Post> items;
    public ItemClickListener listener;

    public void setItemClickListener(ItemClickListener clickListener) {
        this.listener = clickListener;
    }

    public PostAdapter(Context context, List<Post> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.post_list_item, viewGroup, false);
        return new SimpleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder viewHolder, int i) {
        Post currentItem = items.get(i);
        //viewHolder.iv_post.setImageResource(currentItem.image.toString());
        viewHolder.tvp_user_name.setText(currentItem.getUser());
        viewHolder.tv_post_title.setText(currentItem.getPost_title());
        viewHolder.tvp_description.setText(currentItem.getDescription());
        OperationUser.loadImage(context,viewHolder.iv_post,currentItem.getImg());
    }

    public class SimpleViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        // Campos respectivos de un item
        public ImageView iv_post;
        public TextView tvp_user_name;
        public TextView tv_post_title;
        public TextView tvp_description;

        public SimpleViewHolder(View v) {
            super(v);

            iv_post = (ImageView) v.findViewById(R.id.iv_post);
            tvp_user_name = (TextView) v.findViewById(R.id.tvp_user_name);
            tv_post_title = (TextView) v.findViewById(R.id.tv_post_title);
            tvp_description = (TextView) v.findViewById(R.id.tvp_description);

          /*  detalle =(TextView) v.findViewById(R.id.detalle);
            detalle.setOnClickListener(this);
            perfil.setOnClickListener(this);*/

        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(v, getAdapterPosition(), v.getId());

        }
    }


    public interface ItemClickListener {
        void onItemClick(View view, int position, int selected);
    }
}



