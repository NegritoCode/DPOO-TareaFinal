package utils;

import java.util.ArrayList;
import java.util.Random;

import utils.constants.Branch;
import utils.constants.Sector;
import utils.constants.Specialty;
import logic.candidate.Candidate;
import logic.company.Company;


public class Generator {
	private static final String[] NOMBRES = {"Juan", "Maria", "Carlos", "Ana", "Luis", "Elena", "Roberto", "Laura", "Miguel", "Sofia"};
    private static final char[] SEXOS = {'M', 'F'};
    private static final String[] RAMAS = Branch.BRANCHES;
    private static final String[] DIRECCIONES = {"Calle A", "Avenida B", "Carrera C", "Zona D", "Paseo E"};
    private static final String[] ESPECIALIDADES = Specialty.specialties;
    private static final String[] NIVELES = {"Secundaria","Pre","Universidad"};
    private static final String[] NAMES = {"TechCorp", "SafeGuard", "TravelEase", "CyberSecure", "HotelPlus", "DataShield"};
    private static final String[] SECTORS = Sector.SECTORS;
    private static final String[] ADDRESSES = {"Calle A", "Avenida B", "Carrera C", "Zona D", "Paseo E"};
    
    public static ArrayList<Candidate> generarCandidatos(int cantidad) {
        ArrayList<Candidate> candidatos = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < cantidad; i++) {
        	String carnetID = String.valueOf(10000000000L + (Math.abs(random.nextLong()) % 90000000000L)); // Generar CI de 11 dígitos
            String nombreAleatorio = NOMBRES[random.nextInt(NOMBRES.length)];
            char sexoAleatorio = SEXOS[random.nextInt(SEXOS.length)];
            String direccionAleatoria = DIRECCIONES[random.nextInt(DIRECCIONES.length)];
            String telefonoAleatorio = String.valueOf(10000000 + random.nextInt(90000000)); // Teléfono de 8 dígitos
            String nivelesAleatorios = NIVELES[random.nextInt(NIVELES.length)];
            String ramaAleatoria = RAMAS[random.nextInt(RAMAS.length)];
            String especialidadAleatoria = ESPECIALIDADES[random.nextInt(ESPECIALIDADES.length)];
            int anosExpAleatorio = random.nextInt(20); // Años de experiencia entre 0 y 19

            candidatos.add(new Candidate(carnetID, ramaAleatoria, nombreAleatorio, sexoAleatorio, direccionAleatoria,telefonoAleatorio,nivelesAleatorios, especialidadAleatoria, anosExpAleatorio));
        }

        return candidatos;
        
       
    }
    public static ArrayList<Company> generateCompanies(int quantity) {
        ArrayList<Company> companies = new ArrayList<Company>();
        Random random = new Random();

        for (int i = 0; i < quantity; i++) {
            
            String name = NAMES[random.nextInt(NAMES.length)];
            String address = ADDRESSES[random.nextInt(ADDRESSES.length)];
            String phone = String.valueOf(10000000 + random.nextInt(90000000)); // 8 dígitos
            String sector = SECTORS[random.nextInt(SECTORS.length)];

            companies.add(new Company( name, address, phone, sector));
        }

        return companies;
    }
}
