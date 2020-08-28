package com.example.bg_stats;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerView_Config {
    private Context mContext;
    private GamesAdapter mGamesAdapter;
    private GamesAdapter2 mGamesAdapter2;
    private MatchesAdapter mMatchesAdapter;

    public void setConfig(RecyclerView recyclerView, Context context, List<Game> games, List<String> keys) {
        mContext = context;
        mGamesAdapter = new GamesAdapter(games, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mGamesAdapter);
    }

    public void setConfig2(RecyclerView recyclerView, Context context, List<Game> games, List<String> keys) {
        mContext = context;
        mGamesAdapter2 = new GamesAdapter2(games, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mGamesAdapter2);
    }

    public void setConfigMatches(RecyclerView recyclerView, Context context, List<Match> matches) {
        mContext = context;
        mMatchesAdapter = new MatchesAdapter(matches);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mMatchesAdapter);
    }

    class GameItemView extends RecyclerView.ViewHolder {
        private TextView mTitle;

        private String key;

        public GameItemView(ViewGroup parent) {
            super(LayoutInflater.from(mContext).inflate(R.layout.game_list_item, parent, false));

            mTitle = itemView.findViewById(R.id.title_textView);

            itemView.setOnClickListener(new View.OnClickListener() {
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

    class MatchItemView extends RecyclerView.ViewHolder {
        private TextView mPosition;
        private TextView mScore;
        private ImageView mWinner;


        public MatchItemView(ViewGroup parent) {
            super(LayoutInflater.from(mContext).inflate(R.layout.game_item, parent, false));

            mPosition = itemView.findViewById(R.id.positionValue_gi);
            mScore = itemView.findViewById(R.id.scoreValue_gi);
            mWinner = itemView.findViewById(R.id.winner_gi);

        }

        public void bind(Match match) {
            mPosition.setText(match.getPosition().toString());
            mScore.setText(match.getScore());
            if (match.getWinner()) {
                mWinner.setVisibility(View.VISIBLE);
            } else {
                mWinner.setVisibility(View.INVISIBLE);
            }
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

    class MatchesAdapter extends RecyclerView.Adapter<MatchItemView> {
        private List<Match> mMatchesList;

        public MatchesAdapter(List<Match> mMatchesList) {
            this.mMatchesList = mMatchesList;
        }

        @NonNull
        @Override
        public MatchItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MatchItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull MatchItemView holder, int position) {
            holder.bind(mMatchesList.get(position));
        }

        @Override
        public int getItemCount() {
            return mMatchesList.size();
        }
    }

    class GameItemView2 extends RecyclerView.ViewHolder {
        private TextView mTitle;

        private String key;

        public GameItemView2(ViewGroup parent) {
            super(LayoutInflater.from(mContext).inflate(R.layout.game_list_item, parent, false));

            mTitle = itemView.findViewById(R.id.title_textView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, AddGameView.class);
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

    class GamesAdapter2 extends RecyclerView.Adapter<GameItemView2> {
        private List<Game> mGameList;
        private List<String> mKeys;

        public GamesAdapter2(List<Game> mGameList, List<String> mKeys) {
            this.mGameList = mGameList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public GameItemView2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new GameItemView2(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull GameItemView2 holder, int position) {
            holder.bind(mGameList.get(position), mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mGameList.size();
        }
    }
}
