package dao.impl;

import dao.IESCaseDao;
import entity.IESCase;

import java.sql.*;
import java.util.Date;

public class IESCaseDaoImpl implements IESCaseDao {

    public Long createCase(Connection con, IESCase iesCase) throws SQLException {

        Long caseId = getNextCaseId(con);

        // generate CASE_NUM using CASE_ID (realistic + simple)
        String caseNum = generateCaseNum(caseId);

        String sql = "INSERT INTO IES_CASE " +
                "(CASE_ID, CASE_NUM, PROGRAM_CD, CASE_STATUS_CD, COUNTY_CD, OFFICE_CD, " +
                " APPLICATION_DT, ACTIVE_YN, CREATED_BY, CREATED_TS) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, SYSTIMESTAMP)";

        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);

            ps.setLong(1, caseId);
            ps.setString(2, caseNum);
            ps.setString(3, iesCase.getProgramCd());        // 'SNAP'
            ps.setString(4, iesCase.getCaseStatusCd());     // 'INTAKE_CREATED'
            ps.setString(5, iesCase.getCountyCd());
            ps.setString(6, iesCase.getOfficeCd());

            // applicationDt required
            java.sql.Date appDt = new java.sql.Date(iesCase.getApplicationDt().getTime());
            ps.setDate(7, appDt);

            ps.setString(8, iesCase.getActiveYn() == null ? "Y" : iesCase.getActiveYn());
            ps.setString(9, iesCase.getCreatedBy());

            ps.executeUpdate();

            iesCase.setCaseId(caseId);
            iesCase.setCaseNum(caseNum);

            return caseId;
        } finally {
            if (ps != null) ps.close();
        }
    }

    public IESCase findByCaseId(Connection con, Long caseId) throws SQLException {
        String sql = "SELECT CASE_ID, CASE_NUM, PROGRAM_CD, CASE_STATUS_CD, COUNTY_CD, OFFICE_CD, " +
                "APPLICATION_DT, ACTIVE_YN, CREATED_BY, CREATED_TS, UPDATED_BY, UPDATED_TS " +
                "FROM IES_CASE WHERE CASE_ID = ?";

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = con.prepareStatement(sql);
            ps.setLong(1, caseId);
            rs = ps.executeQuery();

            if (!rs.next()) return null;

            IESCase c = new IESCase();
            c.setCaseId(rs.getLong("CASE_ID"));
            c.setCaseNum(rs.getString("CASE_NUM"));
            c.setProgramCd(rs.getString("PROGRAM_CD"));
            c.setCaseStatusCd(rs.getString("CASE_STATUS_CD"));
            c.setCountyCd(rs.getString("COUNTY_CD"));
            c.setOfficeCd(rs.getString("OFFICE_CD"));
            c.setApplicationDt(rs.getDate("APPLICATION_DT"));
            c.setActiveYn(rs.getString("ACTIVE_YN"));
            c.setCreatedBy(rs.getString("CREATED_BY"));
            c.setCreatedTs(rs.getTimestamp("CREATED_TS"));
            c.setUpdatedBy(rs.getString("UPDATED_BY"));
            c.setUpdatedTs(rs.getTimestamp("UPDATED_TS"));

            return c;
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }
    }

    private Long getNextCaseId(Connection con) throws SQLException {
        Statement st = null;
        ResultSet rs = null;
        try {
            st = con.createStatement();
            rs = st.executeQuery("SELECT SEQ_IES_CASE.NEXTVAL FROM DUAL");
            rs.next();
            return rs.getLong(1);
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
        }
    }

    private String generateCaseNum(Long caseId) {
        // YYYY-########
        java.util.Calendar cal = java.util.Calendar.getInstance();
        int year = cal.get(java.util.Calendar.YEAR);

        String idStr = String.valueOf(caseId);
        String padded = leftPad(idStr, 8, '0');
        return year + "-" + padded;
    }

    private String leftPad(String s, int len, char ch) {
        if (s == null) s = "";
        if (s.length() >= len) return s;

        StringBuffer sb = new StringBuffer();
        for (int i = s.length(); i < len; i++) sb.append(ch);
        sb.append(s);
        return sb.toString();
    }
}