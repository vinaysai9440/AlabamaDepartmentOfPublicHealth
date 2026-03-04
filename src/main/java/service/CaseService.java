package service;

import java.util.Date;

public interface CaseService {
    Long createSnapCase(String countyCd, String officeCd, Date applicationDt, String createdBy) throws Exception;
}
