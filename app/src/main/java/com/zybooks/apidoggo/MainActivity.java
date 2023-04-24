//I forgot to change this to something besides zybooks
package com.zybooks.apidoggo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    // The URL for the dog API
    String linkURL = "https://dog.ceo/api/breeds/image/random";

    // Declaring TextView and ImageView to display the image and breed
    TextView textView;
    ImageView imageView;

    //Used to make the API request using Volley
    RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //References the button in the layout and set an onclicklistener that calls imgRequest
        Button button = findViewById(R.id.button);
        // Lambda functions clean up the look fairly well
        button.setOnClickListener(view -> imgRequest());

        //References the textView and imageView in the layout
        textView = findViewById(R.id.textBox);
        imageView = findViewById(R.id.imageView);
        //Creates the new Request using Volley
        queue = Volley.newRequestQueue(this);
        // Calls the imgRequest method so that it makes the request when started
        imgRequest();
    }

    // Method for requesting and setting dog images.
    public void imgRequest() {

        // Create a new StringRequest object using Volley
        StringRequest request = new StringRequest(Request.Method.GET, linkURL, response -> {
            String url = response.substring(response.indexOf("\"h")+1, response.lastIndexOf("\","));
            String breed = response.substring(response.indexOf("s\\/")+3, response.lastIndexOf("\\/"));

            // Use the Picasso library to load the dog image from the URL into the ImageView object
            Picasso.get().load(url).into(imageView);

            // Set the breed text in the TextView object
            textView.setText(breed);
        }, error -> {
            // Log any errors that occur during the API request
            Log.d("error",error.toString());
        });

        // Add the StringRequest object to the RequestQueue object to make the API request
        queue.add(request);
    }
}