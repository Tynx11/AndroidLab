package com.tony.d.networkapp.api;

import android.support.annotation.NonNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by Tony on 30.10.2017.
 */

public class InputStreamService {

    public String load(@NonNull String urlString) throws IOException {
        URL url = new URL(urlString);
        InputStream in = url.openStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder result = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            result.append(line);
        }
        return result.toString();
    }
}
