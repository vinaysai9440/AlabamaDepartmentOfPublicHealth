package service;

public interface CaseService
{
    Long createSnapCase(String countyCd, String officeCd, String createdBy) throws Exception;
}
