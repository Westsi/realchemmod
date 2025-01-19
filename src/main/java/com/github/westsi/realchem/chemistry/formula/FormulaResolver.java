package com.github.westsi.realchem.chemistry.formula;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

public class FormulaResolver {
    private static String CACTUS = "https://cactus.nci.nih.gov/chemical/structure/";
    private static HashMap<String, String> cachedNames = new HashMap<>();
    private static HashMap<String, String> cachedFormulae = new HashMap<>();
    public static String getChemicalNameFromSMILES(String smiles) {
        if (cachedNames.containsKey(smiles)) {
            return cachedNames.get(smiles);
        }
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(CACTUS + smiles + "/iupac_name"))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String s = titleCase(response.body());

//            BufferedReader in = new BufferedReader(
//                    new InputStreamReader(con.getInputStream()));
//            String inputLine;
//            StringBuilder content = new StringBuilder();
//            while ((inputLine = in.readLine()) != null) {
//                content.append(inputLine);
//            }
//            in.close();
//            con.disconnect();
//            String s = titleCase(content.toString());
            cachedNames.put(smiles, s);
            return s;
        } catch(Exception e) {
            return "Unknown name";
        }
    }

    public static String getFormulaFromSMILES(String smiles) {
        if (cachedFormulae.containsKey(smiles)) {
            return cachedFormulae.get(smiles);
        }
        try {
//            URL url = new URI(CACTUS + smiles + "/formula").toURL();
//            HttpURLConnection con = (HttpURLConnection) url.openConnection();
//            con.setRequestMethod("GET");
//
//            BufferedReader in = new BufferedReader(
//                    new InputStreamReader(con.getInputStream()));
//            String inputLine;
//            StringBuilder content = new StringBuilder();
//            while ((inputLine = in.readLine()) != null) {
//                content.append(inputLine);
//            }
//            in.close();
//            con.disconnect();
//            String s = content.toString();
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(CACTUS + smiles + "/formula"))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String s = response.body();
            cachedFormulae.put(smiles, s);
            return s;
        } catch(Exception e) {
            return "Unknown name";
        }
    }

    private static String titleCase(String s) {
        StringBuilder titleCase = new StringBuilder(s.length());
        boolean nextTitleCase = true;

        for (char c : s.toCharArray()) {
            if (Character.isSpaceChar(c)) {
                nextTitleCase = true;
            } else if (nextTitleCase) {
                c = Character.toTitleCase(c);
                nextTitleCase = false;
            }

            titleCase.append(c);
        }

        return titleCase.toString();
    }
}
