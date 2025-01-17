package ca.cmpt276.assignment3;

/*
This class has the game settings and the user can choose different options for dimensions and the number
of fighters they want in the game
 */

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import ca.cmpt276.assignment3.model.Game;

public class GameSetting extends AppCompatActivity {

    private static final String FIGHTER_PREF = "Fighter Prefs";
    private static final String totalFighter = "Total number of fighters";
    private static final String COL_PREF = "Cols pref";
    private static final String ROW_PREF = "Row pref";
    private static final String DIMENSIONS_COL = "Dimensions col";
    private static final String DIMENSIONS_ROW = "Dimensions row";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_setting);



        Button btn = findViewById(R.id.ReturnToMenu);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        createConfirmButton();
        createRadioButtonsForDimensions();
        createRadioButtonsMinesCount();
    }

    private void createConfirmButton() {
        Button btn = findViewById(R.id.confirmButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RadioGroup group = findViewById(R.id.DimensionOptions);
                int idOfSelected = group.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(idOfSelected);
                String message = radioButton.getText().toString();
                //testing selection transfer using id
                System.out.println(idOfSelected);

                RadioGroup group2 = findViewById(R.id.fighterOptions);
                int idOfSelected2 = group2.getCheckedRadioButtonId();
                RadioButton radioButton2 = findViewById(idOfSelected2);
                String message2 = radioButton2.getText().toString();
                //testing selection transfer using id
                System.out.println(idOfSelected2);

                Toast.makeText(GameSetting.this, "Selected the size: "+ message
                                        + " Selected Number of Fighters: "+ message2,
                        Toast.LENGTH_LONG)
                        .show();


                //this set of if statements are for setting the dimensions
                if(idOfSelected == 1) {
                    Game.getInstance().setRow(4);
                    Game.getInstance().setCol(6);
                }
                else if(idOfSelected == 2){
                    Game.getInstance().setRow(5);
                    Game.getInstance().setCol(10);
                }
                else{
                    Game.getInstance().setRow(6);
                    Game.getInstance().setCol(15);
                }

                //this set of if statements are for setting the number of mines
                if(idOfSelected2 == 4) {
                    Game.getInstance().setNumFighters(6);
                }
                else if(idOfSelected2 == 5){
                    Game.getInstance().setNumFighters(10);
                }
                else if(idOfSelected2 == 6){
                    Game.getInstance().setNumFighters(15);
                }
                else{
                    Game.getInstance().setNumFighters(20);
                }
            }
        });
    }

    private void createRadioButtonsForDimensions(){
        RadioGroup group = findViewById(R.id.DimensionOptions);
        int[] sizeOptionsRows = getResources().getIntArray(R.array.Board_Rows);
        int[] sizeOptionsColumns = getResources().getIntArray(R.array.Board_Columns);
        //3 is the maximum number of options
        for(int i = 0; i < 3; i++){
            final int row = sizeOptionsRows[i];
            final int col = sizeOptionsColumns[i];
            //- 4 rows by 6 columns
            //- 5 rows by 10 columns
            //- 6 rows by 15 columns
            RadioButton button = new RadioButton(this);
            button.setText(sizeOptionsRows[i]+" by "+sizeOptionsColumns[i]);

            button.setOnClickListener(view -> {
                saveDimensionsRow(row);
                saveDimensionsCol(col);
            });

            group.addView(button);
        }
    }
    private void createRadioButtonsMinesCount(){
        RadioGroup group2 = findViewById(R.id.fighterOptions);
        int[] fighterOptions = getResources().getIntArray(R.array.Mines_Count);
        for(int i = 0; i < fighterOptions.length; i++){
            final int numFightersDecided = fighterOptions[i];
            //- 6
            //- 10
            //- 15
            //- 20
            RadioButton button = new RadioButton(this);
            button.setText(""+ fighterOptions[i] + " Fighters");

            button.setOnClickListener(view ->
                    saveNumFighters(numFightersDecided));

            group2.addView(button);
        }
    }

    private void saveNumFighters(int fightersNum){
        SharedPreferences prefs = this.getSharedPreferences(FIGHTER_PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(totalFighter, fightersNum);
        editor.apply();

    }
    private void saveDimensionsRow(int row){
        SharedPreferences prefs = this.getSharedPreferences(ROW_PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(DIMENSIONS_ROW, row);
        editor.apply();

    }
    private void saveDimensionsCol(int row){
        SharedPreferences prefs = this.getSharedPreferences(COL_PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(DIMENSIONS_COL, row);
        editor.apply();

    }

    static public int getNumFighters(Context context){
        SharedPreferences  prefs = context.getSharedPreferences(FIGHTER_PREF, MODE_PRIVATE);
        int defaultFighterNum = 6;
        return prefs.getInt(totalFighter, defaultFighterNum);
    }


    static public int getDimensionsRow(Context context){
        SharedPreferences  prefs = context.getSharedPreferences(ROW_PREF, MODE_PRIVATE);
        int defaultDimensionsRow = 6;
        return prefs.getInt(DIMENSIONS_ROW, defaultDimensionsRow);
    }

    static public int getDimensionsCol(Context context){
        SharedPreferences  prefs = context.getSharedPreferences(COL_PREF, MODE_PRIVATE);
        int defaultDimensionsCol = 6;
        return prefs.getInt(DIMENSIONS_COL, defaultDimensionsCol);
    }
}