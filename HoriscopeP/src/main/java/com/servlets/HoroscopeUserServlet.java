package com.servlets;

import com.models.HoroscopeUser;
import com.persistence.HoroscopeUserDAO;
import com.utils.CurrentUser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HoroscopeUserServlet extends HttpServlet {

    HoroscopeUserDAO userDAO = new HoroscopeUserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);

        String username = String.valueOf(req.getParameter("username-sign-in"));
        String pass_word = String.valueOf(req.getParameter("password-sign-in"));

        CurrentUser.currentUser=userDAO.loginHoroscopeUser(username,pass_word);


        //Here I am redirecting the client to the homepagehomepage.html page
        req.getRequestDispatcher("homepagehomepage.html").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
        String username = String.valueOf(req.getParameter("username-input"));
        String passw = String.valueOf(req.getParameter("password-input"));

        HoroscopeUser newUser = new HoroscopeUser(username, passw);
        try {
            userDAO.create(newUser);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //this is just setting the status
        resp.setStatus(203);
        //this is redirecting us to the index.html page aka our login page
        req.getRequestDispatcher("index.html").forward(req,resp);
    }
}