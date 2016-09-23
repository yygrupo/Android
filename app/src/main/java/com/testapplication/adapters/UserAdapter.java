package com.testapplication.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.testapplication.R;
import com.testapplication.model_adapter.User;


import java.util.List;


public class UserAdapter extends RecyclerView.Adapter<UserAdapter.SimpleViewHolder>  {
    private final Context context;
    private List<User> items;
    public ItemClickListener listener;
    public void setItemClickListener(ItemClickListener clickListener){
        this.listener=clickListener;
    }

    public UserAdapter(Context context, List<User> items) {
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
                .inflate(R.layout.user_list_item, viewGroup, false);
        return new SimpleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder viewHolder, int i) {
        User currentItem = items.get(i);
        //viewHolder.iv_user.setImageResource(currentItem.getImg());
        viewHolder.tv_user_name.setText(currentItem.getUser());
        viewHolder.tv_email.setText(currentItem.getEmail());
        viewHolder.tv_description.setText(currentItem.getDescription());
        /*Glide.with(viewHolder.avatar.getContext())
                .load(currentItem.getIdDrawable())
                .centerCrop()
                .into(viewHolder.avatar);*/
    }

    public  class SimpleViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        public ImageView iv_user;
        public TextView tv_user_name;
        public TextView tv_email;
        public TextView tv_description;


        public SimpleViewHolder(View v) {
            super(v);

            iv_user = (ImageView) v.findViewById(R.id.iv_user);
            tv_user_name = (TextView) v.findViewById(R.id.tv_user_name);
            tv_email=(TextView) v.findViewById(R.id.tv_email);
            tv_description =(TextView) v.findViewById(R.id.tv_description);
          /*  pedido.setOnClickListener(this);
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



