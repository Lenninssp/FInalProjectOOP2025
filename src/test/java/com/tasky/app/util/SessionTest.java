package com.tasky.app.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SessionTest {
    @Test
    public void testRedirectWhenNoSessionOrUserId() throws IOException {
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        HttpServletResponse mockResponse = mock(HttpServletResponse.class);

        when(mockRequest.getSession(false)).thenReturn(null);

        Integer result = Session.getLoggedUserId(mockRequest, mockResponse);

        assertNull(result);
        verify(mockResponse).sendRedirect("login.jsp");
    }

    @Test
    public void testReturnsUserIdWhenSessionIsValid() throws IOException {
        HttpSession mockSession = mock(HttpSession.class);
        when(mockSession.getAttribute("userId")).thenReturn(42);

        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        HttpServletResponse mockResponse = mock(HttpServletResponse.class);
        when(mockRequest.getSession(false)).thenReturn(mockSession);

        Integer result = Session.getLoggedUserId(mockRequest, mockResponse);

        assertEquals(42, result);
        verify(mockResponse, never()).sendRedirect(anyString());
    }
}

