package web;

import dao.IESCaseDao;
import dao.impl.IESCaseDaoImpl;
import service.CaseService;
import service.CaseServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateCaseServlet extends HttpServlet
{

    private CaseService caseService = new CaseServiceImpl();

    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletRequest.getRequestDispatcher("/WEB-INF/jsp/createCase.jsp").forward(httpServletRequest,httpServletResponse);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String countyCd = req.getParameter("countyCd");
        String officeCd = req.getParameter("officeCd");

        // In real app, this comes from logged-in user session
        String createdBy = "caseworker1";

        try {
            Long caseId = caseService.createSnapCase(countyCd, officeCd, createdBy);

            req.setAttribute("caseId", caseId);
            req.getRequestDispatcher("/WEB-INF/jsp/caseConfirmation.jsp").forward(req, resp);

        } catch (Exception e) {
            req.setAttribute("errorMsg", "Failed to create case: " + e.getMessage());
            req.getRequestDispatcher("/WEB-INF/jsp/createCase.jsp").forward(req, resp);
        }
    }
}
