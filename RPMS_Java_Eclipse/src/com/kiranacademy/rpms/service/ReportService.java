package com.kiranacademy.rpms.service;

import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;

import com.kiranacademy.rpms.util.DBConnection;

public class ReportService {

    public Map<String, Integer> candidateCountByTechnology() {
        Map<String, Integer> report = new LinkedHashMap<>();

        String sql = "SELECT primary_technology, COUNT(*) total " +
                     "FROM candidates GROUP BY primary_technology ORDER BY total DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                report.put(rs.getString("primary_technology"), rs.getInt("total"));
            }
            return report;

        } catch (SQLException e) {
            throw new RuntimeException("Unable to generate report", e);
        }
    }
}
