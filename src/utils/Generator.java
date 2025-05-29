package utils;

import java.util.ArrayList;
import java.util.Random;
import java.util.HashSet;

import utils.constants.Branch;
import utils.constants.Sector;
import utils.constants.Specialty;
import logic.candidate.Candidate;
import logic.company.Company;
import logic.company.Offer;

public class Generator {
    // Address components
    private static final String[] STREETS = {"Calle", "Avenida", "Carrera", "Zona", "Paseo", "Boulevard", "Camino", "Plaza"};
    private static final String[] STREET_SUFFIXES = {"A", "B", "C", "D", "E", "F", "G", "H"};
    
    // Person names
    private static final String[] BASE_NAMES = {"Juan", "Maria", "Carlos", "Ana", "Luis", "Elena", "Roberto", "Laura", "Miguel", "Sofia"};
    private static final String[] LAST_NAMES = {"Perez", "Gomez", "Rodriguez", "Lopez", "Martinez", "Garcia", "Hernandez", "Fernandez", "Ruiz", "Diaz"};
    private static final HashSet<String> generatedNames = new HashSet<>();
    
    // Other personal data
    private static final char[] GENDERS = {'M', 'F'};
    private static final String[] EDUCATION_LEVELS = {"Secundaria", "Pre", "Universidad"};
    
    // Professional data
    private static final String[] SPECIALTIES = Specialty.specialties;
    
    // Company data
    private static final String[] COMPANY_NAMES = {"TechCorp", "SafeGuard", "TravelEase", "CyberSecure", "HotelPlus", "DataShield"};
    private static final String[] COMPANY_PREFIXES = {"Empresa", "Tech", "Habana", "Cuba", "Global", "Innovación", "Soluciones", "Grupo", "Matarile"};
    private static final String[] COMPANY_SUFFIXES = {"Acueductos", "Electric", "Farmacos", "Consulting", "Logistics", "Industries", "Services", "Cuaso"};
    private static final HashSet<String> generatedCompanyNames = new HashSet<>();
    private static final HashSet<String> generatedAddresses = new HashSet<>();

    private static String generateUniqueName() {
        Random random = new Random();
        String fullName;
        do {
            String firstName = BASE_NAMES[random.nextInt(BASE_NAMES.length)];
            String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];
            fullName = firstName + " " + lastName;
        } while (!generatedNames.add(fullName));
        return fullName;
    }

    private static String generateUniqueAddress() {
        Random random = new Random();
        String address;
        do {
            String street = STREETS[random.nextInt(STREETS.length)];
            String suffix = STREET_SUFFIXES[random.nextInt(STREET_SUFFIXES.length)];
            int number = 1 + random.nextInt(100);
            address = street + " " + suffix + " #" + number;
        } while (!generatedAddresses.add(address));
        return address;
    }

    public static ArrayList<Candidate> generateCandidates(int quantity) {
        ArrayList<Candidate> candidates = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < quantity; i++) {
            String id = String.valueOf(10000000000L + (Math.abs(random.nextLong()) % 90000000000L));
            String name = generateUniqueName();
            char gender = GENDERS[random.nextInt(GENDERS.length)];
            String address = generateUniqueAddress();
            String phone = generateRandomPhoneNumber();
            String educationLevel = EDUCATION_LEVELS[random.nextInt(EDUCATION_LEVELS.length)];
            String branch = Branch.getRandomBranch();
            String specialty = SPECIALTIES[random.nextInt(SPECIALTIES.length)];
            int yearsOfExperience = random.nextInt(20);

            candidates.add(new Candidate(id, branch, name, gender, address, 
                                      phone, educationLevel, specialty, yearsOfExperience));
        }

        return candidates;
    }

    private static String generateUniqueCompanyName() {
        Random random = new Random();
        String companyName;
        do {
            String prefix = COMPANY_PREFIXES[random.nextInt(COMPANY_PREFIXES.length)];
            String base = COMPANY_NAMES[random.nextInt(COMPANY_NAMES.length)];
            String suffix = COMPANY_SUFFIXES[random.nextInt(COMPANY_SUFFIXES.length)];
            companyName = prefix + " " + base + " " + suffix;
        } while (!generatedCompanyNames.add(companyName));
        return companyName;
    }

    public static ArrayList<Company> generateCompanies(int quantity) {
        ArrayList<Company> companies = new ArrayList<>();

        for (int i = 0; i < quantity; i++) {
            String name = generateUniqueCompanyName();
            String address = generateUniqueAddress();
            String phone = generateRandomPhoneNumber();
            String sector = Sector.getRandomSector();

            companies.add(new Company(name, address, phone, sector));
        }

        return companies;
    }

    public static ArrayList<Company> generateOffers(int quantity, ArrayList<Company> companies) {
        Random random = new Random();

        for (int i = 0; i < quantity; i++) {
            Company company = companies.get(random.nextInt(companies.size()));
            String branch = Branch.getRandomBranch();
            double salary = 1000 + random.nextDouble() * 9000; // Salario entre 1000 y 10000

            company.createOffer(branch, salary);
        }

        return companies;
    }

    private static String generateRandomPhoneNumber() {
        Random random = new Random();
        return String.valueOf(10000000 + random.nextInt(90000000));
    }
}