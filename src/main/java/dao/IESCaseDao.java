package dao;

import entity.IESCase;

import java.sql.Connection;
import java.sql.SQLException;

public interface IESCaseDao {
    Long createCase(Connection con, IESCase iesCase) throws SQLException;
    IESCase findByCaseId(Connection con, Long caseId) throws SQLException;
}
