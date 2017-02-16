package com.weather.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.weather.model.WeatherData;


/**
 * Servlet implementation class WeatherServlet
 */
@WebServlet(description = "To intercept weather information requests", urlPatterns = { "/WeatherServlet" })
public class WeatherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WeatherServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("<html><body>Hello..</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dateFormat = "dd-MM-yyyy hh:mm";
	    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
		String cityName = request.getParameter("city");
		response.setContentType("text/html");
		String apiID = request.getServletContext().getInitParameter("APP_ID");
		String apiURLFar = request.getServletContext().getInitParameter("WEATHER_URL_FAR");
		String apiURLCel = request.getServletContext().getInitParameter("WEATHER_URL_CEL");
		String farResult = getResultsFromWaeatherAPI(apiURLFar,apiID,cityName);
		String celResult = getResultsFromWaeatherAPI(apiURLCel,apiID,cityName);
		//String result = getResultsFromJSON();
		
		WeatherData farData = getWeatherData(farResult);
		WeatherData celData = getWeatherData(celResult);
		Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.parseLong(farData.getDt().toString()));
        String date = simpleDateFormat.format(calendar.getTime());
		
		//PrintWriter out = response.getWriter();
		//out.print("<html><body>Hello CHetan"+ cityName +"</body></html>");
		request.setAttribute("farData", farData);
		request.setAttribute("celData", celData);
		request.setAttribute("date", date);
		request.getRequestDispatcher("/results.jsp").forward(request, response);
	}

	private WeatherData getWeatherData(String result) {
		WeatherData wd = new WeatherData();
		JSONObject jObject  = new JSONObject(result);
		JSONArray weather = jObject.getJSONArray("list");
		JSONObject data = weather.getJSONObject(0);
		JSONObject main = data.getJSONObject("main"); // get data object
		JSONObject sys = data.getJSONObject("sys");
		JSONArray wa = data.getJSONArray("weather");
		JSONObject wind = data.getJSONObject("wind");
		wd.setMinTemp(main.getDouble("temp_min"));
		wd.setMaxTemp(main.getDouble("temp_max"));
		//wd.setSunrise(sys.getLong("sunrise"));
		//wd.setSunset(sys.getLong("sunset"));
		JSONObject summary = wa.getJSONObject(0);
		wd.setSummary(summary.getString("description"));
		wd.setSpeed(wind.getDouble("speed"));
		wd.setName(data.getString("name"));
		wd.setDt(data.getLong("dt"));
		return wd;
	}

	private String getResultsFromJSON() {
		StringBuilder result = null;
		String line;
		InputStream stream = getServletContext().getResourceAsStream("/WEB-INF/results.json");
		InputStreamReader reader = new InputStreamReader(stream);
		BufferedReader bf = new BufferedReader(reader);
		result = new StringBuilder();
        try {
			while ( (line = bf.readLine()) != null ) {
			    result.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
        if(result!=null){
        	return result.toString();
        }else{
        	return "";
        }
	}

	private String getResultsFromWaeatherAPI(String apiURL, String apiID, String cityName) {
		StringBuilder result = null;
        String line;
        URL url;
        URLConnection conn;
        BufferedReader in = null;
        try {
            url = new URL(apiURL + "&appid=" +apiID + "&q=" +cityName);
            conn = url.openConnection();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            result = new StringBuilder();
            while ( (line = in.readLine()) != null ) {
                result.append(line);
            }
            
        }catch (MalformedURLException ex) {
            System.err.println(ex);
        } catch (IOException ex) {
            System.err.println(ex);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ex) {
                    System.err.println(ex);
                }
            }
        }
		return result.toString();
	}

}
