package ca.cmpt276.assignment3;

/*
The class GameBoard is where the user actually plays the game and includes the interface of the table.
 */

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;


import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;



import ca.cmpt276.assignment3.model.Game;

public class GameBoard extends AppCompatActivity {

    private static int num_rows;
    private static int num_cols;
    private int numOfFighters;
    private Game currGame;
    Button[][] buttons;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_board);

        currGame = Game.getInstance();

        currGame.setNumFighters(GameSetting.getNumFighters(this));
        currGame.setRow(GameSetting.getDimensionsRow(this));
        currGame.setCol(GameSetting.getDimensionsCol(this));

        num_rows = currGame.getRow();
        num_cols = currGame.getCol();
        numOfFighters = currGame.getNumFighters();

        buttons = new Button[num_rows][num_cols];

        populateButtons();
        updateRadarCount();


    }



    private void populateButtons() {

        currGame.resetFightersFound();
        currGame.resetScanCounter();

        TableLayout table = (TableLayout) findViewById(R.id.tableForButtons);
        currGame.initializeFighterLocations();//added by kia, to be checked by kia later

        for (int row = 0; row < num_rows; row++) {
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f));
            table.addView(tableRow);

            for (int col = 0; col < num_cols; col++){
                final int FINAL_COL = col;
                final int FINAL_ROW = row;

                Button button = new Button(this);
                button.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f));



                // Make text not clip on small buttons
                button.setPadding(0, 0, 0, 0);

                button.setOnClickListener(view -> {
                    if(!currGame.hasBeenRevealed(FINAL_ROW, FINAL_COL)){
                        gridButtonClicked(FINAL_COL, FINAL_ROW);
                    }
                });


                tableRow.addView(button);

                buttons[row][col] = button;
            }
        }
    }



    private void gridButtonClicked(int col, int row) {

        Toast.makeText(this, "Button clicked: " + col + "," + row,
                Toast.LENGTH_SHORT).show();
        Button button = buttons[row][col];

        // Lock Button Sizes:
        lockButtonSizes();

        if(currGame.checkTile(row,col)) {
            // http://commons.wikimedia.org/wiki/Crystal_Clear
            int newWidth = button.getWidth();
            int newHeight = button.getHeight();
            Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.fighter_1);
            Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
            Resources resource = getResources();
            button.setBackground(new BitmapDrawable(resource, scaledBitmap));

            vibingTime();
            updateAll();
            checkForWinner();

        } else {
            button.setTextColor(Color.BLUE);
            currGame.incrementScanCounter();
            button.setText("" + currGame.radar(row,col));


        }
        updateRadarCount();

    }


    private void vibingTime() {

        ((Vibrator) getSystemService(VIBRATOR_SERVICE)).vibrate(VibrationEffect.createOneShot(150, VibrationEffect.DEFAULT_AMPLITUDE));

    }

    private void checkForWinner(){
        if(currGame.getNumFighters() == currGame.getFightersFound()){
            FragmentManager manager = getSupportFragmentManager();
            WinnerFragment dialog = new WinnerFragment();
            dialog.show(manager, "WinnerFragment");
        }
    }



    public void updateRadarCount(){
        TextView radarCount = findViewById(R.id.foundedFighters);
        TextView usedScans = findViewById(R.id.numScans);
        radarCount.setText("Found "+ currGame.getFightersFound()+" Of "+ currGame.getNumFighters());
        usedScans.setText("# Scans Used: " + currGame.getScanCounter());

    }

    private void lockButtonSizes() {
        for (int row = 0; row < num_rows; row++) {
            for (int col = 0; col < num_cols; col++) {
                Button button = buttons[row][col];

                int width = button.getWidth();
                button.setMinWidth(width);
                button.setMaxWidth(width);

                int height = button.getHeight();
                button.setMinHeight(height);
                button.setMaxHeight(height);
            }
        }
    }

    private void updateAll(){
        //going through every button and updating it
        for( int i=0; i< num_rows; i++){
            for(int j = 0; j < num_cols; j++){
                if(currGame.hasBeenRevealed(i,j)){
                    Button btn = buttons[i][j];
                    btn.setText("" + currGame.radar(i, j ));
                }
            }
        }
    }


}