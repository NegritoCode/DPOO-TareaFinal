package gui.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.EmptyBorder;

import gui.components.MButton;
import logic.company.Company;
import logic.company.Offer;
import utils.constants.Branch;

public class OfferFormDialog extends JDialog {
    private static final long serialVersionUID = 1L;
    private JSpinner salarySpinner;
    private JComboBox<String> branchComboBox;
    private Company company;
    private Offer offer;
    
    /**
     * Si el dialogo esta creando una oferta
     */
    public OfferFormDialog(JFrame parent, Company company) {
        this(parent, company, null);
    }
    
    /**
     * Si el dialogo esta editando una oferta
     * @wbp.parser.constructor
     */
    public OfferFormDialog(JFrame parent, Company company, Offer offer) {
        super(parent, "Formulario de Oferta", true);
        setResizable(false);
        this.company = company;
        this.offer = offer;

        initializeUI();
        
        if (offer != null) {
            branchComboBox.setSelectedItem(offer.getBranch());
            branchComboBox.setEnabled(false);
            salarySpinner.setValue(offer.getSalary());  // Cambiado para usar JSpinner
        }
        
        setVisible(true);
    }

    private void initializeUI() {
        setSize(400, 206);
        setLocationRelativeTo(getParent());
        getContentPane().setLayout(new BorderLayout(10, 10));
        
        // Panel principal con borde
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        getContentPane().add(mainPanel, BorderLayout.CENTER);
        

        JPanel formPanel = new JPanel(new GridLayout(2, 2, 5, 10));
        formPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        formPanel.add(new JLabel("Rama:"));
        branchComboBox = new JComboBox<>();
        for (String branch : Branch.BRANCHES) {
            branchComboBox.addItem(branch);
        }
        formPanel.add(branchComboBox);
        
        formPanel.add(new JLabel("Salario:"));
        // Configuración del JSpinner
        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(
            0.0,    // valor inicial
            0.0,     // valor mínimo
            Double.MAX_VALUE, // valor máximo
            100.0    // incremento/decremento
        );
        salarySpinner = new JSpinner(spinnerModel);
        
        // Formatear para mostrar 2 decimales
        JSpinner.NumberEditor editor = new JSpinner.NumberEditor(salarySpinner, "#,##0.00");
        salarySpinner.setEditor(editor);
        
        formPanel.add(salarySpinner);
        
        mainPanel.add(formPanel, BorderLayout.CENTER);
        
        // Panel de botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton saveButton = new MButton("Guardar", new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent arg0) {
                saveOffer();
            }
        });
        JButton cancelButton = new MButton("Cancelar",  new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent arg0) {
                dispose();
            }
        });
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void saveOffer() {
        try {
            String branch = (String) branchComboBox.getSelectedItem();
            double salary = ((Number) salarySpinner.getValue()).doubleValue(); 

            if (offer == null) {
                company.createOffer(branch, salary);
            } else {
                offer.setBranch(branch);
                offer.setSalary(salary);
            }
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar la oferta: " + ex.getMessage());
        }
    }
}