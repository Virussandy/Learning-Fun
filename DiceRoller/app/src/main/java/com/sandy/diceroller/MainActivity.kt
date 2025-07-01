package com.sandy.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dice_button : Button = findViewById(R.id.dice_roll);
        dice_button.setOnClickListener {
            rollDice();
        }
    }
    private fun rollDice(){
        val dice = Dice(6)
        val diceRoll = dice.roll()
        val myLuckyNumber = 3
        val dice_image : ImageView = findViewById(R.id.dice_image);

        val drawableResource = when(diceRoll){
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        dice_image.setImageResource(drawableResource);
        dice_image.contentDescription = diceRoll.toString();
    }
}

class Dice(val i: Int) {
    fun roll(): Int{
        return (1..i).random()
    }
}
