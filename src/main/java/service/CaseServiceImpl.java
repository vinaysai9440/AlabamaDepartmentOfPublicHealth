package service;

import dao.IESCaseDao;
import dao.impl.IESCaseDaoImpl;
import entity.IESCase;
import util.DBConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class CaseServiceImpl implements CaseService {

    private IESCaseDao caseDao = new IESCaseDaoImpl();

    public Long createSnapCase(String countyCd, String officeCd, String createdBy) throws Exception {
        Connection con = null;

        try {
            con = DBConnectionManager.getInstance().getConnection();
            con.setAutoCommit(false); // transaction start

            IESCase c = new IESCase();
            c.setProgramCd("SNAP");
            c.setCaseStatusCd("INTAKE_CREATED");
            c.setCountyCd(countyCd);
            c.setOfficeCd(officeCd);
            c.setApplicationDt(new Date());
            c.setActiveYn("Y");
            c.setCreatedBy(createdBy);

            Long caseId = caseDao.createCase(con, c);

            con.commit(); // transaction success
            return caseId;

        } catch (Exception e) {
            if (con != null) {
                try { con.rollback(); } catch (SQLException ex) { /* log */ }
            }
            throw e;
        } finally {
            if (con != null) {
                try { con.close(); } catch (SQLException ex) { /* log */ }
            }
        }
    }
}
