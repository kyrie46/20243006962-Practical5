package com.example.practicalfive;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Second screen. Shows the name, photograph and factual summary of the
 * municipality that was selected on the main screen.
 */
public class Municipality extends AppCompatActivity {

    /** Intent extra key shared with {@link MainActivity}. */
    public static final String EXTRA_MUNICIPALITY_NAME =
            "com.example.practicalfive.extra.MUNICIPALITY_NAME";

    /** Image shown for each municipality, in municipalities_array order. */
    private static final int[] MUNICIPALITY_IMAGES = {
            R.drawable.beijing,
            R.drawable.shanghai,
            R.drawable.chongqing,
            R.drawable.tianjin
    };

    private final TextProcessor textProcessor = new TextProcessor();

    private String municipalityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_municipality);

        TextView nameView = findViewById(R.id.textViewMunicipalityName);
        TextView infoView = findViewById(R.id.textViewMunicipalityInfo);
        ImageView imageView = findViewById(R.id.imageViewMunicipality);

        String rawName = getIntent().getStringExtra(EXTRA_MUNICIPALITY_NAME);
        String validatedName = textProcessor.processMunicipality(rawName);
        int index = textProcessor.indexOfMunicipality(validatedName);

        if (validatedName == null || index < 0) {
            // No valid municipality was passed in, so there is nothing to show.
            Toast.makeText(this, R.string.error_missing_municipality, Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        municipalityName = validatedName;

        String[] summaries = getResources().getStringArray(R.array.municipalities_array);
        nameView.setText(validatedName);
        imageView.setImageResource(MUNICIPALITY_IMAGES[index]);
        imageView.setContentDescription(
                getString(R.string.content_description_municipality_photo, validatedName));
        infoView.setText(summaries[index]);
    }
}
