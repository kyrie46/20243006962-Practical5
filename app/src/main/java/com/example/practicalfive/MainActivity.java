package com.example.practicalfive;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Main screen of the application. The user types the name of a municipality and
 * presses Enter (or the Go key) to open the information screen.
 */
public class MainActivity extends AppCompatActivity {

    private final TextProcessor textProcessor = new TextProcessor();

    private EditText municipalityInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        municipalityInput = findViewById(R.id.editTextMunicipality);
        municipalityInput.setOnEditorActionListener((view, actionId, event) -> {
            boolean goActionPressed = actionId == EditorInfo.IME_ACTION_GO
                    || actionId == EditorInfo.IME_ACTION_DONE;
            boolean enterKeyPressed = event != null
                    && event.getAction() == KeyEvent.ACTION_DOWN
                    && event.getKeyCode() == KeyEvent.KEYCODE_ENTER;

            if (goActionPressed || enterKeyPressed) {
                submitMunicipality();
                return true;
            }
            return false;
        });
    }

    /**
     * Validates the typed text with {@link TextProcessor}. Valid input opens the
     * second Activity; invalid input shows an error and leaves the user here.
     */
    private void submitMunicipality() {
        String enteredText = municipalityInput.getText().toString();

        if (enteredText.trim().isEmpty()) {
            municipalityInput.setError(getString(R.string.error_empty_input));
            return;
        }

        String municipalityName = textProcessor.processMunicipality(enteredText);
        if (municipalityName == null) {
            municipalityInput.setError(getString(R.string.error_invalid_municipality));
            return;
        }

        Intent intent = new Intent(MainActivity.this, Municipality.class);
        intent.putExtra(Municipality.EXTRA_MUNICIPALITY_NAME, municipalityName);
        startActivity(intent);
    }
}
