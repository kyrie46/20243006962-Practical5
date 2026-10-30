package com.example.practicalfive;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

/**
 * Second screen. Shows the name, photograph and factual summary of the
 * municipality that was selected on the main screen, styled with that
 * municipality's colour.
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

    /** Named colour resource used for each municipality, in the same order. */
    private static final int[] MUNICIPALITY_COLORS = {
            R.color.municipality_beijing,
            R.color.municipality_shanghai,
            R.color.municipality_chongqing,
            R.color.municipality_tianjin
    };

    private final TextProcessor textProcessor = new TextProcessor();

    private String municipalityName;
    private Button visitSiteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_municipality);

        TextView nameView = findViewById(R.id.textViewMunicipalityName);
        TextView infoView = findViewById(R.id.textViewMunicipalityInfo);
        ImageView imageView = findViewById(R.id.imageViewMunicipality);
        visitSiteButton = findViewById(R.id.buttonVisitSite);

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

        applyMunicipalityStyle(nameView, infoView, index);
    }

    /**
     * Colours the second screen with the named colour of the displayed
     * municipality. Light text is used on the coloured backgrounds so that the
     * content stays readable.
     */
    private void applyMunicipalityStyle(TextView nameView, TextView infoView, int index) {
        int municipalityColor = ContextCompat.getColor(this, MUNICIPALITY_COLORS[index]);
        int lightTextColor = ContextCompat.getColor(this, R.color.color_text_light);

        nameView.setBackgroundColor(municipalityColor);
        nameView.setTextColor(lightTextColor);

        infoView.setTextColor(municipalityColor);

        visitSiteButton.setBackgroundTintList(ColorStateList.valueOf(municipalityColor));
        visitSiteButton.setTextColor(lightTextColor);
    }
}
