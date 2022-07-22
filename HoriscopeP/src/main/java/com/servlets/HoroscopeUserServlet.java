package com.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.models.HoroscopeUser;
import com.persistence.HoroscopeUserDAO;
import com.utils.CurrentUser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import static com.utils.CurrentUser.password;
import static com.utils.CurrentUser.username;

public class HoroscopeUserServlet extends HttpServlet {

    HoroscopeUserDAO userDAO = new HoroscopeUserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
        processUserLogin(req, resp);

    }

    private void processUserLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{

        BufferedReader br = req.getReader();
        String line = br.readLine();
        StringBuilder sb = new StringBuilder();



        //here we are going to read through each line of our req.getReader
//        it is possible for there to only be one line in our br
//        but creating a while loop is the safest way
        // here we are iterating through our buffered readLine
        while (line!=null){
            sb.append(line);
            line = br.readLine();
        }
        System.out.println(sb);
        //toString returns it as String
        String body = sb.toString();
//        will split by commas
        String[] sepByComma = body.split(",");
        //creating an array list of string calling it values
        ArrayList<String> values = new ArrayList<>();

        for(String value: sepByComma){
            // this will print out the information from the page
//            System.out.println(value);

            // here we want to trim all the excess symbols as well as the
            //keys and keep the values
            //note we can add multiple replaceAll functions
            // this is replace all the given characters
            value = value.replaceAll("\\{","").replaceAll("}","").replaceAll("\"","");
            System.out.println(value);

            //This will get rid of email:
            String target = value.substring(value.indexOf(":") + 1);

            System.out.println(target);
            values.add(target);

        }
        String username = values.get(0);
        String password = values.get(1);
        // here we are doing the logic to actually log in
        HoroscopeUser horoscopeUser = new HoroscopeUserDAO().loginHoroscopeUser(username, password);
        // I was able to get a user, it is not equal to null
        if(horoscopeUser != null){
            resp.setStatus(200);

            //get an HTTP session

            HttpSession session = req.getSession();

            session.setAttribute("user", horoscopeUser);

            PrintWriter out = resp.getWriter();
            resp.setContentType("application/json");
            resp.addHeader("Access-Control-Allow-Origin","*");


            ObjectMapper om = new ObjectMapper();

            //convert the Java object to a JSON object with the object mapper
            out.println(om.writeValueAsString(horoscopeUser));

            System.out.println("The user with username : " + username + " has logged in");

        }else{
            resp.setStatus(204);
        }

    }
}