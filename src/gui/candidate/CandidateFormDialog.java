package gui.candidate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;
import logic.Agency;
import logic.GlobalAgency;
import logic.candidate.Candidate;
import logic.candidate.SegurityCandidate;
import logic.candidate.TourismCandidate;
import utils.constants.Branch;
import utils.constants.Specialty;

public class CandidateFormDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	
	private JTextField cidField, nameField, phoneField, addressField, schoolLevelField;
    private JComboBox<Character> sexComboBox;
    private JComboBox<String> branchComboBox, specialityComboBox;
    private Agency agency;
    private Candidate candidate;
    private JSpinner xpYearsField;
    private JTextField physicalEfficiencyField, medicalRecordField, languageCertificateField;
    private JPanel extraFieldsPanel;

    public CandidateFormDialog(JFrame parent, Candidate candidate) {
        super(parent, "Formulario de Candidato", true);
        setResizable(false);
        this.agency = GlobalAgency.getInstance();
        this.candidate = candidate;

        setSize(500, 600);
        setLocationRelativeTo(parent);
        getContentPane().setLayout(new BorderLayout(10, 10));

        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        JPanel formPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        contentPanel.add(formPanel, BorderLayout.CENTER);

        formPanel.add(new JLabel("Carnet:"));
        cidField = new JTextField();
        formPanel.add(cidField);

        formPanel.add(new JLabel("Nombre:"));
        nameField = new JTextField();
        formPanel.add(nameField);

        formPanel.add(new JLabel("Sexo:"));
        sexComboBox = new JComboBox<>();
        sexComboBox.addItem('M');
        sexComboBox.addItem('F');
        formPanel.add(sexComboBox);

        formPanel.add(new JLabel("Teléfono:"));
        phoneField = new JTextField();
        formPanel.add(phoneField);

        formPanel.add(new JLabel("Años de Experiencia:"));
        xpYearsField = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
        formPanel.add(xpYearsField);

        formPanel.add(new JLabel("Ramo:"));
        branchComboBox = new JComboBox<>();
        for (String branch : Branch.BRANCHES) {
        	branchComboBox.addItem(branch);
        }
        formPanel.add(branchComboBox);

        formPanel.add(new JLabel("Especialidad:"));
        specialityComboBox = new JComboBox<>();
        for (String branch : Specialty.specialties) {
        	specialityComboBox.addItem(branch);
        }
        formPanel.add(specialityComboBox);

        formPanel.add(new JLabel("Dirección:"));
        addressField = new JTextField();
        formPanel.add(addressField);

        formPanel.add(new JLabel("Nivel Escolar:"));
        schoolLevelField = new JTextField();
        formPanel.add(schoolLevelField);

        extraFieldsPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        contentPanel.add(extraFieldsPanel, BorderLayout.SOUTH);

        physicalEfficiencyField = new JTextField();
        medicalRecordField = new JTextField();
        languageCertificateField = new JTextField();

        updateExtraFields();

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton saveButton = new JButton("Guardar");
        JButton cancelButton = new JButton("Cancelar");
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        branchComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateExtraFields();
            }
        });

        if (candidate != null) {
            loadCandidateData();
        }

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveCandidate();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void loadCandidateData() {
        cidField.setText(candidate.getCid());
        cidField.setEnabled(false);
        nameField.setText(candidate.getName());
        sexComboBox.setSelectedItem(candidate.getSex());
        phoneField.setText(candidate.getPhone());
        xpYearsField.setValue(candidate.getXpYears()); // Actualizar el valor del JSpinner
        branchComboBox.setSelectedItem(candidate.getBranch());
        addressField.setText(candidate.getAddress());
        specialityComboBox.setSelectedItem(candidate.getSpeciality());
        schoolLevelField.setText(candidate.getSchoolLevel());
    }

    private void updateExtraFields() {
        extraFieldsPanel.removeAll();

        String selectedBranch = (String) branchComboBox.getSelectedItem();
        if ("custodio".equalsIgnoreCase(selectedBranch)) {
            extraFieldsPanel.add(new JLabel("Eficiencia Física:"));
            extraFieldsPanel.add(physicalEfficiencyField);

            extraFieldsPanel.add(new JLabel("Número de Registro Médico:"));
            extraFieldsPanel.add(medicalRecordField);
        } else if ("turismo".equalsIgnoreCase(selectedBranch)) {
            extraFieldsPanel.add(new JLabel("Certificado de Idiomas:"));
            extraFieldsPanel.add(languageCertificateField);
        }

        extraFieldsPanel.revalidate();
        extraFieldsPanel.repaint();
    }

    private void saveCandidate() {
        try {
            String cid = cidField.getText();
            String name = nameField.getText();
            char sex = (char) sexComboBox.getSelectedItem();
            String phone = phoneField.getText();
            int xpYears = (int) xpYearsField.getValue(); // Obtener el valor del JSpinner
            String branch = (String) branchComboBox.getSelectedItem();
            String speciality = (String) specialityComboBox.getSelectedItem();
            String address = addressField.getText();
            String schoolLevel = schoolLevelField.getText();

            Candidate candidate = agency.createCandidate(cid, branch, name, sex, address, phone, schoolLevel,
                    speciality, xpYears);

            if (candidate instanceof SegurityCandidate) {
                String physicalEfficiency = physicalEfficiencyField.getText();
                String medicalRecord = medicalRecordField.getText();

                ((SegurityCandidate) candidate).setPhysicalEfficiencyScores(physicalEfficiency);
                ((SegurityCandidate) candidate).setMedicalRecordNumber(medicalRecord);
            } else if (candidate instanceof TourismCandidate) {
                String languageCertificate = languageCertificateField.getText();

                ((TourismCandidate) candidate).setLanguageCertificate(languageCertificate);
            }

            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar el candidato: " + ex.getMessage());
        }
    }
}
