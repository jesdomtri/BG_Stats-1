package com.example.bg_stats;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class GameAdapter extends ArrayAdapter<GamePreview> {

    private ArrayList<GamePreview> mGamePreview;
    private Context mContext;
    private int mListItemResourceID;

    public GameAdapter(Context context, int listItemResourceID, ArrayList<GamePreview> game) {
        super(context,listItemResourceID,game);
        mGamePreview = game;
        mContext = context;
        mListItemResourceID = listItemResourceID;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        GamePreview itemGame = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.game_item, parent, false);
        }
        // Lookup view for data population
        TextView gameIndex = convertView.findViewById(R.id.gameMain);
        TextView gamePosition = convertView.findViewById(R.id.gamePlayer);
        TextView gameWinner = convertView.findViewById(R.id.gameWinner);
        // Populate the data into the template view using the data object
        String username = "";
        String indexString = "Game " + (position + 1);
        gameIndex.setText(indexString);
        gameWinner.setText(itemGame.getWinner());
        gamePosition.setText(itemGame.getPosition(username));
        // Return the completed view to render on screen
        return convertView;
    }
}