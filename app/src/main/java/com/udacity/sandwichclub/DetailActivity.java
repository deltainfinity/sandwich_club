package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(this,json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {

        //get references to the TextViews
        TextView originTextView = findViewById(R.id.origin_tv);
        TextView descriptionTextView = findViewById(R.id.description_tv);
        TextView ingredientsTextView =  findViewById(R.id.ingredients_tv);
        TextView akaTextView = findViewById(R.id.also_known_tv);

        //populate the TextViews with the sandwich values
        originTextView.setText(sandwich.getPlaceOfOrigin());
        descriptionTextView.setText(sandwich.getDescription());
        List<String> ingredients = sandwich.getIngredients();
        String formattedIngredients = arrayToString(ingredients);
        ingredientsTextView.setText(formattedIngredients);
        List<String> aka = sandwich.getAlsoKnownAs();
        String formattedAKA = arrayToString(aka);
        akaTextView.setText(formattedAKA);
    }

    private String arrayToString(List<String> stringList)
    {
        StringBuilder sb = new StringBuilder();
        int lastIndex = stringList.size() - 1;
        for (int i = 0; i < lastIndex; i++)
        {
            sb.append(stringList.get(i));
            sb.append("\n");
        }
        if(stringList.size() > 0)
        {
            sb.append(stringList.get(lastIndex));
        }
        return sb.toString();
    }
}
