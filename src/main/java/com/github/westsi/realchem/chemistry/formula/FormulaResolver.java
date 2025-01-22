package com.github.westsi.realchem.chemistry.formula;

import com.github.westsi.realchem.RealChemistry;

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
    private static HashMap<String, String> cachedNames = new HashMap<>() {{
//        put("[Ca]", "Calcium");
//        put("OS(=O)(=O)O", "Sulfuric Acid");
//        put("CC", "Ethane");

    }};
    private static HashMap<String, String> cachedFormulae = new HashMap<>() {{
//        put("[Ca]", "Ca");
//        put("OS(=O)(=O)O", "H2SO4");
//        put("CC", "C2H6");
    }};
    public static String getChemicalNameFromSMILES(String smiles) {
        if (cachedNames.containsKey(smiles)) {
            return cachedNames.get(smiles);
        }
        RealChemistry.LOGGER.info("Getting Chemical Name");
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(CACTUS + smiles + "/iupac_name"))
                    .build();
            RealChemistry.LOGGER.info("Sending Chemical Name Request" + smiles);
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String s = titleCase(response.body());
            RealChemistry.LOGGER.info("Received Chem Name Response");

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
            String s = checkElements(smiles);
            cachedNames.put(smiles, s);
            return s;
        }
    }

    public static String getFormulaFromSMILES(String smiles) {
        if (cachedFormulae.containsKey(smiles)) {
            return cachedFormulae.get(smiles);
        }
        RealChemistry.LOGGER.info("Getting Formula");
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
            RealChemistry.LOGGER.info("Sending Formula Request");
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String s = response.body();
            RealChemistry.LOGGER.info("Received Formula Response");
            cachedFormulae.put(smiles, s);
            return s;
        } catch(Exception e) {
            return "Unknown formula";
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
            } else {
                c = Character.toLowerCase(c);
            }

            titleCase.append(c);
        }

        return titleCase.toString();
    }

    private static String checkElements(String s) {
        s = s.replaceAll("[^a-zA-Z]", "");
        if (Element.exists(s)) {
            return Element.getBySymbol(s).getFullName();
        }
        return "Unknown name";
    }
}
