package com.example.bg_stats;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerView_Config {
    private Context mContext;
    private GamesAdapter mGamesAdapter;

    public void setConfig(RecyclerView recyclerView, Context context, List<Game> games, List<String> keys) {
        mContext = context;
        mGamesAdapter = new GamesAdapter(games, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mGamesAdapter);
    }

    class GameItemView extends RecyclerView.ViewHolder {
        private TextView mTitle;

        private String key;

        public GameItemView(ViewGroup parent) {
            super(LayoutInflater.from(mContext).inflate(R.layout.game_list_item, parent, false));

            mTitle = itemView.findViewById(R.id.title_textView);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, UpdateGameView.class);
                    intent.putExtra("rKey", key);
                    intent.putExtra("rTitle", mTitle.getText().toString());
                    mContext.startActivity(intent);
                }
            });
        }

        public void bind(Game game, String key) {
            mTitle.setText(game.getName());
            this.key = key;
        }
    }

    class GamesAdapter extends RecyclerView.Adapter<GameItemView> {
        private List<Game> mGameList;
        private List<String> mKeys;

        public GamesAdapter(List<Game> mGameList, List<String> mKeys) {
            this.mGameList = mGameList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public GameItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new GameItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull GameItemView holder, int position) {
            holder.bind(mGameList.get(position), mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mGameList.size();
        }
    }
}
