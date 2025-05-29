package gui.interview;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import logic.Agency;
import logic.GlobalAgency;
import logic.interview.Day;
import logic.interview.Interview;
import logic.interview.MonthRegister;
import utils.Navigation;

public class MonthViewScreen extends JFrame {
    private Agency agency;
    private MonthRegister month;

    public MonthViewScreen() {
        this.agency = GlobalAgency.getInstance();
        this.month = agency.getMonths().get(0);

        setTitle("Vista Mensual");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Header
        JLabel headerLabel = new JLabel("Vista Mensual: " + month.getId(), JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setOpaque(true);
        headerLabel.setBackground(new Color(63, 81, 181));
        headerLabel.setForeground(Color.WHITE);
        add(headerLabel, BorderLayout.NORTH);

        // Calendar Panel
        JPanel calendarPanel = new JPanel();
        calendarPanel.setLayout(new GridLayout(5, 7, 5, 5));
        for (Day day : month.getDays()) {
            JButton dayButton = new JButton(day.getId());
            dayButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showInterviews(day);
                }
            });
            calendarPanel.add(dayButton);
        }
        add(calendarPanel, BorderLayout.CENTER);

        // Back Button
        JButton backButton = new JButton("Atrás");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Navigation.goTo("Home");
            }
        });
        add(backButton, BorderLayout.SOUTH);
    }

    private void showInterviews(Day day) {
        StringBuilder interviewsInfo = new StringBuilder("Entrevistas para el día " + day.getId() + ":\n");
        for (Interview interview : day.getInterviews()) {
            interviewsInfo.append("- Candidato: ").append(interview.getCandidateCid())
                    .append(", Empresa: ").append(interview.getCompanyId())
                    .append(", Oferta: ").append(interview.getOfferId()).append("\n");
        }
        JOptionPane.showMessageDialog(this, interviewsInfo.toString(), "Entrevistas", JOptionPane.INFORMATION_MESSAGE);
    }
}
