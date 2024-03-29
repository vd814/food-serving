package com.swing.foodserving.handler;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;


public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter{

	/*@Autowired
	JwtUtil jwtUtil; */

	public JwtAuthenticationFilter() {
		super("/**");
	}

	@Override
	protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
		return true;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		String header = request.getHeader("Authorization");
		if (header == null || !header.startsWith("Bearer ")) {
			//throw new JwtTokenMissingException("No JWT token found in request headers");
		}

		String authToken = header.substring(7);
		//User user = jwtUtil.parseToken(authToken);
		/*if(user != null) {
			request.setAttribute("userId", user.getUserId());
		}*/
		//JwtAuthenticationToken authRequest = new JwtAuthenticationToken(authToken);
		// authRequest.
		return null;
		//return getAuthenticationManager().authenticate(authRequest);
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		JSONObject blankObject = new JSONObject();
		response.setContentType("application/json");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		//response.getOutputStream().println(new Gson().toJson(Wrapper.responseFailed(blankObject,failed.getMessage())));
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		super.successfulAuthentication(request, response, chain, authResult);

		// As this authentication is in HTTP header, after success we need to continue the request normally
		// and return the response as if the resource was not secured at all
		response.addHeader("Access-Control-Allow-Methods","GET,PUT,POST,DELETE");
		chain.doFilter(request, response);	
	}



}

