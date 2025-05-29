package gui.candidate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import logic.Agency;
import logic.candidate.Candidate;

public class CandidateFormDialog extends JDialog {
    private JTextField cidField, nameField, phoneField, specialityField, xpYearsField, branchField, addressField, schoolLevelField;
    private JComboBox<Character> sexComboBox;
    private Agency agency;
    private Candidate candidate;

    public CandidateFormDialog(JFrame parent, Agency agency, Candidate candidate) {
        super(parent, "Formulario de Candidato", true);
        this.agency = agency;
        this.candidate = candidate;

        setSize(400, 450);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(10, 2, 10, 10));

        add(new JLabel("ID:"));
        cidField = new JTextField();
        add(cidField);

        add(new JLabel("Nombre:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Sexo:"));
        sexComboBox = new JComboBox<>(new Character[]{'M', 'F'});
        add(sexComboBox);

        add(new JLabel("Teléfono:"));
        phoneField = new JTextField();
        add(phoneField);

        add(new JLabel("Especialidad:"));
        specialityField = new JTextField();
        add(specialityField);

        add(new JLabel("Años de Experiencia:"));
        xpYearsField = new JTextField();
        add(xpYearsField);
        
        add(new JLabel("Ramo:"));
        branchField = new JTextField();
        add(branchField);

        add(new JLabel("Dirección:"));
        addressField = new JTextField();
        add(addressField);

        add(new JLabel("Nivel Escolar:"));
        schoolLevelField = new JTextField();
        add(schoolLevelField);

        JButton saveButton = new JButton("Guardar");
        JButton cancelButton = new JButton("Cancelar");
        add(saveButton);
        add(cancelButton);

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
        specialityField.setText(candidate.getSpeciality());
        xpYearsField.setText(String.valueOf(candidate.getXpYears()));
        addressField.setText(candidate.getAddress());
        schoolLevelField.setText(candidate.getSchoolLevel());
    }

    private void saveCandidate() {
        try {
            String cid = cidField.getText();
            String name = nameField.getText();
            char sex = (char) sexComboBox.getSelectedItem();
            String phone = phoneField.getText();
            String speciality = specialityField.getText();
            int xpYears = Integer.parseInt(xpYearsField.getText());
            String branch = branchField.getText();
            String address = addressField.getText();
            String schoolLevel = schoolLevelField.getText();

            if (candidate == null) {
                Candidate newCandidate = new Candidate(cid, branch, name, sex, address, phone, schoolLevel, speciality, xpYears);
                agency.addCandidate(newCandidate);
            } else {
                candidate.setName(name);
                candidate.setSex(sex);
                candidate.setPhone(phone);
                candidate.setSpeciality(speciality);
                candidate.setXpYears(xpYears);
                candidate.setBranch(branch);
                candidate.setAddress(address);
                candidate.setSchoolLevel(schoolLevel);
            }

            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar el candidato: " + ex.getMessage());
        }
    }
}
