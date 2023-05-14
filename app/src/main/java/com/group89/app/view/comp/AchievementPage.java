package com.group89.app.view.comp;

import com.group89.app.model.AchievementRecord;
import com.group89.app.model.AchievementType;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AchievementPage extends JPanel {
    private List<AchievementRecord> achievements;
    private JTable table;
    private DefaultTableModel tableModel;

    public AchievementPage() {
        // Initialize achievements
        achievements = new ArrayList<>();
        achievements.add(new AchievementRecord("Advanced individual in volunteer service", 2020, "Assessed by the Youth League Committee of the College", AchievementType.Honour));
        achievements.add(new AchievementRecord("National first prize",2018,"China \"Internet Plus\" College Student Innovation and Entrepreneurship Competition", AchievementType.Award));
        achievements.add(new AchievementRecord("National third prize",2019,"Challenge Cup\" National College students extracurricular academic science and technology works competition", AchievementType.Award));
        achievements.add(new AchievementRecord("Municipal third prize",2022,"Challenge Cup\" Business Plan Competition for Chinese College students ACM-ICPC International College Student Programming Competition", AchievementType.Award));
        achievements.add(new AchievementRecord("University-level first prize",2021,"National Electronic Design Competition for College students", AchievementType.Award));
        achievements.add(new AchievementRecord("Outstanding student leaders",2020,"School Youth League committee assessment", AchievementType.Honour));
        achievements.add(new AchievementRecord("Excellent Communist Youth League member",2018,"School Youth League committee assessment", AchievementType.Honour));
        achievements.add(new AchievementRecord("Advanced individual in volunteer service",2020,"Assessed by the Youth League Committee of the College", AchievementType.Honour));
        achievements.add(new AchievementRecord("Advanced individual in social practice",2022,"Assessed by the Youth League Committee of the College", AchievementType.Honour));


        // Set up table
        String[] columnNames = {"Year", "Title", "Details", "Type"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        table.setFont(new Font("Arial", Font.PLAIN, 16)); // 设置表格字体大小
        table.setRowHeight(30); // 设置表格行高
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(80); // 设置“Year”列的宽度
        columnModel.getColumn(1).setPreferredWidth(300); // 设置“Title”列的宽度
        columnModel.getColumn(2).setPreferredWidth(600); // 设置“Details”列的宽度
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Set up filter panel
        setLayout(new BorderLayout());

        JPanel filterPanel = new JPanel();
        JLabel yearLabel = new JLabel("Filter by Year:");
        yearLabel.setFont(new Font("Arial", Font.PLAIN, 16)); // 设置字体大小
        JTextField yearField = new JTextField(10);
        yearField.setFont(new Font("Arial", Font.PLAIN, 16)); // 设置字体大小
        JButton filterButton = new JButton("Filter");
        filterButton.setFont(new Font("Arial", Font.PLAIN, 16)); // 设置字体大小
        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filterYear = yearField.getText();
                if (filterYear.isEmpty()) {
                    refreshTable(achievements);
                } else {
                    Integer filterYearInt = Integer.valueOf(filterYear);
                    List<AchievementRecord> filteredAchievements = achievements.stream()
                            .filter(a -> a.getYear().equals(filterYearInt))
                            .collect(Collectors.toList());
                    refreshTable(filteredAchievements);
                }
            }
        });
        add(scrollPane, BorderLayout.CENTER);
        add(filterPanel, BorderLayout.SOUTH);

        filterPanel.add(yearLabel);
        filterPanel.add(yearField);
        filterPanel.add(filterButton);
        add(filterPanel, BorderLayout.SOUTH);

        // Load initial data
        refreshTable(achievements);
    }

    private void refreshTable(List<AchievementRecord> data) {
        tableModel.setRowCount(0);
        for (AchievementRecord achievement : data) {
            Object[] row = {achievement.getYear(), achievement.getTitle(), achievement.getDetails(), achievement.getAchievementType().name()};
            tableModel.addRow(row);
        }
    }
}
