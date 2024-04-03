package com.Hayrama.configue;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.BufferedReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashComparisonFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestBody = extractRequestBody(request);
        String hashHeaderValue = request.getHeader("Hash"); // Assuming the hash is sent in an HTTP header called "Hash"
        System.out.println("doFilterInternal: " + hashHeaderValue);
        // Compute the hash of the request body
        String hashedBody = hashString(requestBody);

        // Compare the computed hash with the hash sent in the header
        if (hashedBody.equals(hashHeaderValue)) {
            filterChain.doFilter(request, response); // Allow the request to proceed
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN); // Reject the request
            response.getWriter().write("Hashes do not match");
        }
    }

    private String extractRequestBody(HttpServletRequest request) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

    private String hashString(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            byte[] hash = digest.digest(input.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}

