package com.mercado.mercadocom.Adapters;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mercado.mercadocom.Model.ChatModel;
import com.mercado.mercadocom.Model.CheckoutModel;
import com.mercado.mercadocom.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ImageHolder>
{
    Context context;
    List<ChatModel> mChatModel;

    public ChatAdapter(Context context, List<ChatModel> mChatModel) {
        this.context = context;
        this.mChatModel = mChatModel;
    }

    @NonNull
    @Override
    public ImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.chat, parent, false);
        return new ImageHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ImageHolder holder, int position)
    {
        ChatModel chatModel = mChatModel.get(position);



        String temp = chatModel.getTemp1();
        if(temp.equals("user"))
        {
            holder.chatTextview.setGravity(Gravity.RIGHT);
            holder.chatTextview.setText(chatModel.getMsg());
            holder.chatTime.setGravity(Gravity.RIGHT);
            holder.chatTime.setText(chatModel.getDate());
        }
        else if(temp.equals("admin"))
        {
            holder.chatTextview.setGravity(Gravity.LEFT);
            holder.chatTextview.setText(chatModel.getMsg());
            holder.chatTime.setGravity(Gravity.LEFT);
            holder.chatTime.setText(chatModel.getDate());
        }
    }

    @Override
    public int getItemCount()
    {
        return mChatModel.size();
    }

    public class ImageHolder extends RecyclerView.ViewHolder
    {
        TextView chatTextview, chatTime;

        public ImageHolder(@NonNull View itemView) {
            super(itemView);

            chatTextview = itemView.findViewById(R.id.chatTextview);
            chatTime = itemView.findViewById(R.id.chatTime);

        }

    }
}
