package com.shivam.lionortiger;



import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    enum Player {

        ONE, TWO, No

    }

    Player currentPlayer = Player.ONE;


    Player[] playerChoices = new Player[9];

    int[][] winnerRowsColumns = {{0, 1, 2}, {3, 4, 5}, { 6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    private boolean gameOver = false;
    private Button btnReset;
    private GridLayout gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int index = 0; index < playerChoices.length ; index++) {
            playerChoices[index] = Player.No;
        }

        btnReset = findViewById(R.id.btnReset);
        gridLayout = findViewById(R.id.gridLayout);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                resetTheGame();

            }
        });
        
    }
    
    public void imageViewIsTapped(View imageView) {


        
        ImageView tappedImageView = (ImageView) imageView;
        
        tappedImageView.setTranslationX(-2000);
        int titag = Integer.parseInt(tappedImageView.getTag().toString());

        if (playerChoices[titag] == Player.No && gameOver == false) {
            playerChoices[titag] = currentPlayer; //As array starts with index 0


            if (currentPlayer == Player.ONE) {

                tappedImageView.setImageResource(R.drawable.lion);
                currentPlayer = Player.TWO;

            } else if (currentPlayer == Player.TWO) {

                tappedImageView.setImageResource(R.drawable.tiger);
                currentPlayer = Player.ONE;


            }


            tappedImageView.animate().translationXBy(2000).alpha(1).rotation(3600).setDuration(2000);


            for (int[] winnerColumns : winnerRowsColumns) {

                if (playerChoices[winnerColumns[0]] == playerChoices[winnerColumns[1]] &&
                        playerChoices[winnerColumns[1]] == playerChoices[winnerColumns[2]] &&
                        playerChoices[winnerColumns[0]] != Player.No) {
                    btnReset.setVisibility(View.VISIBLE);

                    gameOver = true;
                    String winnerOfGame = "";

                    if (currentPlayer == Player.ONE) {

                        winnerOfGame = "Player Two ";

                    } else if (currentPlayer == Player.TWO) {

                        winnerOfGame = "Player One ";

                    }

                    Toast.makeText(this, winnerOfGame + "is the Winner", Toast.LENGTH_SHORT).show();


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
        for (int index = 0; index < playerChoices.length ; index++) {
            playerChoices[index] = Player.No;
        }


        gameOver = false;
        btnReset.setVisibility(View.INVISIBLE);

    }
    
}
