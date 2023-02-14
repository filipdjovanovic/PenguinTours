package com.penguins.project.populate;


import com.penguins.project.Security.JWTGenerator;
import com.penguins.project.model.Accomodation.Accomodation;
import com.penguins.project.model.Arrangement.Arrangement;
import com.penguins.project.model.Arrangement.ArrangementRepository;
import com.penguins.project.model.Location.Location;
import com.penguins.project.model.Program.Program;
import com.penguins.project.model.Role.Role;
import com.penguins.project.model.Role.RoleRepository;
import com.penguins.project.model.User.UserEntity;
import com.penguins.project.model.User.UserRepository;
import com.penguins.project.service.ArrangementService;
import com.penguins.project.service.LocationService;
import com.penguins.project.service.ReservationService;
import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.stream.Collectors;



@Component
public class Populator {


    private final ArrangementService arrangementService;
    private final ReservationService reservationService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTGenerator jwtGenerator;
    private final LocationService locationService;
    private final ArrangementRepository arrangementRepository;

    @Autowired
    public Populator(ArrangementService arrangementService, ReservationService reservationService, RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JWTGenerator jwtGenerator, LocationService locationService,
                     ArrangementRepository arrangementRepository) {
        this.arrangementService = arrangementService;
        this.reservationService = reservationService;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtGenerator = jwtGenerator;
        this.locationService = locationService;
        this.arrangementRepository = arrangementRepository;
    }
    public static Date between(Date startInclusive, Date endExclusive) {
        long startMillis = startInclusive.getTime();
        long endMillis = endExclusive.getTime();
        long randomMillisSinceEpoch = ThreadLocalRandom
                .current()
                .nextLong(startMillis, endMillis);

        return new Date(randomMillisSinceEpoch);
    }
    public void populateRole(){
        Role role = new Role();
        role.setName("ADMIN");
        roleRepository.save(role);


        role = new Role();
        role.setName("STAFF");
        roleRepository.save(role);

    }
    public void populateUser(){

        UserEntity admin = new UserEntity();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin"));
        Role adminRole = roleRepository.findByName("admin").get();
        admin.setRoles(Collections.singletonList(adminRole));

        userRepository.save(admin);

        List<String> users = List.of("nenad","ivan","danilo","filip","luka");

        for (int i =0; i< users.size();i++){
            UserEntity user = new UserEntity();
            user.setUsername(users.get(i));
            user.setPassword(passwordEncoder.encode("staff"));
            Role staffRole = roleRepository.findByName("STAFF").get();
            user.setRoles(Collections.singletonList(staffRole));

            userRepository.save(user);
        }


    }

    public static List<String> readFile(String fileName){
        List<String> lines = new ArrayList<>();
        File file = new File(fileName);
        String s = file.getAbsolutePath();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
        }

        return lines;
    }

    public static String getRandomLine(List<String> lines) {
        int randomIndex = (int) (Math.random() * lines.size());
        return lines.get(randomIndex);
    }


    public static java.sql.Date getRandomDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, (int) (Math.random() * (2024 - 2023 + 1) + 2022));
        calendar.set(Calendar.MONTH, (int) (Math.random() * (12 - 1 + 1) + 1));
        calendar.set(Calendar.DAY_OF_MONTH, (int) (Math.random() * (28 - 1 + 1) + 1));
        Date date = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = dateFormat.format(date);

        try {
            Date utilDate = dateFormat.parse(dateString);
            return new java.sql.Date(utilDate.getTime());
        } catch (ParseException e) {
            System.out.println("Invalid date format: " + dateString);
            return null;
        }
    }

    public static java.sql.Date getRandomDateBefore() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, (int) (Math.random() * (2022 - 2020 + 1) + 2020));
        calendar.set(Calendar.MONTH, (int) (Math.random() * (12 - 1 + 1) + 1));
        calendar.set(Calendar.DAY_OF_MONTH, (int) (Math.random() * (28 - 1 + 1) + 1));
        Date date = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = dateFormat.format(date);

        try {
            Date utilDate = dateFormat.parse(dateString);
            return new java.sql.Date(utilDate.getTime());
        } catch (ParseException e) {
            System.out.println("Invalid date format: " + dateString);
            return null;
        }
    }
    public static java.sql.Date incrementDate(java.sql.Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        return new java.sql.Date(calendar.getTime().getTime());
    }

    public static String getRandomTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, new Random().nextInt(24));
        calendar.set(Calendar.MINUTE, new Random().nextInt(60));
        calendar.set(Calendar.SECOND, new Random().nextInt(60));
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        return dateFormat.format(calendar.getTime());
    }

    public static List<String> stringToList(String input) {
        String[] items = input.split("_");
        return Arrays.asList(items);
    }

    public static Location getMostFrequentLocation(List<Location> locations) {
        Map<Location, Integer> locationCount = new HashMap<>();

        for (Location location : locations) {
            Integer count = locationCount.get(location);
            locationCount.put(location, count == null ? 1 : count + 1);
        }

        Location mostFrequentLocation = null;
        int maxCount = 0;
        for (Map.Entry<Location, Integer> entry : locationCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                mostFrequentLocation = entry.getKey();
                maxCount = entry.getValue();
            }
        }

        return mostFrequentLocation;
    }


    public void populateArrangements() {
        Random rand = new Random();
        Lorem lorem = LoremIpsum.getInstance();

        Integer UPPER_BOUND_PRICE = 3000;
        Integer LOWER_BOUND_PRICE = 100;

        Integer UPPER_BOUND_NUMBER_OF_PROGRAMS = 5;
        Integer LOWER_BOUND_NUMBER_OF_PROGRAMS = 1;

        List<String> namesOfAccomodations = readFile("popular.txt");
        List<String> namesOfLocations = readFile("locations");

        List<String> transporations = List.of("Voz", "Autobus", "Avion", "Brod", "Samostalni prevoz");
        List<String> namesOfStartingLocations = List.of("Kragujevac_Srbija_Evropa", "Beograd_Srbija_Evropa", "Nis_Srbija_Evropa");

        for (int i = 0; i < 2000 ; i++) {
            Arrangement arrangement = new Arrangement();
            arrangement.setPrice(rand.nextInt(UPPER_BOUND_PRICE - LOWER_BOUND_PRICE) + LOWER_BOUND_PRICE);
            arrangement.setStatus("Dostupno");
            arrangement.setRemark(lorem.getParagraphs(1, 3));
            arrangement.setTransportation(transporations.get(rand.nextInt(transporations.size())));

            Set<Program> programs = new HashSet<>();
            int numberOfPrograms = rand.nextInt(UPPER_BOUND_NUMBER_OF_PROGRAMS - LOWER_BOUND_NUMBER_OF_PROGRAMS) + LOWER_BOUND_NUMBER_OF_PROGRAMS;
            java.sql.Date firstDate = getRandomDate();

            String nameOfStartingLocation = namesOfStartingLocations.get(rand.nextInt(namesOfStartingLocations.size()));

            List<String> startingLocationCityCountryContinent = stringToList(nameOfStartingLocation);
            Location startinglocation = new Location();
            startinglocation.setCity(startingLocationCityCountryContinent.get(0));
            startinglocation.setCountry(startingLocationCityCountryContinent.get(1));
            startinglocation.setContinent(startingLocationCityCountryContinent.get(2));
            startinglocation.setPicture("images/" + nameOfStartingLocation + ".jpg");

            for (int j = 0; j < numberOfPrograms; j++) {
                Program program = new Program();

                Set<Location> locations = new HashSet<>();
                int numberOfLocations = rand.nextInt(3 - 1) + 1;
                if (j == 0 || j == numberOfPrograms - 1) {
                        locations.add(startinglocation);
                }
                for (int k = 0; k < numberOfLocations; k++) {
                    Location location = new Location();

                    String names = getRandomLine(namesOfLocations);
                    List<String> cityCountryContinent = stringToList(names);
                    location.setCity(cityCountryContinent.get(0));
                    location.setCountry(cityCountryContinent.get(1));
                    location.setContinent(cityCountryContinent.get(2));
                    location.setPicture("images/" + names + ".jpg");

                    locations.add(location);

                }
                program.setLocations(locations);

                program.setDate(incrementDate(firstDate, j).toLocalDate());
                if (j == 0) {
                    String randomTime = getRandomTime();
                    if (arrangement.getTransportation().equals("Voz")) {
                        String prefix = "Полазак са перона у " + randomTime + ". ";
                        program.setDescription(prefix + lorem.getParagraphs(1, 3));
                    } else if (arrangement.getTransportation().equals("Avion")) {
                        String prefix = "Полазак са аеродрома у" + randomTime + ". ";
                        program.setDescription(prefix + lorem.getParagraphs(1, 3));
                    } else {
                        program.setDescription(lorem.getParagraphs(1, 3));
                    }
                } else if (j == numberOfPrograms - 1) {
                    List<String> times = List.of("jутарњим", "преподневним", "пoподневним", "вечерњим");
                    String postfix = "Долазак у " + stringToList(getRandomLine(namesOfStartingLocations)).get(0) + " у " + times.get(rand.nextInt(times.size()));
                    program.setDescription(lorem.getParagraphs(1, 3) + postfix);
                } else {
                    program.setDescription(lorem.getParagraphs(1, 3));
                }

                programs.add(program);
            }

            arrangement.setPrograms(programs);

            List<String> listOfTypesOfAccomodation = List.of("1/1", "1/2", "1/3", "1/2+1", "1/3+1", "1/4");
            Set<Accomodation> accomodations = new HashSet<>();
            int numberOfAccomodations = rand.nextInt(3 - 1) + 1;
            for (int j = 0; j < numberOfAccomodations; j++) {
                Accomodation accomodation = new Accomodation();

                accomodation.setName(getRandomLine(namesOfAccomodations));
                accomodation.setCategory(rand.nextInt(5 - 1) + 1);
                accomodation.setType(listOfTypesOfAccomodation.get(rand.nextInt(listOfTypesOfAccomodation.size())));


                accomodation.setPicture1("images/" + (rand.nextInt(30 - 1) + 1) + ".jpg");
                accomodation.setPicture2("images/" + (rand.nextInt(30 - 1) + 1) + ".jpg");
                accomodation.setPicture3("images/" + (rand.nextInt(30 - 1) + 1) + ".jpg");
                accomodation.setPicture4("images/" + (rand.nextInt(30 - 1) + 1) + ".jpg");
                accomodation.setPicture5("images/" + (rand.nextInt(30 - 1) + 1) + ".jpg");
                accomodation.setPicture6("images/" + (rand.nextInt(30 - 1) + 1) + ".jpg");


                accomodation.setSafe(rand.nextBoolean());
                accomodation.setAc(rand.nextBoolean());
                accomodation.setFridge(rand.nextBoolean());
                accomodation.setTv(rand.nextBoolean());
                accomodation.setInternet(rand.nextBoolean());

                accomodations.add(accomodation);
            }
            List<Location> listOfLocationsInArrangement = new ArrayList<>();
            listOfLocationsInArrangement = arrangement.getPrograms()
                    .stream()
                    .map(program -> program.getLocations())
                    .flatMap(set -> set.stream())
                    .collect(Collectors.toList());
            Location mostFrequentLocation = getMostFrequentLocation(listOfLocationsInArrangement);

            arrangement.setName(mostFrequentLocation.getCity() + " " + firstDate + "_" + rand.nextInt(10000000));

            arrangement.setAccomodations(accomodations);
            arrangementService.saveArrangement(arrangement);
        }

        for (int i = 0; i < 20; i++) {
            Arrangement arrangement = new Arrangement();
            arrangement.setPrice(rand.nextInt(UPPER_BOUND_PRICE - LOWER_BOUND_PRICE) + LOWER_BOUND_PRICE);
            arrangement.setStatus("Nedostupno");
            arrangement.setRemark(lorem.getParagraphs(1, 3));
            arrangement.setTransportation(transporations.get(rand.nextInt(transporations.size())));

            Set<Program> programs = new HashSet<>();
            int numberOfPrograms = rand.nextInt(UPPER_BOUND_NUMBER_OF_PROGRAMS - LOWER_BOUND_NUMBER_OF_PROGRAMS) + LOWER_BOUND_NUMBER_OF_PROGRAMS;
            java.sql.Date firstDate = getRandomDateBefore();

            String nameOfStartingLocation = namesOfStartingLocations.get(rand.nextInt(namesOfStartingLocations.size()));

            List<String> startingLocationCityCountryContinent = stringToList(nameOfStartingLocation);
            Location startinglocation = new Location();
            startinglocation.setCity(startingLocationCityCountryContinent.get(0));
            startinglocation.setCountry(startingLocationCityCountryContinent.get(1));
            startinglocation.setContinent(startingLocationCityCountryContinent.get(2));
            startinglocation.setPicture("images/" + nameOfStartingLocation + ".jpg");

            for (int j = 0; j < numberOfPrograms; j++) {
                Program program = new Program();

                Set<Location> locations = new HashSet<>();
                int numberOfLocations = rand.nextInt(3 - 1) + 1;
                if (j == 0 || j == numberOfPrograms - 1) {
                    locations.add(startinglocation);
                }
                for (int k = 0; k < numberOfLocations; k++) {
                    Location location = new Location();

                    String names = getRandomLine(namesOfLocations);
                    List<String> cityCountryContinent = stringToList(names);
                    location.setCity(cityCountryContinent.get(0));
                    location.setCountry(cityCountryContinent.get(1));
                    location.setContinent(cityCountryContinent.get(2));
                    location.setPicture("images/" + names + ".jpg");

                    locations.add(location);

                }
                program.setLocations(locations);

                program.setDate(incrementDate(firstDate, j).toLocalDate());
                if (j == 0) {
                    String randomTime = getRandomTime();
                    if (arrangement.getTransportation().equals("Voz")) {
                        String prefix = "Полазак са перона у " + randomTime + ". ";
                        program.setDescription(prefix + lorem.getParagraphs(1, 3));
                    } else if (arrangement.getTransportation().equals("Avion")) {
                        String prefix = "Полазак са аеродрома у" + randomTime + ". ";
                        program.setDescription(prefix + lorem.getParagraphs(1, 3));
                    } else {
                        program.setDescription(lorem.getParagraphs(1, 3));
                    }
                } else if (j == numberOfPrograms - 1) {
                    List<String> times = List.of("jутарњим", "преподневним", "пoподневним", "вечерњим");
                    String postfix = "Долазак у " + stringToList(getRandomLine(namesOfStartingLocations)).get(0) + " у " + times.get(rand.nextInt(times.size()));
                    program.setDescription(lorem.getParagraphs(1, 3) + postfix);
                } else {
                    program.setDescription(lorem.getParagraphs(1, 3));
                }

                programs.add(program);
            }

            arrangement.setPrograms(programs);

            List<String> listOfTypesOfAccomodation = List.of("1/1", "1/2", "1/3", "1/2+1", "1/3+1", "1/4");
            Set<Accomodation> accomodations = new HashSet<>();
            int numberOfAccomodations = rand.nextInt(5 - 1) + 1;
            for (int j = 0; j < numberOfAccomodations; j++) {
                Accomodation accomodation = new Accomodation();

                accomodation.setName(getRandomLine(namesOfAccomodations));
                accomodation.setCategory(rand.nextInt(5 - 1) + 1);
                accomodation.setType(listOfTypesOfAccomodation.get(rand.nextInt(listOfTypesOfAccomodation.size())));


                accomodation.setPicture1("images/" + rand.nextInt(30 - 1) + 1 + ".jpg");
                accomodation.setPicture2("images/" + rand.nextInt(30 - 1) + 1 + ".jpg");
                accomodation.setPicture3("images/" + rand.nextInt(30 - 1) + 1 + ".jpg");
                accomodation.setPicture4("images/" + rand.nextInt(30 - 1) + 1 + ".jpg");
                accomodation.setPicture5("images/" + rand.nextInt(30 - 1) + 1 + ".jpg");
                accomodation.setPicture6("images/" + rand.nextInt(30 - 1) + 1 + ".jpg");


                accomodation.setSafe(rand.nextBoolean());
                accomodation.setAc(rand.nextBoolean());
                accomodation.setFridge(rand.nextBoolean());
                accomodation.setTv(rand.nextBoolean());
                accomodation.setInternet(rand.nextBoolean());

                accomodations.add(accomodation);
            }
            List<Location> listOfLocationsInArrangement = new ArrayList<>();
            listOfLocationsInArrangement = arrangement.getPrograms()
                    .stream()
                    .map(program -> program.getLocations())
                    .flatMap(set -> set.stream())
                    .collect(Collectors.toList());
            Location mostFrequentLocation = getMostFrequentLocation(listOfLocationsInArrangement);

            arrangement.setName(mostFrequentLocation.getCity() + " " + firstDate + "_" + rand.nextInt(100000000));

            arrangement.setAccomodations(accomodations);
            arrangementService.saveArrangement(arrangement);
        }



    }
}
