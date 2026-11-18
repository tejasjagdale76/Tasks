package com.kiranacademy.rpms.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.kiranacademy.rpms.model.Job;
import com.kiranacademy.rpms.util.DBConnection;

public class JobDAOImpl implements JobDAO {

    @Override
    public Job findById(int jobId) {
        String sql = "SELECT * FROM jobs WHERE job_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, jobId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? map(rs) : null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find job", e);
        }
    }

    @Override
    public List<Job> findOpenJobs() {
        List<Job> jobs = new ArrayList<>();
        String sql = "SELECT * FROM jobs WHERE status='OPEN' ORDER BY job_id";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) jobs.add(map(rs));
            return jobs;
        } catch (SQLException e) {
            throw new RuntimeException("Unable to load open jobs", e);
        }
    }

    private Job map(ResultSet rs) throws SQLException {
        Job j = new Job();
        j.setJobId(rs.getInt("job_id"));
        j.setJobCode(rs.getString("job_code"));
        j.setCompanyId(rs.getInt("company_id"));
        j.setJobTitle(rs.getString("job_title"));
        j.setLocation(rs.getString("location"));
        j.setOpenings(rs.getInt("openings"));
        j.setMinPackageLpa(rs.getDouble("min_package_lpa"));
        j.setMaxPackageLpa(rs.getDouble("max_package_lpa"));
        j.setMinPercentage(rs.getDouble("min_percentage"));
        j.setMinPassingYear(rs.getInt("min_passing_year"));
        j.setMaxPassingYear(rs.getInt("max_passing_year"));
        j.setMaxBacklogs(rs.getInt("max_backlogs"));
        Date d = rs.getDate("application_deadline");
        if (d != null) j.setApplicationDeadline(d.toLocalDate());
        j.setStatus(rs.getString("status"));
        return j;
    }
}
