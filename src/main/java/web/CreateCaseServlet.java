package web;

import service.CaseService;
import service.CaseServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateCaseServlet extends HttpServlet {

    private CaseService caseService = new CaseServiceImpl();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/createCase.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String countyCd = trim(req.getParameter("countyCd"));
        String officeCd = trim(req.getParameter("officeCd"));
        String applicationDtStr = trim(req.getParameter("applicationDt"));
        String createdBy = trim(req.getParameter("createdBy"));

        req.setAttribute("countyCd", countyCd);
        req.setAttribute("officeCd", officeCd);
        req.setAttribute("applicationDt", applicationDtStr);
        req.setAttribute("createdBy", createdBy);

        if (isBlank(countyCd) || isBlank(officeCd) || isBlank(applicationDtStr) || isBlank(createdBy)) {
            req.setAttribute("errorMsg", "County, Office, Application Date, and Created By are required.");
            req.getRequestDispatcher("/WEB-INF/jsp/createCase.jsp").forward(req, resp);
            return;
        }

        Date applicationDt;
        try {
            applicationDt = parseDate(applicationDtStr);
        } catch (ParseException e) {
            req.setAttribute("errorMsg", "Application Date must be in YYYY-MM-DD format.");
            req.getRequestDispatcher("/WEB-INF/jsp/createCase.jsp").forward(req, resp);
            return;
        }

        try {
            Long caseId = caseService.createSnapCase(countyCd, officeCd, applicationDt, createdBy);

            req.setAttribute("caseId", caseId);
            req.setAttribute("successMsg", "SNAP case created successfully.");
            req.getRequestDispatcher("/WEB-INF/jsp/caseConfirmation.jsp").forward(req, resp);

        } catch (Exception e) {
            req.setAttribute("errorMsg", "Failed to create case: " + e.getMessage());
            req.getRequestDispatcher("/WEB-INF/jsp/createCase.jsp").forward(req, resp);
        }
    }

    private Date parseDate(String dateValue) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        return sdf.parse(dateValue);
    }

    private String trim(String value) {
        return value == null ? null : value.trim();
    }

    private boolean isBlank(String value) {
        return value == null || value.length() == 0;
    }
}
