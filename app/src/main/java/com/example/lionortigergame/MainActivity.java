package com.example.lionortigergame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    enum Player {

        ONE, TWO , NoOne
    }
    Player currentPlayer = Player.ONE;

    Player[] playerChoices = new Player[9];

    int[][] winnerRowsColumns = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    private boolean gameOver = false;
    private Button btnReset;
    private GridLayout gridLayout;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );

        countLoop ();
        btnReset = findViewById(R.id.btnReset);
        gridLayout = findViewById(R.id.gridlayout);

        btnReset.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
               resetTheGame ();
            }
        } );

    }

    public void imageViewIsTapped(View imageView) {

        ImageView tappedImageView =(ImageView ) imageView;
        int titag = Integer.parseInt ( tappedImageView.getTag ().toString () );

        if (playerChoices[titag] == Player.NoOne && gameOver ==false ) {

            tappedImageView.setTranslationX ( -2000 );

            playerChoices[titag] = currentPlayer;


            if ( currentPlayer == Player.ONE ) {
                tappedImageView.setImageResource ( R.drawable.lion );

                currentPlayer = Player.TWO;
            } else if ( currentPlayer == Player.TWO ) {
                tappedImageView.setImageResource(R.drawable.tiger);
                currentPlayer = Player.ONE;

            }

            tappedImageView.animate().translationXBy(2000).
                    alpha(1).rotation(3600).setDuration(2000);

            Toast.makeText(this, tappedImageView.getTag().toString(),
                    Toast.LENGTH_SHORT).show();


            for (int[] winnerColumns : winnerRowsColumns) {

                if ( playerChoices[winnerColumns[0]] == playerChoices[winnerColumns[1]] &&
                        playerChoices[winnerColumns[1]] == playerChoices[winnerColumns[2]] &&
                        playerChoices[winnerColumns[0]] != Player.NoOne ) {

                        btnReset.setVisibility ( View.VISIBLE );

                        gameOver = true;
                    String winnerOfGame = "";

                    if ( currentPlayer == Player.ONE ) {

                        winnerOfGame = "Player Two ";

                    } else if ( currentPlayer == Player.TWO ) {

                        winnerOfGame = "Player One ";


                    }

                    Toast.makeText ( this , winnerOfGame + "is the Winner" ,
                            Toast.LENGTH_LONG ).show ( );


                }

            }
        }
    }
    // Reset Game Function
    private void resetTheGame() {

        for (int index = 0; index < gridLayout.getChildCount(); index++) {

            ImageView imageView = (ImageView) gridLayout.getChildAt(index);
            imageView.setImageDrawable(null);
            imageView.setAlpha(0.2f);
        }

        currentPlayer = Player.ONE;
        countLoop ();
        gameOver = false;
        btnReset.setVisibility ( View.INVISIBLE );

    }
    private void countLoop(){
        for (int i =0; i < playerChoices.length; i++){
            playerChoices[i] = Player.NoOne;
        }
    }


}
