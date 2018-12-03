package com.chemutai.chat.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.chemutai.chat.Model.ChatMessage;
import com.chemutai.chat.R;

import java.util.List;

public class ChatMessageAdapter extends ArrayAdapter<ChatMessage> {

    private  static final int my_message = 0;
    private static final int bot_message = 1;

    public ChatMessageAdapter(@NonNull Context context, List<ChatMessage> data) {
        super(context, R.layout.user_query_layout, data);
    }

    @Override
    public int getItemViewType(int position) {
        ChatMessage item = getItem(position);

        if (item.isMine() && !item.isImage())
        {
            return my_message;
        }
        else
        {
            return bot_message;
        }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        int viewType = getItemViewType(position);

        if (viewType == my_message)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.user_query_layout, parent, false);
        }
        return super.getView(position, convertView, parent);
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }
}
