package com.kiranacademy.rpms.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.kiranacademy.rpms.model.Application;
import com.kiranacademy.rpms.util.DBConnection;

public class ApplicationDAOImpl implements ApplicationDAO {

    @Override
    public int save(Application a) {
        String sql = "INSERT INTO applications " +
                "(application_code,candidate_id,job_id,eligibility_status,eligibility_reason,application_status) " +
                "VALUES (?,?,?,?,?,?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, a.getApplicationCode());
            ps.setInt(2, a.getCandidateId());
            ps.setInt(3, a.getJobId());
            ps.setString(4, a.getEligibilityStatus());
            ps.setString(5, a.getEligibilityReason());
            ps.setString(6, a.getApplicationStatus());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                return rs.next() ? rs.getInt(1) : 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Application save failed", e);
        }
    }

    @Override
    public boolean exists(int candidateId, int jobId) {
        String sql = "SELECT 1 FROM applications WHERE candidate_id=? AND job_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, candidateId);
            ps.setInt(2, jobId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Duplicate application check failed", e);
        }
    }

    @Override
    public Application findById(int applicationId) {
        String sql = "SELECT * FROM applications WHERE application_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, applicationId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? map(rs) : null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Application lookup failed", e);
        }
    }

    @Override
    public List<Application> findByCandidate(int candidateId) {
        List<Application> list = new ArrayList<>();
        String sql = "SELECT * FROM applications WHERE candidate_id=? ORDER BY application_id DESC";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, candidateId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(map(rs));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException("Unable to load applications", e);
        }
    }

    @Override
    public boolean updateStatus(int applicationId, String newStatus) {
        String sql = "UPDATE applications SET application_status=? WHERE application_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, newStatus);
            ps.setInt(2, applicationId);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException("Application status update failed", e);
        }
    }

    private Application map(ResultSet rs) throws SQLException {
        Application a = new Application();
        a.setApplicationId(rs.getInt("application_id"));
        a.setApplicationCode(rs.getString("application_code"));
        a.setCandidateId(rs.getInt("candidate_id"));
        a.setJobId(rs.getInt("job_id"));
        Timestamp ts = rs.getTimestamp("applied_at");
        if (ts != null) a.setAppliedAt(ts.toLocalDateTime());
        a.setEligibilityStatus(rs.getString("eligibility_status"));
        a.setEligibilityReason(rs.getString("eligibility_reason"));
        a.setApplicationStatus(rs.getString("application_status"));
        return a;
    }
}
