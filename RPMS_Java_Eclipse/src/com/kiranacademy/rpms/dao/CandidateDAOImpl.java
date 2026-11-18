package com.kiranacademy.rpms.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.kiranacademy.rpms.model.Candidate;
import com.kiranacademy.rpms.util.DBConnection;

public class CandidateDAOImpl implements CandidateDAO {

    @Override
    public int save(Candidate c) {
        String sql = "INSERT INTO candidates " +
                "(candidate_code, full_name, email, mobile, qualification, passing_year, " +
                "percentage, backlog_count, primary_technology, profile_status, placement_status) " +
                "VALUES (?,?,?,?,?,?,?,?,?,'ACTIVE','NOT_PLACED')";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, c.getCandidateCode());
            ps.setString(2, c.getFullName());
            ps.setString(3, c.getEmail());
            ps.setString(4, c.getMobile());
            ps.setString(5, c.getQualification());
            ps.setInt(6, c.getPassingYear());
            ps.setDouble(7, c.getPercentage());
            ps.setInt(8, c.getBacklogCount());
            ps.setString(9, c.getPrimaryTechnology());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
            return 0;

        } catch (SQLException e) {
            throw new RuntimeException("Unable to save candidate: " + e.getMessage(), e);
        }
    }

    @Override
    public Candidate findById(int id) {
        String sql = "SELECT * FROM candidates WHERE candidate_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? map(rs) : null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find candidate", e);
        }
    }

    @Override
    public Candidate findByEmail(String email) {
        return findOne("SELECT * FROM candidates WHERE email=?", email);
    }

    @Override
    public Candidate findByMobile(String mobile) {
        return findOne("SELECT * FROM candidates WHERE mobile=?", mobile);
    }

    private Candidate findOne(String sql, String value) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, value);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? map(rs) : null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Candidate lookup failed", e);
        }
    }

    @Override
    public List<Candidate> findAll() {
        List<Candidate> list = new ArrayList<>();
        String sql = "SELECT * FROM candidates ORDER BY candidate_id";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) list.add(map(rs));
            return list;

        } catch (SQLException e) {
            throw new RuntimeException("Unable to load candidates", e);
        }
    }

    @Override
    public List<Candidate> findByTechnology(String technology) {
        List<Candidate> list = new ArrayList<>();
        String sql = "SELECT * FROM candidates WHERE primary_technology=? ORDER BY full_name";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, technology);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(map(rs));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException("Technology search failed", e);
        }
    }

    @Override
    public boolean update(Candidate c) {
        String sql = "UPDATE candidates SET full_name=?, qualification=?, passing_year=?, " +
                     "percentage=?, backlog_count=?, primary_technology=? WHERE candidate_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getFullName());
            ps.setString(2, c.getQualification());
            ps.setInt(3, c.getPassingYear());
            ps.setDouble(4, c.getPercentage());
            ps.setInt(5, c.getBacklogCount());
            ps.setString(6, c.getPrimaryTechnology());
            ps.setInt(7, c.getCandidateId());

            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException("Candidate update failed", e);
        }
    }

    @Override
    public boolean deactivate(int candidateId) {
        String sql = "UPDATE candidates SET profile_status='INACTIVE' WHERE candidate_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, candidateId);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException("Candidate deactivate failed", e);
        }
    }

    private Candidate map(ResultSet rs) throws SQLException {
        Candidate c = new Candidate();
        c.setCandidateId(rs.getInt("candidate_id"));
        c.setCandidateCode(rs.getString("candidate_code"));
        c.setFullName(rs.getString("full_name"));
        c.setEmail(rs.getString("email"));
        c.setMobile(rs.getString("mobile"));
        c.setQualification(rs.getString("qualification"));
        c.setPassingYear(rs.getInt("passing_year"));
        c.setPercentage(rs.getDouble("percentage"));
        c.setBacklogCount(rs.getInt("backlog_count"));
        c.setPrimaryTechnology(rs.getString("primary_technology"));
        c.setProfileStatus(rs.getString("profile_status"));
        c.setPlacementStatus(rs.getString("placement_status"));
        return c;
    }
}
