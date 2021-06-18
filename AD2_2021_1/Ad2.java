package AD2_2021_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Ad2 {
    public static void main(String[] args) {
        List<String> fileContent = readFile("src/ad2/input.txt");

        String countryWithMoreGoldMedals = "";
        String countryWithMoreMedals = "";
        String athleteWithMoreGoldMedals = "";
        String athleteWithMoreMedals = "";
        String athleteWhichSpentMoreTime = "";

        List<String> athletesWithGoldMedals = new ArrayList();
        List<String> goldMedalsCountries = new ArrayList();
        List<String> countriesWithMedals = new ArrayList();
        List<String> athletesWithMedals = new ArrayList();
        Map<String, Double> athletesTime = new HashMap();

        for (int i = 0; i < fileContent.size(); i++) {
            if (i == 0) { continue; }

            String[] informationArray = fileContent.get(i).split(";");

            boolean isRelay = informationArray[0].contains("Revezamento");

            if (isRelay) {
                String[] goldAthletes = informationArray[1].split("/");
                Collections.addAll(athletesWithGoldMedals, goldAthletes);
                Collections.addAll(athletesWithMedals, goldAthletes);
                goldMedalsCountries.add(informationArray[2]);
                countriesWithMedals.add(informationArray[2]);

                Double individualTime = convertStringTimeToDouble(informationArray[3]) / goldAthletes.length;


                for (String athlete : goldAthletes) {
                    if (athletesTime.containsKey(athlete)) {
                        athletesTime.put(athlete, athletesTime.get(athlete) + individualTime);
                    } else {
                        athletesTime.put(athlete, individualTime);
                    }
                }

                String[] medalAthletes = informationArray[4].split("/");
                Collections.addAll(athletesWithMedals, medalAthletes);
                countriesWithMedals.add(informationArray[5]);
                individualTime = convertStringTimeToDouble(informationArray[6]) / medalAthletes.length;


                for (String athlete : medalAthletes) {
                    if (athletesTime.containsKey(athlete)) {
                        athletesTime.put(athlete, athletesTime.get(athlete) + individualTime);
                    } else {
                        athletesTime.put(athlete, individualTime);
                    }
                }

                medalAthletes = informationArray[7].split("/");
                Collections.addAll(athletesWithMedals, medalAthletes);
                countriesWithMedals.add(informationArray[8]);

                individualTime = convertStringTimeToDouble(informationArray[9]) / medalAthletes.length;


                for (String athlete : medalAthletes) {
                    if (athletesTime.containsKey(athlete)) {
                        athletesTime.put(athlete, athletesTime.get(athlete) + individualTime);
                    } else {
                        athletesTime.put(athlete, individualTime);
                    }
                }
                continue;
            }

            athletesWithGoldMedals.add(informationArray[1]);
            goldMedalsCountries.add(informationArray[2]);

            athletesWithMedals.add(informationArray[1]);
            countriesWithMedals.add(informationArray[2]);

            if (athletesTime.containsKey(informationArray[1])) {
                athletesTime.put(informationArray[1], athletesTime.get(informationArray[1]) + convertStringTimeToDouble(informationArray[3]));
            } else {
                athletesTime.put(informationArray[1], convertStringTimeToDouble(informationArray[3]));
            }

            athletesWithMedals.add(informationArray[4]);
            countriesWithMedals.add(informationArray[5]);

            if (athletesTime.containsKey(informationArray[4])) {
                athletesTime.put(informationArray[4], athletesTime.get(informationArray[4]) + convertStringTimeToDouble(informationArray[6]));
            } else {
                athletesTime.put(informationArray[4], convertStringTimeToDouble(informationArray[6]));
            }

            athletesWithMedals.add(informationArray[7]);
            countriesWithMedals.add(informationArray[8]);

            if (athletesTime.containsKey(informationArray[7])) {
                athletesTime.put(informationArray[7], athletesTime.get(informationArray[7]) + convertStringTimeToDouble(informationArray[9]));
            } else {
                athletesTime.put(informationArray[7], convertStringTimeToDouble(informationArray[9]));
            }

        }

        countryWithMoreGoldMedals = getMode(goldMedalsCountries);
        countryWithMoreMedals = getMode(countriesWithMedals);
        athleteWithMoreGoldMedals = getMode(athletesWithGoldMedals);
        athleteWithMoreMedals = getMode(athletesWithMedals);

        Double mostTimeCompeting = 0.0;

        for (Map.Entry<String, Double> entry : athletesTime.entrySet()) {
            String athlete = entry.getKey();
            Double time = entry.getValue();
            if (time > mostTimeCompeting) {
                mostTimeCompeting = time;
                athleteWhichSpentMoreTime = athlete;
            }
        }


        System.out.println("O país com mais medalhas de ouro é " + countryWithMoreGoldMedals);
        System.out.println("O país com mais medalhas é " + countryWithMoreMedals);
        System.out.println("O atleta com mais medalhas de ouro é " + athleteWithMoreGoldMedals);
        System.out.println("O atleta com mais medalhas é " + athleteWithMoreMedals);
        System.out.println("O atleta que gastou mais tempo competindo foi " + athleteWhichSpentMoreTime);

    }

    static List<String> readFile(String filename) {
        List<String> fileContent = new ArrayList();

        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            while ( scanner.hasNextLine() ) {
                fileContent.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Nao foi possivel ler o arquivo");
            e.printStackTrace();
        }

        return fileContent;
    }

    static Double convertStringTimeToDouble(String time) {
        String[] timeSplitter = time.split(":");
        Double individualTime;
        if (timeSplitter.length == 2) {
            int minutes = Integer.parseInt(timeSplitter[0]);
            Double seconds = Double.parseDouble(timeSplitter[1]);
            individualTime = minutes * 60 + seconds;
        } else if (timeSplitter.length == 3) {
            int hours = Integer.parseInt(timeSplitter[0]);
            int minutes = Integer.parseInt(timeSplitter[1]);
            Double seconds = Double.parseDouble(timeSplitter[2]);
            individualTime = hours * 3600 + minutes * 60 + seconds;
        } else {
            individualTime = Double.parseDouble(time);
        }

        return individualTime;
    }

    static String getMode(List<String> list) {
        long frequency = 0;
        String mode = "";

        while (list.size() != 0 ) {
            String item = list.get(0);
            long itemFrequency = list.stream().filter( a -> a.equals(item)).count();

            if (itemFrequency > frequency) {
                mode = item;
                frequency = itemFrequency;
            }

            list = list.stream().filter( a -> !a.equals(item)).collect(Collectors.toList());
        }

        return mode;
    }


}
