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
    private JTextField cidField, nameField, phoneField, xpYearsField, addressField, schoolLevelField;
    private JComboBox<Character> sexComboBox;
    private JComboBox<String> branchComboBox, specialityComboBox;
    private Agency agency;
    private Candidate candidate;
    private JTextField physicalEfficiencyField, medicalRecordField, languageCertificateField;
    private JPanel extraFieldsPanel;

    public CandidateFormDialog(JFrame parent, Candidate candidate) {
        super(parent, "Formulario de Candidato", true);
        this.agency = GlobalAgency.getInstance();
        this.candidate = candidate;

        setSize(500, 600);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));

        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(contentPanel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        contentPanel.add(new JLabel("Carnet:"), gbc);
        cidField = new JTextField();
        gbc.gridx = 1;
        contentPanel.add(cidField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        contentPanel.add(new JLabel("Nombre:"), gbc);
        nameField = new JTextField();
        gbc.gridx = 1;
        contentPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        contentPanel.add(new JLabel("Sexo:"), gbc);
        sexComboBox = new JComboBox<>(new Character[] { 'M', 'F' });
        gbc.gridx = 1;
        contentPanel.add(sexComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        contentPanel.add(new JLabel("Teléfono:"), gbc);
        phoneField = new JTextField();
        gbc.gridx = 1;
        contentPanel.add(phoneField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        contentPanel.add(new JLabel("Años de Experiencia:"), gbc);
        xpYearsField = new JTextField();
        gbc.gridx = 1;
        contentPanel.add(xpYearsField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        contentPanel.add(new JLabel("Ramo:"), gbc);
        branchComboBox = new JComboBox<>(Branch.BRANCHES);
        gbc.gridx = 1;
        contentPanel.add(branchComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        contentPanel.add(new JLabel("Especialidad:"), gbc);
        specialityComboBox = new JComboBox<>(Specialty.specialties);
        gbc.gridx = 1;
        contentPanel.add(specialityComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        contentPanel.add(new JLabel("Dirección:"), gbc);
        addressField = new JTextField();
        gbc.gridx = 1;
        contentPanel.add(addressField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        contentPanel.add(new JLabel("Nivel Escolar:"), gbc);
        schoolLevelField = new JTextField();
        gbc.gridx = 1;
        contentPanel.add(schoolLevelField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        extraFieldsPanel = new JPanel(new GridLayout(0, 2));
        contentPanel.add(extraFieldsPanel, gbc);

        // Campos adicionales
        physicalEfficiencyField = new JTextField();
        medicalRecordField = new JTextField();
        languageCertificateField = new JTextField();

        updateExtraFields();

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton saveButton = new JButton("Guardar");
        JButton cancelButton = new JButton("Cancelar");
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);

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
        xpYearsField.setText(String.valueOf(candidate.getXpYears()));
        branchComboBox.setSelectedItem(candidate.getBranch());
        addressField.setText(candidate.getAddress());
        specialityComboBox.setSelectedItem(candidate.getSpeciality());
        schoolLevelField.setText(candidate.getSchoolLevel());
    }

    private void updateExtraFields() {
        extraFieldsPanel.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        String selectedBranch = (String) branchComboBox.getSelectedItem();
        if ("custodio".equalsIgnoreCase(selectedBranch)) {
            extraFieldsPanel.add(new JLabel("Eficiencia Física:"), gbc);
            gbc.gridx = 1;
            extraFieldsPanel.add(physicalEfficiencyField, gbc);

            gbc.gridx = 0;
            gbc.gridy++;
            extraFieldsPanel.add(new JLabel("Número de Registro Médico:"), gbc);
            gbc.gridx = 1;
            extraFieldsPanel.add(medicalRecordField, gbc);
        } else if ("turismo".equalsIgnoreCase(selectedBranch)) {
            extraFieldsPanel.add(new JLabel("Certificado de Idiomas:"), gbc);
            gbc.gridx = 1;
            extraFieldsPanel.add(languageCertificateField, gbc);
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
            int xpYears = Integer.parseInt(xpYearsField.getText());
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
